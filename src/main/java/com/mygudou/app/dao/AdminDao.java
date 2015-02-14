

/** * @(#)AdminDao.java, 2015年2月11日. * * Copyright 2015 Yodao, Inc. All rights reserved. * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. */
 package com.mygudou.app.dao;
 import com.mygudou.app.model.Admin;

/** * * @author xinwang * *  */
public interface AdminDao {
   
        public Admin checkLogin(String name,String pass);
        public Admin checkLogin(String name);
        public int insert(String name,String pass);



}

