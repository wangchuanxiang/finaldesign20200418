/**
 * 
 */
$(function() {
    var $meetingId = null;
    var meetingUrl = "http://localhost:8888/meeting/";

    $('#modal_create_meeting').on('show.bs.modal', function() {
	$('#modal_create_meeting').find('.meeting_name').val('');
	$('#modal_create_meeting').find('.begin_time').val('');
	$('#modal_create_meeting').find('.end_time').val('');
	$('#modal_create_meeting').find('.comment').val('');
    });

    initPageContent();
    function initPageContent() {
	$("#modal_create_meeting .begin_time").datetimepicker({
	    format : 'yyyy-mm-dd mm:hh:ss',
	    language : 'zh-CN',
	    autoclose : true
	}).on('show', function(event) {
	    event.preventDefault();
	    event.stopPropagation();
	});
	$("#modal_create_meeting .end_time").datetimepicker({
	    format : 'yyyy-mm-dd mm:hh:ss',
	    language : 'zh-CN',
	    autoclose : true
	}).on('show', function(event) {
	    event.preventDefault();
	    event.stopPropagation();
	});

	$("#modal_update_meeting .begin_time").datetimepicker({
	    format : 'yyyy-mm-dd mm:hh:ss',
	    language : 'zh-CN',
	    autoclose : true
	}).on('show', function(event) {
	    event.preventDefault();
	    event.stopPropagation();
	});
	$("#modal_update_meeting .end_time").datetimepicker({
	    format : 'yyyy-mm-dd mm:hh:ss',
	    language : 'zh-CN',
	    autoclose : true
	}).on('show', function(event) {
	    event.preventDefault();
	    event.stopPropagation();
	});
    }

    $('#delete_meeting').on('click', function() {
	$.post(meetingUrl + 'delete_one_meeting', {
	    meetingId : $meetingId
	}, function(json) {
	    if (json.code !== '0000') {
		toastr.error(json.message, '删除会议失败');
		return;
	    }

	    $('#modal_delete_meeting').modal('hide');
	    $('#all_meeting_table').bootstrapTable('refresh');
	}, 'json');
    });

    $('#create_meeting').on('click', function() {
	let beginTime = $('#modal_create_meeting .begin_time').val().trim();
	let endTime = $('#modal_create_meeting .end_time').val().trim();

	beginTime = Date.parse(beginTime);
	endTime = Date.parse(endTime);
	$.post(meetingUrl + 'create_one_meeting', {
	    mName : $('#modal_create_meeting .meeting_name').val().trim(),
	    beginTime : beginTime,
	    endTime : endTime,
	    comment : $('#modal_create_meeting .comment').val().trim()
	}, function(json) {
	    if (json.code !== '0000') {
		toastr.error(json.message, '添加会议失败');
		return;
	    }

	    $('#modal_create_meeting').modal('hide');
	    $('#all_meeting_table').bootstrapTable('refresh');
	}, 'json');
    });

    $('#update_meeting').on('click', function() {
	let beginTime = $('#modal_update_meeting .begin_time').val().trim();
	let endTime = $('#modal_update_meeting .end_time').val().trim();

	beginTime = Date.parse(beginTime);
	endTime = Date.parse(endTime);
	$.post(meetingUrl + 'update_one_meeting', {
	    meetingId : $meetingId,
	    mName : $('#modal_update_meeting .meeting_name').val().trim(),
	    beginTime : beginTime,
	    endTime : endTime,
	    comment : $('#modal_update_meeting .comment').val().trim()
	}, function(json) {
	    if (json.code !== '0000') {
		toastr.error(json.message, '更新会议失败');
		return;
	    }

	    $('#modal_update_meeting').modal('hide');
	    $('#all_meeting_table').bootstrapTable('refresh');
	}, 'json');
    });

    initMeetingTable();
    function initMeetingTable() {
	$('#all_meeting_table').bootstrapTable('destroy').bootstrapTable({
	    url : meetingUrl + 'query_all_meeting', // 请求后台的URL（*）
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
		field : 'mName',
		title : '会议名称',
	    }, {
		field : 'beginTime',
		title : '开始时间',
		formatter : function(value, row, index) {
		    return timeStampString(new Date(row.beginTime));
		}
	    }, {
		field : 'endTime',
		title : '结束时间',
		formatter : function(value, row, index) {
		    return timeStampString(new Date(row.endTime));
		}
	    }, {
		field : 'comment',
		title : '会议备注',
	    }, {
		field : 'op',
		title : '操作',
		formatter : function(value, row, index) {
		    return $('#hidden').find('.op').html();
		},
		events : {
		    "click .glyphicon-edit" : function(e, value, row, index) {
			$meetingId = row.id;
			$('#modal_update_meeting .meeting_name').val(row.mName);
			$('#modal_update_meeting .begin_time').val(timeStampString(new Date(row.beginTime)));
			$('#modal_update_meeting .end_time').val(timeStampString(new Date(row.endTime)));
			$('#modal_update_meeting .comment').val(row.comment);
			$('#modal_update_meeting').modal('show');
		    },
		    "click .glyphicon-trash" : function(e, value, row, index) {
			$meetingId = row.id;
			$('#modal_delete_meeting').modal('show');
		    }
		}
	    } ]
	});
    }
});