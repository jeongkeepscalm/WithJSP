package practice.itemService.usingJsp.menu.service;

import org.springframework.stereotype.Service;

import org.springframework.ui.Model;

@Service
public interface MenuService {

    // 모든 메뉴 조회
    void putMenuListToModel(Model model);


}
