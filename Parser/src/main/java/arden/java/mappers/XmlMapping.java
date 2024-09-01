package arden.java.mappers;

import arden.java.interfaces.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XmlMapping<T> implements Mapper<T> {
    private final XmlMapper xmlMapper = new XmlMapper();

    {
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Override
    public String map(T input) {
        try {
            log.info("Input was successfully parsed to XML");

            return xmlMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            log.warn("File will be created with null fields");
            return null;
        }
    }
}
