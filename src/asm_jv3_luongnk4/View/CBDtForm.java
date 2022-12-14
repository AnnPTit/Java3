/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.View;

import asm_jv3_luongnk4.Model.Student;
import asm_jv3_luongnk4.Service.StudentService;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class CBDtForm extends javax.swing.JFrame {

    /**
     * Creates new form CBDtForm
     */
    DefaultTableModel model;
    List<Student> list;
    StudentService studentService;
    String hinh;

    public CBDtForm() {
        initComponents();
        model = (DefaultTableModel) tblSinhVien.getModel();
        list = new ArrayList<>();
        studentService = new StudentService();
        layDanhSachSinhVien();

    }

    public void layDanhSachSinhVien() {
        list = this.studentService.layDanhSachSinhVien();
        for (Student student : list) {
            String masv = student.getMasv();
            String hoTen = student.getHoten();
            String email = student.getEmail();
            String sdt = student.getSodt();
            boolean gioiTinh = student.isGioitinh();
            String diaChi = student.getDiachi();
            String hinh = student.getHinh();

            model.addRow(new Object[]{masv, hoTen, email, sdt, gioiTinh ? "Nam" : "Nữ", diaChi, hinh});
        }
    }

    public void luuSinhVien(Student student) {

    }

    public Student validateStudent() {
        String masv = txtMasv.getText();
        if (masv.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String ten = txtHoten.getText();
        if (ten.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sinh viên trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String email = txtEmail.getText();
        if (email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email sinh viên trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String soDt = txtSodt.getText();
        if (soDt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại sinh viên trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        boolean gioiTinh;

        if (!rBtnNam.isSelected() && !rBtnNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn giới tính ", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (rBtnNam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        String diaChi = txtDiachi.getText();
        if (diaChi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại sinh viên trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String hinh = this.hinh;
        Student student = new Student(masv, ten, email, soDt, diaChi, hinh, gioiTinh, null);
        return student;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMasv = new javax.swing.JTextField();
        txtHoten = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSodt = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        rBtnNam = new javax.swing.JRadioButton();
        rBtnNu = new javax.swing.JRadioButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDele = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSinhVien = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Quản Lý Sinh Viên");

        jLabel2.setText("Mã sv");

        jLabel3.setText("Họ Tên");

        jLabel4.setText("Email");

        jLabel5.setText("Số ĐT ");

        jLabel6.setText("Giới tính");

        jLabel7.setText("Địa chỉ");

        buttonGroup1.add(rBtnNam);
        rBtnNam.setText("Nam");

        buttonGroup1.add(rBtnNu);
        rBtnNu.setText("Nữ");

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
                "Masv", "Họ tên", "Email", "Số ĐT", "Giới tính", "Địa chỉ", "Hình "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
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
        jScrollPane1.setViewportView(tblSinhVien);

        jLabel9.setBackground(new java.awt.Color(204, 255, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ấn để thêm ảnh ");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rBtnNam)
                                .addGap(30, 30, 30)
                                .addComponent(rBtnNu))
                            .addComponent(txtMasv)
                            .addComponent(txtHoten)
                            .addComponent(txtEmail)
                            .addComponent(txtSodt)
                            .addComponent(txtDiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMasv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSodt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rBtnNam)
                                .addComponent(rBtnNu)))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDele)
                            .addComponent(btnUpdate))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSinhVienMouseClicked
        // TODO add your handling code here:
        int index = tblSinhVien.getSelectedRow();

        String masv = (String) model.getValueAt(index, 0);
        String hoten = (String) model.getValueAt(index, 1);
        String emial = (String) model.getValueAt(index, 2);
        String sodt = (String) model.getValueAt(index, 3);
        String gioiTinh = (String) model.getValueAt(index, 4);
        String diaChi = (String) model.getValueAt(index, 5);

        txtMasv.setText(masv);
        txtHoten.setText(hoten);
        txtEmail.setText(emial);
        txtSodt.setText(sodt);
        if (gioiTinh.equalsIgnoreCase("nam")) {
            rBtnNam.setSelected(true);
        } else {
            rBtnNu.setSelected(true);
        }
        txtDiachi.setText(diaChi);

    }//GEN-LAST:event_tblSinhVienMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        txtMasv.setText("");
        txtHoten.setText("");
        txtEmail.setText("");
        txtSodt.setText("");
        txtDiachi.setText("");
        rBtnNam.setSelected(true);

    }//GEN-LAST:event_btnNewActionPerformed

    private Image fitimage(Image img, int w, int h) {
        BufferedImage resizedimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedimage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return resizedimage;
    }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Student student = validateStudent();

        if (student != null) {
            String ma = student.getMasv();
            Student st = studentService.laySinhVienTheoMa(ma);
            if(st!= null){
                JOptionPane.showMessageDialog(this,"Sinh viên đã tồn tại","ERORR",JOptionPane.ERROR_MESSAGE);
                return;
            }


            studentService.themSinhVien(student);
            String masv = student.getMasv();
            String hoTen = student.getHoten();
            String email = student.getEmail();
            String sdt = student.getSodt();
            boolean gioiTinh = student.isGioitinh();
            String diaChi = student.getDiachi();
            String hinh = student.getHinh();

            model.addRow(new Object[]{masv, hoTen, email, sdt, gioiTinh ? "Nam" : "Nữ", diaChi, hinh});
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
//fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            JOptionPane.showMessageDialog(rootPane, "Selected file: " + selectedFile.getAbsolutePath());
            try {
                BufferedImage myPicture = ImageIO.read(new File(selectedFile.getAbsolutePath()));
                ImageIcon imageIcon = new ImageIcon(fitimage(myPicture, jLabel9.getWidth(), jLabel9.getHeight()));
                jLabel9.setIcon(imageIcon);

                hinh = selectedFile.getAbsolutePath();
            } catch (IOException ex) {
                Logger.getLogger(CBDtForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnDeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleActionPerformed
        // TODO add your handling code here:
        int row = tblSinhVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn sinh viên cần xóa ", "ERORR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String masv = (String) model.getValueAt(row, 0);
        // xoa database
        studentService.xoaSinhVien(masv);
        // 
        model.removeRow(row);
      
    }//GEN-LAST:event_btnDeleActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        Student student = validateStudent();
        if (student != null) {
            int row = tblSinhVien.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(this,"Chọn sinh viên cần xóa","Erorr",JOptionPane.ERROR_MESSAGE);
                return;
                
            }
            String ma = (String) model.getValueAt(row, 0);
            student.setMasv(ma);
            // cap nhat database 
            studentService.capNhat(student);
            // cap nhat view 
            String masv = student.getMasv();
            String hoTen = student.getHoten();
            String email = student.getEmail();
            String sdt = student.getSodt();
            boolean gioiTinh = student.isGioitinh();
            String diaChi = student.getDiachi();
            String hinh = student.getHinh();

            model.setValueAt(masv, row, 0);
            model.setValueAt(hoTen, row, 1);
            model.setValueAt(email, row, 2);
            model.setValueAt(sdt, row, 3);
            model.setValueAt(gioiTinh ? "Nam" : "Nữ", row, 4);
            model.setValueAt(diaChi, row, 5);
            model.setValueAt(hinh, row, 6);

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(CBDtForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CBDtForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CBDtForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CBDtForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CBDtForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDele;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rBtnNam;
    private javax.swing.JRadioButton rBtnNu;
    private javax.swing.JTable tblSinhVien;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMasv;
    private javax.swing.JTextField txtSodt;
    // End of variables declaration//GEN-END:variables
}
