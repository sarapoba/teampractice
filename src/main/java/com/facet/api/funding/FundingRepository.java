package com.facet.api.funding;

import com.facet.api.funding.model.FundingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<FundingProduct,Long> {
}
