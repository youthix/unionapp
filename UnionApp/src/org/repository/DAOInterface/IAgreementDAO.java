package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.AgreementBO;

public interface IAgreementDAO {
	void createAgreement(AgreementBO AgreementBO);
	
	void update(AgreementBO nAgreementBO);
	
	void deleteOnCriteria(AgreementBO AgreementBO, Criteria criteriaObj);

	ArrayList<AgreementBO> fetchAgreement(Criteria criteriaObj, String pageno);
	
	Integer totalRecordCount(Criteria criteriaObj);
	
	ArrayList<AgreementBO> fetchAgreementById(String id);


}
