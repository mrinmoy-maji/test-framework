package com.example.id;

import java.util.function.LongSupplier;

/*
* Sequence id generator. it generates long numbers in a sequence. When counter reaches max long it will
* be set back to zero
* */

public class LongIdGenerator implements LongSupplier {
    protected long value;
    protected long initialValue;
    protected long maxValue;
    protected boolean cycle;

    /*
    * Creates a new default cycled id generator. Starts from 1 and counts upto max long value.
    * */

    public LongIdGenerator() { this(1, Long.MAX_VALUE, true); }

    /*
    * Creates a new cycle id generator with specified initial value
    * */

    public LongIdGenerator(long initialValue) { this(initialValue, Long.MAX_VALUE, true); }

    /*
     * Creates a new id generator with specified range and cycling flag
     * */

    public LongIdGenerator(long initialValue, long maxValue, boolean cycle) {
        if (initialValue < 0 || maxValue <= 0 || initialValue >= maxValue) {
            throw new IllegalArgumentException("Initial value :" + initialValue + " must be non-negative and less " +
                    "than max value, and max value :" + maxValue + " must be positive.");

        }
        this.value = initialValue;
        this.initialValue = initialValue;
        this.maxValue = maxValue;
        this.cycle = cycle;
    }

    /*
    * Returns the next value from the sequence
    * */

    public long next() {
        long id = value;

        value++;
        if (value >= maxValue || value < 0) {
            if (cycle) {
                value = initialValue;
            } else {
                throw new IllegalStateException("Reached max value: " + maxValue + ". Cannot generate more ids.");
            }
        }
        return id;
    }

    @Override
    public long getAsLong() { return next(); }

    /*
    * Get the last generated value
    * @return the last generated value
    * */
    public long get() { return value; }
}
