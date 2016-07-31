package com.markliu.controller.impl;

import com.markliu.controller.UploadController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: markliu
 * Time  : 16-7-31 下午3:52
 */
@Controller
@RequestMapping("/file")
public class UploadControllerImpl implements UploadController {

    private static final Log logger = LogFactory.getLog(UploadControllerImpl.class);

    @RequestMapping(value = "/do_upload_file", method = RequestMethod.POST)
    public String doUploadFile(@RequestParam MultipartFile upload_file, Model model) {

        logger.info("implement MultipartFile: " + upload_file.getClass().getName());
        logger.info("File: " + upload_file.getOriginalFilename());
        if (!upload_file.isEmpty()) {
            try {
                FileUtils.copyInputStreamToFile(upload_file.getInputStream(), new File(
                        System.currentTimeMillis() + "-" + upload_file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    @RequestMapping("/upload_file")
    public String uploadFilePage(){
        logger.info("uploadFilePage called");
        return "upload_file";
    }
}
