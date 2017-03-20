'use strict';
angular.module('app')
.factory('memberService',['store', function(store) {
	var currentMember = null;
	var memberService = {
		setCurrentMember : setCurrentMember,
		getCurrentMember : getCurrentMember
		};
	return memberService;
	
	function setCurrentMember(member){
		console.log('memberservice');
		currentMember = member;
        store.set('member', member);
        return currentMember;
	}
	
	function getCurrentMember(){
		if (!currentMember) {
            currentMember = store.get('member');
        }
        return currentMember;
	}
      
 }]);