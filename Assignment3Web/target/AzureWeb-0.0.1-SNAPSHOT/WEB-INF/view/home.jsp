<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
  <html>
      <head>
        <title>Assignment 3</title>
      
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

          <!-- Optional theme -->
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

          <!-- Latest compiled and minified JavaScript -->
          <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

		<script type="text/javascript">
        function myFunction() {
        	if(document.querySelector("#model") == "linear"){
			document.querySelector("#modelname").innerHTML = "Liner Regression";
        	}
        	else {
        		document.querySelector("#modelname").innerHTML = "some model";
        	}
        		
		}
        </script>
        
        </head>
    
    <body>    
    	<c:if test="${not empty error}">
    		<div class="form-group row">        	    	
		    	<div class="alert alert-danger">    		
		    		<c:if test="${error == 'no_value'}">
		  				One or more field is empty. Please enter the value!
		  			</c:if> 
		  			<c:if test="${error == 'hour'}">
		  				Time can be only in between 0 and 23. Please re-enter the value!
		  			</c:if>
		  			<c:if test="${error == 'holiday'}">
		  				Enter either 0 or 1 for Holiday field!
		  			</c:if>     	
		  			<c:if test="${error == 'invalid'}">
		  				Invalid Output!
		  			</c:if>    	
		  			<c:if test="${error == 'maxdigit'}">
		  				Maximum 5 digits are allowed.
		  			</c:if>    	
		    	</div>	    		    		    	
	    	</div>
	    </c:if>
    	   
      <div class="container">
        <div class="form-group row">
            <h2> Assignment 3 </h2>
         </div>
      
        <form method="POST" action="service">          

          <div class="form-group row">
            <label for="date" class="col-sm-2 col-form-label">Date</label>
            <div class="col-sm-5">
              <input type="date" class="form-control" id="date" name="date" placeholder="Date">
            </div>
          </div>

          <div class="form-group row">
            <label for="time" class="col-sm-2 col-form-label">Time (in Hours)</label>
            <div class="col-sm-5">
              <input type="number" class="form-control" value="0" id="time" name="time" placeholder="Time in hours">
            </div>
          </div>
          
          <div class="form-group row">
            <label for="building" class="col-sm-2 col-form-label">Building</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" value="Building 1" id="building" name="building" placeholder="Enter Building name">
            </div>
          </div>
          
          <div class="form-group row">
            <label for="holiday" class="col-sm-2 col-form-label">Holiday</label>
            <div class="col-sm-5">
              <input type="number" class="form-control" value="0" id="holiday" name="holiday" placeholder="Enter Holiday">
            </div>
          </div>
          
          <div class="form-group row">
            <label for="temperature" class="col-sm-2 col-form-label">Temperature</label>
            <div class="col-sm-5">
              <input type="double" class="form-control" value="35.6" id="temperature" name="temperature" placeholder="Temperature in F">
            </div>
          </div>

          <div class="form-group row">
            <label for="dewpoint" class="col-sm-2 col-form-label">Dew Point</label>
            <div class="col-sm-5">
              <input type="double" class="form-control" value="35.6" id="dewpoint" name="dewpoint" placeholder="Dew Point">
            </div>
          </div>
          
          <div class="form-group row">
            <label for="humidity" class="col-sm-2 col-form-label">Humidity</label>
            <div class="col-sm-5">
              <input type="number" class="form-control" value="100" id="humidity" name="humidity" placeholder="Enter Humidity">
            </div>
          </div>
          
           <div class="form-group row">
            <label for="pressure" class="col-sm-2 col-form-label">Sea Level Pressure</label>
            <div class="col-sm-5">
              <input type="double" class="form-control" value="29.42" id="pressure" name="pressure" placeholder="Sea Level Pressure">
            </div>
          </div>
          
          <div class="form-group row">
            <label for="visibility" class="col-sm-2 col-form-label">Visibility</label>
            <div class="col-sm-5">
              <input type="double" class="form-control" value="5.6" id="visibility" name="visibility" placeholder="Visibility">
            </div>
          </div>
          
           <div class="form-group row">
            <label for="windspeed" class="col-sm-2 col-form-label">Wind Speed MPH</label>
            <div class="col-sm-5">
              <input type="double" class="form-control" value="11.5" id="windspeed" name="windspeed" placeholder="Wind Speed">
            </div>
          </div>
          
           <div class="form-group row">
            <label for="winddir" class="col-sm-2 col-form-label">Wind Direction Degrees</label>
            <div class="col-sm-5">
              <input type="double" class="form-control" value="150" id="winddir" name="winddir" placeholder="Wind Direction Degrees">
            </div>
          </div>

          
          <div class="form-group row">
            <label for="predict" class="col-sm-2 col-form-label">Choose Model for Regression</label>            
            <div class="col-sm-5">
              <label class="radio-inline"><input type="radio" id="model" name="model" value="forest" checked="checked">Random Forest</label>
			  <label class="radio-inline"><input type="radio" id="model" name="model" value="neural">Neural Network</label>
			  <label class="radio-inline"><input type="radio" id="model" name="model" value="linear">Linear Regression</label>
			  <label class="radio-inline"><input type="radio" id="model" name="model" value="decision">Boosted Decision Tree</label>
            </div>
          </div>
                                 
          <div class="form-group row" align="center">
            <div class="offset-sm-2 col-sm-10">
              <input type="submit" onclick="myfunction()" class="btn btn-primary" />
            </div>
          </div>
        </form>        
      </div>
      
      <div class="form-group row">   
      		<c:if test="${not empty output}">
      		<div class="alert alert-info">
	      		<div class="col-sm-6">
	      			<h3>Predicted normalized consumption by <span id="modelname"></span> model is: <c:out value="${output}"></c:out></h3>
	      		</div>
	      	</div>
      		</c:if>
      </div>
      
  </body>
 </html>