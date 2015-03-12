

/** * @(#)AdminService.java, 2015年2月11日. * * Copyright 2015 Yodao, Inc. All rights reserved. * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. */
 package com.mygudou.app.service; import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.mygudou.app.model.Admin;
import com.mygudou.app.daoimpl.AdminDaoimpl;
import com.mygudou.app.dao.*;
/** * * @author xinwang * *  */
 

 @Service("AdminService")
 @Transactional
public class AdminService {
     
	@Resource(name = "AdminDao")
     private AdminDaoimpl AdminDao;
     private long expires = 24 * 60 * 60;//一天
     /**
      * 用户输入网址，发送get请求，依次判断session中是否有admin，cookie中是否有。。。
      * 如果返回true,则说明不用填写用户名和密码，返回的modelandview success
      * 如果返回的是false，则说明要填写用户名和密码，返回的modelandview showform
      * 
      */
     public boolean LoginCheck(HttpServletRequest request,HttpServletResponse response){
    	 Object object = request.getSession().getAttribute("admin");
    	 if(object != null){
    		 return true;
    	 }
    	 Cookie autoCookie = null;
         Cookie cookies[] = request.getCookies();
         if(cookies!=null){
        	 for(Cookie cookie:cookies){
        		
        		 if("autologin".equals(cookie.getName())){
                     autoCookie = cookie;
                 }
        	 }
        	 if(autoCookie == null){
        		 return false;
        	
        	 }
        	 String value = autoCookie.getValue();
        	 try {
				value = URLDecoder.decode(value,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             String temp[] = value.split(":");
             
             if(temp.length!=3){
            	 return false;
             }
             
             String name = temp[0];
             String time = temp[1];
             String service_md5value = temp[2];
             if(Long.valueOf(time)<=System.currentTimeMillis()){
            	 return false;
             }
             
             Admin entity = AdminDao.checkLogin(name);
             
             if(entity == null){
            	 return false;
                 }
             String md5Temp = entity.getName() + ":" + entity.getPass()+":"+time;
             
             if(!(md5Value(md5Temp).equals(service_md5value))){
             	return false;
             }
             request.getSession().setAttribute("admin", entity);
             
         }
         else{
        	 return false;
         }
		return true;
    	 
     }
     /**
      * 当用户输入用户名和密码，提交表单的时候，对用户的用户名密码进行验证。
      * 如果返回为true,则说明输入的是正确的，返回为false，输入的是错误的。
      * 同时如果用户选择了记住密码，这个时候要生成一个cookie，加到response中去。
      * @param req
      * @param resp
      * @return
      */
     public boolean Loginconf(HttpServletRequest req,HttpServletResponse resp){

         String name = req.getParameter("name");
         String pass = req.getParameter("pass");
         String mark = req.getParameter("mark");
         Admin entity = AdminDao.checkLogin(name, pass);
         if(entity!=null){
             if("mark".equals(mark)){
                 String day = req.getParameter("day");
                 expires = Integer.valueOf(day)*expires;
                 System.out.println(expires);
                 Cookie autoCookie = null;
                 Cookie cookies[] = req.getCookies();
                 for(Cookie cookie:cookies){
                     System.out.println(cookie.getName());
                     if("autologin".equals(cookie.getName())){
                         autoCookie = cookie;
                         long time = System.currentTimeMillis()+expires*1000;
                         String newValue = name+":"+time+":"+md5Value(name+":"+pass+":"+time);
                         autoCookie.setValue(newValue);
                     }
                     else{
                         // name+":"+time+":"+md5(name:pass:time)  
                         long time = System.currentTimeMillis() + expires* 1000;  
                         //cookie拼接的value值,(可以根据自己的想法设计)  
                         String cookieValue = name + ":" + time + ":"  
                                 + md5Value(name + ":" + pass + ":" + time);  
                         //创建cookie  
                        // name = java.net.URLEncoder.encode(name,"gbk");
                         try {
							cookieValue = URLEncoder.encode(cookieValue,"utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                         autoCookie = new Cookie("autologin", cookieValue);
                       }
                 }
                 autoCookie.setMaxAge((int)expires);
                 resp.addCookie(autoCookie);
                 req.getSession().setAttribute("admin", entity);
                 
             }
             return true;
         }else{
             return false;
         }
        
         
     }
     
     
     private String md5Value(String value) {
         MessageDigest digest;
         try {
             digest = MessageDigest.getInstance("md5");
             byte result[] = digest.digest(value.getBytes());
             BASE64Encoder encoder = new BASE64Encoder();
             return encoder.encode(result);
         } catch (NoSuchAlgorithmException e) {
             
             // TODO Auto-generated catch block e.printStackTrace();
             
         }
         return "";
         
         
         
         // TODO Auto-generated method stub return null;
         
     }
}

