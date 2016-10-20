package org.presentation.dto.feature;

public class AttachmentDTO {
	private String url;
	private String title;
	private String type;
	
	public AttachmentDTO(String title,String url,String type){
		this.url=url;
		this.title=title;
		this.type=type;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
