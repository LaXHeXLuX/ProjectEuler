public class test {
    public static void main(String[] args) {
        int limit = 10;
        for (int i = 0; i < limit; i++) {
            System.out.println(i);
            if (i == 50) limit = i;
        }
    }
}
