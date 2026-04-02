package euler;

import utils.Diophantine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PE_098 {
    private static Map<Integer, Map<String, List<String>>> anagrams;
    private static int maxSize = 0;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String file = "src/euler/inputs/PE_098_words.txt";
        String[] words = parse(file);
        makeAnagrams(words);
        return String.valueOf(largestSquareWithProperty());
    }

    private static String[] parse(String filename) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        line = line.substring(1, line.length()-1);
        return line.split("\",\"");
    }
    
    private static void makeAnagrams(String[] words) {
        anagrams = new HashMap<>();
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

        Map<Integer, Map<String, List<String>>> temp = anagrams;
        anagrams = new TreeMap<>(Comparator.reverseOrder());
        anagrams.putAll(temp);
    }

    private static boolean wordsMapSquare(char[] w1, char[] w2, long square) {
        int[] map = new int[26];
        boolean[] used = new boolean[10];
        Arrays.fill(map, -1);
        for (int i = w1.length-1; i >= 0; i--) {
            int index = w1[i] - 'A';
            int digit = (int) (square % 10);
            if (map[index] != -1 && map[index] != digit) return false;
            if (map[index] == -1 && used[digit]) return false;
            used[digit] = true;
            map[index] = digit;
            square /= 10;
        }
        long square2 = 0;
        if (map[w2[0] - 'A'] == 0) return false;
        for (char c : w2) {
            square2 = 10*square2 + map[c - 'A'];
        }
        return Diophantine.root(square2) >= 0;
    }

    private static long largestSquareWithProperty() {
        for (Integer i : anagrams.keySet()) {
            Map<String, List<String>> sortedMap = anagrams.get(i);
            int nLow = (int) Math.sqrt(Diophantine.pow10[i-1] - 1) + 1;
            int nHigh = (int) Math.sqrt(Diophantine.pow10[i] - 1);
            for (int n = nHigh; n >= nLow; n--) {
                long square = (long) n * n;
                for (String sorted : sortedMap.keySet()) {
                    List<String> words = sortedMap.get(sorted);
                    for (int w1i = 0; w1i < words.size()-1; w1i++) {
                        char[] w1 = words.get(w1i).toCharArray();
                        for (int w2i = w1i+1; w2i < words.size(); w2i++) {
                            char[] w2 = words.get(w2i).toCharArray();
                            if (wordsMapSquare(w1, w2, square)) {
                                return square;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
