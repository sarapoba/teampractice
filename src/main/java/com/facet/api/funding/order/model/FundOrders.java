package com.facet.api.funding.order.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FundOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @ColumnDefault("false")
    private boolean paid; // 결제 확인 여부

    private Long paymentPrice; // 결제 금액

    @Setter
    private String pgPaymentId;

    @OneToMany(mappedBy = "fundOrders",fetch = FetchType.LAZY)
    private List<FundOrdersItem> items;
}
