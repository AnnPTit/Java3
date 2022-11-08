/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.Repository;

import asm_jv3_luongnk4.Model.Grade;
import asm_jv3_luongnk4.Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class GradeRepository {

    List<Grade> danhSachDiem;
    DBconnect dBconnect;
    //   StudentRepository studentRepository;

    public GradeRepository() {
        dBconnect = new DBconnect();
        danhSachDiem = new ArrayList<>();

        layDanhSachDiem();
    }

    public List<Grade> layDanhSachDiem() {
        String sql = "select * from GRADE ";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String masv2 = rs.getString("masv");
                int tiengAnh = rs.getInt("tienganh");
                int tinHoc = rs.getInt("tinhoc");
                int gdtc = rs.getInt("gdtc");

                Grade grade = new Grade(id, tiengAnh, tinHoc, gdtc, masv2);
                danhSachDiem.add(grade);
            }
        } catch (Exception e) {
            System.out.println("lỗi lấy dữ liệu điểm");
            e.printStackTrace();
        }
        return danhSachDiem;
    }

    public Grade layDiemTheoMaSV(String masv) {
        String sql = "select * from GRADE where masv = ?";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, masv);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String masv2 = rs.getString("masv");
                int tiengAnh = rs.getInt("tienganh");
                int tinHoc = rs.getInt("tinhoc");
                int gdtc = rs.getInt("gdtc");

                Grade grade = new Grade(id, tiengAnh, tinHoc, gdtc, masv2);
                return grade;
            }
//            if (!rs.next()) {
//                int id = danhSachDiem.size() + 1;
//                String masv2 = masv;
//                int tiengAnh = 0;
//                int tinHoc = 0;
//                int gdtc = 0;
//                Grade grade = new Grade(id, tiengAnh, tinHoc, gdtc, masv2);
//                return grade;
//            }

            conn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("lỗi lấy dữ liệu điểm");
            e.printStackTrace();
        }
        return null;
    }

    public void xoaDiem(String masv) {
        String sql = "delete grade where masv = ?";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, masv);
            int rs = st.executeUpdate();
            conn.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Lỗi khi xóa điểm");
            e.printStackTrace();
        }
    }

    public void capNhatDiem(Grade grade) {
        String sql = "update GRADE set TIENGANH=?,TINHOC =?,GDTC =? where MASV =?";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, grade.getTienganh());
            st.setInt(2, grade.getTinhoc());
            st.setInt(3, grade.getGdtc());
            st.setString(4, grade.getMasv());
            JOptionPane.showMessageDialog(null, grade.getMasv());

            int rs = st.executeUpdate();
            conn.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật điểm");
            e.printStackTrace();

        }
    }

    public void themDiem(Grade grade) {
        String sql = "insert into GRADE values(?,?,?,?,?)";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, grade.getId());
            System.out.println(grade.getId());
            st.setString(2, grade.getMasv());
            st.setInt(3, grade.getTienganh());
            st.setInt(4, grade.getTinhoc());
            st.setInt(5, grade.getGdtc());
            int rs = st.executeUpdate();

            conn.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm điểm");
            e.printStackTrace();
        }
    }

    public int layID() {
        String sql = "select top 1 * from GRADE order by id desc";
        int id;
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                return id;
            }

            conn.close();
            st.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Lỗi lấy id");
            e.printStackTrace();
        }
        return 0;
    }

}
