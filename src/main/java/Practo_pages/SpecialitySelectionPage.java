package Practo_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import base.base;

public class SpecialitySelectionPage extends base {
    By Speciality = By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input");

    public void selectSpeciality() throws Exception {
        WebElement specialist = driver.findElement(Speciality);
        specialist.sendKeys(config.getProperty("specialty"));
        specialist.click();
        Thread.sleep(3000);

        specialist.sendKeys(Keys.ARROW_DOWN);
        specialist.sendKeys(Keys.ARROW_DOWN);
        specialist.sendKeys(Keys.ARROW_DOWN);
        specialist.sendKeys(Keys.ARROW_DOWN);
        specialist.sendKeys(Keys.ARROW_DOWN);
        specialist.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }
}

