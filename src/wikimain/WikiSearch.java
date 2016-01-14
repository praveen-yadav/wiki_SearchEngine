package wikimain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class WikiSearch {
    String corpus;
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
                    System.out.println("Enter xml dump address");
                    String document;
                    document = cin.nextLine();
                    if (document.isEmpty()) {
                        corpus=new String("/home/praveeny/wikidump/wiki-dump.xml");                        
                    }
                    else corpus=(document);
                
                    SimpleCollection collection = new SimpleCollection(corpus);
                    collection.addtoCollection();
                    collection.processDocuments();
                
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
