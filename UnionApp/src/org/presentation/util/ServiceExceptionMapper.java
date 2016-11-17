package org.presentation.util;

import org.presentation.dto.ResStatus;
import org.presentation.dto.ResponseObj;

/**
 * The utility class is used to map the Exception instances to the particular
 * functions and to accordingly set the require values.
 */

public class ServiceExceptionMapper {

	public static ResponseObj toResponse(Exception exceptionObj) {
		ResponseObj respObj = new ResponseObj();
		ResStatus resStatusObj = new ResStatus();
		resStatusObj.setStatus("FAILURE");
		resStatusObj.setCode("01");
		if (exceptionObj instanceof ServiceException) {

			resStatusObj.setMsg(exceptionObj.getMessage());

		} else {
			exceptionObj.printStackTrace();

			resStatusObj.setMsg(ServiceConstant.GENERIC_ERROR);

		}

		respObj.setResStatus(resStatusObj);
		return respObj;

	}

}
