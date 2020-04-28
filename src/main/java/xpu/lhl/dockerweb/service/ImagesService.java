package xpu.lhl.dockerweb.service;

import xpu.lhl.dockerweb.vo.ImageDetailVO;
import xpu.lhl.dockerweb.vo.ImageVO;
import xpu.lhl.dockerweb.vo.SearchImageVO;

import java.util.List;

public interface ImagesService {
    /**
     * 获得本地所有的镜像
     * @return 镜像列表
     */
    List<ImageVO> getAllImages();


    /**
     * 删除镜像
     * @param imageId 镜像ID
     */
    boolean removeImage(String imageId);

    /**
     * 根据关键字搜索镜像
     * @param key 关键字
     * @return 搜索结果集合
     */
    List<SearchImageVO> searchImages(String key);

    /**
     * 查看镜像详细信息
     * @param imageId 镜像ID
     * @return 镜像详细信息
     */
    ImageDetailVO inspectImageInfo(String imageId);
}
