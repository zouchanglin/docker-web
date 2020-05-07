package xpu.lhl.dockerweb.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.service.MemoryWebSocket;
import xpu.lhl.dockerweb.service.MonitorService;
import xpu.lhl.dockerweb.utils.PhysicalFacilityUtil;
import xpu.lhl.dockerweb.vo.MemoryVO;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MonitorServiceImpl implements MonitorService {
    private final MemoryWebSocket memoryWebSocket;

    private MemoryVO memoryVO;

    public MonitorServiceImpl(MemoryWebSocket memoryWebSocket) {
        this.memoryWebSocket = memoryWebSocket;
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
}