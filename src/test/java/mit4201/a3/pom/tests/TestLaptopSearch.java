package mit4201.a3.pom.tests;

import mit4201.a3.pom.pages.LaptopResult;
import mit4201.a3.pom.pages.MySoftlogicHome;
import mit4201.a3.pom.utils.ExcelUtils;
import mit4201.a3.pom.utils.TestingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLaptopSearch extends TestingUtils {
    @Test
    public void searchLaptop() {

        try {
            ExcelUtils excelUtils = new ExcelUtils("src/test/resources/TestData.xlsx", "laptops");
            String selectURL = excelUtils.getCellData(0,1);
            String searchText = excelUtils.getCellData(1, 1);
            String selectOption = excelUtils.getCellData(2, 1);
            System.out.println("URL: " + excelUtils.getCellData(0, 1));
            System.out.println("Search Text: " + excelUtils.getCellData(1, 1));
            System.out.println("Select Option: " + excelUtils.getCellData(2, 1));
            System.out.println("Page Title: " + driver.getTitle());

            MySoftlogicHome mySoftlogicHome = loadBasePage().loadURL(selectURL);
            mySoftlogicHome.insertTextToSearchBox(searchText);
            LaptopResult laptopResult = mySoftlogicHome.clickSearchButton();

            laptopResult.scroll(0, 400);
            Assert.assertTrue(laptopResult.laptopBrand.isDisplayed(), "Dell brand is not displayed!");
            laptopResult.clickBrand();

            Assert.assertTrue(driver.getTitle().contains("laptop"), "Page title does not contain 'shoes'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
