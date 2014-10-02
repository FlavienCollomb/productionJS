import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Concat {
    ArrayList<File> files;

    public Concat(){
        this.files = new ArrayList<File>();
    }
    public void add(File f){
        if(this.getExtension(f)!=null)
            if(!this.files.contains(f))
                this.files.add(f);
    }
    public void addJSONArray(JSONArray files){
        for(int j=0;j<files.size();j++){
            File f = new File((String)files.get(j));
            this.add(f);
        }
    }
    public void add(ArrayList<File> files){
        for (File f : files)
            this.add(f);
    }
    public void output(String out) throws IOException {
        // Output file
        Path output = Paths.get(out);
        if(output.toFile().exists() && output.toFile().isFile())
            output.toFile().delete();
        // Charset for read and write
        Charset charset = StandardCharsets.UTF_8;

        // Join files (lines)
        for (File file : this.files) {
            ProductionJS.logger.info("Concat file " + file.getAbsolutePath());
            List<String> lines = Files.readAllLines(file.toPath(), charset);
            Files.write(output, lines, charset, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        }
    }
    public ArrayList<File> getFiles() {
        return files;
    }
    /*
        * *
         */
    private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
