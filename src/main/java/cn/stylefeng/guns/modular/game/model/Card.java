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
 * 提现卡包
 * </p>
 *
 * @author canrom7
 * @since 2019-04-08
 */
@TableName("yz_card")
public class Card extends Model<Card> {

    private static final long serialVersionUID = 1L;

    /**
     * CID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * UID
     */
    private Integer uid;
    /**
     * 账户类型（1=支付宝  2=银行卡）
     */
    private Integer type;
    /**
     * 提现账号
     */
    private String account;
    /**
     * 银行品牌
     */
    private String bank;
    /**
     * 状态（1=正常 2=删除）
     */
    private Integer status;
    /**
     * 支行名称
     */
    @TableField("bank_name")
    private String bankName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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
        return "Card{" +
        ", id=" + id +
        ", uid=" + uid +
        ", type=" + type +
        ", status=" + status +
        ", account=" + account +
        ", bank=" + bank +
        ", bankName=" + bankName +
        ", createTime=" + createTime +
        "}";
    }
}
