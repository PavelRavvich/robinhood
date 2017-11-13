package org.robinhood.parser.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.WebDriver;

/**
 * Author : Pavel Ravvich.
 * Created : 08.10.17.
 * <p>
 * AutoLogIn
 */
@Data
@AllArgsConstructor
public abstract class AutoLogIn implements Login {

    protected final WebDriver driver;

    public abstract void enter(final String username, final String password);
}
