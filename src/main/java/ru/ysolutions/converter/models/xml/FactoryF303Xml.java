package ru.ysolutions.converter.models.xml;

import org.apache.commons.collections4.CollectionUtils;
import ru.ysolutions.converter.models.xls.f303.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;


public class FactoryF303Xml {
    public static Ф0409303 getDataXmlByF303(F303 f303Xls) {
        final Ф0409303 f303 = new Ф0409303();
        f303.setОтчДата(getXmlGregorianCalendarByLocalDate(f303Xls.reportDate()));
        f303.setДатаВремяФормирования(getXmlGregorianCalendarByLocalDateTime(LocalDateTime.now()));
        /* всегда ли константа */
        f303.setВидОтчета("отчет КО");
        /* всегда ли константа */
        f303.setПериодичность(ТипПериодаТип.МЕСЯЧНАЯ);
        f303.setКодФормы("0409303");
        /* задать вопрос как формировать*/
        f303.setУникИдОЭС("986FBCCE-A26E-4D0D-8460-55B53FE24D6E");
        /* всегда ли константа */
        f303.setВидОЭС(ВидОЭСтип.ОТЧЕТНОСТЬ_КО);
        f303.setОКУД("0409303");

        // Состовитель
        final Ф0409303Составитель creator = new Ф0409303Составитель();
        creator.setДатаПодписания(getXmlGregorianCalendarByLocalDate(LocalDate.now()));
        creator.setКодОрг("2546");
        creator.setВидОрг(ВидОргТип.КО);
        creator.setОКАТО("45286596");
        creator.setОКПО("17541272");
        creator.setАдрес("РОССИЯ, 119180, Москва г, Полянка Б. ул, 50/1 стр. 1");
        creator.setСокрНаимен("АО АКБ \"НОВИКОМБАНК\"");
        creator.setБИК("044525162");
        creator.setКодТУ("45");
        creator.setОГРН("1027739075891");

        final Ф0409303СоставительГлавБух buh = new Ф0409303СоставительГлавБух();
        buh.setДолжность("Главный бухгалтер");
        buh.setФИО("Литвинцева Н.А.");
        creator.setГлавБух(buh);

        final Ф0409303СоставительРуководитель manager = new Ф0409303СоставительРуководитель();
        manager.setДолжность("Первый заместитель Председателя Правления");
        manager.setФИО("Урсуляк Д.В.");
        creator.setРуководитель(manager);

        final Ф0409303СоставительИсполнитель executor = new Ф0409303СоставительИсполнитель();
        executor.setДолжность("Главный специалист");
        executor.setФИО("Травкина В.И.");
        executor.setТелефон("+7(495)974-7187, доб.137");
        //executor.setФакс("");
        //executor.setЭлПочта("");
        creator.setИсполнитель(executor);

        f303.setСоставитель(creator);

        f303.setДанные303(getDataF303ByParam(f303Xls));

        return f303;
    }

    private static Ф0409303Данные303 getDataF303ByParam(F303 f303Xls) {
        final Ф0409303Данные303 data = new Ф0409303Данные303();
        data.setИд(BigInteger.ONE);
        data.setОперДень(getXmlGregorianCalendarByLocalDate(LocalDate.now()));
        data.getДоговор().addAll(f303Xls.contracts()
                .stream()
                .map(FactoryF303Xml::getContractByParam)
                .collect(Collectors.toList())
        );
        return data;
    }

    private static Ф0409303Данные303Договор getContractByParam(F303Contract contractParam) {
        final Ф0409303Данные303Договор contract = new Ф0409303Данные303Договор();
        // Part 1 клиент
        contract.setР1(getClientByParam(contractParam.client_p1()));
        if (CollectionUtils.isNotEmpty(contractParam.client_p1().clientGVZs())) {
            contract.getР1ГВЗ().addAll(contractParam.client_p1().clientGVZs()
                    .stream()
                    .map(FactoryF303Xml::getGvzByParam)
                    .collect(Collectors.toList())
            );
        }

        // Part 2 договор
        contract.setР21(contractParam.p13());
        contract.setР2(getContractAttrByParam(contractParam));
        if (CollectionUtils.isNotEmpty(contractParam.encumbrances())) {
            contract.getР2Обрем().addAll(contractParam.encumbrances()
                    .stream()
                    .map(FactoryF303Xml::getEncumbrancyParam)
                    .collect(Collectors.toList())
            );
        }
        // Part 3
        contract.setР3(getConditionByParam(contractParam));
        if (CollectionUtils.isNotEmpty(contractParam.conditionsCodes())) {
            contract.getУсл().addAll(contractParam.conditionsCodes()
                    .stream()
                    .map(FactoryF303Xml::getConditionsCodesByParam)
                    .collect(Collectors.toList())
            );
        }
        // Part 4
        if (CollectionUtils.isNotEmpty(contractParam.warranties())) {
            contract.getР4Обесп().addAll(contractParam.warranties()
                    .stream()
                    .map(FactoryF303Xml::getWarranty)
                    .collect(Collectors.toList())
            );
        }

        // Part 5
        contract.setР5(getP5ByParam(contractParam));

        // Part 6
        contract.setР6(getP6ByParam(contractParam));

        // Part 7
        contract.setР7(getP7ByParam(contractParam));

        // Part 8
        contract.setР8(getP8ByParam(contractParam));

        // Part 9
        contract.setР9(getP9ByParam(contractParam));
        if (CollectionUtils.isNotEmpty(contractParam.f303Repayments())) {
            contract.getПогшн().addAll(contractParam.f303Repayments()
                    .stream()
                    .map(FactoryF303Xml::getRepayment)
                    .collect(Collectors.toList())
            );
        }
        if (CollectionUtils.isNotEmpty(contractParam.f303RepaymentSources())) {
            contract.getИст().addAll(contractParam.f303RepaymentSources()
                    .stream()
                    .map(FactoryF303Xml::getRepaymentSource)
                    .collect(Collectors.toList())
            );
        }
        // Part 10
        if (CollectionUtils.isNotEmpty(contractParam.p10())) {
            contract.getР10().addAll(contractParam.p10()
                    .stream()
                    .map(FactoryF303Xml::getP10ByParam)
                    .collect(Collectors.toList())
            );
        }

        // Part tranches
        if (CollectionUtils.isNotEmpty(contractParam.tranches())) {
            contract.getТранш().addAll(contractParam.tranches()
                    .stream()
                    .map(FactoryF303Xml::getTranchesByParam)
                    .collect(Collectors.toList())
            );
        }

        return contract;
    }

    private static Ф0409303Данные303ДоговорТранш getTranchesByParam(F303Tranche f303Tranche) {
        final Ф0409303Данные303ДоговорТранш tr = new Ф0409303Данные303ДоговорТранш();

        tr.setР52(f303Tranche.p63());

        //p2
        final Ф0409303Данные303ДоговорТраншР2Т p2 = new Ф0409303Данные303ДоговорТраншР2Т();
        p2.setР210(f303Tranche.p22());
        p2.setР213Н(getXmlGregorianCalendarByLocalDate(f303Tranche.p25()));
        p2.setР221(f303Tranche.p33());
        tr.setР2Т(p2);

        //p3
        final Ф0409303Данные303ДоговорТраншР3Т p3 = new Ф0409303Данные303ДоговорТраншР3Т();
        p3.setР32(f303Tranche.p36());
        p3.setР33(f303Tranche.p37());
        p3.setР34(f303Tranche.p38());
        p3.setР35(f303Tranche.p39());
        p3.setР36(f303Tranche.p40());
        p3.setР37(getXmlGregorianCalendarByLocalDate(f303Tranche.p41()));
        p3.setР38(getXmlGregorianCalendarByLocalDate(f303Tranche.p42()));
        p3.setР39(f303Tranche.p43());
        p3.setР310(f303Tranche.p44());
        p3.setР311(f303Tranche.p45());
        p3.setР312(f303Tranche.p46());
        p3.setР313Н(f303Tranche.p47());
        p3.setР314(f303Tranche.p48());
        if (CollectionUtils.isNotEmpty(f303Tranche.conditionsCodes())) {
            tr.getУслТ().addAll(f303Tranche.conditionsCodes()
                    .stream()
                    .map(FactoryF303Xml::getConditionsCodesTrncheByParam)
                    .collect(Collectors.toList())
            );
        }
        p3.setР317(f303Tranche.p52());
        p3.setР318(f303Tranche.p53());
        p3.setР319(f303Tranche.p54());
        p3.setР320(f303Tranche.p55());
        tr.setР3Т(p3);

        //p4
        if (CollectionUtils.isNotEmpty(f303Tranche.warranties())) {
            tr.getР4ОбеспТ().addAll(f303Tranche.warranties()
                    .stream()
                    .map(FactoryF303Xml::getWarrantyTranshe)
                    .collect(Collectors.toList())
            );
        }

        //p5
        final Ф0409303Данные303ДоговорТраншР5Т p5 = new Ф0409303Данные303ДоговорТраншР5Т();
        p5.setР51(getXmlGregorianCalendarByLocalDate(f303Tranche.p62()));
        p5.setР53(f303Tranche.p64());
        p5.setР54(f303Tranche.p65());
        p5.setР55(f303Tranche.p66());
        p5.setР56(f303Tranche.p67());
        p5.setР57(f303Tranche.p68());
        p5.setР58(f303Tranche.p69());
        p5.setР59(f303Tranche.p70());
        tr.setР5Т(p5);

        final Ф0409303Данные303ДоговорТраншР6Т p6 = new Ф0409303Данные303ДоговорТраншР6Т();
        p6.setР61(f303Tranche.p71());
        p6.setР62(f303Tranche.p72());
        p6.setР63(f303Tranche.p73());
        p6.setР64(f303Tranche.p74());
        p6.setР65(f303Tranche.p75());
        p6.setР66(f303Tranche.p76());
        p6.setР67(f303Tranche.p77());
        p6.setР68(f303Tranche.p78());
        p6.setР69(f303Tranche.p79());
        p6.setР610(f303Tranche.p80());

        p6.setР612(f303Tranche.p82());
        p6.setР613(f303Tranche.p83());
        p6.setР614(f303Tranche.p84());
        tr.setР6Т(p6);

        final Ф0409303Данные303ДоговорТраншР7Т p7 = new Ф0409303Данные303ДоговорТраншР7Т();
        p7.setР71(f303Tranche.p85());
        p7.setР72(f303Tranche.p86());
        p7.setР73Н(f303Tranche.p87());
        tr.setР7Т(p7);

        final Ф0409303Данные303ДоговорТраншР9Т p9 = new Ф0409303Данные303ДоговорТраншР9Т();
        p9.setР91(f303Tranche.p92());
        p9.setР92(f303Tranche.p93());
        p9.setР93(f303Tranche.p94());
        p9.setР94(f303Tranche.p95());
        p9.setР95(f303Tranche.p96());
        p9.setР96(f303Tranche.p97());
        p9.setР97(f303Tranche.p98());
        p9.setР98(getXmlGregorianCalendarByLocalDate(f303Tranche.p99()));
        p9.setР913(f303Tranche.p104());
        p9.setР914(f303Tranche.p105());
        tr.setР9Т(p9);

        if (CollectionUtils.isNotEmpty(f303Tranche.f303Repayments())) {
            tr.getПогшнТ().addAll(
                    f303Tranche.f303Repayments()
                            .stream()
                            .map(r -> {
                                final Ф0409303Данные303ДоговорТраншПогшнТ p = new Ф0409303Данные303ДоговорТраншПогшнТ();
                                p.setР98(getXmlGregorianCalendarByLocalDate(r.p99()));
                                p.setР99(getXmlGregorianCalendarByLocalDate(r.p100()));
                                return p;
                            })
                            .collect(Collectors.toList())
            );
        }

        if (CollectionUtils.isNotEmpty(f303Tranche.f303RepaymentSources())) {
            tr.getИстТ().addAll(f303Tranche.f303RepaymentSources()
                    .stream()
                    .map(FactoryF303Xml::getRepaymentSourceTranche)
                    .collect(Collectors.toList())
            );
        }

        return tr;
    }

    private static Ф0409303Данные303ДоговорТраншИстТ getRepaymentSourceTranche(F303RepaymentSource f303RepaymentSource) {
        final Ф0409303Данные303ДоговорТраншИстТ rp = new Ф0409303Данные303ДоговорТраншИстТ();
        rp.setР910(f303RepaymentSource.p101());

        if (f303RepaymentSource.p102() != null || f303RepaymentSource.p103() != null) {
            final Ф0409303Данные303ДоговорТраншИстТИстДог dog = new Ф0409303Данные303ДоговорТраншИстТИстДог();
            dog.setР911(f303RepaymentSource.p102());
            dog.setР912(f303RepaymentSource.p103());
            rp.getИстДог().add(dog);
        }

        if (f303RepaymentSource.p94() != null
                || f303RepaymentSource.p97() != null
                || f303RepaymentSource.p98() != null
                || f303RepaymentSource.p104() != null
                || f303RepaymentSource.p105() != null) {
            final Ф0409303Данные303ДоговорТраншИстТИстСум sum = new Ф0409303Данные303ДоговорТраншИстТИстСум();
            sum.setР93(f303RepaymentSource.p94());
            sum.setР96(f303RepaymentSource.p97());
            sum.setР97(f303RepaymentSource.p98());
            sum.setР913(f303RepaymentSource.p104());
            sum.setР914(f303RepaymentSource.p105());
            rp.getИстСум().add(sum);
        }

        return rp;
    }

    private static Ф0409303Данные303ДоговорТраншР4ОбеспТ getWarrantyTranshe(F303Warranty warranty) {
        final Ф0409303Данные303ДоговорТраншР4ОбеспТ cover = new Ф0409303Данные303ДоговорТраншР4ОбеспТ();
        cover.setР41(warranty.p56());
        cover.setР42(warranty.p57());
        cover.setР43(getXmlGregorianCalendarByLocalDate(warranty.p58()));
        cover.setР44(warranty.p59());
        cover.setР45Н(warranty.p60());
        cover.setР46Н(warranty.p61());
        return cover;
    }

    private static Ф0409303Данные303ДоговорТраншУслТ getConditionsCodesTrncheByParam(F303SpecialCondition f303SpecialCondition) {
        final Ф0409303Данные303ДоговорТраншУслТ condition = new Ф0409303Данные303ДоговорТраншУслТ();
        condition.setР315(f303SpecialCondition.p49());
        if (CollectionUtils.isNotEmpty(f303SpecialCondition.conditionsCodeConds())) {
            condition.getДогПоУсл().addAll(f303SpecialCondition.conditionsCodeConds()
                    .stream()
                    .map(FactoryF303Xml::getConditionsCodesCondTrancheByParam)
                    .collect(Collectors.toList())
            );
        }

        return condition;
    }

    private static Ф0409303Данные303ДоговорТраншУслТДогПоУсл getConditionsCodesCondTrancheByParam(F303SpecialConditionProperty f303SpecialConditionProperty) {
        final Ф0409303Данные303ДоговорТраншУслТДогПоУсл cond = new Ф0409303Данные303ДоговорТраншУслТДогПоУсл();
        cond.setР316(f303SpecialConditionProperty.p50());
        cond.setР316Рг(f303SpecialConditionProperty.p51());
        return cond;
    }

    private static Ф0409303Данные303ДоговорИст getRepaymentSource(F303RepaymentSource f303RepaymentSource) {
        final Ф0409303Данные303ДоговорИст rp = new Ф0409303Данные303ДоговорИст();
        rp.setР910(f303RepaymentSource.p101());
        if (f303RepaymentSource.p102() != null || f303RepaymentSource.p103() != null) {
            final Ф0409303Данные303ДоговорИстИстДог dog = new Ф0409303Данные303ДоговорИстИстДог();
            dog.setР911(f303RepaymentSource.p102());
            dog.setР912(f303RepaymentSource.p103());
            rp.getИстДог().add(dog);
        }

        if (f303RepaymentSource.p94() != null
                || f303RepaymentSource.p97() != null
                || f303RepaymentSource.p98() != null
                || f303RepaymentSource.p104() != null
                || f303RepaymentSource.p105() != null) {
            final Ф0409303Данные303ДоговорИстИстСум sum = new Ф0409303Данные303ДоговорИстИстСум();
            sum.setР93(f303RepaymentSource.p94());
            sum.setР96(f303RepaymentSource.p97());
            sum.setР97(f303RepaymentSource.p98());
            sum.setР913(f303RepaymentSource.p104());
            sum.setР914(f303RepaymentSource.p105());
            rp.getИстСум().add(sum);
        }

        return rp;
    }

    private static Ф0409303Данные303ДоговорИстИстДог getRepaymentSourceContracts(F303RepaymentSourceContract f303RepaymentSourceContract) {
        final Ф0409303Данные303ДоговорИстИстДог c = new Ф0409303Данные303ДоговорИстИстДог();
        c.setР911(f303RepaymentSourceContract.p102());
        c.setР912(f303RepaymentSourceContract.p103());
        return c;
    }

    private static Ф0409303Данные303ДоговорИстИстСум getRepaymentSourceProperties(F303RepaymentSourceProperties f303RepaymentSourceProperties) {
        final Ф0409303Данные303ДоговорИстИстСум r = new Ф0409303Данные303ДоговорИстИстСум();
        r.setР93(f303RepaymentSourceProperties.p94());
        r.setР96(f303RepaymentSourceProperties.p97());
        r.setР97(f303RepaymentSourceProperties.p98());
        r.setР913(f303RepaymentSourceProperties.p104());
        r.setР914(f303RepaymentSourceProperties.p105());

        return r;
    }

    private static Ф0409303Данные303ДоговорПогшн getRepayment(F303Repayment f303Repayment) {
        final Ф0409303Данные303ДоговорПогшн rp = new Ф0409303Данные303ДоговорПогшн();
        rp.setР98(getXmlGregorianCalendarByLocalDate(f303Repayment.p99()));
        rp.setР99(getXmlGregorianCalendarByLocalDate(f303Repayment.p100()));

        return rp;
    }

    private static Ф0409303Данные303ДоговорР9 getP9ByParam(F303Contract contractParam) {
        final Ф0409303Данные303ДоговорР9 p9 = new Ф0409303Данные303ДоговорР9();
        p9.setР91(contractParam.p92());
        p9.setР92(contractParam.p93());
        p9.setР93(contractParam.p94());
        p9.setР94(contractParam.p95());
        p9.setР95(contractParam.p96());
        p9.setР96(contractParam.p97());
        p9.setР97(contractParam.p98());
        p9.setР98(getXmlGregorianCalendarByLocalDate(contractParam.p99()));

        p9.setР913(contractParam.p104());
        p9.setР914(contractParam.p105());

        return p9;
    }

    private static Ф0409303Данные303ДоговорР8 getP8ByParam(F303Contract contractParam) {
        final Ф0409303Данные303ДоговорР8 p8 = new Ф0409303Данные303ДоговорР8();
        p8.setР81(contractParam.p88());
        p8.setР82(contractParam.p89());
        p8.setР83(contractParam.p90());
        p8.setР84(contractParam.p91());

        return p8;
    }

    private static Ф0409303Данные303ДоговорР7 getP7ByParam(F303Contract contractParam) {
        final Ф0409303Данные303ДоговорР7 p7 = new Ф0409303Данные303ДоговорР7();
        p7.setР71(contractParam.p85());
        p7.setР72(contractParam.p86());
        p7.setР73Н(contractParam.p87());
        return p7;
    }

    private static Ф0409303Данные303ДоговорР6 getP6ByParam(F303Contract contractParam) {
        final Ф0409303Данные303ДоговорР6 p6 = new Ф0409303Данные303ДоговорР6();
        p6.setР61(contractParam.p71());
        p6.setР62(contractParam.p72());
        p6.setР63(contractParam.p73());
        p6.setР64(contractParam.p74());
        p6.setР65(contractParam.p75());
        p6.setР66(contractParam.p76());
        p6.setР67(contractParam.p77());
        p6.setР68(contractParam.p78());
        p6.setР69(contractParam.p79());
        p6.setР610(contractParam.p80());
        p6.setР611(contractParam.p81());
        p6.setР612(contractParam.p82());
        p6.setР613(contractParam.p83());
        p6.setР614(contractParam.p84());

        return p6;
    }

    private static Ф0409303Данные303ДоговорР5 getP5ByParam(F303Contract contractParam) {
        final Ф0409303Данные303ДоговорР5 p5 = new Ф0409303Данные303ДоговорР5();
        p5.setР51(getXmlGregorianCalendarByLocalDate(contractParam.p62()));
        p5.setР53(contractParam.p64());
        p5.setР54(contractParam.p65());
        p5.setР55(contractParam.p66());
        p5.setР56(contractParam.p67());
        p5.setР57(contractParam.p68());
        p5.setР58(contractParam.p69());
        p5.setР59(contractParam.p70());

        return p5;
    }

    private static Ф0409303Данные303ДоговорУсл getConditionsCodesByParam(F303SpecialCondition f303SpecialCondition) {
        final Ф0409303Данные303ДоговорУсл condition = new Ф0409303Данные303ДоговорУсл();
        condition.setР315(f303SpecialCondition.p49());
        if (CollectionUtils.isNotEmpty(f303SpecialCondition.conditionsCodeConds())) {
            condition.getДогПоУсл().addAll(f303SpecialCondition.conditionsCodeConds()
                    .stream()
                    .map(FactoryF303Xml::getConditionsCodesCondByParam)
                    .collect(Collectors.toList())
            );
        }
        return condition;
    }

    private static Ф0409303Данные303ДоговорУслДогПоУсл getConditionsCodesCondByParam(F303SpecialConditionProperty f303SpecialConditionProperty) {
        final Ф0409303Данные303ДоговорУслДогПоУсл cond = new Ф0409303Данные303ДоговорУслДогПоУсл();
        cond.setР316(f303SpecialConditionProperty.p50());
        cond.setР316Рг(f303SpecialConditionProperty.p51());

        return cond;
    }

    private static Ф0409303Данные303ДоговорР3 getConditionByParam(F303Contract contractParam) {
        final Ф0409303Данные303ДоговорР3 condition = new Ф0409303Данные303ДоговорР3();
        condition.setР31(contractParam.p35());
        condition.setР32(contractParam.p36());
        condition.setР33(contractParam.p37());
        condition.setР34(contractParam.p38());
        condition.setР35(contractParam.p39());
        condition.setР36(contractParam.p40());
        condition.setР37(getXmlGregorianCalendarByLocalDate(contractParam.p41()));
        condition.setР38(getXmlGregorianCalendarByLocalDate(contractParam.p42()));
        condition.setР39(contractParam.p43());
        condition.setР310(contractParam.p44());
        condition.setР311(contractParam.p45());
        condition.setР312(contractParam.p46());
        condition.setР313Н(contractParam.p47());
        condition.setР314(contractParam.p48());
        condition.setР317(contractParam.p52());
        condition.setР318(contractParam.p53());
        condition.setР319(contractParam.p54());
        condition.setР320(contractParam.p55());

        return condition;
    }

    private static Ф0409303Данные303ДоговорР10 getP10ByParam(F303p10 f303p10) {
        final Ф0409303Данные303ДоговорР10 p10 = new Ф0409303Данные303ДоговорР10();
        p10.setР101(f303p10.p106());
        p10.setР102(f303p10.p107());
        p10.setР103(f303p10.p108());
        p10.setР104(f303p10.p109());
        p10.setР105(f303p10.p110());
        p10.setР106(f303p10.p111());
        p10.setР107(f303p10.p112());
        p10.setР108(f303p10.p113());
        p10.setР109(f303p10.p114());

        return p10;
    }

    private static Ф0409303Данные303ДоговорР2Обрем getEncumbrancyParam(F303Encumbrance encumbrance) {
        final Ф0409303Данные303ДоговорР2Обрем en = new Ф0409303Данные303ДоговорР2Обрем();
        en.setР214Н(encumbrance.p26());
        en.setР215Н(encumbrance.p27());
        en.setР216Н(encumbrance.p28());
        en.setР217Н(encumbrance.p29());
        en.setР218Н(encumbrance.p30());
        en.setР219Н(encumbrance.p31());
        en.setР220Н(getXmlGregorianCalendarByLocalDate(encumbrance.p32()));

        return en;
    }

    private static Ф0409303Данные303ДоговорР2 getContractAttrByParam(F303Contract contractParam) {
        final Ф0409303Данные303ДоговорР2 contract = new Ф0409303Данные303ДоговорР2();
        contract.setР22(contractParam.p14());
        contract.setР23(getXmlGregorianCalendarByLocalDate(contractParam.p15()));
        contract.setР24(contractParam.p16());
        contract.setР25(getXmlGregorianCalendarByLocalDate(contractParam.p17()));
        contract.setР26(contractParam.p18());
        contract.setР27(contractParam.p19());
        contract.setР28(contractParam.p20());
        contract.setР29(contractParam.p21());
        contract.setР210(contractParam.p22());
        contract.setР211Н(contractParam.p23());
        contract.setР212Н(getXmlGregorianCalendarByLocalDate(contractParam.p24()));
        contract.setР213Н(getXmlGregorianCalendarByLocalDate(contractParam.p25()));
        contract.setР221(contractParam.p33());
        contract.setР222(contractParam.p34());

        return contract;
    }

    private static Ф0409303Данные303ДоговорР1ГВЗ getGvzByParam(F303ClientGVZ f303ClientGVZ) {
        final Ф0409303Данные303ДоговорР1ГВЗ gvz = new Ф0409303Данные303ДоговорР1ГВЗ();
        gvz.setР110(f303ClientGVZ.p11());
        gvz.setР111(f303ClientGVZ.p12());
        return gvz;
    }

    private static Ф0409303Данные303ДоговорР1 getClientByParam(F303Client client_p1) {
        final Ф0409303Данные303ДоговорР1 client = new Ф0409303Данные303ДоговорР1();
        client.setР11(client_p1.p1());
        client.setР12(client_p1.p2());
        client.setР13(client_p1.p3());
        client.setР14(getXmlGregorianCalendarByLocalDate(client_p1.p4()));
        client.setР15(client_p1.p5());
        client.setР15Тип(client_p1.p6());
        client.setР16(client_p1.p7());
        client.setР17(client_p1.p8());
        client.setР18(client_p1.p9());
        client.setР19(client_p1.p10());

        return client;
    }

    private static Ф0409303Данные303ДоговорР4Обесп getWarranty(F303Warranty warranty) {
        final Ф0409303Данные303ДоговорР4Обесп cover = new Ф0409303Данные303ДоговорР4Обесп();
        cover.setР41(warranty.p56());
        cover.setР42(warranty.p57());
        cover.setР43(getXmlGregorianCalendarByLocalDate(warranty.p58()));
        cover.setР44(warranty.p59());
        cover.setР45Н(warranty.p60());
        cover.setР46Н(warranty.p61());
        return cover;
    }

    private static XMLGregorianCalendar getXmlGregorianCalendarByLocalDate(LocalDate localDate) {
        try {
            if (localDate == null) return null;
            final GregorianCalendar gcal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            final XMLGregorianCalendar result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            result.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            result.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            result.setSecond(DatatypeConstants.FIELD_UNDEFINED);
            result.setMinute(DatatypeConstants.FIELD_UNDEFINED);
            result.setHour(DatatypeConstants.FIELD_UNDEFINED);
            return result;
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static XMLGregorianCalendar getXmlGregorianCalendarByLocalDateTime(LocalDateTime localDateTime) {
        try {
            if (localDateTime == null) return null;

            String iso = localDateTime.toString();
            if (localDateTime.getSecond() == 0 && localDateTime.getNano() == 0) {
                iso += ":00"; // necessary hack because the second part is not optional in XML
            }
            final XMLGregorianCalendar result = DatatypeFactory.newInstance().newXMLGregorianCalendar(iso);
            result.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            result.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            return result;
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
