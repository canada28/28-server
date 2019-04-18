/**
 * 下注管理管理初始化
 */
var Stakes = {
    id: "StakesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Stakes.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '游戏ID', field: 'pid', visible: true, align: 'center', valign: 'middle'},
            {title: '期号ID', field: 'issue', visible: true, align: 'center', valign: 'middle'},
            {title: '房间ID', field: 'rid', visible: true, align: 'center', valign: 'middle'},
            {title: '玩家ID', field: 'uid', visible: true, align: 'center', valign: 'middle'},
            {title: '下注规则', field: 'ruid', visible: true, align: 'center', valign: 'middle'},
            {title: '下注金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
            {title: '状态（1=已中奖 2=未中奖 3=待开奖 4=已撤单）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '下注时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Stakes.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Stakes.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加下注管理
 */
Stakes.openAddStakes = function () {
    var index = layer.open({
        type: 2,
        title: '添加下注管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/stakes/stakes_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看下注管理详情
 */
Stakes.openStakesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '下注管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/stakes/stakes_update/' + Stakes.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除下注管理
 */
Stakes.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/stakes/delete", function (data) {
            Feng.success("删除成功!");
            Stakes.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("stakesId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询下注管理列表
 */
Stakes.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Stakes.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Stakes.initColumn();
    var table = new BSTable(Stakes.id, "/stakes/list", defaultColunms);
    table.setPaginationType("client");
    Stakes.table = table.init();
});
