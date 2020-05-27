package xpu.lhl.dockerweb.other;

public class CPUTest {
    public static void main(String[] args) {
        int i = 0;
        while(true){
            if(i % 2 == 0) i += 2;
            if(i == Integer.MAX_VALUE) return;
        }
    }
}
