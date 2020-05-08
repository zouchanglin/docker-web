package xpu.lhl.dockerweb.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.service.CPUWebSocket;
import xpu.lhl.dockerweb.service.DiskWebSocket;
import xpu.lhl.dockerweb.service.MemoryWebSocket;
import xpu.lhl.dockerweb.service.MonitorService;
import xpu.lhl.dockerweb.utils.DiskFreeUtils;
import xpu.lhl.dockerweb.utils.PhysicalFacilityUtil;
import xpu.lhl.dockerweb.vo.CPUVO;
import xpu.lhl.dockerweb.vo.DiskVO;
import xpu.lhl.dockerweb.vo.MemoryVO;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MonitorServiceImpl implements MonitorService {
    private final MemoryWebSocket memoryWebSocket;
    private final DiskWebSocket diskWebSocket;
    private final CPUWebSocket cpuWebSocket;

    private MemoryVO memoryVO;
    private CPUVO cpuvo;

    public MonitorServiceImpl(MemoryWebSocket memoryWebSocket,
                              DiskWebSocket diskWebSocket,
                              CPUWebSocket cpuWebSocket) {
        this.memoryWebSocket = memoryWebSocket;
        this.diskWebSocket = diskWebSocket;
        this.cpuWebSocket = cpuWebSocket;
    }

    @Override
    public void startSendMemoryMessage() {
        new Thread(()->{
            if(memoryVO == null) memoryVO = new MemoryVO();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CopyOnWriteArrayList<MemoryWebSocket> socketSet = MemoryWebSocket.webSocketSet;

            while (!socketSet.isEmpty()){
                double used = PhysicalFacilityUtil.getUsedPhysicalMemory();
                double free = PhysicalFacilityUtil.getFreePhysicalMemory();
                double total = PhysicalFacilityUtil.getTotalPhysicalMemory();

                double usedSwap = PhysicalFacilityUtil.getCommittedVirtualMemory();
                double freeSwap = PhysicalFacilityUtil.getFreeSwapSpaceMemory();
                double totalSwap = PhysicalFacilityUtil.getTotalSwapMemory();

                String newUsed = String.format("%.2f", used/total);
                String newFree = String.format("%.2f", free/total);
                String newFreeSwap = String.format("%.2f", freeSwap/totalSwap);
                String newUsedSwap = String.format("%.2f", usedSwap/totalSwap);

                memoryVO.update(newUsed, newFree, newFreeSwap, newUsedSwap);
                memoryVO.setFreeNow(String.format("%.2f", free));
                memoryVO.setUsedNow(String.format("%.2f", used));
                memoryVO.setTotal(String.format("%.2f", total));

                memoryWebSocket.sendTextMessage(JSONObject.toJSONString(memoryVO));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void startSendDiskMessage() {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CopyOnWriteArrayList<DiskWebSocket> socketSet = DiskWebSocket.webSocketSet;
            Map<String, String> diskInfo = DiskFreeUtils.getInfo();
            while (!socketSet.isEmpty()){
                DiskVO diskVO = new DiskVO();
                diskVO.setFree(diskInfo.get("free"));
                diskVO.setUsed(diskInfo.get("used"));
                diskWebSocket.sendTextMessage(JSONObject.toJSONString(diskVO));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void startSendCPUMessage() {
        new Thread(()->{
            if(cpuvo == null) cpuvo = new CPUVO();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CopyOnWriteArrayList<CPUWebSocket> socketSet = CPUWebSocket.webSocketSet;

            while (!socketSet.isEmpty()){
                String[] allCPUInfo = PhysicalFacilityUtil.getAllCPUInfo();
                cpuvo.update(allCPUInfo[0], allCPUInfo[1], allCPUInfo[2], allCPUInfo[3], allCPUInfo[4]);
                log.info("VO={}", cpuvo);
                String toJSONString = JSONObject.toJSONString(cpuvo);
                log.info("JSON={}", toJSONString);
                cpuWebSocket.sendTextMessage(toJSONString);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}