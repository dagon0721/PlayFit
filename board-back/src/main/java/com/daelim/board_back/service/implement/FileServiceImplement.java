package com.daelim.board_back.service.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daelim.board_back.service.FileService;

import java.io.File;
import java.net.URI;
import java.util.UUID;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filepath;
    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        
        if (file.isEmpty()) return null;
        
        String originalFileName = file.getOriginalFilename();
        @SuppressWarnings("null") //오류 나면 확인해야됨
        String extenstion = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extenstion;
        String savePath = filepath + saveFileName;

        try {
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
        return url;
    }

    @Override
    public Resource getImage(String fileName) {
        
        Resource resource = null;

        try {
            resource = new UrlResource("file:" + filepath + fileName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return resource;

    }
    
}
