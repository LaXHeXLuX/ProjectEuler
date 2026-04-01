package euler;

import utils.Diophantine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PE_098 {
    private static final Map<Integer, Map<String, List<String>>> anagrams = new HashMap<>();
    private static int maxSize = 0;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        String file = "src/euler/inputs/PE_098_words.txt";
        String[] words = parse(file);
        System.out.println(words.length);
        double s = System.currentTimeMillis();
        makeAnagrams(words);
        double e = System.currentTimeMillis();
        System.out.println("anagrams: " + (e-s) + " ms");
        long limit = (long) Math.sqrt(Diophantine.pow10[maxSize]);
        return String.valueOf(largestSquareWithProperty(limit));
    }

    private static String[] parse(String filename) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] words = line.split(",");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("\"", "");
        }

        return words;
    }
    
    private static void makeAnagrams(String[] words) {
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            anagrams.putIfAbsent(chars.length, new HashMap<>());
            anagrams.get(chars.length).putIfAbsent(sortedWord, new ArrayList<>());
            anagrams.get(chars.length).get(sortedWord).add(word);
        }
        for (Map<String, List<String>> value : anagrams.values()) {
            value.entrySet().removeIf(entry -> entry.getValue().size() < 2);
        }
        anagrams.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        for (Integer i : anagrams.keySet()) {
            if (i > maxSize) maxSize = i;
        }
    }

    private static int[] digits(long n) {
        int[] digits = new int[10];
        while (n > 0) {
            digits[(int) (n % 10)]++;
            n /= 10;
        }
        return digits;
    }

    private static int[] wordDigits(String word) {
        char[] chars = word.toCharArray();
        int[] digits = new int[26];
        for (char c : chars) {
            digits[c - 'A']++;
        }
        return digits;
    }

    private static int[] distinctCounts(int[] digits) {
        int[] distinctCounts = new int[10];
        for (int digit : digits) {
            if (digit == 0) continue;
            distinctCounts[digit]++;
        }
        return distinctCounts;
    }

    private static long largestSquareWithProperty(long limit) {
        for (long n = limit; n > 0; n--) {
            long square = n*n;
            int[] digits = digits(square);
            int[] distinctCounts = distinctCounts(digits);
            Map<String, List<String>> anagramMap = anagrams.get((int) Math.log10(square) + 1);
            if (anagramMap == null) {
                n = Diophantine.pow10[(int) Math.log10(n)];
                continue;
            }
            for (String sorted : anagramMap.keySet()) {
                List<String> words = anagramMap.get(sorted);
                int[] wordDistinctCounts = distinctCounts(wordDigits(sorted));
                boolean works = true;
                for (int i = 0; i < wordDistinctCounts.length; i++) {
                    if (wordDistinctCounts[i] != distinctCounts[i]) {
                        works = false;
                        break;
                    }
                }
                if (!works) continue;
                for (int i = 0; i < words.size(); i++) {
                    String w1 = words.get(i);
                    int[] map = new int[26];
                    Arrays.fill(map, -1);
                    long temp = square;
                    works = true;
                    for (int j = w1.length()-1; j >= 0; j--) {
                        int index = w1.charAt(j) - 'A';
                        int d = (int) (temp % 10);
                        if (map[index] != -1 && map[index] != d) {
                            works = false;
                            break;
                        }
                        map[index] = d;
                        temp /= 10;
                    }
                    if (!works) continue;
                    for (int j = 0; j < words.size(); j++) {
                        if (i == j) continue;
                        String w2 = words.get(j);
                        if (map[w2.charAt(0) - 'A'] == 0) continue;
                        long newSquare = 0;
                        for (int k = 0; k < w2.length(); k++) {
                            newSquare = 10*newSquare + map[w2.charAt(k) - 'A'];
                        }
                        long root = Diophantine.root(newSquare);
                        if (root >= 0) return square;
                    }
                }
            }
        }
        return -1;
    }
}
