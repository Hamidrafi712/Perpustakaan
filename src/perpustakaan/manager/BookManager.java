/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.manager;
import java.util.ArrayList;
import java.util.List;
import perpustakaan.model.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import perpustakaan.helper.CheckConnection;
import perpustakaan.helper.ConnectionHelper;
/**
 *
 * @author Rapiii
 */
public class BookManager {
    
    public static List<Book> showAllBook(){
    List<Book> bookList = new ArrayList<>();
    
        try {
            Connection con = ConnectionHelper.getConnection();//menginisiasikan conn dan memngggil method getConnection dari class ConenectionHelper
            Statement stmn = con.createStatement();//pemanggilan statement untuk melakukan pemanggilan statement sql agar bisa melakukann query
            ResultSet rs = stmn.executeQuery("select * from buku");//Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()) { //Mengecek dan melakukan perulangan bila ada data
               //mengeset properti didalam class buku
               //yang datanya diambil dari database
               Book book = new Book();
               book.setId_buku(Integer.parseInt(rs.getString("id_buku")));
               book.setJudul_buku(rs.getString("judul_buku"));
               book.setPenerbit(rs.getString("penerbit"));
               book.setPengarang(rs.getString("pengarang"));
               book.setTahun_terbit(Integer.valueOf(rs.getString("tahun_terbit")));
               
               bookList.add(book);//Menambahkan properti kedalam bukulist
            }
        } catch (SQLException e) {//Menangkap error apablila terjadi error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e); //melakukan logging apabila ada error didalam class CheckConnection 
        }
    return bookList;//Mengembalikan nilai bukulist
    }
}
