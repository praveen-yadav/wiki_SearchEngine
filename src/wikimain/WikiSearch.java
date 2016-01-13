package wikimain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class WikiSearch {

    ArrayList<String> corpus = new ArrayList<String>();
    Scanner cin;

    public void showMenu() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        System.out.println("1. Indexing 2. Retreival 3. Exit");
        String choiceS;
        int choiceI;
        cin = new Scanner(System.in);
        choiceS = cin.nextLine();
        choiceI = Integer.parseInt(choiceS);
        if (choiceI == 1) {
            {
                do {
                    System.out.println("Enter next file/folder address or press enter otherwise");
                    String document;
                    document = cin.nextLine();
                    if (document.isEmpty()) {
                        corpus.add("/home/praveeny/wikidump/");
                        break;
                    }
                    corpus.add(document);
                } while (choiceI == 1);
                if (corpus.isEmpty()) {
                    System.out.println("No corpus selected!");
                    return;
                }
                SimpleCollection collection = new SimpleCollection(corpus);
                collection.addtoCollection();
                collection.processDocuments();
                
                File f = new File("./tmp/WikiArticlesCollection/Anarchism");
                tokenizer tok = new tokenizer(f);
                tok.openfile();
                tok.removetags();
                tok.closefile();
            }
        } else if (choiceI == 2) {
        } else {
            System.exit(1);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        WikiSearch myObj = new WikiSearch();
        myObj.showMenu();
    }
}
