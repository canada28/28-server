/**
 * 房间管理管理初始化
 */
var Playground = {
    id: "PlaygroundTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Playground.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '游戏', field: 'gid', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 1:
                            return '<span style="color: black">加拿大28</span>';
                        case 2:
                            return '<span style="color: black">北京28</span>';
                        default:
                            return '<span style="color: black">未知</span>';
                    }
                }
                },
            {title: '房间标题', field: 'title', visible: false, align: 'center', valign: 'middle'},
            {title: '赔率说明', field: 'oddsExplain', visible: false, align: 'center', valign: 'middle'},
            {title: '游戏规则', field: 'gameRule', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    switch (value) {
                        case 1:
                            return '<span style="color: blue">正常</span>';
                        default:
                            return '<span style="color: red">维护</span>';
                    }
                }

            },
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作', field: 'id', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                if (row.status == 1) {
                    return '<a href="#" onclick="Playground.repairPlayground(' + value + ',1)">关闭房间</a>';
                }
                return '<a href="#" onclick="Playground.repairPlayground(' + value + ',2)">开启房间</a>';
            }
        }
    ];
};


/**
 *  房间维护
 * @param id
 */
Playground.repairPlayground= function (pid,status) {
    var operation = function () {
        var ajax = new $ax(Feng.ctxPath + "/playground/changePlaygroundStatus", function (data) {
            if (data.code === 200) {
                Feng.success("操作成功!");
            } else {
                Feng.error("操作失败!" + data.message + "!");
            }
            Playground.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.set("pid", pid);
        ajax.set("status", status);
        ajax.start();
    }
    if (status==1){
        Feng.confirm("确定关闭 "+pid+" 房间 ?", operation);
    } else {
        Feng.confirm("确定开启 "+pid+" 房间 ?", operation);
    }


}

/**
 * 检查是否选中
 */
Playground.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Playground.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加房间管理
 */
Playground.openAddPlayground = function () {
    var index = layer.open({
        type: 2,
        title: '添加房间管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/playground/playground_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看房间管理详情
 */
Playground.openPlaygroundDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '房间管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/playground/playground_update/' + Playground.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除房间管理
 */
Playground.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/playground/delete", function (data) {
            Feng.success("删除成功!");
            Playground.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("playgroundId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询房间管理列表
 */
Playground.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Playground.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Playground.initColumn();
    var table = new BSTable(Playground.id, "/playground/list", defaultColunms);
    table.setPaginationType("client");
    Playground.table = table.init();
});
