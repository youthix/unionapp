package org.presentation.controller.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.common.UnionAppConstants;
import org.presentation.controller.Interface.RESTfulServiceInterface;
import org.presentation.dto.RequestObj;
import org.presentation.dto.ResponseObj;
import org.presentation.util.ServiceException;
import org.presentation.util.ServiceExceptionMapper;
import org.service.delegateService.ServiceDelegator;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
	@Path("/logout")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj logout(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.logout(reqparam);
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
	@Path("/updateuserprofile")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateuserprofile(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateuserprofile(reqparam);

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

	@Override
	@POST
	@Path("/createactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createactivity(reqparam);

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
	@Path("/updateactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateactivity(reqparam);

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
	@Path("/fetchactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchactivity(reqparam);

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
	@Path("/acceptdenyactivity")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj acceptdenyactivity(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.acceptdenyactivity(reqparam);

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
	@Path("/createnewsletter")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createNewsLetter(reqparam);

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
	@Path("/updatenewsletter")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateNewsLetter(reqparam);

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
	@GET
	@Path("/fetchnewsletter/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchNewsLetterById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchNewsLetterById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchNewsLetter(reqparam);

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
	@Path("/createpayrate")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createPayrate(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createPayrate(reqparam);

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
	@Path("/updatepayrate")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatePayrate(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatePayrate(reqparam);

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
	@GET
	@Path("/fetchpayrate/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchPayrateById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchPayrateById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchPayrate(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchPayrate(reqparam);

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
	@Path("/createamr")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createAmr(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createAmr(reqparam);

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
	@Path("/updateamr")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateAmr(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateAmr(reqparam);

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
	@GET
	@Path("/fetchamr/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchAmrById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchAmrById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchAmr(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchAmr(reqparam);

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
	@Path("/createagreement")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createAgreement(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createAgreement(reqparam);

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
	@Path("/updateagreement")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateAgreement(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateAgreement(reqparam);

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
	@GET
	@Path("/fetchagreement/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchAgreementById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchAgreementById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchAgreement(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchAgreement(reqparam);

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
	@Path("/createsummary")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createSummary(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createSummary(reqparam);

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
	@Path("/updatesummary")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updateSummary(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updateSummary(reqparam);

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
	@GET
	@Path("/fetchsummary/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchSummaryById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchSummaryById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchSummary(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchSummary(reqparam);

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
	@Path("/createsuggestionidea")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj createsuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.createsuggestionidea(reqparam);

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
	@Path("/updatesuggestionidea")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj updatesuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.updatesuggestionidea(reqparam);

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
	@Path("/fetchsuggestionidea")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchsuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchsuggestionidea(reqparam);

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
	@GET
	@Path("/fetchsuggestionidea/{id}")
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String fetchsuggestionideaById(@PathParam("id") String id) {

		String responseObj;

		try {
			if (null != id) {
				responseObj = serviceDelegator.fetchsuggestionideaById(id);

			} else {
				ServiceException serviceExceptionObj = new ServiceException("Request Object is NULL");
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
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@HeaderParam(value = "featureType") String featureType, @HeaderParam(value = "featureId") String featureId,
			@HeaderParam(value = "attachmentType") String attachmentType,
			@HeaderParam(value = "attachmentTitle") String attachmentTitle,
			@HeaderParam(value = "attachmentName") String attachmentName) {
		
		ResponseObj responseObj;

		/*
		 * String featureType = feature; System.out.println("featureType = "+
		 * featureType); String featureId = "1234"; String attachmentType =
		 * "doc";
		 */

		 //String path = "/C:/Saurabh/Images/" + featureType + "/" + featureId + "/" + attachmentType;

		// Create Directory if not already Exists
		//String path = "/../" + featureType + "/" + featureId + "/" + attachmentType;
		String path = UnionAppConstants.serverAbsPath + featureType + "/" + featureId + "/" + attachmentType;

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
			
			
			responseObj = serviceDelegator.updateAttachmentDetail(featureType, featureId,filelocationtitle,attachmentType);
		} catch (Exception exceptionObj) {
			return ServiceExceptionMapper.toResponse(exceptionObj);
		}
		//String output = "File successfully uploaded to : " + fileLocation;
		// return Response.status(200).entity(output).build();
		return responseObj;
	}
	
	@Override
	@POST
	@Path("/addcategory")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj addcategory(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.addcategory(reqparam);

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
	@Path("/fetchcategory")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseObj fetchcategory(RequestObj reqparam) {

		ResponseObj responseObj;

		try {
			if (null != reqparam) {
				responseObj = serviceDelegator.fetchcategory(reqparam);

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
