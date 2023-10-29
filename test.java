import UsefulFunctions.ArrayFunctions;

public class test {
    public static void main(String[] args) {
        ArrayFunctions af = new ArrayFunctions();
        /*
        int[] arr = {1, 2, 3, 3, 9, 126, 127};
        int el = 126;
        System.out.println(af.sortedContains(el, arr));

         */
        long start = System.currentTimeMillis();
        int[] bigArr = new int[Integer.MAX_VALUE/1000];
        System.out.println("L: " + bigArr.length);
        for (int i = 0; i < bigArr.length; i++) {
            bigArr[i] = (int) (Math.random()*(Integer.MAX_VALUE/100));
        }
        long end = System.currentTimeMillis();
        System.out.println("Massiivi loomine: " + (end-start) + " ms");

        start = System.currentTimeMillis();
        bigArr = af.mergeSort(bigArr);
        end = System.currentTimeMillis();
        System.out.println("Massiivi sorteerimine: " + (end-start) + " ms");

        int[] searches = new int[1000];
        for (int i = 0; i < searches.length; i++) {
            searches[i] = (int) (Math.random()*(Integer.MAX_VALUE/1_000));
        }

        start = System.currentTimeMillis();
        for (int search : searches) {
            System.out.print(af.contains(search, bigArr) + " ");
        }
        System.out.println();
        end = System.currentTimeMillis();
        System.out.println("Leidmine: " + (end-start) + " ms");

        start = System.currentTimeMillis();
        for (int search : searches) {
            System.out.print(af.sortedContains(search, bigArr) + " ");
        }
        System.out.println();
        end = System.currentTimeMillis();
        System.out.println("Sorted leidmine: " + (end-start) + " ms");

    }
}
