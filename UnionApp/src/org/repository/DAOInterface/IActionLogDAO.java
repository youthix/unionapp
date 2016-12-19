package org.repository.DAOInterface;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.repository.entity.ActionLogBO;
import org.repository.entity.ActiveUserBO;

public interface IActionLogDAO {

	void addActionLog(ActionLogBO actionLogBO);

	ArrayList<ActionLogBO> fetchActionLog();
	
	BigDecimal fetchDbSize(String dbName);
	
	ArrayList<ActiveUserBO> fetchActiveUser(ActiveUserBO a);
	
	void addActiveUser(ActiveUserBO activeUserBO);
	
	void updateActiveUser(ActiveUserBO activeUserBO);

}
