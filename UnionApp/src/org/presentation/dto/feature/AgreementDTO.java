package org.presentation.dto.feature;

public class AgreementDTO {
	private String subject;
	private String armdate;
	private String armtime;
	private String detail;
	private String creator;
	private String status;
	private String armid;
	private AttachmentList attachmentlist;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getArmdate() {
		return armdate;
	}
	public void setArmdate(String armdate) {
		this.armdate = armdate;
	}
	public String getArmtime() {
		return armtime;
	}
	public void setArmtime(String armtime) {
		this.armtime = armtime;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getArmid() {
		return armid;
	}
	public void setArmid(String armid) {
		this.armid = armid;
	}
	public AttachmentList getAttachmentlist() {
		if(null==this.attachmentlist){
			this.attachmentlist=new AttachmentList();
		}
		return attachmentlist;
	}
	public void setAttachmentlist(AttachmentList attachmentlist) {
		this.attachmentlist = attachmentlist;
	}	
}
