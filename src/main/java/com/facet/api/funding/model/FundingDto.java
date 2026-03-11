package com.facet.api.funding.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FundingDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class FundingList{
        private Long idx;
        private String category;
        private String name;
        private String brand;
        private String img;
        private Long percent;
        private Long price;
        private Long supporters;
        private Long days;
        private String status;

        public static FundingDto.FundingList from(Funding entity){
            return FundingList.builder()
                    .idx(entity.getIdx())
                    .category(entity.getCategory())
                    .name(entity.getName())
                    .brand(entity.getBrand())
                    .img(entity.getImg())
                    .percent(entity.getPercent())
                    .price(entity.getPrice())
                    .supporters(entity.getSupporters())
                    .days(entity.getDays())
                    .status(entity.getStatus())
                    .build();
        }
    }
}
