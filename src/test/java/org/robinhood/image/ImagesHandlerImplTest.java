package org.robinhood.image;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static com.sun.deploy.util.SystemUtils.deleteRecursive;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ImagesHandlerImplTest {

    private final ImagesHandler imagesHandler = new ImagesHandlerImpl(dir.getAbsolutePath(), 3);

    private static File dir;

    public ImagesHandlerImplTest() throws AWTException {
    }

    @BeforeClass
    public static void beforeClass() throws IOException, NoSuchFieldException, IllegalAccessException {
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
        assertTrue(dir.listFiles()[0].exists());
    }

    @Test
    public void whenDoScreenThenFilenameLikeTemplate() throws IOException, AWTException {
        imagesHandler.doScreen();
        assertThat("SCREEN_0", is(dir.listFiles()[0].getName()));
    }

    @Test
    public void whenDoScreenThenFilenameIncrement() throws IOException, AWTException {
        imagesHandler.doScreen();
        imagesHandler.doScreen();
        assertThat("SCREEN_0", is(Arrays.asList(dir.listFiles()).stream().filter(item ->
                item.getName().equals("SCREEN_0")).findFirst().get().getName()));

        assertThat("SCREEN_1", is(Arrays.asList(dir.listFiles()).stream().filter(item ->
                item.getName().equals("SCREEN_1")).findFirst().get().getName()));
    }

    @Test
    public void whenAmountScreensOfMaxThenAllFilesDelete() throws IOException, AWTException {
        for (int i = 0; i < 4; i++) {
            imagesHandler.doScreen();
        }
        assertThat(1, is(dir.listFiles().length));
    }

    /**
     * Test images for matching.
     */
    private final File screenshot = new File("src/test/resources/screenshot.png");
    private final File subImage = new File("src/test/resources/image.png");
    private final File notContainSubImg = new File("src/test/resources/not_contain_img.png");

    @Test
    public void whenSubImageContainsInScreenshotThenReturnPointNotEmpty() throws IOException {
        final BufferedImage src = ImageIO.read(screenshot);
        final BufferedImage sub = ImageIO.read(subImage);
        final Point result = imagesHandler.findSubImg(sub, src);
        assertTrue(result.getX() != 0D);
        assertTrue(result.getY() != 0D);
    }

    @Test
    public void whenSubImageNotContainsInScreenshotThenReturnEmptyPoint() throws IOException {
        final BufferedImage sub = ImageIO.read(notContainSubImg);
        final BufferedImage src = ImageIO.read(screenshot);
        final Point result = imagesHandler.findSubImg(sub, src);
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