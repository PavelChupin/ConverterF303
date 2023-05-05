package ru.ysolutions.converter.helper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.ysolutions.converter.models.xml.Ф0409303;

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
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");

        //Убираем standalone из тега заголовка
        //Включаем режим вывода только XML фрагмента без заголовка
        //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        //Добавляем заголовок в нужном виде
        //marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"windows-1251\"?>");


        // устанавливаем флаг для читабельного вывода XML в JAXB
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Включаем экранирование символов
        //marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CustomCharacterEscapeHandler());

        // маршаллинг объекта в файл  ansi-1251
//        final XMLStreamWriter xmlStreamWriter =
//               XMLOutputFactory.newInstance().createXMLStreamWriter(new FileOutputStream(xmlFileOut.toFile()), "windows-1251");
        // XMLOutputFactory.newInstance().createXMLStreamWriter(new FileOutputStream(xmlFileOut.toFile()));
        //xmlStreamWriter.writeProcessingInstruction("xml", "version=\"1.0\" encoding=\"windows-1251\"");
        //xmlStreamWriter.writeStartDocument("windows-1251", "1.0");
        //xmlStreamWriter.
        marshaller.marshal(f303, xmlFileOut.toFile());
//        marshaller.marshal(f303, xmlStreamWriter);
//        xmlStreamWriter.writeEndDocument();
    }
}