/**
 * 下分管理管理初始化
 */
var Remit = {
    id: "RemitTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Remit.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: 'UID', field: 'uid', visible: true, align: 'center', valign: 'middle'},
        {
            title: '打款账号', field: 'cid', visible: true, align: 'center', valign: 'middle'
            ,
            formatter: function (value, row, index) {
                return '<a href="#" onclick="Remit.openRemitAccountDetail(' + value + ')">查看</a>';
            }
        },
        {title: '提现金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
        {
            title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                switch (value) {
                    case 1:
                        return '<span style="color: blue">待打款</span>';
                    case 1:
                        return '<span style="color: darkgreen">已经打款</span>';
                    case 1:
                        return '<span style="color: red">即时</span>';
                    default:
                        return '<span style="color: blue">未知</span>';
                }
            }
        },
        {title: '审核人', field: 'sysid', visible: true, align: 'center', valign: 'middle'},
        {title: '申请时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '审核时间', field: 'letTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Remit.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Remit.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加下分管理
 */
Remit.openAddRemit = function () {
    var index = layer.open({
        type: 2,
        title: '添加下分管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/remit/remit_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看下分账号详情
 */
Remit.openRemitAccountDetail = function (aid) {
    var index = layer.open({
        type: 2,
        title: '下分管理详情',
        area: ['875px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/remit/remit_account/' + aid
    });
    this.layerIndex = index;
};



/**
 * 打开查看下分管理详情
 */
Remit.openRemitDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '下分管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/remit/remit_update/' + Remit.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除下分管理
 */
Remit.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/remit/delete", function (data) {
            Feng.success("删除成功!");
            Remit.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("remitId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询下分管理列表
 */
Remit.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Remit.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Remit.initColumn();
    var table = new BSTable(Remit.id, "/remit/list", defaultColunms);
    table.setPaginationType("client");
    Remit.table = table.init();
});
