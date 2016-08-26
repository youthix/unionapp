package org.service.delegateService;

import java.util.ArrayList;

import org.presentation.dto.RequestObj;
import org.presentation.dto.ResStatus;
import org.presentation.dto.ResponseObj;
import org.presentation.dto.user.User;
import org.presentation.dto.user.UserList;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.ITestDAO;
import org.repository.RepositoryDelegate.RepositoryDelegator;
import org.repository.entity.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

public class ServiceDelegator {

	@Autowired
	private RepositoryDelegator repositoryDelegator;
	private CacheManager cacheManager;
	UserList res;

	ResStatus resStatus;

/*	@Autowired
	ITestDAO dao;*/

	public ResponseObj login(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			UserBO userBOObj = repositoryDelegator.login(userListObj);

			ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

			User userObj = userList.get(0);

			if ((userBOObj.getUsname().equalsIgnoreCase(userObj.getUsNa()))
					&& (userBOObj.getPwd().equalsIgnoreCase(userObj.getPwd()))) {
				setResponse(responseObj);
			}
			else {
				ServiceException serviceExceptionObj = new ServiceException("Credentials Incorrect");
				throw serviceExceptionObj;
			}
			responseObj.setUserListObj(userListObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj register(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			repositoryDelegator.register(userListObj);
			responseObj.setUserListObj(userListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetch(RequestObj reqparam) {

		return null;
	}

	public void hello() {
		System.out.println("In Service");
	}

	public ResponseObj Testregister(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		// repositoryDelegator.register(reqparam);

		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			repositoryDelegator.register(userListObj);
			responseObj.setUserListObj(userListObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		/*
		 * System.out.println("InAddUser"); UserBO userBOObj = new UserBO();
		 * userBOObj.setUsname("TestUser"); userBOObj.setPwd("TestPwd");
		 * dao.addUser(userBOObj);
		 * 
		 * System.out.println("doneAddUser");
		 */
		return responseObj;
	}

/*	public ITestDAO getDao() {
		return dao;
	}

	public void setDao(ITestDAO dao) {
		this.dao = dao;
	}*/

	private void setResponse(ResponseObj responseObj) {
		ResStatus resStatus = new ResStatus();
		resStatus.setCode("00");
		resStatus.setMsg("SUCCESS");
		responseObj.setResStatus(resStatus);
	}
}
