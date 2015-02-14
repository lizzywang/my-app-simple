package com.mygudou.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import scala.util.parsing.json.JSON;

import com.mygudou.app.daoimpl.AdminDaoimpl;
import com.mygudou.app.service.AdminService;
/**
 * 首页登录
 */
@Controller
@RequestMapping(value = "/simple")
public class IndexController {
    
    @Resource(name = "AdminService")
    private AdminService AdminService;  
	@Resource(name = "AdminDao")
    private AdminDaoimpl AdminDao;
    
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView showLoginForm(HttpServletRequest request,HttpServletResponse response){
    	ModelAndView mv =  new ModelAndView();
    	boolean flag = AdminService.LoginCheck(request, response);
    	
    	if(!flag){
    		mv.setViewName("showform");
        }
    	else{
    		
    		mv.addObject("message", "登录成功");
    		mv.setViewName("success");
    	}
    	return mv;
      }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ModelAndView submit(HttpServletRequest req,HttpServletResponse resp){
        ModelAndView mv = new ModelAndView();
        boolean flag = AdminService.Loginconf(req, resp);
        if(flag){
            mv.addObject("message", "登录成功");
            mv.setViewName("success");
        }else{
            mv.addObject("message", "您输入的的用户名或密码错误");
            mv.setViewName("showform");
        }
        return mv;
       }    
    
    @RequestMapping(value="/signup" ,method = RequestMethod.GET)
    public String simplesignup(){
    	return "simplesignup";
    }
    
	
    @RequestMapping(value="/signup" ,method = RequestMethod.POST)
    public void signup(String name,String pass,HttpServletResponse response){
    	
    	int affectRows = AdminDao.insert(name, pass);
		JSONObject bjson = new JSONObject();
		bjson.put("check", affectRows);
		String bString = bjson.toString();
		PrintWriter out = null;
		 response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        try {
	            out = response.getWriter();
	            out.write(bString);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
         
    	
    }
    
}
