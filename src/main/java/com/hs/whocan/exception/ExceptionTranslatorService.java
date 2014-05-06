package com.hs.whocan.exception;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yinwenbo on 14-5-5.
 */
@Service
public class ExceptionTranslatorService {

    private static final String TEMPLATE_PLACEHOLDER = "${%s}";

    @Resource
    private Properties messages;

    public Properties getMessages() {
        return messages;
    }

    public void setMessages(Properties messages) {
        this.messages = messages;
    }

    public String trans(String message, Map<String, String> data){
        String template = messages.getProperty(message, message);
        if (MapUtils.isNotEmpty(data)) {
            return replace(template, data);
        }
        return template;
    }

    private String replace(String template, Map<String, String> data) {
        List<String> keys = new ArrayList<String>(data.size());
        List<String> values = new ArrayList<String>(data.size());
        for (String key : data.keySet()) {
            keys.add(String.format(TEMPLATE_PLACEHOLDER, key));
            values.add(data.get(key));
        }
        return StringUtils.replaceEach(template, getStringArray(keys), getStringArray(values));
    }

    private String[] getStringArray(List<String> keys) {
        return keys.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }
}
