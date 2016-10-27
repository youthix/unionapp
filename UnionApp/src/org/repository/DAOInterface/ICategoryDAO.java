package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.CategoryBO;
import org.repository.entity.UserBO;

public interface ICategoryDAO {

	public void addCategory(CategoryBO categoryBO);

	public ArrayList<CategoryBO> fetchCategory(Criteria criteriaObj);

}
