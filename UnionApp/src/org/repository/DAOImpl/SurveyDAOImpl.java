package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.ISurveyDAO;
import org.repository.entity.AgreementBO;
import org.repository.entity.SurveyBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SurveyDAOImpl implements ISurveyDAO {

	@PersistenceContext
	private EntityManager manager;

	public void createSurvey(SurveyBO surveyBOObj) {
		try {
			System.out.println("In createSurvey");
			manager.persist(surveyBOObj);
			System.out.println("Done createSurvey");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(SurveyBO surveyBOObj) {
		try {

			manager.merge(surveyBOObj);

		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SurveyBO> fetchSurvey(Criteria criteriaObj, String pageno) {

		System.out.println("In fetchSurvey");
		ArrayList<SurveyBO> surveyBOList = null;

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
				if (criteriaObj.getFetchSurveyCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchSurveyCriteriaObj().getValue()
							&& criteriaObj.getFetchSurveyCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchSurveyCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchSurveyCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchSurveyCriteriaObj().getValue() + "'";
						}

						String SQL = "select m from " + SurveyBO.class.getName() + " m where "
								+ criteriaObj.getFetchSurveyCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by m.surveyid asc";

						surveyBOList = (ArrayList<SurveyBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				/*
				 * String SQL = "select m from " + SurveyBO.class.getName() +
				 * " m where status not in ('delete') order by m.surveyid asc ";
				 */
				String SQL = "select m from " + SurveyBO.class.getName() + " m order by m.surveyid asc ";
				surveyBOList = (ArrayList<SurveyBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return surveyBOList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SurveyBO> fetchSurveyById(String id) {

		ArrayList<SurveyBO> surveyBOListObj = null;

		try {

			String SQL = "select m from " + SurveyBO.class.getName() + " m where surveyid in (" + id
					+ ") order by m.surveyid asc";
			surveyBOListObj = (ArrayList<SurveyBO>) manager.createQuery(SQL).getResultList();

		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return surveyBOListObj;
	}

	public Integer totalRecordCount(Criteria criteriaObj) {

		int count = 0;
		String SQL = "";

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchSurveyCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchSurveyCriteriaObj().getValue()
							&& criteriaObj.getFetchSurveyCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchSurveyCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchSurveyCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchAgreementCriteriaObj().getValue() + "'";
						}

						SQL = "select m from " + SurveyBO.class.getName() + " m where "
								+ criteriaObj.getFetchSurveyCriteriaObj().getName() + " in (" + searchCriteria + ") ";

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				SQL = "select m from " + SurveyBO.class.getName() +" m";

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
	public void deleteOnCriteria(SurveyBO surveyBO, Criteria criteriaObj) {
		try {

			String SQL = "delete from " + SurveyBO.class.getName() + " where surveyid = '" + surveyBO.getSurveyid()
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
