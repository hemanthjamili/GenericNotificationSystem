angular.module('genericNotificationService').controller('NotificationController', NotificationController);

NotificationController.$inject = ['$scope', 'Notification'];

function NotificationController($scope, Notification){
	
	$scope.notifications = Notification.query();

	$scope.notification = {};
	
	$scope.buttonText="Send";
	$scope.saveNotification = function() {		
		
		if ($scope.notification.id !== undefined) {
			Notification.update($scope.notification, function() {
				$scope.notifications = Notification.query();
				$scope.notification = {};
				$scope.buttonText="Send";
			});
		} else {
			Notification.save($scope.notification, function() {
				$scope.notifications = Notification.query();
				$scope.notification = {};
			});
		}		
	}

	$scope.updateNotification = function(notification) {
		
		$scope.buttonText="Update and Send";
		$scope.notification = notification;
	}

	$scope.deleteNotification = function(notification) {
		notification.$delete({id: notification.id}, function() {
			$scope.notifications = Notification.query();
		});
	}	
}     