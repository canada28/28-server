/**
 * Canada28数据管理初始化
 */
var Canada28 = {
    id: "Canada28Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Canada28.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '期号', field: 'period', visible: true, align: 'center', valign: 'middle'},
            {title: '日期', field: 'dateStr', visible: true, align: 'center', valign: 'middle'},
            {title: '时间', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '中国时间', field: 'chTime', visible: true, align: 'center', valign: 'middle'},
            {title: '开奖号码', field: 'numbers', visible: false, align: 'center', valign: 'middle'},
            {title: '球一结果', field: 'one', visible: true, align: 'center', valign: 'middle'},
            {title: '球二结果', field: 'two', visible: true, align: 'center', valign: 'middle'},
            {title: '球三结果', field: 'three', visible: true, align: 'center', valign: 'middle'},
            {title: '最终结果', field: 'result', visible: true, align: 'center', valign: 'middle'},
            {title: '单双数', field: 'oddEven', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 1:
                            return '<span style="color: chocolate">单数</span>';
                        default:
                            return '<span style="color: blue">双数</span>';
                    }
                }
            },
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Canada28.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Canada28.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加Canada28数据
 */
Canada28.openAddCanada28 = function () {
    var index = layer.open({
        type: 2,
        title: '添加Canada28数据',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/canada28/canada28_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看Canada28数据详情
 */
Canada28.openCanada28Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'Canada28数据详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/canada28/canada28_update/' + Canada28.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除Canada28数据
 */
Canada28.delete = function () {
    if (this.check()) {

        var operation = function () {
        var ajax = new $ax(Feng.ctxPath + "/canada28/delete", function (data) {
            Feng.success("删除成功!");
            Canada28.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("canada28Id",Canada28.seItem.id);
        ajax.start();
        }
        Feng.confirm("确定删除该数据 ?", operation);

    }
};

/**
 * 查询Canada28数据列表
 */
Canada28.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Canada28.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Canada28.initColumn();
    var table = new BSTable(Canada28.id, "/canada28/list", defaultColunms);
    table.setPaginationType("server");
    Canada28.table = table.init();
});

