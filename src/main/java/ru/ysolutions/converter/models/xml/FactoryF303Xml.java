package ru.ysolutions.converter.models.xml;

import org.apache.commons.collections4.CollectionUtils;
import ru.ysolutions.converter.models.xls.f303.*;

import javax.xml.datatype.DatatypeConfigurationException;
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
        f303.setУникИдОЭС("4FE94FDB-8C5D-4DE0-9AAC-886AB3789267");
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
        creator.setАдрес("119180, г. Москва, ул. Полянка Большая, д. 50/1, стр. 1.");
        creator.setСокрНаимен("АО АКБ \"НОВИКОМБАНК\"");
        creator.setБИК("044525162");
        creator.setКодТУ("45");
        creator.setОГРН("1027739075891");

        final Ф0409303СоставительГлавБух buh = new Ф0409303СоставительГлавБух();
        buh.setДолжность("Главный бухгалтер");
        buh.setФИО("Петров П.П.");
        creator.setГлавБух(buh);

        final Ф0409303СоставительРуководитель manager = new Ф0409303СоставительРуководитель();
        manager.setДолжность("Первый заместитель Председателя Правления");
        manager.setФИО("Иванов И.И.");
        creator.setРуководитель(manager);

        final Ф0409303СоставительИсполнитель executor = new Ф0409303СоставительИсполнитель();
        executor.setДолжность("Главный специалист");
        executor.setФИО("Сидоров С.С.");
        executor.setТелефон("+7(495)111-1111, доб.111");
        executor.setФакс("");
        executor.setЭлПочта("");
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
        return contract;
    }

    private static Ф0409303Данные303ДоговорИст getRepaymentSource(F303RepaymentSource f303RepaymentSource) {
        final Ф0409303Данные303ДоговорИст rp = new Ф0409303Данные303ДоговорИст();
        rp.setР910(f303RepaymentSource.p101());
        if (CollectionUtils.isNotEmpty(f303RepaymentSource.f303RepaymentSourceProperties())) {
            rp.getИстСум().addAll(f303RepaymentSource.f303RepaymentSourceProperties()
                    .stream()
                    .map(FactoryF303Xml::getRepaymentSourceProperties)
                    .collect(Collectors.toList())

            );
        }
        if (CollectionUtils.isNotEmpty(f303RepaymentSource.f303RepaymentSourceContracts())) {
            rp.getИстДог().addAll(f303RepaymentSource.f303RepaymentSourceContracts()
                    .stream()
                    .map(FactoryF303Xml::getRepaymentSourceContracts)
                    .collect(Collectors.toList())
            );
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
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
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

            return DatatypeFactory.newInstance().newXMLGregorianCalendar(iso);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @XmlElement(name = "\u0414\u0430\u043d\u043d\u044b\u0435303")
    protected Ф0409303Данные303 данные303;

    @XmlElement(name = "\u041d\u0435\u0442\u0414\u0430\u043d\u043d\u044b\u0445")
    protected Ф0409303НетДанных нетДанных;

    @XmlElement(name = "\u041f\u043e\u044f\u0441\u043d\u0435\u043d\u0438\u0435")
    protected Ф0409303Пояснение пояснение;

    @XmlElement(name = "\u041f\u0440\u043e\u0442\u043e\u043a\u043e\u043b\u041a\u043e\u043d\u0442\u0440\u043e\u043b\u044f")
    protected Ф0409303ПротоколКонтроля протоколКонтроля;

    @XmlElement(name = "\u041f\u043e\u044f\u0441\u043d\u0435\u043d\u0438\u0435\u041e\u0448\u0438\u0431\u043e\u043a")
    protected Ф0409303ПояснениеОшибок пояснениеОшибок;

    @XmlElement(name = "\u0418\u043d\u0444\u041f\u041a")
    protected Ф0409303ИнфПК инфПК;
    */
}
