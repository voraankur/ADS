package com.azure.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.zip.InflaterInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AzureML {
	   
	    public static String apiurl;
	    public static String apikey;
	    
	    // Regression
	    // Calling random Forest model
	    public String callDecisionForestService(String json) {
	    	System.out.println("calling random forest model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/bbedb2d56a594afe8f69e0482d9e9e0c/services/afe6cb069f0e4cf09e28d057a9db25f7/execute?api-version=2.0&details=true";
	    	apikey = "qPL3pQetjdtpde9gzLasS5basWi0B6sw/9YXivrY0IYDOob3+Tgdqk9k9adadAkg1/E1+gsukMLUKVSc0FY4pw==";
	    		    	
	    	String result = rrsHttpPost(json);
	    	return retrieveOutput(result, "Regression", 1);
	    }
	    
	    // Calling Neural network model
	    public String callNeuralNetworkService(String json) {
	    	System.out.println("calling neural network model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/bbedb2d56a594afe8f69e0482d9e9e0c/services/8be715f097864ca997753d2cc71cd107/execute?api-version=2.0&details=true";
	    	apikey = "sHJildMu9ZCgeS4sAypO74YMusF1D6jFDdtPJtcQ8LVXGBUHj3FruYxxTtR7/3mZm33I5ZXfCunbh2AeXTOY+g==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Regression", 2);
	    }
	    
	    // Calling Linear Regression model 
	    public String callLinearRegressionService(String json) {
	    	System.out.println("calling linear regression model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/bbedb2d56a594afe8f69e0482d9e9e0c/services/fc61d2ce12c14e33bd9de8be23c8c81f/execute?api-version=2.0&details=true";
	    	apikey = "lNXx9qSZTEzV02LelrflVR+Pvj6aRqRbIhqRqiorjikIHuBgKZolhNZ7NTqHjF1PDX3O5S7BCC+Nyp5ZIS3VLg==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Regression", 3);	
	    }
	    
	    // Calling Boosted Decision Tree model 
	    public String callBoostedDecisionTreeService(String json) {
	    	System.out.println("calling boosted decision tree regression model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/bbedb2d56a594afe8f69e0482d9e9e0c/services/3a0ba8fb889b4e70a640ec0c298889ee/execute?api-version=2.0&details=true";
	    	apikey = "7nt1uTg+VmOk30YaFtZQmvE4LuW0qq+KnpB9Ow+ZQgTN+RzI6wDCKT5HHKDcza91lX35Il8LCi/1V/SudRRoCA==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Regression", 4);	
	    }
	    
	    //Classification
	    // Calling Logistic Classification
	    public String callLogisticClassificationService(String json) {
	    	System.out.println("calling logistic classification model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/1893663180d44b459c8fe63a23a70448/services/f70ada5de8a64995828f5c801f4f6d78/execute?api-version=2.0&details=true";
	    	apikey = "hFMZOXffrlk9Qr+9Mvy81IcQIhI1t2qqnpSSj1aaDQW00YAoTmu0ZCGlhywuUt7iFBR2c+X2CRpxIqv4tpt58A==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Classification", 5);	
	    }
	    
	    // Calling Neural Classification
	    public String callNeuralClassificationService(String json) {
	    	System.out.println("calling neural classification model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/1893663180d44b459c8fe63a23a70448/services/3b8b71910a7d480dbed540a9600adffc/execute?api-version=2.0&details=true";
	    	apikey = "13d7bylFscQZ8sOPw8Jw9k3q4QDLj0pAyGaOolajeNtNqUggs7YfLhhdnU/CYEFi9KAo9EjAA6yOPpvzdk6AmA==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Classification", 6);	
	    }
	    
	    // Calling Decision Tree Classification
	    public String callDecisionTreeClassificationService(String json) {
	    	System.out.println("calling decision tree classification model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/1893663180d44b459c8fe63a23a70448/services/fc407550921c4c72a686bf9d4f64ca5e/execute?api-version=2.0&details=true";
	    	apikey = "a/3ToMlAjKAQb3zzMUsq5Bu7YkhqbghhC+XH8ua77Af017dmjCs+fjeDGpLm6PQDH4NJCL7uFKgC4tznFiAXYQ==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Classification", 7);	
	    }
	    
	    // Calling Random Forest Classification
	    public String callRandomForestClassificationService(String json) {
	    	System.out.println("calling decision forest classification model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/1893663180d44b459c8fe63a23a70448/services/2b1f7d1388454f519c2facb4c6580892/execute?api-version=2.0&details=true";
	    	apikey = "tUhiZK8w8r0FYXjy+hqbu6W1pH1HqNxF2n04By4ZWyMI+GWLoAOjC5blGfxBX+qrrSzmhFji0I0godMHhkzLow==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Classification", 8);	
	    }
	    
	    // Calling K-Means Clustering
	    public String callKMeansClusteringService(String json) {
	    	System.out.println("calling K-Means Clustering model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/ab815273407841428d5f610bd7a1028a/services/e3a6c792dda54c6f97cbd20d16c54fab/execute?api-version=2.0&details=true";
	    	apikey = "M4LLU8fVgS0+GskBhBe/QNWRkC7CsDfjhEEnfPDo722IQ7ZJjg3tpr7Gyu8IO15uujpB6tdbeSFumhYLjgL3zw==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Clustering", 9);	
	    }
	    
	    // Calling Hierarchical Clustering
	    public String callHierarchicalClusteringService(String json) {
	    	System.out.println("calling K-Means Clustering model: ");
	    	
	    	apiurl = "https://ussouthcentral.services.azureml.net/workspaces/ab815273407841428d5f610bd7a1028a/services/e3a6c792dda54c6f97cbd20d16c54fab/execute?api-version=2.0&details=true";
	    	apikey = "M4LLU8fVgS0+GskBhBe/QNWRkC7CsDfjhEEnfPDo722IQ7ZJjg3tpr7Gyu8IO15uujpB6tdbeSFumhYLjgL3zw==";
	    	
	    	String input = rrsHttpPost(json);
	    	return retrieveOutput(input, "Clustering", 10);	
	    }
	   
	    /**
	     * Call REST API for retrieving prediction from Azure ML 
	     * @return response from the REST API
	     */	
	    public String rrsHttpPost(String jsonBody) {
	        
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
	            System.out.println("Error occurred while calling the service!!");
	            return e.toString();
	        }
	    }	 
	    
	    public String retrieveOutput(String input, String modelType, int num) {
			
	    	try {
		    	JSONParser parser = new JSONParser();	    	
		    	Object obj = parser.parse(input);	 
		    	
				JSONObject json = (JSONObject) obj;
								
			   JSONObject result = (JSONObject) json.get("Results");
			   System.out.println(json.get("Results"));
				
		       JSONObject output1 = (JSONObject)result.get("output1");
		       System.out.println(result.get("output1"));	
		       
		       JSONObject value = (JSONObject)output1.get("value");
		       System.out.println(output1.get("value"));
		        
		       String res = null; 
		       JSONArray strArray = (JSONArray)value.get("Values");				
		       Iterator<Object> itr = strArray.iterator();
			
				
				while(itr.hasNext()) {
					res = itr.next().toString();
					break;
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append(res);
				
				if(modelType.equalsIgnoreCase("Regression")) {
					String op[] = res.split(",");
					String result_first = "The Normalised Consumption by ";
					if (num==1) {
						String output = result_first + "Random Forest Regression is " + op[11];
						return output;
					} else if (num==2) {
						String output = result_first + "Neural Network Regression is " + op[11];
						return output;
					} else if (num==3) {
						String output = result_first + "Linear Regression Regression is " + op[11];
						return output;
					} else if (num==4) {
						String output = result_first + "Boosted Decision Regression is " + op[11];
						return output;
					}
				} else if(modelType.equalsIgnoreCase("Classification")) {
					String op[] = res.split(",");
					String result_first = "The Classified Base Hour Class by ";
					if (num==5) {
						String output = result_first + "Logistic Regression Classification is " + op[5];
						return output;
					} else if (num==6) {
						String output = result_first + "Neural Network Classification is " + op[5];
						return output;
					} else if (num==7) {
						String output = result_first + "Decision Tree Classification is " + op[5];
						return output;
					} else if (num==8) {
						String output = result_first + "Random Forest Classification is " + op[5];
						return output;
					}
				} else if(modelType.equalsIgnoreCase("Clustering")) {
					String op[] = res.split(",");
					String result_first = "Cluster Number is ";
					if (num==9) {
						String output = result_first + op[8];
						return output;
					} 
					if (num==10) {
						String output = result_first + op[8];
						return output;
					}
				}
				
				
				//String op[] = res.split(",");
				//String s = res.substring(2,sb.indexOf("]"));				
				
				//String output =	s.substring(0,s.lastIndexOf('"'));
				String output = res;
				System.out.println(output);
				return output;
	    	}
	    	catch(Exception e) {
	    		System.out.println("Error while parsing output!!");
	    		e.printStackTrace();;
	    	}
	    	
	    	return null;
	    }
	    }