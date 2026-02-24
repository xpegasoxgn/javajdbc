package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;


@WebServlet("/usuarios")    
public class UsuarioServlet  extends HttpServlet{
    
    private UsuarioDAO usuarioDAO=new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<Usuario> usuarios = usuarioDAO.listar();
        response.getWriter().println("<h1>Lista de Usuarios</h1>");
        for(Usuario u : usuarios) {
            response.getWriter().println("<p>" + u.getId() + " - " + u.getNome() + " - " + u.getEmail() + "</p>");
        }
    }
}
