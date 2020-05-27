package xpu.lhl.dockerweb.other;

import sun.misc.Unsafe;
import sun.security.jca.GetInstance;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class MemoryTest {
    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        long memory = unsafe.allocateMemory(1024L * 1024 * 200);
        TimeUnit.SECONDS.sleep(3);
        unsafe.freeMemory(memory);
    }
}
