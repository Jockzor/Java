package Klimat;

import Klimat.KlimatAnläggningDBDao;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 

@Path("/KlimatAnlaggningService")


public class KlimatAnläggningService {
    static KlimatAnläggningDBDao klimatDB = new KlimatAnläggningDBDao();
   // private static List<Temperatur> getAllTemp = klimatDB.GetAllTemp();
    @GET 
   @Path("/dailyElForBrukning") 
   @Produces(MediaType.APPLICATION_XML)
     public List<ElForBrukning> getDailyElBrukning()
     {
         List<ElForBrukning> elf = klimatDB.getDailyElForBrukning();
         return elf;       
     }
    @GET 
   @Path("/dailyElkostnad") 
   @Produces(MediaType.APPLICATION_XML)
     public List<ElKostnad> getDailyElKostnad()
     {
         List<ElKostnad> ek = klimatDB.getDailyElkostnad();
         return ek;       
     }
     @GET 
   @Path("/averageElForBrukning") 
   @Produces(MediaType.APPLICATION_XML)
     public ElForBrukning getDailyEl()
     {
         ElForBrukning el = klimatDB.getAverageElForBrukning();
         return el;
     }
    @GET 
   @Path("/averageTemp") 
   @Produces(MediaType.APPLICATION_XML) 
   public Temperatur getAverageTemp() throws SQLException{ 
    Temperatur t = klimatDB.getAverageTemp();    
    return t;
   }
   @GET 
   @Path("/minForBrukning") 
   @Produces(MediaType.APPLICATION_XML)
   
   public ElForBrukning getMinEl()
   {
       ElForBrukning el = klimatDB.getMinElForBrukning();
       return el;
   }
   @GET 
   @Path("/maxForBrukning") 
   @Produces(MediaType.APPLICATION_XML)
   
   public ElForBrukning getMaxEl()
   {
       ElForBrukning el = klimatDB.getMaxElForBrukning();
       return el;
   }
   @GET 
   @Path("/minTemp") 
   @Produces(MediaType.APPLICATION_XML) 
   public Temperatur getMinTemp() throws SQLException{ 
    Temperatur t = klimatDB.getMinimumTemp();
    return t;
   }
    @GET 
   @Path("/maxTemp") 
   @Produces(MediaType.APPLICATION_XML) 
   public Temperatur getMaxTemp() throws SQLException{ 
    Temperatur t = klimatDB.getMaximumTemp();    
    return t;
   }
   @GET 
   @Path("/currentTemp") 
   @Produces(MediaType.APPLICATION_XML) 
   public Temperatur getCurrentTemp(){ 
       Temperatur t = klimatDB.GetCurrentTemp(); ;
       System.out.println("in curentTemp "+t.getTemp());
       //return "hej";
       return t; 
   }  
  
   @GET 
   @Path("/currentElkostnad") 
   @Produces(MediaType.APPLICATION_XML)
   public ElKostnad getCurrentElkostnad()
   {
       ElKostnad k = klimatDB.GetCurrentElKostnad();
       return k;
   }
   @GET 
   @Path("/currentElForBrukning") 
   @Produces(MediaType.APPLICATION_XML)
   public ElForBrukning getCurrentElForBrukning()
   {
       ElForBrukning ef = klimatDB.GetCurrentElForBrukning();
       return ef;
   }
   
   @GET 
   @Path("/allTemp") 
   @Produces(MediaType.APPLICATION_XML)
   public List<Temperatur> getAllTemp()
           {
                         
               return klimatDB.GetAllTemp();
           }
   @GET 
   @Path("/allInfo") 
   @Produces(MediaType.APPLICATION_XML)
        public AllInfo getAllInfo()
        {
           
            AllInfo a = new AllInfo();
            a.setTemp(25);
            a.setElKostnad(3);
            a.setKwhPerÅR(20000);
            System.out.println("i allinfo");
           
            return a;
        }
   @POST 
   @Path("/insertTemp") 
   @Produces(MediaType.APPLICATION_XML)
   public Temperatur insertTemp(Temperatur t) throws SQLException
   {
       klimatDB.InsertTemp(t.getTemp());
       return t;
   }
   @POST 
   @Path("/insertElkostnad") 
   @Produces(MediaType.APPLICATION_XML)
   public ElKostnad insertElkostnad(ElKostnad k)
   {
       klimatDB.InsertElKostnad(k.getElKostnad());
       return k;
   }
}
