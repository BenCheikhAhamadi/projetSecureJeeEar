package com.samanecorp.secureapp.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.secureapp.secureappEJB.dto.AccountUserDto;
import com.samanecorp.secureapp.secureappEJB.service.SecurityServiceImpl;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="singup",value="/singup")
public class SingUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 private static Logger logger = LoggerFactory.getLogger(SingUpServlet.class);
	 
	 @EJB
	 private SecurityServiceImpl loginService;
	 private final String LOGIN_PAGE = "index.jsp";
	 
	 @Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	 
	 public SingUpServlet() {
		super();
	}
	 
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		loadIndex(null,req,resp);
	}

	private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		req.setAttribute("message",message);
		req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("pwd");
		logger.info("Tantative d'inscription avec {}", email);
		String message = null;
		
		try {
			AccountUserDto accountUserDto = new AccountUserDto();
			accountUserDto.setEmail(email);
			accountUserDto.setPassword(password);
			int result = loginService.save(accountUserDto);
			if(result == 1) {
				message = "Informations ajoutees dans la base de donnees";
				logger.info("{}",message);
			}
		} catch (Exception e) {
			message = "information non ajoutees";
			logger.error("{}",message);
			logger.error("error",e);
		}
		loadIndex(message,req,resp);
	}
}
