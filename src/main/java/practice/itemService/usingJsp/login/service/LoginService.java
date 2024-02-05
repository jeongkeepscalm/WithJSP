package practice.itemService.usingJsp.login.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import practice.itemService.usingJsp.exception.CustomBindingResultException;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.menu.vo.MenuVO;

import java.util.List;

@Service
public interface LoginService {

    // 모든 회원 조회
    User selectUserDetail(String id);

    // 모든 회원 수 조회
    int selectAllUserCount() throws Exception;

    // 회원 등록
    List<User> selectAllUser() throws Exception;

    // 회원 상세 조회
    boolean insertUser(SaveUserRequest saveUserRequest);

    // 회원 삭제
    boolean deleteUser(String id) throws Exception;

    // 회원 정보 수정
    boolean updateUser(SaveUserRequest saveUserRequest) throws Exception;

    // 회원인지 확인
    User checkIfUser(LoginRequest loginRequest) throws Exception;

    // 로그인 프로세스
    User loginProcess(LoginRequest loginRequest, BindingResult bindingResult) throws CustomBindingResultException;

    // 회원가입 프로세스
    void signUpProcess(SaveUserRequest saveUserRequest, BindingResult bindingResult) throws CustomBindingResultException;
}
