package mit4201.a3.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhoneResult extends BasePage {
    @FindBy(xpath = "//a[@data-filter-brand='.brand_4' and contains(text(),'Samsung')]")
    public WebElement phoneCheckBox;

    public PhoneResult(WebDriver driver) {
        super(driver);
    }

    public void clickPhone() {
        phoneCheckBox.click();
    }

}
