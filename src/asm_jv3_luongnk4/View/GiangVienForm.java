/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.View;

import asm_jv3_luongnk4.Model.Grade;
import asm_jv3_luongnk4.Model.Student;
import asm_jv3_luongnk4.Repository.GradeRepository;
import asm_jv3_luongnk4.Service.GradeService;
import asm_jv3_luongnk4.Service.StudentService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class GiangVienForm extends javax.swing.JFrame {

    /**
     * Creates new form GiangVienForm
     */
    DefaultTableModel model;
    StudentService studentService;
    GradeService gradeService;

    List<Student> list;
    int itemPerPage = 3;
    int totalItem;
    int currentPage = 1;
    int numPage;
    List<Student> listPagin;

    public GiangVienForm() {
        initComponents();
        model = (DefaultTableModel) tblSinhVien.getModel();
        studentService = new StudentService();
        gradeService = new GradeService();
        list = new ArrayList<>();
        layDanhSachSinhVien();
        listPagin = new ArrayList<>();
        totalItem = list.size();
        numPage = totalItem / itemPerPage;
        if (totalItem % itemPerPage != 0) {
            numPage++;
        }
        int start = (currentPage - 1) * itemPerPage;
        int end = start + itemPerPage - 1;
        //loadtable(list.subList(start, end));
        listPagin = list;
        if (list.size() == 2) {
            itemPerPage = 2;
        }
        if (list.size() == 1) {
            itemPerPage = 1;
        }
        if (list.size() == 1) {
            totalItem = 1;
        }
        if(list.size()==0){
            loadTable(list);
            return;
        }

        loadTable(list.subList(0, itemPerPage));
    }

    public void layDanhSachSinhVien() {
        list = this.studentService.layDanhSachSinhVien();

        loadTable(list);

//        for (Student student : list) {
////            String masv = student.getMasv();
////            String hoTen = student.getHoten();
////            int tiengAnh =student.getGrade().getTienganh();
////            int tinHoc = student.getGrade().getTinhoc();
////            int gdtc = student.getGrade().getGdtc();
////            double diemTB = Double.valueOf((tiengAnh+tinHoc+gdtc)/3);
////  model.addRow(new Object[]{masv,hoTen,tiengAnh,tinHoc,gdtc,diemTB});
//
//        }
    }

    public void loadTable(List<Student> list) {
        model.setNumRows(0);
        for (Student student : list) {

            double diemTB = (student.getGrade().getTienganh() + student.getGrade().getTinhoc() + student.getGrade().getGdtc()) / 3;
            model.addRow(new Object[]{student.getMasv(), student.getHoten(), student.getGrade().getTienganh(), student.getGrade().getTinhoc(), student.getGrade().getGdtc(), diemTB});
        }
    }

    public void getStuden(int index) {
//        Student student = list.get(index);

//        lbHoten.setText(student.getHoten());
//        txtMasv2.setText(student.getMasv());
//        txtTiengAnh.setText(String.valueOf(student.getGrade().getTienganh()));
//        txtTinhoc.setText(String.valueOf(student.getGrade().getTinhoc()));
//        txtGDTC.setText(String.valueOf(student.getGrade().getGdtc()));
//        double diemTB = (student.getGrade().getTienganh() + student.getGrade().getTinhoc() + student.getGrade().getGdtc()) / 3;
//        lbDiemTB.setText(String.valueOf(diemTB));
        String masv = model.getValueAt(index, 0).toString();
        String hoten = model.getValueAt(index, 1).toString();
        int tiengAnh = (int) model.getValueAt(index, 2);
        int tinHoc = (int) model.getValueAt(index, 3);
        int gdtc = (int) model.getValueAt(index, 4);
        double diemTB = (tiengAnh + tinHoc + gdtc) / 3;

        txtMasv2.setText(masv);
        txtTiengAnh.setText(String.valueOf(tiengAnh));
        txtTinhoc.setText(String.valueOf(tinHoc));
        txtGDTC.setText(String.valueOf(gdtc));
        lbDiemTB.setText(String.valueOf(diemTB));
        lbHoten.setText(hoten);

    }

    public Grade validateDiem() {
        String masv = txtMasv2.getText();

        if (masv.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên trống trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;

        }
        int tiengAnh = Integer.valueOf(txtTiengAnh.getText());
        if (txtTiengAnh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điểm tiếng anh trống ", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (tiengAnh > 10 || tiengAnh < 0) {
            JOptionPane.showMessageDialog(this, "Điểm <10 & > 0 ", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int tinHoc = Integer.valueOf(txtTinhoc.getText());
        if (txtTinhoc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điểm Tin trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
          if (tinHoc > 10 || tinHoc< 0) {
            JOptionPane.showMessageDialog(this, "Điểm <10 & > 0 ", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int gdtc = Integer.valueOf(txtGDTC.getText());
        if (txtGDTC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điểm GDTC trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
          if (gdtc > 10 || gdtc < 0) {
            JOptionPane.showMessageDialog(this, "Điểm <10 & > 0 ", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Grade grade1 = gradeService.layDiemTheoMaSV(masv);
        Grade grade = new Grade(grade1.getId(), tiengAnh, tinHoc, gdtc, grade1.getMasv());
        return grade;
    }

    public void xoaSinhVien(String masv) {
        this.studentService.xoaSinhVien(masv);
    }

    public void upDate(Grade grade) {
        this.gradeService.upDateDiem(grade);
    }

    public List<Student> sapXep() {
        return this.studentService.sapXep();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMasv = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbHoten = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMasv2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTiengAnh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTinhoc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGDTC = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbDiemTB = new javax.swing.JLabel();
        btnFrist = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDele = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSinhVien = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Quản lí điểm sinh viên ");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Mã Sv : ");

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/search-icon-24.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addComponent(txtMasv, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMasv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Tìm kiếm");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Họ Tên Sinh Viên : ");

        lbHoten.setForeground(new java.awt.Color(0, 0, 255));
        lbHoten.setText("Nguyễn văn tèo");

        jLabel6.setText("Mã Sv : ");

        jLabel7.setText("Tiếng Anh ");

        jLabel8.setText("Tin học");

        jLabel9.setText("Giáo dục TC ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("Điểm TB ");

        lbDiemTB.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbDiemTB.setForeground(new java.awt.Color(0, 0, 255));
        lbDiemTB.setText("9.0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMasv2)
                            .addComponent(txtTiengAnh)
                            .addComponent(txtTinhoc)
                            .addComponent(txtGDTC, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(lbDiemTB))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel10))))
                    .addComponent(lbHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMasv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTiengAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTinhoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDiemTB))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtGDTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel10)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        btnFrist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/Button-First-icon-48.png"))); // NOI18N
        btnFrist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFristActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/Button-Forward-icon-48.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/Fast-backward-icon-48.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/Button-Last-icon-48.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/new-icon-16.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/Save-icon.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDele.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/Actions-edit-delete-icon-16.png"))); // NOI18N
        btnDele.setText("Delete");
        btnDele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asm_jv3_luongnk4/icons/Actions-document-edit-icon-16.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tblSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MASV", "Họ Tên ", "Tiếng Anh ", "Tin Học", "GDTC", "Điểm Tb"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSinhVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSinhVien);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(260, 260, 260))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnFrist)
                                            .addGap(49, 49, 49)
                                            .addComponent(btnNext)
                                            .addGap(41, 41, 41)
                                            .addComponent(btnPre)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnLast)))
                                    .addGap(99, 99, 99)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnDele, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(btnUpdate)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(btnNew)
                        .addGap(38, 38, 38)
                        .addComponent(btnSave)
                        .addGap(38, 38, 38)
                        .addComponent(btnDele)
                        .addGap(41, 41, 41)
                        .addComponent(btnUpdate)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLast)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFrist)
                        .addComponent(btnNext)
                        .addComponent(btnPre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSinhVienMouseClicked
        // TODO add your handling code here:
        int index = tblSinhVien.getSelectedRow();
        getStuden(index);
    }//GEN-LAST:event_tblSinhVienMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        // TODO add your handling code here:
        String masv = txtMasv.getText();
        if (masv.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã sinh viên", "ERORR", JOptionPane.ERROR_MESSAGE);
            txtMasv.requestFocus();
            return;
        }
        Student student = studentService.laySinhVienTheoMa(masv);
        if (student != null) {
            lbHoten.setText(student.getHoten());
            txtMasv2.setText(student.getMasv());
            txtTiengAnh.setText(String.valueOf(student.getGrade().getTienganh()));
            txtTinhoc.setText(String.valueOf(student.getGrade().getTinhoc()));
            txtGDTC.setText(String.valueOf(student.getGrade().getGdtc()));
            double diemTB = (student.getGrade().getTienganh() + student.getGrade().getTinhoc() + student.getGrade().getGdtc()) / 3;
            lbDiemTB.setText(String.valueOf(diemTB));
        } else {
            JOptionPane.showMessageDialog(this, "Mã sinh viên không tồn tại", "ERORR", JOptionPane.ERROR_MESSAGE);
            lbHoten.setText("");
            lbDiemTB.setText("");
            return;
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnFristActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFristActionPerformed
        // TODO add your handling code here:

        int end = 0;
        if (list.size() == 1) {
            end = 1;
        } else if (list.size() == 2) {
            end = 2;

        } else {
            end = 3;
        }
        loadTable(list.subList(0, end));
        currentPage = 1;


    }//GEN-LAST:event_btnFristActionPerformed

    private void btnDeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleActionPerformed
        // TODO add your handling code here:
        int row = tblSinhVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa", "ERORR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String id = (String) model.getValueAt(row, 0);
        // xoa database
        this.studentService.xoaSinhVien(id);
        JOptionPane.showMessageDialog(this, "xóa thành công");
        // xóa giao diện  
        model.removeRow(row);
        // xoa text 
        btnNewActionPerformed(evt);


    }//GEN-LAST:event_btnDeleActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        int start = 0;
        if (list.size() == 1 || list.size() == 2) {
            start = 0;
        } else {
            start = list.size() - 3;
        }
        loadTable(list.subList(start, list.size()));
        currentPage = list.size() - 3;

    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (currentPage < numPage) {
            // JOptionPane.showMessageDialog(this,"Max num page ! ");
            currentPage++;
            totalItem = listPagin.size();

            int start = (currentPage - 1) * itemPerPage;
            int end = start + itemPerPage;
            if (end > totalItem) {
                end = totalItem;
            }

            loadTable(listPagin.subList(start, end));
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        if (currentPage > 1) {
            // JOptionPane.showMessageDialog(this,"Min num page");
            currentPage--;
            totalItem = listPagin.size();
            numPage = (totalItem - 1) / itemPerPage + 1;

            int start = (currentPage - 1) * itemPerPage;
            int end = start + itemPerPage;
            if (end > totalItem) {
                end = totalItem;
            }

            loadTable(listPagin.subList(start, end));

        }
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        Grade grade = validateDiem();

        if (grade != null) {
            int row = tblSinhVien.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Chọn dòng cần cập nhật", "ERORR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String masv = (String) model.getValueAt(row, 0);
            grade.setMasv(masv);
            // data 
            upDate(grade);
            //
            int tiengAnh = grade.getTienganh();
            int tin = grade.getTinhoc();
            int gdtc = grade.getGdtc();

            double diemTB = (tiengAnh + tin + gdtc) / 3;

            //
            model.setValueAt(tiengAnh, row, 2);
            model.setValueAt(tin, row, 3);
            model.setValueAt(gdtc, row, 4);
            model.setValueAt(diemTB, row, 5);
            lbDiemTB.setText(String.valueOf(diemTB));
            JOptionPane.showMessageDialog(this, "cập nhật thành công");
            layDanhSachSinhVien();
            list = sapXep();
            loadTable(list.subList(0, 3));
            currentPage = 1;

        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        txtGDTC.setText("");
        txtMasv.setText("");
        txtTiengAnh.setText("");
        txtMasv2.setText("");
        txtTinhoc.setText("");
        lbHoten.setText("");
        lbDiemTB.setText("");

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Giáo viên không thể thêm sinh viên ", "ERORR", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GiangVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiangVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiangVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiangVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiangVienForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDele;
    private javax.swing.JButton btnFrist;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbDiemTB;
    private javax.swing.JLabel lbHoten;
    private javax.swing.JTable tblSinhVien;
    private javax.swing.JTextField txtGDTC;
    private javax.swing.JTextField txtMasv;
    private javax.swing.JTextField txtMasv2;
    private javax.swing.JTextField txtTiengAnh;
    private javax.swing.JTextField txtTinhoc;
    // End of variables declaration//GEN-END:variables
}
