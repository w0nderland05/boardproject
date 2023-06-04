package org.boardpj.controllers.admins;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.boardpj.commons.MenuDetail;
import org.boardpj.commons.Menus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("AdminBoardController")
@RequestMapping("/admin/bord")
@RequiredArgsConstructor
public class BoardController {

    private final HttpServletRequest request;

    /**
     * 게시판 목록
     * @return
     */
    @GetMapping
    public String index(Model model){
        commonProcess(model);
        return  "/admin/board/index";
    }

    private void commonProcess(Model model){
        //서브메뉴 처리
        List<MenuDetail>submenus = Menus.gets("board");
        model.addAttribute("submenus",submenus);
    }
}
