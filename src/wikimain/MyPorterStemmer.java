package wikimain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class MyPorterStemmer {

    Dictionary dict = new Hashtable();
    File input, output , filename;
    Scanner scanI, scanO ;

    public MyPorterStemmer(File filename) {
        this.filename = filename;
    }

    MyPorterStemmer() {
        
    }


    public void openfile() throws FileNotFoundException {
        input = new File("./share/tests/porterstemmer/voc.txt");
        output = new File("./share/tests/porterstemmer/output.txt");
        scanI = new Scanner(input);
        scanO = new Scanner(output);
        
    }

    public void closefile() {
        scanI.close();
        scanO.close();
    }

    public void buildDict() {
        String inputword,outputword;
        while (scanI.hasNextLine() && scanO.hasNextLine()) {
            inputword = scanI.nextLine();
            outputword = scanO.nextLine();
            dict.put(inputword, outputword);
        }
    }
    public String getStemmedOP(String input){
        String op = (String) dict.get(input);
        
        if(op==null){
            Stemmer stemmer = new Stemmer();
            stemmer.add(input.toCharArray(),input.length());
            stemmer.stem();
            return stemmer.toString();
        }
        else 
            return op;
    }
    
    public static void main(String args[]) throws FileNotFoundException{
        MyPorterStemmer obj = new MyPorterStemmer(new File("./src/corpus/hyderabad.txt"));
        obj.openfile();
        obj.buildDict();
        System.out.println(obj.getStemmedOP("masculine"));
        System.out.println(obj.getStemmedOP("sucked"));
        obj.closefile();
    }
}
