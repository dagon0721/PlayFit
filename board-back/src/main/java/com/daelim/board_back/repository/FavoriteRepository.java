package com.daelim.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import javax.transaction.Transactional;

import com.daelim.board_back.dto.object.BoardListItem;
import com.daelim.board_back.entity.FavoriteEntity;
import com.daelim.board_back.entity.primaryKey.FavoritePk;
import com.daelim.board_back.repository.resultSet.GetFavoriteListResultSet;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk>{
    
    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber, String userEmail);

    @Query(
        value=
        "   SELECT " +
        "   U.email AS email, " +
        "   U.nickname AS nickname, " +
        "   U.profile_image AS profileImage " +
        "FROM favorite AS F " +
        "INNER JOIN user AS U " +
        "ON F.user_email = U.email " +
        "where F.board_number = ?1",
        nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);

    // 사용자가 좋아요를 누른 게시물 목록을 가져오는 쿼리
    @Query(
        value = 
        "   SELECT " +
        "   B.board_number AS boardNumber, " +
        "   B.title AS title, " +
        "   B.content AS content, " +
        "   B.favorite_count AS favoriteCount, " +
        "   B.comment_count AS commentCount, " +
        "   B.view_count AS viewCount, " +
        "   B.write_datetime AS writeDatetime, " +
        "   U.nickname AS writerNickname, " +
        "   U.profile_image AS writerProfileImage " +
        "FROM favorite AS F " +
        "INNER JOIN board AS B " +
        "ON F.board_number = B.board_number " +
        "INNER JOIN user AS U " +
        "ON B.writer_email = U.email " +
        "WHERE F.user_email = ?1",
        nativeQuery = true
    )
    List<BoardListItem> getFavoriteBoardsByUserEmail(String userEmail);

    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
}
