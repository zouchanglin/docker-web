package xpu.lhl.dockerweb.service;

public interface MonitorService {
    void startSendMemoryMessage();
    void startSendDiskMessage();
    void startSendCPUMessage();
}
