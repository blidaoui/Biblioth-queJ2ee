<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Livres - Bibliothèque ESSAT</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style>
    body { font-family: 'Segoe UI', sans-serif; background-color: #f8f9fa; }
    .sidebar { height: 100vh; background-color: #343a40; }
    .sidebar .nav-link { color: #ffffff; }
    .sidebar .nav-link.active { background-color: #0d6efd; color: white; }
    .sidebar .nav-link:hover { background-color: #495057; }
    .content { padding: 2rem; }
  </style>
</head>
<body>

  <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
    <a class="navbar-brand fw-bold text-primary" href="#"><i class="fas fa-book me-2"></i>Bibliothèque ESSAT</a>
    <div class="ms-auto">
      <a class="btn btn-primary" href="Logout"><i class="fas fa-sign-out-alt me-1"></i> ${sessionScope.user.nom} (Déconnexion)</a>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <nav class="col-md-2 d-none d-md-block sidebar p-3">
        <ul class="nav flex-column">
          <li class="nav-item mb-2">
            <a class="nav-link" href="DashboardUser"><i class="fas fa-home me-2"></i>Dashboard</a>
          </li>
          <li class="nav-item mb-2">
            <a class="nav-link active" href="GestionLivres"><i class="fas fa-book me-2"></i>Livres disponibles</a>
          </li>
          <li class="nav-item mb-2">
            <a class="nav-link" href="GestionEmprunts"><i class="fas fa-list me-2"></i>Mes emprunts</a>
          </li>
          <li class="nav-item mb-2">
            <a class="nav-link" href="GestionCategories"><i class="fas fa-tags me-2"></i>Catégories</a>
          </li>
        </ul>
      </nav>

      <main class="col-md-10 ms-sm-auto content">
        <div class="container py-3">
          <h2 class="text-center text-primary mb-4">
            Livres disponibles
            <c:if test="${not empty categorie}"> - ${categorie.titre}</c:if>
          </h2>

          <c:choose>
            <c:when test="${not empty livres}">
              <div class="table-responsive">
                <table class="table table-bordered align-middle bg-white shadow-sm">
                  <thead class="table-primary">
                    <tr>
                      <th>Titre</th>
                      <th>Catégorie</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="lv" items="${livres}">
                      <tr>
                        <td>${lv.titre}</td>
                        <td>${lv.cat.titre}</td>
                        <td>
                          <a href="GestionEmprunts?action=add&livreId=${lv.id}" class="btn btn-outline-success btn-sm">
                            <i class="fas fa-plus"></i> Emprunter
                          </a>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </c:when>
            <c:when test="${not empty categories}">
              <div class="row g-4">
                <c:forEach var="cat" items="${categories}">
                  <div class="col-sm-6 col-lg-4">
                    <div class="card h-100 border-0 shadow-sm">
                      <div class="card-body text-center">
                        <i class="fas fa-folder-open text-primary mb-3 fa-2x"></i>
                        <h5 class="card-title">${cat.titre}</h5>
                        <a href="GestionLivres?action=byCategory&id=${cat.id}" class="btn btn-outline-primary mt-2">Voir les livres</a>
                      </div>
                    </div>
                  </div>
                </c:forEach>
              </div>
            </c:when>
            <c:otherwise>
              <p class="text-center text-muted">Aucun livre disponible.</p>
            </c:otherwise>
          </c:choose>
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
