package wikimain;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class UserHandler extends DefaultHandler {
   boolean mediawiki = false;
   boolean page = false;
   boolean title = false;
   boolean redirect = false;
   boolean text = false;
   boolean revision = false;
   PrintWriter writer ;
   PrintWriter IndexedFilenameswriter;
   
   
   @Override
   public void startElement(String uri, 
      String localName, String qName, Attributes attributes)
         throws SAXException {
      if (qName.equalsIgnoreCase("title")) {
         title = true;
      } else if (qName.equalsIgnoreCase("redirect")) {
         redirect = true;
      }
      else if (qName.equalsIgnoreCase("text")) {
         text = true;  
      }
   }

   @Override
   public void endElement(String uri, 
      String localName, String qName) throws SAXException {
       if(qName.equalsIgnoreCase("text")) {
          writer.close();
          text = false;
      }
   }

   @Override
   public void characters(char ch[],int start, int length) throws SAXException {
      if (text){
          String str = new String(ch, start, length);
          writer.print(str);
      }
      else if (title){
            try {
                writer =new PrintWriter("tmp/WikiArticlesCollection/"+new String(ch, start, length), "UTF-8");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                title = false;
            }
          title = false;
      }
   }
}
public class XMLParser {
    File inputFile;
    public XMLParser(File filename) {
        this.inputFile=filename;
        try {	
             SAXParserFactory factory = SAXParserFactory.newInstance();
             SAXParser saxParser = factory.newSAXParser();
             UserHandler userhandler = new UserHandler();
             saxParser.parse(inputFile, userhandler);     
             System.out.println("exit from xml");
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    
}
