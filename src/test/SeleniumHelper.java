package test;

import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.*;

public class SeleniumHelper {

    List<String> days_of_week = Arrays.asList("SUN","MON","TUE","WED","THU","FRI","SAT");

    public void waitTillPageLoads(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            if (js.executeScript("return document.readyState").toString().equals("complete")
                    || js.executeScript("return document.readyState").equals("loaded")) {
                break;
            }
        }
    }

    public void pressEnter(WebElement we){
        we.sendKeys(Keys.RETURN);
    }

    public void elementPresentCheck(WebElement we) {
        try {
            Assert.assertTrue(we.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Element not found : " + we);
        }
    }

    public void tearDown(WebDriver driver){
        driver.close();
    }

    public int getDayOfWeek(){
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public WebElement[] getOperatingHours(WebDriver driver,int currentday){
        String pathDay = "(//div[@class='hours-list-item-day'])["+currentday+"]";
        String pathHours = "(//div[@class='hours-list-item-hours'])["+currentday+"]";
        WebElement[] hours = {driver.findElement(By.xpath(pathDay)),driver.findElement(By.xpath(pathHours))};
        return hours;
    }

    public String convertDayToFormat(String day){
        if(day.length()>3){
            day = day.substring(0,3);
        }
        return day.toUpperCase();
    }

    public boolean checkIfInputDayIsValid(String input_day){
        if(!days_of_week.contains(input_day))
            return false;
        return true;
    }

    public HashMap returnMeetingCount(WebDriver driver){
        HashMap<String, HashMap<String,Integer>> lhm = new LinkedHashMap<>();
        for(int i=1;i<=7;i++){
            HashMap<String,Integer> hm = new HashMap<>();
            String xpath = "(//div[@class='schedule-detailed-day-meetings'])["+i+"]//div[@class='schedule-detailed-day-meetings-item-leader']";
            List<WebElement> web_elements_array = driver.findElements(By.xpath(xpath));
            for(WebElement element:web_elements_array){
                String name = element.getText();

                if(hm.containsKey(name))
                    hm.put(name,hm.get(name)+1);
                else
                    hm.put(name,1);
                }
            lhm.put(days_of_week.get(i-1),hm);
            }
        return lhm;
    }

    public void printMeetings(String day, HashMap<String, HashMap<String,Integer>> map){
        HashMap<String,Integer> inner_map = map.get(day);
        System.out.println();
        for(Map.Entry me: inner_map.entrySet()){
            System.out.println(me.getKey() + "  " + me.getValue());
        }
    }
}
