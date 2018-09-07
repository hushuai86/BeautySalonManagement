'use strict';

/**
 * Config for the router
 */
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
          var layout = "tpl/app.jsp";
          $urlRouterProvider.otherwise('/app/dashboard-v1');
        
          $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: layout
              })
              .state('app.dashboard-v1', {
                  url: '/dashboard-v1',
                  templateUrl: 'tpl/app_dashboard_v1.html',
                  controller: 'mainCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/main.js'])
                
              })
              //密码修改
              .state('app.updatePassword', {
                  url: '/updatePassword',
                  templateUrl: 'tpl/password.html',
                  controller: 'passwordCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/password.js'])
              })
              //基本信息管理
              .state('app.system', {
                  url: '/system',
                  template: '<div ui-view ></div>'
              })
             .state('app.system.info', {//店铺基本信息维护
                  url: '/info',
                  templateUrl: 'tpl/system/info.html',
                  controller: 'infoCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/info.js'])
              })
              .state('app.system.user', {//账号管理
                  url: '/user',
                  templateUrl: 'tpl/system/user.html',
                  controller: 'userCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/user.js'])
              })
               .state('app.system.hairdresser', {//理发师管理
                  url: '/hairdresser',
                  templateUrl: 'tpl/system/hairdresser.html',
                  controller: 'hairdresserCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/hairdresser.js'])
              })
               .state('app.system.code', {//服务项管理
                  url: '/code',
                  templateUrl: 'tpl/system/code.html',
                  controller: 'codeCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/code.js'])
              })
                .state('app.system.wages', {//工资管理
                  url: '/wages',
                  templateUrl: 'tpl/system/wages.html',
                  controller: 'wagesCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/wages.js'])
              })
              .state('app.system.order', {//订单管理
                  url: '/order',
                  templateUrl: 'tpl/system/order.html',
                  controller: 'orderCtr',
                  resolve: load(['smart-table','xeditable','ui.select', 'toaster', 'js/controllers/system/order.js'])
              })
              .state('app.system.count', {//统计管理
                  url: '/count',
                  templateUrl: 'tpl/system/count.html',
                  controller: 'countCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/count.js','js/echarts.js'])
              })
              //管理员
               .state('app.manager', {
                  url: '/manager',
                  template: '<div ui-view ></div>'
              })
              .state('app.manager.user', {//账号管理
                  url: '/user',
                  templateUrl: 'tpl/manager/user.html',
                  controller: 'userCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/user.js'])
              })
               .state('app.manager.order', {//预约管理
                  url: '/order',
                  templateUrl: 'tpl/manager/order.html',
                  controller: 'orderCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/manager/order.js'])
              })
               .state('app.manager.order2', {//订单管理
                  url: '/order2',
                  templateUrl: 'tpl/manager/order2.html',
                  controller: 'order2Ctr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/manager/order2.js'])
              })
              //美发师
              .state('app.hairdresser', {
                  url: '/hairdresser',
                  template: '<div ui-view ></div>'
              })
               .state('app.hairdresser.info', {//个人信息管理
                  url: '/info',
                  templateUrl: 'tpl/hairdresser/info.html',
                  controller: 'hairdressInfoCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/hairdresser/info.js'])
              })
                .state('app.hairdresser.order', {//订单查询
                  url: '/order',
                  templateUrl: 'tpl/hairdresser/order.html',
                  controller: 'orderCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/hairdresser/order.js'])
              })
               .state('app.hairdresser.wages', {//工资查询
                  url: '/wages',
                  templateUrl: 'tpl/hairdresser/wages.html',
                  controller: 'wagesCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/hairdresser/wages.js','js/echarts.js'])
              })
              //会员
              .state('app.user', {
                  url: '/user',
                  template: '<div ui-view ></div>'
              })
              .state('app.user.info', {//个人信息管理
                  url: '/info',
                  templateUrl: 'tpl/user/info.html',
                  controller: 'userInfoCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/user/info.js'])
              })
                .state('app.user.description', {//预约列表
                  url: '/description',
                  templateUrl: 'index/description.html',
                  controller: 'salonInfoCtrl',
              })
               .state('app.user.hairdresser', {//美发师列表
                  url: '/hairdresser',
                  templateUrl: 'tpl/user/hairdresser.html',
                  controller: 'hairdresserCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/user/hairdresser.js' ])
              })
              .state('app.user.hairdresserInfo', {//美发师详情
                  url: '/mfs/{code}',
                  templateUrl: 'tpl/user/hairdresserInfo.html',
                  controller: 'hairdresserInfoCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/user/hairdresserInfo.js' ])
              })
               .state('app.user.code', {//服务项管理
                  url: '/code',
                  templateUrl: 'index/code.html',
                  controller: 'codeCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/system/code.js'])
              })
                .state('app.user.order', {//预约列表
                  url: '/order',
                  templateUrl: 'tpl/user/order.html',
                  controller: 'orderCtr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/user/order.js' ])
              })
               .state('app.user.order2', { 
                  url: '/order2',
                  templateUrl: 'tpl/user/order2.html',
                  controller: 'order2Ctr',
                  resolve: load(['xeditable','ui.select', 'toaster', 'js/controllers/user/order2.js' ])
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
