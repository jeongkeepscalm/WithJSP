package practice.itemService.usingJsp.menu.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice.itemService.usingJsp.menu.vo.MenuVO;

import java.util.List;

@Mapper
public interface MenuMapper {

    // 모든 메뉴 조회
    List<MenuVO> selectAllMenu();

}
