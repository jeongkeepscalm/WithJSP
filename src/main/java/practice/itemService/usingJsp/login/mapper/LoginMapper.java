package practice.itemService.usingJsp.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice.itemService.usingJsp.login.dto.LoginRequest;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;

import java.util.List;

@Mapper
public interface LoginMapper {

    // 모든 유저 조회
    List<User> selectAllUser();

    // 모든 유저 수 조회
    int selectAllUserCount();

    // 회원 등록
    int insertUser(SaveUserRequest saveUserRequest);

    // 회원 상세 조회
    User selectUserDetail(String id);

    // 회원 삭제
    int deleteUser(String id);

    // 회원 정보 수정
    int updateUser(SaveUserRequest saveUserRequest);

    // 회원인지 확인
    User checkIfUser(LoginRequest loginRequest);



}
