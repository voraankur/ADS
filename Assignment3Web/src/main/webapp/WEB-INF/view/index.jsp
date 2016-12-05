<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
body {
  padding : 10px ;
  
}

#exTab1 .tab-content {
  color : white;
  background-color: #428bca;
  padding : 5px 15px;
}

#exTab2 h3 {
  color : white;
  background-color: #428bca;
  padding : 5px 15px;
}

/* remove border radius for the tab */

#exTab1 .nav-pills > li > a {
  border-radius: 0;
}

/* change border radius for the tab , apply corners on top*/

#exTab3 .nav-pills > li > a {
  border-radius: 4px 4px 0 0 ;
}

#exTab3 .tab-content {
  color : white;
  background-color: #428bca;
  padding : 5px 15px;
}
</style>

<head>
<title>Assignment 3</title>
<script type="text/javascript">
        function isNumber(evt) {
            evt = (evt) ? evt : window.event;
            var charCode = (evt.which) ? evt.which : evt.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            	alert("You can enter only digits!")
                return false;
            }
            return true;
        }
        </script>

<script type="text/javascript">
        function SetDate()
        {
        var date = new Date();

        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();

        if (month < 10) month = "0" + month;
        if (day < 10) day = "0" + day;

        var today = year + "-" + month + "-" + day;


        document.getElementById('date').value = today;
        }
        </script>

<script type="text/javascript">
            //<![CDATA[
            try{if (!window.CloudFlare) {var CloudFlare=[{verbose:0,p:0,byc:0,owlid:"cf",bag2:1,mirage2:0,oracle:0,paths:{cloudflare:"/cdn-cgi/nexp/dok3v=1613a3a185/"},atok:"b274a8634124cafa9b77ae2d5fbebca3",petok:"0c4931b11db65e057eed90a30d0139b933bb7b0a-1470431826-1800",zone:"easy-development.com",rocket:"0",apps:{"abetterbrowser":{"ie":"7"}},sha2test:0}];!function(a,b){a=document.createElement("script"),b=document.getElementsByTagName("script")[0],a.async=!0,a.src="//ajax.cloudflare.com/cdn-cgi/nexp/dok3v=0489c402f5/cloudflare.min.js",b.parentNode.insertBefore(a,b)}()}}catch(e){};
            //]]>
            </script>
<script src="resources/uff/jquery.js"></script>
<link href="resources/uff/animate.css" rel="stylesheet" type="text/css">
<script src="resources/uff/jquery-ultimate-fancy-form.min.js"></script>
<link href="resources/uff/jquery-ultimate-fancy-form.css"
	rel="stylesheet" type="text/css">
<link href="resources/assets/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<!-- <script src="resources/assets/bootstrap/js/bootstrap.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script src="resources/assets/bootstrap/js/bootstrap.js"></script>
<link href="resources/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<link href="resources/assets/registration/custom.css" rel="stylesheet"
	type="text/css">
</head>

<body onload="SetDate();">
<div class="container">
		<h2>Assignment 3 - Team 11</h2>
	</div>
	<div class="form-group row col-lg-4 col-lg-offset-3">
		<div class="col-sm-2">
			<a target="_blank" href="resources/Assignment3.pdf">
				Documentation </a>
		</div>
		<div class="col-sm-1">
			<a target="_blank" href="https://github.com/voraankur/ADS">
				GitHub </a>
		</div>
		<div class="col-sm-1">
			<a target="_blank" href="resources/testdata.xlsx"> Test Data </a>
		</div>
	</div>

<div class="nav-control">
		<div class="form-group row">
			<c:if test="${not empty output}">
				<div>
					<div class="col-sm-6">
						<h3>
							<c:out value="${output}"></c:out>
						</h3>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	
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
	
<div id="exTab2" class="container">	

<ul class="nav nav-tabs">
			<li class="active">
        <a  href="#1" data-toggle="tab">Regression</a>
			</li>
			<li><a href="#2" data-toggle="tab">Classification</a>
			</li>
			<li><a href="#3" data-toggle="tab">Clusturing</a>
			</li>
		</ul>

			<div class="tab-content ">
			  <div class="tab-pane active" id="1">
          <form method="POST" action="service">

					<div class="form-group row">
						<label for="date" class="col-sm-2 col-form-label">Date</label>
						<div class="col-sm-5">
							<input type="date" class="form-control" id="date" name="date"
								placeholder="Date" />
						</div>
					</div>

					<div class="form-group row">
						<label for="time" class="col-sm-2 col-form-label">Time (in
							Hours)</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" value="7" id="time"
								name="time" placeholder="Time in hours" />
						</div>
					</div>

					<div class="form-group row">
						<label for="building" class="col-sm-2 col-form-label">Building</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" value="Building 1"
								id="building" name="building" placeholder="Enter Building name">
						</div>
					</div>

					<div class="form-group row">
						<label for="holiday" class="col-sm-2 col-form-label">Holiday</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" value="0" id="holiday"
								name="holiday" placeholder="Enter Holiday">
						</div>
					</div>

					<div class="form-group row">
						<label for="temperature" class="col-sm-2 col-form-label">Temperature</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="35.6"
								id="temperature" name="temperature"
								placeholder="Temperature in F" />
						</div>
					</div>

					<div class="form-group row">
						<label for="dewpoint" class="col-sm-2 col-form-label">Dew
							Point</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="35.6"
								id="dewpoint" name="dewpoint" placeholder="Dew Point">
						</div>
					</div>

					<div class="form-group row">
						<label for="humidity" class="col-sm-2 col-form-label">Humidity</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" value="100"
								id="humidity" name="humidity" placeholder="Enter Humidity">
						</div>
					</div>

					<div class="form-group row">
						<label for="pressure" class="col-sm-2 col-form-label">Sea
							Level Pressure</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="29.42"
								id="pressure" name="pressure" placeholder="Sea Level Pressure">
						</div>
					</div>

					<div class="form-group row">
						<label for="visibility" class="col-sm-2 col-form-label">Visibility</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="5.6"
								id="visibility" name="visibility" placeholder="Visibility">
						</div>
					</div>

					<div class="form-group row">
						<label for="windspeed" class="col-sm-2 col-form-label">Wind
							Speed MPH</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="11.5"
								id="windspeed" name="windspeed" placeholder="Wind Speed">
						</div>
					</div>

					<div class="form-group row">
						<label for="winddir" class="col-sm-2 col-form-label">Wind
							Direction Degrees</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="150"
								id="winddir" name="winddir" placeholder="Wind Direction Degrees">
						</div>
					</div>

					<div class="form-group row">
						<label for="predict" class="col-sm-2 col-form-label">Choose
							Model for Regression</label>
						<div class="col-sm-5">
							<label class="radio-inline"><input type="radio"
								id="model" name="model" value="forest" checked="checked">Random
								Forest</label> <label class="radio-inline"><input type="radio"
								id="model" name="model" value="neural">Neural Network</label> <label
								class="radio-inline"><input type="radio" id="model"
								name="model" value="linear">Linear Regression</label> <label
								class="radio-inline"><input type="radio" id="model"
								name="model" value="decision">Boosted Decision Tree</label>
						</div>
					</div>

					<div class="form-group row" align="center">
						<div class="offset-sm-2 col-sm-10">
						<input type="hidden" value="regression" name="analyticsType" />
							<input type="submit" onclick="myfunction()"
								class="btn btn-primary" />
						</div>
					</div>
				</form>
				</div>
				<div class="tab-pane" id="2">
          <form method="POST" action="service">

					<div class="form-group row">
						<label for="meternumbc" class="col-sm-2 col-form-label">Meter
							Number</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" value="1"
								id="meternumbc" name="meternumbc"
								placeholder="Enter Meter Number">
						</div>
					</div>

					<div class="form-group row">
						<label for="temperaturec" class="col-sm-2 col-form-label">Temperature</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="35.6"
								id="temperaturec" name="temperaturec"
								placeholder="Temperature in F">
						</div>
					</div>

					<div class="form-group row">
						<label for="dewpointc" class="col-sm-2 col-form-label">Dew
							Point</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="33.8"
								id="dewpointc" name="dewpointc" placeholder="Dew Point">
						</div>
					</div>

					<div class="form-group row">
						<label for="winddirc" class="col-sm-2 col-form-label">Wind
							Direction Degrees</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="150"
								id="winddirc" name="winddirc"
								placeholder="Wind Direction Degrees">
						</div>
					</div>
					<div class="form-group row">
						<label for="base_hr_usagec" class="col-sm-2 col-form-label">Base
							hour usage</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="25"
								id="base_hr_usagec" name="base_hr_usagec"
								placeholder="base_hr_usage">
						</div>
					</div>

					<div class="form-group row">
						<label for="predictc" class="col-sm-2 col-form-label">Choose
							Model for Classification</label>
						<div class="col-sm-5">
							<label class="radio-inline"><input type="radio"
								name="modelc" value="treec" checked="checked">Decision
								Tree</label> <label class="radio-inline"><input type="radio"
								name="modelc" value="forestc">Random Forest</label> <label
								class="radio-inline"><input type="radio" name="modelc"
								value="neuralc">Neural Network</label> <label
								class="radio-inline"><input type="radio" name="modelc"
								value="linearc">Linear Regression</label>
						</div>
					</div>

					<input type="hidden" value="classification" name="analyticsType" />
					<div class="form-group row" align="center">
						<div class="offset-sm-2 col-sm-10">
							<input type="submit" class="btn btn-primary" />
						</div>
					</div>
				</form>
				</div>
        <div class="tab-pane" id="3">
          <form method="POST" action="service">

					<div class="form-group row">
						<label for="BuildingID_cluster" class="col-sm-2 col-form-label">BuildingID
						</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" value="5198"
								id="BuildingID_cluster" name="BuildingID_cluster"
								placeholder="Enter Meter Number">
						</div>
					</div>

					<div class="form-group row">
						<label for="meternumb_cluster" class="col-sm-2 col-form-label">Meter
							Number</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" value="1"
								id="meternumb_cluster" name="meternumb_cluster"
								placeholder="Enter Meter Number">
						</div>
					</div>

					<div class="form-group row">
						<label for="norm_consumption_cluster"
							class="col-sm-2 col-form-label">Normalized Consumption</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="0.227"
								id="norm_consumption_cluster" name="norm_consumption_cluster"
								placeholder="normalized consumption cluster">
						</div>
					</div>

					<div class="form-group row">
						<label for="dewpoint_cluster" class="col-sm-2 col-form-label">Dew
							Point</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="33.8"
								id="dewpoint_cluster" name="dewpoint_cluster"
								placeholder="Dew Point">
						</div>
					</div>

					<div class="form-group row">
						<label for="temperature_cluster" class="col-sm-2 col-form-label">Temperature</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="35.6"
								id="temperature_cluster" name="temperature_cluster"
								placeholder="Temperature in F">
						</div>
					</div>

					<div class="form-group row">
						<label for="humidity_cluster" class="col-sm-2 col-form-label">Humidity</label>
						<div class="col-sm-5">
							<input type="number" class="form-control" value="93"
								id="humidity_cluster" name="humidity_cluster"
								placeholder="Enter Humidity">
						</div>
					</div>

					<div class="form-group row">
						<label for="pressure_cluster" class="col-sm-2 col-form-label">Sea
							Level Pressure</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="29.42"
								id="pressure_cluster" name="pressure_cluster"
								placeholder="Sea Level Pressure">
						</div>
					</div>

					<div class="form-group row">
						<label for="visibility_cluster" class="col-sm-2 col-form-label">Visibility</label>
						<div class="col-sm-5">
							<input type="double" class="form-control" value="5.6"
								id="visibility_cluster" name="visibility_cluster"
								placeholder="Visibility">
						</div>
					</div>

					<div class="form-group row">
						<label for="predict_cluster" class="col-sm-2 col-form-label">Choose
							Model for Classification</label>
						<div class="col-sm-5">
							<label class="radio-inline"><input type="radio"
								name="model_cluster" value="kmeans" checked="checked">K-means</label>
							<label class="radio-inline"><input type="radio"
								name="model_cluster" value="hie">Hierarchical</label>
						</div>
					</div>

					<div class="form-group row" align="center">
						<div class="offset-sm-2 col-sm-10">
							<input type="hidden" value="cluster" name="analyticsType" /> <input
								type="submit" class="btn btn-primary" />
						</div>
					</div>
				</form>
				</div>
			</div>
  </div>
  

	

</body>
</html>