import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private Properties properties;

    public Property(String cfgFile) throws IOException{
        this.properties = new Properties();
        this.properties.load(new FileInputStream(cfgFile + ".properties"));
    }

    public String getProperty(String property){
        return this.properties.getProperty(property);
    }
}
