package ru.ysolutions.converter.models.xls;

import ru.ysolutions.converter.ConverterExcel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelHeader {
    public final static Map<Integer, String> parts = new HashMap<>();

    static {
        parts.put(0, "Раздел 1. Сведения о заемщике");
        parts.put(12, "Раздел 2. Заключенные договоры");
        parts.put(34, "Раздел 3. Условия договоров");
        parts.put(55, "Раздел 4. Обеспечение, страхование");
        parts.put(61, "Раздел 5. Сведения о предоставлении ссуд");
        parts.put(70, "Раздел 6. Сведения, характеризующие задолженность по основному долгу");
        parts.put(84, "Раздел 7. Требования по получению процентных доходов");
        parts.put(87, "Раздел 8. Условные обязательства кредитного характера по ссуде");
        parts.put(91, "Раздел 9. Обслуживание долга");
        parts.put(105, "Раздел 10.  Уступка прав требования (цессия), эмиссия ценных бумаг, обеспеченных требованиями по ссудной задолженности");
    }

    public final static Map<Integer, ExcelHeaderColumnProperties> columnNames = new HashMap<>();

    static {
        final List<ExcelHeaderColumnProperties> names = Arrays.asList(new ExcelHeaderColumnProperties("Наименование заемщика", (short) 2000)
                , new ExcelHeaderColumnProperties("ОГРН", (short) 4000)
                , new ExcelHeaderColumnProperties("ОГРНИП", (short) 4000)
                , new ExcelHeaderColumnProperties("Дата государственной регистрации", (short) 3000)
                , new ExcelHeaderColumnProperties("Идентификационный номер налогоплательщика", (short) 4000)
                , new ExcelHeaderColumnProperties("Расшифровка ИНН", (short) 2000)
                , new ExcelHeaderColumnProperties("ОКПО", (short) 3000)
                , new ExcelHeaderColumnProperties("Код страны заемщика по ОКСМ", (short) 2000)
                , new ExcelHeaderColumnProperties("Характер отношений с КО", (short) 2000)
                , new ExcelHeaderColumnProperties("код вида экономической деятельности (для нерезидента)", (short) 2000)
                , new ExcelHeaderColumnProperties("Принадлежность к группе взаимосвязанных заемщиков: номер группы", (short) 5000)
                , new ExcelHeaderColumnProperties("Принадлежность к группе взаимосвязанных заемщиков: наименование группы", (short) 3500)
                , new ExcelHeaderColumnProperties("Идентификационный код основного договора", (short) 4000)
                , new ExcelHeaderColumnProperties("Номер основного договора", (short) 5000)
                , new ExcelHeaderColumnProperties("Дата основного договора", (short) 3000)
                , new ExcelHeaderColumnProperties("Номер договора о приобретении прав требования по ссуде", (short) 2000)
                , new ExcelHeaderColumnProperties("Дата договора о приобретении прав требования по ссуде", (short) 2000)
                , new ExcelHeaderColumnProperties("Наименование организации, у которой приобретена ссуда", (short) 2000)
                , new ExcelHeaderColumnProperties("Идентификатор организации, у которой приобретена ссуда: ОГРН/ОГРНИП", (short) 2000)
                , new ExcelHeaderColumnProperties("Идентификатор организации, у которой приобретена ссуда: регистрационный номер (для КО)", (short) 2000)
                , new ExcelHeaderColumnProperties("Идентификатор организации, у которой приобретена ссуда: ОКСМ (нерез)", (short) 2000)
                , new ExcelHeaderColumnProperties("Количество реструктуризаций", (short) 2000)
                , new ExcelHeaderColumnProperties("Информация о процедурах банкротства", (short) 2000)
                , new ExcelHeaderColumnProperties("Дата подачи заявления о признании должника банкротом", (short) 3000)
                , new ExcelHeaderColumnProperties("Дата последней реструктуризации", (short) 3000)
                , new ExcelHeaderColumnProperties("Инфо об обременении: наименование лица, в пользу которого осуществлено обременение", (short) 2000)
                , new ExcelHeaderColumnProperties("Инфо об обременении: идентификатор лица, в пользу кот. осуществлено обременение: ОГРН/ОГРНИП", (short) 2000)
                , new ExcelHeaderColumnProperties("Инфо об обременении: идентификатор лица, в пользу кот. осуществлено обременение: рег. номер КО", (short) 2000)
                , new ExcelHeaderColumnProperties("Инфо об обременении: идентификатор лица, в пользу кот. осуществлено обременение: код по ОКСМ", (short) 2000)
                , new ExcelHeaderColumnProperties("Инфо об обременении: обязательство, с которым связано обременение: вид", (short) 2000)
                , new ExcelHeaderColumnProperties("Инфо об обременении: обязательство, с которым связано обременение: стоимость, руб. коп.", (short) 2000)
                , new ExcelHeaderColumnProperties("Инфо об обременении: обязательство, с которым связано обременение: срок погашения", (short) 2000)
                , new ExcelHeaderColumnProperties("Вид реструктуризации", (short) 2000)
                , new ExcelHeaderColumnProperties("Уникальный идентификатор договора (сделки)", (short) 10000)
                , new ExcelHeaderColumnProperties("Вид ссуды", (short) 2000)
                , new ExcelHeaderColumnProperties("Цель кредитования", (short) 2000)
                , new ExcelHeaderColumnProperties("Сумма договора по первоначальному договору, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма договора с учетом изменений, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Код валюты договора по первоначальному договору", (short) 2000)
                , new ExcelHeaderColumnProperties("Код валюты договора с учетом изменений", (short) 2000)
                , new ExcelHeaderColumnProperties("Дата погашения задолженности по первоначальному договору", (short) 3000)
                , new ExcelHeaderColumnProperties("Дата погашения задолженности с учетом изменений", (short) 3000)
                , new ExcelHeaderColumnProperties("Вид процентной ставки по первоначальному договору", (short) 2000)
                , new ExcelHeaderColumnProperties("Размер процентной ставки по первоначальному договору, %", (short) 2000)
                , new ExcelHeaderColumnProperties("Размер процентной ставки по договору (с учетом изменений) на отчетную дату, %", (short) 2000)
                , new ExcelHeaderColumnProperties("Размер процентной ставки по просроченной части ссуды на отчетную дату, %", (short) 2000)
                , new ExcelHeaderColumnProperties("Процентный период по первоначальному договору, в днях", (short) 2000)
                , new ExcelHeaderColumnProperties("Вид компонента в основе ставки по первоначальному договору", (short) 4000)
                , new ExcelHeaderColumnProperties("Специальные условия договора: код условия", (short) 2000)
                , new ExcelHeaderColumnProperties("Специальные условия договора: идентификационный код договора", (short) 5000)
                , new ExcelHeaderColumnProperties("Регистрационный номер реорганизованной КО", (short) 2000)
                , new ExcelHeaderColumnProperties("Процентная ставка на отчетную дату (с учетом изменений): вид процентной ставки", (short) 2000)
                , new ExcelHeaderColumnProperties("Процентная ставка на отчетную дату (с учетом изменений): вид компонента в основе ставки", (short) 4000)
                , new ExcelHeaderColumnProperties("Субсидированная часть процентной ставки, в процентах по первоначальному договору", (short) 2000)
                , new ExcelHeaderColumnProperties("Субсидированная часть процентной ставки, в процентах на отчетную дату", (short) 2000)
                , new ExcelHeaderColumnProperties("Вид обеспечения", (short) 2000)
                , new ExcelHeaderColumnProperties("Стоимость обеспечения в рублевом эквиваленте, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Дата возникновения основания для обращения взыскания на обеспечение", (short) 3000)
                , new ExcelHeaderColumnProperties("Сумма обеспечения, принимаемая в расчет при определении резерва на возм. потери по ссуде, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма обеспечения для расчета резерва на возм. потери по треб. по получ. процент. доходов, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма обеспечения для расчета резерва на возм. потери по усл. обязат-вам кред. характера, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Дата предоставления ссуды (транша)", (short) 3000)
                , new ExcelHeaderColumnProperties("Номер транша", (short) 3000)
                , new ExcelHeaderColumnProperties("Объем предоставленных средств, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Код валюты", (short) 2000)
                , new ExcelHeaderColumnProperties("Код территории места нахождения по ОКАТО подразделения КО, предоставившего ссуду", (short) 2000)
                , new ExcelHeaderColumnProperties("Объем средств, пролонгированных в отчетном периоде, сумма, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Объем средств, пролонгированных в отчетном периоде, код валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Объем средств, предоставленных в отчетном периоде, сумма, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Объем средств, предоставленных в отчетном периоде, код валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Номер лицевого счета по учету задолженности, срочной", (short) 10000)
                , new ExcelHeaderColumnProperties("Номер лицевого счета по учету задолженности, просроченной", (short) 10000)
                , new ExcelHeaderColumnProperties("Задолженность по основному долгу, срочная, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Задолженность по основному долгу, просроченная, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Категория качества ссуды", (short) 2000)
                , new ExcelHeaderColumnProperties("Оценка на индивидуальной или портфельной основе", (short) 2000)
                , new ExcelHeaderColumnProperties("Резерв на возможные потери по ссудам, расчетный, %", (short) 2000)
                , new ExcelHeaderColumnProperties("Резерв на возможные потери по ссудам, расчетный с учетом обеспечения, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Резерв на возможные потери по ссудам, фактически сформированный, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Дополнительные сведения о классификации ссуд в соответствии с Положением N 590-П", (short) 2000)
                , new ExcelHeaderColumnProperties("Определение категории качества ссуды: финансовое положение заемщика", (short) 2000)
                , new ExcelHeaderColumnProperties("Определение категории качества ссуды: качество обслуживания долга", (short) 2000)
                , new ExcelHeaderColumnProperties("Код актива (только 6007 и тд)", (short) 2000)
                , new ExcelHeaderColumnProperties("Уровень кредитоспособности", (short) 2000)
                , new ExcelHeaderColumnProperties("Сумма требований по поручению процентных доходов, непросроченные, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма требований по поручению процентных доходов, просроченные, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Фактически сформированный резерв на возможные потери по треб. по получению проц. доходов, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма условных обязательств кредитного характера по ссуде, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Резерв на возможные потери, расчетный с учетом обеспечения, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Резерв на возможные потери, фактически сформированный, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Резерв на возможные потери, расчетный, процент", (short) 2000)
                , new ExcelHeaderColumnProperties("Периодичность погашения основного долга", (short) 2000)
                , new ExcelHeaderColumnProperties("Сумма выплаты основного долга, предусмотренная, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма выплаты основного долга, фактически уплаченная, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Периодичность уплаты процентов", (short) 2000)
                , new ExcelHeaderColumnProperties("Сумма процентов, предусмотренная, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма процентов, фактически уплаченная, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Сумма комиссий, штрафов и прочих выплат в пользу КО, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Дата выноса задолженности по основному долгу и/или по процентам на просрочку", (short) 3000)
                , new ExcelHeaderColumnProperties("Дата погашения просроченной задолженности по основному долгу и по процентам в полном объеме", (short) 3000)
                , new ExcelHeaderColumnProperties("Источник погашения", (short) 2000)
                , new ExcelHeaderColumnProperties("Идентификационный код нового кредитного договора", (short) 5000)
                , new ExcelHeaderColumnProperties("Регистрационный номер КО, за счет средств которой была погашена задолженность", (short) 2000)
                , new ExcelHeaderColumnProperties("Код валюты платежа по основному долгу", (short) 2000)
                , new ExcelHeaderColumnProperties("Код валюты платежа по процентам", (short) 2000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): Вид", (short) 2000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): Объем уступленных ден. средств (покрытия по цен. бум.), руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): Объем фактически полученных денежных средств, руб. коп.", (short) 4000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): Наименование организации", (short) 5000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): ОГРН/ОГРНИП", (short) 3000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): рег. номер КО", (short) 3000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): ОКСМ организации", (short) 3000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): Условия сделки: сумма, ед. валюты", (short) 4000)
                , new ExcelHeaderColumnProperties("Уступка прав требования (цессия): Условия сделки: код валюты", (short) 2000)
        );

        for (int i = 0; i < ConverterExcel.countColumn; i++) {
            columnNames.put(i, names.get(i));
        }

    }

    public final static Map<Integer, String> columnPart = new HashMap<>();

    static {
        final List<String> nameParts = Arrays.asList("1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "5_1"
                , "6"
                , "7"
                , "8"
                , "9"
                , "10"
                , "11"
                , "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
                , "10"
                , "11"
                , "12"
                , "13"
                , "14"
                , "15"
                , "16"
                , "17"
                , "18"
                , "19"
                , "20"
                , "21"
                , "22"
                , "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
                , "10"
                , "11"
                , "12"
                , "13"
                , "14"
                , "15"
                , "16"
                , "16_1"
                , "17"
                , "18"
                , "19"
                , "20"
                , "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
                , "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
                , "10"
                , "11"
                , "12"
                , "13"
                , "14"
                , "1"
                , "2"
                , "3"
                , "1"
                , "2"
                , "3"
                , "4"
                , "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
                , "10"
                , "11"
                , "12"
                , "13"
                , "14"
                , "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
        );

        for (int i = 0; i < ConverterExcel.countColumn; i++) {
            columnPart.put(i, nameParts.get(i));
        }
    }
}