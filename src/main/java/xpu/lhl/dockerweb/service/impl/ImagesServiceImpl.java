package xpu.lhl.dockerweb.service.impl;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.ImageInfo;
import com.spotify.docker.client.messages.ImageSearchResult;
import com.spotify.docker.client.messages.RemovedImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.service.DockerClientPool;
import xpu.lhl.dockerweb.service.ImagesService;
import xpu.lhl.dockerweb.utils.MyDateFormat;
import xpu.lhl.dockerweb.vo.ImageDetailVO;
import xpu.lhl.dockerweb.vo.ImageVO;
import xpu.lhl.dockerweb.vo.SearchImageVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImagesServiceImpl implements ImagesService {
    @Override
    public List<ImageVO> getAllImages() {
        DockerClient client = DockerClientPool.getClient();
        try {
            return convertImageVOList(client.listImages(DockerClient.ListImagesParam.allImages()));
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
