<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="customerManagerApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Manager</title>

	<script data-require="angular.js@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular.js"></script>
	<script data-require="angular-animate@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular-animate.js"></script>  
	<script type="text/javascript" src="<c:url value="/resources/js/app.js" />"></script>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css">
<link href="<c:url value="/resources/css/css/font-awesome.css" />" rel="stylesheet"  type="text/css">
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>
<body>

<div ng-controller="customerManagerController">
	<div id="task-panel" class="fadein fadeout showpanel panel"  ng-show="toggle">	
		<div class="panel-heading">
			<i class="panel-title-icon fa fa-tasks"></i>
			<span class="panel-title">Customers</span>
			<div class="panel-heading-controls">
				<button ng-click="toggle = !toggle" class="btn-panel">Add New Customer</button>
			</div>
		</div>
		<div class="panel-body">
			<div class="task" ng-repeat="customer in customers track by $index">
			

				<a href="<c:url value="/customerManagerApp/customer/{{customer.customerId}}" />">
					<span >{{customer.customerName}}</span>
				</a>									 				 				 		 
				 <span class="priority priority-yellow">{{customer.customerDescription}}</span>
				
				<div class="action-checkbox">
					<input id="{{customer.customerId}}" type="checkbox" value="{{customer.customerId}}" ng-checked="selection.indexOf(customer.customerId)>-1" ng-click="toggleSelection(customer.customerId)" />
	  				<label for="{{customer.customerId}}"></label>		
				</div>
				
			</div>
		</div>
	</div>
	<div id="add-customer-panel" class="fadein fadeout addpanel panel" ng-hide="toggle">
		<div class="panel-heading">
			<i class="panel-title-icon fa fa-plus"></i>
			<span class="panel-title">Add Customer</span>
			<div class="panel-heading-controls">
				<button ng-click="toggle = !toggle" class="btn-panel">Show All Customers</button>
			</div>
		</div>
		<div class="panel-body">
			<div class="task" >
				<table class="add-task">
					<tr>
						<td>Customer Name:</td>
						<td><input type="text" ng-model="customerName"/></td>
					</tr>
					<tr>
						<td>Customer Description:</td>
						<td><input type="text" ng-model="customerDescription"/></td>
					</tr>
										
					<tr>
						<td><br/><button ng-click="addCustomer()" class="btn-panel-big">Add New Customer</button></td>
					</tr>
				</table>								
			</div>
		</div>
	</div>
</div>
</body>
</html>