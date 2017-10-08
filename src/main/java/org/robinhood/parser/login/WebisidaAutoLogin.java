package org.robinhood.parser.login;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Author : Pavel Ravvich.
 * Created : 08.10.17.
 * <p>
 * WebisidaAutoLogin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WebisidaAutoLogin extends AutoLogIn {

    public WebisidaAutoLogin(final WebDriver driver) {

        super(driver);

        driver.navigate().to(WebisidaConst.URL_LOGIN_PAGE.getVal());
    }

    public void enter(final String username,
                      final String password) {

        driver.findElement(By.id(WebisidaConst.ID_SELECTOR_USERNAME.getVal()))
                .sendKeys(username);

        driver.findElement(By.id(WebisidaConst.ID_SELECTOR_PASSWORD.getVal()))
                .sendKeys(password);

        final By submit = By.xpath(WebisidaConst.XPATH_SELECTOR_SUBMIT.getVal());

        driver.findElement(submit).click();
    }
}
