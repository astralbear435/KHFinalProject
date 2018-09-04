package kr.spring.admin.mainpage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.admin.mainpage.domain.MainImageCommand;
import kr.spring.admin.mainpage.service.MainImageService;
import kr.spring.util.MediaUtils;
import kr.spring.util.UploadFileUtils;

@Controller
public class UploadController {
    // bean�� id�� uploadPath�� �±׸� ����
    @Resource(name="uploadPath")
    String uploadPath;
    
    String fN;
  
    @Resource
	private MainImageService mainImageService;
    Integer order = 0; 
	private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value="/admin/pageSetup/mainPage.do", method=RequestMethod.GET)
    public String uploadAjax(){
        // uploadAjax.jsp�� ������
    	return "mainPageSetup";
    }

    // produces="text/plain;charset=utf-8" : ���� �ѱ�ó��
    @ResponseBody
    @RequestMapping(value="/admin/pageSetup/uploadAjax.do", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
    	log.debug("originalName : "+file.getOriginalFilename());
    	log.debug("size : "+file.getSize());
    	log.debug("contentType : "+file.getContentType());
    	fN = UploadFileUtils.uploadMain(uploadPath, file.getOriginalFilename(), file.getBytes());
    	MainImageCommand mainimage = new MainImageCommand();
    	mainimage.setMain_img_name(fN.substring(fN.indexOf("s_")+2));
    	mainimage.setS_main_img_name(fN.substring(fN.indexOf("s_")));
    	if(mainImageService.selectImageCount()==0) {
    		order=1;
    	}else {
    		order=mainImageService.selectImageCount()+1;
    	}
    		mainimage.setMain_order(order);
    	mainImageService.insertMainImage(mainimage);
        return new ResponseEntity<String>(fN, HttpStatus.OK);
    }
    // 6. �̹��� ǥ�� ����
    @ResponseBody // view�� �ƴ� data����
    @RequestMapping("/admin/pageSetup/displayFile.do")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
        // ������ ������ �ٿ�ε��ϱ� ���� ��Ʈ��
        InputStream in = null; //java.io
        ResponseEntity<byte[]> entity = null;
        try {
            // Ȯ���ڸ� �����Ͽ� formatName�� ����
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // ������ Ȯ���ڸ� MediaUtilsŬ��������  �̹������Ͽ��θ� �˻��ϰ� ���Ϲ޾� mType�� ����
            MediaType mType = MediaUtils.getMediaType(formatName);
            // ��� ���� ��ü(�ܺο��� �����͸� �ְ���� ������ header�� body�� �����ؾ��ϱ� ������)
            HttpHeaders headers = new HttpHeaders();
            // InputStream ����
            in = new FileInputStream(uploadPath + fileName);
            // �̹��� �����̸�
            if (mType != null) { 
                headers.setContentType(mType);
            // �̹����� �ƴϸ�
            } else { 
                fileName = fileName.substring(fileName.indexOf("_") + 1);
                // �ٿ�ε�� ����Ʈ Ÿ��
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                // 
                // ����Ʈ�迭�� ��Ʈ������ : new String(fileName.getBytes("utf-8"),"iso-8859-1") * iso-8859-1 ���������, ū ����ǥ ���ο�  " \" ���� \" "
                // ������ �ѱ� ���� ����
                headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("utf-8"), "iso-8859-1")+"\"");
                System.out.println("�̹���: "+fileName);
                //headers.add("Content-Disposition", "attachment; filename='"+fileName+"'");
            }
            // ����Ʈ�迭, ���, HTTP�����ڵ�
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // HTTP���� �ڵ�()
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close(); //��Ʈ�� �ݱ�
        }
        return entity;
    }

    // 7. ���� ���� ����
    @ResponseBody // view�� �ƴ� ������ ����
    @RequestMapping(value = "/admin/pageSetup/deleteFile.do", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {
        // ������ Ȯ���� ����
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // �̹��� ���� ���� �˻�
        MediaType mType = MediaUtils.getMediaType(formatName);
        // �̹����� ���(����� + �������� ����), �̹����� �ƴϸ� �������ϸ� ����
        // �̹��� �����̸�
        if (mType != null) {
            // ����� �̹��� ���� ����
            String front = fileName.substring(0, 11);
            String end = fileName.substring(13);
            System.out.println("front : "+ front);
            System.out.println("emd : "+end);
            // ����� �̹��� ����
            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }
        // ���� ���� ����
        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
        System.out.println(fileName);
        mainImageService.deleteMainImage(fileName.substring(fileName.indexOf("s_")));
        // �����Ϳ� http ���� �ڵ� ����
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
}

