package com.facet.api.funding.order;

import com.facet.api.funding.FundingRepository;
import com.facet.api.funding.model.FundProduct;
import com.facet.api.funding.order.model.FundOrders;
import com.facet.api.funding.order.model.FundOrdersDto;
import com.facet.api.funding.order.model.FundOrdersItem;
import com.facet.api.user.model.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FundOrdersService {
    private final FundOrdersRepository fundOrdersRepository;
    private final FundItemRepository fundItemRepository;
    private final FundingRepository fundingRepository;

    public FundOrdersDto.OrdersRes create(AuthUserDetails user, FundOrdersDto.OrdersReq dto) {
        // 주문한 리워드 불러오기
        List<FundProduct> productList = fundingRepository.findAllById(dto.getFundIdxList());

        FundOrders fundOrders = fundOrdersRepository.save(dto.toEntity(user.toEntity()));

        // 주문한 상품을 하나씩 뽑아서 DB에 담는다
        for(FundProduct product : productList){
            FundOrdersItem fundOrdersItem = FundOrdersItem.builder()
                    .fundProduct(product)
                    .fundOrders(fundOrders)
                    .build();
            fundItemRepository.save(fundOrdersItem);
        }

        // 주문자 idx, 결제 여부
        return FundOrdersDto.OrdersRes.from(fundOrders);
    }
}
