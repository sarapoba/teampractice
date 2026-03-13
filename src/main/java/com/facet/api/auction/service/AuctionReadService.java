package com.facet.api.auction.service;

import com.facet.api.auction.model.AucDto;
import com.facet.api.auction.model.AucProduct;
import com.facet.api.auction.repository.AuctionReadQueryRepository;
import com.facet.api.auction.repository.AuctionReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuctionReadService {
    private final AuctionReadRepository auctionReadRepository;
    private final AuctionReadQueryRepository auctionReadQueryRepository;

    @Transactional(readOnly = true)
    public AucDto.PageRes list(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<AucProduct> resEntity = auctionReadRepository.findAll(pageRequest);
        return AucDto.PageRes.from(resEntity);
    }

    @Transactional(readOnly = true)
    public List<AucDto.ListRes> search(AucDto.SearchReq dto){
        List<AucProduct> result = auctionReadQueryRepository.search(dto);

        return result.stream().map(AucDto.ListRes::from).toList();
    }

    @Transactional(readOnly = true)
    public AucDto.DetailRes detail(Long prodIdx) {
        AucProduct entity = auctionReadRepository.findById(prodIdx).orElseThrow();
        return AucDto.DetailRes.from(entity);
    }

}
