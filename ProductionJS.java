import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.UUID;

public class ProductionJS {
    public static Logger logger=Logger.getLogger(ProductionJS.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        boolean precondition = true;
        BackupLog backupLog = new BackupLog("log/backupLog.txt");
        Property properties = null;
        Settings settings = null;

        // Load main cfg file
        try{  properties = new Property("cfg/cfg"); }
        catch(IOException e){ backupLog.log("ERROR : Config file not found"); precondition = false; }

        // Load cfg for Log4J
        if(precondition){
            try{
                DOMConfigurator.configureAndWatch(properties.getProperty("loggerConfiguration"));
                logger.info("ProductionJS is launched\n_______________________");
            }
            catch(Exception e){ backupLog.log(e.getMessage()); precondition = false; }
        }


        // Load settings for current execution
        if(precondition){
            try{
                settings = new Settings(new File(properties.getProperty("importConfiguration")));
            }
            catch(IOException e){ logger.error("Properties file not found"); precondition = false;}
            catch (org.json.simple.parser.ParseException e) { logger.error("Properties file reading error : JSON may be invalid, check it!"); precondition = false; }
        }

        // All is OK : GO!
        if(precondition){
            logger.info("Configuration OK");
            logger.info("\"_______________________\nConcat BEGIN\n_______________________\n");

            Concat concat = new Concat();
            try{
                if(settings.getPrior() != null) {
                    logger.info("Add Prior files\n_______________________\n");
                    concat.addJSONArray(settings.getPrior());
                }

                for(int i=0;i<settings.getIn().size();i++){
                    ProductionJS.logger.info("Importation number " + (i+1));
                    ProductionJS.logger.info("Directory imported " + settings.getIn().get(i));

                    Directory dir = new Directory(new File(settings.getIn().get(i).toString()));
                    dir.scan();
                    concat.add(dir.getFiles());
                }
                concat.output(settings.getOut());

                logger.info("\"_______________________\nConcat END\n_______________________\n");
                if(settings.getMinify()!= null && settings.getMinify() == true){
                    logger.info("\"_______________________\nMinify of concatened file BEGIN\n_______________________\n");
                    new Minify(new File(settings.getOut()),settings.getOut());
                    logger.info("\"_______________________\nMinify of concatened file END\n_______________________\n");
                }

                if(settings.getLicense() != null){
                    logger.info("Add License\n_______________________\n");
                    concat = new Concat();
                    UUID uniqueID = UUID.randomUUID();

                    PrintWriter tmp = new PrintWriter(uniqueID.toString()+".txt");
                    tmp.println(settings.getLicense());
                    tmp.close();
                    File license = new File(uniqueID.toString()+".txt");
                    concat.add(license);

                    File out = new File(settings.getOut());
                    File tmpOut = new File(uniqueID + out.getName());
                    out.renameTo(tmpOut);
                    concat.add(tmpOut);

                    concat.output(settings.getOut());
                    license.delete();
                    tmpOut.delete();
                }
            }
            catch (IOException e){
                StackTrace stackTrace = new StackTrace(e.getStackTrace());
                logger.error("An error occurred " + e.getMessage() + " " + stackTrace.toString());
            }
        }
    }
}
