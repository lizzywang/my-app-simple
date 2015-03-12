

/** * @(#)Admin.java, 2015年2月11日. * * Copyright 2015 Yodao, Inc. All rights reserved. * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. */
 package com.mygudou.app.model; 
 import java.io.Serializable;

/** * * @author xinwang * *  */
public class Admin  {

    private Integer id;
    
	private String name;
    private String pass;
    private String friendID;
    public Admin(){
        super();
    }
    public Admin(Integer id,String name,String pass){
        super();
        this.id = id;
        this.name = name;
        this.pass= pass;
    }
    public String getFriendID() {
		return friendID;
	}
	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    
}

