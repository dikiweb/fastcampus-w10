package com.festcampus.w10_project_board.common.entity.constant;

import lombok.Getter;

/**
 * packageName   : com.festcampus.w10_project_board.common.entity.constant
 * fileName     : FormStatus
 * author       : danny
 * date         : 2024-06-07
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-07          danny         최초 생성
 */
public enum FormStatus {

    CREATE("저장", false),
    UPDATE("수정", true);

    @Getter
    private final String description;
    @Getter
    private final Boolean update;

    FormStatus(String description, Boolean update) {
        this.description = description;
        this.update = update;
    }
}
