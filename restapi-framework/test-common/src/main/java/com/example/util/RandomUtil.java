package com.example.util;

import com.example.logging.LogFactory;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class RandomUtil {
    private final Random cGenerator;
    private final AtomicLong seed;

    public RandomUtil(long seed) {
        Logger cLog = LogFactory.getLogger(this.getClass());
        cLog.info(() -> "Random SEED = " + seed);
        this.seed = new AtomicLong(seed);
        this.cGenerator = new Random(seed);
    }

    public Random geRandomGenerator() { return cGenerator; }

    public long nextLong() { return cGenerator.nextLong(); }

    public int nextInt() { return cGenerator.nextInt(); }

    public int nextInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Bound must be positive");
        }
        return cGenerator.nextInt(bound);
    }

    public double nextDouble() { return cGenerator.nextDouble(); }

    public double nextGaussian() { return cGenerator.nextGaussian(); }

    public int getRand(int min, int max) {
        if (min == max) {
            return min;
        }
        return (int) Math.round((nextDouble() * (max -min + 1)) - 0.5) + min;
    }

    public long getRand(long min, long max) {
        if (min == max) {
            return min;
        }
        return Math.round((nextDouble() * (max -min + 1)) - 0.5) + min;
    }

    public double getRand(double min, double max) {
        if (min == max) {
            return min;
        }
        return (nextDouble() * (max -min + 1) - 0.5) + min;
    }
}
