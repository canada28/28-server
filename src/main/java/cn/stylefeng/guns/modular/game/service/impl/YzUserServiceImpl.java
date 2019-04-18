package cn.stylefeng.guns.modular.game.service.impl;

import cn.stylefeng.guns.modular.game.dao.YzUserMapper;
import cn.stylefeng.guns.modular.game.model.YzUser;
import cn.stylefeng.guns.modular.game.service.IYzUserService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author canrom7
 * @since 2019-04-04
 */
@Service
public class YzUserServiceImpl extends ServiceImpl<YzUserMapper, YzUser> implements IYzUserService {
    /**
     * 根据条件查询用户
     * @param page       分页
     * @param name       UID/姓名/手机号/昵称
     * @param beginTime  开始时间
     * @param endTime    结束时间
     */
    @Override
    public List<YzUser> selectListByQuery(Page<YzUser> page, String name, String beginTime, String endTime) {
        return baseMapper.selectListByQuery(page,name,beginTime,endTime);
    }
}
