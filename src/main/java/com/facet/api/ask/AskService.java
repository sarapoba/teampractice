package com.facet.api.ask;

import com.facet.api.ask.model.Ask;
import com.facet.api.ask.model.AskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AskService {
    private final AskRepository askRepository;

    public AskDto.RegRes reg(AskDto.RegReq dto) {
        Ask ask = dto.toEntity();
        Ask result = askRepository.save(ask);

        return AskDto.RegRes.from(result);

    }
}
