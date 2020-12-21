package com.tew.presentacion.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tew.model.User;

/**
 * Servlet Filter implementation class LoginUsuario
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST },
		description = "Filtro de seguridad",
		urlPatterns = { "/faces/restricted/usuario/*", "/restricted/usuario/*"}, 
		initParams = { 
				@WebInitParam(name = "LoginParam", value = "/faces/restricted/administrador/opciones-admin.xhtml", description = "Pagina de loggeo Usuario")
		})
public class LoginUsuario implements Filter {
	
	FilterConfig config = null;


    /**
     * Default constructor. 
     */
    public LoginUsuario() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		config = fConfig;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		

		// Si no es petición HTTP nada que hacer
		if (!(request instanceof HttpServletRequest)){
			chain.doFilter(request, response);
			return;
		}
			
	// En el resto de casos se verifica que se haya hecho login previamente
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		User u = (User) session.getAttribute("LOGGEDIN_USER");
		String rol =u.getRol();


		if (rol.equals("administrador")) {
			String loginForm = config.getInitParameter("LoginParam");
			// Si no hay login, redirección al formulario de login
			res.sendRedirect(req.getContextPath() + loginForm);
			return;
		}
		
		chain.doFilter(request, response);
	}



}
