package com.mygudou.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;









import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mygudou.app.daoimpl.AdminDaoimpl;
import com.mygudou.app.daoimpl.FriendDaoImpl;
import com.mygudou.app.model.Admin;
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
    @Resource(name = "FriendDao")
    private FriendDaoImpl FriendDao;
    
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView showLoginForm(HttpServletRequest request,HttpServletResponse response){
    	ModelAndView mv =  new ModelAndView();
    	boolean flag = AdminService.LoginCheck(request, response);
    	if(!flag){
    	    mv.setViewName("showform");
        }
    	else{
    	    Admin admin = (Admin) request.getSession().getAttribute("admin");
    	    String friend_ids = admin.getFriendID();
    	    if(friend_ids != null){
    	    List<Admin> list = FriendDao.getFriendsList(friend_ids);
    	    mv.addObject("friendslist", list);
        }
    	    mv.addObject("admin",admin);
    	    mv.setViewName("main");
    	}
    	    return mv;
      }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ModelAndView submit(HttpServletRequest req,HttpServletResponse resp){
        ModelAndView mv = new ModelAndView();
        boolean flag = AdminService.Loginconf(req, resp);
        
        if(flag){
            
            Admin admin = (Admin) req.getSession().getAttribute("admin");
    		String friend_ids = admin.getFriendID();
    		System.out.println(friend_ids);	
    		if(friend_ids != null){
                    List<Admin> list = FriendDao.getFriendsList(friend_ids);
                    mv.addObject("friendslist", list);
               }
    		mv.addObject("admin",admin);
    		mv.setViewName("main");
    		return mv;
        }else{
            mv.addObject("message", "您输入的的用户名或密码错误");
            mv.setViewName("showform");
        }
        return mv;
       }    
    
    @RequestMapping(value="/signup")
    public String simplesignup(){
    	return "simplesignup";
    }
    
    @ResponseBody
    @RequestMapping(value="/sign",method = RequestMethod.POST)
    public void signup(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){

    	System.out.println(json);
    	JSONObject jsonString = JSON.parseObject(json);
    	String name = jsonString.getString("name");
    	String pass = jsonString.getString("pass");
    	int affectRows = AdminDao.insert(name, pass);
		JSONObject bjson = new JSONObject();
		bjson.put("check", affectRows);
		String bString = bjson.toString();
		
		System.out.println(bString);
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
