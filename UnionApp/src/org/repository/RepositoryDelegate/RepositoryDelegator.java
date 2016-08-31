package org.repository.RepositoryDelegate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

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
			ServiceException serviceExceptionObj = new ServiceException("UserDetails Empty. Check and Resend");
			throw serviceExceptionObj;
		}

		return userBOObj;
	}

	public UserList fetch(Criteria criteriaObj) {
		System.out.println("InRDFetch");

		UserList userListObj = new UserList();
		ArrayList<User> userDTOList = new ArrayList<User>();

		ArrayList<UserBO> userBOList;

		userBOList = userdao.fetchUser(criteriaObj);

		if (null != userBOList && userBOList.size() > 0) {

			Iterator<UserBO> litr = userBOList.iterator();

			while (litr.hasNext()) {
				User userDTOObj = new User();
				populateUserDTO(userDTOObj, litr.next());
				userDTOList.add(userDTOObj);

			}

			userListObj.setUl(userDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
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

	private void populateUserDTO(User userObj, UserBO userBOObj) {

		userObj.setAdd(userBOObj.getAdd());
		userObj.setAge(userBOObj.getAge());
		userObj.setConNu(userBOObj.getConNu());
		userObj.setEmId(userBOObj.getEmailid());
		userObj.setFn(userBOObj.getFn());
		userObj.setGen(userBOObj.getGen());
		userObj.setJoinDt(userBOObj.getJoindt());
		userObj.setLn(userBOObj.getLn());
		userObj.setPwd(userBOObj.getPwd());
		userObj.setRole(userBOObj.getRole());
		userObj.setStatus(userBOObj.getStatus());
		userObj.setUsNa(userBOObj.getUsname());

	}

}
