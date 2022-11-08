/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.Service;

import asm_jv3_luongnk4.Model.Grade;
import asm_jv3_luongnk4.Model.Student;
import asm_jv3_luongnk4.Repository.GradeRepository;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class GradeService {
    GradeRepository gradeRepository;
    public GradeService() {
        gradeRepository = new GradeRepository();
    }
    public void upDateDiem(Grade grade){
        this.gradeRepository.capNhatDiem(grade);
    }
     public Grade layDiemTheoMaSV(String masv) {
         return this.gradeRepository.layDiemTheoMaSV(masv);
     }
     
    
    
}
