package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.ActionLogBO;

public interface IActionLogDAO {

	public void addActionLog(ActionLogBO actionLogBO);

	public ArrayList<ActionLogBO> fetchActionLog();

}
