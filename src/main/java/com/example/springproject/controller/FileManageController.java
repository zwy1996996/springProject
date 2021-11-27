package com.example.springproject.controller;

import com.example.springproject.config.WebConfig;
import com.example.springproject.utils.FileUploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * TODO
 *
 * @author Administrator
 * @date 2021/11/27
 */
@RequestMapping("/fileManage")
@RestController
public class FileManageController {

    @RequestMapping("/imgFileUp")
    public String ImgFileUp(@RequestParam MultipartFile file){
        try {
            if (! file.isEmpty()){
                String fileName = FileUploadUtils.uploadPicture(WebConfig.getUploadPath(), file);
                System.out.println("fileName:" + fileName);
            }
            return "成功";
        }catch (Exception e){
            return "失败";
        }
    }

    /**
     * 查看图片
     */
    @RequestMapping(value = "/getUrlImg", method = RequestMethod.GET)
   // @ResponseBody
    public void getUrlImg(@RequestParam String url, HttpServletResponse response) throws IOException {
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就返回默认图片或者进行异常处理
        if (!(file.exists() && file.canRead())) {
            System.out.println("文件不存在");
        }
        FileInputStream inputStream = null;
        ServletOutputStream stream = null;
        try {
            inputStream = new FileInputStream(file);
            response.setContentType("image/png;charset=utf-8");
            stream = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = inputStream.read(buffer)) != -1) {
                stream.write(buffer, 0, len);
            }
            stream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
            stream.close();
        }
    }
}
