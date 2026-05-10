package tn.essat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.essat.model.Utilisateur;

/**
 * Servlet implementation class DashboardUser
 */
public class DashboardUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DashboardUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("Principal");
			return;
		}
		
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		if ("admin".equals(user.getRole())) {
			response.sendRedirect("Principal");
			return;
		}
		
		request.getRequestDispatcher("/espace_user/acceuil_user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}