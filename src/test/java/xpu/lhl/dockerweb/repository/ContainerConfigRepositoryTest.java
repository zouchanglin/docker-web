package xpu.lhl.dockerweb.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.lhl.dockerweb.entity.ContainerConfig;

import java.util.UUID;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ContainerConfigRepositoryTest {
    @Autowired
    private ContainerConfigRepository repository;

    @Test
    public void save(){
        ContainerConfig entity = new ContainerConfig();
        entity.setContainerId(UUID.randomUUID().toString());
        entity.setImage("docker.io/mysql:5.7");
        entity.setContainerName("my-mysql");
        ContainerConfig saveResult = repository.save(entity);
        assertNotNull(saveResult);
        log.info("【ContainerConfigRepositoryTest】saveResult={}", saveResult);
    }
}