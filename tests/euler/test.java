package euler;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class test {
    static Map<Class<?>, Long> tests = new LinkedHashMap<>() {{
        put(PE_001.class, 233168L);
        put(PE_002.class, 4613732L);
        put(PE_003.class, 6857L);
        put(PE_004.class, 906609L);
        put(PE_005.class, 232792560L);
        put(PE_006.class, 25164150L);
        put(PE_007.class, 104743L);
        put(PE_008.class, 23514624000L);
        put(PE_009.class, 31875000L);
        put(PE_010.class, 142913828922L);
        put(PE_011.class, 70600674L);
        put(PE_012.class, 76576500L);
        put(PE_013.class, 5537376230L);
        put(PE_014.class, 837799L);
        put(PE_015.class, 137846528820L);
        put(PE_016.class, 1366L);
        put(PE_017.class, 21124L);
        put(PE_018.class, 1074L);
        put(PE_019.class, 171L);
        put(PE_020.class, 648L);
        put(PE_021.class, 31626L);
        put(PE_022.class, 871198282L);
        put(PE_023.class, 4179871L);
        put(PE_024.class, 2783915460L);
        put(PE_025.class, 4782L);
        put(PE_026.class, 983L);
        put(PE_027.class, -59231L);
        put(PE_028.class, 669171001L);
        put(PE_029.class, 9183L);
        put(PE_030.class, 443839L);
        put(PE_031.class, 73682L);
        put(PE_032.class, 45228L);
        put(PE_033.class, 100L);
        put(PE_034.class, 40730L);
        put(PE_035.class, 55L);
        put(PE_036.class, 872187L);
        put(PE_037.class, 748317L);
        put(PE_038.class, 932718654L);
        put(PE_039.class, 840L);
        put(PE_040.class, 210L);
        put(PE_041.class, 7652413L);
        put(PE_042.class, 162L);
        put(PE_043.class, 16695334890L);
        put(PE_044.class, 5482660L);
        put(PE_045.class, 1533776805L);
        put(PE_046.class, 5777L);
        put(PE_047.class, 134043L);
        put(PE_048.class, 9110846700L);
        put(PE_049.class, 296962999629L);
        put(PE_050.class, 997651L);
        put(PE_051.class, 121313L);
        put(PE_052.class, 142857L);
        put(PE_053.class, 4075L);
        put(PE_054.class, 376L);
        put(PE_055.class, 249L);
        put(PE_056.class, 972L);
        put(PE_057.class, 153L);
        put(PE_058.class, 26241L);
        put(PE_059.class, 129448L);
        put(PE_060.class, 26033L);
        put(PE_061.class, 28684L);
        put(PE_062.class, 127035954683L);
        put(PE_063.class, 49L);
        put(PE_064.class, 1322L);
        put(PE_065.class, 272L);
        put(PE_066.class, 661L);
        put(PE_067.class, 7273L);
        put(PE_068.class, 6531031914842725L);
        put(PE_069.class, 510510L);
        put(PE_070.class, 8319823L);
        put(PE_071.class, 428570L);
        put(PE_072.class, 303963552391L);
        put(PE_073.class, 7295372L);
        put(PE_074.class, 402L);
        put(PE_075.class, 161667L);
        put(PE_076.class, 190569291L);
        put(PE_077.class, 71L);
        put(PE_078.class, 55374L);
        put(PE_079.class, 73162890L);
        put(PE_080.class, 40886L);
        put(PE_081.class, 427337L);
        put(PE_082.class, 260324L);
        put(PE_083.class, 425185L);
        put(PE_084.class, 101524L);
        put(PE_085.class, 2772L);
        put(PE_086.class, 1818L);
        put(PE_087.class, 1097343L);
        put(PE_088.class, 7587457L);
        put(PE_089.class, 743L);
        put(PE_090.class, 1217L);
        put(PE_091.class, 14234L);
        put(PE_092.class, 8581146L);
        put(PE_093.class, 1258L);
        put(PE_094.class, 518408346L);
        put(PE_095.class, 14316L);
        put(PE_096.class, 24702L);
        put(PE_097.class, 8739992577L);
        put(PE_098.class, 18769L);
        put(PE_099.class, 709L);
        put(PE_100.class, 756872327473L);
        put(PE_101.class, 37076114526L);
        put(PE_102.class, 228L);
        put(PE_103.class, 20313839404245L);
        put(PE_104.class, 329468L);
        put(PE_105.class, 73702L);
        put(PE_106.class, 21384L);
        //put(PE_107.class, ???L);
        put(PE_108.class, 180180L);
        put(PE_109.class, 38182L);
        put(PE_110.class, 9350130049860600L);
        put(PE_111.class, 612407567715L);
        put(PE_112.class, 1587000L);
    }};

    public static void main(String[] args) {
        tests();
    }
    private static void assertEquals(long l1, long l2) {
        if (l1 != l2) throw new AssertionError(l1 + " != " + l2);
    }
    public static void tests() {
        double allStart = System.currentTimeMillis();
        for (Class<?> cls : tests.keySet()) {
            System.out.print(cls.getName() + "\r");
            double start = System.currentTimeMillis();
            long result;
            try {
                result = (long) cls.getMethod("PE").invoke(null);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            assertEquals(result, tests.get(cls));
            double end = System.currentTimeMillis();
            double time = end - start;
            if (time > 500) System.out.println(cls.getName() + ": " + (end - start) + " ms");
        }
        double allEnd = System.currentTimeMillis();
        System.out.println("Total time: " + (allEnd - allStart) + " ms");
    }
}