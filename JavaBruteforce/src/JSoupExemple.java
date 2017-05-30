
package javax.net.ssl;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JSoupExemple 
{

	public static void main(String[] args) throws Exception
	{  /*
		// TODO Auto-generated method stub
		Document doc = Jsoup.connect("http://www.rollshop.co.il/test.php").get();
		String data = doc.html();
		//System.out.println(title);
		Element link = doc.select("body").first();
		System.out.println(link);
		*/
		
		/*
		String html="<div>somer <strong>bold</strong> text</div>";	
		Document doc = Jsoup.parse(html);
		Element p= doc.select("strong").first();
		String text = doc.body().text(); //some bold text
		
	    //String text1 = text.replaceAll("<div>", "");
		//text1 = text1.replaceAll("</div>", "");
		
		System.out.println(text);
		*/
		System.out.println( getTextBetweenTags("<div>somer <strong>bold</strong> text</div>") );
		
	}
	
	
	
	
	public void getTextFromWeb() throws Exception
	{
		Document doc1 = Jsoup.connect("http://dslab.us").get();
		String html = doc1.html();
		
		Document doc = Jsoup.parse(html);
		Element p= doc.select("p").first();
		String text = doc.body().text(); //some bold text
		System.out.println(text);
	}
	
	/**
	 * 
	 * @param param = "<div>somer <strong>bold</strong> text</div>"
	 * @return text
	 * @throws Exception
	 */
	
	public static String getTextBetweenTags(String param) throws Exception
	{
		
		String html = param;	
		Document doc = Jsoup.parse(html);
		Element p= doc.select("p").first();
		String text = doc.body().text(); //some bold text
		//System.out.println(text);
		return text;
	}
	
	

}
