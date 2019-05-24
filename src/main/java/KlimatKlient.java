import java.io.IOException;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
public class KlimatKlient {
    private static ClientConfig config  = new DefaultClientConfig();
    private static Client client = Client.create(config);
    private static WebResource service = client.resource(
            UriBuilder.fromUri("http://localhost:8080/KlimatAnlaggning").build());
    
    public String getCurrentTemp()
    {
        String xmlGetTemp = service.path("rest/KlimatAnlaggningService/currentTemp")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlGetTemp);
        Document doc = Jsoup.parse(xmlGetTemp, "", Parser.xmlParser());
        String temp = doc.tagName("temp").text();
        return temp;
        /*
        Temperatur[] tempArray = service.path("rest/KlimatAnlaggningService/currentTemp")
                .accept(MediaType.APPLICATION_XML).get(Temperatur[].class);
        
        for(Temperatur t : tempArray)
        {
            System.out.println("Temp: " + t.getTemp());
        }
      List<Temperatur> tempList = Arrays.asList(tempArray);
        */
    }
    public String getCurrentElForBrukning()
    {
        String xmlGetElForBrukning = service.path("rest/KlimatAnlaggningService/currentElForBrukning")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlGetElForBrukning);
        Document doc = Jsoup.parse(xmlGetElForBrukning, "", Parser.xmlParser());
        String elForBrukning = doc.select("KwhPerÅr").text();
        return elForBrukning;
    }
    
    public String getCurrentElKostnad()
    {
        String xmlGetElKostnad = service.path("rest/KlimatAnlaggningService/currentElkostnad")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlGetElKostnad);
        Document doc = Jsoup.parse(xmlGetElKostnad, "", Parser.xmlParser());
        String elKostnad = doc.tagName("temp").text();
        return elKostnad;
    }
    public String getAverageTemp()
    {
        String xmlGetAverageTemp = service.path("rest/KlimatAnlaggningService/averageTemp")
                .accept(MediaType.APPLICATION_XML).get(String.class);
       // System.out.println(xmlGetAverageTemp);
        Document doc = Jsoup.parse(xmlGetAverageTemp, "", Parser.xmlParser());
        String avgtemp = doc.tagName("temp").text();
    return avgtemp;
    }
    public String getAverageElForBrukning()
    {
        String xmlGetAverageEl = service.path("rest/KlimatAnlaggningService/averageElForBrukning")
                .accept(MediaType.APPLICATION_XML).get(String.class);
       // System.out.println(xmlGetAverageTemp);
        Document doc = Jsoup.parse(xmlGetAverageEl, "", Parser.xmlParser());
        String avgEl = doc.tagName("temp").text();
    return avgEl;
    }
    public String getMinElForBrukning()
    {
        String xmlGetMinEl = service.path("rest/KlimatAnlaggningService/minForBrukning")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlGetMinEl);
        Document doc = Jsoup.parse(xmlGetMinEl, "", Parser.xmlParser());
        String minEl = doc.tagName("temp").text();
        return minEl;
    }
    public String getMaxElForBrukning()
    {
        String xmlGetMaxEl = service.path("rest/KlimatAnlaggningService/maxForBrukning")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlGetMaxEl);
        Document doc = Jsoup.parse(xmlGetMaxEl, "", Parser.xmlParser());
        String maxEl = doc.tagName("temp").text();
        return maxEl;
    }
    
    public String getMinTemp()
    {
        String xmlGetMinTemp = service.path("rest/KlimatAnlaggningService/minTemp")
                .accept(MediaType.APPLICATION_XML).get(String.class);
       // System.out.println(xmlGetMinTemp);
        Document doc = Jsoup.parse(xmlGetMinTemp, "", Parser.xmlParser());
        String mintemp = doc.tagName("temp").text();  
    return mintemp;
    }
    
    public String getMaxTemp()
    {
         String xmlGetMaxTemp = service.path("rest/KlimatAnlaggningService/maxTemp")
                .accept(MediaType.APPLICATION_XML).get(String.class);
       // System.out.println(xmlGetMaxTemp);
        Document doc = Jsoup.parse(xmlGetMaxTemp, "", Parser.xmlParser());
        String maxtemp = doc.tagName("temp").text();
    return maxtemp;
    }
    public String getAllTemp()
    {
       // List<Temperatur> allTemps =  new ArrayList();
      String xmlAllTemps =  service.path("rest/KlimatAnlaggningService/allTemp")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlAllTemps);
        Document doc = Jsoup.parse(xmlAllTemps, "", Parser.xmlParser());
        String allTemps = doc.tagName("temp").text();
        
       return allTemps;
    }
     public String getAllElkostnad()
    {
       // List<Temperatur> allTemps =  new ArrayList();
      String xmlAllElkostnad =  service.path("rest/KlimatAnlaggningService/dailyElkostnad")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlAllElkostnad);
        Document doc = Jsoup.parse(xmlAllElkostnad, "", Parser.xmlParser());
        String allEl = doc.tagName("elkostnad").text();
        
       return allEl;
    }
    public String getAllElForBrukning()
    {
        String xmlAllElkostnad =  service.path("rest/KlimatAnlaggningService/dailyElForBrukning")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlAllElkostnad);
        Document doc = Jsoup.parse(xmlAllElkostnad, "", Parser.xmlParser());
        String allEl = doc.select("kwhPerÅr").text();
        
       return allEl;
    }
    public void getElRapport()
    {
        System.out.println("Elkostnader senaste dygnet: " + getAllElkostnad());
        System.out.println("All elförbrukning senaste dygnet: " + getAllElForBrukning());
        System.out.println("Medelvärde för elförbrukning senaste dygnet " + getAverageElForBrukning());
        System.out.println("Maxvärde för elförbrukning senaste dygnet "+ getMaxElForBrukning());
        System.out.println("Minvärde för elförbrukning senaste dygnet "+ getMinElForBrukning());
    }
    public void getAllInfo()
    {
        System.out.println("Nuvarande temp: " + getCurrentTemp()+ " grader.");
        System.out.println("Nuvarande elkostnad: " + getCurrentElKostnad()+ " KrPerKwh.");
        System.out.println("Nuvarande elförbrukning: " + getCurrentElForBrukning()+ " ");
    }
    public void getDailyRapportOnTemp()
    {
        System.out.println("Alla temperaturer senaste dygnet: " + getAllTemp());
        System.out.println("Medelvärde temp senaste dygnet: " + getAverageTemp() + " grader.");
        System.out.println("MaxTemp för senaste dygnet: " + getMaxTemp()+ " grader.");
        System.out.println("MinTemp för senaste dygnet: " + getMinTemp()+ " grader.");
    }
    
    public void updateElkostnad()
    {
        Scanner sc = new Scanner(System.in);
      
        System.out.println("Ange Elkostnad: ");
        double kostnad = sc.nextInt();
        String time = "2019-05-24";
        ElKostnad ek = new ElKostnad(kostnad, time);
        ClientResponse response = service.path("rest/KlimatAnlaggningService/insertElkostnad")
                .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, ek);
        
        System.out.println("Response: " + response.getEntity(String.class));
        
        
    }
    public void updateTemp()
    {
        Scanner sc = new Scanner(System.in);
      
        System.out.println("Ange Temperatur: ");
        int temp = sc.nextInt();
        String time = "2019-05-24";
        Temperatur t = new Temperatur(temp, time);
        ClientResponse response = service.path("rest/KlimatAnlaggningService/insertTemp")
                .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, t);
        
        System.out.println("Response: " + response.getEntity(String.class));
    }
    public static void main(String[] args) throws IOException{ 
        KlimatKlient k = new KlimatKlient();
       // k.getAllTemp();
        
       int x = 0;
       while (x == 0)
       {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("MENY");
        System.out.println("1. Hämta Temp");
        System.out.println("2. Hämta Elkostnad");
        System.out.println("3. Hämta Elförbrukning");
        System.out.println("4. Hämta fullständig rapport");
        System.out.println("5. Uppdatera Temp");
        System.out.println("6. Uppdatera Elkostnad");
        System.out.println("7. Hämta temprapport");
        System.out.println("8. Hämta El-Rapport");
        System.out.println("9. Avsluta");
        
        System.out.println("Ange Nummer-> ");
         int svar = scan.nextInt();
         
         switch(svar){
           case 1:
               System.out.println("Nuvarande temp: " + k.getCurrentTemp()+ " grader.");
                break;
           case 2:
               System.out.println("Nuvarande elkostnad: " + k.getCurrentElKostnad()+ " KrPerKwh.");
                break;
           case 3:
               System.out.println("Nuvarnade elförbrukning: " + k.getCurrentElForBrukning()+ " ");
                break;
           case 4:
                k.getAllInfo();
                break;   
           case 5:
                k.updateTemp();
                break;  
           case 6:
                k.updateElkostnad();
                break;     
           case 7:
                k.getDailyRapportOnTemp();
                break;  
           case 8:
                k.getElRapport();
                break;       
           case 9:
                x = 1;
                break;       
                     
       }
      //  k.getCurrentTemp();
      //  k.getCurrentElForBrukning();
      //  k.getCurrentElKostnad();
      //  k.updateElkostnad();
      //  k.updateTemp();
       }
    }
}
