package Practo_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import base.base;

public class CitySelectionPage extends base {
    By city = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/input");
    By InitialcityRemove = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/span[2]/span/i");

    public void selectCity() throws Exception {
        WebElement from = driver.findElement(city);
        from.click();
        driver.findElement(InitialcityRemove).click();

        from.sendKeys(config.getProperty("cityName"));
        Thread.sleep(3000);

        from.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        from.sendKeys(Keys.ARROW_DOWN);
        from.sendKeys(Keys.ARROW_DOWN);
        from.sendKeys(Keys.ARROW_DOWN);
        from.sendKeys(Keys.ENTER);
    }
}


