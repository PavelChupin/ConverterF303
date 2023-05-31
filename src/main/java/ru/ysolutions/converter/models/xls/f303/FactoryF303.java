package ru.ysolutions.converter.models.xls.f303;

import org.apache.commons.collections4.CollectionUtils;
import ru.ysolutions.converter.exception.AmountSourceException;
import ru.ysolutions.converter.exception.ContractSourceException;
import ru.ysolutions.converter.models.xml.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Arrays;
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
                        .p13(c.getР21())
                        .p14(c.getР2().getР22())
                        .p15(getLocalDateByXMLGregorianCalendar(c.getР2().getР23()))
                        .p16(c.getР2().getР24())
                        .p17(getLocalDateByXMLGregorianCalendar(c.getР2().getР25()))
                        .p18(c.getР2().getР26())
                        .p19(c.getР2().getР27())
                        .p20(c.getР2().getР28())
                        .p21(c.getР2().getР29())
                        .p22(c.getР2().getР210())
                        .p23(c.getР2().getР211Н())
                        .p24(getLocalDateByXMLGregorianCalendar(c.getР2().getР212Н()))
                        .p25(getLocalDateByXMLGregorianCalendar(c.getР2().getР213Н()))
                        .encumbrances(getEncumbrances(c.getР2Обрем()))
                        .p33(c.getР2().getР221())
                        .p34(c.getР2().getР222())
                        // p3
                        .p35(c.getР3().getР31())
                        .p36(c.getР3().getР32())
                        .p37(c.getР3().getР33())
                        .p38(c.getР3().getР34())
                        .p39(c.getР3().getР35())
                        .p40(c.getР3().getР36())
                        .p41(getLocalDateByXMLGregorianCalendar(c.getР3().getР37()))
                        .p42(getLocalDateByXMLGregorianCalendar(c.getР3().getР38()))
                        .p43(c.getР3().getР39())
                        .p44(c.getР3().getР310())
                        .p45(c.getР3().getР311())
                        .p46(c.getР3().getР312())
                        .p47(c.getР3().getР313Н())
                        .p48(c.getР3().getР314())
                        .p49(getP49ByParam(c.getУсл()))
                        .conditionsCodes(getConditionCodesByParam(c.getУсл()))
                        .p52(c.getР3().getР317())
                        .p53(c.getР3().getР318())
                        .p54(c.getР3().getР319())
                        .p55(c.getР3().getР320())
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
                        .p92(c.getР9() != null ? c.getР9().getР91() : null)
                        .p93(c.getР9() != null ? c.getР9().getР92() : null)
                        .p94(c.getР9() != null ? c.getР9().getР93() : null)
                        .p95(c.getР9() != null ? c.getР9().getР94() : null)
                        .p96(c.getР9() != null ? c.getР9().getР95() : null)
                        .p97(c.getР9() != null ? c.getР9().getР96() : null)
                        .p98(c.getР9() != null ? c.getР9().getР97() : null)
                        .p99(c.getР9() != null ? getLocalDateByXMLGregorianCalendar(c.getР9().getР98()) : null)
                        .p101(getP101ByIst(c.getИст()))
                        .f303Repayments(getRepaymentsByParam(c.getПогшн()))
                        .f303RepaymentSources(getRepaymentSourcesByParam(c.getИст()))
                        .p104(c.getР9() != null ? c.getР9().getР913() : null)
                        .p105(c.getР9() != null ? c.getР9().getР914() : null)

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

    private static String getP49ByParam(List<Ф0409303Данные303ДоговорУсл> conditionCodesByParam) {
        if (CollectionUtils.isEmpty(conditionCodesByParam)) {
            return null;
        } else {
            return Arrays.toString(conditionCodesByParam.stream()
                    .map(Ф0409303Данные303ДоговорУсл::getР315)
                    .toArray())
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");
        }
    }

    private static String getP101ByIst(List<Ф0409303Данные303ДоговорИст> ist) {
        if (CollectionUtils.isEmpty(ist)) {
            return null;
        } else {
            return Arrays.toString(ist.stream()
                    .map(Ф0409303Данные303ДоговорИст::getР910)
                    .toArray())
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");
        }
    }

    private static List<F303RepaymentSource> getRepaymentSourcesByParam(List<Ф0409303Данные303ДоговорИст> repaymentSource) {
        return repaymentSource
                .stream()
                .map(r -> {
                            if (CollectionUtils.isNotEmpty(r.getИстСум()) && r.getИстСум().size() > 1) {
                                throw new AmountSourceException();
                            } else if (CollectionUtils.isNotEmpty(r.getИстДог()) && r.getИстДог().size() > 1) {
                                throw new ContractSourceException();
                            } else {
                                return new F303RepaymentSource()
                                        .p94(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР93())
                                        .p97(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР96())
                                        .p98(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР97())
                                        .p101(r.getР910())
                                        .p102(CollectionUtils.isEmpty(r.getИстДог()) ? null : r.getИстДог().get(0).getР911())
                                        .p103(CollectionUtils.isEmpty(r.getИстДог()) ? null : r.getИстДог().get(0).getР912())
                                        .p104(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР913())
                                        .p105(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР914());
                            }
                        }
                )
                .collect(Collectors.toList());
    }

    private static List<F303Repayment> getRepaymentsByParam(List<Ф0409303Данные303ДоговорПогшн> repayments) {
        return repayments
                .stream()
                .map(r -> new F303Repayment()
                        .p99(getLocalDateByXMLGregorianCalendar(r.getР98()))
                        .p100(getLocalDateByXMLGregorianCalendar(r.getР99()))
                )
                .collect(Collectors.toList());
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
                        .p22(t.getР2Т() != null ? t.getР2Т().getР210() : null)
                        .p25(t.getР2Т() != null ? getLocalDateByXMLGregorianCalendar(t.getР2Т().getР213Н()) : null)
                        .p33(t.getР2Т() != null ? t.getР2Т().getР221() : null)
                        // p3
                        .p36(t.getР3Т() != null ? t.getР3Т().getР32() : null)
                        .p37(t.getР3Т() != null ? t.getР3Т().getР33() : null)
                        .p38(t.getР3Т() != null ? t.getР3Т().getР34() : null)
                        .p39(t.getР3Т() != null ? t.getР3Т().getР35() : null)
                        .p40(t.getР3Т() != null ? t.getР3Т().getР36() : null)
                        .p41(t.getР3Т() != null ? getLocalDateByXMLGregorianCalendar(t.getР3Т().getР37()) : null)
                        .p42(t.getР3Т() != null ? getLocalDateByXMLGregorianCalendar(t.getР3Т().getР38()) : null)
                        .p43(t.getР3Т() != null ? t.getР3Т().getР39() : null)
                        .p44(t.getР3Т() != null ? t.getР3Т().getР310() : null)
                        .p45(t.getР3Т() != null ? t.getР3Т().getР311() : null)
                        .p46(t.getР3Т() != null ? t.getР3Т().getР312() : null)
                        .p47(t.getР3Т() != null ? t.getР3Т().getР313Н() : null)
                        .p48(t.getР3Т() != null ? t.getР3Т().getР314() : null)
                        .p49(getP49TrancheByParam(t.getУслТ()))
                        // 49/50/51
                        .conditionsCodes(getTranchegetConditionCodesByParam(t.getУслТ()))
                        .p52(t.getР3Т() != null ? t.getР3Т().getР317() : null)
                        .p53(t.getР3Т() != null ? t.getР3Т().getР318() : null)
                        .p54(t.getР3Т() != null ? t.getР3Т().getР319() : null)
                        .p55(t.getР3Т() != null ? t.getР3Т().getР320() : null)
                        // p4
                        .warranties(getTrancheWarrantiesByParam(t.getР4ОбеспТ()))
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
                        .p92(t.getР9Т() != null ? t.getР9Т().getР91() : null)
                        .p93(t.getР9Т() != null ? t.getР9Т().getР92() : null)
                        .p94(t.getР9Т() != null ? t.getР9Т().getР93() : null)
                        .p95(t.getР9Т() != null ? t.getР9Т().getР94() : null)
                        .p96(t.getР9Т() != null ? t.getР9Т().getР95() : null)
                        .p97(t.getР9Т() != null ? t.getР9Т().getР96() : null)
                        .p98(t.getР9Т() != null ? t.getР9Т().getР97() : null)
                        .p99(t.getР9Т() != null ? getLocalDateByXMLGregorianCalendar(t.getР9Т().getР98()) : null)
                        .p101(getTrancheP101ByIst(t.getИстТ()))
                        .f303Repayments(getTrancheRepaymentsByParam(t.getПогшнТ()))
                        .f303RepaymentSources(getTrancheRepaymentSourcesByParam(t.getИстТ()))
                        .p104(t.getР9Т() != null ? t.getР9Т().getР913() : null)
                        .p105(t.getР9Т() != null ? t.getР9Т().getР914() : null)
                )
                .collect(Collectors.toList());
    }

    private static String getP49TrancheByParam(List<Ф0409303Данные303ДоговорТраншУслТ> cond) {
        if (CollectionUtils.isEmpty(cond)) {
            return null;
        } else {
            return Arrays.toString(cond.stream()
                    .map(Ф0409303Данные303ДоговорТраншУслТ::getР315)
                    .toArray())
                    .replace("[", "")
                    .replace("]", "");
        }
    }

    private static String getTrancheP101ByIst(List<Ф0409303Данные303ДоговорТраншИстТ> ist) {
        if (CollectionUtils.isEmpty(ist)) {
            return null;
        } else {
            return Arrays.toString(ist.stream()
                    .map(Ф0409303Данные303ДоговорТраншИстТ::getР910)
                    .toArray())
                    .replace("[", "")
                    .replace("]", "");
        }
    }

    private static List<F303RepaymentSource> getTrancheRepaymentSourcesByParam(List<Ф0409303Данные303ДоговорТраншИстТ> repaymentSource) {
        return repaymentSource
                .stream()
                .map(r -> {
                            if (CollectionUtils.isNotEmpty(r.getИстСум()) && r.getИстСум().size() > 1) {
                                throw new AmountSourceException();
                            } else if (CollectionUtils.isNotEmpty(r.getИстДог()) && r.getИстДог().size() > 1) {
                                throw new ContractSourceException();
                            } else {
                                return new F303RepaymentSource()
                                        .p94(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР93())
                                        .p97(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР96())
                                        .p98(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР97())
                                        .p101(r.getР910())
                                        .p102(CollectionUtils.isEmpty(r.getИстДог()) ? null : r.getИстДог().get(0).getР911())
                                        .p103(CollectionUtils.isEmpty(r.getИстДог()) ? null : r.getИстДог().get(0).getР912())
                                        .p104(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР913())
                                        .p105(CollectionUtils.isEmpty(r.getИстСум()) ? null : r.getИстСум().get(0).getР914());
                            }
                        }
                )
                .collect(Collectors.toList());
    }

    private static List<F303Repayment> getTrancheRepaymentsByParam(List<Ф0409303Данные303ДоговорТраншПогшнТ> repayments) {
        return repayments
                .stream()
                .map(r -> new F303Repayment()
                        .p99(getLocalDateByXMLGregorianCalendar(r.getР98()))
                        .p100(getLocalDateByXMLGregorianCalendar(r.getР99()))
                )
                .collect(Collectors.toList());
    }

    private static List<F303Warranty> getTrancheWarrantiesByParam(List<Ф0409303Данные303ДоговорТраншР4ОбеспТ> warranties) {
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

    private static List<F303SpecialCondition> getTranchegetConditionCodesByParam(List<Ф0409303Данные303ДоговорТраншУслТ> conditionCode) {
        return conditionCode
                .stream()
                .map(v -> new F303SpecialCondition()
                        .p49(v.getР315())
                        .conditionsCodeConds(getTrancheConditionsCodeConds(v.getДогПоУсл()))
                )
                .collect(Collectors.toList());
    }

    private static List<F303SpecialConditionProperty> getTrancheConditionsCodeConds(List<Ф0409303Данные303ДоговорТраншУслТДогПоУсл> conditionsCodeConds) {
        return conditionsCodeConds
                .stream()
                .map(v -> new F303SpecialConditionProperty()
                        .p50(v.getР316())
                        .p51(v.getР316Рг()))
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

    private static List<F303SpecialCondition> getConditionCodesByParam(List<Ф0409303Данные303ДоговорУсл> conditionCode) {
        return conditionCode
                .stream()
                .map(v -> new F303SpecialCondition()
                        .p49(v.getР315())
                        .conditionsCodeConds(getConditionsCodeConds(v.getДогПоУсл()))
                )
                .collect(Collectors.toList());
    }

    private static List<F303SpecialConditionProperty> getConditionsCodeConds(List<Ф0409303Данные303ДоговорУслДогПоУсл> conditionsCodeConds) {
        return conditionsCodeConds
                .stream()
                .map(v -> new F303SpecialConditionProperty()
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
                        .p11(c.getР110())
                        .p12(c.getР111()))
                .collect(Collectors.toList());

        return new F303Client()
                .p1(client.getР11())
                .p2(client.getР12())
                .p3(client.getР13())
                .p4(getLocalDateByXMLGregorianCalendar(client.getР14()))
                .p5(client.getР15())
                .p6(client.getР15Тип())
                .p7(client.getР16())
                .p8(client.getР17())
                .p9(client.getР18())
                .p10(client.getР19())
                .clientGVZs(clientGVZs);
    }
}