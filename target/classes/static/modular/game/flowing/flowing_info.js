/**
 * 初始化账单记录详情对话框
 */
var FlowingInfoDlg = {
    flowingInfoData : {}
};

/**
 * 清除数据
 */
FlowingInfoDlg.clearData = function() {
    this.flowingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowingInfoDlg.set = function(key, val) {
    this.flowingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FlowingInfoDlg.close = function() {
    parent.layer.close(window.parent.Flowing.layerIndex);
}

/**
 * 收集数据
 */
FlowingInfoDlg.collectData = function() {
    this
    .set('id')
    .set('uid')
    .set('money')
    .set('afterMoney')
    .set('type')
    .set('businessType')
    .set('businessid')
    .set('businessInfo')
    .set('createTime');
}

/**
 * 提交添加
 */
FlowingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowing/add", function(data){
        Feng.success("添加成功!");
        window.parent.Flowing.table.refresh();
        FlowingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FlowingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowing/update", function(data){
        Feng.success("修改成功!");
        window.parent.Flowing.table.refresh();
        FlowingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowingInfoData);
    ajax.start();
}

$(function() {

});
