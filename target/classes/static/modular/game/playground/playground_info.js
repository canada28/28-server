/**
 * 初始化房间管理详情对话框
 */
var PlaygroundInfoDlg = {
    playgroundInfoData : {}
};

/**
 * 清除数据
 */
PlaygroundInfoDlg.clearData = function() {
    this.playgroundInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlaygroundInfoDlg.set = function(key, val) {
    this.playgroundInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlaygroundInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PlaygroundInfoDlg.close = function() {
    parent.layer.close(window.parent.Playground.layerIndex);
}

/**
 * 收集数据
 */
PlaygroundInfoDlg.collectData = function() {
    this
    .set('id')
    .set('gid')
    .set('title')
    .set('oddsExplain')
    .set('gameRule')
    .set('status')
    .set('createTime');
}

/**
 * 提交添加
 */
PlaygroundInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/playground/add", function(data){
        Feng.success("添加成功!");
        window.parent.Playground.table.refresh();
        PlaygroundInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playgroundInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PlaygroundInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/playground/update", function(data){
        Feng.success("修改成功!");
        window.parent.Playground.table.refresh();
        PlaygroundInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playgroundInfoData);
    ajax.start();
}

$(function() {

});
