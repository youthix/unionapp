package org.presentation.dto.criteria;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "fetchagreementcriteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FetchAgreementCriteria {
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}