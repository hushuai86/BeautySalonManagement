/**
 * 日期控制
 */

  app.controller('DatepickerDemoCtrl', ['$scope', function($scope) {
    

   
    // Disable weekend selection
    $scope.disabled = function(date, mode) {
      return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function() {
      $scope.minDate = $scope.minDate ? null : new Date();
    };
    $scope.toggleMin();

    

//    $scope.dateOptions = {
//      formatYear: 'yy',
//      startingDay: 1,
//      class: 'datepicker'
//    };

//    $scope.initDate = new Date('2016-15-20');
//    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
//    $scope.format = $scope.formats[0];
  }])