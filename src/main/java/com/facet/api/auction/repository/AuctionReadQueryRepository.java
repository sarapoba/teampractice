package com.facet.api.auction.repository;

import com.facet.api.auction.model.AucDto;
import com.facet.api.auction.model.AucProduct;
import com.facet.api.auction.model.QAucProduct;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuctionReadQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private QAucProduct aucProduct;

    public AuctionReadQueryRepository(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
        this.aucProduct = QAucProduct.aucProduct;
    }

    // 검색 기능 메소드
    public List<AucProduct> search(AucDto.SearchReq dto){
        List<AucProduct> result = jpaQueryFactory
                .selectFrom(aucProduct)
                .where(
                        nameContains(dto.getName()),
                        categoryContains(dto.getCategory()),
                        brandNameContains(dto.getBrandName())
                )
                .fetch();
        return result;
    }

    // 제목 검색
    public BooleanExpression nameContains(String name){
        return name != null && !name.isBlank() ?
                aucProduct.name.contains(name) : null;
    }

    public BooleanExpression categoryContains(String category){
        return category != null && !category.isBlank() ?
                aucProduct.name.contains(category) : null;
    }

    public BooleanExpression brandNameContains(String brandName){
        return brandName != null && !brandName.isBlank() ?
                aucProduct.name.contains(brandName) : null;
    }
}
