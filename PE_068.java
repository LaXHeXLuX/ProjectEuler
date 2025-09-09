import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class PE_068 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int size = 5;
        int digitRestriction = 15;
        int[][] nGons = magicNGonRings(size);
        return maxConcatRing(nGons, digitRestriction);
    }

    private static int[][] magicNGonRings(int n) {
        int[] nGonRing= new int[n*2];
        return findNGonRings(nGonRing);
    }

    private static long maxConcatRing(int[][] nGons, int digitRestriction) {
        long limit = (long) Math.pow(10, digitRestriction+1);
        long biggest = 0;
        for (int[] nGon : nGons) {
            long asLong = convertToLong(nGon);
            if (asLong > biggest && (digitRestriction == -1 || asLong < limit)) biggest = asLong;
        }
        return biggest;
    }

    private static long convertToLong(int[] nGon) {
        int[][] ring = convertToRing(nGon);
        StringBuilder n = new StringBuilder();
        for (int[] ints : ring) {
            for (int i : ints) {
                n.append(i);
            }
        }
        return Long.parseLong(String.valueOf(n));
    }

    private static int[][] convertToRing(int[] nGon) {
        if (nGon == null) return null;
        int[][] ring = new int[nGon.length/2][];

        for (int i = 0; i < ring.length; i++) {
            ring[i] = new int[3];
            ring[i][0] = nGon[i*2];
            ring[i][1] = nGon[i*2+1];
        }

        for (int i = 0; i < ring.length; i++) {
            int nextI = (i+1) % ring.length;
            ring[i][2] = ring[nextI][1];
        }

        return ring;
    }

    private static int[][] findNGonRings(int[] currentRing) {
        List<int[]> finalRings = new ArrayList<>();
        boolean[] taken = new boolean[currentRing.length+1];
        int[] sums = minAndMaxSumOfRowInNGonRing(currentRing.length/2);
        for (int i = currentRing.length/2+1; i > 0; i--) {
            taken[i] = true;
            currentRing[0] = i;
            for (int j = currentRing.length; j > 0; j--) {
                if (taken[j]) continue;
                taken[j] = true;
                currentRing[1] = j;
                for (int k = currentRing.length; k > 0; k--) {
                    if (taken[k]) continue;
                    int sum = i+j+k;
                    if (sum < sums[0] || sum > sums[1]) continue;
                    taken[k] = true;
                    currentRing[3] = k;

                    int[][] rings = findNGonRings(currentRing, 2, sum, taken);
                    for (int[] ring : rings) {
                        if (ring == null) continue;
                        finalRings.add(ring);
                    }

                    currentRing[3] = 0;
                    taken[k] = false;
                }
                currentRing[1] = 0;
                taken[j] = false;
            }
            currentRing[0] = 0;
            taken[i] = false;
        }

        return Converter.listToArr(finalRings);
    }

    private static int[][] findNGonRings(int[] currentRing, int index, int sum, boolean[] taken) {
        if (index == currentRing.length-2) {
            int next = sum - currentRing[currentRing.length-1] - currentRing[1];
            if (next <= currentRing[0] || next >= taken.length || taken[next]) return new int[0][];
            currentRing[index] = next;
            int[] newCurrentRing = new int[currentRing.length];
            System.arraycopy(currentRing, 0, newCurrentRing, 0, currentRing.length);
            return new int[][] {newCurrentRing};
        }

        List<int[]> finalRings = new ArrayList<>();

        for (int i = currentRing.length; i > currentRing[0]; i--) {
            if (taken[i]) continue;
            currentRing[index] = i;
            int next = sum - currentRing[index] - currentRing[index+1];
            if (next == currentRing[index] || next <= 0 || next >= taken.length || taken[next]) continue;
            currentRing[index+3] = next;
            taken[i] = true;
            taken[next] = true;

            int[][] rings = findNGonRings(currentRing, index+2, sum, taken);
            for (int[] ring : rings) {
                if (ring == null) continue;
                finalRings.add(ring);
            }

            taken[i] = false;
            taken[next] = false;
            currentRing[index] = 0;
            currentRing[index+3] = 0;
        }
        if (finalRings.isEmpty()) return new int[0][];
        return Converter.listToArr(finalRings);
    }

    private static int[] minAndMaxSumOfRowInNGonRing(int n) {
        int min = (int) (2.5*n + 1.5);
        int max = min + n;
        return new int[] {min, max};
    }
}
