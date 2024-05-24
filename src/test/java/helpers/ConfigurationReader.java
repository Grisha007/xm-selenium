package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private String browser;
    private String baseUrl;
    private int windowWidth;
    private int windowHeight;

    public ConfigurationReader() {

        String configurationPath = "src/test/resources/configuration.properties";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(configurationPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Configuration file not found at: " + configurationPath);
        }

        Properties properties = new Properties();
        try {
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        browser = properties.getProperty("browser");
        baseUrl = properties.getProperty("baseUrl");
        windowWidth = Integer.parseInt(properties.getProperty("windowWidth"));
        windowHeight = Integer.parseInt(properties.getProperty("windowHeight"));
    }

    public String getBrowser() {
        if (!browser.isEmpty()) return browser;
        else throw new RuntimeException("\"browser\" is not specified in the configuration.properties file");
    }

    public String getBaseUrl() {
        if (!baseUrl.isEmpty()) return baseUrl;
        else throw new RuntimeException("\"baseUrl\" is not specified in the configuration.properties file");
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
