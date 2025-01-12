package mit4201.a3.pom.tests;

import mit4201.a3.pom.pages.MySoftlogicHome;
import mit4201.a3.pom.pages.PhoneResult;
import mit4201.a3.pom.utils.ExcelUtils;
import mit4201.a3.pom.utils.TestingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestMobilePhonesSearch extends TestingUtils {
    @Test
    public void searchMobilePhones() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils("src/test/resources/TestData.xlsx", "phones");
        String selectURL = excelUtils.getCellData(0,1);
        String searchText = excelUtils.getCellData(1, 1);

        try {
            MySoftlogicHome mySoftlogicHome = loadBasePage().loadURL(selectURL);
            mySoftlogicHome.insertTextToSearchBox(searchText);
            PhoneResult phoneResult = mySoftlogicHome.clickSearchButton();

            phoneResult.scroll(0, 600);
            Assert.assertTrue(phoneResult.phoneCheckBox.isDisplayed(), "Samsung brand is not displayed!");
            phoneResult.clickPhone();

            Assert.assertTrue(driver.getTitle().contains("mobile phones"), "Page title does not contain 'mobile phones'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
