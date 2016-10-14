package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.ISuggestionIdeaDAO;
import org.repository.entity.SuggestionIdeaBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SuggestionIdeaDAOImpl implements ISuggestionIdeaDAO {

	@PersistenceContext
	private EntityManager manager;

	public void createSuggestionIdea(SuggestionIdeaBO suggestionIdeaBO) {
		try {
			manager.persist(suggestionIdeaBO);
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(SuggestionIdeaBO suggestionIdeaBO) {
		try {
			/*
			 * String SQL = "update " + UserBO.class.getName() +
			 * " u Set u.status='"+ userBO.getStatus()+"' where usname = '" +
			 * userBO.getUsname() + "'"; Query query = manager.createQuery(SQL);
			 * query.executeUpdate();
			 */

			manager.merge(suggestionIdeaBO);

		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SuggestionIdeaBO> fetchSuggestionIdea(Criteria criteriaObj, String pageno) {

		ArrayList<SuggestionIdeaBO> suggestionIdeaBOList = null;

		/* int pageno = pageno.v */

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
				if (criteriaObj.getFetchSuggestionIdeaCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue()
							&& criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue() + "'";
						}

						String SQL = "select s from " + SuggestionIdeaBO.class.getName() + " s where "
								+ criteriaObj.getFetchSuggestionIdeaCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by s.date asc";

						suggestionIdeaBOList = (ArrayList<SuggestionIdeaBO>) manager.createQuery(SQL)
								.setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				String SQL = "select m from " + SuggestionIdeaBO.class.getName()
						+ " m where status not in ('delete') order by m.actdate asc ";
				suggestionIdeaBOList = (ArrayList<SuggestionIdeaBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return suggestionIdeaBOList;
	}

	public Integer totalRecordCount(Criteria criteriaObj) {

		int count = 0;
		String SQL = "";

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchSuggestionIdeaCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue()
							&& criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchSuggestionIdeaCriteriaObj().getValue() + "'";
						}

						SQL = "select m from " + SuggestionIdeaBO.class.getName() + " m where "
								+ criteriaObj.getFetchSuggestionIdeaCriteriaObj().getName() + " in (" + searchCriteria
								+ ") ";

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				SQL = "select m from " + SuggestionIdeaBO.class.getName() + " m where status not in ('delete') ";
			}

			if (null != manager.createQuery(SQL).getResultList()) {
				count = manager.createQuery(SQL).getResultList().size();
			}
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
	public void deleteOnCriteria(SuggestionIdeaBO suggestionIdeaBO, Criteria criteriaObj) {
		try {

			String SQL = "delete from " + SuggestionIdeaBO.class.getName() + " where activityid = '"
					+ suggestionIdeaBO.getActivityid() + "'";

			Query query = manager.createQuery(SQL);
			query.executeUpdate();

			System.out.println("Done deleteOnCriteria");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}
}
