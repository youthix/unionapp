package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deletefile")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DeleteFile {
	

	private String featureType;

	private String featureId;

	private String fileName;
	
	private String attachmentType;

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}



}
