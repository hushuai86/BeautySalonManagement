/**
 * 弹出框指令
 */

angular.module('app')
  .directive('ngAlert', ['$timeout', function ($timeout) {
    return {
    	template:'<div><p>是否确定操作？</p><button class="btn btn-warning m-r " ng-click="cancel()">取消</button><button ng-click="ok()" class="btn btn-info ">确定</button></div>',
    	link: function(scope, element, attrs){
    		  
    	      scope.ok = function() {		  	
    			  	scope.ok_or_cancel = true;
    	
    	        	scope.isclick = false
    	        	
    	        	scope.$emit('clickOk','true')
    	      };
    	      scope.cancel = function() {
    	    	 scope.isclick = false;
    	    	 scope.ok_or_cancel = false;
    	    	 scope.$emit('clickCanel','false')
      	      };
    	    },
    	scope: {
    		ok_or_cancel:"=",
    		isclick:"="
    	}
    };
  }]);