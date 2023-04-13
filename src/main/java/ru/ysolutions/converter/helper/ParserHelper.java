package ru.ysolutions.converter.helper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.poi.ss.usermodel.Workbook;
import ru.ysolutions.converter.models.xml.Ф0409303;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

public class ParserHelper {
    public static Ф0409303 getObjectFromXMLFile(Path xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Ф0409303.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        //Устанавливаем  кодировку
        //unmarshaller.setProperty(Marshaller.JAXB_ENCODING, Charset.forName("windows-1251"));

        //Убираем standalone из тега заголовка
        //Включаем режим вывода только XML фрагмента без заголовка
        //unmarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        //Добавляем заголовок в нужном виде
        //unmarshaller.setProperty("com.sun.xml.bind.xmlHeaders","<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        // устанавливаем флаг для читабельного вывода XML в JAXB
        //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Включаем экранирование символов
        //unmarshaller.setProperty(CharacterEscapeHandler.class.getName(), new CustomCharacterEscapeHandler());

        // Получаем объект из файла XML
        return (Ф0409303) unmarshaller.unmarshal(xmlFile.toFile());
    }

    public static void saveObjectToXMLFile(Path xmlFileOut, Ф0409303 f303) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Ф0409303.class);
        Marshaller marshaller = context.createMarshaller();

        //Устанавливаем  кодировку
        marshaller.setProperty(Marshaller.JAXB_ENCODING, Charset.forName("windows-1251"));

        //Убираем standalone из тега заголовка
        //Включаем режим вывода только XML фрагмента без заголовка
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        //Добавляем заголовок в нужном виде
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"windows-1251\"?>");

        // устанавливаем флаг для читабельного вывода XML в JAXB
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Включаем экранирование символов
        //marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CustomCharacterEscapeHandler());

        // маршаллинг объекта в файл
        marshaller.marshal(f303, xmlFileOut.toFile());
    }

    public static void writeIntoExcelXlsx(Path fileTo, Workbook book) throws IOException {
        // Записываем всё в файл
        book.write(new FileOutputStream(fileTo.toFile()));
        book.close();
    }
}
