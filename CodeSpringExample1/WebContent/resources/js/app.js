
var customerManagerModule = angular.module('customerManagerApp', ['ngAnimate']);

customerManagerModule.controller('customerManagerController', function ($scope,$http) {
	
	var urlBase="/CodeSpringExample1/customerManagerApp";
	
	$scope.toggle=true;
	$scope.selection = [];
	
	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		
	//get all customers and display initially
	$http.get(urlBase+'/customers').
    	success(function(data) {    		
	        $scope.customers = data;	        
    })
    .error(function (data){    
	});
	
	//add a new customer
	$scope.addCustomer = function addCustomer() {
				
		if($scope.customerName=="" || $scope.customerDescription=="" ){
			alert("Insufficient Data! Please provide values for customer name and description");
		}
		else{					
			var req = {
					method: 'POST',
					url: urlBase + '/customer/insert',
					headers: { 'Content-Type': 'application/json' },
					data: { customerName: $scope.customerName , customerDescription : $scope.customerDescription}
					}

			$http(req).
			success(function(data) {
				alert("Customer added");
				$scope.customers = data;	
				$scope.customerName="";
				$scope.customerDescription="";
						 			 
				$scope.toggle='!toggle';			 
			}).error(function(data,status,headers,config){
				alert(data);
				alert(status);
				alert(headers);
				alert(config);
			});	
		}
	};
		
	// delete for a given customer by customer id
	  $scope.toggleSelection = function toggleSelection(customerId) {
	      
		  $http.delete(urlBase + '/customer/' +customerId).
		  success(function(data) {
			 alert("Customer delete completed");
			 $scope.customers = data;
		  }).error(function(data,status,headers,config){
		    	alert(data);
		    	alert(status);
		    	alert(headers);
		    	alert(config);
		    });
	      
	      $scope.selection.push(customerId);
	  };
	  
	

	
});
