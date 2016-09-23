package org.presentation.controller.Impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.presentation.controller.Interface.RESTfulServiceInterface;
import org.presentation.dto.RequestObj;
import org.presentation.dto.ResStatus;
import org.presentation.dto.ResponseObj;
import org.presentation.util.ServiceException;
import org.presentation.util.ServiceExceptionMapper;
import org.service.delegateService.ServiceDelegator;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/service")
public class RESTfulServiceImpl implements RESTfulServiceInterface {

	@Autowired
	private ServiceDelegator serviceDelegator;

	@Override
	@POST
	@Path("/register")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj register(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.register(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/login")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj login(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.login(reqparam);
			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetch")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetch(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetch(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/update")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj update(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.update(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatepwd")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatepwd(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatepwd(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/resetpwd")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj resetpwd(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.resetpwd(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createmeeting")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createmeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createmeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatemeeting")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatemeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatemeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchmeeting")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchmeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchmeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/acceptdenymeeting")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj acceptdenymeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.acceptdenymeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	public ServiceDelegator getServiceDelegator() {
		return serviceDelegator;
	}

	public void setServiceDelegator(ServiceDelegator serviceDelegator) {
		this.serviceDelegator = serviceDelegator;
	}

}
