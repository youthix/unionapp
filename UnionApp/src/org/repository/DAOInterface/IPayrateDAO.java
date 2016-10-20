package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.PayrateBO;

public interface IPayrateDAO {

	void createPayrate(PayrateBO PayrateBO);
	
	void update(PayrateBO nPayrateBO);
	
	void deleteOnCriteria(PayrateBO PayrateBO, Criteria criteriaObj);

	ArrayList<PayrateBO> fetchPayrate(Criteria criteriaObj, String pageno);
	
	Integer totalRecordCount(Criteria criteriaObj);
	
	ArrayList<PayrateBO> fetchPayrateById(String id);


}
