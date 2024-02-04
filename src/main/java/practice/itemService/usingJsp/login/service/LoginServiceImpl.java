package practice.itemService.usingJsp.login.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.itemService.usingJsp.AES256;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.login.mapper.LoginMapper;
import practice.itemService.usingJsp.menu.vo.MenuVO;

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
    public User checkIfUser(LoginRequest loginRequest) throws Exception {

        // 요청된 비밀번호를 암호화한 뒤, DB에 저장된 비밀번호와 비교한다.
        String encryptedPassword = AES256.encrypt(loginRequest.getPassword());
        loginRequest.setPassword(encryptedPassword);

        return loginMapper.checkIfUser(loginRequest);
    }


}
