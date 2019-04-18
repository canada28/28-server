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
 * 游戏表
 * </p>
 *
 * @author canrom7
 * @since 2019-04-09
 */
@TableName("yz_game")
public class Game extends Model<Game> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 游戏名
     */
    private String title;
    /**
     * 游戏描述
     */
    private String descr;
    /**
     * 图标
     */
    private String icon;
    /**
     * 玩法介绍
     */
    private Integer Introduction;
    /**
     * 每期间隔
     */
    private Integer interval;
    /**
     * 开奖前封盘时间（毫秒）
     */
    @TableField("befor_time")
    private Integer beforTime;
    /**
     * 开奖后封盘时间（毫秒）
     */
    @TableField("after_time")
    private Integer afterTime;
    /**
     * 接口地址
     */
    private String url;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态（1=正常 2=维护）
     */
    private Integer status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(Integer Introduction) {
        this.Introduction = Introduction;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getBeforTime() {
        return beforTime;
    }

    public void setBeforTime(Integer beforTime) {
        this.beforTime = beforTime;
    }

    public Integer getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Integer afterTime) {
        this.afterTime = afterTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "Game{" +
        ", id=" + id +
        ", title=" + title +
        ", descr=" + descr +
        ", icon=" + icon +
        ", Introduction=" + Introduction +
        ", interval=" + interval +
        ", beforTime=" + beforTime +
        ", afterTime=" + afterTime +
        ", url=" + url +
        ", sort=" + sort +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
