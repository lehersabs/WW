package test.PageFactoryObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WW {

	WebDriver driver;

    public WW(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//a[@id='ela-menu-visitor-desktop-supplementa_find-a-studio']//span[contains(text(),'Find a Studio')]")
    WebElement find_a_studio;

    public void clickFindAStudio(){
        find_a_studio.click();
    }

    @FindBy(xpath = "//input[@id='meetingSearch']")
    WebElement meeting_search_textbox;

    public WW setmeeting_search_textbox(String strUserName){
        meeting_search_textbox.sendKeys(strUserName);
        return this;
    }
    public WebElement getmeeting_search_textbox(){
        return meeting_search_textbox;
    }

    @FindBy(xpath="(//div[@class='meeting-location'])[1]")
    WebElement first_result;

    public WebElement getFirstResult() {
        return first_result;
    }

    @FindBy(xpath = "(//div[@class='location__name'])[1]")
    WebElement result_title;

    public WebElement getTitle() {
        return result_title;
    }
    public void clickFirstResultTitle(){
        result_title.click();
    }

    @FindBy(xpath = "(//div[@class='location__distance'])[1]")
    WebElement result_location;

    public WebElement getLocation() {
        return result_location;
    }

    @FindBy(xpath="//div[@class='meeting-information__left']//div[@class='location__name']//span")
    WebElement profile_name;

    public WebElement getProfileName() {
        return profile_name;
    }


}
