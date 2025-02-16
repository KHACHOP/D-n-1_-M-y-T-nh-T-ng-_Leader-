package app.view;

import app.model.NhanVien;
import app.service.NhanVienService;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.mindrot.jbcrypt.BCrypt;
import utils.Constant;

/**
 *
 * @author admin
 */
public class DangNhap extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    private NhanVienService nhanVienService = new NhanVienService();

    public DangNhap() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void login() {

        String sdt = txtEmail.getText();

        String matKhau = txtMatKhau.getText();
        if (sdt == null || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
            return;
        }
        if (!Constant.isValidPhoneNumber(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
            return;
        }
        if (matKhau == null || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
            return;
        }

        NhanVien nhanVien = nhanVienService.dangNhap(sdt, matKhau);
        if (nhanVien != null) {
            if (nhanVien.isTrangThaiXoa() != true) {
                JOptionPane.showMessageDialog(this, "Bạn không có quyển đăng nhập");
                return;
            }
            MainApplicationView1 applicationView = new MainApplicationView1(nhanVien);
            Constant.NHAN_VIEN = nhanVien;
            applicationView.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Tên tài khoản hoặc mật khẩu không đúng");
            return;
        }
    }

    public void sendMailDangNhap(String mk, String email) throws MessagingException {

        String host = "smtp.gmail.com";
        String port = "587";
        String username = "hopnkph46001@fpt.edu.vn";
        String password = "ADMIN123@1234567890";

        // Thông tin người gửi và người nhận
        String fromEmail = "hopnkph46001@fpt.edu.vn";
        String toEmail = email;
        System.out.println(email);

        // Cấu hình properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Xác thực tài khoản email
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // Tạo session
        Session session = Session.getInstance(properties, authenticator);

        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Thiết lập thông tin người gửi và người nhận
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            // Thiết lập tiêu đề email
            message.setSubject("Mật khẩu Nhân Viên TheHans ");

            // Thiết lập nội dung email
            String body = "Đổi mật khẩu";
            message.setText(body);
            String Htmlcode = "<h3>Mật Khẩu mới của bạn là : " + mk + "</h3>";
            String Htmlcode1 = "<h5>Vui lòng bảo mật mật khẩu</h5>";

            message.setContent(Htmlcode + Htmlcode1, "text/html;charset=UTF-8");
            // Gửi email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(this, "Có lỗi trong quá trình gửi mail. Vui lòng thử lại !");
        }

        //body mail
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGradiente1 = new swing.PanelGradiente();
        panelBorder1 = new swing.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        btnDangNhap = new swing.MyButton();
        jPanel1 = new javax.swing.JPanel();
        txtMatKhau = new swing.MyPasswordField();
        txtEmail = new swing.MyTextField1();
        cbShowPass = new javax.swing.JCheckBox();
        btnThoat = new swing.MyButton();
        btnQuenMatKhau = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelGradiente1.setColorPrimario(new java.awt.Color(255, 204, 102));
        panelGradiente1.setColorSecundario(new java.awt.Color(255, 204, 51));
        panelGradiente1.setPreferredSize(new java.awt.Dimension(700, 450));

        panelBorder1.setBackground(new java.awt.Color(204, 255, 204));
        panelBorder1.setPreferredSize(new java.awt.Dimension(400, 450));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hi !\n");
        panelBorder1.add(jLabel2);
        jLabel2.setBounds(30, 20, 250, 60);

        btnDangNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.setColorOver(new java.awt.Color(0, 204, 0));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        panelBorder1.add(btnDangNhap);
        btnDangNhap.setBounds(30, 270, 110, 40);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        txtMatKhau.setBackground(new java.awt.Color(51, 102, 0));
        txtMatKhau.setForeground(new java.awt.Color(204, 204, 255));
        txtMatKhau.setHint("Mật Khẩu");
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyPressed(evt);
            }
        });

        txtEmail.setBackground(new java.awt.Color(51, 102, 0));
        txtEmail.setForeground(new java.awt.Color(153, 153, 255));
        txtEmail.setCaretColor(new java.awt.Color(255, 102, 102));
        txtEmail.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtEmail.setHint("SDT HOẶC EMAIL");
        txtEmail.setInheritsPopupMenu(true);
        txtEmail.setVerifyInputWhenFocusTarget(false);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        cbShowPass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbShowPass.setForeground(new java.awt.Color(51, 102, 0));
        cbShowPass.setText("Hiển Thị Mật Khẩu");
        cbShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbShowPass))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbShowPass)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        txtEmail.getAccessibleContext().setAccessibleName("");

        panelBorder1.add(jPanel1);
        jPanel1.setBounds(0, 100, 310, 150);

        btnThoat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnThoat.setText("Thoát");
        btnThoat.setColorOver(new java.awt.Color(0, 204, 0));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        panelBorder1.add(btnThoat);
        btnThoat.setBounds(180, 270, 110, 40);

        btnQuenMatKhau.setForeground(new java.awt.Color(51, 51, 51));
        btnQuenMatKhau.setText("Quên mật khẩu ?");
        btnQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuenMatKhauMouseClicked(evt);
            }
        });
        panelBorder1.add(btnQuenMatKhau);
        btnQuenMatKhau.setBounds(180, 340, 100, 16);

        panelGradiente1.add(panelBorder1);
        panelBorder1.setBounds(200, 30, 310, 380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void cbShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbShowPassActionPerformed
        if (cbShowPass.isSelected()) {   // HIỂN THỊ MẬT KHẨU 
            txtMatKhau.setEchoChar((char) 0); //password = JPasswordField 
        } else {
            txtMatKhau.setEchoChar('*');
        }
    }//GEN-LAST:event_cbShowPassActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMatKhau.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtMatKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_txtMatKhauKeyPressed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);

    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuenMatKhauMouseClicked
        String sdt = JOptionPane.showInputDialog("Nhập số điện thoại nhân viên");
        if (sdt == null || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            return;
        }
        if (!Constant.isValidPhoneNumber(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
            return;
        }
        NhanVien nhanVien = nhanVienService.timTheoSdt(sdt);
        if (nhanVien == null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại nhân viên không có trong hệ thống");
            return;
        }
        // set mật khẩu mới. TỰ ĐỘNG SET MK MỚI 
        String matKhauMoi = "hopbn";
        String matKhauHash = BCrypt.hashpw(matKhauMoi, BCrypt.gensalt(Constant.saltRoundPassword));
        int kq = nhanVienService.doiMatKhauNhanVien(nhanVien.getMaNV(), matKhauHash);
        if (kq > 0) {
            try {
                sendMailDangNhap(matKhauMoi, nhanVien.getEmail());
            } catch (MessagingException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Mật khẩu đã được thay đổi. Mật khẩu mới đã được gửi về mail của bạn");
        } else {
            JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu không thành công.");

        }
        // gửi email mật khẩu mới về 


    }//GEN-LAST:event_btnQuenMatKhauMouseClicked

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

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
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton btnDangNhap;
    private javax.swing.JLabel btnQuenMatKhau;
    private swing.MyButton btnThoat;
    private javax.swing.JCheckBox cbShowPass;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private swing.PanelBorder panelBorder1;
    private swing.PanelGradiente panelGradiente1;
    private swing.MyTextField1 txtEmail;
    private swing.MyPasswordField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
