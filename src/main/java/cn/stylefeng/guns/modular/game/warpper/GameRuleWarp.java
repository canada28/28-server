package cn.stylefeng.guns.modular.game.warpper;

import cn.stylefeng.guns.modular.game.model.Gamerule;
import cn.stylefeng.guns.modular.game.model.Odds;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.util.List;

/**
 * Created by ccanrom7
 * CreateDate on 2019/4/11  11:29.
 * Email canrom7@outlook.com
 **/
public class GameRuleWarp {
    private List<Gamerule> gameruleList;
    private List<Odds> oddsList;

    public GameRuleWarp() {
    }

    public GameRuleWarp(List<Gamerule> gameruleList, List<Odds> oddsList) {
        this.gameruleList = gameruleList;
        this.oddsList = oddsList;
    }

    public List<Gamerule> getGameruleList() {
        return gameruleList;
    }

    public void setGameruleList(List<Gamerule> gameruleList) {
        this.gameruleList = gameruleList;
    }

    public List<Odds> getOddsList() {
        return oddsList;
    }

    public void setOddsList(List<Odds> oddsList) {
        this.oddsList = oddsList;
    }

}
