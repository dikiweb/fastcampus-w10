package com.festcampus.w10_project_board.common.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

/**
 * packageName   : com.festcampus.w10_project_board.common.service
 * fileName     : PaginationService
 * author       : danny
 * date         : 2024-06-05
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-05          danny         최초 생성
 */
@Service
public class PaginationService {

    private static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBara(int currentPageNumber, int totalPages) {

        // 중앙값을 찾아서 넣어주자
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    public int currentBarLength() {
        return BAR_LENGTH;
    }

}
