package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "surveylist")
public class SurveyList {

	private List<SurveyDTO> surveydtoLs;

	public List<SurveyDTO> getSurveydtoLs() {

		if (null == this.surveydtoLs) {
			surveydtoLs = new ArrayList<SurveyDTO>();
		}
		return surveydtoLs;
	}

	public void setSurveydtoLs(List<SurveyDTO> surveydtoLs) {
		this.surveydtoLs = surveydtoLs;
	}

}
