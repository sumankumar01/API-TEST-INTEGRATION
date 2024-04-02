package org.example.apiUtil;

import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Util {
    @SneakyThrows
    public static  List<Map<String, Object>> config(String Key) {
        Reader yamlFile = new FileReader("src/test/resources/config.yml");
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMaps = (Map<String, Object>) yaml.load(yamlFile);
        System.out.println(yamlMaps);
        System.out.println(yamlMaps.get(Key));

        final List<Map<String, Object>> module_name = (List<Map<String, Object>>) yamlMaps.get(Key);
        return module_name;
       /* if (yamlMaps.get(Key).getClass().equals(java.util.ArrayList.class))
        */
    }
    @SneakyThrows
    public static  Map<String, Object> configkey() {
        Reader yamlFile = new FileReader("src/test/resources/config.yml");
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMaps = (Map<String, Object>) yaml.load(yamlFile);
        System.out.println(yamlMaps);
        return yamlMaps;
    }

}
