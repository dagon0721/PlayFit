package com.daelim.board_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.daelim.board_back.common.ResponseCode;
import com.daelim.board_back.common.ResponseMessage;
import com.daelim.board_back.dto.object.BoardListItem;
import com.daelim.board_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetFavoriteBoardsResponseDto extends ResponseDto {
    
    private List<BoardListItem> favoriteBoards;

    public GetFavoriteBoardsResponseDto(List<BoardListItem> favoriteBoards) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteBoards = favoriteBoards;
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<? super GetFavoriteBoardsResponseDto> success(List<BoardListItem> favoriteBoards) {
        GetFavoriteBoardsResponseDto responseDto = new GetFavoriteBoardsResponseDto(favoriteBoards);
        return ResponseEntity.ok(responseDto);
    }
}
