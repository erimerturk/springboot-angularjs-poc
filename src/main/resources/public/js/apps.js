var narRacApp = angular.module('nar-rac',[]);

narRacApp.controller('SignUpController', ['$scope', '$http', '$window', function ($scope, $http, $window) {

    $scope.newPerson = {};

    $scope.signUp = function(){
        $http.post('signUp',$scope.newPerson)
            .success(function(data, status, headers, config) {
                $scope.newPerson = {};
                $window.location.href = "/";
            })
            .error(function(data, status, headers, config) {
                alert('Error register');
            });
    };


}]);

var personsApp = angular.module('persons',['ngRoute']);

personsApp.controller('PersonController', [ '$scope', '$http', function ($scope, $http) {

    $scope.newPerson = {};
    $scope.editPerson = {};

    $scope.loadPersons = function(){
        $http.get('persons')
            .success(function(data, status, headers, config) {
                $scope.persons = data;
            })
            .error(function(data, status, headers, config) {
                alert('Error loading Persons');
            });
    };

    $scope.loadPerson = function(person){
        $http.get('persons/'+person.id)
            .success(function(data, status, headers, config) {
                $scope.editPerson = data;
            })
            .error(function(data, status, headers, config) {
                alert('Error loading Person');
            });
    };

    $scope.updatePerson = function(){
        $http.post('persons',$scope.editPerson)
            .success(function(data, status, headers, config) {
                $scope.editPerson = {};
                $scope.loadPersons();
            })
            .error(function(data, status, headers, config) {
                alert('Error update');
            });
    };

    $scope.deletePerson = function(person){
        $http.delete('persons/'+person.id)
            .success(function(data, status, headers, config) {
                $scope.loadPersons()
            })
            .error(function(data, status, headers, config) {
                alert('Error deleting Person');
            });
    };

    $scope.loadPersons();
}]);
