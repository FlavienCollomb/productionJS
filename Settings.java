import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Settings {
    private JSONParser parser;

    private JSONArray in;
    private JSONArray prior;
    private String out;
    private Boolean minify;
    private String license;

    public Settings(File path) throws IOException, ParseException {
        if(path.exists() && !path.isDirectory()) {
            ProductionJS.logger.info("Import config in " + path.getAbsolutePath());
            this.parser=new JSONParser();
            // Open JSON of settings
            JSONObject settings=(JSONObject)parser.parse(new FileReader(path));
            this.in = (JSONArray)settings.get("in");
            this.prior = (JSONArray)settings.get("prior");
            this.out = (String)settings.get("out");
            this.minify = (Boolean)settings.get("minify");
            this.license = (String)settings.get("license");
        }
    }
    public JSONArray getIn() {
        return this.in;
    }
    public JSONArray getPrior() {
        return this.prior;
    }
    public String getOut() {
        return this.out;
    }
    public Boolean getMinify() {
        return this.minify;
    }
    public String getLicense() {
        return this.license;
    }
}
