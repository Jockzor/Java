package Klimat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class KlimatAnläggningDBDao {
    static String username = "Joacim";
   static String password = "127693";
    
   public void tryClassForName()
   {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KlimatAnläggningDBDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void InsertTemp(Double temp) throws SQLException
   {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KlimatAnläggningDBDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
       PreparedStatement stmt = con.prepareStatement("INSERT INTO temperature(tempdata) VALUES(?)");
       stmt.setDouble(1,temp);
       
        stmt.executeUpdate();
       }
       catch (SQLException e){
            e.printStackTrace();
        }
   }
   public void InsertElKostnad(double elKostnad)
   {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KlimatAnläggningDBDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
       PreparedStatement stmt = con.prepareStatement("INSERT INTO elkostnad(KrPerKwh) VALUES(?)");
       stmt.setDouble(1,elKostnad);
       
        stmt.executeUpdate();
       }
       catch (SQLException e){
            e.printStackTrace();
        }
   }
   public AllInfo getAllInfo()
   {
       tryClassForName();
       AllInfo a = new AllInfo();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
         //  Statement stmt2 = con.createStatement();
         //  Statement stmt3 = con.createStatement();
        ResultSet rs = stmt.executeQuery(" select * from klimatanläggning order by Created desc limit 1");
         //  ResultSet rs = stmt.executeQuery("SELECT tempdata FROM temperature WHERE id=(SELECT max(id) FROM temperature)");
      //  ResultSet rs2 = stmt.executeQuery("SELECT KrPerKwh FROM elkostnad WHERE id=(SELECT max(id) FROM elkostnad)");
          //       ResultSet rs3 = stmt.executeQuery("SELECT KwhPerÅr FROM elforbrukning WHERE id=(SELECT max(id) FROM temperature)");
           while(rs.next())
            {
                
                float f = rs.getFloat("tempdata"); 
                float f2 = rs.getFloat("KrPerKwh");
                float f3 = rs.getFloat("KwhPerÅr");
                a.setTemp(f);
                a.setElKostnad(f2);
                a.setKwhPerÅR(f3);
               // String s = rs.getString("created");          
                
            }
        
       }
       
       catch (SQLException e){
            e.printStackTrace();
        }
       return a;
   }
   public ElForBrukning GetCurrentElForBrukning()
   {
      tryClassForName();
       ElForBrukning ef = new ElForBrukning();
       
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           
           ResultSet rs = stmt.executeQuery("SELECT KwhPerÅr, created FROM elforbrukning WHERE id=(SELECT max(id) FROM elforbrukning)");

           while(rs.next())
            {
                int i = rs.getInt("KwhPerÅr"); 
                String s = rs.getString("created");
                
                
                
                ef.setKwhPerÅr(i);
                ef.setCreated(s);
            }
       }
       catch (SQLException e){
            e.printStackTrace();
        }
       System.out.println("Elförbrukning:" + ef.getKwhPerÅr());
       return ef;
   }
   public List<ElForBrukning> getDailyElForBrukning()
   {
       List <ElForBrukning> el = new ArrayList();
       tryClassForName();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
       ResultSet rs = stmt.executeQuery("SELECT KwhPerÅr FROM elforbrukning WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               ElForBrukning elf = new ElForBrukning();
               elf.setKwhPerÅr(rs.getFloat(1));
               el.add(elf);
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }    
       return el;
   }
   public List<ElKostnad> getDailyElkostnad()
   {
       List <ElKostnad> ek = new ArrayList();
       tryClassForName();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
       ResultSet rs = stmt.executeQuery("SELECT KrPerKwh FROM elkostnad WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               ElKostnad elk = new ElKostnad();
               elk.setElKostnad(rs.getFloat(1));
               ek.add(elk);
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }    
       return ek;
   }
   public Temperatur GetCurrentTemp() 
   {
       Temperatur t = new Temperatur();
     tryClassForName();
       
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT tempdata FROM temperature WHERE id=(SELECT max(id) FROM temperature)");
            
            while(rs.next())
            {
                float f = rs.getFloat("tempdata"); 
               // String s = rs.getString("created");
                
                
                
                t.setTemp(f); 
              //  t.setCreated(s);
            }
            
       }
       catch (SQLException e){
            e.printStackTrace();
        }
       System.out.println("Temp: " + t.getTemp() + "Created: " + t.getCreated());
       return t;
   }
   
   public ElKostnad GetCurrentElKostnad()
   {
     tryClassForName();
       
       ElKostnad ek = new ElKostnad();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT KrPerKwh FROM elkostnad WHERE id=(SELECT max(id) FROM elkostnad)");
            

            while(rs.next())
            {
                ek.setElKostnad(rs.getFloat("KrPerKwh"));
                //ek.setCreated(rs.getString("created"));
            }
            
       }
       catch (SQLException e){
            e.printStackTrace();
        }
       System.out.println("Elkostnad: " + ek.getElKostnad() + " kr.");
       return ek;
   }
   
   public ElForBrukning getAverageElForBrukning()
   {
       ElForBrukning el = new ElForBrukning();
       tryClassForName();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT AVG(KwhPerÅr) FROM elforbrukning WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               el.setKwhPerÅr(rs.getFloat(1));
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }
       
           return el;
   }
   public Temperatur getAverageTemp() throws SQLException
   {
       Temperatur t = new Temperatur();
       tryClassForName();
     
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT AVG(tempdata) FROM temperature WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               t.setTemp(rs.getFloat(1));
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }
       
           return t;
   }
   public ElForBrukning getMaxElForBrukning()
   {
       ElForBrukning el = new ElForBrukning();
       tryClassForName();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT MAX(KwhPerÅr) FROM elforbrukning WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               el.setKwhPerÅr(rs.getFloat(1));
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }
       
           return el;
   }
   public ElForBrukning getMinElForBrukning()
   {
       ElForBrukning el = new ElForBrukning();
       tryClassForName();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT MIN(KwhPerÅr) FROM elforbrukning WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               el.setKwhPerÅr(rs.getFloat(1));
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }
       
           return el;
   }
   public Temperatur getMinimumTemp()
   {
        Temperatur t = new Temperatur();
       tryClassForName();
       
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT MIN(tempdata) FROM temperature WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               t.setTemp(rs.getFloat(1));
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }
       
           return t;
   }
   public Temperatur getMaximumTemp()
   {
        Temperatur t = new Temperatur();
       tryClassForName();
       
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT MAX(tempdata) FROM temperature WHERE created >= NOW() - INTERVAL 1 DAY");
           
           while(rs.next())
           {
               t.setTemp(rs.getFloat(1));
           }
           
        }
       catch (SQLException e){
            e.printStackTrace();
                   }
       
           return t;
   }
   public List<Temperatur> GetAllTemp()
   {
       List<Temperatur> AllTemp = new ArrayList();
       tryClassForName();
       try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/klimatanläggning?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
username, password);)
       {
           Statement stmt =  con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT tempdata FROM temperature WHERE created >= NOW() - INTERVAL 1 DAY");
           while(rs.next())
            {
                Temperatur t = new Temperatur();
                t.setTemp(rs.getInt("tempdata"));
               // t.setCreated(rs.getString("created"));
                AllTemp.add(t);
            }
       }
       catch (SQLException e){
            e.printStackTrace();
        }
       return AllTemp;
   }
   
    public static void main(String[]args)throws Exception
   {
       KlimatAnläggningDBDao kl = new KlimatAnläggningDBDao();
        kl.GetCurrentElForBrukning();
       //GetCurrentTemp();
      // GetCurrentElKostnad();
      // kl.InsertElKostnad(1.8);
     // kl.GetCurrentElKostnad();
   }
   
   
}
