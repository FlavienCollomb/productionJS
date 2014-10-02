import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

public class Directory {
    private File path;
    /** * @var array */
    private ArrayList<Directory> directories;
    /** * @var array */
    private ArrayList<File> files;

    public Directory(File path){
        if(path.exists() && path.isDirectory()) {
            this.path = path;
            this.directories = new ArrayList<Directory>();
            this.files = new ArrayList<File>();
        }
        else
            throw new InvalidParameterException();
    }
    public void scan() throws IOException {
        String[] paths;
        paths = this.path.list();

        for(String path:paths){
            File tmp = new File(this.path, path);
            if(tmp.isDirectory()){
                Directory dir = new Directory(tmp);
                this.directories.add(dir);
            }
            else
                this.files.add(tmp);
        }
    }
    public File getPath() {
        return path;
    }
    public void setPath(File path) {
        this.path = path;
    }
    public ArrayList<Directory> getDirectories() {
        return directories;
    }
    public void setDirectories(ArrayList<Directory> directories) {
        this.directories = directories;
    }
    public ArrayList<File> getFiles() {
        return files;
    }
    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }
}
