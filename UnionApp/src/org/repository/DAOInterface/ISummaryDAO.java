package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.SummaryBO;

public interface ISummaryDAO {
	
	void createSummary(SummaryBO SummaryBO);
	
	void update(SummaryBO nSummaryBO);
	
	void deleteOnCriteria(SummaryBO SummaryBO, Criteria criteriaObj);

	ArrayList<SummaryBO> fetchSummary(Criteria criteriaObj, String pageno);
	
	Integer totalRecordCount(Criteria criteriaObj);
	
	ArrayList<SummaryBO> fetchSummaryById(String id);

}
