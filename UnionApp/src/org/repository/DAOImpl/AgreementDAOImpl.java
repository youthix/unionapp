package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IAgreementDAO;
import org.repository.entity.AgreementBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AgreementDAOImpl implements IAgreementDAO{


	@PersistenceContext
	private EntityManager manager;

	public void createAgreement(AgreementBO AgreementBO) {
		try {
			System.out.println("In createAgreement");
			manager.persist(AgreementBO);
			System.out.println("Done createAgreement");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(AgreementBO AgreementBO) {
		try {
			System.out.println("InDAOAddUser");
			manager.merge(AgreementBO);

			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<AgreementBO> fetchAgreement(Criteria criteriaObj, String pageno) {

		System.out.println("In fetchAgreement");
		ArrayList<AgreementBO> AgreementBOList = null;

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
				if (criteriaObj.getFetchAgreementCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchAgreementCriteriaObj().getValue()
							&& criteriaObj.getFetchAgreementCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchAgreementCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchAgreementCriteriaObj().getValue().split(",");

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

						String SQL = "select m from " + AgreementBO.class.getName() + " m where "
								+ criteriaObj.getFetchAgreementCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by m.armdate desc";

						AgreementBOList = (ArrayList<AgreementBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				String SQL = "select m from " + AgreementBO.class.getName()
						+ " m where status not in ('delete') order by m.armdate desc ";
				AgreementBOList = (ArrayList<AgreementBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return AgreementBOList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AgreementBO> fetchAgreementById(String id) {

		System.out.println("In fetchAgreementById");
		ArrayList<AgreementBO> AgreementBOList = null;
		
		try {

			String SQL = "select m from " + AgreementBO.class.getName() + " m where armid in (" 
						+ id + ") order by m.armdate desc";
			AgreementBOList = (ArrayList<AgreementBO>) manager.createQuery(SQL).getResultList();			

			
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}
		System.out.println("Done fetchAgreementById");
		return AgreementBOList;
	}

	public Integer totalRecordCount(Criteria criteriaObj) {

		int count = 0;
		String SQL = "";

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchAgreementCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					if (null != criteriaObj.getFetchAgreementCriteriaObj().getValue()
							&& criteriaObj.getFetchAgreementCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchAgreementCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchAgreementCriteriaObj().getValue().split(",");

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

						SQL = "select m from " + AgreementBO.class.getName() + " m where "
								+ criteriaObj.getFetchAgreementCriteriaObj().getName() + " in (" + searchCriteria
								+ ") ";

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				SQL = "select m from " + AgreementBO.class.getName()
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
	public void deleteOnCriteria(AgreementBO AgreementBO, Criteria criteriaObj) {
		try {

			String SQL = "delete from " + AgreementBO.class.getName() + " where armid = '" + AgreementBO.getArmid()
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
