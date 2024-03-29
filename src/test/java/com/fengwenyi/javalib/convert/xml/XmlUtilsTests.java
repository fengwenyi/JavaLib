package com.fengwenyi.javalib.convert.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fengwenyi.javalib.convert.XmlUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-05
 */
public class XmlUtilsTests {

    @Test
    public void testToXml() {
        User user = new User()
                .setName("Zhang San")
                .setAge(20)
                ;
        // String xml = XmlUtils.convertString(user);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        try {
            xml = xmlMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        PrintUtils.info(xml);
    }

}
