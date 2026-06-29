package euler;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class test {
    private static final Map<Class<?>, String> tests = new LinkedHashMap<>() {{
        put(PE_0001.class, "233168");
        put(PE_0002.class, "4613732");
        put(PE_0003.class, "6857");
        put(PE_0004.class, "906609");
        put(PE_0005.class, "232792560");
        put(PE_0006.class, "25164150");
        put(PE_0007.class, "104743");
        put(PE_0008.class, "23514624000");
        put(PE_0009.class, "31875000");
        put(PE_0010.class, "142913828922");
        put(PE_0011.class, "70600674");
        put(PE_0012.class, "76576500");
        put(PE_0013.class, "5537376230");
        put(PE_0014.class, "837799");
        put(PE_0015.class, "137846528820");
        put(PE_0016.class, "1366");
        put(PE_0017.class, "21124");
        put(PE_0018.class, "1074");
        put(PE_0019.class, "171");
        put(PE_0020.class, "648");
        put(PE_0021.class, "31626");
        put(PE_0022.class, "871198282");
        put(PE_0023.class, "4179871");
        put(PE_0024.class, "2783915460");
        put(PE_0025.class, "4782");
        put(PE_0026.class, "983");
        put(PE_0027.class, "-59231");
        put(PE_0028.class, "669171001");
        put(PE_0029.class, "9183");
        put(PE_0030.class, "443839");
        put(PE_0031.class, "73682");
        put(PE_0032.class, "45228");
        put(PE_0033.class, "100");
        put(PE_0034.class, "40730");
        put(PE_0035.class, "55");
        put(PE_0036.class, "872187");
        put(PE_0037.class, "748317");
        put(PE_0038.class, "932718654");
        put(PE_0039.class, "840");
        put(PE_0040.class, "210");
        put(PE_0041.class, "7652413");
        put(PE_0042.class, "162");
        put(PE_0043.class, "16695334890");
        put(PE_0044.class, "5482660");
        put(PE_0045.class, "1533776805");
        put(PE_0046.class, "5777");
        put(PE_0047.class, "134043");
        put(PE_0048.class, "9110846700");
        put(PE_0049.class, "296962999629");
        put(PE_0050.class, "997651");
        put(PE_0051.class, "121313");
        put(PE_0052.class, "142857");
        put(PE_0053.class, "4075");
        put(PE_0054.class, "376");
        put(PE_0055.class, "249");
        put(PE_0056.class, "972");
        put(PE_0057.class, "153");
        put(PE_0058.class, "26241");
        put(PE_0059.class, "129448");
        put(PE_0060.class, "26033");
        put(PE_0061.class, "28684");
        put(PE_0062.class, "127035954683");
        put(PE_0063.class, "49");
        put(PE_0064.class, "1322");
        put(PE_0065.class, "272");
        put(PE_0066.class, "661");
        put(PE_0067.class, "7273");
        put(PE_0068.class, "6531031914842725");
        put(PE_0069.class, "510510");
        put(PE_0070.class, "8319823");
        put(PE_0071.class, "428570");
        put(PE_0072.class, "303963552391");
        put(PE_0073.class, "7295372");
        put(PE_0074.class, "402");
        put(PE_0075.class, "161667");
        put(PE_0076.class, "190569291");
        put(PE_0077.class, "71");
        put(PE_0078.class, "55374");
        put(PE_0079.class, "73162890");
        put(PE_0080.class, "40886");
        put(PE_0081.class, "427337");
        put(PE_0082.class, "260324");
        put(PE_0083.class, "425185");
        put(PE_0084.class, "101524");
        put(PE_0085.class, "2772");
        put(PE_0086.class, "1818");
        put(PE_0087.class, "1097343");
        put(PE_0088.class, "7587457");
        put(PE_0089.class, "743");
        put(PE_0090.class, "1217");
        put(PE_0091.class, "14234");
        put(PE_0092.class, "8581146");
        put(PE_0093.class, "1258");
        put(PE_0094.class, "518408346");
        put(PE_0095.class, "14316");
        put(PE_0096.class, "24702");
        put(PE_0097.class, "8739992577");
        put(PE_0098.class, "18769");
        put(PE_0099.class, "709");
        put(PE_0100.class, "756872327473");
        put(PE_0101.class, "37076114526");
        put(PE_0102.class, "228");
        put(PE_0103.class, "20313839404245");
        put(PE_0104.class, "329468");
        put(PE_0105.class, "73702");
        put(PE_0106.class, "21384");
        put(PE_0107.class, "259679");
        put(PE_0108.class, "180180");
        put(PE_0109.class, "38182");
        put(PE_0110.class, "9350130049860600");
        put(PE_0111.class, "612407567715");
        put(PE_0112.class, "1587000");
        put(PE_0113.class, "51161058134250");
        put(PE_0114.class, "16475640049");
        put(PE_0115.class, "168");
        put(PE_0116.class, "20492570929");
        put(PE_0117.class, "100808458960497");
        put(PE_0118.class, "44680");
        put(PE_0119.class, "248155780267521");
        put(PE_0120.class, "333082500");
        put(PE_0121.class, "2269");
        put(PE_0122.class, "1582");
        put(PE_0123.class, "21035");
        put(PE_0124.class, "21417");
        put(PE_0125.class, "2906969179");
        put(PE_0126.class, "18522");
        put(PE_0127.class, "18407904");
        put(PE_0128.class, "14516824220");
        put(PE_0129.class, "1000023");
        put(PE_0130.class, "149253");
        put(PE_0131.class, "173");
        put(PE_0132.class, "843296");
        put(PE_0133.class, "453647705");
        put(PE_0134.class, "18613426663617118");
        put(PE_0135.class, "4989");
        put(PE_0136.class, "2544559");
        put(PE_0137.class, "1120149658760");
        put(PE_0138.class, "1118049290473932");
        put(PE_0139.class, "10057761");
        put(PE_0140.class, "5673835352990");
        put(PE_0141.class, "878454337159");
        put(PE_0142.class, "1006193");
        //put(PE_0143.class, ???);
        //put(PE_0144.class, ???);
        put(PE_0145.class, "608720");
        put(PE_0164.class, "378158756814587");
        put(PE_0173.class, "1572729");
        put(PE_0174.class, "209566");
        put(PE_0179.class, "986262");
        put(PE_0187.class, "17427258");
        put(PE_0188.class, "95962097");
        put(PE_0191.class, "1918080160");
        put(PE_0203.class, "34029210557338");
        put(PE_0204.class, "2944730");
        put(PE_0205.class, "0.5731441");
        put(PE_0206.class, "1389019170");
        put(PE_0293.class, "2209");
        put(PE_0297.class, "2252639041804718029");
        put(PE_0301.class, "2178309");
        put(PE_0303.class, "1111981904675169");
        put(PE_0313.class, "2057774861813004");
        put(PE_0315.class, "13625242");
        put(PE_0321.class, "2470433131948040");
        put(PE_0323.class, "6.3551758451");
        put(PE_0329.class, "199740353/29386561536000");
        put(PE_0336.class, "CAGBIHEFJDK");
        put(PE_0340.class, "291504964");
        put(PE_0345.class, "13938");
        put(PE_0346.class, "336108797689259276");
        put(PE_0347.class, "11109800204052");
        put(PE_0348.class, "1004195061");
        put(PE_0351.class, "11762187201804552");
        put(PE_0357.class, "1739023853137");
        put(PE_0359.class, "40632119");
        put(PE_0381.class, "139602943319822");
        put(PE_0387.class, "696067597313468");
        put(PE_0401.class, "281632621");
        put(PE_0407.class, "39782849136421");
        put(PE_0997.class, "5765993594880");
    }};
    private static final int DEFAULT_LOG_TIME = 100;

    static void main() {
        tests();
    }
    private static void assertEquals(String s, Class<?> cls) {
        if (!s.equals(tests.get(cls))) throw new AssertionError(cls.getName() + ": " + s + " != " + tests.get(cls));
    }
    @SuppressWarnings("unused")
    private static void tests() {
        tests(0, tests.size()-1, DEFAULT_LOG_TIME);
    }
    @SuppressWarnings({"unused", "SameParameterValue"})
    private static void tests(int logTime) {
        tests(0, tests.size()-1, logTime);
    }
    @SuppressWarnings({"unused", "SameParameterValue"})
    private static void tests(int startIndex, int endIndex) {
        tests(startIndex, endIndex, DEFAULT_LOG_TIME);
    }
    public static void tests(int startIndex, int endIndex, int logTime) {
        double allStart = System.currentTimeMillis();
        int index = -1;
        for (Class<?> cls : tests.keySet()) {
            index++;
            if (index < startIndex) continue;
            if (index > endIndex) break;
            System.out.print(cls.getName() + "\r");
            double start = System.currentTimeMillis();
            String result;
            try {
                result = (String) cls.getMethod("PE").invoke(null);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            assertEquals(result, cls);
            double end = System.currentTimeMillis();
            double time = end - start;
            if (time > logTime) System.out.println(cls.getName() + ": " + (int) time + " ms");
        }
        double allEnd = System.currentTimeMillis();
        System.out.println("Total time: " + (allEnd - allStart) + " ms");
    }
}