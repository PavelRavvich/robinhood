package org.robinhood.image;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.sun.deploy.util.SystemUtils.deleteRecursive;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ImagesHandlerImplTest {

    private final ImagesHandler imagesHandler = new ImagesHandlerImpl(dir.getAbsolutePath(), 3);

    private static File dir;

    public ImagesHandlerImplTest() throws AWTException {
    }

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
        imagesHandler.doScreen();
        final boolean result = dir.listFiles()[0].exists();
        assertTrue(result);
    }

    @Test
    public void whenDoScreenThenFilenameLikeTemplate() throws IOException, AWTException {
        imagesHandler.doScreen();
        final String name = dir.listFiles()[0].getName();
        assertThat("SCREEN_0", is(name));
    }

    @Test
    public void whenDoScreenThenFilenameIncrement() throws IOException, AWTException {
        imagesHandler.doScreen();
        imagesHandler.doScreen();
        final String name = dir.listFiles()[1].getName();
        assertThat("SCREEN_1", is(name));
    }

    @Test
    public void whenAmountScreensOfMaxThenAllFilesDelete() throws IOException, AWTException {

        for (int i = 0; i < 4; i++) {
            imagesHandler.doScreen();
        }

        int amountOfFiles = dir.listFiles().length;
        assertThat(1, is(amountOfFiles));
    }

    /**
     * Test images for matching.
     */
    private final File screenshot = new File("/Users/pavel/GitHub/robinhood/core-device-manager/src/test/java/org/robinhood/image/test_image/screenshot.png");
    private final File subImage = new File("/Users/pavel/GitHub/robinhood/core-device-manager/src/test/java/org/robinhood/image/test_image/image.png");
    private final File notContainSubImg = new File("/Users/pavel/GitHub/robinhood/core-device-manager/src/test/java/org/robinhood/image/test_image/not_contain_ing.png");

    @Test
    public void whenSubImageContainsInScreenshotThenReturnPointNotEmpty() throws IOException {
        final Point result = imagesHandler.findSubImg(subImage, screenshot);
        assertTrue(result.getX() != 0D);
        assertTrue(result.getY() != 0D);
    }

    @Test
    public void whenSubImageNotContainsInScreenshotThenReturnEmptyPoint() throws IOException {
        final Point result = imagesHandler.findSubImg(notContainSubImg, screenshot);
        assertThat(result.getX(), is(0D));
        assertThat(result.getY(), is(0D));
    }

    @Test
    public void whenSubImageContainsInScreenshotStreamThenReturnPointNotEmpty() throws IOException {
        final Point result = imagesHandler.findSubImgStream(subImage, screenshot);
        assertTrue(result.getX() != 0D);
        assertTrue(result.getY() != 0D);
    }

    @Test
    public void whenSubImageNotContainsInScreenshotStreamThenReturnEmptyPoint() throws IOException {
        final Point result = imagesHandler.findSubImgStream(notContainSubImg, screenshot);
        assertThat(result.getX(), is(0D));
        assertThat(result.getY(), is(0D));
    }
}