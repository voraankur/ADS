import java.io.File;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.net.Proxy.Type.HTTP;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.*;
import sun.net.www.http.HttpClient;

public class AzureWebServices extends HttpServlet
{
	public void init(){
		System.out.println("INIT Called");
	}

	public void destroy(){
		System.out.println("DESTROY Called");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();


		String date = request.getParameter("date");
		String hour = request.getParameter("hour");
		String algo = request.getParameter("optionsRadios");
		
		
//		out.println("<html>");
//		out.println("<body>");
//		out.println("<h2>Form Response</h2>");
//
//                out.println("<table style='width:50%''>");
//                out.println("<tr>");
//                        out.println("<td>Date</td>");
//                        out.println("<td>"+date+"</td>");
//                out.println("</tr>");
//                out.println("<tr>");
//                        out.println("<td>Hour</td>");
//                        out.println("<td>"+hour+"</td>");
//                out.println("</tr>");
//                out.println("<tr>");
//                        out.println("<td>Algo</td>");
//                        out.println("<td>"+algo+"</td>");
//                out.println("</tr>");
//                out.println("</table>");
//                out.println("</body></html>");

		out.close();
	}
        
        // public static JSONObject inpParms;
    public static String apikey;
    public static String apiurl;
    public static String jsonBody;
    
    public static void readJson(String filename) {
        try {
            File apiFile = new File(filename);
            Scanner sc = new Scanner(apiFile);
            jsonBody = "";
            while (sc.hasNext()) {
                jsonBody += sc.nextLine()+"\n";
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    public static String rrsHttpPost() {
        
        HttpPost post;
        HttpClient client;
        StringEntity entity;
        
        try {
            // create HttpPost and HttpClient object
            post = new HttpPost(apiurl);
            client = HttpClientBuilder.create().build();
            
            // setup output message by copying JSON body into 
            // apache StringEntity object along with content type
            entity = new StringEntity(jsonBody, HTTP.UTF_8);
            entity.setContentEncoding(HTTP.UTF_8);
            entity.setContentType("text/json");

            // add HTTP headers
            post.setHeader("Accept", "text/json");
            post.setHeader("Accept-Charset", "UTF-8");
        
            // set Authorization header based on the API key
            post.setHeader("Authorization", ("Bearer "+apikey));
            post.setEntity(entity);

            // Call REST API and retrieve response content
            HttpResponse authResponse = client.execute(post);
            
            return EntityUtils.toString(authResponse.getEntity());
            
        }
        catch (Exception e) {
            
            return e.toString();
        }
    
    }
    
    /**
     * @param args the command line arguments specifying JSON and API info file names
     */
    public static void main(String[] args) {
        
        try {
		
                String jsonFile = "C:\\Tomcat7\\webapps\\AdsAzureWebServices\\WEB-INF\\classes\\rrsJson.json";

                apiurl = "https://ussouthcentral.services.azureml.net/workspaces/bbedb2d56a594afe8f69e0482d9e9e0c/services/afe6cb069f0e4cf09e28d057a9db25f7/execute?api-version=2.0&details=true";
                apikey = "qPL3pQetjdtpde9gzLasS5basWi0B6sw/9YXivrY0IYDOob3+Tgdqk9k9adadAkg1/E1+gsukMLUKVSc0FY4pw==";
                // call method to read JSON input from the JSON file
		readJson(jsonFile);
                
                // print the response from REST API
                String s = rrsHttpPost();
		System.out.println(s);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}