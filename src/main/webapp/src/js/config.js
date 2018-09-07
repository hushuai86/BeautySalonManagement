// config

var app =  
angular.module('app')
  .config(
    [        '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide) {
        
        // lazy controller, directive and service
        app.controller = $controllerProvider.register;
        app.directive  = $compileProvider.directive;
        app.filter     = $filterProvider.register;
        app.factory    = $provide.factory;
        app.service    = $provide.service;
        app.constant   = $provide.constant;
        app.value      = $provide.value;
    }
  ])
  .config(['$translateProvider', function($translateProvider){
    // Register a loader for the static files
    // So, the module will search missing translation tables under the specified urls.
    // Those urls are [prefix][langKey][suffix].
    $translateProvider.useStaticFilesLoader({
      prefix: 'l10n/',
      suffix: '.js'
    });
    // Tell the module what language to use by default
    $translateProvider.preferredLanguage('en');
    // Tell the module to store the language in the local storage
    $translateProvider.useLocalStorage();
  }])
  .config(['$httpProvider', function($httpProvider) {  
    $httpProvider.defaults.headers.post["Content-Type"] =  
        "application/x-www-form-urlencoded";  
 $httpProvider.defaults.  
     transformRequest.unshift(function(data,headersGetter) {  
    var key, result = [];  
    for (key in data) {  
     if (data.hasOwnProperty(key)) {  
         result.push(encodeURIComponent(key) + "="  
                + encodeURIComponent(data[key]));  
     }  
 }  
 return result.join("&");  
});  
}])
app.filter('paging', function() {
	  return function (items, index, pageSize) {
	    if (!items)
	      return [];
	    var offset = (index - 1) * pageSize;
	    return items.slice(offset, offset + pageSize);
	  }
	});
app.filter('size', function() {
	  return function (items) {
	    if (!items)
	      return 0;
	    return items.length || 0
	  }
	});