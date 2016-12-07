package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "visitorinfolist")
public class VisitorInfoList {

	private List<VisitorInfoDTO> visitorinfodtoLs;

	public List<VisitorInfoDTO> getVisitorinfodtoLs() {

		if (null == this.visitorinfodtoLs) {
			visitorinfodtoLs = new ArrayList<VisitorInfoDTO>();
		}
		return visitorinfodtoLs;
	}

	public void setVisitorinfodtoLs(List<VisitorInfoDTO> visitorinfodtoLs) {
		this.visitorinfodtoLs = visitorinfodtoLs;
	}

}
