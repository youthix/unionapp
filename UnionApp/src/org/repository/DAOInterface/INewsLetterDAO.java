package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.NewsLetterBO;

public interface INewsLetterDAO {

	void createNewsLetter(NewsLetterBO newsLetterBO);
	
	void update(NewsLetterBO nnewsLetterBO);

	void updateOnCriteria(NewsLetterBO newsLetterBO, Criteria criteriaObj);
	
	void deleteOnCriteria(NewsLetterBO newsLetterBO, Criteria criteriaObj);

	ArrayList<NewsLetterBO> fetchNewsLetter(Criteria criteriaObj, String pageno);

}