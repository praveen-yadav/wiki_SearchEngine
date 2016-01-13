package wikimain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class tokenizer {

    Map<String, Integer> list = new HashMap<String, Integer>();     
    File Filename;
    Scanner cin = null;
    StopwordRemoval stopword = null;
    PrintWriter writer;
    MyPorterStemmer stemmer; 
    
    public tokenizer(File Filename) throws FileNotFoundException, UnsupportedEncodingException {
        this.Filename = Filename;
        writer = new PrintWriter((Filename.getAbsoluteFile() + ".tokens"), "UTF-8");
    }

    public void openfile() throws FileNotFoundException {
        try {
            cin = new Scanner(Filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        stemmer = new MyPorterStemmer();
        stemmer.openfile();
        stemmer.buildDict();

    }
    
    public void removetags() throws FileNotFoundException{
        while (cin.hasNextLine()) {
            String line = cin.nextLine();
            if(line.startsWith("{{")){
                if(line.endsWith("}}")){
                    line = line.substring(2,line.length()-2);
                }
                else{
                    while(cin.hasNextLine() && !cin.nextLine().endsWith("}}")){
                        line+=cin.nextLine();
                    }
                    //line+=cin.nextLine();
                    line=line.substring(2, line.length()-2);
                }
                //System.out.println(line);
            }
            else if(line.startsWith("==")){
                if(line.endsWith("==")){
                    line = line.substring(2,line.length()-2);
                }
                //System.out.println(line);
            }
            else{
                readFile(line);
            }
        }
    }
    
    public void readFile(String line) throws FileNotFoundException {

            //Scanner scanword = new Scanner(line).useDelimiter("\\s*fish\\s*");
            Scanner scanword = new Scanner(line).useDelimiter("\\s*\\s|\\.");
            while (scanword.hasNext()) {
                String s = scanword.next();
                StopwordRemoval stopword = new StopwordRemoval();
                stopword.readFile();
                if (stopword.search(s)) {
                    //ignore word
                } else {//stem the word
                    s=stemmer.getStemmedOP(s);
                    int n;
                    try {
                        list.put(s, list.get(s) + 1);
                    } catch (NullPointerException e) {
                        list.put(s, 1);
                    }
                    
                }
               
            }
        
    }

    public void closefile() {
        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            writer.println(entry.getKey() + "/" + entry.getValue()+"-" + Filename.getName());
        }
        cin.close();
        writer.close();
    }

    public String tosmallcase(String word) {
        return word.toLowerCase();
    }

    public static void main() {
    }
}
