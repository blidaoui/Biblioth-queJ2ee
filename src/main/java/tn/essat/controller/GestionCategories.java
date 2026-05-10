package tn.essat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.essat.dao.GestionImp;
import tn.essat.dao.IGestion;
import tn.essat.model.Categorie;
import tn.essat.model.Utilisateur;

/**
 * Servlet implementation class GestionCategories
 */
public class GestionCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GestionCategories() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("Principal");
			return;
		}
		
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		IGestion dao = new GestionImp();
		
		String action = request.getParameter("action");
		
		if ("delete".equals(action) && "admin".equals(user.getRole())) {
			// Delete category functionality
			int catId = Integer.parseInt(request.getParameter("id"));
			dao.deleteCategorie(catId);
			response.sendRedirect("GestionCategories");
			return;
		}
		
		List<Categorie> categories = dao.getAllCats();
		request.setAttribute("categories", categories);
		
		if ("admin".equals(user.getRole())) {
			request.getRequestDispatcher("/espace_admin/categories_admin.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/espace_user/categories.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("Principal");
			return;
		}
		
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		if (!"admin".equals(user.getRole())) {
			response.sendRedirect("Principal");
			return;
		}
		
		String titre = request.getParameter("titre");
		
		if (titre != null && !titre.trim().isEmpty()) {
			IGestion dao = new GestionImp();
			Categorie cat = new Categorie();
			cat.setTitre(titre.trim());
			dao.addCategorie(cat);
		}
		
		response.sendRedirect("GestionCategories");
	}
}