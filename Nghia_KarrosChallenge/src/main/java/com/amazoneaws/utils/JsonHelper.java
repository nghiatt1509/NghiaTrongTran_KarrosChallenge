package com.amazoneaws.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonHelper {

    /***
     * Read data from json file
     * @param jsonPath
     * @return
     */
    public static String getResponseBaseLine(String jsonPath)
    {
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = new JSONObject();

        try (FileReader reader = new FileReader("src/main/resources/APIResponse/" + jsonPath)) {
            //Read JSON file
            obj = (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return obj.toString();
    }
}
