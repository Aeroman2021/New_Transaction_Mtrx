import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {
    private static String str = "214 260 763:2417:846 552 1019";

    public static void main(String[] args) {
        String num = "16 586 785 898 975 1368 1551:4992:1149 568 684 522 1128 710 231";
        String replace = num.replace(":", " ");
        String str[] = replace.split(" : ");
        List<String> al;
        al = Arrays.asList(str);
        for(String s: al){
            System.out.println(s);
        }
    }

}
