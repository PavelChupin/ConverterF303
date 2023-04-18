package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303Contract {
    private F303Client client;
    private String contractId_p2_13;
    private String contractNumber_p2_14;
    private LocalDate contractDate_p2_15;
    private String numberBuyClaimRights_p2_16;
    private LocalDate dateBuyClaimRights_p2_17;
    private String nameOrganizationBuyClaimRights_p2_18;
    private String ogrnOrganizationBuyClaimRights_p2_19;
    private String regNumberOrganizationBuyClaimRights_p2_20;
    private String okcmOrganizationBuyClaimRights_p2_21;
    private BigInteger countRestrict_p2_22;
    private String informationBancrot_p2_23;
    private LocalDate dateInformationBancrot_p2_24;
    private LocalDate dateLastRestrict_p2_25;
    private List<F303Encumbrance> encumbrances;
    private String kindRestrict_p2_33;
    private String contractUID_p2_34;
    private F303Conditions conditions;
    private List<F303Warranty> warranties;
    private List<F303Tranche> tranches;
}