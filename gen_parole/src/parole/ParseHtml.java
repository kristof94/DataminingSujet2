package parole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParseHtml {

	static Document doc;
	static String texte;
	static String PATH = "/home/kristof/";
	static String autor,title;
	static String format;
	
	public static void main(String[] args) {
		
		

	}
	private void Parse(String path){
		File song = new File(path);
		
		try {
			
			doc = Jsoup.parse(song, "UTF-8") ;
			//Elements element= doc.getElementsByClass("ebzNative").remove() ;					
			Elements element= doc.getElementsByClass("ebzNative").remove() ;
			element= doc.getElementsByClass("song-text") ;
			
			
			texte = element.html();
			texte = texte.replace("<br><br>"," ");
			texte = texte.replace("<br>"," ");			
			texte = texte.replace("  ","");
			//texte = texte.replace("  ","");
			
			texte = texte.replace(" "+System.getProperty("line.separator")+System.getProperty("line.separator"),System.getProperty("line.separator"));
			//texte = texte.replace(System.getProperty("line.separator")+System.getProperty("line.separator")+" ",System.getProperty("line.separator"));
			
			texte = texte.replace(System.getProperty("line.separator")+System.getProperty("line.separator"),System.getProperty("line.separator"));
			
			element = doc.select("span[property=v:artist]");
			autor = element.text();
			element = doc.select("span[property=v:name]");
			title = element.text();
			
			format = autor+"¤"+title+"¤";

			texte = texte.replace(System.getProperty("line.separator"),System.getProperty("line.separator")+format);
			texte = format + texte;


			System.out.println(texte);
		    FileWriter file = new FileWriter(PATH+"parole.txt", true);		    
		    BufferedWriter bw = new BufferedWriter ( file ) ;  
		    PrintWriter pw = new PrintWriter ( bw ) ;
		    pw. print (texte) ; 
		    pw. close ( ) ; 
		    
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
