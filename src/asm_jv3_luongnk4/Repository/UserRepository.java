/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.Repository;

import asm_jv3_luongnk4.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserRepository {

    List<User> danhSachUser;
    DBconnect dBconnect;

    public UserRepository() {
        danhSachUser = new ArrayList<>();
        dBconnect = new DBconnect();
    }

    public List<User> layDanhSachUser() {
        String sql = "SELECT * FROM USERS";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("USERNAME");
                String password = rs.getString("PASSWORDS");
                String role = rs.getString("ROLES");

                User user = new User(userName, password, role);

                danhSachUser.add(user);
            }
            conn.close();
            st.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu user");
            e.printStackTrace();
        }
        return danhSachUser;
    }
//    public String layUserName(String userName){
//        String sql = "select USERNAME from USERS where USERNAME = ? ";
//        try {
//            Connection conn = dBconnect.getConnection();
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setString(1, userName);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {                
//                String user = rs.getString("USERNAME");
//                return user;
//            }
//            
//        } catch (Exception e) {
//            System.out.println("Lỗi không lấy được user");
//            e.printStackTrace();
//        }
//        return null;
//        
//    }
//       public String layPass(String pass){
//        String sql = "select PASSWORDS from USERS where PASSWORDS = ? ";
//        try {
//            Connection conn = dBconnect.getConnection();
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setString(1, pass);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {                
//                String user = rs.getString("PASSWORDS");
//                return user;
//            }
//            
//        } catch (Exception e) {
//            System.out.println("Lỗi không lấy được pass");
//            e.printStackTrace();
//        }
//        return null;
//        
//    }

    public User layUser(String Name) {
        String sql = "SELECT * FROM USERS where USERNAME =?";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, Name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("USERNAME");
                String password = rs.getString("PASSWORDS");
                String role = rs.getString("ROLES");

                User user = new User(userName, password, role);
                return user;

            }
            conn.close();
            st.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu user");
            e.printStackTrace();
        }
        return null;
    }

}
