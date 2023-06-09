package ru.ysolutions.converter.models.xls.f303;

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
public class F303Tranche {
    // p2
    private BigInteger p22;
    private LocalDate p25;
    private String p33;
    // p3
    private String p36;
    private BigDecimal p37;
    private BigDecimal p38;
    private String p39;
    private String p40;
    private LocalDate p41;
    private LocalDate p42;
    private String p43;
    private BigDecimal p44;
    private BigDecimal p45;
    private BigDecimal p46;
    private BigInteger p47;
    private String p48;
    private String p49;
    // 49/50/51
    private List<F303SpecialCondition> conditionsCodes;
    private String p52;
    private String p53;
    private BigDecimal p54;
    private BigDecimal p55;
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
    // p9
    private String p92;
    private BigDecimal p93;
    private BigDecimal p94;
    private String p95;
    private BigDecimal p96;
    private BigDecimal p97;
    private BigDecimal p98;
    private LocalDate p99;
    private String p101;

    //p99/p100
    private List<F303Repayment> f303Repayments;

    //p94/p97/p98/p101/102/103/104/105
    private List<F303RepaymentSource> f303RepaymentSources;

    private String p104;
    private String p105;
}