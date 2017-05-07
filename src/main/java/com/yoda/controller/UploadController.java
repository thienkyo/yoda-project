package com.yoda.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
    @Autowired
    private Environment env;
    
   /* @RequestMapping(value = "/uploadfile2", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
    	HttpHeaders headers = new HttpHeaders();
    	String filename="empty";
      try {
    	String currentTime = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new java.util.Date());  
    	
        // Get the filename and build the local file path
         filename = currentTime+"-"+uploadfile.getOriginalFilename();
        String directory = env.getProperty("yoda.uploadedFiles.thumbnail");
        String filepath = Paths.get(directory, filename).toString();
        
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
    } // method uploadFile
 */
    
    @RequestMapping(value = "/uploadfile2", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
    	String directory = env.getProperty("yoda.uploadedFiles.thumbnail");
    	return savefile(directory,uploadfile);
    } // method uploadFile
    
    @RequestMapping(value = "/uploadfile3", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFileProductImage(@RequestParam("file") MultipartFile uploadfile) {
    	String directory = env.getProperty("yoda.uploadedFiles.productDetail");
    	return savefile(directory,uploadfile);
    }
    
    
    private ResponseEntity<String>savefile(String dir, MultipartFile uploadfile){
    	HttpHeaders headers = new HttpHeaders();
    	String filename="empty";
    	String filepath = "";
      try {
    	String currentTime = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new java.util.Date());  
    	
        // Get the filename and build the local file path
         filename = currentTime+"-"+uploadfile.getOriginalFilename();
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
