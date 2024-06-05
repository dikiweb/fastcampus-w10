package com.festcampus.w10_project_board.main.controller;

import com.festcampus.w10_project_board.common.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName   : com.festcampus.w10_project_board.main.controller
 * fileName     : MainControllerTest
 * author       : danny
 * date         : 2024-06-03
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-03          danny         최초 생성
 */
@DisplayName("비지니스(기능) 로직 - 메인페이지 이동")
@Import(SecurityConfig.class)
@WebMvcTest(MainController.class)
class MainControllerTest {

    private final MockMvc mvc;

    public MainControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("메인페이지로 이동 한다")
    @Test
    void givenNotiong_whenRequestingRootPage_thenLodingIndexPage() throws Exception {
        // given

        //when & them
        mvc.perform(get("/")).andExpect(status().isOk());
    }

}