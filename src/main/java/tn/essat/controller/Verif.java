package tn.essat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.essat.dao.GestionImp;
import tn.essat.dao.IGestion;
import tn.essat.model.Utilisateur;

/**
 * Servlet implementation class Verif
 */
public class Verif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verif() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
	
		  String login = request.getParameter("login");
	        String password = request.getParameter("password");
	 
	        IGestion dao = new GestionImp();
	 
	        Utilisateur u = dao.verifUser(login, password);
	 
	        if (u != null) {
	 
	            session.setAttribute("user", u);
	            if ("admin".equals(u.getRole())) {
	                request.getRequestDispatcher("espace_admin/accueil_admin.jsp").forward(request, response);
	            } else {
	                request.getRequestDispatcher("espace_user/acceuil_user.jsp").forward(request, response);
	            }
	 
	        } else {
	            request.setAttribute("erreur", "Login ou mot de passe incorrect");
	            request.getRequestDispatcher("connection.jsp").forward(request, response);
	        }
	    }
	}
		
	


