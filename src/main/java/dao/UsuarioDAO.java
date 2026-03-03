package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;


public class UsuarioDAO {
    public List<Usuario> listar() {
       List <Usuario> usuarios = new ArrayList<>();
            try(Connection conn = Conexion.obtenerConexion()) {
               PreparedStatement ps = conn.prepareStatement("SELECT id , nombre , email from sistema.usuario");
               ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                     Usuario u = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("email")
                     );
                     usuarios.add(u);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usuarios;
    }

    public void insertar(Usuario usuario) {

        System.out.println("Insertando usuario: " + usuario.getNome() + " - " + usuario.getEmail());
        
        String sql = "INSERT INTO sistema.usuario (nombre, email) VALUES (?, ?)";
        try(Connection conn = Conexion.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ;
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void actualizar(Usuario usuario) {
      String sql="UPDATE sistema.usuario SET nombre=?, email=? WHERE id=?";
      try(Connection conn = Conexion.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getEmail());
        ps.setLong(3, usuario.getId());
        ps.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    public Usuario buscarPorId(Long id) {
        String sql = "SELECT id, nombre, email FROM sistema.usuario WHERE id = ?";
        try(Connection conn = Conexion.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Usuario(
                    rs.getLong("id"),
                    rs.getString("nombre"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM sistema.usuario WHERE id = ?";
        try(Connection conn = Conexion.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
}
