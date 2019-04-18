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
 * 用户流水表
 * </p>
 *
 * @author canrom7
 * @since 2019-04-08
 */
@TableName("yz_flowing")
public class Flowing extends Model<Flowing> {

    private static final long serialVersionUID = 1L;

    /**
     * FID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * UID
     */
    private Integer uid;
    /**
     * 交易金额
     */
    private Float money;
    /**
     * 交易标题
     */
    private String title;
    /**
     * 交易后余额
     */
    @TableField("after_money")
    private Float afterMoney;
    /**
     * 交易类型（1=支出 2=收入）
     */
    private Integer type;
    /**
     * 业务类型（1=充值 2=提现 3=业务 4=提成 5=分红 6=介绍 7=返水 8=活动）
     */
    @TableField("business_type")
    private Integer businessType;
    /**
     * 业务 I D
     */
    private String businessid;
    /**
     * 业务详情
     */
    @TableField("business_info")
    private String businessInfo;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    public Flowing() {
    }

    public Flowing(Integer uid, Float money,String title, Float afterMoney, Integer type, Integer businessType, String businessid, String businessInfo, Date createTime) {
        this.uid = uid;
        this.money = money;
        this.title = title;
        this.afterMoney = afterMoney;
        this.type = type;
        this.businessType = businessType;
        this.businessid = businessid;
        this.businessInfo = businessInfo;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(Float afterMoney) {
        this.afterMoney = afterMoney;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
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
        return "Flowing{" +
        ", id=" + id +
        ", uid=" + uid +
        ", money=" + money +
        ", title=" + title +
        ", afterMoney=" + afterMoney +
        ", type=" + type +
        ", businessType=" + businessType +
        ", businessid=" + businessid +
        ", businessInfo=" + businessInfo +
        ", createTime=" + createTime +
        "}";
    }
}
