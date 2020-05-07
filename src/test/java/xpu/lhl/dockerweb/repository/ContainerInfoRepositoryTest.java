package xpu.lhl.dockerweb.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.lhl.dockerweb.entity.ContainerInfo;

import static org.junit.Assert.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ContainerInfoRepositoryTest {

    @Autowired
    private ContainerInfoRepository repository;

    @Test
    public void save(){
        ContainerInfo containerInfo = new ContainerInfo();
        containerInfo.setContainerId("27891783912675");
        containerInfo.setContainerName("MyUbuntu");
        ContainerInfo saveResult = repository.save(containerInfo);
        assertNotNull(saveResult);
        assertEquals(1, repository.findAll().size());
    }
}