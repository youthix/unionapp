package org.presentation.controller.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.common.UnionAppConstants;
import org.common.UnionAppMsgConstants;
import org.presentation.controller.Interface.RESTfulServiceInterface;
import org.presentation.dto.RequestObj;
import org.presentation.dto.ResStatus;
import org.presentation.dto.ResponseObj;
import org.presentation.dto.feature.OptionDTO;
import org.presentation.dto.feature.QuestionDTO;
import org.presentation.dto.feature.SurveyDTO;
import org.presentation.dto.feature.SurveyList;
import org.presentation.util.ServiceException;
import org.presentation.util.ServiceExceptionMapper;
import org.service.delegateService.ServiceDelegator;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/service")
public class RESTfulServiceImpl implements RESTfulServiceInterface {

	@Autowired
	private ServiceDelegator serviceDelegator;

	@Override
	@POST
	@Path("/register")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj register(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.register(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj login(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.login(reqparam);
			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/logout")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj logout(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.logout(reqparam);
			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetch(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetch(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchalluser")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchAllUser(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchAllUser(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj update(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.update(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updateuserprofile")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateuserprofile(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateuserprofile(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatepwd(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatepwd(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj resetpwd(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.resetpwd(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createmeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createmeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatemeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatemeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchmeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchmeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj acceptdenymeeting(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.acceptdenymeeting(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createactivity(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updateactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateactivity(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchactivity(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/acceptdenyactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj acceptdenyactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.acceptdenyactivity(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createnewsletter")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createNewsLetter(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatenewsletter")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateNewsLetter(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@GET
	@Path("/fetchnewsletter/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchNewsLetterById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchNewsLetterById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj).toString();
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchnewsletter")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchNewsLetter(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createpayrate")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createPayrate(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createPayrate(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatepayrate")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatePayrate(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatePayrate(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@GET
	@Path("/fetchpayrate/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchPayrateById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchPayrateById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj).toString();
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchpayrate")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchPayrate(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchPayrate(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createamr")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createAmr(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createAmr(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updateamr")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateAmr(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateAmr(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@GET
	@Path("/fetchamr/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchAmrById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchAmrById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj).toString();
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchamr")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchAmr(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchAmr(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createagreement")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createAgreement(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createAgreement(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updateagreement")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateAgreement(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateAgreement(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@GET
	@Path("/fetchagreement/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchAgreementById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchAgreementById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj).toString();
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchagreement")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchAgreement(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchAgreement(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createsummary")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createSummary(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createSummary(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatesummary")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateSummary(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateSummary(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@GET
	@Path("/fetchsummary/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchSummaryById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchSummaryById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj).toString();
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchsummary")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchSummary(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchSummary(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/createsuggestionidea")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createsuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createsuggestionidea(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatesuggestionidea")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatesuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatesuggestionidea(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchsuggestionidea")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchsuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchsuggestionidea(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@GET
	@Path("/fetchsuggestionidea/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchsuggestionideaById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchsuggestionideaById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj).toString();
		}

		return responseObj;

	}

	// @Override
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ResponseObj uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@HeaderParam(value = "featureType") String featureType, @HeaderParam(value = "featureId") String featureId,
			@HeaderParam(value = "attachmentType") String attachmentType,
			@HeaderParam(value = "attachmentTitle") String attachmentTitle,
			@HeaderParam(value = "attachmentName") String attachmentName) {

		ResponseObj responseObj = new ResponseObj();

		// String path = UnionAppConstants.serverAbsPath + featureType + "/" +
		// featureId + "/" + attachmentType;

		String path = UnionAppConstants.serverAbsPath + featureType + "/" + featureId + "/" + attachmentType + "/"
				+ attachmentTitle;

		File filePath = new File(path);
		if (!filePath.isDirectory()) {
			boolean success = filePath.mkdirs();
			if (success) {
				System.out.println("Created path: " + filePath.getPath());
			} else {
				System.out.println("Could not create path: " + filePath.getPath());
			}
		} else {
			System.out.println("Path exists: " + filePath.getPath());

		}

		// saving file
		String fileName = attachmentName;

		String fileLocation = filePath + "/" + fileName;

		String filelocationtitle = fileLocation + "~~~" + attachmentTitle;

		// String fileLocation = filePath + "/" + "testfile.txt";

		try {
			/*
			 * filePath = new File(fileLocation); if (!filePath.exists()) {
			 * filePath.createNewFile(); }
			 */

			filePath = new File(fileLocation);

			if (featureType.equalsIgnoreCase("profile")) {
				responseObj = serviceDelegator.updateAttachmentDetail(featureType, featureId, filelocationtitle,
						attachmentType);
			}

			else if (!filePath.exists()) {
				System.out.println("filePath>>" + fileLocation);
				System.out.println("featureType>>" + featureType);
				System.out.println("featureId>>" + featureId);
				System.out.println("filelocationtitle>>" + filelocationtitle);
				System.out.println("attachmentType>>" + attachmentType);
				responseObj = serviceDelegator.updateAttachmentDetail(featureType, featureId, filelocationtitle,
						attachmentType);

			} else {
				ResStatus resStatus = new ResStatus();
				resStatus.setCode("00");
				resStatus.setMsg("SUCCESS");
				responseObj.setResStatus(resStatus);
			}

			FileOutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

			// Update the DB Attachment Status

		} catch (Exception exceptionObj) {
			return ServiceExceptionMapper.toResponse(exceptionObj);
		}
		// String output = "File successfully uploaded to : " + fileLocation;
		// return Response.status(200).entity(output).build();
		return responseObj;
	}

	@Override
	@POST
	@Path("/deletefile")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj deleteFile(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam && null != reqparam.getDeleteFileObj()) {
				String path = UnionAppConstants.serverAbsPath + reqparam.getDeleteFileObj().getFeatureType() + "/"
						+ reqparam.getDeleteFileObj().getFeatureId() + "/"
						+ reqparam.getDeleteFileObj().getAttachmentType() + "/"
						+ reqparam.getDeleteFileObj().getFileName();
				new File(path).delete();

				responseObj = serviceDelegator.deletefile(reqparam.getDeleteFileObj().getFeatureType(),
						reqparam.getDeleteFileObj().getFeatureId(), reqparam.getDeleteFileObj().getFileName(),
						reqparam.getDeleteFileObj().getAttachmentType());

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/addcategory")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj addcategory(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.addcategory(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchcategory")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchcategory(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchcategory(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatecategory")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatecategory(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatecategory(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/hellosurvey")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj helloSurvey(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		SurveyList surveyListObj = new SurveyList();

		ArrayList<SurveyDTO> surveyDTOList = new ArrayList<SurveyDTO>();

		SurveyDTO surveyDTOObj1 = new SurveyDTO();

		QuestionDTO questionDTOObj1 = new QuestionDTO();

		String value = "å";// This is a special character
		String value2 = "";

		try {
			// value=URLEncoder.encode(value, "UTF8");
			value = URLDecoder.decode(URLEncoder.encode(value, "UTF-8"), "ISO-8859-1");
			value2 = URLDecoder.decode(value, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		questionDTOObj1.setDetail("ø");
		questionDTOObj1.setSubject("Test1");
		System.out.println("Danish Lang - " + "ø");

		QuestionDTO questionDTOObj2 = new QuestionDTO();

		questionDTOObj2.setDetail("TestQuestion2");
		questionDTOObj2.setSubject("Test2");

		OptionDTO optionDTOObj1 = new OptionDTO();
		optionDTOObj1.setDetail(value);
		optionDTOObj1.setResponsecount("4");

		OptionDTO optionDTOObj2 = new OptionDTO();

		optionDTOObj2.setDetail(value);
		optionDTOObj2.setResponsecount("4");

		OptionDTO optionDTOObj3 = new OptionDTO();

		optionDTOObj3.setDetail(value2);
		optionDTOObj3.setResponsecount("4");

		List<OptionDTO> opotionDTOObjLs1 = new ArrayList<OptionDTO>();
		opotionDTOObjLs1.add(optionDTOObj1);
		opotionDTOObjLs1.add(optionDTOObj2);

		List<OptionDTO> opotionDTOObjLs2 = new ArrayList<OptionDTO>();
		opotionDTOObjLs2.add(optionDTOObj1);
		opotionDTOObjLs2.add(optionDTOObj2);
		opotionDTOObjLs2.add(optionDTOObj3);

		questionDTOObj1.setOptiondtoLs(opotionDTOObjLs1);

		questionDTOObj2.setOptiondtoLs(opotionDTOObjLs2);

		List<QuestionDTO> questionDTOObjLs = new ArrayList<QuestionDTO>();
		questionDTOObjLs.add(questionDTOObj1);
		questionDTOObjLs.add(questionDTOObj2);

		surveyDTOObj1.setQuestiondtoLs(questionDTOObjLs);

		// 1. Convert object to JSON string
		Gson gson = new Gson();
		String json = gson.toJson(surveyDTOObj1);
		System.out.println(json);
		SurveyDTO surveyDTOObj2 = new SurveyDTO();
		surveyDTOObj2 = gson.fromJson(json, SurveyDTO.class);

		surveyDTOList.add(surveyDTOObj1);
		surveyDTOList.add(surveyDTOObj2);

		surveyListObj.setSurveydtoLs(surveyDTOList);

		responseObj.setSurveyListObj(surveyListObj);

		return responseObj;

	}

	@Override
	@POST
	@Path("/createsurvey")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createSurvey(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createSurvey(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/updatesurvey")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ResponseObj updateSurvey(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateSurvey(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@GET
	@Path("/fetchsurvey/{surveyid}/{userid}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ResponseObj fetchSurveyById(@PathParam("surveyid") String surveyid, @PathParam("userid") String userid) {

		ResponseObj responseObj;

		try {
			if (null != surveyid && null != userid) {
				responseObj = serviceDelegator.fetchSurveyById(surveyid, userid);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchsurvey")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchSurvey(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchSurvey(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchactionlog")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchActionLog(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchActionLog(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchspaceinfo")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchSpaceInfo(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchSpaceInfo(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/fetchvisitorinfo")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchVisitorInfo(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchVisitorInfo(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@POST
	@Path("/user/active")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj setActiveUser(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.setActiveUser(reqparam);

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.REQOBJECTNULL);
				throw serviceExceptionObj;

			}
		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@GET
	@Path("/send/notification")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ResponseObj sendNotification() {

		ResponseObj responseObj;

		try {
			responseObj = serviceDelegator.sendNotification();

		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@POST
	@Path("/fetch/notification")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchNotificationItem(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			responseObj = serviceDelegator.fetchNotificationItem(reqparam);

		} catch (Exception exceptionObj) {

			return ServiceExceptionMapper.toResponse(exceptionObj);
		}

		return responseObj;

	}

	@Override
	@POST
	@Path("/testmail")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String testMail() {

		/*
		 * String USER_NAME = "inbox.saurabh@gmail.com"; // GMail user name
		 * (just the part before "@gmail.com") String PASSWORD = "dec_2482"; //
		 * GMail password
		 */
		String USER_NAME = "TestMail"; // GMail user name (just
										// the part before
										// "@gmail.com")
		String PASSWORD = "XYZ123"; // GMail password
		String RECIPIENT = "code6play@gmail.com";

		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT }; // list of recipient email addresses
		String subject = "Java send mail example";
		String body = "Welcome to JavaMail!";

		// serviceDelegator.sendMail(from, pass, to, subject, body);
		return "success";
	}

	public ServiceDelegator getServiceDelegator() {
		return serviceDelegator;
	}

	public void setServiceDelegator(ServiceDelegator serviceDelegator) {
		this.serviceDelegator = serviceDelegator;
	}

}
