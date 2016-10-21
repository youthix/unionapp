package org.presentation.dto.feature;

public class PayrateDTO {
	private String subject;
	private String paydate;
	private String paytime;
	private String detail;
	private String creator;
	private String status;
	private String payid;
	private AttachmentList attachmentlist;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
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
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public AttachmentList getAttachmentlist() {
		if(null==this.getAttachmentlist())
		{
			this.attachmentlist=new AttachmentList();
		}
		return attachmentlist;
	}
	public void setAttachmentlist(AttachmentList attachmentlist) {
		this.attachmentlist = attachmentlist;
	}
}
