package xpu.lhl.dockerweb.vo;

import lombok.Data;
import xpu.lhl.dockerweb.utils.MyDateFormat;

import java.util.Date;

/**
 * CPU信息视图对象(这是一个数据组)
 */
@Data
public class CPUVO {
    private String[] time;
    private String[] systemUsed;
    private String[] userUsed;
    private String[] wait;
    private String[] free;
    private String[] load;

    private int length;

    public CPUVO() {
        this.time = new String[8];
        this.systemUsed = new String[8];
        this.userUsed = new String[8];
        this.wait = new String[8];
        this.free = new String[8];
        this.load = new String[8];
        length = 0;
    }

    public void update(String newSystemUsed,
                       String newUserUsed,
                       String newWait,
                       String newFree,
                       String newLoad){
        String newTime = MyDateFormat.dateFormatForEChars.format(new Date());
        if(length >= 8){
            updateArray(time, newTime);
            updateArray(systemUsed, newSystemUsed);
            updateArray(userUsed, newUserUsed);
            updateArray(wait, newWait);
            updateArray(free, newFree);
            updateArray(load, newLoad);
        }else{
            addArray(time, newTime);
            addArray(systemUsed, newSystemUsed);
            addArray(userUsed, newUserUsed);
            addArray(wait, newWait);
            addArray(free, newFree);
            addArray(load, newLoad);
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
