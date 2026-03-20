package com.facet.api.auction.controller;

import com.facet.api.auction.model.AucDto;
import com.facet.api.auction.service.AuctionBidService;
import com.facet.api.common.model.BaseEntity;
import com.facet.api.common.model.BaseResponse;
import com.facet.api.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auction")
@RestController
@RequiredArgsConstructor
@Tag(name = "경매 기능")
public class AuctionBidController {
    private final AuctionBidService auctionBidService;


    @PostMapping("/bid")
    @Operation(summary = "입찰 기능", description = "현재 입찰가보다 높은 금액을 입력하면 입찰할 수 있는 기능")
    public ResponseEntity bid(@RequestBody AucDto.BidReq dto, @RequestHeader("ATOKEN") String token){
        JwtUtil jwtUtil = new JwtUtil();
        Long userIdx = jwtUtil.getUserIdx(token);
        dto.builder().userIdx(userIdx).build();
        AucDto.BidRes result = auctionBidService.bid(dto);
        return ResponseEntity.ok(BaseResponse.success(result));
    }
}
