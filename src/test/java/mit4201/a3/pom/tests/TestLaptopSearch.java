package mit4201.a3.pom.tests;

import mit4201.a3.pom.pages.LaptopResult;
import mit4201.a3.pom.pages.MySoftlogicHome;
import mit4201.a3.pom.utils.ExcelUtils;
import mit4201.a3.pom.utils.TestingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestLaptopSearch extends TestingUtils {
    @Test
    public void searchLaptop() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils("src/test/resources/TestData.xlsx", "laptops");
        String selectURL = excelUtils.getCellData(0,1);
        String searchText = excelUtils.getCellData(1, 1);

        try {
            MySoftlogicHome mySoftlogicHome = loadBasePage().loadURL(selectURL);
            mySoftlogicHome.insertTextToSearchBox(searchText);
            LaptopResult laptopResult = mySoftlogicHome.clickSearchButton();

            laptopResult.scroll(0, 400);
            Assert.assertTrue(laptopResult.laptopBrand.isDisplayed(), "Dell brand is not displayed!");
            laptopResult.clickBrand();

            Assert.assertTrue(driver.getTitle().contains("laptop"), "Page title does not contain 'laptop'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
