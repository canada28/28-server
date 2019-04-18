/**
 * 初始化用户管理详情对话框
 */
var YzUserInfoDlg = {
    yzUserInfoData : {}
};

/**
 * 清除数据
 */
YzUserInfoDlg.clearData = function() {
    this.yzUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YzUserInfoDlg.set = function(key, val) {
    this.yzUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YzUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YzUserInfoDlg.close = function() {
    parent.layer.close(window.parent.YzUser.layerIndex);
}

/**
 * 收集数据
 */
YzUserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('token')
    .set('phone')
    .set('email')
    .set('icon')
    .set('nickName')
    .set('qq')
    .set('name')
    .set('password')
    .set('foundPassword')
    .set('msgCode')
    .set('lock')
    .set('parent')
    .set('assets')
    .set('frozenAssets')
    .set('status')
    .set('loginIp')
    .set('loginTime')
    .set('createTime');
}

/**
 * 提交添加
 */
YzUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yzUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.YzUser.table.refresh();
        YzUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yzUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YzUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yzUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.YzUser.table.refresh();
        YzUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yzUserInfoData);
    ajax.start();
}

$(function() {

});
