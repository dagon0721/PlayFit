package com.daelim.board_back.dto.object;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@NoArgsConstructor
public class LikedBoardDto {
    private int boardNumber;
    private String title;
    private String content;
    private String boardTitleImage;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writeDatetime; // String 타입으로 유지
    private String writerNickname;
    private String writerProfileImage;

    public LikedBoardDto(Object[] result) {
        this.boardNumber = (Integer) result[0];
        this.title = (String) result[1];
        this.content = (String) result[2];
        this.boardTitleImage = (String) result[3];
        this.favoriteCount = (Integer) result[4];
        this.commentCount = (Integer) result[5];
        this.viewCount = (Integer) result[6];

        // write_datetime을 Timestamp로 받아서 String으로 변환
        Timestamp timestamp = (Timestamp) result[7];
        this.writeDatetime = formatTimestamp(timestamp); // Timestamp를 String으로 변환

        this.writerNickname = (String) result[8];
        this.writerProfileImage = (String) result[9];
    }

    private String formatTimestamp(Timestamp timestamp) {
        if (timestamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 원하는 포맷으로 변경 가능
            return sdf.format(timestamp);
        }
        return null; // null 처리
    }
}
