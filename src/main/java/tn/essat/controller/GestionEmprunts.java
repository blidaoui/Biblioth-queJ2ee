package tn.essat.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.essat.dao.GestionImp;
import tn.essat.dao.IGestion;
import tn.essat.model.Emprunt;
import tn.essat.model.Livre;
import tn.essat.model.Utilisateur;

/**
 * Servlet implementation class GestionEmprunts
 */
public class GestionEmprunts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GestionEmprunts() {
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
		
		if ("add".equals(action)) {
			// Add borrow for this book
			int livreId = Integer.parseInt(request.getParameter("livreId"));
			Livre livre = dao.getLivreById(livreId);
			
			Emprunt emprunt = new Emprunt();
			emprunt.setUser(user);
			emprunt.setLv(livre);
			emprunt.setDate_emprunt(LocalDate.now());
			
			dao.addEmprunt(emprunt);
			response.sendRedirect("GestionEmprunts");
			return;
		}
		
		List<Emprunt> emprunts = dao.getAllEmpruntsByUser(user.getId());
		request.setAttribute("emprunts", emprunts);
		
		if ("admin".equals(user.getRole())) {
			request.getRequestDispatcher("/espace_admin/emprunts_admin.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/espace_user/emprunts.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}