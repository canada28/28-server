package cn.stylefeng.guns.modular.game.dao;

import cn.stylefeng.guns.modular.game.model.YzUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author canrom7
 * @since 2019-04-04
 */
public interface YzUserMapper extends BaseMapper<YzUser> {

    /**
     * 根据条件查询用户
     * @param page       分页
     * @param namea       UID/姓名/手机号/昵称
     * @param beginTime  开始时间
     * @param endTime    结束时间
     */
    List<YzUser> selectListByQuery( @Param("page") Page<YzUser> page, @Param("namea") String namea, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
