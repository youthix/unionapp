package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "amrlist")
public class AmrList {
	private List<AmrDTO> amrdtoLs;

	public List<AmrDTO> getAmrdtoLs() {
		if(null==this.amrdtoLs){
			this.amrdtoLs=new ArrayList<AmrDTO>();
		}
		return amrdtoLs;
	}

	public void setAmrdtoLs(List<AmrDTO> amrdtoLs) {
		this.amrdtoLs = amrdtoLs;
	}
}
