package com.facet.api.ask;

import com.facet.api.ask.model.Ask;
import com.facet.api.ask.model.AskDto;
import com.facet.api.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ask")
@RequiredArgsConstructor
@RestController
public class AskController {
    private final AskService askService;

    @PostMapping("/reg")
    public ResponseEntity reg(@RequestBody AskDto.RegReq dto){
        AskDto.RegRes result = askService.reg(dto);
        return ResponseEntity.ok(BaseResponse.success(result));
    }
}
