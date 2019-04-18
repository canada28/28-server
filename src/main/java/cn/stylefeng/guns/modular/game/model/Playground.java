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
 * 游戏场（房间）表
 * </p>
 *
 * @author canrom7
 * @since 2019-04-09
 */
@TableName("yz_playground")
public class Playground extends Model<Playground> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 游戏ID
     */
    private Integer gid;
    /**
     * 房间标题
     */
    private String title;
    /**
     * 赔率说明
     */
    @TableField("odds_explain")
    private Integer oddsExplain;
    /**
     * 游戏规则
     */
    @TableField("game_rule")
    private Integer gameRule;
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

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOddsExplain() {
        return oddsExplain;
    }

    public void setOddsExplain(Integer oddsExplain) {
        this.oddsExplain = oddsExplain;
    }

    public Integer getGameRule() {
        return gameRule;
    }

    public void setGameRule(Integer gameRule) {
        this.gameRule = gameRule;
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
        return "Playground{" +
        ", id=" + id +
        ", gid=" + gid +
        ", title=" + title +
        ", oddsExplain=" + oddsExplain +
        ", gameRule=" + gameRule +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
