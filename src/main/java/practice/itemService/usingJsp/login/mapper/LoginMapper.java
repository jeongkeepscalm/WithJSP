package practice.itemService.usingJsp.login.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    // 아이디 중복체크
    int idDuplicationCheck(String id);
}
