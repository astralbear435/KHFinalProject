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
    // bean의 id가 uploadPath인 태그를 참조
    @Resource(name="uploadPath")
    String uploadPath;
    
    String fN;
  
    @Resource
	private MainImageService mainImageService;
    Integer order = 0; 
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
    // 6. 이미지 표시 매핑
    @ResponseBody // view가 아닌 data리턴
    @RequestMapping("/admin/pageSetup/displayFile.do")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
        // 서버의 파일을 다운로드하기 위한 스트림
        InputStream in = null; //java.io
        ResponseEntity<byte[]> entity = null;
        try {
            // 확장자를 추출하여 formatName에 저장
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 추출한 확장자를 MediaUtils클래스에서  이미지파일여부를 검사하고 리턴받아 mType에 저장
            MediaType mType = MediaUtils.getMediaType(formatName);
            // 헤더 구성 객체(외부에서 데이터를 주고받을 때에는 header와 body를 구성해야하기 때문에)
            HttpHeaders headers = new HttpHeaders();
            // InputStream 생성
            in = new FileInputStream(uploadPath + fileName);
            // 이미지 파일이면
            if (mType != null) { 
                headers.setContentType(mType);
            // 이미지가 아니면
            } else { 
                fileName = fileName.substring(fileName.indexOf("_") + 1);
                // 다운로드용 컨텐트 타입
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                // 
                // 바이트배열을 스트링으로 : new String(fileName.getBytes("utf-8"),"iso-8859-1") * iso-8859-1 서유럽언어, 큰 따옴표 내부에  " \" 내용 \" "
                // 파일의 한글 깨짐 방지
                headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("utf-8"), "iso-8859-1")+"\"");
                System.out.println("이미지: "+fileName);
                //headers.add("Content-Disposition", "attachment; filename='"+fileName+"'");
            }
            // 바이트배열, 헤더, HTTP상태코드
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // HTTP상태 코드()
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close(); //스트림 닫기
        }
        return entity;
    }

    // 7. 파일 삭제 매핑
    @ResponseBody // view가 아닌 데이터 리턴
    @RequestMapping(value = "/admin/pageSetup/deleteFile.do", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {
        // 파일의 확장자 추출
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 이미지 파일 여부 검사
        MediaType mType = MediaUtils.getMediaType(formatName);
        // 이미지의 경우(썸네일 + 원본파일 삭제), 이미지가 아니면 원본파일만 삭제
        // 이미지 파일이면
        if (mType != null) {
            // 썸네일 이미지 파일 추출
            String front = fileName.substring(0, 11);
            String end = fileName.substring(13);
            System.out.println("front : "+ front);
            System.out.println("emd : "+end);
            // 썸네일 이미지 삭제
            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }
        // 원본 파일 삭제
        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
        System.out.println(fileName);
        mainImageService.deleteMainImage(fileName.substring(fileName.indexOf("s_")));
        // 데이터와 http 상태 코드 전송
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
}

