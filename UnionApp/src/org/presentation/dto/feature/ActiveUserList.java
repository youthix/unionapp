package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "acctiveuserlist")
public class ActiveUserList {

	private List<ActiveUserDTO> activeuserdtoLs;

	public List<ActiveUserDTO> getActiveuserdtoLs() {
		if (null == this.activeuserdtoLs) {
			activeuserdtoLs = new ArrayList<ActiveUserDTO>();
		}
		return activeuserdtoLs;
	}

	public void setActiveuserdtoLs(List<ActiveUserDTO> activeuserdtoLs) {
		this.activeuserdtoLs = activeuserdtoLs;
	}
	
}
