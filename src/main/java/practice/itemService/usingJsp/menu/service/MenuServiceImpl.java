package practice.itemService.usingJsp.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import practice.itemService.usingJsp.menu.mapper.MenuMapper;
import practice.itemService.usingJsp.menu.vo.MenuVO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final MenuMapper menuMapper;

    @Override
    public void putMenuListToModel(Model model) {

        List<MenuVO> menuList = menuMapper.selectAllMenu();

        Map<Integer, List<MenuVO>> menuDepthList
                = menuList.stream().collect(Collectors.groupingBy(MenuVO::getMenuDepth));

        menuDepthList.values().stream().collect(Collectors.toList()).forEach(menus -> {
            Integer menuDepth = menus.get(0).getMenuDepth();
            String key = "menuDepth" + String.valueOf(menuDepth);
            model.addAttribute(key, menus);
        });



    }

}
