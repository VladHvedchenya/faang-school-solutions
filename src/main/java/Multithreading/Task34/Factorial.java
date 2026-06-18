package Multithreading.Task34;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class Factorial {
    private static final int MAX_INT_FACTORIAL = 12;
    private static final int MAX_LONG_FACTORIAL = 19;

    public List<CompletableFuture<BigInteger>> calculateFactorials(List<? extends Number> values, ExecutorService executor) {
        List<CompletableFuture<BigInteger>> futures = new ArrayList<>();

        for (var number : values) {
            int n;
            if (number instanceof BigInteger bigNum) {
                n = bigNum.intValueExact();
            } else {
                n = Math.toIntExact(number.longValue());
            }

            if (n < 0) {
                throw new IllegalArgumentException("Факториал отрицательного числа не существует: " + n);
            }


            CompletableFuture<BigInteger> future = CompletableFuture.supplyAsync(() -> {
                if (n <= MAX_INT_FACTORIAL)
                    return BigInteger.valueOf(factorialInt(n));
                else if (n <= MAX_LONG_FACTORIAL)
                    return BigInteger.valueOf(factorialLong(n));
                else
                    return factorialBigInteger(n);
            }, executor);
            futures.add(future);
        }

        return futures;
    }

    private static int factorialInt(int n) {
        if (n < 0 || n > MAX_INT_FACTORIAL) {
            throw new IllegalArgumentException("factorialInt поддерживает значения от 0 до 12. Передано: " + n);
        }

        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static long factorialLong(int n) {
        if (n <= MAX_INT_FACTORIAL || n > MAX_LONG_FACTORIAL) {
            throw new IllegalArgumentException("factorialLong поддерживает значения от 13 до 19. Передано: " + n);
        }

        long value = factorialInt(MAX_INT_FACTORIAL);

        for (int i = MAX_INT_FACTORIAL + 1; i <= n; i++) {
            value *= i;
        }

        return value;
    }

    private static BigInteger factorialBigInteger(int n) {
        if (n <= MAX_LONG_FACTORIAL) {
            throw new IllegalArgumentException("factorialBig поддерживает значения от 20. Передано: " + n);
        }

        BigInteger value = BigInteger.valueOf(factorialLong(MAX_LONG_FACTORIAL));

        for (int i = MAX_LONG_FACTORIAL + 1; i <= n; i++) {
            value = value.multiply(BigInteger.valueOf(i));
        }

        return value;
    }
}