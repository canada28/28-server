/**
 * 初始化下注管理详情对话框
 */
var StakesInfoDlg = {
    stakesInfoData : {}
};

/**
 * 清除数据
 */
StakesInfoDlg.clearData = function() {
    this.stakesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StakesInfoDlg.set = function(key, val) {
    this.stakesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StakesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StakesInfoDlg.close = function() {
    parent.layer.close(window.parent.Stakes.layerIndex);
}

/**
 * 收集数据
 */
StakesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pid')
    .set('issue')
    .set('rid')
    .set('uid')
    .set('ruid')
    .set('money')
    .set('status')
    .set('createTime');
}

/**
 * 提交添加
 */
StakesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stakes/add", function(data){
        Feng.success("添加成功!");
        window.parent.Stakes.table.refresh();
        StakesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stakesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StakesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stakes/update", function(data){
        Feng.success("修改成功!");
        window.parent.Stakes.table.refresh();
        StakesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stakesInfoData);
    ajax.start();
}

$(function() {

});
