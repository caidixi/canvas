package wnderful.myPoject.util;

import org.springframework.stereotype.Service;
import wnderful.myPoject.exception.InitializeException;
import wnderful.myPoject.exception.LoadException;
import wnderful.myPoject.exception.SaveException;

import java.io.*;

@Service
public class FileHelper {
    private String path;

    public FileHelper() throws InitializeException {
        path = "src/main/resources/static/mark/";
        initialize();
    }

    private void initialize()throws InitializeException{
        File file = new File("src/main/resources/static/mark");
        if(!file.exists()) {
            if(!file.mkdir()){
                throw new InitializeException();
            }
        }
    }

    public void save(String name, String content) throws IOException{
        try{

            PrintWriter out = new PrintWriter(new FileWriter(path +name+".txt"));
            out.write(content);
            out.close();
        }catch (IOException ex){
            ex.printStackTrace();
            throw new SaveException();
        }
    }

    public String load(String name) throws IOException{
        StringBuilder content = new StringBuilder();
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path+name+".txt")));
            String str;
            while ((str=in.readLine())!=null){
                content.append(str);
            }
            in.close();
        }catch (IOException ex){
            ex.printStackTrace();
            throw new LoadException();
        }
            return content.toString();

    }
}
