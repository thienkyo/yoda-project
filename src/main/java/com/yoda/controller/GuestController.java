package com.yoda.controller;

import com.yoda.UtilityConstant;
import com.yoda.models.Orders;
import com.yoda.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private OrdersService orderService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "saveOrder", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GuestController.guestResponse saveOrder(@RequestBody final Orders order, final HttpServletRequest request) throws ServletException {
        Orders or = orderService.save(order);
        return new GuestController.guestResponse("SUCCESS",or.getOrderId()+"");
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadFileProductImage(@RequestParam("files") List<MultipartFile> uploadFile, @RequestParam("oldNames") String oldNames, @RequestParam("phone") String phone, @RequestParam("orderId") String orderId) {

        String directory = env.getProperty("yoda.uploadedFiles.eachProductImageOrder");
        return UtilityConstant.saveMultipleFile(directory,uploadFile, oldNames, phone, orderId);
    }

    private static class guestResponse {
        public String replyStr;
        public String orderId;
        public guestResponse(final String replyStr, final String orderId) {
            this.replyStr = replyStr;
            this.orderId = orderId;
        }
    }
}
