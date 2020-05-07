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
                String used = PhysicalFacilityUtil.getUsedPhysicalMemory();
                String free = PhysicalFacilityUtil.getFreePhysicalMemory();
                String usedSwap = PhysicalFacilityUtil.getCommittedVirtualMemory();
                String freeSwap = PhysicalFacilityUtil.getFreeSwapSpaceMemory();

                memoryVO.update(used, free, freeSwap, usedSwap);
                memoryVO.setFreeNow(free);
                memoryVO.setUsedNow(used);
                memoryVO.setTotal(PhysicalFacilityUtil.getTotalPhysicalMemory());

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
