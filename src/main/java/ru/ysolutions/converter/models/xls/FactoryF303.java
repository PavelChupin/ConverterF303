package ru.ysolutions.converter.models.xls;

import ru.ysolutions.converter.models.xml.*;

import java.time.LocalDate;
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
                        .contractDate_p2_15(c.getР2().getР23() != null ? LocalDate.of(c.getР2().getР23().getYear(), c.getР2().getР23().getMonth(), c.getР2().getР23().getDay()) : null)
                        .numberBuyClaimRights_p2_16(c.getР2().getР24())
                        .dateBuyClaimRights_p2_17(c.getР2().getР25() != null ? LocalDate.of(c.getР2().getР25().getYear(), c.getР2().getР25().getMonth(), c.getР2().getР25().getDay()) : null)
                        .nameOrganizationBuyClaimRights_p2_18(c.getР2().getР26())
                        .ogrnOrganizationBuyClaimRights_p2_19(c.getР2().getР27())
                        .regNumberOrganizationBuyClaimRights_p2_20(c.getР2().getР28())
                        .okcmOrganizationBuyClaimRights_p2_21(c.getР2().getР29())
                        .countRestrict_p2_22(c.getР2().getР210())
                        .informationBancrot_p2_23(c.getР2().getР211Н())
                        .dateInformationBancrot_p2_24(c.getР2().getР212Н() != null ? LocalDate.of(c.getР2().getР212Н().getYear(), c.getР2().getР212Н().getMonth(), c.getР2().getР212Н().getDay()) : null)
                        .dateLastRestrict_p2_25(c.getР2().getР213Н() != null ? LocalDate.of(c.getР2().getР213Н().getYear(), c.getР2().getР213Н().getMonth(), c.getР2().getР213Н().getDay()) : null)
                        .encumbrances(getEncumbrances(c.getР2Обрем()))
                        .kindRestrict_p2_33(c.getР2().getР221())
                        .contractUID_p2_34(c.getР2().getР222())
                        .conditions(getConditionsByParam(c.getР3(), c.getУсл()))
                )
                .collect(Collectors.toList());

        final LocalDate reportDate = f303.getОтчДата() != null ? LocalDate.of(f303.getОтчДата().getYear()
                , f303.getОтчДата().getMonth()
                , f303.getОтчДата().getDay()) : null;
        return new F303()
                .reportDate(reportDate)
                .contracts(f303Contracts);
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
                .p3_41(conditions.getР37() != null ? LocalDate.of(conditions.getР37().getYear(), conditions.getР37().getMonth(), conditions.getР37().getDay()) : null)
                .p3_42(conditions.getР38() != null ? LocalDate.of(conditions.getР38().getYear(), conditions.getР38().getMonth(), conditions.getР38().getDay()) : null)
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
                        .p2_32(e.getР220Н() != null ? LocalDate.of(e.getР220Н().getYear(), e.getР220Н().getMonth(), e.getР220Н().getDay()) : null)
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
                .ogrnDate_p1_4(client.getР14() != null ? LocalDate.of(client.getР14().getYear(), client.getР14().getMonth(), client.getР14().getDay()) : null)
                .inn_p1_5(client.getР15())
                .innDescription_p1_6(client.getР15Тип())
                .okpo_p1_7(client.getР16())
                .countryCode_p1_8(client.getР17())
                .settingKo_p1_9(client.getР18())
                .businessCode_p1_10(client.getР19())
                .clientGVZs(clientGVZs);
    }
}