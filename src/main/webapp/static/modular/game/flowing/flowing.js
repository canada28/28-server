/**
 * 账单记录管理初始化
 */
var Flowing = {
    id: "FlowingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Flowing.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'FID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'UID', field: 'uid', visible: true, align: 'center', valign: 'middle'},
            {title: '交易金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
            {title: '交易后余额', field: 'afterMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '交易类型（1=支出 2=收入）', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '业务类型（1=充值 2=提现 3=业务 4=提成 5=分红 6=介绍 7=返水 8=活动）', field: 'businessType', visible: true, align: 'center', valign: 'middle'},
            {title: '业务 I D', field: 'businessid', visible: true, align: 'center', valign: 'middle'},
            {title: '业务详情', field: 'businessInfo', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Flowing.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Flowing.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加账单记录
 */
Flowing.openAddFlowing = function () {
    var index = layer.open({
        type: 2,
        title: '添加账单记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/flowing/flowing_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看账单记录详情
 */
Flowing.openFlowingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账单记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/flowing/flowing_update/' + Flowing.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除账单记录
 */
Flowing.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/flowing/delete", function (data) {
            Feng.success("删除成功!");
            Flowing.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("flowingId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询账单记录列表
 */
Flowing.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Flowing.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Flowing.initColumn();
    var table = new BSTable(Flowing.id, "/flowing/list", defaultColunms);
    table.setPaginationType("client");
    Flowing.table = table.init();
});
