/*Question 2:
----------

The following exercise does not require user login. Please use ID or class as selectors.

Steps:
1. Navigate to https://www.weightwatchers.com/us/
2. Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help”
3. On the right corner of the page, click on “Find a Studio”
4. Verify loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
5. In the search field, search for meetings for zip code: 10011
6. Print the title of the first result and the distance (located on the right of location title/name)
7. Click on the first search result and then, verify displayed location name/title matches with the name of the first searched result that was clicked.
8. From this location page, print TODAY’s hours of operation (located towards the bottom of the page)
9. Create a method to print the number of meeting the each person(under the scheduled time) has a particular day of the week
e.g. printMeetings("Sun")
Output should be:
Person A  3
Person B  1

Write an automated test for this scenario using WebDriver. */

package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.PageFactoryObjects.WW;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Question2 {

    static WebDriver driver = null;
    WW locator;
    WebDriverWait wait;
    SeleniumHelper helper = new SeleniumHelper();
    SoftAssert sa = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        //The chromedriver in the project works for Chrome version 76. Please replace the chromedriver based on the chrome browser version.
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver");
        driver = new ChromeDriver();
        locator = new WW(driver);
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    @Parameters("dayOfWeek")
    public void Question2(String dayOfWeek) throws Exception {
        //Step 1 : Navigate to https://www.weightwatchers.com/us/
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.weightwatchers.com/us/");

        //Step 2 : Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help”
        String titleOfPage = driver.getTitle();
        sa.assertEquals(titleOfPage, "WW (Weight Watchers): Weight Loss & Wellness Help");

        //Step 3 : On the right corner of the page, click on “Find a Studio”
        locator.clickFindAStudio();
        helper.waitTillPageLoads(driver);

        //Step 4 : Verify loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
        titleOfPage = driver.getTitle();
        sa.assertEquals(titleOfPage, "Find WW Studios & Meetings Near You | WW USA");

        //Step 5 : In the search field, search for meetings for zip code: 10011
        locator.setmeeting_search_textbox("10011");
        helper.pressEnter(locator.getmeeting_search_textbox());

        //Step 6 : Print the title of the first result and the distance
        WebElement firstSearchResult = locator.getFirstResult();
        sa.assertTrue(firstSearchResult.isDisplayed());
        WebElement title = locator.getTitle();
        String resultPageTitle = title.getText();
        System.out.println(resultPageTitle);
        WebElement location = locator.getLocation();
        System.out.println(location.getText() + "\n");

        //Step 7 : Click on the first search result and then, verify displayed location name/title matches with the name of the first searched result that was clicked.
        locator.clickFirstResultTitle();
        WebElement profilePageName = locator.getProfileName();
        helper.waitTillElementIsDisplayed(locator.getProfileName(), wait);
        helper.waitTillElementIsDisplayed(locator.getOperating_hours(), wait);
        helper.waitTillElementIsDisplayed(locator.getStudio_schedule(), wait);
        sa.assertEquals(profilePageName.getText(), resultPageTitle);

        //Step 8 : From this location page, print TODAY’s hours of operation
        int currentDay = helper.getDayOfWeek();
        WebElement[] list = helper.getOperatingHours(driver, currentDay);
        for (WebElement w : list) {
            wait.until(ExpectedConditions.visibilityOf(w));
            System.out.println(w.getText());
        }

        //Step 9 : Create a method to print the number of meeting the each person(under the scheduled time) has a particular day of the week
        dayOfWeek = helper.convertDayToFormat(dayOfWeek);
        sa.assertTrue(dayOfWeek.length() == 3);

        if(dayOfWeek instanceof String){
            HashMap<String, HashMap<String, Integer>> map = helper.returnMeetingCount(driver);
            helper.printMeetings(dayOfWeek, map);
        }

        sa.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        helper.tearDown(driver);
    }


}
