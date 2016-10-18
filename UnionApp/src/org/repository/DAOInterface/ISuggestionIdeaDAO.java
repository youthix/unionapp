package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.SuggestionIdeaBO;
import org.repository.entity.SummaryBO;

public interface ISuggestionIdeaDAO {

	public void createSuggestionIdea(SuggestionIdeaBO suggestionIdeaBO);
	
	public void update(SuggestionIdeaBO suggestionIdeaBO);

	public void deleteOnCriteria(SuggestionIdeaBO suggestionIdeaBO, Criteria criteriaObj);

	public ArrayList<SuggestionIdeaBO> fetchSuggestionIdea(Criteria criteriaObj, String pageno);
	
	public Integer totalRecordCount(Criteria criteriaObj);
	
	ArrayList<SuggestionIdeaBO> fetchSuggestionIdeaById(String id);
	
}
