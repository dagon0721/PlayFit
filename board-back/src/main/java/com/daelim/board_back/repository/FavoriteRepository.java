package com.daelim.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import javax.transaction.Transactional;

import com.daelim.board_back.dto.object.BoardListItem;
import com.daelim.board_back.entity.BoardListViewEntity;
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

    @Query(
    value = 
    "SELECT B.board_number, B.title, B.content, " +
    "I.image AS title_image, " + // title_image로 변경
    "B.favorite_count, B.comment_count, B.view_count, " +
    "B.write_datetime, U.nickname, U.profile_image " +
    "FROM board AS B " +
    "INNER JOIN favorite AS F ON B.board_number = F.board_number " +
    "INNER JOIN user AS U ON B.writer_email = U.email " +
    "LEFT JOIN image AS I ON B.board_number = I.board_number " + // 이미지 조인 추가
    "WHERE F.user_email = :userEmail",
    nativeQuery = true
)
List<Object[]> findLikedBoardsByUserEmail(@Param("userEmail") String userEmail);



    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
}
