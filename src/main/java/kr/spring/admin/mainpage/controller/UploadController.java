package kr.spring.admin.mainpage.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.util.UploadFileUtils;

@Controller
public class UploadController {
	private String uploadPath = "D:\\javaWork\\workspace_spring\\KHFinalProject\\src\\main\\webapp\\upload";
	private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value="/admin/pageSetup/mainPage.do", method=RequestMethod.GET)
    public String uploadAjax(){
        // uploadAjax.jsp로 포워딩
    	return "mainPageSetup";
    }

    // produces="text/plain;charset=utf-8" : 파일 한글처리
    @ResponseBody
    @RequestMapping(value="/admin/pageSetup/uploadAjax.do", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
    	log.debug("originalName : "+file.getOriginalFilename());
    	log.debug("size : "+file.getSize());
    	log.debug("contentType : "+file.getContentType());
        return new ResponseEntity<String>(UploadFileUtils.uploadMain(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
    }
}

