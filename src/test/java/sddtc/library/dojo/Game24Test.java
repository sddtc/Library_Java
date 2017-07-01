package sddtc.library.dojo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class Game24Test {
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    final int[] digits = {1,2,4,8};

    @Test
    public void should_return_correct() {
        Game24 game24 = new Game24();
        systemInMock.provideText("12*48+*");
        Assert.assertEquals("Correct!", game24.playing(digits));
    }

    @Test
    public void should_return_incorrect() {
        Game24 game24 = new Game24();
        systemInMock.provideText("12*48*+");
        Assert.assertEquals("Not correct.", game24.playing(digits));
    }

    @Test
    public void should_return_value_incorrect() {
        Game24 game24 = new Game24();
        systemInMock.provideText("12*49*+");
        Assert.assertEquals("Not the same digits.", game24.playing(digits));
    }
}
