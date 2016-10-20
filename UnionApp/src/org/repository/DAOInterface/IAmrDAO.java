package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.AmrBO;

public interface IAmrDAO {

	void createAmr(AmrBO AmrBO);
	
	void update(AmrBO nAmrBO);
	
	void deleteOnCriteria(AmrBO AmrBO, Criteria criteriaObj);

	ArrayList<AmrBO> fetchAmr(Criteria criteriaObj, String pageno);
	
	Integer totalRecordCount(Criteria criteriaObj);
	
	ArrayList<AmrBO> fetchAmrById(String id);



}
