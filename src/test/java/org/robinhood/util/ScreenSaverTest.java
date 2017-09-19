package org.robinhood.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.sun.deploy.util.SystemUtils.deleteRecursive;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ScreenSaverTest {

    private final ScreenSaver screenSaver = new ScreenSaver(dir.getAbsolutePath(), 3);

    private static File dir;

    @BeforeClass
    public static void beforeClass() throws IOException {
        dir = Files.createTempDirectory(null).toFile();
    }

    @AfterClass
    public static void afterClass() throws IOException {
        if (dir == null) {
            return;
        }
        deleteRecursive(dir);
        System.out.println(dir.exists());
    }

    @Test
    public void whenDoScreenThenCreatePrintScreen() throws IOException, AWTException {
        screenSaver.doScreen();
        final boolean result = dir.listFiles()[0].exists();
        assertTrue(result);
    }

    @Test
    public void whenDoScreenThenFilenameLikeTemplate() throws IOException, AWTException {
        screenSaver.doScreen();
        final String name = dir.listFiles()[0].getName();
        assertThat("SCREEN_0", is(name));
    }

    @Test
    public void whenDoScreenThenFilenameIncrement() throws IOException, AWTException {
        screenSaver.doScreen();
        screenSaver.doScreen();
        final String name = dir.listFiles()[1].getName();
        assertThat("SCREEN_1", is(name));
    }

    @Test
    public void whenAmountScreensOfMaxThenAllFilesDelete() throws IOException, AWTException {

        for (int i = 0; i < 4; i++) {
            screenSaver.doScreen();
        }

        int amountOfFiles = dir.listFiles().length;
        assertThat(1, is(amountOfFiles));
    }
}