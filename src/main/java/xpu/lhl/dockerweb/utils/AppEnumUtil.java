package xpu.lhl.dockerweb.utils;


import xpu.lhl.dockerweb.enums.ApplicationNameEnum;

public class AppEnumUtil {
    public static ApplicationNameEnum getByName(String appName) {
        ApplicationNameEnum[] nameEnums = ApplicationNameEnum.values();
        for(ApplicationNameEnum nameEnum: nameEnums){
            if(appName.equals(nameEnum.getAppName())) return nameEnum;
        }
        return null;
    }
}