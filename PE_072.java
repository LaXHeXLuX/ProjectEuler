public class PE_072 {
    public static void main(String[] args) {
        int limitD = 1_000_000;
        System.out.println(elementCountForAllFractions(limitD));
    }

    private static long elementCountForAllFractions(int limitD) {
        long answer = 0;
        for (int i = 2; i < limitD; i++) {
            answer += Primes.eulersTotient(i)+1;
        }
        answer += Primes.eulersTotient(limitD)+1 - limitD;
        return answer;
    }
}
