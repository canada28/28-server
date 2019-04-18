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
 * 提现下分表
 * </p>
 *
 * @author canrom7
 * @since 2019-04-08
 */
@TableName("yz_remit")
public class Remit extends Model<Remit> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * UID
     */
    private Integer uid;
    /**
     * 卡包ID
     */
    private Integer cid;
    /**
     * 提现金额
     */
    private Float money;
    /**
     * 状态（1=待打款  2=已经打款 3=异常单）
     */
    private Integer status;
    /**
     * 审核人
     */
    private Integer sysid;
    /**
     * 申请时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 审核时间
     */
    @TableField("let_time")
    private Date letTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSysid() {
        return sysid;
    }

    public void setSysid(Integer sysid) {
        this.sysid = sysid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLetTime() {
        return letTime;
    }

    public void setLetTime(Date letTime) {
        this.letTime = letTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Remit{" +
        ", id=" + id +
        ", uid=" + uid +
        ", cid=" + cid +
        ", money=" + money +
        ", status=" + status +
        ", sysid=" + sysid +
        ", createTime=" + createTime +
        ", letTime=" + letTime +
        "}";
    }
}
