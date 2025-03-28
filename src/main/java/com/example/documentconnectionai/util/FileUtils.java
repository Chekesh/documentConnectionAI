package com.example.documentconnectionai.util;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Извлекает текст из переданного файла с помощью Apache Tika.
 *
 * @paramfile Файл, из которого нужно извлечь текст
 * @return Извлеченный текст
 * @throws IOException Если произошла ошибка ввода-вывода
 * @throws TikaException Если произошла ошибка парсинга файла
 */
public class FileUtils {
    public static String extractTextFromFile(MultipartFile file) throws IOException, TikaException, SAXException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Файл не может быть пустым или null");
        }
        BodyContentHandler handler = new BodyContentHandler();
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();

        try (InputStream stream = file.getInputStream()) {
            parser.parse(stream, handler, metadata);
            return handler.toString().trim();
        }
    }
}
