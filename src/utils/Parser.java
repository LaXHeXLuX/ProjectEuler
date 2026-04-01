package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static String[] parseStrings(String filename) {
        List<String> rows = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            if (line == null) {
                br.close();
                return new String[0];
            }

            while (line != null) {
                rows.add(line);
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Converter.listToArr(rows, String.class);
    }
    public static String[][] parseManyStrings(String filename) {
        return parseManyStrings(filename, " ");
    }
    public static String[][] parseManyStrings(String filename, String splitter) {
        List<String[]> rows = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            if (line == null) {
                br.close();
                return new String[0][];
            }

            while (line != null) {
                rows.add(line.split(splitter));
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Converter.listToArr(rows);
    }
    public static int[] parseInts(String filename) {
        List<Integer> rows = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            if (line == null) {
                br.close();
                return new int[0];
            }

            while (line != null) {
                rows.add(Integer.parseInt(line));
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Converter.listToArr(rows);
    }

    public static int[][] parseManyInts(String filename) {
        return parseManyInts(filename, " ");
    }
    public static int[][] parseManyInts(String filename, String splitter) {
        List<int[]> rows = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            if (line == null) {
                br.close();
                return new int[0][];
            }

            while (line != null) {
                String[] split = line.split(splitter);
                int[] arr = new int[split.length];
                for (int i = 0; i < split.length; i++) {
                    arr[i] = Integer.parseInt(split[i]);
                }
                rows.add(arr);
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Converter.listToArr(rows);
    }
}
