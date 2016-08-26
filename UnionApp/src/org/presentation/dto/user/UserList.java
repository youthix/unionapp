package org.presentation.dto.user;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userlist")
public class UserList {
	
	private List<User> ul;
	

	public List<User> getUl() {
		if(null==this.ul){
			ul=new ArrayList<User>();
		}
		return ul;
	}

	public void setUl(List<User> ul) {
		this.ul = ul;
	}

	
}
