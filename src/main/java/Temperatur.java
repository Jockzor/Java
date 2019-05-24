import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "temp")
public class Temperatur implements Serializable {
    private static final long serialVersionUID = 1L;
    private double temp;
    private String created;
    
    public Temperatur(){}
    
    Temperatur(double temp, String created)
    {
        this.temp = temp;
        this.created = created;
    }
    
    public double getTemp()
    {
        return temp;
    }
    
    public String getCreated()
    {
        return created;
    }
    
    @XmlElement
    public void setTemp(double temp)
    {
        this.temp = temp;
    }
   @XmlElement
    public void setCreated(String created) {
        this.created = created;
    }
    
}
