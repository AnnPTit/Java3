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
public class StudentRepository {

    List<Student> danhSachSinhVien;
    DBconnect dBconnect;
    GradeRepository gradeRepository;
    int id = 1;
    

    public StudentRepository() {
        danhSachSinhVien = new ArrayList<>();
        dBconnect = new DBconnect();
        gradeRepository = new GradeRepository();

    }

    public List<Student> layDanhSachSinhVien() {
        String sql = "SELECT * FROM STUDENTS ";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Student student = mapping(rs);
                if (student != null) {
                    danhSachSinhVien.add(student);
                }

            }
            conn.close();
            st.close();
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu sinh viên");
            e.printStackTrace();
        }
        return danhSachSinhVien;
    }

    public Student laySinhVienTheoMa(String masv) {
        String sql = "SELECT * FROM STUDENTS where masv = ?";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, masv);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Student student = mapping(rs);
                if (student != null) {
                    return student;
                }
//              
            }
            conn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy sinh viên theo mã");
            e.printStackTrace();
        }
        return null;
    }

    public Student mapping(ResultSet rs) {
        try {
            if (rs != null) {
                String masv = rs.getString("masv");
                String hoTen = rs.getString("hoten");
                String email = rs.getString("email");
                String sodt = rs.getString("sodt");
                boolean gioiTinh = rs.getBoolean("gioitinh");
                String diaChi = rs.getString("diachi");
                String hinh = rs.getString("hinh");
                Grade grade = gradeRepository.layDiemTheoMaSV(masv);

                Student student = new Student(masv, hoTen, email, sodt, diaChi, hinh, gioiTinh, grade);
                return student;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "lỗi mapping");
            e.printStackTrace();
        }
        return null;
    }

    public void xoaThongTinSinhVien(String masv) {
        String sql = "delete students where masv = ?";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, masv);
            gradeRepository.xoaDiem(masv);
            int rs = st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công ");

            conn.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa sinh viên");
            e.printStackTrace();
        }

    }

    public void themSinhVien(Student student) {
        String sql = "insert into STUDENTS values(?,?,?,?,?,?,?) ";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, student.getMasv());

            st.setString(2, student.getHoten());
            st.setString(3, student.getEmail());
            st.setString(4, student.getSodt());
            st.setBoolean(5, student.isGioitinh());
            st.setString(6, student.getDiachi());
            st.setString(7, student.getHinh());
            Grade grade = new Grade();
            int id = gradeRepository.layID();
            if(id==0){
                id =1;
            }
            id++;
            grade.setId(id);
           

            grade.setMasv(student.getMasv());
            grade.setTienganh(0);
            grade.setTinhoc(0);
            grade.setGdtc(0);

            int rs = st.executeUpdate();
            gradeRepository.themDiem(grade);
            JOptionPane.showMessageDialog(null, "Thêm thành công");

            conn.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sinh viên");
            e.printStackTrace();
        }

    }

    public void capNhat(Student student) {
        String sql = "  update STUDENTS set HOTEN=?,EMAIL=?,SODT=?,GIOITINH=?,DIACHI=?,HINH=? where MASV =?";
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, student.getHoten());
            st.setString(2, student.getEmail());
            st.setString(3, student.getSodt());
            st.setBoolean(4, student.isGioitinh());
            st.setString(5, student.getDiachi());
            st.setString(6, student.getHinh());
            st.setString(7, student.getMasv());
            int rs = st.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            conn.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật sinh viên");
            e.printStackTrace();
        }

    }

    public List<Student> sapXep() {
        String sql = "select masv from GRADE order by TIENGANH+TINHOC+GDTC desc";
        List<Student> listSapXep = new ArrayList<>();
        try {
            Connection conn = dBconnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                String masv2 = rs.getString("masv");

                Student student = new Student();
                student = laySinhVienTheoMa(masv2);

                String masv = student.getMasv();
                String hoTen = student.getHoten();
                String email = student.getEmail();
                String sodt = student.getSodt();
                boolean gioiTinh = student.isGioitinh();
                String diaChi = student.getDiachi();
                String hinh = student.getHinh();
                listSapXep.add(student);
            }

        } catch (Exception e) {
            System.out.println("Lỗi sắp xếp");
            e.printStackTrace();
        }
        return listSapXep;
    }

}
