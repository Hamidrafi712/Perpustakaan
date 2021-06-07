/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.ui;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import perpustakaan.helper.ConnectionHelper;
import javax.swing.JOptionPane;
import perpustakaan.model.Book;


/**
 *
 * @author Rapiii
 */
public class AddBook extends javax.swing.JFrame {
    //deklarasi variable
    String penerbit = "";
    String formtitle = "";
    int bukuID = 0;
    Book bukuGlobal;
    /**
     * Creates new form AddBook
     */
    public AddBook() { //method untuk mengset tampilan
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public AddBook(String title, int id){
        initComponents();
        setLocationRelativeTo(null);
        AddBook.setText(title);
        formtitle = title;
        bukuID = id;
        try {
            Connection conn = ConnectionHelper.getConnection(); //digunanakan untuk membangun koneksi
            Statement stm = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            System.out.println(id);
            ResultSet rs = stm.executeQuery("SELECT * FROM buku WHERE id_buku = " + id); //Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet
            while (rs.next()) { //Melakukan iterable atau looping bila memiliki datanya di dalam database
                //menset data yang ditambahkann oleh  user
                bookTextField.setText(rs.getString("judul_buku"));
                authorTextField.setText(rs.getString("pengarang"));
                switch (rs.getString("penerbit")) {
                    case "Jember Pustaka" ->
                        jbrPustakaRadBtn.setSelected(true);
                    case "Gramedia" ->
                        gramediaRadBtn.setSelected(true);
                    case "Polije Press" ->
                        polijePresRadBtn.setSelected(true);
                    case "RRI Library" ->
                        rriLibRadBtn.setSelected(true);
                    default ->
                        System.out.println("HMMMMM");
                }
                thnTerbitComboBox.setSelectedItem(rs.getString("tahun_terbit"));
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
//        validateExite();       
    }
    
    //method yang digunakan unutk close frame/windows
//    private void validateExite() {
//        AddBook.this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e); 
//            }
//
//        });
//    }

    //method unutk menampilkann informasi berhasil atau tidak menyimpan data buku
    public void showMessage(String message, int type){
        if(type == 1){
            JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, message, "Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //method untuk mengambil data dari user dan menyimpan data pada localhost
    void radioPenerbit(){
        if(jbrPustakaRadBtn.isSelected()){
            penerbit = "Jember Pustaka";
        } else if (gramediaRadBtn.isSelected()){
            penerbit = "Gramedia";
     
        } else if (polijePresRadBtn.isSelected()){
            penerbit = "Polije Press";
        } else if (rriLibRadBtn.isSelected()){
            penerbit = "RRI Library";
        }
    }
    
    //method update untuk menghubungkan antara NetBeans dengan database, emngambil data dan menampilkan di frame lalu di ubah dan disimpan kembali.
    void updateBuku(int id) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            radioPenerbit();
            System.out.println(authorTextField.getText());
            String query = "UPDATE buku SET judul_buku = '" + bookTextField.getText() + "',"
                    + "tahun_terbit = '" + thnTerbitComboBox.getSelectedItem() + "',"
                    + "pengarang = '" + authorTextField.getText() + "',"
                    + "penerbit = '" + penerbit + "' WHERE id_buku = " + id + " ";

            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data sudah di update ", "infomasi", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak terupdate" + e.getMessage(), "infomasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //method untuk menguhubungkan antara NB dengan localhost(database)
    void tambah(){
        try{
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            radioPenerbit();
            String query = "INSERT INTO `buku` (`judul_buku`, `tahun_terbit`, `pengarang`, `penerbit`) "
                    + "VALUES ('"+bookTextField.getText() +"', '"+ thnTerbitComboBox.getSelectedItem() + "', '"+ authorTextField.getText()+
                    "', '"+ penerbit + "');";
            
            stm.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Data Sudah di Tambahkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e){
             System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        AddBook = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbrPustakaRadBtn = new javax.swing.JRadioButton();
        gramediaRadBtn = new javax.swing.JRadioButton();
        polijePresRadBtn = new javax.swing.JRadioButton();
        rriLibRadBtn = new javax.swing.JRadioButton();
        thnTerbitComboBox = new javax.swing.JComboBox<>();
        authorTextField = new javax.swing.JTextField();
        bookTextField = new javax.swing.JTextField();
        submitBtn = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        AddBook.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        AddBook.setText("Tambahkan Buku");

        jLabel2.setText("Judul");

        jLabel3.setText("Pengarang");

        jLabel4.setText("Penerbit");

        jLabel5.setText("Tahun Terbit");

        jbrPustakaRadBtn.setText("Jember Pustaka");
        jbrPustakaRadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbrPustakaRadBtnActionPerformed(evt);
            }
        });

        gramediaRadBtn.setText("Gramaedia");

        polijePresRadBtn.setText("Polije Press");

        rriLibRadBtn.setText("RRI Library");

        thnTerbitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021", " " }));
        thnTerbitComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thnTerbitComboBoxActionPerformed(evt);
            }
        });

        bookTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookTextFieldActionPerformed(evt);
            }
        });

        submitBtn.setText("Simpan");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thnTerbitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jbrPustakaRadBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(polijePresRadBtn))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(gramediaRadBtn)
                                            .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(submitBtn))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(rriLibRadBtn))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(AddBook)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddBook)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbrPustakaRadBtn)
                        .addComponent(polijePresRadBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rriLibRadBtn)
                    .addComponent(gramediaRadBtn))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(thnTerbitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBtn)
                    .addComponent(btnBatal))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbrPustakaRadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbrPustakaRadBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbrPustakaRadBtnActionPerformed

    private void bookTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookTextFieldActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (bukuID == 0) {
            tambah();
        } else {
            updateBuku(bukuID);
        }
        new ShowBookFrame().setVisible(true);
    }//GEN-LAST:event_submitBtnActionPerformed

    private void thnTerbitComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thnTerbitComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thnTerbitComboBoxActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        new ShowBookFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

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
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddBook;
    private javax.swing.JTextField authorTextField;
    private javax.swing.JTextField bookTextField;
    private javax.swing.JButton btnBatal;
    private javax.swing.JRadioButton gramediaRadBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jbrPustakaRadBtn;
    private javax.swing.JRadioButton polijePresRadBtn;
    private javax.swing.JRadioButton rriLibRadBtn;
    private javax.swing.JButton submitBtn;
    private javax.swing.JComboBox<String> thnTerbitComboBox;
    // End of variables declaration//GEN-END:variables
}
