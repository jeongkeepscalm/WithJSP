package practice.itemService.usingJsp.login.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import practice.itemService.usingJsp.AES256;
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
    public boolean insertUser(SaveUserRequest saveUserRequest) throws Exception {

        // 비밀번호 암호화
        String encryptedPassword = AES256.encrypt(saveUserRequest.getPassword());
        saveUserRequest.setPassword(encryptedPassword);

        return loginMapper.insertUser(saveUserRequest) > 0 ? true : false;
    }

    @Override
    public User selectUserDetail(String id) throws Exception {
        return loginMapper.selectUserDetail(id);
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

        return user;

    }



}
