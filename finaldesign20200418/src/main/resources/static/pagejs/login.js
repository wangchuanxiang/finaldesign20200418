/**
 * 
 */
$(function() {
	$("#loginSystem").on('click', loginSystem);

	var video = document.getElementById("video");
	let constraints = {
		video : {
			width : 200,
			height : 100
		},
		audio : true
	};
	let promise = navigator.mediaDevices.getUserMedia(constraints);
	promise.then(function(MediaStream) {
		video.srcObject = MediaStream;
		video.play();
		canvasPlay();
		loginSystemByPhoto();
	});
1
	var canvas = document.getElementById('show_canvas');
	var ctx = canvas.getContext('2d');
	function canvasPlay() {
		ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
		setTimeout(canvasPlay, 150);
	}

	function loginSystemByPhoto() {
		var data = canvas.toDataURL("image/png");
		$.post('http://192.168.199.235:8082/login_system_by_photo', {
			base64Img : data
		}, function(json) {
			if (json.result == null) {
				loginSystemByPhoto();
				return;
			}
			setCookie('photo', photo);
			window.location.href = "index.html";
		}, 'json')
	}
})

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

function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}