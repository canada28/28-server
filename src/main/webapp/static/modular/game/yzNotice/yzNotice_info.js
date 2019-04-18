/**
 * 初始化公告管理详情对话框
 */
var YzNoticeInfoDlg = {
    yzNoticeInfoData : {}
};

/**
 * 清除数据
 */
YzNoticeInfoDlg.clearData = function() {
    this.yzNoticeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YzNoticeInfoDlg.set = function(key, val) {
    this.yzNoticeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YzNoticeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YzNoticeInfoDlg.close = function() {
    parent.layer.close(window.parent.YzNotice.layerIndex);
}

/**
 * 收集数据
 */
YzNoticeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('suid')
    .set('img')
    .set('title')
    .set('content')
    .set('indx')
    .set('type')
    .set('status')
    .set('zuthor')
    .set('timing')
    .set('staTime')
    .set('endTime')
    .set('updateTime')
    .set('createTime')
    .set('remark');
}

/**
 * 提交添加
 */
YzNoticeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yzNotice/add", function(data){
        Feng.success("添加成功!");
        window.parent.YzNotice.table.refresh();
        YzNoticeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yzNoticeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YzNoticeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yzNotice/update", function(data){
        Feng.success("修改成功!");
        window.parent.YzNotice.table.refresh();
        YzNoticeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yzNoticeInfoData);
    ajax.start();
}

$(function() {

});
