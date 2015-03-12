package com.mygudou.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mygudou.app.daoimpl.AdminDaoimpl;
import com.mygudou.app.daoimpl.FriendDaoImpl;
import com.mygudou.app.model.Admin;
import com.mygudou.app.service.FriendService;

@Controller
@RequestMapping(value = "/friend")
public class FriendController {
    @Resource(name = "FriendService")
    private FriendService FriendService; 
    @Resource(name = "FriendDao")
    private FriendDaoImpl FriendDao;
	@Resource(name = "AdminDao")
    private AdminDaoimpl AdminDao;
	
	
	@RequestMapping(value = "/search")
	public ModelAndView searchFriend(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		List<Admin> list = FriendDao.findFriends(name);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("friendsList");
		return mv;
		
	}
	@RequestMapping(value="/add" )
	public void addFriend(HttpServletRequest request,HttpServletResponse response){
		int friend_id = Integer.valueOf(request.getParameter("id"));
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int id = admin.getId();
		FriendService.SetFriends(id, friend_id);
		FriendService.SetFriends(friend_id,id);
		}
	@RequestMapping(value = "/getList")
	public ModelAndView getFriendsList(HttpServletRequest request,HttpServletResponse response){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int id = admin.getId();
		List<Admin> list = FriendService.getFriendsList(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("friendslist", list);
		mv.setViewName("friends");
		return mv;
			
	}
	
	@RequestMapping(value ="/enterMsg")
	public ModelAndView enterMsg(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		int id = Integer.valueOf(request.getParameter("id"));
		Admin admin = AdminDao.getById(id);
		mv.addObject("admin1", admin);
		mv.setViewName("board");
		return mv;
		
	}

}
