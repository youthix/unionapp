package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agreementlist")
public class AgreementList {
	private List<AgreementDTO> agreementdtoLs;

	public List<AgreementDTO> getAgreementdtoLs() {
		if(null==this.agreementdtoLs)
			this.agreementdtoLs=new ArrayList<AgreementDTO>();
		return agreementdtoLs;
	}

	public void setAgreementdtoLs(List<AgreementDTO> agreementdtoLs) {
		this.agreementdtoLs = agreementdtoLs;
	}
}
