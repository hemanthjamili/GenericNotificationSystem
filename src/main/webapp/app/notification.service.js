angular.module('genericNotificationService').factory('Notification', Notification);

Notification.$inject = [ '$resource' ];

function Notification($resource) {
	var resourceUrl = 'api/notification/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}