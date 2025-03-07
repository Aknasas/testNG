package mit4201.a3.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver=driver;
    }

    public MySoftlogicHome loadURL(String url){
        driver.get(url);
        return PageFactory.initElements(driver, MySoftlogicHome.class);
    }

    public void scroll(int x,int y){
        new Actions(driver).scrollByAmount(x,y).perform();
    }
}
