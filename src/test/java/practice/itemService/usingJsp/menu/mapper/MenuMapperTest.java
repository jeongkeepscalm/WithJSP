package practice.itemService.usingJsp.menu.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practice.itemService.usingJsp.menu.vo.MenuVO;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootTest
class MenuMapperTest {

    @Autowired
    MenuMapper menuMapper;

    @Test
    void 모든메뉴조회() {

        HashMap<String, Object> map = new HashMap<>();

        List<MenuVO> menuList = menuMapper.selectAllMenu();

        Map<Integer, List<MenuVO>> menuDepthList
                = menuList.stream().collect(Collectors.groupingBy(MenuVO::getMenuDepth));

        menuDepthList.values().stream().collect(Collectors.toList()).forEach(menus -> {
            Integer menuDepth = menus.get(0).getMenuDepth();
            String key = "menuDepth" + String.valueOf(menuDepth);
            map.put(key, menus);
        });

        System.out.println(map);
    }

}