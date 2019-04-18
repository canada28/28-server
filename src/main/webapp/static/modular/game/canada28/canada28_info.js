/**
 * 初始化Canada28数据详情对话框
 */
var Canada28InfoDlg = {
    canada28InfoData : {}
};

/**
 * 清除数据
 */
Canada28InfoDlg.clearData = function() {
    this.canada28InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Canada28InfoDlg.set = function(key, val) {
    this.canada28InfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Canada28InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Canada28InfoDlg.close = function() {
    parent.layer.close(window.parent.Canada28.layerIndex);
}

/**
 * 收集数据
 */
Canada28InfoDlg.collectData = function() {
    this
    .set('id')
    .set('period')
    .set('dateStr')
    .set('timeStr')
    .set('chTime')
    .set('numbers')
    .set('one')
    .set('two')
    .set('three')
    .set('result')
    .set('oddEven')
    .set('createTime');
}

/**
 * 提交添加
 */
Canada28InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/canada28/add", function(data){
        Feng.success("添加成功!");
        window.parent.Canada28.table.refresh();
        Canada28InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.canada28InfoData);
    ajax.start();
}

/**
 * 收集游戏修改数据
 */
Canada28InfoDlg.collectaData = function() {
    this
        .set('idstr')
        .set('avatar')
        .set('title')
        .set('descr')
        .set('sort')
        .set('interval')
        .set('beforTime')
        .set('afterTime')
        .set('url');
}

/**
 * 提交游戏修改
 */
Canada28InfoDlg.editaSubmit = function(id) {
    this.clearData();
    this.collectaData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gameset/setGame", function(data){
        Feng.success("修改成功!");
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.canada28InfoData);
    ajax.start();
}


$(function () {

    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});

/**
 * 提交修改
 */
Canada28InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/canada28/update", function(data){
        Feng.success("修改成功!");
        window.parent.Canada28.table.refresh();
        Canada28InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.canada28InfoData);
    ajax.start();
}

$(function() {

});
