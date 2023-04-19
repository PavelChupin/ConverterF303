package ru.ysolutions.converter.models.xls;

import ru.ysolutions.converter.models.xml.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FactoryF303 {
    public static F303 getF303ByXmlData(Ф0409303 f303) {
        final List<F303Contract> f303Contracts = f303.getДанные303().getДоговор()
                .stream()
                .map(c -> new F303Contract()
                        // p1
                        .client_p1(getClientByContract(c.getР1(), c.getР1ГВЗ()))
                        // p2
                        .contractId_p13(c.getР21())
                        .contractNumber_p14(c.getР2().getР22())
                        .contractDate_p15(getLocalDateByXMLGregorianCalendar(c.getР2().getР23()))
                        .numberBuyClaimRights_p16(c.getР2().getР24())
                        .dateBuyClaimRights_p17(getLocalDateByXMLGregorianCalendar(c.getР2().getР25()))
                        .nameOrganizationBuyClaimRights_p18(c.getР2().getР26())
                        .ogrnOrganizationBuyClaimRights_p19(c.getР2().getР27())
                        .regNumberOrganizationBuyClaimRights_p20(c.getР2().getР28())
                        .okcmOrganizationBuyClaimRights_p21(c.getР2().getР29())
                        .countRestrict_p22(c.getР2().getР210())
                        .informationBancrot_p23(c.getР2().getР211Н())
                        .dateInformationBancrot_24(getLocalDateByXMLGregorianCalendar(c.getР2().getР212Н()))
                        .dateLastRestrict_p25(getLocalDateByXMLGregorianCalendar(c.getР2().getР213Н()))
                        .encumbrances(getEncumbrances(c.getР2Обрем()))
                        .kindRestrict_p33(c.getР2().getР221())
                        .contractUID_p34(c.getР2().getР222())
                        // p3
                        .conditions(getConditionsByParam(c.getР3(), c.getУсл()))
                        // p4
                        .warranties(getWarratiesByParam(c.getР4Обесп()))
                        // p5
                        .p62(c.getР5() != null ? getLocalDateByXMLGregorianCalendar(c.getР5().getР51()) : null)
                        .p63(null)
                        .p64(c.getР5() != null ? c.getР5().getР53() : null)
                        .p65(c.getР5() != null ? c.getР5().getР54() : null)
                        .p66(c.getР5() != null ? c.getР5().getР55() : null)
                        .p67(c.getР5() != null ? c.getР5().getР56() : null)
                        .p68(c.getР5() != null ? c.getР5().getР57() : null)
                        .p69(c.getР5() != null ? c.getР5().getР58() : null)
                        .p70(c.getР5() != null ? c.getР5().getР59() : null)
                        // p6
                        .p71(c.getР6() != null ? c.getР6().getР61() : null)
                        .p72(c.getР6() != null ? c.getР6().getР62() : null)
                        .p73(c.getР6() != null ? c.getР6().getР63() : null)
                        .p74(c.getР6() != null ? c.getР6().getР64() : null)
                        .p75(c.getР6() != null ? c.getР6().getР65() : null)
                        .p76(c.getР6() != null ? c.getР6().getР66() : null)
                        .p77(c.getР6() != null ? c.getР6().getР67() : null)
                        .p78(c.getР6() != null ? c.getР6().getР68() : null)
                        .p79(c.getР6() != null ? c.getР6().getР69() : null)
                        .p80(c.getР6() != null ? c.getР6().getР610() : null)
                        .p81(c.getР6() != null ? c.getР6().getР611() : null)
                        .p82(c.getР6() != null ? c.getР6().getР612() : null)
                        .p83(c.getР6() != null ? c.getР6().getР613() : null)
                        .p84(c.getР6() != null ? c.getР6().getР614() : null)

                        // p7
                        .p85(c.getР7() != null ? c.getР7().getР71() : null)
                        .p86(c.getР7() != null ? c.getР7().getР72() : null)
                        .p87(c.getР7() != null ? c.getР7().getР73Н() : null)

                        // p8
                        .p88(c.getР8() != null ? c.getР8().getР81() : null)
                        .p89(c.getР8() != null ? c.getР8().getР82() : null)
                        .p90(c.getР8() != null ? c.getР8().getР83() : null)
                        .p91(c.getР8() != null ? c.getР8().getР84() : null)

                        // p9


                        // p10
                        .p10(getF303p10(c.getР10()))
                        .tranches(getTrancheByParam(c.getТранш()))
                )
                .collect(Collectors.toList());

        final LocalDate reportDate = getLocalDateByXMLGregorianCalendar(f303.getОтчДата());
        return new F303()
                .reportDate(reportDate)
                .contracts(f303Contracts);
    }

    private static List<F303p10> getF303p10(List<Ф0409303Данные303ДоговорР10> p10) {
        return p10
                .stream()
                .map(p -> new F303p10()
                        .p106(p.getР101())
                        .p107(p.getР102())
                        .p108(p.getР103())
                        .p109(p.getР104())
                        .p110(p.getР105())
                        .p111(p.getР106())
                        .p112(p.getР107())
                        .p113(p.getР108())
                        .p114(p.getР109())
                )
                .collect(Collectors.toList());
    }

    private static List<F303Tranche> getTrancheByParam(List<Ф0409303Данные303ДоговорТранш> tranches) {
        return tranches
                .stream()
                .map(t -> new F303Tranche()
                                // p2
                                // p3
                                // p4

                                // p5
                                .p62(getLocalDateByXMLGregorianCalendar(t.getР5Т().getР51()))
                                .p63(t.getР52())
                                .p64(t.getР5Т() != null ? t.getР5Т().getР53() : null)
                                .p65(t.getР5Т() != null ? t.getР5Т().getР54() : null)
                                .p66(t.getР5Т() != null ? t.getР5Т().getР55() : null)
                                .p67(t.getР5Т() != null ? t.getР5Т().getР56() : null)
                                .p68(t.getР5Т() != null ? t.getР5Т().getР57() : null)
                                .p69(t.getР5Т() != null ? t.getР5Т().getР58() : null)
                                .p70(t.getР5Т() != null ? t.getР5Т().getР59() : null)
                                // p6
                                .p71(t.getР6Т() != null ? t.getР6Т().getР61() : null)
                                .p72(t.getР6Т() != null ? t.getР6Т().getР62() : null)
                                .p73(t.getР6Т() != null ? t.getР6Т().getР63() : null)
                                .p74(t.getР6Т() != null ? t.getР6Т().getР64() : null)
                                .p75(t.getР6Т() != null ? t.getР6Т().getР65() : null)
                                .p76(t.getР6Т() != null ? t.getР6Т().getР66() : null)
                                .p77(t.getР6Т() != null ? t.getР6Т().getР67() : null)
                                .p78(t.getР6Т() != null ? t.getР6Т().getР68() : null)
                                .p79(t.getР6Т() != null ? t.getР6Т().getР69() : null)
                                .p80(t.getР6Т() != null ? t.getР6Т().getР610() : null)
                                .p81(null)
                                .p82(t.getР6Т() != null ? t.getР6Т().getР612() : null)
                                .p83(t.getР6Т() != null ? t.getР6Т().getР613() : null)
                                .p84(t.getР6Т() != null ? t.getР6Т().getР614() : null)
                                // p7
                                .p85(t.getР7Т() != null ? t.getР7Т().getР71() : null)
                                .p86(t.getР7Т() != null ? t.getР7Т().getР72() : null)
                                .p87(t.getР7Т() != null ? t.getР7Т().getР73Н() : null)

                        // p9
                )
                .collect(Collectors.toList());
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
                        .p49(v.getР315())
                        .conditionsCodeConds(getConditionsCodeConds(v.getДогПоУсл()))
                )
                .collect(Collectors.toList());

        return new F303Conditions()
                .p35(conditions.getР31())
                .p36(conditions.getР32())
                .p37(conditions.getР33())
                .p38(conditions.getР34())
                .p39(conditions.getР35())
                .p40(conditions.getР36())
                .p41(getLocalDateByXMLGregorianCalendar(conditions.getР37()))
                .p42(getLocalDateByXMLGregorianCalendar(conditions.getР38()))
                .p43(conditions.getР39())
                .p44(conditions.getР310())
                .p45(conditions.getР311())
                .p46(conditions.getР312())
                .p47(conditions.getР313Н())
                .p48(conditions.getР314())
                .conditionsCodes(conditionsCodes)
                .p52(conditions.getР317())
                .p53(conditions.getР318())
                .p54(conditions.getР319())
                .p55(conditions.getР320());


    }

    private static List<F303ConditionsCodeCond> getConditionsCodeConds(List<Ф0409303Данные303ДоговорУслДогПоУсл> conditionsCodeConds) {
        return conditionsCodeConds
                .stream()
                .map(v -> new F303ConditionsCodeCond()
                        .p50(v.getР316())
                        .p51(v.getР316Рг()))
                .collect(Collectors.toList());
    }

    private static List<F303Encumbrance> getEncumbrances(List<Ф0409303Данные303ДоговорР2Обрем> encumbrance) {
        return encumbrance
                .stream()
                .map(e -> new F303Encumbrance()
                        .p26(e.getР214Н())
                        .p27(e.getР215Н())
                        .p28(e.getР216Н())
                        .p29(e.getР217Н())
                        .p30(e.getР218Н())
                        .p31(e.getР219Н())
                        .p32(getLocalDateByXMLGregorianCalendar(e.getР220Н()))
                )
                .collect(Collectors.toList());
    }

    private static F303Client getClientByContract(Ф0409303Данные303ДоговорР1 client, List<Ф0409303Данные303ДоговорР1ГВЗ> clientGVZ) {
        final List<F303ClientGVZ> clientGVZs = clientGVZ
                .stream()
                .map(c -> new F303ClientGVZ()
                        .groupNumber_p11(c.getР110())
                        .groupName_p12(c.getР111()))
                .collect(Collectors.toList());

        return new F303Client()
                .name_p1(client.getР11())
                .ogrn_p2(client.getР12())
                .ogrnIp_p3(client.getР13())
                .ogrnDate_p4(getLocalDateByXMLGregorianCalendar(client.getР14()))
                .inn_p5(client.getР15())
                .innDescription_p6(client.getР15Тип())
                .okpo_p7(client.getР16())
                .countryCode_p8(client.getР17())
                .settingKo_p9(client.getР18())
                .businessCode_p10(client.getР19())
                .clientGVZs(clientGVZs);
    }
}