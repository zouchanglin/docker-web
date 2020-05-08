package xpu.lhl.dockerweb.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 磁盘工具类
 */
public class DiskFreeUtils {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    /**
     * 获取磁盘使用信息
     */
    public static Map<String, String> getInfo() {
        double total = 0.0;
        double free = 0.0;
        double used = 0.0;
        // 获取磁盘分区列表
        File[] roots = File.listRoots();
        for (File file : roots) {
            long freeSpace=file.getFreeSpace();
            long totalSpace=file.getTotalSpace();
            long usableSpace=totalSpace-freeSpace;

            free += freeSpace / 1024.0 / 1024 / 1024; // 空闲空间
            total += totalSpace / 1024.0 / 1024 / 1024; // 总空间
            used += usableSpace / 1024.0 / 1024 / 1024;
        }
        Map<String, String> retMap = new HashMap<>();
        retMap.put("total", DECIMAL_FORMAT.format(total));
        retMap.put("used", DECIMAL_FORMAT.format(used));
        retMap.put("free", DECIMAL_FORMAT.format(free));

        return retMap;
    }
}