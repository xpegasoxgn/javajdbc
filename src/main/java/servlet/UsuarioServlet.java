package servlet;

import java.io.IOException;
import java.util.List;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;


@WebServlet("/usuarios")    
public class UsuarioServlet  extends HttpServlet{
    
    private UsuarioDAO usuarioDAO=new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html");
        //List<Usuario> usuarios = usuarioDAO.listar();
        //response.getWriter().println("<h1>Lista de Usuarios</h1>");
        //for(Usuario u : usuarios) {
          //  response.getWriter().println("<p>" + u.getId() + " - " + u.getNome() + " - " + u.getEmail() +"<a href='editar?id="+u.getId()+"'>Editar</a>"+ "</p>");
        //}

        String id=request.getParameter("id");
        if(id!=null) {
           Usuario usuario = usuarioDAO.buscarPorId(Long.parseLong(id));
              request.setAttribute("usuario", usuario);
        }
        List<Usuario> usuarios = usuarioDAO.listar();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);

    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            System.err.println("Recibiendo solicitud POST para crear un nuevo usuario...");
            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");

            if (nombre != null && email != null) {

            nombre = nombre.trim();
            email = email.trim();

            if (!nombre.isEmpty() && !email.isEmpty()) {

                Usuario usuario = new Usuario();
                usuario.setNome(nombre);

                usuario.setEmail(email);

                if (id == null || id.trim().isEmpty()) {
                    //insert
                    usuarioDAO.insertar(usuario);    
                }else {
                    //update
                    usuario.setId(Long.parseLong(id.trim()));
                    usuarioDAO.actualizar(usuario);
                }   
                
            }
        }

        response.sendRedirect("usuarios");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        if (idStr != null && nombre != null && email != null) {
            try {
                Long id = Long.parseLong(idStr.trim());
                nombre = nombre.trim();
                email = email.trim();

                if (!nombre.isEmpty() && !email.isEmpty()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(id);
                    usuario.setNome(nombre);
                    usuario.setEmail(email);

                    usuarioDAO.actualizar(usuario);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("usuarios");
    }

    
}
