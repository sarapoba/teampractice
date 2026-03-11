package com.facet.api.auction;

import com.facet.api.auction.model.AucDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auction")
@RestController
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService auctionService;

    @GetMapping("/list")
    private ResponseEntity list(
            @RequestParam(required = true, defaultValue = "0") int page,
            @RequestParam(required = true, defaultValue = "12") int size
    ){
        AucDto.PageRes dto = auctionService.list(page, size);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity search(@PathVariable String search){
        AucDto.PageRes dto = auctionService.search(0, 12, search);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/read/{prodIdx}")
    public ResponseEntity read(@PathVariable Long prodIdx){
        AucDto.ReadRes dto = auctionService.read(prodIdx);
        return ResponseEntity.ok(dto);
    }

}
