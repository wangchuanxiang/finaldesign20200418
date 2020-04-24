/**
 * 
 */
$(function() {
    var meetingLogUrl = "http://localhost:8888/meeting_log/";

    let video = document.getElementById("video");
    let constraints = {
	video : {
	    width : 600,
	    height : 600
	},
	audio : true
    };
    let promise = navigator.mediaDevices.getUserMedia(constraints);
    promise.then(function(MediaStream) {
	video.srcObject = MediaStream;
	video.play();
	canvasPlay();
    });

    let canvas = document.getElementById('show_canvas');
    let ctx = canvas.getContext('2d');
    function canvasPlay() {
	ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
	setTimeout(canvasPlay, 150);
    }

    clickInMeeting();
    function clickInMeeting() {
	let canvas = document.getElementById('show_canvas');
	let imgData = canvas.toDataURL("image/png");
	$.post(meetingLogUrl + 'clock_in_meeting', {
	    photo : imgData
	}, function(json) {
	    if(json.code !== '0000') {
		toastr.error(json.message, '打卡失败');
		setTimeout(clickInMeeting, 1500);
	    }
	    
	    toastr.success('您好，' + json.result.uName, '打卡成功');
	}, 'json');
    }
});