package cn.stylefeng.guns.modular.game.dao;

import cn.stylefeng.guns.modular.game.model.Canada28;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加拿大28数据 Mapper 接口
 * </p>
 *
 * @author canrom7
 * @since 2019-04-10
 */
public interface Canada28Mapper extends BaseMapper<Canada28> {

    List<Canada28> selectCanadaByQuery(@Param("page") Page<Canada28> page,@Param("condition") String condition);
}
