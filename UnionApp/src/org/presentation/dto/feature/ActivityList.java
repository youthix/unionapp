package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "activitylist")
public class ActivityList {

	private List<ActivityDTO> activitydtoLs;

	public List<ActivityDTO> getActivitydtoLs() {

		if (null == this.activitydtoLs) {
			activitydtoLs = new ArrayList<ActivityDTO>();
		}
		return activitydtoLs;
	}

	public void setActivitydtoLs(List<ActivityDTO> activitydtoLs) {
		this.activitydtoLs = activitydtoLs;
	}

}
