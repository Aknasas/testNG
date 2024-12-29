package mit4201.a3.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LaptopResult extends BasePage {
    @FindBy(xpath = "//a[@data-filter-brand='.brand_20' and contains(text(),'Dell')]")
    public WebElement laptopBrand;

    public LaptopResult(WebDriver driver) {
        super(driver);
    }

    public void clickBrand() {
        laptopBrand.click();
    }
}
