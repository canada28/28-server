package cn.stylefeng.guns.modular.game.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 加拿大28数据
 * </p>
 *
 * @author canrom7
 * @since 2019-04-10
 */
@TableName("yz_canada28")
public class Canada28 extends Model<Canada28> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;
    /**
     * 期号
     */
    private Long period;
    /**
     * 日期
     */
    @TableField("date_str")
    private String dateStr;
    /**
     * 时间
     */
    @TableField("time_str")
    private String timeStr;
    /**
     * 中国时间
     */
    @TableField("ch_time")
    private Date chTime;
    /**
     * 开奖号码
     */
    private String numbers;
    /**
     * 球一结果
     */
    private Integer one;
    /**
     * 球二结果
     */
    private Integer two;
    /**
     * 球三结果
     */
    private Integer three;
    /**
     * 最终结果
     */
    private Integer result;
    /**
     * 奇偶数（1=奇数 2=偶数）
     */
    @TableField("odd_even")
    private Integer oddEven;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Date getChTime() {
        return chTime;
    }

    public void setChTime(Date chTime) {
        this.chTime = chTime;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Integer getOne() {
        return one;
    }

    public void setOne(Integer one) {
        this.one = one;
    }

    public Integer getTwo() {
        return two;
    }

    public void setTwo(Integer two) {
        this.two = two;
    }

    public Integer getThree() {
        return three;
    }

    public void setThree(Integer three) {
        this.three = three;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getOddEven() {
        return oddEven;
    }

    public void setOddEven(Integer oddEven) {
        this.oddEven = oddEven;
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
        return "Canada28{" +
        ", id=" + id +
        ", period=" + period +
        ", dateStr=" + dateStr +
        ", timeStr=" + timeStr +
        ", chTime=" + chTime +
        ", numbers=" + numbers +
        ", one=" + one +
        ", two=" + two +
        ", three=" + three +
        ", result=" + result +
        ", oddEven=" + oddEven +
        ", createTime=" + createTime +
        "}";
    }
}
