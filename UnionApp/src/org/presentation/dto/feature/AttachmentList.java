package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attachmentlist")
public class AttachmentList {
	private List<AttachmentDTO> attachmentdtoLs;
	private int listSize;

	public List<AttachmentDTO> getAttachmentdtoLs() {
		if(null==this.attachmentdtoLs)
			this.attachmentdtoLs=new ArrayList<AttachmentDTO>();
		return attachmentdtoLs;
	}

	public void setAttachmentdtoLs(List<AttachmentDTO> attachmentdtoLs) {
		this.attachmentdtoLs = attachmentdtoLs;
	}

	public int getListSize() {
		listSize=this.getAttachmentdtoLs().size();
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	
}
