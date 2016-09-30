package org.presentation.dto.criteria;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "updatemeetingcriteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UpdateActivityCriteria {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	

}
