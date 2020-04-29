package xpu.lhl.dockerweb.service;

import org.springframework.web.multipart.MultipartFile;
import xpu.lhl.dockerweb.form.BuildImageForm;
import xpu.lhl.dockerweb.vo.ImageDetailVO;
import xpu.lhl.dockerweb.vo.ImageVO;
import xpu.lhl.dockerweb.vo.SearchImageVO;

import java.io.File;
import java.util.List;

public interface ImagesService {
    /**
     * 获得本地所有的镜像
     * @return 镜像列表
     */
    List<ImageVO> getAllImages();


    /**
     * 根据镜像Id删除镜像
     * @param imageId 镜像Id
     */
    boolean removeImage(String imageId);

    /**
     * 根据关键字搜索镜像
     * @param key 关键字
     * @return 搜索结果集合
     */
    List<SearchImageVO> searchImages(String key);

    /**
     * 根据镜像Id查看镜像详细信息
     * @param imageId 镜像Id
     * @return 镜像详细信息
     */
    ImageDetailVO inspectImageInfo(String imageId);


    /**
     * 通过Dockerfile包去构建镜像
     * @param file Dockerfile包
     * @param buildImageForm 构建镜像信息表单
     * @return 构建完成后的新镜像Id
     */
    String buildImage(File file, BuildImageForm buildImageForm);

    /**
     * 推送镜像至云镜像仓库
     * @param imageName 镜像名称
     * @return 是否推送成功
     */
    boolean pushImage(String imageName);


    /**
     * 通过镜像名称下载镜像
     * @param imageName 镜像名称
     * @return 下载是否成功
     */
    boolean pullImage(String imageName);
}
