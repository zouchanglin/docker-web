package xpu.lhl.dockerweb.enums;

import lombok.Getter;

@Getter
public enum CPUSharesEnum {
    A(10000L, "CPU_SHARE_A"),
    B(30000L, "CPU_SHARE_B"),
    C(50000L, "CPU_SHARE_C"),
    D(70000L, "CPU_SHARE_D"),
    E(90000L, "CPU_SHARE_E");

    CPUSharesEnum(long value, String name) {
        this.value = value;
        this.name = name;
    }

    private final long value;
    private String name;
}
