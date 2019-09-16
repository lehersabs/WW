package test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.testng.annotations.Test;
import java.io.*;
import java.util.Properties;

public class Question1 {

    Properties prop = new Properties();

    String className = this.getClass().getSimpleName();

    public boolean doesFileExist(String path) {
        File f = new File(path);
        if (f.isFile() && f.exists())
            return true;
        return false;
    }

    public void readContent(String path) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject;
        JsonElement jsonElement = null;
        try {
            jsonElement = parser.parse(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        jsonObject = jsonElement.getAsJsonObject();
        for(String name: jsonObject.keySet()){
            System.out.println("\n"+name);
            JsonArray arrayOfDescription = jsonObject.get(name).getAsJsonArray();
            for(JsonElement j : arrayOfDescription){
                System.out.println(j.getAsString());
            }
        }
    }

    public String readConfigFile(){
        try {
            prop.load(new FileInputStream("./src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path=prop.getProperty("filepath");
        return path;
    }

    @Test(description = "Question 1(a)")
    public void Question1a() {
        String finalFilePath = readConfigFile();
        if(doesFileExist(finalFilePath))
            System.out.println("File Exists");
        else
            System.out.println("File Doesn't exist");

    }

    @Test(description = "Question 1(b)")
    public void Question1b() {
        String finalFilePath = readConfigFile();
        readContent(finalFilePath);
    }

}
