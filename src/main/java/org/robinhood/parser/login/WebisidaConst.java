package org.robinhood.parser.login;

import lombok.Getter;

@Getter
public enum WebisidaConst {

    ID_SELECTOR_USERNAME("UserName"),
    ID_SELECTOR_PASSWORD("Password"),
    XPATH_SELECTOR_SUBMIT("//input[@value='Войти в аккаунт']"),
    URL_LOGIN_PAGE("http://webisida.com/Account/LogOn?ReturnUrl=%2fAccount");

    String val;

    WebisidaConst(String val) {
        this.val = val;
    }
}
