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
 * 下注表
 * </p>
 *
 * @author canrom7
 * @since 2019-04-11
 */
@TableName("yz_stakes")
public class Stakes extends Model<Stakes> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 游戏ID
     */
    private Integer gid;
    /**
     * 期号ID
     */
    private Long issue;
    /**
     * 房间ID
     */
    private Integer rid;
    /**
     * 玩家ID
     */
    private Integer uid;
    /**
     * 下注规则
     */
    private Integer ruid;
    /**
     * 下注金额
     */
    private Integer money;
    /**
     * 中奖金额
     */
    private Integer winning;
    /**
     * 盈利金额
     */
    private Integer profit;

    /**
     * 状态（1=已中奖 2=未中奖 3=待开奖 4=已撤单）
     */
    private Integer status;
    /**
     * 下注时间
     */
    @TableField("create_time")
    private Date createTime;

    public Stakes() {
    }

    public Stakes(Integer gid, Long issue, Integer rid, Integer uid, Integer ruid, Integer money, Date createTime) {
        this.gid = gid;
        this.issue = issue;
        this.rid = rid;
        this.uid = uid;
        this.ruid = ruid;
        this.money = money;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Long getIssue() {
        return issue;
    }

    public void setIssue(Long issue) {
        this.issue = issue;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getWinning() {
        return winning;
    }

    public void setWinning(Integer winning) {
        this.winning = winning;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "Stakes{" +
                ", id=" + id +
                ", gid=" + gid +
                ", issue=" + issue +
                ", rid=" + rid +
                ", uid=" + uid +
                ", ruid=" + ruid +
                ", money=" + money +
                ", winning=" + winning +
                ", profit=" + profit +
                ", status=" + status +
                ", createTime=" + createTime +
                "}";
    }
}
