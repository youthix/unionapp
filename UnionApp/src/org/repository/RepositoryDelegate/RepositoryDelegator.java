package org.repository.RepositoryDelegate;

import java.util.ArrayList;
import java.util.Iterator;

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

	// @Cacheable(cacheName = "fetchPagesCache", keyGenerator =
	// @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties =
	// @Property(name = "includeMethod", value = "false") ) )

	public UserBO login(UserList userListObj) {
		System.out.println("InRDLogin");

		UserBO userBOObj ;
		
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
		userBOObj.setStatus(userObj.getStatus());
		userBOObj.setuId(userObj.getuId());

	}

	public UserList fetch(UserList reqparam) {
		System.out.println("InRDFetch");
		return null;
	}

}
