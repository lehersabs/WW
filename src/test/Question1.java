/*Question 1:
----------

There is a file containing a word and its possible meanings (like a Dictionary). The contents of the file look like this:

Apple – a fruit, a tech firm
Table – an object, contains rows and columns when used in context of computers
Orange – a fruit

Given a path to the file, do the following:

a)    Create a method called doesFileExist(String path) which takes the path of the file and tells the user if the file exists at that path or not. Assume all paths are relative to your project structure. If the file does not exist, catch the requisite exception.
b)    Read each word and its possible meanings and print them out. Your output should look like this:

Word1
Meaning 1
Meaning 2
Word2
Meaning1
Meaning2

Use appropriate data structures wherever necessary.*/


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

    public void doesFileExist(String path) throws FileNotFoundException {
        File f = new File(path);
        if (!(f.isFile() && f.exists()))
                throw new FileNotFoundException("File Doesn't exist");
        else
            System.out.println("File Exists");
    }

    public void readContent(String path) throws FileNotFoundException {
        doesFileExist(path);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject;
        JsonElement jsonElement = null;
        try {
            jsonElement = parser.parse(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        jsonObject = jsonElement.getAsJsonObject();
        for (String name : jsonObject.keySet()) {
            System.out.println("\n" + name);
            JsonArray arrayOfDescription = jsonObject.get(name).getAsJsonArray();
            for (JsonElement j : arrayOfDescription) {
                System.out.println(j.getAsString());
            }
        }
    }

    public String readConfigFile() {
        try {
            prop.load(new FileInputStream("./src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = prop.getProperty("filepath");
        return path;
    }

    @Test(description = "Question 1(a)")
    public void Question1a() throws FileNotFoundException {
        String finalFilePath = readConfigFile();
        doesFileExist(finalFilePath);
    }

    @Test(description = "Question 1(b)")
    public void Question1b() throws FileNotFoundException {
        String finalFilePath = readConfigFile();
        readContent(finalFilePath);
    }

}
