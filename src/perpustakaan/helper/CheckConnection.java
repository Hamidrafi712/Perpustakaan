/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaan.helper;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Rapiii
 */
public class CheckConnection {
    
    public static void main(String[] args) { //deklarasi main method
        isConnected();//Memanggil Method isConnetced
        showBook();//Memanggil Method showBook
    }
    
    private static boolean isConnected(){//dibuat untuk menangani eror yang mungkin terjadi saat proses koneksi dengan database
        try { //membuat try-catch untuk menangani error
            ConnectionHelper.getConnection(); // memanggil class connectionhelper untuk mendapatkan koneksi, jika berhasil maka
            System.out.println("Database COnnected"); //print 
            return true;
        }
        catch(SQLException ex){
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex); //untuk melakukan logging untuk pencatatan error
            System.out.println("Failed to connected database"); //print
            return false; //mengembalikan nilai false bila gagal konek 
        }
        
    }
    public static void showBook() {
        try {//membuat try-catch untuk menangani error
            Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
            Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            ResultSet rs = stmn.executeQuery("Select * from buku"); //Melalukan ExecuteQuery agar bisa mengambil data dari database //lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()){ //Melakukan iterable atau looping bila memiliki datanya di dalam database
                System.out.println("ID Buku         : " + rs.getString("id_buku")
                        +"\nJudul Buku      : "+rs.getString("judul_buku")
                        +"\nPengarang       : "+rs.getString("pengarang")
                        +"\nTahun Terbit    : "+rs.getString("tahun_terbit"));
            }
        }
        catch (SQLException ex){//menggunakan SQLException untuk menangkap error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex); //untuk melakukan logging untuk pencatatan error
        }
    }
}
