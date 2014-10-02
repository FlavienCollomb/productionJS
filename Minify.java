
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Minify {
    public Minify(File in, String out) throws IOException {
        ProductionJS.logger.info("Minify of JS file " + in.getAbsolutePath());
        Writer tmp = new StringWriter();

        String path=in.getPath();
        Reader reader=new InputStreamReader(new FileInputStream(path),"UTF-8");

        JavaScriptCompressor compressor = new JavaScriptCompressor(reader,new MyErrorReporter());
        reader.close();
        compressor.compress(tmp,2000,true,false,true,true);

        File outputExist = new File(out);
        if(outputExist.exists() && outputExist.isFile())
            outputExist.delete();

        PrintStream output = null;
        output = new PrintStream(new FileOutputStream(out));
        output.print(tmp);
        output.close();
    }
}

