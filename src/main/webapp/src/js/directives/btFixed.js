/**
 * 固定在底部的提示例如你选中了多少
 */
angular.module('app')
  .directive('btFixed', [ function () {
    return {
    	template:'<div ng-show="count > 0" class="bt-fixed info_count bg-info transition"><p class="wrapper-sm"><span style="font-weight: 700">提示:</span>&nbsp;&nbsp;选中了<span class="font-size-bg">{{count}}</span>个学生</p></div>',
//    	link: function(scope, element, attrs){
//    	      scope.ok = function() {
//    			  	scope.ok_or_cancel = true;
//    	        	scope.isclick = false
//    	        	console.log(scope)
//    	        	scope.$emit('clickOk','true')
//    	      };
//    	      scope.cancel = function() {
//    	    	 scope.isclick = false
//    	    	 scope.ok_or_cancel = false;
//    	    	 scope.$emit('clickCanel','false')
//      	      };
//    	    },
    	scope: {
    		count: "=",
    	},
    	replace: true
    };
  }]);