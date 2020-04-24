/**
 * 
 */
$(function() {
    var $userId = null;
    var sUserUrl = "http://localhost:8888/s_user/";

    $('#modal_create_user').on('show.bs.modal', function() {
	$('#modal_create_user').find('.s_name').val('');
	$('#modal_create_user').find('.s_tel').val('');
	$('#create_user_show_img').addClass('hidden');
	$('#create_user_show_canvas').removeClass('hidden');
	initCreateUserModel();
    });

    $('#modal_update_user').on('show.bs.modal', function() {
	$('#update_user_show_img').addClass('hidden');
	$('#update_user_show_canvas').removeClass('hidden');
	initUpdateUserModel();
    });

    $('#create_user_photo').on('change', function() {
	var file = $('#create_user_photo')[0].files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
	    var img = $('#create_user_show_img')[0];
	    img.src = e.target.result;
	}
	reader.readAsDataURL(file);

	$('#create_user_show_img').removeClass('hidden');
	$('#create_user_show_canvas').addClass('hidden');
    });

    $('#update_user_photo').on('change', function() {
	var file = $('#update_user_photo')[0].files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
	    var img = $('#update_user_show_img')[0];
	    img.src = e.target.result;
	}
	reader.readAsDataURL(file);

	$('#update_user_show_img').removeClass('hidden');
	$('#update_user_show_canvas').addClass('hidden');
    });
    
    $('#delete_user').on('click', function() {
	$.post(sUserUrl + 'delete_one_suser', {
	    sUserId : $userId
	}, function(json) {
	    if (json.code !== '0000') {
		toastr.error(json.message, '删除用户失败');
		return;
	    }

	    $('#modal_delete_user').modal('hide');
	    $("#all_user_table").bootstrapTable('refresh');
	}, 'json');
    });

    $('#create_user').on('click', function() {
	var imgData = '';
	if (!$("#create_user_show_img").hasClass('hidden')) {
	    imgData = $('#create_user_show_img').prop('src');
	} else if (!$('#create_user_show_canvas').hasClass('hidden')) {
	    let canvas = document.getElementById('create_user_show_canvas');
	    canvas.mozImageSmoothingEnabled = false;
	    canvas.webkitImageSmoothingEnabled = false;
	    canvas.msImageSmoothingEnabled = false;
	    canvas.imageSmoothingEnabled = false;
	    imgData = canvas.toDataURL("image/png", 1);
	}

	$.post(sUserUrl + 'create_one_suser', {
	    uName : $('#modal_create_user .s_name').val(),
	    telephone : $('#modal_create_user .s_tel').val(),
	    photo : imgData
	}, function(json) {
	    if (json.code !== '0000') {
		toastr.error(json.message, '添加用户失败');
		return;
	    }

	    $('#modal_create_user').modal('hide');
	    $("#all_user_table").bootstrapTable('refresh');
	}, 'json');
    });

    $('#update_user').on('click', function() {
	var imgData = '';
	if (!$("#update_user_show_img").hasClass('hidden')) {
	    imgData = $('#update_user_show_img').prop('src');
	} else if (!$('#update_user_show_canvas').hasClass('hidden')) {
	    let canvas = document.getElementById('update_user_show_canvas');
	    canvas.mozImageSmoothingEnabled = false;
	    canvas.webkitImageSmoothingEnabled = false;
	    canvas.msImageSmoothingEnabled = false;
	    canvas.imageSmoothingEnabled = false;
	    imgData = canvas.toDataURL("image/png", 1);
	}

	$.post(sUserUrl + 'update_one_suser', {
	    sUserId : $userId,
	    uName : $('#modal_update_user .s_name').val(),
	    telephone : $('#modal_update_user .s_tel').val(),
	    photo : imgData
	}, function(json) {
	    if (json.code !== '0000') {
		toastr.error(json.message, '更新用户失败');
		return;
	    }

	    $('#modal_update_user').modal('hide');
	    $("#all_user_table").bootstrapTable('refresh');
	}, 'json');
    });

    function initCreateUserModel() {
	var video = document.getElementById("create_user_video");
	let promise = navigator.mediaDevices.getUserMedia({
	    video : {
		width : 150,
		height : 150
	    },
	    audio : true
	});
	promise.then(function(MediaStream) {
	    video.srcObject = MediaStream;
	    video.play();
	    canvasPlay();
	});
	var canvas = document.getElementById('create_user_show_canvas');
	var ctx = canvas.getContext('2d');
	function canvasPlay() {
	    ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
	    setTimeout(canvasPlay, 150);
	}
    }

    function initUpdateUserModel() {
	var video = document.getElementById("update_user_video");
	let promise = navigator.mediaDevices.getUserMedia({
	    video : {
		width : 150,
		height : 150
	    },
	    audio : true
	});
	promise.then(function(MediaStream) {
	    video.srcObject = MediaStream;
	    video.play();
	    canvasPlay();
	});
	var canvas = document.getElementById('update_user_show_canvas');
	var ctx = canvas.getContext('2d');
	function canvasPlay() {
	    ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
	    setTimeout(canvasPlay, 150);
	}
    }

    initUserTable();
    function initUserTable() {
	$('#all_user_table').bootstrapTable('destroy').bootstrapTable({
	    url : sUserUrl + 'query_all_suser', // 请求后台的URL（*）
	    method : 'post', // 请求方式（*）
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
	    responseHandler : function(res) { // res 后台给你返回的数据
		return {
		    rows : res.result,
		// total : res.total
		}
	    },
	    columns : [ {
		field : 'id',
		title : '序列',
	    }, {
		field : 'createTime',
		title : '创建时间',
		formatter : function(value, row, index) {
		    return timeStampString(new Date(row.createTime));
		}
	    }, {
		field : 'uName',
		title : '用户姓名',
	    }, {
		field : 'uTel',
		title : '联系方式',
	    }, {
		field : 'uPhoto',
		title : '图片',
		formatter : function(value, row, index) {
		    return '<img style="width: 60px;height: 60px;" src="' + row.uPhoto + '">';
		},
	    }, {
		field : 'op',
		title : '操作',
		formatter : function(value, row, index) {
		    return $('#hidden').find('.op').html();
		},
		events : {
		    "click .glyphicon-edit" : function(e, value, row, index) {
			$userId = row.id;
			$('#modal_update_user').find('.s_name').val(row.uName);
			$('#modal_update_user').find('.s_tel').val(row.uTel);
			$('#modal_update_user').modal('show');
		    },
		    "click .glyphicon-trash" : function(e, value, row, index) {
			$userId = row.id;
			$('#modal_delete_user').modal('show');
		    }
		}
	    } ]
	});
    }
});