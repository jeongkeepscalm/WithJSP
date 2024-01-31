package practice.itemService.usingJsp.menu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import practice.itemService.usingJsp.menu.service.MenuServiceImpl;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuServiceImpl menuService;

    // 메인 페이지
    @GetMapping("/main")
    public String mainPage(Model model) {
        menuService.putMenuListToModel(model);
        return "main";
    }

}
