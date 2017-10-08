package org.robinhood.parser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Author : Pavel Ravvich.
 * Created : 08.10.17.
 * <p>
 * PageLoader
 */
public class PageLoader {

    public static void main(String[] args) {
        // Создаем экземпляр WebDriver
        // Следует отметить что скрипт работает с интерфейсом,
        // а не с реализацией.
        WebDriver driver = new FirefoxDriver();



        // Открываем гугл, используя драйвер
        //driver.get("http://webisida.com/Account/LogOn?ReturnUrl=%2fAccount");
        // По-другому это можно сделать так:
        driver.navigate().to("http://webisida.com/Account/LogOn?ReturnUrl=%2fAccount");
        //System.out.println(driver.getPageSource());

        final WebElement userName = driver.findElement(By.id("UserName"));
        userName.sendKeys("polikov");

        // Находим элемент по атрибуту name
        final WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("mg8esM");

        try {
            driver.findElement(By.xpath("//input[@value='Войти в аккаунт']")).click();
            System.out.println(driver.getPageSource());
        } finally {
            driver.quit();
        }
    }
}

