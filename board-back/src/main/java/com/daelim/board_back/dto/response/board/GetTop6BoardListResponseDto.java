package com.daelim.board_back.dto.response.board;

import com.daelim.board_back.common.ResponseCode;
import com.daelim.board_back.common.ResponseMessage;
import com.daelim.board_back.dto.object.BoardListItem;
import com.daelim.board_back.dto.response.ResponseDto;
import com.daelim.board_back.entity.BoardListViewEntity;

import lombok.Getter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetTop6BoardListResponseDto extends ResponseDto{
    
    private List<BoardListItem> top6List;

    private GetTop6BoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top6List = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetTop6BoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetTop6BoardListResponseDto result = new GetTop6BoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
