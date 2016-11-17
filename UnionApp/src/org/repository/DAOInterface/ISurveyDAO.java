package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.SurveyBO;

public interface ISurveyDAO {
	void createSurvey(SurveyBO surveyBOObj);

	void update(SurveyBO surveyBOObj);

	void deleteOnCriteria(SurveyBO SurveyBO, Criteria criteriaObj);

	ArrayList<SurveyBO> fetchSurvey(Criteria criteriaObj, String pageno);

	Integer totalRecordCount(Criteria criteriaObj);

	ArrayList<SurveyBO> fetchSurveyById(String id);

}
