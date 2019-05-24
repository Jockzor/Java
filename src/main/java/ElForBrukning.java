import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "elforbrukning")
public class ElForBrukning implements Serializable {
    private static final long serialVersionUID = 1L;
    private double KwhPerÅr;
    private String created;
    
    ElForBrukning(){}
    
    ElForBrukning(double KwhPerÅr, String created)
    {
        this.KwhPerÅr = KwhPerÅr;
        this.created = created;
    }

    public double getKwhPerÅr() {
        return KwhPerÅr;
    }
   @XmlElement
    public void setKwhPerÅr(double KwhPerÅr) {
        this.KwhPerÅr = KwhPerÅr;
    }

    public String getCreated() {
        return created;
    }

   @XmlElement
    public void setCreated(String created) {
        this.created = created;
    }
}
