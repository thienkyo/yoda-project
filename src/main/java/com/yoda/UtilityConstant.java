package com.yoda;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class UtilityConstant {
	final public static int AUTHENTICATION_TIMEOUT = 60;
	
	final public static int ACTIVE_STATUS = 1;
	final public static int INACTIVE_STATUS = 0;
	
	final public static String MEMBER_ROLE = "MEMBER";
	final public static String ADMIN_ROLE = "ADMIN";
	final public static String MOD_ROLE = "MOD";
	
	final public static int ORDER_STATUS_ORDERED = 20;
	final public static int ORDER_STATUS_PAID = 21;
	final public static int ORDER_STATUS_SHIPPED = 22;
	final public static int ORDER_STATUS_DONE = 23;
	
	final public static int PRODUCT_PAGE_SIZE = 9;
	final public static int BLOG_PAGE_SIZE = 4;
	
	
	final public static ResponseEntity<String>savefile(String dir, MultipartFile uploadfile, String oldName){
    	HttpHeaders headers = new HttpHeaders();
    	String oldFilepath = "";
    	String filename="empty";
    	String filepath = "";
      try {
    	String currentTime = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new java.util.Date());  
        // Get the filename and build the local file path
         filename = currentTime+"-"+uploadfile.getOriginalFilename();
         if(!oldName.isEmpty()){
        	 oldFilepath = Paths.get(dir, oldName).toString();
        	 try{
    	        //Delete if tempFile exists
    	        File fileTemp = new File(oldFilepath);
    	          if (fileTemp.exists()){
    	             fileTemp.delete();
    	          }   
    	      }catch(Exception e){
    	         // if any error occurs
    	         e.printStackTrace();
    	      }
         }
         
         
      //  String directory = env.getProperty("yoda.uploadedFiles.thumbnail");
        filepath = Paths.get(dir, filename).toString();
        
        // Save the file locally
        BufferedOutputStream stream =
            new BufferedOutputStream(new FileOutputStream(new File(filepath)));
        stream.write(uploadfile.getBytes());
        stream.close();
        
        headers.add("newName", filename);
        headers.add("imageDir", filepath);
        headers.setContentType(MediaType.TEXT_PLAIN);
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      
      return new  ResponseEntity<>(filename,headers,HttpStatus.OK);
    }
}
