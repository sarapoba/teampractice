package com.facet.api.funding;

import com.facet.api.funding.model.FundingProduct;
import com.facet.api.funding.model.FundingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FundingService {
    private final FundingRepository fundingRepository;

    public  List<FundingDto.FundingList> list() {
        List<FundingProduct> res = fundingRepository.findAll();
        List<FundingDto.FundingList> result = new ArrayList<>();

        for(FundingProduct data: res){
            result.add(FundingDto.FundingList.from(data));
        }

        return result;
    }

    public FundingDto.PageRes pageList(int page, int size, String currentFilter) {
        // 기본 정렬: 최신순(idx 오름차순으로 )
        Sort sort = Sort.by("idx").ascending();


        String sortType = currentFilter;

        if ("price".equals(sortType)) {
            sort = Sort.by("price").ascending(); // 낮은 가격순
        } else if ("imminent".equals(sortType)) {
            sort = Sort.by("days").ascending(); // 마감 임박순 (필드명에 맞춰 수정 필요)
        }

        // 정렬 정보(sort)를 포함하여 PageRequest 생성
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<FundingProduct> result = fundingRepository.findAll(pageRequest);
        return FundingDto.PageRes.from(result);
    }
}
