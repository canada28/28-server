package cn.stylefeng.guns.modular.game.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author canrom7
 * @since 2019-04-04
 */
@TableName("yz_user")
public class YzUser extends Model<YzUser> {

    private static final long serialVersionUID = 1L;

    /**
     * UID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * Tokem
     */
    private String token;
    /**
     * 手机
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String icon;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * QQ
     */
    private String qq;
    /**
     * 姓名
     */
    private String name;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 资金密码
     */
    @TableField("found_password")
    private String foundPassword;
    /**
     * 短信验码
     */
    @TableField("msg_code")
    private String msgCode;
    /**
     * 设备锁（1=锁定  2=未锁）
     */
    private Integer lock;
    /**
     * 邀请人
     */
    private Integer parent;
    /**
     * 可用资产
     */
    private Float assets;
    /**
     * 冻结资产
     */
    @TableField("frozen_assets")
    private Float frozenAssets;
    /**
     * 状态（1=正常 2=冻结）
     */
    private Integer status;
    /**
     * 预留字段
     */
    private Integer reserved;
     /**
     * 所在游戏场ID
     */
    private Integer roomid;

    /**
     * 登陆IP
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 登陆时间
     */
    @TableField("login_time")
    private Date loginTime;
    /**
     * 注册时间
     */
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoundPassword() {
        return foundPassword;
    }

    public void setFoundPassword(String foundPassword) {
        this.foundPassword = foundPassword;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Float getAssets() {
        return assets;
    }

    public void setAssets(Float assets) {
        this.assets = assets;
    }

    public Float getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(Float frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "YzUser{" +
        ", id=" + id +
        ", token=" + token +
        ", phone=" + phone +
        ", email=" + email +
        ", icon=" + icon +
        ", nickName=" + nickName +
        ", qq=" + qq +
        ", name=" + name +
        ", password=" + password +
        ", foundPassword=" + foundPassword +
        ", msgCode=" + msgCode +
        ", lock=" + lock +
        ", parent=" + parent +
        ", assets=" + assets +
        ", frozenAssets=" + frozenAssets +
        ", status=" + status +
        ", roomid=" + roomid +
        ", reserved=" + reserved +
        ", loginIp=" + loginIp +
        ", loginTime=" + loginTime +
        ", createTime=" + createTime +
        "}";
    }
}
