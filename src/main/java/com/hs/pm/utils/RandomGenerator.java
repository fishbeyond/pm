package com.hs.pm.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by root on 14-3-16.
 */
@Service
public class RandomGenerator {

    public static int getRandom(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }
}
