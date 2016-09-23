package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meetinglist")
public class MeetingList {

	private List<MeetingDTO> meetingdtoLs;

	public List<MeetingDTO> getMeetingdtoLs() {

		if (null == this.meetingdtoLs) {
			meetingdtoLs = new ArrayList<MeetingDTO>();
		}
		return meetingdtoLs;
	}

	public void setMeetingdtoLs(List<MeetingDTO> meetingdtoLs) {
		this.meetingdtoLs = meetingdtoLs;
	}

}
