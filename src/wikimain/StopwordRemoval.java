package wikimain;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.*;

public class StopwordRemoval {
    File Filename;
    Scanner cin;
    Set<String> set = new HashSet<String>();
    
    public void openfile() throws FileNotFoundException {
            File f = new File("./share/stopword-list.txt");
            cin = new Scanner(f);
    }

    public void readFile() throws FileNotFoundException{
        openfile();
        String s;
        while ( cin.hasNextLine()) {
            s = cin.nextLine();
            //System.out.println(s);
            set.add(s);
        }
        closefile();
    }
    public void closefile() {
        cin.close();
    }
    public boolean search(String word){
        return set.contains(word);
    }
    public static void main(String args[]) throws FileNotFoundException { 
        StopwordRemoval stopwords= new StopwordRemoval();
        stopwords.readFile();
        System.out.print(stopwords.search("this"));
        System.out.print(stopwords.search("suck"));
        System.out.print(stopwords.search("it"));
    }
    
}
