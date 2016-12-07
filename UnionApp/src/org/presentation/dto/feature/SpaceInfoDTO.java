package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "spaceinfodto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SpaceInfoDTO {

	private String totalspace;

	private String usedspace;

	private String remspace;
	
	private String unit;

	public String getTotalspace() {
		return totalspace;
	}

	public void setTotalspace(String totalspace) {
		this.totalspace = totalspace;
	}

	public String getUsedspace() {
		return usedspace;
	}

	public void setUsedspace(String usedspace) {
		this.usedspace = usedspace;
	}

	public String getRemspace() {
		return remspace;
	}

	public void setRemspace(String remspace) {
		this.remspace = remspace;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}
