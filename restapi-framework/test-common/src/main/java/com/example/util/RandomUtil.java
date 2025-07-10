package com.example.util;

import com.example.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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

    /*
    * Get a random double in an interval with desired scale
    * */
    public double getRand(double min, double max, int scale) {
        return BigDecimal.valueOf(getRand(min, max))
                .setScale(scale, RoundingMode.FLOOR)
                .doubleValue();
    }

    public BigDecimal getRandBigDecimal(double min, double max, int scale) {
        return BigDecimal.valueOf(getRand(min, max))
                .setScale(scale, RoundingMode.FLOOR);
    }

    /*
    * Get a random number up to a maimum. Minimum in this case is 0
    * */
    public int getRand(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max must be positive");
        }
        return getRand(0, max);
    }

    public long getRand(long max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max must be positive");
        }
        return getRand(0, max);
    }

    /*
    * Get the last seed given to the generator
    * */

    public long getSeed() { return seed.get(); }

    /*
    * Select a fixed number of random elements (but not the same one more than once)
    *
    * @param <0>
    * @param allItems   The array to select elements from
    * @param subsetLength An array of the proper type and length to return to the elements in
    * @return the subset populatd with random selections from allItem.
    * */

    public <O> List<O> getRandomSubset(List<O> allItems, int subSetLength) {
        if (allItems.size() <= subSetLength) {
            throw new IllegalArgumentException("Subset length cannot be greater than the size of the list. allItems=" +
                    allItems.size() + ", subSetLength=" + subSetLength);
        }
        List<O> subset = new ArrayList<>();

        int[] tIdx = new int[allItems.size()];
        for (int i = 0; i < subSetLength; i++) {
            tIdx[i] = i;
        }
        for (int i = subSetLength; i < allItems.size(); i++) {
            tIdx[i] = -1;
        }
        for (int i = 0; i < allItems.size(); i++) {
            int tRand = getRand(allItems.size() - 1);
            int tTmp = tIdx[i];
            tIdx[i] = tIdx[tRand];
            tIdx[tRand] = tTmp;
        }
        for (int i = 0; i < allItems.size(); i++) {
            if (tIdx[i] != -1) {
                subset.add(allItems.get(tIdx[i]));
            }
        }
        return subset;
    }

    public Object getRandomInArray(Object[] pObjects) {
        if (pObjects == null || pObjects.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        return pObjects[this.getRand(pObjects.length - 1)];
    }
}
