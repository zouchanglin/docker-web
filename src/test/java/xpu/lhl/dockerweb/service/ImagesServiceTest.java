package xpu.lhl.dockerweb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.lhl.dockerweb.vo.ImageVO;
import xpu.lhl.dockerweb.vo.SearchImageVO;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImagesServiceTest {
    @Autowired
    private ImagesService imagesService;

    @Test
    public void getAllImages() {
        List<ImageVO> allImages = imagesService.getAllImages();
        allImages.forEach(System.out::println);
        assertTrue(allImages.size() > 0);
    }

    @Test
    public void removeImage(){
        imagesService.removeImage("8e8c6f8dc9df");
    }

    @Test
    public void searchImages(){
        List<SearchImageVO> imageVOList = imagesService.searchImages("Tomcat");
        imageVOList.forEach(System.out::println);
    }

    @Test
    public void inspectImageInfo(){
        imagesService.inspectImageInfo("f965319e89de");
    }

    @Test
    public void pullImage(){
        imagesService.pullImage("docker.io/hello-world");
    }
}