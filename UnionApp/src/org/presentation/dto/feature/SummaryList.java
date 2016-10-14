package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "summarylist")
public class SummaryList {
	private List<SummaryDTO> summarydtoLs;

	public List<SummaryDTO> getSummarydtoLs() {
		if (null == this.summarydtoLs) {
			summarydtoLs = new ArrayList<SummaryDTO>();
		}
		return summarydtoLs;
	}

	public void setSummarydtoLs(List<SummaryDTO> summarydtoLs) {
		this.summarydtoLs = summarydtoLs;
	}
	
}
