package com.java.controller;

import com.java.service.impl.QiniuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/12- 16:57
 */
@Controller
public class TestController {

        @Resource
        private QiniuService qiniuService;

   /*     @RequestMapping(value = "/testUpload", method = RequestMethod.POST)
        @ResponseBody
        public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

            if(file.isEmpty()) {
                return "error";
            }

            try {
                String fileUrl=qiniuService.saveImage(file);
                return "success, imageUrl = " + fileUrl;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "fail";
        }*/

    @ResponseBody
    @PostMapping("/testUpload")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, Object> succMap = new HashMap<>();
            String imgUrl = qiniuService.saveImage(file);
            succMap.put("error", 0);
            succMap.put("url", imgUrl);
            return succMap;
        } catch (Exception e){
            Map<String, Object> msg = new HashMap<String, Object>();
            msg.put("error", 1);
            msg.put("message", "<font size='3'>您选择的文件上传失败！</font>");
            return msg;
        }
    }
}
