'use strict';

angular.module('app')
.run(
  [          '$rootScope', '$state', '$stateParams',
    function ($rootScope,   $state,   $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;        
    }
  ]
)
  .config(
    [          '$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 'MODULE_CONFIG', 
      function ($stateProvider,   $urlRouterProvider, JQ_CONFIG, MODULE_CONFIG) {
          $urlRouterProvider.otherwise('/index/dashboard');
          $stateProvider
              .state('index', {
                  abstract: true,
                  url: '/index',
                  templateUrl: 'index/app.html',
              })
              .state('index.dashboard', {
                  url: '/dashboard',
                  templateUrl: 'index/dashboard.html',
                  controller: 'mainCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/main.js'])
              })
               .state('index.description', {
                  url: '/description',
                  templateUrl: 'index/description.html',
                  controller: 'salonInfoCtrl',
              })
               .state('index.code', {//服务项管理
                  url: '/code',
                  templateUrl: 'index/code.html',
                  controller: 'codeCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/code.js'])
              })
               .state('index.hairdressers', {
                       url: '/hairdressers',
                       templateUrl: 'index/hairdresser.html',
                       controller: 'hairdresserCtr',
                       resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/user/hairdresser.js' ])
              })
                .state('index.hairdresserInfo', {//美发师详情
                  url: '/mfs/{code}',
                  templateUrl: 'index/hairdresserInfo.html',
                  controller: 'hairdresserInfoCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/user/hairdresserInfo.js' ])
              })
               .state('index.services', {
                  url: '/services',
                  templateUrl: 'index/services.html',
                  controller: 'servicesCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/main.js'])
              })
              function load(srcs, callback) {
    return {
        deps: ['$ocLazyLoad', '$q',
          function( $ocLazyLoad, $q ){
            var deferred = $q.defer();
            var promise  = false;
            srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
            if(!promise){
              promise = deferred.promise;
            }
            angular.forEach(srcs, function(src) {
              promise = promise.then( function(){
                if(JQ_CONFIG[src]){
                  return $ocLazyLoad.load(JQ_CONFIG[src]);
                }
                angular.forEach(MODULE_CONFIG, function(module) {
                  if( module.name == src){
                    name = module.name;
                  }else{
                    name = src;
                  }
                });
                return $ocLazyLoad.load(name);
              } );
            });
            deferred.resolve();
            return callback ? promise.then(function(){ return callback(); }) : promise;
        }]
    }
  }
      }
	 ]
  );
