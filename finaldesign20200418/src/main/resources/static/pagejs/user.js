/**
 * 
 */
$(function() {
    $('#modal_create_user').on('show.bs.modal', function() {
	initCreateUserModel();
    });

    initUserTable();
    $('#create_user_photo').on('change', function() {
	var file = $('#create_user_photo')[0].files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
	    var img = $('#show_img')[0];
	    img.src = e.target.result;
	}
	reader.readAsDataURL(file);
	
	$('#show_img').removeClass('hidden');
	$('#show_canvas').addClass('hidden');
    });

    function initCreateUserModel() {
	var video = document.getElementById("video");
	let promise = navigator.mediaDevices.getUserMedia({
	    video : {
		width : 200,
		height : 200
	    },
	    audio : true
	});
	promise.then(function(MediaStream) {
	    video.srcObject = MediaStream;
	    video.play();
	    canvasPlay();
	});
	var canvas = document.getElementById('show_canvas');
	var ctx = canvas.getContext('2d');
	function canvasPlay() {
	    ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
	    setTimeout(canvasPlay, 150);
	}
    }

    function initUserTable() {
	$('#all_user_table').bootstrapTable('destroy').bootstrapTable({
	    url : '/B_Product/GetProductData', // 请求后台的URL（*）
	    method : 'get', // 请求方式（*）
	    toolbar : '#toolbar', // 工具按钮用哪个容器
	    striped : true, // 是否显示行间隔色
	    cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    pagination : true, // 是否显示分页（*）
	    sortable : false, // 是否启用排序
	    sortOrder : "asc", // 排序方式
	    // 传递参数（*）
	    sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
	    pageNumber : 1, // 初始化加载第一页，默认第一页
	    pageSize : 10, // 每页的记录行数（*）
	    pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
	    search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    strictSearch : false,
	    showColumns : false, // 是否显示所有的列
	    showRefresh : false, // 是否显示刷新按钮
	    minimumCountColumns : 2, // 最少允许的列数
	    clickToSelect : true, // 是否启用点击选中行
	    // uniqueId : "Id", // 每一行的唯一标识，一般为主键列
	    showToggle : false, // 是否显示详细视图和列表视图的切换按钮
	    cardView : false, // 是否显示详细视图
	    detailView : false, // 是否显示父子表
	    showExport : false, // 是否显示导出按钮
	    buttonsAlign : "right", // 按钮位置
	    exportTypes : [ 'excel' ], // 导出文件类型
	    Icons : 'glyphicon-export',
	    queryParams : function(params) {
		return {};
	    },
	    columns : [ {
		field : 'seq',
		title : '序列',
	    }, {
		field : 'name',
		title : '姓名',
	    }, {
		field : 'age',
		title : '年龄',
	    }, {
		field : 'photo',
		title : '图片',
	    }, {
		field : 'op',
		title : '操作',
	    } ]
	});
    }
});