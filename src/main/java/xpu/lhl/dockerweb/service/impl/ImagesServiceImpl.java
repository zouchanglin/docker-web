package xpu.lhl.dockerweb.service.impl;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.form.BuildImageForm;
import xpu.lhl.dockerweb.service.DockerClientPool;
import xpu.lhl.dockerweb.service.ImagesService;
import xpu.lhl.dockerweb.utils.MyDateFormat;
import xpu.lhl.dockerweb.utils.ZipUtil;
import xpu.lhl.dockerweb.vo.ImageDetailVO;
import xpu.lhl.dockerweb.vo.ImageVO;
import xpu.lhl.dockerweb.vo.SearchImageVO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImagesServiceImpl implements ImagesService {
    @Override
    public List<ImageVO> getAllImages() {
        DockerClient client = DockerClientPool.getClient();
        try {
            return convertImageVOList(client.listImages(DockerClient.ListImagesParam.allImages(false)));
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean removeImage(String imageId) {
        DockerClient client = DockerClientPool.getClient();
        try {
            List<RemovedImage> removedImageList = client.removeImage(imageId);
            return !removedImageList.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SearchImageVO> searchImages(String key) {
        DockerClient client = DockerClientPool.getClient();
        try {
            return convertSearchImageVOList(client.searchImages(key));
        } catch (InterruptedException | DockerException e) {
            log.error("【ImagesServiceImpl】searchImages", e);
        }
        return new ArrayList<>();
    }

    @Override
    public String buildImage(File file, BuildImageForm buildImageForm) {
        DockerClient client = DockerClientPool.getClient();
        final AtomicReference<String> imageIdFromMessage = new AtomicReference<>();
        try {
            File myTmpDir = new File(FileUtils.getTempDirectory(), "docker-web-build");
            if(myTmpDir.exists()){
                boolean deleteResult = myTmpDir.delete();
                log.info("【ImagesServiceImpl】buildImage deleteResult = {}", deleteResult);
            }
            boolean mkdirResult = myTmpDir.mkdir();
            log.info("【ImagesServiceImpl】buildImage mkdirResult = {}", mkdirResult);
            String zipFilePath = file.getAbsolutePath();
            //解压文件并且去除.zip后缀名 //C:\Users\15291\Desktop\build.zip -> C:\Users\15291\Desktop\build
            ZipUtil.decompress(zipFilePath.substring(0, zipFilePath.length() - 4), myTmpDir.getPath());
            String buildImageId = client.build(
                    Paths.get(myTmpDir.getPath()), message -> {
                        final String imageId = message.buildImageId();
                        if (imageId != null) {
                            imageIdFromMessage.set(imageId);
                        }
                    });
            client.tag(buildImageId, String.format("%s:%s", buildImageForm.getRepository(), buildImageForm.getTag()));
            return buildImageId;
        } catch (DockerException | InterruptedException | IOException e) {
            log.error("【ImagesServiceImpl】buildImage", e);
        }
        return "";
    }

    public ImageDetailVO inspectImageInfo(String imageId){
        DockerClient client = DockerClientPool.getClient();
        try {
            ImageInfo imageInfo = client.inspectImage(imageId);
            ImageDetailVO detailVO = new ImageDetailVO();
            detailVO.setArchitecture(imageInfo.architecture());
            detailVO.setOs(imageInfo.os());
            detailVO.setVirtualSize(String.format("%.2f M", imageInfo.virtualSize() / 1024.0 / 1024.0));
            return detailVO;
        } catch (InterruptedException| DockerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SearchImageVO convertSearchImageVO(ImageSearchResult result){
        SearchImageVO searchImageVO = new SearchImageVO();
        searchImageVO.setStars(result.starCount());
        searchImageVO.setOfficial(String.valueOf(result.official()));
        searchImageVO.setName(result.name());
        searchImageVO.setDescription(result.description());
        searchImageVO.setAutomated(String.valueOf(result.automated()));
        //BeanUtils.copyProperties(result, searchImageVO);
        return searchImageVO;
    }

    private List<SearchImageVO> convertSearchImageVOList(List<ImageSearchResult> imageList){
        return imageList.stream().map(this::convertSearchImageVO).collect(Collectors.toList());
    }

    private ImageVO convertImageVO(Image image){
        ImageVO imageVO = new ImageVO();
        double size = image.size() / 1024.0 / 1024.0;
        imageVO.setSize(String.format("%.2f", size));
        imageVO.setImageId(image.id().substring(7, 19));
        Date createDate = new Date(Long.parseLong(image.created()) * 1000);
        imageVO.setCreated(MyDateFormat.dateFormatForImage.format(createDate));
        String[] repoAndTag = Objects.requireNonNull(image.repoTags()).get(0).split(":");
        imageVO.setRepository(repoAndTag[0]);
        imageVO.setTag(repoAndTag[1]);
        return imageVO;
    }

    private List<ImageVO> convertImageVOList(List<Image> imageList){
        return imageList.stream().map(this::convertImageVO).collect(Collectors.toList());
    }
}
