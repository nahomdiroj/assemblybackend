package com.nahom.assembly.model;

import java.math.BigDecimal;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sherholderdetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal devidend;
    private String nameamh;
    private String nameeng;
    private BigDecimal paid_capital;
    private String phone;
    private String share_holder_id;
    private Long total_capital;
    private Long sharesubsription;
    private int attendance;
    private String fiscal_year;
 }
