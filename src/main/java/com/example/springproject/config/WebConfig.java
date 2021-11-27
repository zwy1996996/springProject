package com.example.springproject.config;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author Administrator
 * @date 2021/11/27
 */
@Component
public class WebConfig {

    /**
     * 上传路径
     */
    private static String profile = "E:/fileManage/";

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        WebConfig.profile = profile;
    }

    // 获取上传头像路径
    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    // 获取下载路径
    public static String getDownloadPath() {
        return profile + "download/";
    }

    // 获取上传路径
    public static String getUploadPath() {
        return profile + "upload/";
    }

}
