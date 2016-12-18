package org.repository.DAOInterface;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.repository.entity.ActionLogBO;

public interface IActionLogDAO {

	void addActionLog(ActionLogBO actionLogBO);

	ArrayList<ActionLogBO> fetchActionLog();
	
	BigDecimal fetchDbSize(String dbName);

}
