package com.festcampus.w10_project_board.main.controller;

import com.festcampus.w10_project_board.board.dto.response.ArticleCommentResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * packageName  : com.festcampus.w10_project_board.main.controller
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

    /**
     * 사이트 메인페이지 SEO 설정을 같이 넘기는 것을 참조
     *
     * @param
     * @return
     *
     */
    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("seoAuthor", "By Daniel Park (Park jong chul)");
        model.addAttribute("seoGenerator", "By IntelliJ IDEA");
        model.addAttribute("seoDescript", "SEO  사이트 설명 입력할 곳");
        model.addAttribute("seoKeyword", "SEO 키워드 입력할 곳");

        model.addAttribute("title", "패스트캠퍼스 게시판 프로젝트");

        return "index";
    }

}
