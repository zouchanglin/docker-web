package xpu.lhl.dockerweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.enums.ApplicationNameEnum;
import xpu.lhl.dockerweb.service.AppStoreService;
import xpu.lhl.dockerweb.service.ImagesService;
import xpu.lhl.dockerweb.utils.AppEnumUtil;

@Service
public class AppStoreServiceImpl implements AppStoreService {
    private final ImagesService imagesService;

    @Autowired
    public AppStoreServiceImpl(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @Override
    public void pullImageByName(String appName) {
        ApplicationNameEnum applicationNameEnum = AppEnumUtil.getByName(appName);
        imagesService.pullImage(applicationNameEnum.getAppImageName());
    }
}