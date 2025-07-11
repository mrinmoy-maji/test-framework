package com.example.util;


/**
 * Single-abstract-method (SAM) interface to be implemented for test assertion chunks that you want to re-run few times
 * if it fails because of timing issues. If it fails, it will retry the assertion up to a maximum number of times
 * If you use this frequently, something is wrong with the interaction you are testing
 *
 * @param <T> the type returned from the retried operation
 * */
@FunctionalInterface
public interface RetryableWithReturn<T> {

    /**
     *
     * @throws throw an AssertionError or a RuntimeException if you want to be called again
     * @return the result of the retried operation
     */

    T thingsToRetry() throws Exception;
}
