package xpu.lhl.dockerweb.vo;

import lombok.Data;
import xpu.lhl.dockerweb.utils.MyDateFormat;

import java.util.Date;

@Data
public class MemoryVO {
    private String[] time;
    private String[] used;
    private String[] free;
    private String[] freeSwap;
    private String[] usedSwap;
    private int length;
    private String usedNow;
    private String freeNow;
    private String total;

    public MemoryVO() {
        this.time = new String[8];
        this.used = new String[8];
        this.free = new String[8];
        this.freeSwap = new String[8];
        this.usedSwap = new String[8];
        length = 0;
    }

    public void update(String newUsed, String newFree,
                       String newFreeSwap, String newUsedSwap){
        String newTime = MyDateFormat.dateFormatForEChars.format(new Date());
        if(length >= 8){
            updateArray(time, newTime);
            updateArray(used, newUsed);
            updateArray(free, newFree);
            updateArray(freeSwap, newFreeSwap);
            updateArray(usedSwap, newUsedSwap);
        }else{
            addArray(time, newTime);
            addArray(used, newUsed);
            addArray(free, newFree);
            addArray(freeSwap, newFreeSwap);
            addArray(usedSwap, newUsedSwap);
            length++;
        }
    }

    private void addArray(String[] time, String newTime) {
        time[length] = newTime;
    }

    private static void updateArray(String[] time, String newTime) {
        if (time.length - 1 >= 0) System.arraycopy(time, 1, time, 0, time.length - 1);
        time[time.length-1] = newTime;
    }
}
