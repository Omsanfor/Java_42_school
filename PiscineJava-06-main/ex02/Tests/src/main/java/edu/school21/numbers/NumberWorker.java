package edu.school21.numbers;

import org.codehaus.plexus.component.annotations.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class NumberWorker {

    public boolean isPrime(int number) {
        int temp;
        if (number <= 2) {
            throw new IllegalNumberException();
        } else {
            for (int i = 2; i * i <= number; i++){
                if (number % i == 0)
                    return false;
            }
            return true;
        }
    }

    public int digitsSum(int number) {
        int result = 0;

        if (number < 0)
            number = -number;

        result += number % 10;
        while (number > 10){
            number = number / 10;
            result += number % 10;
        }
        return (result);
    }

    class IllegalNumberException extends RuntimeException {
    }

}
