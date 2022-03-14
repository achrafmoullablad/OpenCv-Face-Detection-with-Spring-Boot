package com.opencv.springboot.controller;

import com.opencv.springboot.entity.FaceEntity;
import com.opencv.springboot.service.FaceDetectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private FaceDetectionService faceDetectionService;


    @ResponseBody
    @RequestMapping(value = "/faceDetect/image", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] detectFaceImage(@RequestParam("file") MultipartFile file) throws IOException {

       if ( !validateImage(file))
       {
          return new byte[1];
       }

        return faceDetectionService.detectFace(file).toImage();
    }

    @ResponseBody
    @RequestMapping(value = "/faceDetect/json", method = RequestMethod.POST)
    public List<FaceEntity> detectFaceJson(@RequestParam("file") MultipartFile file) throws IOException {

        if ( !validateImage(file))
        {
        	System.out.println("Not validate");
            return new ArrayList<>();
        }

        return faceDetectionService.detectFace(file).toList();
    }



    private Boolean validateImage(MultipartFile image) {
        return image.getContentType().equals("image/jpeg");
    }


}
