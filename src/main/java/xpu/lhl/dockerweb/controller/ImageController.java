package xpu.lhl.dockerweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.service.ImagesService;
import xpu.lhl.dockerweb.vo.ImageDetailVO;
import xpu.lhl.dockerweb.vo.ImageVO;
import xpu.lhl.dockerweb.vo.SearchImageVO;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImagesService imagesService;

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

    @GetMapping("search")
    public ModelAndView searchImages(String key, Map<String, Object> map){
        List<SearchImageVO> searchImageVOList = imagesService.searchImages(key);
        map.put("searchImageList", searchImageVOList);
        return new ModelAndView("virtual/images/search", map);
    }

    @GetMapping("inspect")
    public ModelAndView inspectImageInfo(String imageId, Map<String, Object> map){
        ImageDetailVO imageInfo = imagesService.inspectImageInfo(imageId);
        map.put("imageDetailInfo", imageInfo);
        return new ModelAndView("virtual/images/detail", map);
    }
}