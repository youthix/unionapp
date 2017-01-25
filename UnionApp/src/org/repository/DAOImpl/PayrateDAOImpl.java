package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IPayrateDAO;
import org.repository.entity.PayrateBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PayrateDAOImpl implements IPayrateDAO{


	@PersistenceContext
	private EntityManager manager;

	public void createPayrate(PayrateBO PayrateBO) {
		try {
			System.out.println("In createPayrate");
			manager.persist(PayrateBO);
			System.out.println("Done createPayrate");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(PayrateBO PayrateBO) {
		try {
			System.out.println("InDAOAddUser");

			/*
			 * String SQL = "update " + UserBO.class.getName() +
			 * " u Set u.status='"+ userBO.getStatus()+"' where usname = '" +
			 * userBO.getUsname() + "'"; Query query = manager.createQuery(SQL);
			 * query.executeUpdate();
			 */

			manager.merge(PayrateBO);

			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<PayrateBO> fetchPayrate(Criteria criteriaObj, String pageno) {

		System.out.println("In fetchPayrate");
		ArrayList<PayrateBO> PayrateBOList = null;

		int offsetno;

		int pageSize = 6;

		int pageNo = Integer.parseInt(pageno);

		if (null != pageno && pageno != "" && pageNo > 1) {

			offsetno = (pageNo - 1) * pageSize;
		} else {
			offsetno = 0;

		}

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchPayrateCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchPayrateCriteriaObj().getValue()
							&& criteriaObj.getFetchPayrateCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchPayrateCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchPayrateCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchPayrateCriteriaObj().getValue() + "'";
						}

						String SQL = "select m from " + PayrateBO.class.getName() + " m where "
								+ criteriaObj.getFetchPayrateCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by m.paydate desc";

						PayrateBOList = (ArrayList<PayrateBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				String SQL = "select m from " + PayrateBO.class.getName()
						+ " m where status not in ('delete') order by m.paydate desc ";
				PayrateBOList = (ArrayList<PayrateBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return PayrateBOList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PayrateBO> fetchPayrateById(String id) {

		System.out.println("In fetchPayrateById");
		ArrayList<PayrateBO> PayrateBOList = null;
		
		try {

			String SQL = "select m from " + PayrateBO.class.getName() + " m where payid in (" 
						+ id + ") order by m.paydate desc";
			PayrateBOList = (ArrayList<PayrateBO>) manager.createQuery(SQL).getResultList();			

			
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}
		System.out.println("Done fetchPayrateById");
		return PayrateBOList;
	}

	public Integer totalRecordCount(Criteria criteriaObj) {

		int count = 0;
		String SQL = "";

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchPayrateCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchPayrateCriteriaObj().getValue()
							&& criteriaObj.getFetchPayrateCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchPayrateCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchPayrateCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchPayrateCriteriaObj().getValue() + "'";
						}

						SQL = "select m from " + PayrateBO.class.getName() + " m where "
								+ criteriaObj.getFetchPayrateCriteriaObj().getName() + " in (" + searchCriteria
								+ ") ";

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				SQL = "select m from " + PayrateBO.class.getName()
						+ " m where status not in ('delete') ";

			}

			if (null != manager.createQuery(SQL).getResultList()) {
				count = manager.createQuery(SQL).getResultList().size();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return count;

	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void deleteOnCriteria(PayrateBO PayrateBO, Criteria criteriaObj) {
		try {

			String SQL = "delete from " + PayrateBO.class.getName() + " where payid = '" + PayrateBO.getPayid()
					+ "'";

			Query query = manager.createQuery(SQL);
			query.executeUpdate();

			System.out.println("Done deleteOnCriteria");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}


}
