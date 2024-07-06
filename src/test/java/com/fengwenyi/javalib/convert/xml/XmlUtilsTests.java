package com.fengwenyi.javalib.convert.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-05
 */
public class XmlUtilsTests {

    @Test
    public void testToXml() {
        User user = new User()
                .setName("Zhang San")
                .setAge(20);
        // String xml = XmlUtils.convertString(user);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        try {
            xml = xmlMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(xml);
    }

}
