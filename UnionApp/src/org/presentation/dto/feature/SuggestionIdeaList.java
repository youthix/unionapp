package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "suggestionidealist")
public class SuggestionIdeaList {

	private List<SuggestionIdeaDTO> suggestionideadtoLs;

	public List<SuggestionIdeaDTO> getSuggestionideadtoLs() {
		if (null == this.suggestionideadtoLs) {
			suggestionideadtoLs = new ArrayList<SuggestionIdeaDTO>();
		}
		return suggestionideadtoLs;
	}

	public void setSuggestionideadtoLs(List<SuggestionIdeaDTO> suggestionideadtoLs) {
		this.suggestionideadtoLs = suggestionideadtoLs;
	}

}
