package org.robinhood.parser;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.robinhood.parser.login.WebisidaAutoLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : Pavel Ravvich.
 * Created : 08.10.17.
 * <p>
 * PageLoader
 */
@Slf4j
public class PageLoader {

    private final static Logger logger = LoggerFactory.getLogger(PageLoader.class);

    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();

        try {
            new WebisidaAutoLogin(driver).enter("polikov", "mg8esM");
            logger.info(driver.getPageSource());
        } finally {
            driver.quit();
        }

        logger.info("!!!!!!!!========================+++!!!!!!!!");
    }
}

