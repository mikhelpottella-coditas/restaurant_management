package com.practise.restaurant_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "revenue")
@NoArgsConstructor
@AllArgsConstructor
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    private Long totalOrders;



    @Column(name = "income")
    private Double income;

    private Double profit;

    @ManyToOne
    private Branches branch;

    @Column(name = "expenditure_amount")
    private Double expenditureAmount;

}
