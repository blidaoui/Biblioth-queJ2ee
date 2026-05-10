<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Gestion Categories</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: #f8f9fa;
    }
    .sidebar {
      height: 100vh;
      background-color: #343a40;
    }
    .sidebar .nav-link {
      color: #ffffff;
    }
    .sidebar .nav-link.active {
      background-color: #0d6efd;
      color: white;
    }
    .sidebar .nav-link:hover {
      background-color: #495057;
    }
    .content {
      padding: 2rem;
    }
    .card-hover:hover {
      transform: scale(1.02);
      transition: all 0.2s;
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }
  </style>
</head>
<body>

 <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
    <a class="navbar-brand fw-bold text-primary" href="#">
      <i class="fas fa-book me-2"></i>Bonjour ${user.nom}
    </a>
    <div class="ms-auto">
       <a class="btn btn-primary" href="Logout">
         <i class="fas fa-sign-out-alt me-1"></i> Déconnexion
       </a>
    </div>
  </nav>
  <div class="container-fluid">
    <div class="row">
      <!-- Sidebar -->
      <nav class="col-md-2 d-none d-md-block sidebar p-0">
        <ul class="nav flex-column mt-2">
          <li class="nav-item">
            <a class="nav-link" href="DashboardAdmin"><i class="fas fa-home me-2"></i>Dashboard</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="GestionLivres"><i class="fas fa-book me-2"></i>Gestion Livres</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="GestionCategories"><i class="fas fa-tags me-2"></i>Catégories</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="GestionEmprunts"><i class="fas fa-list me-2"></i>Emprunts</a>
          </li>
        </ul>
      </nav>

      <!-- Main Content -->
      <main class="col-md-10 ms-sm-auto content">
        <h1 class="text-primary mb-4">Gestion des Catégories</h1>

        <!-- Add Category Form -->
        <div class="row mb-5">
          <div class="col-md-6">
            <div class="card shadow-sm">
              <div class="card-header bg-success text-white">
                <h5 class="mb-0">Ajouter une Catégorie</h5>
              </div>
              <div class="card-body">
                <form action="GestionCategories" method="POST">
                  <div class="mb-3">
                    <label for="titre" class="form-label">Titre de la catégorie</label>
                    <input type="text" class="form-control" id="titre" name="titre" 
                           placeholder="Ex: Romans, Science, Histoire..." required>
                  </div>
                  <div class="text-center">
                    <button type="submit" class="btn btn-success">
                      <i class="fas fa-plus me-2"></i>Ajouter
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <!-- Categories Table -->
        <div class="row">
          <div class="col-12">
            <div class="card shadow-sm">
              <div class="card-header bg-primary text-white">
                <h5 class="mb-0">Liste des Catégories</h5>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table table-bordered align-middle bg-white">
                    <thead class="table-light">
                      <tr>
                        <th style="width: 10%;">ID</th>
                        <th style="width: 50%;">Titre</th>
                        <th style="width: 40%;">Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:choose>
                        <c:when test="${not empty requestScope.categories}">
                          <c:forEach var="cat" items="${requestScope.categories}">
                            <tr>
                              <td><strong>${cat.id}</strong></td>
                              <td>${cat.titre}</td>
                              <td>
                                <a href="GestionLivres?action=byCategory&id=${cat.id}" 
                                   class="btn btn-outline-primary btn-sm">
                                  <i class="fas fa-book me-1"></i>Voir Livres
                                </a>
                                <a href="GestionCategories?action=delete&id=${cat.id}" 
                                   class="btn btn-outline-danger btn-sm"
                                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette catégorie?');">
                                  <i class="fas fa-trash me-1"></i>Supprimer
                                </a>
                              </td>
                            </tr>
                          </c:forEach>
                        </c:when>
                        <c:otherwise>
                          <tr>
                            <td colspan="3" class="text-center text-muted py-4">
                              <i class="fas fa-inbox fa-2x mb-2"></i><br>
                              Aucune catégorie trouvée.
                            </td>
                          </tr>
                        </c:otherwise>
                      </c:choose>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>

  <!-- Footer -->
  <footer class="bg-light text-center text-muted py-3 mt-5 border-top">
    <small>&copy; 2025 - Application Bibliothèque ESSAT</small>
  </footer>

  <!-- Bootstrap & Icons -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
