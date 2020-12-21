

package com.tew.presentacion.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Servlet Filter implementation class CORSFilter
 */
@WebFilter("/FiltrarRol")
public class FiltrarRol implements Filter {

    /**
     * Default constructor. 
     */
    public FiltrarRol() {
      
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        //Cogemos la carpeta de donde viene la petición
        String rolr="";
        String rr=req.getHeader("Referer");
        String rol = req.getPathInfo().toString();
        if(rr.contains("administrador")) rolr = "administrador";
        else if(rr.contains("usuario")) rolr="usuario";
        else rolr="desc";
        
        //Comparamos el usuario que esta en sesion con desde donde se manda la petición
        if((rol.contains("/todas/") && rolr=="desc") || (rol.contains("/admin/") && rolr=="administrador") || (rol.contains("/usuario/") && rolr=="usuario")) System.out.println("Acceso correcto");
        else{ System.out.println("Acceso incorrecto"); 
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        res.addHeader("Access-Control-Allow-Credentials", "false");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        res.addHeader("Access-Control-Max-Age", "1209600");
        return;
        }

        // Forward the request down the filter chain.
        chain.doFilter(req, res);
     }

	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
