package practice.itemService.usingJsp.login.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public List<User> selectAllUser() {
        return loginMapper.selectAllUser();
    }

    @Override
    public int selectAllUserCount() {
        return loginMapper.selectAllUserCount();
    }

    @Override
    public boolean insertUser(SaveUserRequest saveUserRequest) {
        return loginMapper.insertUser(saveUserRequest) > 0 ? true : false;
    }

    @Override
    public User selectUserDetail(String id) {
        return loginMapper.selectUserDetail(id);
    }

    @Override
    public boolean deleteUser(String id) {
        try {
            return loginMapper.deleteUser(id) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateUser(SaveUserRequest saveUserRequest) {
        return loginMapper.updateUser(saveUserRequest) > 0 ? true : false;
    }

    @Override
    public User checkIfUser(LoginRequest loginRequest) {
        return loginMapper.checkIfUser(loginRequest);
    }

    @Override
    public List<MenuVO> selectAllMenu() {
        return null;
    }


}
