package com.markliu.controller;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: markliu
 * Time  : 16-7-31 下午3:51
 */
public interface UploadController {

    String doUploadFile(MultipartFile file, Model model);
    String uploadFilePage();
}
