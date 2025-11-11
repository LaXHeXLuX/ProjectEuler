package euler;

import utils.Converter;
import utils.Divisors;
import utils.Primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE_108 {
    private static int[] primes;
    private static double[] primeLogs;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int limit = 4_000_000;
        makePrimesAndLogs(limit);
        List<Integer> pf = smallestPrimeFactorsWithSolutionCountAbove(limit);
        System.out.println();
        System.out.println(logValue(pf));
        return numberFromPf(pf);
//        for (int i = 1; i < 102; i+=1) {
//            List<Integer> pf = smallestPrimeFactorsWithSolutionCountAbove(i);
//            long n = numberFromPf(pf);
//            System.out.println(i + ": " + n + " - " + pf + " - " + solutionCount(pf));
//        }
    }

    private static List<Integer> logPrediction(long n) {
        n = 2*n + 1;
        List<Integer> result = new ArrayList<>();
        result.add(3);
        result.add(2);
        n /= 35;
        while (n > 0) {
            result.add(1);
            n /= 3;
        }
        return result;
    }

    private static void makePrimesAndLogs(int limit) {
        primes = Converter.booleanArrToIntArr(Primes.sieveOfPrimes((int) Math.pow(limit, 0.75)));
        primeLogs = new double[primes.length];
        for (int i = 0; i < primes.length; i++) {
            primeLogs[i] = Math.log(primes[i]);
        }
    }

    private static List<Integer> smallestPrimeFactorsWithDivisorsCountAbove(int n) {
        if (n == 1) return new ArrayList<>();
        if (n == 2) return Collections.singletonList(1);
        double bestLog = Double.MAX_VALUE;
        List<Integer> bestPf = null;
        for (int i = 1; i < n; i++) {
            List<Integer> input = new ArrayList<>();
            input.add(i);
            List<Integer> pf = smallestPrimeFactorsWithDivisorsCountAbove(n, input, bestLog);
            double log = logValue(pf);
            if (log < bestLog) {
                bestLog = log;
                bestPf = new ArrayList<>(pf);
            }
        }
        return bestPf;
    }

    private static List<Integer> smallestPrimeFactorsWithDivisorsCountAbove(int n, List<Integer> current, double bestLog) {
        if (divisorCount(current) >= n) {
            return current;
        }
        List<Integer> bestPf = null;
        for (int i = 1; i <= current.getLast(); i++) {
            current.add(i);
            List<Integer> pf = smallestPrimeFactorsWithDivisorsCountAbove(n, current, bestLog);
            double log = logValue(pf);
            if (log < bestLog) {
                bestLog = log;
                bestPf = new ArrayList<>(pf);
            }
            current.removeLast();
        }
        return bestPf;
    }

    private static List<Integer> smallestPrimeFactorsWithSolutionCountAbove(int n) {
        List<Integer> bestPf = logPrediction(n);
        double bestLog = logValue(bestPf);
        int limit = (int) (Math.log(n) / Math.log(2)) + 5; // help here with justifying this limit
        System.out.println("predicting: " + bestLog + " - " + bestPf + ", first limit: " + limit);
        for (int i = 1; i <= limit; i++) {
            List<Integer> input = new ArrayList<>();
            input.add(i);
            List<Integer> pf = smallestPrimeFactorsWithSolutionCountAbove(n, input, bestLog);
            if (pf == null) continue;
            double log = logValue(pf);
            if (log < bestLog) {
                bestLog = log;
                bestPf = new ArrayList<>(pf);
            }
        }
        return bestPf;
    }

    private static List<Integer> smallestPrimeFactorsWithSolutionCountAbove(int n, List<Integer> current, double bestLog) {
        if (solutionCount(current) > n) {
            return new ArrayList<>(current);
        }
        List<Integer> bestPf = null;
        for (int i = 1; i <= current.getLast(); i++) {
            current.add(i);
            List<Integer> pf = smallestPrimeFactorsWithSolutionCountAbove(n, current, bestLog);
            current.removeLast();
            if (pf == null) continue;
            double log = logValue(pf);
            if (log < bestLog) {
                bestLog = log;
                bestPf = pf;
            }
        }
        return bestPf;
    }

    private static long divisorCount(List<Integer> number) {
        long prod = 1;
        for (int i : number) {
            prod *= i+1;
        }
        return prod;
    }

    private static double logValue(List<Integer> number) {
        double sum = 0;
        for (int i = 0; i < number.size(); i++) {
            if (number.get(i) == 0) continue;
            sum += number.get(i) * primeLogs[i];
        }
        return sum;
    }

    private static long numberFromPf(List<Integer> pf) {
        long number = 1;
        for (int i = 0; i < pf.size(); i++) {
            number *= (long) Math.pow(primes[i], pf.get(i));
        }
        return number;
    }

    private static int smallestWithNDivisors(int n) {
        int x = 1;
        long[] divisors = Divisors.divisors(x);
        while (divisors.length < n) {
            x++;
            divisors = Divisors.divisors(x);
        }
        return x;
    }

    private static int solutionCount(List<Integer> primeFactors) {
        if (primeFactors.isEmpty()) return 1;
        if (primeFactors.size() == 1 && primeFactors.getFirst() == 1) return 2;
        int prod = 1;
        for (int pf : primeFactors) {
            prod *= 2*pf + 1;
        }
        return (prod+1)/2;
    }

    public static List<List<Integer>> findPrimeFactors(int n) {
        List<List<Integer>> primeFactors = new ArrayList<>();

        int count = 0;
        while (n % 2 == 0) {
            count++;
            n /= 2;
        }
        if (count > 0) {
            primeFactors.add(List.of(2, count));
            count = 0;
        }

        while (n % 3 == 0) {
            count++;
            n /= 3;
        }
        if (count > 0) {
            primeFactors.add(List.of(3, count));
            count = 0;
        }

        int limit = (int) Math.sqrt(n);
        for (int i = 5; i <= limit; i += 6) {
            while (n % i == 0) {
                count++;
                n /= i;
                limit = (int) Math.sqrt(n);
            }
            if (count > 0) {
                primeFactors.add(List.of(i, count));
                count = 0;
            }
            while (n % (i+2) == 0) {
                count++;
                n /= i+2;
                limit = (int) Math.sqrt(n);
            }
            if (count > 0) {
                primeFactors.add(List.of(i+2, count));
                count = 0;
            }
        }

        if (n > 1) primeFactors.add(List.of(n, 1));
        return primeFactors;
    }
}
