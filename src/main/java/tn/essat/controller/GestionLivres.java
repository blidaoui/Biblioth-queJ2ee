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
import tn.essat.model.Livre;
import tn.essat.model.Utilisateur;

public class GestionLivres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GestionLivres() {
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

		if ("byCategory".equals(action)) {
			int catId = Integer.parseInt(request.getParameter("id"));
			List<Livre> livres = dao.getAllLivresByCats(catId);
			request.setAttribute("livres", livres);
			request.setAttribute("categorie", dao.getCatsById(catId));
			if ("admin".equals(user.getRole())) {
				request.getRequestDispatcher("/espace_admin/livres_admin.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/espace_user/livre.jsp").forward(request, response);
			}
		} else if ("add".equals(action) && "admin".equals(user.getRole())) {
			List<Categorie> categories = dao.getAllCats();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/espace_admin/add_livre.jsp").forward(request, response);
		} else if ("delete".equals(action) && "admin".equals(user.getRole())) {
			int livreId = Integer.parseInt(request.getParameter("id"));
			dao.deleteLivre(livreId);
			response.sendRedirect("GestionLivres");
			return;
		} else {
			List<Categorie> categories = dao.getAllCats();
			request.setAttribute("categories", categories);
			if ("admin".equals(user.getRole())) {
				request.getRequestDispatcher("/espace_admin/livres_admin.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/espace_user/livre.jsp").forward(request, response);
			}
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
		int catId = Integer.parseInt(request.getParameter("catId"));

		IGestion dao = new GestionImp();
		Categorie cat = dao.getCatsById(catId);
		Livre lv = new Livre();
		lv.setTitre(titre);
		lv.setCat(cat);
		dao.addLivre(lv);

		response.sendRedirect("GestionLivres");
	}
}
