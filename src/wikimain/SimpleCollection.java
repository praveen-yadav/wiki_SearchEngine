package wikimain;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
public class SimpleCollection {
    ArrayList<String> corpus = new ArrayList<String>();
    Iterator it ;
    public SimpleCollection(ArrayList<String> corpus) {
        this.corpus = corpus ;     
    }
    public void addtoCollection(){
        int counter = 1;
        it = corpus.iterator();
        System.out.println("Following Documents were added to collection");
        while (it.hasNext()) {
            String obj = (String) it.next();
            System.out.println(counter + ":" + obj);
            counter++;
        }
        
    }
    public void processDocuments() throws FileNotFoundException, UnsupportedEncodingException, IOException{
        System.out.println("Processing each document sequentially");
        it = corpus.iterator();

        File dir = new File("tmp/WikiArticlesCollection");
        if(dir.exists()){
            System.out.print("Dir exists");
        }
        else{
            dir.mkdirs();
            while(it.hasNext()){
                walk((String)it.next());
            }
        }
    }
    public void walk( String path ) {

        File root = new File( path );
        File[] list;
        if(root.isFile()){
            list = new File[1];
            list[0] = root;
        }
        else list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                File filename = f.getAbsoluteFile();
                System.out.println( "File:" + filename );
                String[] tokens = filename.toString().split("\\.(?=[^\\.]+$)");
                if(tokens[1].equals("xml")){
                    XMLParser xmlParser = new XMLParser(filename);
                }
                else{
                    System.out.println( "Ignoring::File-type unknown for File:" + tokens[0] );
                }
            }
        }
    }
}
