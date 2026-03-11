package com.facet.api.funding;

import com.facet.api.common.model.BaseResponse;
import com.facet.api.funding.model.FundingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/funding")
@RequiredArgsConstructor
@RestController
public class FundingController {
    private final FundingService fundingService;

    @GetMapping("/fundinglist")
    public ResponseEntity list(){
        List<FundingDto.FundingList> result = fundingService.findAll();
        return ResponseEntity.ok(BaseResponse.success(result));
    }
}
