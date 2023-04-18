package ru.ysolutions.converter.models.xls;

import org.apache.commons.collections4.CollectionUtils;
import ru.ysolutions.converter.models.xml.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FactoryF303 {
    public static F303 getF303ByXmlData(Ф0409303 f303) {
        final List<F303Contract> f303Contracts = f303.getДанные303().getДоговор()
                .stream()
                .map(c -> new F303Contract()
                        .client(getClientByContract(c.getР1(), c.getР1ГВЗ()))
                        .contractId_p2_13(c.getР21())
                        .contractNumber_p2_14(c.getР2().getР22())
                        .contractDate_p2_15(getLocalDateByXMLGregorianCalendar(c.getР2().getР23()))
                        .numberBuyClaimRights_p2_16(c.getР2().getР24())
                        .dateBuyClaimRights_p2_17(getLocalDateByXMLGregorianCalendar(c.getР2().getР25()))
                        .nameOrganizationBuyClaimRights_p2_18(c.getР2().getР26())
                        .ogrnOrganizationBuyClaimRights_p2_19(c.getР2().getР27())
                        .regNumberOrganizationBuyClaimRights_p2_20(c.getР2().getР28())
                        .okcmOrganizationBuyClaimRights_p2_21(c.getР2().getР29())
                        .countRestrict_p2_22(c.getР2().getР210())
                        .informationBancrot_p2_23(c.getР2().getР211Н())
                        .dateInformationBancrot_p2_24(getLocalDateByXMLGregorianCalendar(c.getР2().getР212Н()))
                        .dateLastRestrict_p2_25(getLocalDateByXMLGregorianCalendar(c.getР2().getР213Н()))
                        .encumbrances(getEncumbrances(c.getР2Обрем()))
                        .kindRestrict_p2_33(c.getР2().getР221())
                        .contractUID_p2_34(c.getР2().getР222())
                        .conditions(getConditionsByParam(c.getР3(), c.getУсл()))
                        .warranties(getWarratiesByParam(c.getР4Обесп()))
                        .tranches(getTrancheByParam(c.getР5(), c.getР6(), c.getТранш()))

                )
                .collect(Collectors.toList());

        final LocalDate reportDate = getLocalDateByXMLGregorianCalendar(f303.getОтчДата());
        return new F303()
                .reportDate(reportDate)
                .contracts(f303Contracts);
    }

    private static List<F303Tranche> getTrancheByParam(Ф0409303Данные303ДоговорР5 p5, Ф0409303Данные303ДоговорР6 p6, List<Ф0409303Данные303ДоговорТранш> tranche) {
        final List<F303Tranche> tranches = new ArrayList<>();
        if (p5 != null || p6 != null) {
            final F303InfoDebtOD f303InfoDebtOD = new F303InfoDebtOD()
                    .p71(p6.getР61())
                    .p72(p6.getР62())
                    .p73(p6.getР63())
                    .p74(p6.getР64())
                    .p75(p6.getР65())
                    .p76(p6.getР66())
                    .p77(p6.getР67())
                    .p78(p6.getР68())
                    .p79(p6.getР69())
                    .p80(p6.getР610())
                    .p81(p6.getР611())
                    .p82(p6.getР612())
                    .p83(p6.getР613())
                    .p84(p6.getР614());

            tranches.add(new F303Tranche()
                    .p62(getLocalDateByXMLGregorianCalendar(p5.getР51()))
                    .p64(p5.getР53())
                    .p65(p5.getР54())
                    .p66(p5.getР55())
                    .p67(p5.getР56())
                    .p68(p5.getР57())
                    .p69(p5.getР58())
                    .p70(p5.getР59())
                    .f303InfoDebtOD(f303InfoDebtOD)
            );
        }
        if (CollectionUtils.isNotEmpty(tranche)) {
            tranches.addAll(
                    tranche
                            .stream()
                            .map(t -> new F303Tranche()
                                    .p62(getLocalDateByXMLGregorianCalendar(t.getР5Т().getР51()))
                                    .p63(t.getР52())
                                    .p64(t.getР5Т().getР53())
                                    .p65(t.getР5Т().getР54())
                                    .p66(t.getР5Т().getР55())
                                    .p67(t.getР5Т().getР56())
                                    .p68(t.getР5Т().getР57())
                                    .p69(t.getР5Т().getР58())
                                    .p70(t.getР5Т().getР59())
                                    .f303InfoDebtOD(getF303InfoDebtODByParam(t.getР6Т()))
                            )
                            .collect(Collectors.toList())
            );
        }
        return tranches;
    }

    private static F303InfoDebtOD getF303InfoDebtODByParam(Ф0409303Данные303ДоговорТраншР6Т p6T) {
        return new F303InfoDebtOD()
                .p71(p6T.getР61())
                .p72(p6T.getР62())
                .p73(p6T.getР63())
                .p74(p6T.getР64())
                .p75(p6T.getР65())
                .p76(p6T.getР66())
                .p77(p6T.getР67())
                .p78(p6T.getР68())
                .p79(p6T.getР69())
                .p80(p6T.getР610())
                .p82(p6T.getР612())
                .p83(p6T.getР613())
                .p84(p6T.getР614());
    }

    private static LocalDate getLocalDateByXMLGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar != null ? LocalDate.of(xmlGregorianCalendar.getYear()
                , xmlGregorianCalendar.getMonth()
                , xmlGregorianCalendar.getDay()) : null;
    }

    private static List<F303Warranty> getWarratiesByParam(List<Ф0409303Данные303ДоговорР4Обесп> warranties) {
        return warranties
                .stream()
                .map(w -> new F303Warranty()
                        .p56(w.getР41())
                        .p57(w.getР42())
                        .p58(getLocalDateByXMLGregorianCalendar(w.getР43()))
                        .p59(w.getР44())
                        .p60(w.getР45Н())
                        .p61(w.getР46Н())
                )
                .collect(Collectors.toList());
    }

    private static F303Conditions getConditionsByParam(Ф0409303Данные303ДоговорР3 conditions, List<Ф0409303Данные303ДоговорУсл> conditionCode) {
        final List<F303ConditionsCode> conditionsCodes = conditionCode
                .stream()
                .map(v -> new F303ConditionsCode()
                        .p3_49(v.getР315())
                        .conditionsCodeConds(getConditionsCodeConds(v.getДогПоУсл()))
                )
                .collect(Collectors.toList());

        return new F303Conditions()
                .p3_35(conditions.getР31())
                .p3_36(conditions.getР32())
                .p3_37(conditions.getР33())
                .p3_38(conditions.getР34())
                .p3_39(conditions.getР35())
                .p3_40(conditions.getР36())
                .p3_41(getLocalDateByXMLGregorianCalendar(conditions.getР37()))
                .p3_42(getLocalDateByXMLGregorianCalendar(conditions.getР38()))
                .p3_43(conditions.getР39())
                .p3_44(conditions.getР310())
                .p3_45(conditions.getР311())
                .p3_46(conditions.getР312())
                .p3_47(conditions.getР313Н())
                .p3_48(conditions.getР314())
                .conditionsCodes(conditionsCodes)
                .p3_52(conditions.getР317())
                .p3_53(conditions.getР318())
                .p3_54(conditions.getР319())
                .p3_55(conditions.getР320());


    }

    private static List<F303ConditionsCodeCond> getConditionsCodeConds(List<Ф0409303Данные303ДоговорУслДогПоУсл> conditionsCodeConds) {
        return conditionsCodeConds
                .stream()
                .map(v -> new F303ConditionsCodeCond()
                        .p3_50(v.getР316())
                        .p3_51(v.getР316Рг()))
                .collect(Collectors.toList());
    }

    private static List<F303Encumbrance> getEncumbrances(List<Ф0409303Данные303ДоговорР2Обрем> encumbrance) {
        return encumbrance
                .stream()
                .map(e -> new F303Encumbrance()
                        .p2_26(e.getР214Н())
                        .p2_27(e.getР215Н())
                        .p2_28(e.getР216Н())
                        .p2_29(e.getР217Н())
                        .p2_30(e.getР218Н())
                        .p2_31(e.getР219Н())
                        .p2_32(getLocalDateByXMLGregorianCalendar(e.getР220Н()))
                )
                .collect(Collectors.toList());
    }

    private static F303Client getClientByContract(Ф0409303Данные303ДоговорР1 client, List<Ф0409303Данные303ДоговорР1ГВЗ> clientGVZ) {
        final List<F303ClientGVZ> clientGVZs = clientGVZ
                .stream()
                .map(c -> new F303ClientGVZ()
                        .groupNumber_p1_11(c.getР110())
                        .groupName_p1_12(c.getР111()))
                .collect(Collectors.toList());

        return new F303Client()
                .name_p1_1(client.getР11())
                .ogrn_p1_2(client.getР12())
                .ogrnIp_p1_3(client.getР13())
                .ogrnDate_p1_4(getLocalDateByXMLGregorianCalendar(client.getР14()))
                .inn_p1_5(client.getР15())
                .innDescription_p1_6(client.getР15Тип())
                .okpo_p1_7(client.getР16())
                .countryCode_p1_8(client.getР17())
                .settingKo_p1_9(client.getР18())
                .businessCode_p1_10(client.getР19())
                .clientGVZs(clientGVZs);
    }
}