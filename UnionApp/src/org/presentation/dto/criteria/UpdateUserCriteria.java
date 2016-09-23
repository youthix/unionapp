package org.presentation.dto.criteria;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "updateusercriteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UpdateUserCriteria {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	

}
