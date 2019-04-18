/**
 * 用户卡包管理初始化
 */
var Card = {
    id: "CardTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Card.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '记录ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'UID', field: 'uid', visible: true, align: 'center', valign: 'middle'},
            {title: '账户类型', field: 'type', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 1:
                            return '<span style="color: rebeccapurple">支付宝</span>';
                        default:
                            return '<span style="color: blue">银行卡</span>';
                    }
                }
            },
            {title: '提现账号', field: 'account', visible: true, align: 'center', valign: 'middle'},
            {title: '银行品牌', field: 'bank', visible: true, align: 'center', valign: 'middle'},
            {title: '支行名称', field: 'bankName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Card.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Card.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户卡包
 */
Card.openAddCard = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户卡包',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/card/card_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户卡包详情
 */
Card.openCardDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户卡包详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/card/card_update/' + Card.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户卡包
 */
Card.delete = function () {
    if (this.check()) {
        var operation = function () {
        var ajax = new $ax(Feng.ctxPath + "/card/delete", function (data) {
            Feng.success("删除成功!");
            Card.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cardId",Card.seItem.id);
        ajax.start();
        }
        Feng.confirm("确定删除该卡片?", operation);

    }
};

/**
 * 查询用户卡包列表
 */
Card.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Card.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Card.initColumn();
    var table = new BSTable(Card.id, "/card/list", defaultColunms);
    table.setPaginationType("client");
    Card.table = table.init();
});
