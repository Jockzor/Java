package Klimat;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "elkostnad")
public class ElKostnad implements Serializable{
    private double elKostnad;
    private String created;
    
    ElKostnad(){}
    
    ElKostnad(double elKostnad, String created)
    {
        this.elKostnad = elKostnad;
        this.created = created;
    }
    
    public double getElKostnad()
    {
        return elKostnad;
    }
    
    public String getCreated()
    {
        return created;
    }
    
    @XmlElement
    public void setElKostnad(double elKostnad)
    {
        this.elKostnad = elKostnad;
    }
   @XmlElement
    public void setCreated(String created) {
        this.created = created;
    }
}
