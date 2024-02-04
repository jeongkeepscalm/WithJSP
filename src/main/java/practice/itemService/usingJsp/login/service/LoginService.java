package practice.itemService.usingJsp.login.service;

import org.springframework.stereotype.Service;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.menu.vo.MenuVO;

import java.util.List;

@Service
public interface LoginService {

    // 모든 회원 조회
    User selectUserDetail(String id) throws Exception;

    // 모든 회원 수 조회
    int selectAllUserCount() throws Exception;

    // 회원 등록
    List<User> selectAllUser() throws Exception;

    // 회원 상세 조회
    boolean insertUser(SaveUserRequest saveUserRequest) throws Exception;

    // 회원 삭제
    boolean deleteUser(String id) throws Exception;

    // 회원 정보 수정
    boolean updateUser(SaveUserRequest saveUserRequest) throws Exception;

    // 회원인지 확인
    User checkIfUser(LoginRequest loginRequest) throws Exception;


}
