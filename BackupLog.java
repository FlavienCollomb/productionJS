import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class BackupLog {
    private PrintWriter writer;

    public BackupLog(String path){
        this.writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void log(String message){
        this.writer.println(message);
        this.writer.close();
    }
}
