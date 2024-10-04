package com.daelim.board_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daelim.board_back.dto.response.ResponseDto;
import com.daelim.board_back.dto.response.search.GetPopularListResponseDto;
import com.daelim.board_back.dto.response.search.GetRelationListResponseDto;
import com.daelim.board_back.repository.SearchLogRepository;
import com.daelim.board_back.repository.resultSet.GetPopularListResultSet;
import com.daelim.board_back.repository.resultSet.GetRelationListResultSet;
import com.daelim.board_back.service.SearchService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {
    
    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getPopularList();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

        List<GetRelationListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getRelationList(searchWord);
            System.out.println("ResultSets: " + resultSets);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRelationListResponseDto.success(resultSets);
    }
    
    

}
