package com.facet.api.auction.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public class AucDto {
    @Getter
    @Builder
    public static class PageRes{
        private List<ListRes> auctionList;
        private int totalPage;
        private Long totalCount;
        private int currentPage;
        private int currentSize;
        
        public static PageRes from(Page<AucProduct> result){
            return PageRes.builder()
                    .auctionList(result.get().map(ListRes::from).toList())
                    .totalPage(result.getTotalPages())
                    .totalCount(result.getTotalElements())
                    .currentPage(result.getPageable().getPageNumber())
                    .currentSize(result.getPageable().getPageSize())
                    .build();
        }
        
    }
    
    @Getter
    @Builder
    public static class ListRes{
        private Long idx;
        private String name;
        private String category;
        private String brandName;
        private int status;
        private String image;
        private int startPrice;
        private Date startAt;

        public static ListRes from(AucProduct entity){
            return ListRes.builder()
                    .idx(entity.getIdx())
                    .name(entity.getName())
                    .category(entity.getCategory())
                    .brandName(entity.getBrandName())
                    .status(entity.getStatus())
                    .image(entity.getImage())
                    .startPrice(entity.getStartPrice())
                    .startAt(entity.getStartAt())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class RankRes{
        private Long idx;
        private String name;
        private String image;
        private int status;
    }

    @Getter
    @Builder
    public static class SlideRes{
        private Long idx;
        private String name;
        private String image;
        private int status;
    }

    @Getter
    @Builder
    public static class ReadRes{
        private Long idx;
        private String category;
        private String name;
        private String brandName;
        private String description;
        private String image;
        private int startPrice;
        private Date startAt;
        private Date endAt;
        private int status;
        private int bidIncrement;
        private Long currentPrice;

        public static ReadRes from(AucProduct entity) {
            return ReadRes.builder()
                    .idx(entity.getIdx())
                    .category(entity.getCategory())
                    .name(entity.getName())
                    .brandName(entity.getBrandName())
                    .description(entity.getDescription())
                    .image(entity.getImage())
                    .startPrice(entity.getStartPrice())
                    .startAt(entity.getStartAt())
                    .endAt(entity.getEndAt())
                    .status(entity.getStatus())
                    .bidIncrement(entity.getBidIncrement())
                    .currentPrice (entity.getCurrentPrice())
                    .build();
        }
    }
}
