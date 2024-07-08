package com.samanecorp.secureapp.controller;

import java.io.IOException;
import java.util.Optional;

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

@WebServlet(name="login",value="/login")
public class LoginServlet extends HttpServlet {
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private final String LOGIN_PAGE = "index.jsp";
	private final String WELCOME_PAGE = "welcome.jsp";
	@EJB
	private SecurityServiceImpl loginService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		loadIndex(null,req, resp);
	}
	
	private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		
		req.setAttribute("message",message);
		req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		logger.info("Tantative de connexion avec {} et {}",email,password);
		try {
			
		 Optional<AccountUserDto> userDto = loginService.login(email,password);
		 
		 AccountUserDto accountUserDto = userDto.get();
		 req.getSession().setAttribute("username", accountUserDto.getEmail());
		 resp.sendRedirect(WELCOME_PAGE);
		} catch (Exception e) {
			String message = "Informations de connexion incorrect";
			logger.error("{}",message);
			loadIndex(message,req,resp);
		}
	}

}
