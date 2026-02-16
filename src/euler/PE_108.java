package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_108 {
    private static int[] primes;
    private static double[] primeLogs;

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000;
        return smallestNumberWithSolutionCountAbove(limit);
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

    private static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    private static void makePrimesAndLogs(int limit) {
        int primeLimit = (int) Math.pow(limit, 0.75);
        primes = Primes.primes(primeLimit);
        primeLogs = new double[primes.length];
        for (int i = 0; i < primes.length; i++) {
            primeLogs[i] = log2(primes[i]);
        }
    }

    public static long smallestNumberWithSolutionCountAbove(int n) {
        return numberFromPf(smallestPrimeFactorsWithSolutionCountAbove(n));
    }

    private static List<Integer> smallestPrimeFactorsWithSolutionCountAbove(int n) {
        makePrimesAndLogs(n);
        List<Integer> bestPf = logPrediction(n);
        double bestLog = logValue(bestPf);
        int limit = (int) log2(bestLog) + 1;
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

    private static int solutionCount(List<Integer> primeFactors) {
        if (primeFactors.isEmpty()) return 1;
        if (primeFactors.size() == 1 && primeFactors.getFirst() == 1) return 2;
        int prod = 1;
        for (int pf : primeFactors) {
            prod *= 2*pf + 1;
        }
        return (prod+1)/2;
    }
}
