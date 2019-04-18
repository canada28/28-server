package cn.stylefeng.guns.modular.game.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author canrom7
 * @since 2019-04-07
 */
@TableName("yz_notice")
public class YzNotice extends Model<YzNotice> {

    private static final long serialVersionUID = 1L;

    /**
     * NID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 管理ID
     */
    private Integer suid;
    /**
     * 封面
     */
    private String img;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 位置
     */
    private Integer indx;
    /**
     * 类型（1=系统公告&横滚文 2=最新公告 3=会员必读  4=玩法介绍 5=赔率说明 6=玩法规则  7=banner图通知）
     */
    private Integer type;
    /**
     * 状态（1=发布 2=待发布）
     */
    private Integer status;
    /**
     * 作者
     */
    private String zuthor;
    /**
     * 定时（1=即时 2=定时）
     */
    private Integer timing;
    /**
     * 上架时间
     */
    @TableField("sta_time")
    private Date staTime;
    /**
     * 下架时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIndx() {
        return indx;
    }

    public void setIndx(Integer indx) {
        this.indx = indx;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getZuthor() {
        return zuthor;
    }

    public void setZuthor(String zuthor) {
        this.zuthor = zuthor;
    }

    public Integer getTiming() {
        return timing;
    }

    public void setTiming(Integer timing) {
        this.timing = timing;
    }

    public Date getStaTime() {
        return staTime;
    }

    public void setStaTime(Date staTime) {
        this.staTime = staTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "YzNotice{" +
        ", id=" + id +
        ", suid=" + suid +
        ", img=" + img +
        ", title=" + title +
        ", content=" + content +
        ", indx=" + indx +
        ", type=" + type +
        ", status=" + status +
        ", zuthor=" + zuthor +
        ", timing=" + timing +
        ", staTime=" + staTime +
        ", endTime=" + endTime +
        ", updateTime=" + updateTime +
        ", createTime=" + createTime +
        ", remark=" + remark +
        "}";
    }
}
