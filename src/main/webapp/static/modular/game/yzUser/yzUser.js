/**
 * 用户管理管理初始化
 */
var YzUser = {
    id: "YzUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YzUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'UID', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '手机', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {title: '邮箱', field: 'email', visible: false, align: 'center', valign: 'middle'},
        {title: '头像', field: 'icon', visible: false, align: 'center', valign: 'middle'},
        {title: '昵称', field: 'nickName', visible: true, align: 'center', valign: 'middle'},
        {title: 'QQ', field: 'qq', visible: false, align: 'center', valign: 'middle'},
        {title: '姓名', field: 'name', visible: false, align: 'center', valign: 'middle'},
        {title: '短信验码', field: 'msgCode', visible: false, align: 'center', valign: 'middle'},
        {
            title: '设备锁', field: 'lock', visible: false, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                switch (value) {
                    case 1:
                        return '<span style="color: rebeccapurple">锁定</span>';
                    default:
                        return '<span style="color: blue">未锁</span>';
                }
            }
        },
        {title: '邀请人', field: 'parent', visible: false, align: 'center', valign: 'middle'},
        {title: '可用资产', field: 'assets', visible: true, align: 'center', valign: 'middle'},
        {title: '冻结资产', field: 'frozenAssets', visible: true, align: 'center', valign: 'middle'},
        {
            title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                switch (value) {
                    case 1:
                        return '<span style="color: blue">正常</span>';
                    default:
                        return '<span style="color: red">冻结</span>';
                }
            }
        },
        {title: '登陆IP', field: 'loginIp', visible: true, align: 'center', valign: 'middle'},
        {title: '登陆时间', field: 'loginTime', visible: true, align: 'center', valign: 'middle'},
        {title: '注册时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
        {
            title: '操作', field: 'id', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                if (row.status == 1) {
                    return '<a href="#" onclick="YzUser.repairOrder(' + value + ',1)">冻结</a>';
                }
                return '<a href="#" onclick="YzUser.repairOrder(' + value + ',2)">解冻</a>';
            }
        }
    ];
};


/**
 *  封号
 * @param id
 */
YzUser.repairOrder = function (id,status) {
    var operation = function () {
        var ajax = new $ax(Feng.ctxPath + "/yzUser/changeUserStatus", function (data) {
            if (data.code === 200) {
                Feng.success("操作成功!");
            } else {
                Feng.error("操作失败!" + data.message + "!");
            }
            YzUser.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userid", id);
        ajax.set("status", status);
        ajax.start();
    }
    if (status==1){
        Feng.confirm("确定冻结 "+id+" 用户 ?", operation);
    } else {
        Feng.confirm("确定解冻 "+id+" 用户 ?", operation);
    }


}

/**
 * 检查是否选中
 */
YzUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        YzUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户管理
 */
YzUser.openAddYzUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yzUser/yzUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户管理详情
 */
YzUser.openYzUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yzUser/yzUser_update/' + YzUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户管理
 */
YzUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/yzUser/delete", function (data) {
            Feng.success("删除成功!");
            YzUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("yzUserId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户管理列表
 */
YzUser.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    YzUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = YzUser.initColumn();
    var table = new BSTable(YzUser.id, "/yzUser/list", defaultColunms);
    table.setPaginationType("server");
    YzUser.table = table.init();
});
