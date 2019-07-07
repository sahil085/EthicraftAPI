package com.iskcon.EthicraftAPI.exception;

import com.iskcon.EthicraftAPI.EthicraftApiApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sahil on 3/10/18.
 */
public class CustomMessageException {

    private static Properties validationProperties = null;

    private static void loadValidationPropertiesBuilder() throws IOException {
        if (validationProperties == null) {
            InputStream inputStream = EthicraftApiApplication.class.getClassLoader().getResourceAsStream("message.properties");
            validationProperties = new Properties();
            validationProperties.load(inputStream);
        }
    }

    public static String getPropertyValue(String key){
        try {
            loadValidationPropertiesBuilder();
            return validationProperties.getProperty(key);
        }
        catch (Exception exp) {
            return "Server error.";
        }
    }
}
