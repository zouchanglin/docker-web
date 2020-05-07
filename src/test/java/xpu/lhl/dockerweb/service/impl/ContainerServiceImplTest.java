package xpu.lhl.dockerweb.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.lhl.dockerweb.service.ContainerService;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ContainerServiceImplTest {
    @Autowired
    private ContainerService containerService;

    @Test
    public void createContainer() {

    }
}