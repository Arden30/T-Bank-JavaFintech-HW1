package arden.java;

import arden.java.mappers.XmlMapping;
import arden.java.model.City;
import arden.java.parsers.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
public class JsonToXmlParser {
    private final Dotenv dotenv;

    public void toXml(Path jsonFile) {
        JsonParser<City> jp = new JsonParser<>(City.class);
        City parsed = jp.parse(new File(jsonFile.toString()));
        XmlMapping<City> xmlMapping = new XmlMapping<>();

        String filename = jsonFile.getFileName().toString();
        String xmlFileName = filename.substring(0, filename.lastIndexOf('.')) + ".xml";

        try {
            Files.writeString(Path.of(dotenv.get("OUTPUT_DIRECTORY") + xmlFileName), xmlMapping.map(parsed));
            log.info("Result was saved in {}", xmlFileName);
        } catch (IOException e) {
            log.error("Error while writing xml file", e);
        }
    }
}
