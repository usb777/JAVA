/***
 * More powerfull exemple - https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
 * 
 * https://www.mkyong.com/java/java-https-client-httpsurlconnection-example/
 */

package javax.net.ssl;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpClientExample 
{

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception 
	{

		HttpClientExample http = new HttpClientExample();
/*
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

	
	*/	
		System.out.println("\nTesting 1 - Send Http POST request with One Loop");
				
	/*	
		if (http.sendPost1Loop())
		{ 
			System.out.println("Find!");
			System.exit(0);
		}
		 else
		 {  
			 if (http.sendPost2Loop())
			 {
				 System.out.println("Find!");
					System.exit(0);
			 }
			 else
			 {
				 if (http.sendPost3Loop())
				 {
					 System.out.println("Find!");
						System.exit(0);
				 }
				 else
				 {
					 http.sendPost4Loop();
				 }  
			 }
			 
		 } // else http.sendPost1Loop()
		
		*/
		
		if (http.sendPost3Loop())
		{ 
			System.out.println("Find!");
			System.exit(0);
		}
		 else
		 {  
			 if (http.sendPost4Loop())
			 {
				 System.out.println("Find!");
					System.exit(0);
			 }
		 }
		
	}

	// HTTP GET request
	private void sendGet() throws Exception 
	{

		String url = "http://experiment.com/test.php?code=14563";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " +
                       response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) 
		{
			result.append(line);
		}

		System.out.println(result.toString());

	}

	private boolean sendPost1Loop () throws Exception 
	{   String code = "";
	     boolean flag = false;
	
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		
		
		//put LOOP here
		
		for (int i=0;i<=9;i++)
		{		
	  	    code = 	""+i;
		params.add(new BasicNameValuePair("code", code));         //i+""+j));
		//params.add(new BasicNameValuePair("param-2", "Hello!"));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) 
		{
		    InputStream instream = entity.getContent();
		    
		    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
		    
		    try 
		    {
		        // do something useful
		    	
		    	StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) 
				{
					result.append(line);
				}
				
				System.out.println("code="+ code  );   
				String postAnswer = getTextBetweenTags(result.toString(), "body");
				System.out.println("Result:!!!"+postAnswer+"!!!");
				if (postAnswer.equals("WRONG =("))
				 { 
					System.out.println("BAD result");
					//break;
					flag = false;
				 }
				else 
				{
					System.out.println("Good result");
					flag = true;
					break;
				}
				
		    } 
		    finally 
		      {
		         instream.close();
		      }
		} //if  (entity != null) 
		
		
		  
		} //for i
		return flag;
	} // send Post 1 Loop
	
	/**
	 * send code from 00 to 99
	 * @throws Exception
	 */
	private boolean sendPost2Loop () throws Exception 
	{	String code ="";
	 boolean flag = false;
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
		for (int i1=0;i1<10;i1++)
		{
			for (int i2=0;i2<10;i2++)
			{  code = ""+i1+i2;
			
				//System.out.println(code);
			params.add(new BasicNameValuePair("code", code));         //i+""+j));
			//params.add(new BasicNameValuePair("param-2", "Hello!"));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) 
			{
			    InputStream instream = entity.getContent();
			    
			    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
			    
			    try 
			    {
			        // do something useful
			    	
			    	StringBuffer result = new StringBuffer();
					String line = "";
					while ((line = rd.readLine()) != null) 
					{
						result.append(line);
					}
					
					System.out.println("code="+ code  );   
					String postAnswer = getTextBetweenTags(result.toString(), "body");
					System.out.println("Result:!!!"+postAnswer+"!!!");
					if (postAnswer.equals("WRONG =("))
					 { 
						System.out.println("BAD result");
						//break;
						flag = false;
					 }
					else 
					{
						System.out.println("Good result");
						flag = true;
						break;
					}
					
			    } 
			    finally 
			      {
			         instream.close();
			      }
			} //if  (entity != null) 
			}
		}
		return flag;
	}
	
	/**
	 * send code from 000 to 999
	 * @throws Exception
	 */
	private boolean sendPost3Loop() throws Exception 
	{	
		String code ="";
		boolean flag=false;
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		
		
		for (int i1=0;i1<10;i1++)
		{
			for (int i2=0;i2<10;i2++)
			{
				for (int i3=0;i3<10;i3++)
				{
					code = ""+i1+i2+i3;
				 // System.out.println(code);
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null) 
				} // i3
			} // i2
		}  // i1
		return flag;
	}	//sendPost3Loop
	
	
	
	/**
	 * send code from 0000 to 9999
	 * @throws Exception
	 */
	private boolean sendPost4Loop() throws Exception 
	{		
	String code ="";
	boolean flag=false;
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
	
	for (int i1=0;i1<10;i1++)
	{
		for (int i2=0;i2<10;i2++)
		{
			for (int i3=0;i3<10;i3++)
			{
				for (int i4=0;i4<10;i4++)
				{
				  code = ""+i1+i2+i3+i4;
				  // System.out.println(code);
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null) 
				} // i4
			} // i3
		} // i2
	}  // i1
		
	
	return flag;
	}//sendPost4Loop
	
	
	/**
	 * send code from 00000 to 99999
	 * @throws Exception
	 */
	private boolean sendPost5Loop() throws Exception 
	{		String code ="";
	  boolean flag=false;
	
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
	
	for (int i1=0;i1<10;i1++)
	{
		for (int i2=0;i2<10;i2++)
		{
			for (int i3=0;i3<10;i3++)
			{
				for (int i4=0;i4<10;i4++)
				{
					for (int i5=0;i5<10;i5++)
					{
				  code = ""+i1+i2+i3+i4+i5;
				 
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null) 
					
					} //i5
				} // i4
			} // i3
		} // i2
	}  // i1
		
	return flag;
}//sendPost5Loop
	
	
	
	/**
	 * send code from 000000 to 999999
	 * @throws Exception
	 */
	private boolean sendPost6Loop() throws Exception 
	{		String code ="";
	      boolean flag = false;
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
	
	for (int i1=0;i1<10;i1++)
	{
		for (int i2=0;i2<10;i2++)
		{
			for (int i3=0;i3<10;i3++)
			{
				for (int i4=0;i4<10;i4++)
				{
					for (int i5=0;i5<10;i5++)
					{
						for (int i6=0;i6<10;i6++)
						{
				  code = ""+i1+i2+i3+i4+i5+i6;
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null) 
						} //i6
					} //i5
				} // i4
			} // i3
		} // i2
	}  // i1
		
	return flag;
}//sendPost6Loop
		
	
	/**
	 * send code from 0000000 to 9999999
	 * @throws Exception
	 */
	private boolean sendPost7Loop() throws Exception 
	{		String code ="";
	boolean flag= false;
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
	
	for (int i1=0;i1<10;i1++)
	{
		for (int i2=0;i2<10;i2++)
		{
			for (int i3=0;i3<10;i3++)
			{
				for (int i4=0;i4<10;i4++)
				{
					for (int i5=0;i5<10;i5++)
					{
						for (int i6=0;i6<10;i6++)
						{
							for (int i7=0;i7<10;i7++)
							{
				  code = ""+i1+i2+i3+i4+i5+i6+i7;
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null) 
						   } //i7
						} //i6
					} //i5
				} // i4
			} // i3
		} // i2
	}  // i1
		
	return flag;
}//sendPost7Loop	
	
	
	
	/**
	 * send code from 00000000 to 99999999
	 * @throws Exception
	 */
	private boolean sendPost8Loop() throws Exception 
	{		String code ="";
	  boolean flag=false;
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
	
	for (int i1=0;i1<10;i1++)
	{
		for (int i2=0;i2<10;i2++)
		{
			for (int i3=0;i3<10;i3++)
			{
				for (int i4=0;i4<10;i4++)
				{
					for (int i5=0;i5<10;i5++)
					{
						for (int i6=0;i6<10;i6++)
						{
							for (int i7=0;i7<10;i7++)
							{
								for (int i8=0;i8<10;i8++)
								{
				  code = ""+i1+i2+i3+i4+i5+i6+i7+i8;
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null)      
							 } //i8
						   } //i7
						} //i6
					} //i5
				} // i4
			} // i3
		} // i2
	}  // i1
		
	return flag;
}//sendPost8Loop	
		
	
	
	/**
	 * send code from 000000000 to 999999999
	 * @throws Exception
	 */
	private boolean sendPost9Loop() throws Exception 
	{		
	  String code ="";
	  boolean flag=false;
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
	
	for (int i1=0;i1<10;i1++)
	{
		for (int i2=0;i2<10;i2++)
		{
			for (int i3=0;i3<10;i3++)
			{
				for (int i4=0;i4<10;i4++)
				{
					for (int i5=0;i5<10;i5++)
					{
						for (int i6=0;i6<10;i6++)
						{
							for (int i7=0;i7<10;i7++)
							{
								for (int i8=0;i8<10;i8++)
								{
									for (int i9=0;i9<10;i9++)
									{
				  code = ""+i1+i2+i3+i4+i5+i6+i7+i8+i9;
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null)      
									} //i9
								} //i8
						   } //i7
						} //i6
					} //i5
				} // i4
			} // i3
		} // i2
	}  // i1
		
	return flag;
}//sendPost9Loop	
		
	/**
	 * send code from 0000000000 to 9999999999
	 * @throws Exception
	 */
	private boolean sendPost10Loop() throws Exception 
	{		
	  String code ="";
	  boolean flag=false;
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://www.rollshop.co.il/test.php");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	
	
	for (int i1=0;i1<10;i1++)
	{
		for (int i2=0;i2<10;i2++)
		{
			for (int i3=0;i3<10;i3++)
			{
				for (int i4=0;i4<10;i4++)
				{
					for (int i5=0;i5<10;i5++)
					{
						for (int i6=0;i6<10;i6++)
						{
							for (int i7=0;i7<10;i7++)
							{
								for (int i8=0;i8<10;i8++)
								{
									for (int i9=0;i9<10;i9++)
									{
										for (int i10=0;i10<10;i10++)
										{
				  code = ""+i1+i2+i3+i4+i5+i6+i7+i8+i9+i10;
					params.add(new BasicNameValuePair("code", code));         //i+""+j));
					//params.add(new BasicNameValuePair("param-2", "Hello!"));
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					//Execute and get the response.
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) 
					{
					    InputStream instream = entity.getContent();
					    
					    BufferedReader rd = new BufferedReader(new InputStreamReader(instream ));
					    
					    try 
					    {
					        // do something useful
					    	
					    	StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) 
							{
								result.append(line);
							}
							
							System.out.println("code="+ code  );   
							String postAnswer = getTextBetweenTags(result.toString(), "body");
							System.out.println("Result:!!!"+postAnswer+"!!!");
							if (postAnswer.equals("WRONG =("))
							 { 
								System.out.println("BAD result");
								//break;
								flag = false;
							 }
							else 
							{
								System.out.println("Good result");
								flag = true;
								break;
							}
							
					    } 
					    finally 
					      {
					         instream.close();
					      }
					} //if  (entity != null) 
									} //i10
									} //i9
								} //i8
						   } //i7
						} //i6
					} //i5
				} // i4
			} // i3
		} // i2
	}  // i1
		
	return flag;
}//sendPost10Loop		
	
	
	
	
	public static String getTextBetweenTags(String param, String tag) throws Exception
	{
		
		String html = param;	
		Document doc = Jsoup.parse(html);
		Element p= doc.select(tag).first();
		String text = doc.body().text(); //some bold text
		//System.out.println(text);
		return text;
	}	
	
	
}