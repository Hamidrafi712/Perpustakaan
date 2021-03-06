/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.ui;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import perpustakaan.manager.BookManager;
import perpustakaan.model.Book;
import perpustakaan.helper.ConnectionHelper;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Rapiii
 */
public class ShowBookFrame extends javax.swing.JFrame {

    /**
     * Creates new form ShowBookFrame
     */
    public ShowBookFrame() {
        initComponents();
        setLocationRelativeTo(null); //set lokasi agar frame tampil ditengah layar
        initTableBook();
        loadBook();        
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        refreshBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Daftar Buku");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 402));

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit"
            }
        ));
        jScrollPane1.setViewportView(bookTable);

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel1))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(refreshBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBtn)
                .addGap(18, 18, 18)
                .addComponent(updateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteBtn)
                .addGap(33, 33, 33))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshBtn)
                    .addComponent(addBtn)
                    .addComponent(updateBtn)
                    .addComponent(deleteBtn))
                .addGap(75, 75, 75))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rootPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Dekalrasi variable
    public static DefaultTableModel tableModel;
    public static List<Book> listBook;
    
    public void initTableBook() {
        //Deklarasi array bookColumns tipe data String
        String[] bookColumns = new String[]{"ID", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit"};
        
        int[] columnWidth = {//Deklarasi array columWidth tipe data int
            70, 190, 210, 90, 90
        };
        
        tableModel = new DefaultTableModel(bookColumns, 0);//Deklarasi tableModel 
        bookTable.setModel(tableModel);//Set table model
        bookTable.setRowHeight(20);//set jumlah baris table
        
        int i = 0;
        for (int width : columnWidth) {
            TableColumn column = bookTable.getColumnModel().getColumn(i++);//set column bookTable
            column.setMaxWidth(width);//set lebar table
            column.setPreferredWidth(width);//set lebar table                
        }       
    }
    
    public static void loadBook(){//Method untuk memuat data buku
        listBook = new ArrayList<>();//Deklarasi arrayList
        listBook = BookManager.showAllBook();//lalu nilainya adalah return nilai dari method showAllBook
        tableModel.setRowCount(0);//set unutk jumlah baris menjadi 0
        listBook.forEach(book ->{//Melakukan perulangan untuk memasukkan data buku
                tableModel.addRow(new Object[]{
                    book.getId_buku(),
                    book.getJudul_buku(),
                    book.getPengarang(),
                    book.getPenerbit(),
                    book.getTahun_terbit()
                });
        });      
    }
    
    
    
    
    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel)bookTable.getModel();
        model.setRowCount(0);
        loadBook();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        new AddBook().setVisible(true); //action saat klik AddButton untuk mengakses class AddBook 
        dispose(); //saat klik AddButton maka akan close frame ShowBook
    }//GEN-LAST:event_addBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        int selectedRow = bookTable.getSelectedRow(); //fungsi untuk mengambil data 
        if (selectedRow == -1){
            showMessage("Pilih Baris dulu!", 2);
        }else{
           Book buku = listBook.get(selectedRow);
           new AddBook("Update Buku", buku.getId_buku()).setVisible(true);
        }
        dispose();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1){
            showMessage("Pilihlah Baris Terlebih Dahulu", 2);
        }else{
            int option = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data ini?", "Hapus Data", JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION){
                try{
                    Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
                    Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
                    Book book = listBook.get(selectedRow);
                    stmn.executeUpdate("DELETE FROM buku WHERE id_buku = " + book.getId_buku());
                    loadBook();
                }catch (Exception e){
                    System.out.println("ERROR" + e.getMessage());
                }
            }
        }
        
    }//GEN-LAST:event_deleteBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ShowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowBookFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTable bookTable;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

    private void showMessage(String pilih_Baris_dulu, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
