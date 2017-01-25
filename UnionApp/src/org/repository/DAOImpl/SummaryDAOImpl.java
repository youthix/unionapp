package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.ISummaryDAO;
import org.repository.entity.SummaryBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SummaryDAOImpl implements ISummaryDAO {

	@PersistenceContext
	private EntityManager manager;

	public void createSummary(SummaryBO SummaryBO) {
		try {
			System.out.println("In createSummary");
			manager.persist(SummaryBO);
			System.out.println("Done createSummary");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(SummaryBO SummaryBO) {
		try {
			System.out.println("In update");

			/*
			 * String SQL = "update " + UserBO.class.getName() +
			 * " u Set u.status='"+ userBO.getStatus()+"' where usname = '" +
			 * userBO.getUsname() + "'"; Query query = manager.createQuery(SQL);
			 * query.executeUpdate();
			 */

			manager.merge(SummaryBO);

			System.out.println("Done update");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SummaryBO> fetchSummary(Criteria criteriaObj, String pageno) {

		System.out.println("In fetchSummary");
		ArrayList<SummaryBO> SummaryBOList = null;

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
				if (criteriaObj.getFetchSummaryCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchSummaryCriteriaObj().getValue()
							&& criteriaObj.getFetchSummaryCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchSummaryCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchSummaryCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchSummaryCriteriaObj().getValue() + "'";
						}

						String SQL = "select m from " + SummaryBO.class.getName() + " m where "
								+ criteriaObj.getFetchSummaryCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by m.sumdate desc";

						SummaryBOList = (ArrayList<SummaryBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				String SQL = "select m from " + SummaryBO.class.getName()
						+ " m where status not in ('delete') order by m.sumdate desc";
				SummaryBOList = (ArrayList<SummaryBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchSummary");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return SummaryBOList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SummaryBO> fetchSummaryById(String id) {

		System.out.println("In fetchSummaryById");
		ArrayList<SummaryBO> SummaryBOList = null;
		
		try {

			String SQL = "select m from " + SummaryBO.class.getName() + " m where sumid in (" 
						+ id + ") order by m.sumdate desc";
			SummaryBOList = (ArrayList<SummaryBO>) manager.createQuery(SQL).getResultList();			

			
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}
		System.out.println("Done fetchSummaryById");
		return SummaryBOList;
	}

	public Integer totalRecordCount(Criteria criteriaObj) {

		int count = 0;
		String SQL = "";

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchSummaryCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchSummaryCriteriaObj().getValue()
							&& criteriaObj.getFetchSummaryCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchSummaryCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchSummaryCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchSummaryCriteriaObj().getValue() + "'";
						}

						SQL = "select m from " + SummaryBO.class.getName() + " m where "
								+ criteriaObj.getFetchSummaryCriteriaObj().getName() + " in (" + searchCriteria
								+ ") ";

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				SQL = "select m from " + SummaryBO.class.getName()
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
	public void deleteOnCriteria(SummaryBO SummaryBO, Criteria criteriaObj) {
		try {

			String SQL = "delete from " + SummaryBO.class.getName() + " where sumid = '" + SummaryBO.getSumid()
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
