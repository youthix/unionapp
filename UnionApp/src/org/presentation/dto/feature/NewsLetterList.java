package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "activitylist")
public class NewsLetterList {
	
	private List<NewsLetterDTO> newsletterdtoLs;

	public List<NewsLetterDTO> getNewsletterdtoLs() {
		if (null == this.newsletterdtoLs) {
			newsletterdtoLs = new ArrayList<NewsLetterDTO>();
		}
		return newsletterdtoLs;
	}

	public void setNewsletterdtoLs(List<NewsLetterDTO> newsletterdtoLs) {
		this.newsletterdtoLs = newsletterdtoLs;
	}
	
}
