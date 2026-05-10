<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Dashboard Admin - ${sessionScope.user.nom}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style>
    body { font-family: 'Segoe UI', sans-serif; background-color: #f8f9fa; }
    .sidebar { height: 100vh; background-color: #343a40; }
    .sidebar .nav-link { color: #ffffff; }
    .sidebar .nav-link.active { background-color: #0d6efd; color: white; }
    .sidebar .nav-link:hover { background-color: #495057; }
    .content { padding: 2rem; }
    .card-hover:hover { transform: scale(1.02); transition: all 0.2s; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
  </style>
</head>
<body>

  <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
    <a class="navbar-brand fw-bold text-primary" href="#"><i class="fas fa-book me-2"></i>Espace Admin</a>
    <div class="ms-auto">
      <a class="btn btn-primary" href="Logout"><i class="fas fa-sign-out-alt me-1"></i> Déconnexion</a>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <nav class="col-md-2 d-none d-md-block sidebar p-3">
        <ul class="nav flex-column">
          <li class="nav-item mb-2">
            <a class="nav-link active" href="DashboardAdmin"><i class="fas fa-home me-2"></i>Dashboard</a>
          </li>
          <li class="nav-item mb-2">
            <a class="nav-link" href="GestionLivres"><i class="fas fa-book me-2"></i>Livres</a>
          </li>
          <li class="nav-item mb-2">
            <a class="nav-link" href="GestionEmprunts"><i class="fas fa-list me-2"></i>Emprunts</a>
          </li>
          <li class="nav-item mb-2">
            <a class="nav-link" href="GestionCategories"><i class="fas fa-tags me-2"></i>Catégories</a>
          </li>
        </ul>
      </nav>

      <main class="col-md-10 ms-sm-auto content">
        <h3 class="mb-4">Bienvenue, ${sessionScope.user.nom} !</h3>
        <div class="row g-4">
          <div class="col-md-6 col-lg-4">
            <div class="card card-hover border-0 shadow-sm">
              <div class="card-body">
                <h5 class="card-title"><i class="fas fa-book me-2 text-primary"></i>Livres</h5>
                <p class="card-text">Gérer les livres de la bibliothèque.</p>
                <a href="GestionLivres" class="btn btn-outline-primary">Gérer</a>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4">
            <div class="card card-hover border-0 shadow-sm">
              <div class="card-body">
                <h5 class="card-title"><i class="fas fa-list me-2 text-success"></i>Emprunts</h5>
                <p class="card-text">Consulter tous les emprunts.</p>
                <a href="GestionEmprunts" class="btn btn-outline-success">Voir</a>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4">
            <div class="card card-hover border-0 shadow-sm">
              <div class="card-body">
                <h5 class="card-title"><i class="fas fa-tags me-2 text-warning"></i>Catégories</h5>
                <p class="card-text">Gérer les catégories de livres.</p>
                <a href="GestionCategories" class="btn btn-outline-warning">Gérer</a>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>

  <footer class="bg-light text-center text-muted py-3 mt-4 border-top">
    &copy; 2025 - Application Bibliothèque ESSAT
  </footer>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
