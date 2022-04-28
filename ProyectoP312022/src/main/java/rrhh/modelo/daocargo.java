/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh.modelo;

import rrhh.controlador.clscargo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class daocargo {

    private static final String SQL_SELECT = "SELECT idcargo, nombrecargo, estatuscargo FROM tbl_cargo";
    private static final String SQL_INSERT = "INSERT INTO tbl_cargo(nombrecargo,estatuscargo) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tbl_cargo SET nombrecargo=?, estatuscargo=? WHERE idcargo = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_cargo WHERE idcargo=?";
    private static final String SQL_QUERY = "SELECT  idcargo, nombre_cargo, estatus_cargo FROM tbl_cargo WHERE nombrecargo = ?";

    public List<clscargo> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clscargo usuario = null;
        List<clscargo> usuarios = new ArrayList<clscargo>();
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("idcargo");
                String nombre = rs.getString("nombrecargo");
                String estatus = rs.getString("estatuscargo");

                usuario = new clscargo();
                usuario.setidcargo(codigo);
                usuario.setnombrecargo(nombre);
                usuario.setestatuscargo(estatus);

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return usuarios;
    }

    public int insert(clscargo perfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, perfil.getnombrecargo());
            stmt.setString(2, perfil.getestatuscargo());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int update(clscargo perfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
             
            stmt.setString(2, perfil.getnombrecargo());
            stmt.setString(3, perfil.getestatuscargo());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int delete(clscargo perfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, perfil.getidcargo());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public clscargo query(clscargo perfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, perfil.getnombrecargo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("idcargo");
                String nombre = rs.getString("nombre_cargo");
                String estatus = rs.getString("estatus_cargo");

                perfil = new clscargo();
                perfil.setidcargo(codigo);
                perfil.setnombrecargo(nombre);
                perfil.setestatuscargo(estatus);
                
            }
            //System.out.println("Registros buscado:" + persona);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        //return personas;  // Si se utiliza un ArrayList
        return perfil;
    }
}
