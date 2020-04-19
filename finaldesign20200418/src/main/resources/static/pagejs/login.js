/**
 * 
 */
$(function() {
    $("#loginSystem").on('click', function() {
	if ($('.username').val() === 'admin' && $('.password').val() === 'admin') {
	    window.location.href = "./meeting_log.html";
	}
    });
});

function loginSystem() {
    $.post('http://127.0.0.1:8082/login_system', {
	username : $('.username').val(),
	password : $('.password').val(),
    }, function(json) {
	if (json.error_code != 0) {
	    alert(json.error_msg);
	    return;
	}
	setCookie('photo', json.user.photo);
	window.location.href = "index.html";
    }, 'json')
}