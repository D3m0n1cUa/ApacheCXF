var app = angular.module('app',[]);

app.controller('PersonCtrl', ['$scope','PersonService', function ($scope,PersonService) {
    
    $scope.getPerson = function (search) {
    	$scope.hideAllMessages();
    	if ($scope.searchBy === '1') {
    		$scope.getPersonByNIF(search);
    	} else {
    		$scope.getPersonByName(search);
    	}
    }
    
    $scope.getPersonByNIF = function(nif){
    	PersonService.getPersonByNIF(nif)
        .then(function success(response){
      	  if (response.data.nif != null) {
	        	$scope.persons.length = 0;
	            $scope.persons.push(response.data);
        	} else {
        		$scope.persons.length = 0;
        	}
        },
        function error (response ){
            if (response.status === 404){
          	  $scope.errorMessage('User not found!');
            }
            else {
          	  $scope.errorMessage("Error getting user!");
            }
        });
    }
    
    $scope.getPersonByName = function(name){
    	PersonService.getPersonByName(name)
        .then(function success(response){
      	  if (response.data.length > 0) {
	        	$scope.persons.length = 0;
	        	$scope.persons = response.data;
        	} else {
        		$scope.persons.length = 0;
        	}
        },
        function error (response ){
            if (response.status === 404){
          	  $scope.errorMessage('User not found!');
            }
            else {
          	  $scope.errorMessage("Error getting user!");
            }
        });
    }
    
    
    $scope.addPerson = function () {
        if ($scope.person != null && $scope.person.nif && $scope.person.name) {
            PersonService.addPerson($scope.person.nif, $scope.person.name, $scope.person.address, $scope.person.phone)
              .then (function success(response){
            	  $scope.getAllPersons();
            	  $scope.init();
                  $scope.clearInput();
                  $scope.successMessage('Person added!');
              },
              function error(response){
                  $scope.errorMessage('Error adding person!');
            });
        }
        else {
        	$scope.errorMessage('Please enter nif or/and name!');
        }
    }
    
    $scope.deletePerson = function (nif) {
        PersonService.deletePerson(nif)
          .then (function success(response){
        	  $scope.getAllPersons();
        	  $scope.init();
              $scope.clearInput();
              $scope.successMessage('Person deleted!');
          },
          function error(response){
        	  $scope.errorMessage('Error deleting person!');
          })
    }
    
    $scope.getAllPersons = function () {
        PersonService.getAllPersons()
          .then(function success(response){
              $scope.persons = response.data;
          },
          function error (response ){
        	  $scope.errorMessage('Error getting persons!');
          });
    }
    
    $scope.reset = function () {
    	$scope.getAllPersons();
    	$scope.init();
    	$scope.clearInput();
    }
    
    $scope.init = function () {
    	$scope.showSuccess = false;
    	$scope.message = '';
    	$scope.showError = false;
    	$scope.errorMessage = '';	
    }
    
    
    $scope.clearInput = function() {
    	$scope.searchRequest = null;
    	$scope.person = null;
    	$scope.searchBy = null;
    }
    
    $scope.hideAllMessages = function() {
    	$scope.message = '';
        $scope.showSuccess = false;
        $scope.errorMessage = '';
        $scope.errorMessage = false;
    }
    
    $scope.successMessage = function(message) {
    	$scope.message = message;
        $scope.showSuccess = true;
        $scope.errorMessage = '';
        $scope.errorMessage = false;
    }
    
    $scope.errorMessage = function(message) {
    	$scope.errorMessage = message;
        $scope.errorMessage = true;
        $scope.message = '';
        $scope.showSuccess = false;
    }

}]);

app.service('PersonService',['$http', function ($http) {
  
    this.getPersonByNIF = function getPersonByNIF(nif){
        return $http({
          method: 'GET',
          url: 'services/person/get/nif/'+nif
        });
  }
    
    this.getPersonByName = function getPersonByName(name){
        return $http({
          method: 'GET',
          url: 'services/person/get/name/'+name
        });
  }
  
    this.addPerson = function addPerson(nif, name, address, phone){
        return $http({
          method: 'POST',
          url: 'services/person/add',
          data: {nif:nif, name:name, address:address, phone:phone}
        });
    }
  
    this.deletePerson = function deletePerson(nif){
        return $http({
          method: 'DELETE',
          url: 'services/person/delete/'+nif
        })
    }
  
    this.getAllPersons = function getAllPersons(){
        return $http({
          method: 'GET',
          url: 'services/person/getall'
        });
    }

}]);