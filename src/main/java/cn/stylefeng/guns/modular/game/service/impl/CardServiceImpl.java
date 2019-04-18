package cn.stylefeng.guns.modular.game.service.impl;

import cn.stylefeng.guns.modular.game.model.Card;
import cn.stylefeng.guns.modular.game.dao.CardMapper;
import cn.stylefeng.guns.modular.game.service.ICardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 提现卡包 服务实现类
 * </p>
 *
 * @author canrom7
 * @since 2019-04-08
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {

}
