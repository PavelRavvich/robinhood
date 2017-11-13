package org.robinhood.parser.ui4j_ex;

import io.webfolder.ui4j.api.browser.Page;
import io.webfolder.ui4j.api.dom.Document;
import io.webfolder.ui4j.api.dom.Element;

import static io.webfolder.ui4j.api.browser.BrowserFactory.getWebKit;

/**
 * Author : Pavel Ravvich.
 * Created : 08.10.17.
 * <p>
 * Ui4jex
 */
public class Ui4jex {

    private static String LOGIN_PAGE = "http://webisida.com/Account/LogOn?ReturnUrl=%2fAccount";

    public static void main(String[] args) {

        try (final Page page = getWebKit().navigate(LOGIN_PAGE)) {

            final Document document = page.getDocument();

            final Element username =
                    document.query("input[name='UserName']").get();

            final Element password =
                    document.query("input[name='Password']").get();

            username.setValue("polikov");
            password.setValue("mg8esM");

            document.query("input[value='Войти в аккаунт']").get().click();

            page.show();
            System.out.println(document.getBody());
            page.close();
        }
    }
}
