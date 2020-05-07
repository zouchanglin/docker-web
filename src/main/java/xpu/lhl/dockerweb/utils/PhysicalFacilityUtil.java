package xpu.lhl.dockerweb.utils;

import com.sun.management.OperatingSystemMXBean;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import xpu.lhl.dockerweb.vo.HostBasicInfoVO;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * 物理机基本信息
 */
public class PhysicalFacilityUtil {
    /** 操作系统版本 */
    public static String getOsName(){
        return System.getProperty("os.name");
    }

    /** CPU核数 */
    public static Integer getCPUNumber(){
        return Runtime.getRuntime().availableProcessors();
    }

    /** 总内存 G为单位 */
    public static double getTotalPhysicalMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存
        return omb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024;
    }

    /** 可用内存 G为单位 */
    public static double getFreePhysicalMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存
        return omb.getFreePhysicalMemorySize() / 1024.0 / 1024 / 1024;
    }

    /** 已使用内存 G为单位 */
    public static double getUsedPhysicalMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存
        return (omb.getTotalPhysicalMemorySize() - omb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024;
    }

    /** 可用交换区内存 G为单位 */
    public static double getFreeSwapSpaceMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 可用交换区内存
        return omb.getFreeSwapSpaceSize() / 1024.0 / 1024 / 1024;
    }

    /** 已提交的交换区内存 G为单位 */
    public static double getCommittedVirtualMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 可用交换区内存
        return omb.getCommittedVirtualMemorySize() / 1024.0 / 1024 / 1024;
    }

    public static double getTotalSwapMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总交换区内存
        return omb.getTotalSwapSpaceSize() / 1024.0 / 1024 / 1024;
    }

    /** [CPU系统使用率，CPU用户使用率，CPU当前等待率，CPU当前空闲率，CPU平均负载] */
    public static String[] getAllCPUInfo(){
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        String[] strings = new String[5];
        strings[0] = new DecimalFormat("#.##%").format((cSys>0? cSys:1) * 1.0 / totalCpu);
        strings[1] = new DecimalFormat("#.##%").format(user * 1.0 / totalCpu);
        strings[2] = new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu);
        strings[3] = new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu);
        strings[4] = String.format("%.1f%%", processor.getSystemCpuLoadBetweenTicks() * 100);
        return strings;
    }

    /**
     * 物理磁盘监控
     */
    public void getAllDiskInfo(){
        // 磁盘使用情况
        File[] files = File.listRoots();
        for (File file : files) {
            String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024 / 1024);
            String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024 / 1024);
            String used = new DecimalFormat("#.#").format(file.getUsableSpace() * 1.0 / 1024 / 1024 / 1024);
            String path = file.getPath();
        }
    }

    public static HostBasicInfoVO getHostAndJVMInfo(){
        Properties props = System.getProperties(); //系统属性
        HostBasicInfoVO infoVO = new HostBasicInfoVO();
        infoVO.setOsName(props.getProperty("os.name"));
        infoVO.setOsArch(props.getProperty("os.arch"));
        infoVO.setOsVersion(props.getProperty("os.version"));
        infoVO.setCpuNumber(getCPUNumber());
        infoVO.setUserName(props.getProperty("user.name"));
        infoVO.setHomePath(props.getProperty("user.home"));

        infoVO.setJavaVersion(props.getProperty("java.version"));
        infoVO.setJavaVendor(props.getProperty("java.vendor"));
        infoVO.setJavaVendorUrl(props.getProperty("java.vendor.url"));
        infoVO.setVmSpecificationVersion(props.getProperty("java.vm.specification.version"));
        infoVO.setVmSpecificationVendor(props.getProperty("java.vm.specification.vendor"));
        infoVO.setVmSpecificationName(props.getProperty("java.vm.specification.name"));
        infoVO.setVmVersion(props.getProperty("java.vm.version"));
        infoVO.setJavaVendor(props.getProperty("java.vm.vendor"));
        infoVO.setVmName(props.getProperty("java.vm.name"));
        return infoVO;
    }
}