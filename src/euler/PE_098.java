package src.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PE_098 {
    private static Map<Integer, Map<String, List<String>>> anagrams;
    private static final Map<Integer, Map<String, List<Integer>>> squareAnagrams = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        String file = "src/euler/inputs/PE_098_words.txt";
        String[] words = parse(file);
        makeAnagrams(words);
        int limit = Collections.max(anagrams.keySet());
        limit = (int) Math.sqrt(Math.pow(10, limit));
        makeSquareAnagrams(limit);
        int result = largestSquareWithProperty();
        return (long) result * result;
    }

    private static int largestSquareWithProperty() {
        int biggest = -1;
        for (int i : anagrams.keySet()) {
            Map<String, List<String>> wordMap = anagrams.get(i);
            Map<String, List<Integer>> squareMap = squareAnagrams.get(i);
            if (squareMap == null) continue;
            for (String s1 : wordMap.keySet()) {
                List<String> words = wordMap.get(s1);
                for (String s2 : squareMap.keySet()) {
                    if (!replacementWorks(s1, s2)) continue;
                    List<Integer> squares = squareMap.get(s2);
                    for (int i1 = 0; i1 < words.size()-1; i1++) {
                        String word1 = words.get(i1);
                        for (int i2 = i1+1; i2 < words.size(); i2++) {
                            String word2 = words.get(i2);
                            int result = maxAnagramicSquare(word1, word2, squares);
                            if (result > biggest) {
                                biggest = result;
                            }
                        }
                    }
                }
            }
            if (biggest > 0) return biggest;
        }
        return -1;
    }

    private static int maxAnagramicSquare(String word1, String word2, List<Integer> squares) {
        int biggest = -1;
        for (int square1 : squares) {
            for (int square2 : squares) {
                if (anagramic(word1, word2, square1*square1, square2*square2)) {
                    biggest = Math.max(biggest, Math.max(square1, square2));
                }
            }
        }
        return biggest;
    }

    private static boolean anagramic(String word1, String word2, int square1, int square2) {
        char[] digitMap = new char[10];
        int index = word1.length()-1;
        while (square1 > 0) {
            int digit = square1 % 10;
            char c = word1.charAt(index);
            if (digitMap[digit] != 0 && digitMap[digit] != c) return false;
            digitMap[digit] = c;
            square1 /= 10;
            index--;
        }

        StringBuilder word = new StringBuilder();
        while (square2 > 0) {
            word.insert(0, digitMap[square2 % 10]);
            square2 /= 10;
        }
        return word.toString().equals(word2);
    }

    private static boolean replacementWorks(String s1, String s2) {
        char last1 = s1.charAt(0);
        char last2 = s2.charAt(0);
        for (int i = 1; i < s1.length(); i++) {
            if (s1.charAt(i) == last1 && s2.charAt(i) != last2) return false;
            if (s1.charAt(i) != last1 && s2.charAt(i) == last2) return false;
            last1 = s1.charAt(i);
            last2 = s2.charAt(i);
        }
        return true;
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
        Map<Integer, Map<String, List<String>>> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            map.putIfAbsent(chars.length, new HashMap<>());
            map.get(chars.length).putIfAbsent(sortedWord, new ArrayList<>());
            map.get(chars.length).get(sortedWord).add(word);
        }
        for (Map<String, List<String>> value : map.values()) {
            value.entrySet().removeIf(entry -> entry.getValue().size() < 2);
        }
        map.entrySet().removeIf(entry -> entry.getValue().isEmpty());

        anagrams = map.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getKey(), e1.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, _) -> a,
                        LinkedHashMap::new
                ));
    }

    private static void makeSquareAnagrams(int limit) {
        for (int i = 1; i < limit; i++) {
            int n = i*i;
            char[] digits = new char[(int) Math.log10(n) + 1];
            int index = 0;
            while (n > 0) {
                digits[index] = (char) ('0' + n%10);
                n /= 10;
                index++;
            }
            Arrays.sort(digits);
            String s = new String(digits);
            squareAnagrams.putIfAbsent(s.length(), new HashMap<>());
            squareAnagrams.get(s.length()).putIfAbsent(s, new ArrayList<>());
            squareAnagrams.get(s.length()).get(s).add(i);
        }

        for (Map<String, List<Integer>> value : squareAnagrams.values()) {
            value.entrySet().removeIf(entry -> entry.getValue().size() < 2);
        }
        squareAnagrams.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
}
