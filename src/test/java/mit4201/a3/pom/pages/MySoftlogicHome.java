package mit4201.a3.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MySoftlogicHome extends BasePage {
    private String visibleText;

    @FindBy(xpath = "//input[@id='search_input']")
    public WebElement searchBox;


    @FindBy(xpath = "//button[@class='header_search_button trans_300']")
    public WebElement searchButton;

    public MySoftlogicHome(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void insertTextToSearchBox(String searchText) {
        searchBox.sendKeys(searchText);
    }


    public <T> T clickSearchButton() {
        searchButton.click();
        if (driver.getTitle().contains("mobile phones")) {
            return (T) PageFactory.initElements(driver, PhoneResult.class);
        } else {
            return (T) PageFactory.initElements(driver, LaptopResult.class);
        }
    }
}
