package com.daelim.board_back.dto.response.search;

import com.daelim.board_back.common.ResponseCode;
import com.daelim.board_back.common.ResponseMessage;
import com.daelim.board_back.dto.response.ResponseDto;
import com.daelim.board_back.repository.resultSet.GetRelationListResultSet;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import lombok.Getter;

@Getter
public class GetRelationListResponseDto extends ResponseDto{
    
    private List<String> relativeWordList;

    private GetRelationListResponseDto(List<GetRelationListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> relativeWordList = new ArrayList<>();
        for (GetRelationListResultSet resultSet: resultSets) {
            String relativeWord = resultSet.getSearchWord();
            relativeWordList.add(relativeWord);
        }
        this.relativeWordList = relativeWordList;
    }

    public static ResponseEntity<GetRelationListResponseDto> success(List<GetRelationListResultSet> resultSets) {
        GetRelationListResponseDto result = new GetRelationListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
