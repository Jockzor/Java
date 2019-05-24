import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "all")
public class AllInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private double temp;
    private double elKostnad;
    private double KwhPerÅr;
   // private String created;
    
    public AllInfo(){}
    
    AllInfo(double temp,double elKostnad, double KwhPerÅr)
    {
        this.temp = temp;
        this.elKostnad = elKostnad;
        this.KwhPerÅr = KwhPerÅr;
       // this.created = created;
    }
    
    public double getTemp()
    {
        return temp;
    }
    
    public double getElKostnad()
    {
        return elKostnad;
    }
    public double getKwhPerÅr()
    {
        return KwhPerÅr;
    }
    
   
    
    @XmlElement
    public void setTemp(double temp)
    {
        this.temp = temp;
    }
   
    @XmlElement
    public void setElKostnad(double elKostnad)
    {
        this.elKostnad = elKostnad;
    }
   @XmlElement
    public void setKwhPerÅR(double KwhPerÅr) {
        this.KwhPerÅr = KwhPerÅr;
    }
}

