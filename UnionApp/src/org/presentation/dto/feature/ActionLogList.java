package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "actionloglist")
public class ActionLogList {

	private List<ActionLogDTO> actionlogdtoLs;

	public List<ActionLogDTO> getActionlogdtoLs() {

		if (null == this.actionlogdtoLs) {
			actionlogdtoLs = new ArrayList<ActionLogDTO>();
		}
		return actionlogdtoLs;
	}

	public void setActionlogdtoLs(List<ActionLogDTO> actionlogdtoLs) {
		this.actionlogdtoLs = actionlogdtoLs;
	}

}
