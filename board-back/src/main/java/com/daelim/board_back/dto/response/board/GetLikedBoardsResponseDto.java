package com.daelim.board_back.dto.response.board;

import com.daelim.board_back.common.ResponseCode;
import com.daelim.board_back.common.ResponseMessage;
import com.daelim.board_back.dto.object.LikedBoardDto; // LikedBoardDto 사용
import com.daelim.board_back.dto.response.ResponseDto;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Getter;

@Getter
public class GetLikedBoardsResponseDto extends ResponseDto {
    
    private List<LikedBoardDto> likedBoards; // LikedBoardDto 리스트

    private GetLikedBoardsResponseDto(List<LikedBoardDto> likedBoards) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.likedBoards = likedBoards;
    }

    public static ResponseEntity<GetLikedBoardsResponseDto> success(List<LikedBoardDto> likedBoards) {
        GetLikedBoardsResponseDto result = new GetLikedBoardsResponseDto(likedBoards);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noLikedBoards() {
        ResponseDto result = new ResponseDto(ResponseCode.NO_LIKED_BOARDS, ResponseMessage.NO_LIKED_BOARDS);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
