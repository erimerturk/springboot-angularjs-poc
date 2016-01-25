var narRacApp = angular.module('nar-rac', ['ngRoute']);

narRacApp.config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'home'
    }).when('/login', {
        templateUrl: 'login.html',
        controller: 'navigation'
    }).when('/signUp', {
        templateUrl: 'signUp.html',
        controller: 'SignUpController'
    }).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

});

narRacApp.controller('SignUpController', function ($rootScope, $scope, $http, $location) {

    $scope.newPerson = {};

    $scope.signUp = function () {
        $http.post('register', $scope.newPerson)
            .success(function (data, status, headers, config) {
                $scope.newPerson = {};
                $location.path("/");
                $rootScope.authenticated = true;
            })
            .error(function (data, status, headers, config) {
                $rootScope.authenticated = false;
                alert('Error register');
            });
    };


});

narRacApp.controller('home', function ($scope, $http) {

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

    $http.get('/resource/').success(function (data) {
        $scope.greeting = data;
    })
});
narRacApp.controller('navigation',
    function ($rootScope, $scope, $http, $location) {
        var authenticate = function (callback) {
            $http.get('user').success(function (data) {
                if (data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }).error(function () {
                $rootScope.authenticated = false;
                callback && callback();
            });
        }
        authenticate();
        $scope.credentials = {};
        $scope.login = function () {
            $http.post('login', $.param($scope.credentials), {
                headers: {
                    "content-type": "application/x-www-form-urlencoded"
                }
            }).success(function (data) {
                authenticate(function () {
                    if ($rootScope.authenticated) {
                        $location.path("/");
                        $scope.error = false;
                    } else {
                        $location.path("/login");
                        $scope.error = true;
                    }
                });
            }).error(function (data) {
                $location.path("/login");
                $scope.error = true;
                $rootScope.authenticated = false;
            })
        };

        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function (data) {
                $rootScope.authenticated = false;
            });
        }

    });