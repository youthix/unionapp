package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "payratelist")
public class PayrateList {
	
	private List<PayrateDTO> payratedtoLs;

	public List<PayrateDTO> getPayratedtoLs() {
		if(null==this.payratedtoLs){
			this.payratedtoLs=new ArrayList<PayrateDTO>();
		}
		return payratedtoLs;
	}

	public void setPayratedtoLs(List<PayrateDTO> payratedtoLs) {
		this.payratedtoLs = payratedtoLs;
	}
	

}
