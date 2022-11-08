/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.Service;

import asm_jv3_luongnk4.Model.Student;
import asm_jv3_luongnk4.Repository.StudentRepository;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class StudentService {

    StudentRepository studentRepository;
    public StudentService() {
        studentRepository = new StudentRepository();
    }
    public List<Student> layDanhSachSinhVien(){
        return this.studentRepository.layDanhSachSinhVien();
    }
    public Student laySinhVienTheoMa(String masv ){
        return this.studentRepository.laySinhVienTheoMa(masv);
    }
    public void xoaSinhVien(String masv){
        this.studentRepository.xoaThongTinSinhVien(masv);
    }
    public void themSinhVien(Student student){
        this.studentRepository.themSinhVien(student);
    }
     public void capNhat(Student student){
         this.studentRepository.capNhat(student);
     }
     public List<Student> sapXep() {
          return this.studentRepository.sapXep();
      }
    
    
    
}
