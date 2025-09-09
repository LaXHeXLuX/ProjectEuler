import util.Primes;

public class PE_072 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        return elementCountForAllFractions(limit);
    }

    private static long elementCountForAllFractions(int limitD) {
        long answer = 0;
        for (int i = 2; i <= limitD; i++) {
            answer += Primes.eulersTotient(i);
        }
        return answer;
    }
}
