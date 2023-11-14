package com.example.hellospringboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    // 前端传过来的字段必须与username和file一致，
    public String up(String username, MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("用户名：" + username);
        System.out.println("文件名：" + file.getOriginalFilename());
        System.out.println("文件类型：" + file.getContentType());

        String path = request.getServletContext().getRealPath("/upload");
        System.out.println("Web服务器运行路径：" + path);
        saveFile(file, path); // 将文件存储到Web服务器
        return "Upload Success !!!";
    }

    public void saveFile(MultipartFile file, String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean wasSuccessful = dir.mkdir();
            if (!wasSuccessful) {
                System.out.println("was not successful.");
            }
        }
        File target = new File(path + file.getOriginalFilename());
        file.transferTo(target);
    }
}
