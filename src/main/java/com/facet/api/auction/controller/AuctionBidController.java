package com.facet.api.auction.controller;

import com.facet.api.auction.model.AucDto;
import com.facet.api.auction.service.AuctionBidService;
import com.facet.api.auction.service.AuctionReadService;
import com.facet.api.common.model.BaseEntity;
import com.facet.api.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auction")
@RequiredArgsConstructor
@RestController
public class AuctionBidController {
    private final AuctionBidService auctionBidService;
    private final AuctionReadService auctionReadService;


    @PostMapping("/bid")
    public ResponseEntity bid(@RequestBody AucDto.BidReq dto){
        AucDto.BidRes result = auctionBidService.bid(dto);
        return ResponseEntity.ok(BaseResponse.success(result));
    }
}
