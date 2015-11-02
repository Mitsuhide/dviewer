var app = angular.module("dviewer-client", []);

app.controller("InitCtrl", function($scope, $http) {

    /* Consume init service */
    $http.get('api/init')
        .success(function(data) {
            $scope.imageSet = data;
            console.log("response API " + $scope.imageSet);
            return $scope.imageSet;
        })
        .error(function() {
            console.log("can't find data from api.");
        });
        
    /* Consume init service with name folder in parameter */
    $scope.getImages =  function(data) {
        $http.get('api/init/'+ data)
            .success(function(data) {
                $scope.imagesList = data.images;
                console.log("response API " + data.nbImages);
                /* TODO: load html part with path */
                return $scope.imagesList;
            })
            .error(function() {
                console.log("can't find data from api.");
            });
    }
});

