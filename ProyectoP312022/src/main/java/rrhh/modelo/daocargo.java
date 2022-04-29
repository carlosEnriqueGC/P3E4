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
    private static final String SQL_QUERY = "SELECT  idcargo, nombrecargo, estatuscargo FROM tbl_cargo WHERE idcargo = ?";

    public List<clscargo> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clscargo cargo = null;
        List<clscargo> cargos = new ArrayList<clscargo>();
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idcargo");
                String nombre = rs.getString("nombrecargo");
                String estatus = rs.getString("estatuscargo");

                cargo = new clscargo();
                cargo.setidcargo(id);
                cargo.setnombrecargo(nombre);
                cargo.setestatuscargo(estatus);

                cargos.add(cargo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return cargos;
    }

    public int insert(clscargo cargo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cargo.getnombrecargo());
            stmt.setString(2, cargo.getestatuscargo());

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

    public int update(clscargo cargo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
         
            stmt.setString(1, cargo.getnombrecargo());
            stmt.setString(2, cargo.getestatuscargo());
            stmt.setInt(3, cargo.getidcargo());
            
            
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

    public int delete(clscargo cargo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cargo.getidcargo());
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

    public clscargo query(clscargo cargo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, cargo.getidcargo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idcargo");
                String nombre = rs.getString("nombrecargo");
                String estatus = rs.getString("estatuscargo");

                cargo = new clscargo();
                cargo.setidcargo(id);
                cargo.setnombrecargo(nombre);
                cargo.setestatuscargo(estatus);
                
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
        return cargo;
    }
}
