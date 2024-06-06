package com.festcampus.w10_project_board.board.repository.querydsl;

import com.festcampus.w10_project_board.common.entity.Hashtag;
import com.festcampus.w10_project_board.common.entity.QHashtag;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * packageName   : com.festcampus.w10_project_board.board.repository.querydsl
 * fileName     : HashtagRepositoryCustomImpl
 * author       : danny
 * date         : 2024-06-06
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-06          danny         최초 생성
 */
public class HashtagRepositoryCustomImpl extends QuerydslRepositorySupport implements HashtagRepositoryCustom {

    public HashtagRepositoryCustomImpl() {
        super(Hashtag.class);
    }

    @Override
    public List<String> findAllHashtagNames() {

        QHashtag hashtag = QHashtag.hashtag;

        return from(hashtag)
                .select(hashtag.hashtagName)
                .fetch();
    }
}
