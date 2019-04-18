/**
 * 公告管理管理初始化
 */
var YzNotice = {
    id: "YzNoticeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YzNotice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'NID', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '管理ID', field: 'suid', visible: false, align: 'center', valign: 'middle'},
            {title: '封面', field: 'img', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    if (value == null){
                        return "-";
                    }
                    return "<img src=\""+value+"\" style=\"height: 35px ;width: 35px\" >";
                }
            },
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'indx', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 1:
                            return '<span style="color: black">最新公告</span>';
                        case 2:
                            return '<span style="color: black">最新公告</span>';
                        case 3:
                            return '<span style="color: black">会员必读</span>';
                        case 4:
                            return '<span style="color: black">玩法介绍</span>';
                        case 5:
                            return '<span style="color: black">赔率说明</span>';
                        case 6:
                            return '<span style="color: black">玩法规则</span>';
                        case 7:
                            return '<span style="color: black">banner图</span>';
                        default:
                            return '<span style="color: black">未知</span>';
                    }
                }

            },
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 1:
                            return '<span style="color: rebeccapurple">已发布</span>';
                        default:
                            return '<span style="color: blue">未发布</span>';
                    }
                }
            },
            {title: '作者', field: 'zuthor', visible: false, align: 'center', valign: 'middle'},
            {title: '定时', field: 'timing', visible: false, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 1:
                            return '<span style="color: rebeccapurple">即时</span>';
                        default:
                            return '<span style="color: blue">定时</span>';
                    }
                }
            },
            {title: '上架时间', field: 'staTime', visible: true, align: 'center', valign: 'middle'},
            {title: '下架时间', field: 'endTime', visible: false, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YzNotice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YzNotice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公告管理
 */
YzNotice.openAddYzNotice = function () {
    var index = layer.open({
        type: 2,
        title: '添加公告管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yzNotice/yzNotice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公告管理详情
 */
YzNotice.openYzNoticeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公告管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yzNotice/yzNotice_update/' + YzNotice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公告管理
 */
YzNotice.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/yzNotice/delete", function (data) {
            Feng.success("删除成功!");
            YzNotice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("yzNoticeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公告管理列表
 */
YzNotice.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    YzNotice.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = YzNotice.initColumn();
    var table = new BSTable(YzNotice.id, "/yzNotice/list", defaultColunms);
    table.setPaginationType("client");
    YzNotice.table = table.init();
});
