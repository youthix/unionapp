package org.repository.RepositoryDelegate;

import java.util.ArrayList;
import java.util.Iterator;

import org.presentation.dto.fetchcriteria.Criteria;
import org.presentation.dto.user.User;
import org.presentation.dto.user.UserList;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IUserDAO;
import org.repository.entity.UserBO;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryDelegator {

	/*
	 * @Autowired ITestDAO dao;
	 */

	@Autowired
	IUserDAO userdao;

	public UserList register(UserList userListObj) {
		System.out.println("InRDRegister");

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		if (userList.size() > 0) {
			Iterator<User> userListIterator = userList.iterator();

			while (userListIterator.hasNext()) {

				User userObj = userListIterator.next();

				UserBO userBOObj = new UserBO();

				populateUserBO(userObj, userBOObj);
				userdao.addUser(userBOObj);
				userObj.setuId(userBOObj.getId().toString());
			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return userListObj;
	}

	public UserBO login(UserList userListObj) {
		System.out.println("InRDLogin");

		UserBO userBOObj = null;

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		if (userList.size() > 0) {

			User userObj = userList.get(0);

			userBOObj = userdao.fetchUserByParam(userObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return userBOObj;
	}

	public UserList fetch(Criteria criteriaObj) {
		System.out.println("InRDLogin");

		UserBO userBOObj;

		UserList userListObj = null;

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		if (userList.size() > 0) {

			User userObj = userList.get(0);

			userBOObj = userdao.fetchUserByParam(userObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return userListObj;
	}

	private void populateUserBO(User userObj, UserBO userBOObj) {
		userBOObj.setUsname(userObj.getUsNa());
		userBOObj.setPwd(userObj.getPwd());
		userBOObj.setAdd(userObj.getAdd());
		userBOObj.setAge(userObj.getAge());
		userBOObj.setConNu(userObj.getConNu());
		userBOObj.setEmailid(userObj.getEmId());
		userBOObj.setFn(userObj.getFn());
		userBOObj.setGen(userObj.getGen());
		userBOObj.setJoindt(userObj.getJoinDt());
		userBOObj.setLn(userObj.getLn());
		if (null == userObj.getStatus() || userObj.getStatus().equals("")) {
			userBOObj.setStatus("P");
		} else {
			userBOObj.setStatus(userObj.getStatus());
		}
		if (null == userObj.getRole() || userObj.getRole().equals("")) {
			userBOObj.setRole("N");
		} else {
			userBOObj.setRole(userObj.getRole());
		}
		userBOObj.setuId(userObj.getuId());

	}

}
