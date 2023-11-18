import UsefulFunctions.Combinations;
import UsefulFunctions.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_062 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int n = 5;
        System.out.println(Arrays.toString(firstCubeWithNPermutations(n)));

        long end = System.currentTimeMillis();
        System.out.println("TIME: " + (end-start));
    }

    private static long[] permutationCubes(long cube, List<Long> cubes) {
        List<Long> permutationCubes = new ArrayList<>();

        for (long otherCube : cubes) {
            int[] digitsCube = Converter.digitArray(cube);
            int[] digitsOtherCube = Converter.digitArray(otherCube);
            if (Combinations.isPermutationOf(digitsCube, digitsOtherCube)) permutationCubes.add(otherCube);
        }

        return Converter.listToArrLong(permutationCubes);
    }

    private static long[] firstCubeWithNPermutations(int n) {
        List<Long> cubes = new ArrayList<>();
        long i = 1;
        long cube = i*i*i;

        while (cube > 0) {
            cubes.add(cube);
            long[] permutations = permutationCubes(cube, cubes);
            if (permutations.length == n) return permutations;
            i++;
            cube = i*i*i;
        }

        return new long[0];
    }
}
