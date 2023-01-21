package edu.school21.numbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class NumberWorkerTest {
    @ParameterizedTest(name = "{index} => isPrime({0})")
    @ValueSource(ints = {163, 331, 569, 31, 991})
    public void isPrimeForPrimes(int num) {
        assertTrue(new NumberWorker().isPrime(num));
    }

    @ParameterizedTest(name = "{index} => isNotPrimes({0})")
    @ValueSource(ints = {87, 598, 688, 720, 999})
    public void isPrimeForNotPrimes(int num) {
        assertFalse(new NumberWorker().isPrime(num));
    }

    @ParameterizedTest(name = "{index} => isIncorrect({0})")
    @ValueSource(ints = {Integer.MAX_VALUE + 1, -24214, 0, 1, 2, -55555555, Integer.MIN_VALUE})
    public void isPrimeForIncorrectNumbers(int num) {
        assertThrows(NumberWorker.IllegalNumberException.class, () -> {new NumberWorker().isPrime(num);});
    }

    @ParameterizedTest(name = "{index} => isIncorrect({0})")
    @CsvFileSource(resources = "/data.csv")
    public void digitsumCheck(Integer input, Integer expected) {
        assertEquals(new NumberWorker().digitsSum(input), expected);
    }

}