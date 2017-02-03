package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.common.UnionAppMsgConstants;
import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.ICategoryDAO;
import org.repository.entity.CategoryBO;
import org.repository.entity.UserBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryDAOImpl implements ICategoryDAO {

	@PersistenceContext
	private EntityManager manager;

	public void addCategory(CategoryBO categoryBO) {
		try {

			manager.persist(categoryBO);
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public ArrayList<CategoryBO> fetchCategory(Criteria criteriaObj) {

		ArrayList<CategoryBO> categoryBOList = null;

		try {

			String SQL = "select u from " + CategoryBO.class.getName() + " u";
			categoryBOList = (ArrayList<CategoryBO>) manager.createQuery(SQL).getResultList();

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT + e.getMessage());
			throw serviceExceptionObj;
		}

		return categoryBOList;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
}
