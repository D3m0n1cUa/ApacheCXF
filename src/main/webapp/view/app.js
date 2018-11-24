var app = angular.module('app',[]);

app.controller('PersonCtrl', ['$scope','PersonService', function ($scope,PersonService) {
    
    $scope.getPerson = function () {
        var id = $scope.person.nif;
        PersonService.getPerson($scope.person.nif)
          .then(function success(response){
              $scope.person = response.data;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'User not found!';
              }
              else {
                  $scope.errorMessage = "Error getting user!";
              }
          });
    }
    
    $scope.addPerson = function () {
        if ($scope.person != null && $scope.person.nif && $scope.person.name) {
            PersonService.addPerson($scope.person.nif, $scope.person.name, $scope.person.address, $scope.person.phone)
              .then (function success(response){
                  $scope.message = 'Person added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding person!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please enter nif or/and name!';
            $scope.message = '';
        }
    }
    
    $scope.deletePerson = function () {
        PersonService.deleteUser($scope.person.nif)
          .then (function success(response){
              $scope.message = 'Person deleted!';
              $scope.person = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error deleting person!';
              $scope.message='';
          })
    }
    
    $scope.getAllPersons = function () {
        PersonService.getAllPersons()
          .then(function success(response){
              $scope.persons = response.data;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting persons!';
          });
    }

}]);

app.service('PersonService',['$http', function ($http) {
  
    this.getPerson = function getPerson(personNif){
        return $http({
          method: 'GET',
          url: 'services/person/get/'+personNif
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