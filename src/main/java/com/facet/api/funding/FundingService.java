package com.facet.api.funding;

import com.facet.api.funding.model.Funding;
import com.facet.api.funding.model.FundingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FundingService {
    private final FundingRepository fundingRepository;

    public  List<FundingDto.FundingList> findAll() {
        List<Funding> res = fundingRepository.findAll();
        List<FundingDto.FundingList> result = new ArrayList<>();

        for(Funding data: res){
            result.add(FundingDto.FundingList.from(data));
        }

        return result;
    }
}
