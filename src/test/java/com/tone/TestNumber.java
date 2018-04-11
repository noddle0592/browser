package com.tone;

import com.tone.gf.strategy.ArithUtil;
import org.junit.Test;

public class TestNumber {

    @Test
    public void testDouble() {
        double gradient = 17.67, floatPrice = 0.01;
        System.out.println("gradient = " + gradient + ", float = " + floatPrice + ", sellPrice = " + (gradient + floatPrice));
        System.out.println("gradient = " + gradient + ", float = " + floatPrice + ", sellPrice = " + ArithUtil.add(gradient, floatPrice));
    }
}
