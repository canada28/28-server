package cn.stylefeng.guns.modular.system.model;

/**
 * Created by ccanrom7
 * CreateDate on 2019/3/22  21:41.
 * Email canrom7@outlook.com
 **/
public class DayCount {
    private int day;
    private String moneys;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMoneys() {
        return moneys;
    }

    public void setMoneys(String moneys) {
        this.moneys = moneys;
    }

    @Override
    public String toString() {
        return "DayCount{" +
                "day=" + day +
                ", moneys=" + moneys +
                '}';
    }
}
