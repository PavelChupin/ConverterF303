package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303Contract {
    // p1
    private F303Client client_p1;

    // p2
    private String contractId_p13;
    private String contractNumber_p14;
    private LocalDate contractDate_p15;
    private String numberBuyClaimRights_p16;
    private LocalDate dateBuyClaimRights_p17;
    private String nameOrganizationBuyClaimRights_p18;
    private String ogrnOrganizationBuyClaimRights_p19;
    private String regNumberOrganizationBuyClaimRights_p20;
    private String okcmOrganizationBuyClaimRights_p21;
    private BigInteger countRestrict_p22;
    private String informationBancrot_p23;
    private LocalDate dateInformationBancrot_24;
    private LocalDate dateLastRestrict_p25;
    private List<F303Encumbrance> encumbrances;
    private String kindRestrict_p33;
    private String contractUID_p34;

    // p3
    private F303Conditions conditions;

    // p4
    private List<F303Warranty> warranties;

    // p5
    private LocalDate p62;
    private String p63;
    private BigDecimal p64;
    private String p65;
    private String p66;
    private BigDecimal p67;
    private String p68;
    private BigDecimal p69;
    private String p70;

    // p6
    private String p71;
    private String p72;
    private BigDecimal p73;
    private BigDecimal p74;
    private String p75;
    private String p76;
    private BigDecimal p77;
    private BigDecimal p78;
    private BigDecimal p79;
    private String p80;
    private String p81;
    private String p82;
    private String p83;
    private String p84;

    // p7
    private BigDecimal p85;
    private BigDecimal p86;
    private BigDecimal p87;

    // p8
    private BigDecimal p88;
    private BigDecimal p89;
    private BigDecimal p90;
    private BigDecimal p91;

    // p9


    // p10
    protected List<F303p10> p10;

    // transh
    private List<F303Tranche> tranches;
}