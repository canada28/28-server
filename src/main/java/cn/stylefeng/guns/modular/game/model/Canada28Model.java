package cn.stylefeng.guns.modular.game.model;

import java.util.List;

/**
 * Created by ccanrom7
 * CreateDate on 2019/4/2  14:48.
 * Email canrom7@outlook.com
 **/
public class Canada28Model {


    /**
     * drawNbr : 2409113
     * drawDate : Apr 1, 2019
     * drawTime : 12:37:00 AM
     * drawNbrs : [2,9,10,18,22,23,24,28,30,31,37,48,53,58,59,62,64,68,71,78]
     * drawBonus : 2
     */

    private long drawNbr;
    private String drawDate;
    private String drawTime;
    private int drawBonus;
    private List<Integer> drawNbrs;

    public long getDrawNbr() {
        return drawNbr;
    }

    public void setDrawNbr(long drawNbr) {
        this.drawNbr = drawNbr;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    public int getDrawBonus() {
        return drawBonus;
    }

    public void setDrawBonus(int drawBonus) {
        this.drawBonus = drawBonus;
    }

    public List<Integer> getDrawNbrs() {
        return drawNbrs;
    }

    public void setDrawNbrs(List<Integer> drawNbrs) {
        this.drawNbrs = drawNbrs;
    }

    @Override
    public String toString() {
        return "Canada28Model{" +
                "drawNbr=" + drawNbr +
                ", drawDate='" + drawDate + '\'' +
                ", drawTime='" + drawTime + '\'' +
                ", drawBonus=" + drawBonus +
                ", drawNbrs=" + drawNbrs +
                '}';
    }
}
