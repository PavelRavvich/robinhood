package org.robinhood;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Author : Pavel Ravvich.
 * Created : 13/11/2017.
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);
    }
}
