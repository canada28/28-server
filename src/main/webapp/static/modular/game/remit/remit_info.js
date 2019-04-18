/**
 * 初始化下分管理详情对话框
 */
var RemitInfoDlg = {
    remitInfoData : {}
};

/**
 * 清除数据
 */
RemitInfoDlg.clearData = function() {
    this.remitInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RemitInfoDlg.set = function(key, val) {
    this.remitInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RemitInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RemitInfoDlg.close = function() {
    parent.layer.close(window.parent.Remit.layerIndex);
}

/**
 * 收集数据
 */
RemitInfoDlg.collectData = function() {
    this
    .set('id')
    .set('uid')
    .set('cid')
    .set('money')
    .set('status')
    .set('sysid')
    .set('createTime')
    .set('letTime');
}

/**
 * 提交添加
 */
RemitInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/remit/add", function(data){
        Feng.success("添加成功!");
        window.parent.Remit.table.refresh();
        RemitInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.remitInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RemitInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/remit/update", function(data){
        Feng.success("修改成功!");
        window.parent.Remit.table.refresh();
        RemitInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.remitInfoData);
    ajax.start();
}

$(function() {

});
