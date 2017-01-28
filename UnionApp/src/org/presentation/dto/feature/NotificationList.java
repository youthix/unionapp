package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notificationlist")
public class NotificationList {
	private List<NotificationDTO> notificationdtoLs;

	public List<NotificationDTO> getNotificationdtoLs() {
		if (null == this.notificationdtoLs) {
			this.notificationdtoLs = new ArrayList<NotificationDTO>();
		}
		return notificationdtoLs;
	}

	public void setNotificationdtoLs(List<NotificationDTO> notificationdtoLs) {
		this.notificationdtoLs = notificationdtoLs;
	}

}
