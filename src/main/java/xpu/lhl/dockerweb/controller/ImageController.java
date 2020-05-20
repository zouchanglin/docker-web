package xpu.lhl.dockerweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.form.BuildImageForm;
import xpu.lhl.dockerweb.service.ImagesService;
import xpu.lhl.dockerweb.vo.ImageDetailVO;
import xpu.lhl.dockerweb.vo.ImageVO;
import xpu.lhl.dockerweb.vo.SearchImageVO;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/images")
public class ImageController {
    private final ImagesService imagesService;

    @Autowired
    public ImageController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    // 获得本地镜像列表
    @GetMapping("list")
    public ModelAndView getImagesList(Map<String, Object> map){
        List<ImageVO> images = imagesService.getAllImages();
        map.put("images", images);
        return new ModelAndView("virtual/images/list", map);
    }

    // 根据imageId移除镜像
    @GetMapping("remove")
    public ModelAndView removeImage(String imageId, Map<String, Object> map){
        map.put("url", "/images/list");
        if(imagesService.removeImage(imageId)){
            map.put("msg", "Remove this image success!");
            return new ModelAndView("common/success", map);
        }else{
            map.put("msg", "Remove this image error! The container is using the image!");
            return new ModelAndView("common/error", map);
        }
    }

    // 获得搜索镜像的页面
    @GetMapping("search-page")
    public ModelAndView getSearchImagePage(){
        return new ModelAndView("virtual/images/search");
    }

    // 执行搜索镜像
    @GetMapping("search")
    public ModelAndView searchImages(String key, Map<String, Object> map){
        List<SearchImageVO> searchImageVOList = imagesService.searchImages(key);
        map.put("searchImageList", searchImageVOList);
        return new ModelAndView("virtual/images/search", map);
    }

    // 查看镜像的详细信息
    @GetMapping("inspect")
    public ModelAndView inspectImageInfo(String imageId, Map<String, Object> map){
        ImageDetailVO imageInfo = imagesService.inspectImageInfo(imageId);
        map.put("imageDetailInfo", imageInfo);
        return new ModelAndView("virtual/images/detail", map);
    }

    // DockerFile上传并构建镜像
    @PostMapping("build")
    public ModelAndView buildImage(@RequestParam("file") MultipartFile file,
                                   BuildImageForm buildImageForm,
                                   Map<String, Object> map){
        File directory = FileUtils.getTempDirectory();
        File destFile = new File(directory, "tmp.zip");
        if(destFile.exists()) {
            boolean delete = destFile.delete();
            log.info("【ImageController】buildImage tmp.zip delete result: {}", delete);
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            log.error("【ImageController】buildImage", e);
            map.put("msg", "Build Failed");
            map.put("url", "/images/list");
            return new ModelAndView("common/error");
        }
        String newImageId = imagesService.buildImage(destFile, buildImageForm);
        map.put("msg", "Build new image success, This image's id is " + newImageId);
        map.put("url", "/images/list");
        return new ModelAndView("common/success");
    }

    // 获得构建镜像的页面
    @GetMapping("build-page")
    public ModelAndView getBuildImagePage(){
        return new ModelAndView("virtual/images/build");
    }

    // 推送镜像至云仓库
    @GetMapping("push")
    public ModelAndView pushImage(String imageId, Map<String, Object> map){
        map.put("url", "/images/list");
        if(imagesService.pushImage(imageId)){
            map.put("msg", "Push Image Success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Push Image Error!");
            return new ModelAndView("common/error");
        }
    }

    // 从仓库拉取镜像
    @GetMapping("pull")
    public ModelAndView pullImage(String imageName, Map<String, String> map){
        map.put("url", "/images/list");
        if(imagesService.pullImage(imageName)){
            map.put("msg", "Pull image success!");
            return new ModelAndView("common/success", map);
        }else{
            map.put("msg", "Pull image failed!");
            return new ModelAndView("common/error", map);
        }
    }
}