package pl.project.weekop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import pl.project.weekop.model.User;
import pl.project.weekop.service.UserService;

@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) arg0;
		if(httpReq.getUserPrincipal()!=null && httpReq.getSession().getAttribute("user")==null) {
			saveUserInSession(httpReq);
		}
		arg2.doFilter(arg0, arg1);
	}
	
	private void saveUserInSession(HttpServletRequest request) {
		UserService userService = new UserService();
		String username = request.getUserPrincipal().getName();
		User userByUsername = userService.getUserByUsername(username);
		request.getSession(true).setAttribute("user", userByUsername);
	}
	
	
	
}
