package com.festcampus.w10_project_board.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName   : com.festcampus.w10_project_board.main.controller
 * fileName     : MainController
 * author       : danny
 * date         : 2024-06-03
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-03          danny         최초 생성
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "index";
    }
}
