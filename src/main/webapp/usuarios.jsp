<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<body>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>

<h2><%= (usuario != null ? "Editar Usuario" : "Nuevo Usuario") %></h2>

<form action="usuarios" method="post">
    <input type="hidden" name="id" value="<%= (usuario != null ? usuario.getId() : "") %>">

    Nombre:
    <input type="text" name="nombre"
           value="<%= (usuario != null ? usuario.getNome() : "") %>">
    <br>

    Email:
    <input type="text" name="email"
           value="<%= (usuario != null ? usuario.getEmail() : "") %>">
    <br>

    <button type="submit">
        <%= (usuario != null ? "Actualizar" : "Crear") %>
    </button>
</form>

<hr>

<h3>Lista de Usuarios</h3>

<form action="usuarios" method="get">
    Buscar por nombre:
    <input type="text" name="buscar"/>
    <button type="submit">Buscar</button>

</form>
<ul>
<%
    if (usuarios != null) {
        for (Usuario u : usuarios) {
%>
    <li>
        <%= u.getNome() %> - <%= u.getEmail() %>
        <a href="usuarios?id=<%= u.getId() %>">Editar</a>

        <form action="usuarios" method="post" style="display: inline;">
            <input type="hidden" name="id" value="<%= u.getId() %>">
            <input type="hidden" name="accion" value="eliminar">
            <button type="submit"
                onclick="return confirm('¿Estás seguro de eliminar este usuario?');">
                Eliminar</button>
        </form>
    </li>
<%
        }
    }
%>
</ul>

</body>
</html>