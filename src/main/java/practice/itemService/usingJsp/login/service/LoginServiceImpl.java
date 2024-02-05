package practice.itemService.usingJsp.login.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import practice.itemService.usingJsp.AES256;
import practice.itemService.usingJsp.AdminPasswordConst;
import practice.itemService.usingJsp.RememberMe;
import practice.itemService.usingJsp.exception.CustomBindingResultException;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.login.mapper.LoginMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    @Override
    public List<User> selectAllUser() throws Exception {
        return loginMapper.selectAllUser();
    }

    @Override
    public int selectAllUserCount() throws Exception {
        return loginMapper.selectAllUserCount();
    }

    @Override
    public boolean insertUser(SaveUserRequest saveUserRequest) {

        try {
            return loginMapper.insertUser(saveUserRequest) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Inserting user failed : {}", e);
        }

    }

    @Override
    public User selectUserDetail(String id) {
        try {
            return loginMapper.selectUserDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Getting user information failed : {}", e);
        }
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return loginMapper.deleteUser(id) > 0 ? true : false;
    }

    @Override
    public boolean updateUser(SaveUserRequest saveUserRequest) throws Exception {
        return loginMapper.updateUser(saveUserRequest) > 0 ? true : false;
    }

    @Override
    public User checkIfUser(LoginRequest loginRequest) {
        try {
            return loginMapper.checkIfUser(loginRequest);
        } catch (Exception e) {
            throw new RuntimeException("User validation failed.", e);
        }
    }

    @Override
    public User loginProcess(LoginRequest loginRequest, BindingResult bindingResult) throws CustomBindingResultException {

        if (bindingResult.hasErrors()) {
            throw new CustomBindingResultException(bindingResult);
        }

        String requestedPassword = loginRequest.getPassword();

        // 요청된 비밀번호를 암호화한 뒤, DB에 저장된 비밀번호와 비교한다.
        String encryptedPassword = AES256.encrypt(requestedPassword);
        loginRequest.setPassword(encryptedPassword);

        User user = this.checkIfUser(loginRequest);

        if (user == null) {

            // 요청된 비밀번호로 reset
            loginRequest.setPassword(requestedPassword);

            bindingResult.addError(new FieldError("user"
                    , "password"
                    , "* 아이디 또는 비밀번호가 맞지 않습니다."));

            throw new CustomBindingResultException(bindingResult);

        }

        RememberMe.writeCredentialsText(loginRequest);

        return user;

    }


    @Override
    public void signUpProcess(SaveUserRequest saveUserRequest, BindingResult bindingResult) throws CustomBindingResultException {


        // 관리자 체크 되어 있을 경우
        if (saveUserRequest.getIsAdmin().equals("Y")) {

            // 관리자 비밀번호를 입력하지 않았을 경우
            if (saveUserRequest.getAdminPassword().equals("")) {
                bindingResult.addError(new FieldError("user", "adminPassword", "* 비밀번호를 입력해 주세요"));
                throw new CustomBindingResultException(bindingResult);
            }

            // 관리자 비밀번호가 맞지 않을 경우
            if (!saveUserRequest.getAdminPassword().equals(AdminPasswordConst.ADMIN_PASSWORD)) {
                bindingResult.addError(new FieldError("user", "adminPassword", "* 비밀번호가 맞지 않습니다."));
                throw new CustomBindingResultException(bindingResult);
            }

        }

        // 중복되는 아이디가 있을 경우
        if (this.selectUserDetail(saveUserRequest.getId()) != null) {
            bindingResult.addError(new FieldError("user", "id", "* 중복되는 아이디가 있습니다."));
            throw new CustomBindingResultException(bindingResult);
        }

        if (bindingResult.hasErrors()) {
            throw new CustomBindingResultException(bindingResult);
        }

        // 비밀번호 암호화
        String encryptedPassword = AES256.encrypt(saveUserRequest.getPassword());
        saveUserRequest.setPassword(encryptedPassword);

        this.insertUser(saveUserRequest);




    }



}
