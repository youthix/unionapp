package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IAmrDAO;
import org.repository.entity.AmrBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AmrDAOImpl implements IAmrDAO{


	@PersistenceContext
	private EntityManager manager;

	public void createAmr(AmrBO AmrBO) {
		try {
			System.out.println("In createAmr");
			manager.persist(AmrBO);
			System.out.println("Done createAmr");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(AmrBO AmrBO) {
		try {
			System.out.println("InDAOAddUser");

			/*
			 * String SQL = "update " + UserBO.class.getName() +
			 * " u Set u.status='"+ userBO.getStatus()+"' where usname = '" +
			 * userBO.getUsname() + "'"; Query query = manager.createQuery(SQL);
			 * query.executeUpdate();
			 */

			manager.merge(AmrBO);

			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<AmrBO> fetchAmr(Criteria criteriaObj, String pageno) {

		System.out.println("In fetchAmr");
		ArrayList<AmrBO> AmrBOList = null;

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
				if (criteriaObj.getFetchAmrCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchAmrCriteriaObj().getValue()
							&& criteriaObj.getFetchAmrCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchAmrCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchAmrCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchAmrCriteriaObj().getValue() + "'";
						}

						String SQL = "select m from " + AmrBO.class.getName() + " m where "
								+ criteriaObj.getFetchAmrCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by m.amrdate asc";

						AmrBOList = (ArrayList<AmrBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				String SQL = "select m from " + AmrBO.class.getName()
						+ " m where status not in ('delete') order by m.amrdate asc ";
				AmrBOList = (ArrayList<AmrBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return AmrBOList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AmrBO> fetchAmrById(String id) {

		System.out.println("In fetchAmrById");
		ArrayList<AmrBO> AmrBOList = null;
		
		try {

			String SQL = "select m from " + AmrBO.class.getName() + " m where amrid in (" 
						+ id + ") order by m.amrdate asc";
			AmrBOList = (ArrayList<AmrBO>) manager.createQuery(SQL).getResultList();			

			
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}
		System.out.println("Done fetchAmrById");
		return AmrBOList;
	}

	public Integer totalRecordCount(Criteria criteriaObj) {

		int count = 0;
		String SQL = "";

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchAmrCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchAmrCriteriaObj().getValue()
							&& criteriaObj.getFetchAmrCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchAmrCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchAmrCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchAmrCriteriaObj().getValue() + "'";
						}

						SQL = "select m from " + AmrBO.class.getName() + " m where "
								+ criteriaObj.getFetchAmrCriteriaObj().getName() + " in (" + searchCriteria
								+ ") ";

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				SQL = "select m from " + AmrBO.class.getName()
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
	public void deleteOnCriteria(AmrBO AmrBO, Criteria criteriaObj) {
		try {

			String SQL = "delete from " + AmrBO.class.getName() + " where amrid = '" + AmrBO.getAmrid()
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
