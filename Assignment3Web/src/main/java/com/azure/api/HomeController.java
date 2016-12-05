package com.azure.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	public static String jsonBody;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}

	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public ModelAndView service(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		String analyticsType = request.getParameter("analyticsType");

		// Regression
		if (analyticsType.equals("regression")) {

			String date = request.getParameter("date");
			String time = request.getParameter("time");
			String building = request.getParameter("building");
			String holiday = request.getParameter("holiday");
			String temperature = request.getParameter("temperature");
			String dewpoint = request.getParameter("dewpoint");
			String humidity = request.getParameter("humidity");
			String pressure = request.getParameter("pressure");
			String visibility = request.getParameter("visibility");
			String windspeed = request.getParameter("windspeed");
			String winddir = request.getParameter("winddir");
			String model = request.getParameter("model");

			int weekday = -1;
			int month = -1;
			int day = -1;
			String jsonBody = null;

			if (date == "" || time == "" || building == "" || holiday == "" || temperature == "" || dewpoint == ""
					|| humidity == "" || pressure == "" || visibility == "" || windspeed == "" || winddir == "") {
				mv.addObject("error", "no_value");
				return mv;
			}

			int hours = Integer.parseInt(time);
			System.out.println(hours);

			if (hours < 0 || hours > 23) {
				mv.addObject("error", "hour");
				return mv;
			}

			int holiday1 = Integer.parseInt(holiday);
			if (holiday1 < 0 || holiday1 > 1) {
				mv.addObject("error", "holiday");
				return mv;
			}

			double temperature1 = Double.parseDouble(temperature);
			double dewpoint1 = Double.parseDouble(dewpoint);
			int humidity1 = Integer.parseInt(humidity);
			double pressure1 = Double.parseDouble(pressure);
			double visibility1 = Double.parseDouble(visibility);
			double windspeed1 = Double.parseDouble(windspeed);
			double winddir1 = Double.parseDouble(winddir);

			try {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
				Date startDate = (Date) formatter.parse(date);
				day = Integer.parseInt(date.replaceAll("-", ""));
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				month = cal.get(Calendar.MONTH) + 1;
				// day = cal.get(Calendar.DAY_OF_MONTH);

				/* creating json from form values */

				JSONObject obj = new JSONObject();
				JSONObject inputs = new JSONObject();
				JSONObject input = new JSONObject();

				JSONArray columnName = new JSONArray();
				columnName.add("date");
				columnName.add("hour");
				columnName.add("building");
				columnName.add("isHoliday");
				columnName.add("TemperatureF");
				columnName.add("Dew_PointF");
				columnName.add("Humidity");
				columnName.add("Sea_Level_PressureIn");
				columnName.add("VisibilityMPH");
				columnName.add("Wind_SpeedMPH");
				columnName.add("WindDirDegrees");

				JSONArray allValues = new JSONArray();
				JSONArray value = new JSONArray();
				value.add(day);
				value.add(hours);
				value.add(building);
				value.add(holiday1);
				value.add(temperature1);
				value.add(dewpoint1);
				value.add(humidity1);
				value.add(pressure1);
				value.add(visibility1);
				value.add(windspeed1);
				value.add(winddir1);

				allValues.add(value);

				input.put("ColumnNames", columnName);
				input.put("Values", allValues);
				inputs.put("input1", input);
				obj.put("Inputs", inputs);

				System.out.println("print the value of json " + obj);

				// converting json to string
				jsonBody = obj.toString();
			} catch (Exception e) {
				System.out.println("Error occurred!!" + e);
			}

			// Creating instance of azure class
			AzureML am = new AzureML();

			String res = null;
			System.out.println("Model: " + model);

			if (model.equals("forest")) {
				res = am.callDecisionForestService(jsonBody);
			} else if (model.equals("neural")) {
				res = am.callNeuralNetworkService(jsonBody);
			} else if (model.equals("linear")) {
				res = am.callLinearRegressionService(jsonBody);
			} else if (model.equals("decision")) {
				res = am.callBoostedDecisionTreeService(jsonBody);
			}

			if (res != null) {
				System.out.println("Result of web service :" + res);

				mv.addObject("output", res);
			} else {
				System.out.println("Error thrown by web service call!!");
				mv.addObject("error", "invalid");
				return mv;
			}
			
		} 
//-----------------------------------------------------------------------------------
		// Classification
		else if (analyticsType.equals("classification")) {
			System.out.println("In Classification");

			String meterNum = request.getParameter("meternumbc");
			String temperature = request.getParameter("temperaturec");
			String dewpoint = request.getParameter("dewpointc");
			String winddir = request.getParameter("winddirc");
			String baseHrUsage = request.getParameter("base_hr_usagec");
			String model = request.getParameter("modelc");

			String jsonBody = null;

			if (meterNum == "" || temperature == "" || dewpoint == "" || winddir == "" || baseHrUsage == "") {
				mv.addObject("error", "no_value");
				return mv;
			}
			
			int meterNum1 = Integer.parseInt(meterNum);
			double temperature1 = Double.parseDouble(temperature);
			double dewpoint1 = Double.parseDouble(dewpoint);
			double winddir1 = Double.parseDouble(winddir);
			double baseHrUsage1 = Double.parseDouble(baseHrUsage);
			
			try {
				/* creating json from form values */

				JSONObject obj = new JSONObject();
				JSONObject inputs = new JSONObject();
				JSONObject input = new JSONObject();

				JSONArray columnName = new JSONArray();
				
				columnName.add("meternumb");
				columnName.add("TemperatureF");
				columnName.add("Dew_PointF");
				columnName.add("WindDirDegrees");
				columnName.add("base_hr_usage");

				JSONArray allValues = new JSONArray();
				JSONArray value = new JSONArray();
				value.add(meterNum1);
				value.add(temperature1);
				value.add(dewpoint1);
				value.add(winddir1);
				value.add(baseHrUsage1);

				allValues.add(value);

				input.put("ColumnNames", columnName);
				input.put("Values", allValues);
				inputs.put("input1", input);
				obj.put("Inputs", inputs);

				System.out.println("print the value of json " + obj);

				// converting json to string
				jsonBody = obj.toString();
			} catch (Exception e) {
				System.out.println("Error occurred!!" + e);
			}
			// Creating instance of azure class
			AzureML am = new AzureML();

			String res = null;
			System.out.println("Model: " + model);

			if (model.equals("treec")) {
				res = am.callDecisionTreeClassificationService(jsonBody);
			} else if (model.equals("forestc")) {
				res = am.callRandomForestClassificationService(jsonBody);
			} else if (model.equals("neuralc")) {
				res = am.callNeuralClassificationService(jsonBody);
			} else if (model.equals("linearc")) {
				res = am.callLogisticClassificationService(jsonBody);
			}

			if (res != null) {
				System.out.println("Result of web service :" + res);

				mv.addObject("output", res);
			} else {
				System.out.println("Error thrown by web service call!!");
				mv.addObject("error", "invalid");
				return mv;
			}
		} 
//-------------------------------------------------------------------------------------		
		// Clustering
		else if (analyticsType.equals("cluster")) {
			System.out.println("In Clustering");

			String buildingID = request.getParameter("BuildingID_cluster");
			String meterNum = request.getParameter("meternumb_cluster");
			String normConsumption = request.getParameter("norm_consumption_cluster");
			String temperature = request.getParameter("temperature_cluster");
			String dewpoint = request.getParameter("dewpoint_cluster");
			String humidity = request.getParameter("humidity_cluster");
			String pressure = request.getParameter("pressure_cluster");
			String visibility = request.getParameter("visibility_cluster");
			String model = request.getParameter("model_cluster");

			String jsonBody = null;

			if (meterNum == "" || temperature == "" || dewpoint == "" || 
					buildingID == "" || normConsumption == "" || humidity == "" || 
							pressure == "" || visibility == "" ) {
				mv.addObject("error", "no_value");
				return mv;
			}
			
			int buildingID1 = Integer.parseInt(buildingID);
			int meterNum1 = Integer.parseInt(meterNum);
			double normConsumption1 = Double.parseDouble(normConsumption);
			double temperature1 = Double.parseDouble(temperature);
			double dewpoint1 = Double.parseDouble(dewpoint);
			int humidity1 = Integer.parseInt(humidity);
			double pressure1 = Double.parseDouble(pressure);
			double visibility1 = Double.parseDouble(visibility);
			
			try {
				/* creating json from form values */

				JSONObject obj = new JSONObject();
				JSONObject inputs = new JSONObject();
				JSONObject input = new JSONObject();

				JSONArray columnName = new JSONArray();
				
				columnName.add("BuildingID");
				columnName.add("meternumb");
				columnName.add("norm_consumption");
				columnName.add("TemperatureF");
				columnName.add("Dew_PointF");
				columnName.add("Humidity");
				columnName.add("Sea_Level_PressureIn");
				columnName.add("VisibilityMPH");

				JSONArray allValues = new JSONArray();
				JSONArray value = new JSONArray();
				value.add(buildingID1);
				value.add(meterNum1);
				value.add(normConsumption1);
				value.add(temperature1);
				value.add(dewpoint1);
				value.add(humidity1);
				value.add(pressure1);
				value.add(visibility1);

				allValues.add(value);

				input.put("ColumnNames", columnName);
				input.put("Values", allValues);
				inputs.put("input1", input);
				obj.put("Inputs", inputs);

				System.out.println("print the value of json " + obj);

				// converting json to string
				jsonBody = obj.toString();
			} catch (Exception e) {
				System.out.println("Error occurred!!" + e);
			}
			// Creating instance of azure class
			AzureML am = new AzureML();

			String res = null;
			System.out.println("Model: " + model);

			if (model.equals("kmeans")) {
				res = am.callKMeansClusteringService(jsonBody);
			} else if (model.equals("hie")) {
				res = am.callHierarchicalClusteringService(jsonBody);
			} 

			if (res != null) {
				System.out.println("Result of web service :" + res);

				mv.addObject("output", res);
			} else {
				System.out.println("Error thrown by web service call!!");
				mv.addObject("error", "invalid");
				return mv;
			}
		}

		return mv;
	}

}
