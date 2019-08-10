package com.ps.service.impl;

import com.ps.MD5Util;
import com.ps.config.ThreadPool;
import com.ps.domain.*;
import com.ps.mapper.QunaireMapper;
import com.ps.service.marketing_service.QuestionnaireService;
import com.ps.service.member_service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service(version = "1.0.0")
public class QunnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private QunaireMapper qunaireMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ThreadPool threadPool;

    @Override
    public void addQunaire(List<QNaire> qNaire, Question question) {
        qunaireMapper.addTitle(question);
        int titleId = qunaireMapper.queryTitle(question);
        for (QNaire naire : qNaire) {
            naire.setTitleId(titleId);
        }
        qunaireMapper.addQunaire(qNaire);
    }

    @Override
    public Question getTitle(int id) {
        return qunaireMapper.getTitle(id);
    }

    @Override
    public List<QNaire> getQue(int id) {
        return qunaireMapper.getQue(id);
    }

    @Override
    public void answer(List<QuResult> quResult) {
        qunaireMapper.answer(quResult);
    }

    @Override
    public String invitation(int id) {
        String code = MD5Util.getMd5Code(System.currentTimeMillis() + "" + id + "qqq");
        userService.addCode(code, id);
        return code;
    }

    @Override
    public Result seckill(Order order) throws ParseException {
        Result result = new Result();
        String info = template.opsForValue().get(order.getUserId());
        if (info != null) {
            result.setCode(1);
            result.setMessage("操作太快,5秒一次");
            return result;
        }
        template.opsForValue().set(order.getUserId()+"","userInfo",5);
        User user = userService.queryById(order.getUserId());
        Shop shop = qunaireMapper.queryShop(order.getShopId());
        if (user == null) {
            result.setCode(1);
            result.setMessage("该用户不存在");
            return result;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        long start = simpleDateFormat.parse(shop.getStart()).getTime();
        long end = simpleDateFormat.parse(shop.getEnd()).getTime();
        long now = System.currentTimeMillis();
        if (start > now || end < now) {
            result.setCode(1);
            result.setMessage("超过时间");
            return result;
        }
        if (shop.getNumber() == 0) {
            result.setCode(1);
            result.setMessage("已被抢光");
            return result;
        }
        if (shop.getIntegral() > user.getIntegral()) {
            result.setCode(1);
            result.setMessage("积分不够");
            return result;
        }
        Order order1 = qunaireMapper.getOrder(order.getUserId(), order.getShopId());
        if (order1 == null) {
            result.setCode(1);
            result.setMessage("你已兑换过此商品");
            return result;
        }

        result.setCode(0);
        result.setMessage("兑换成功");
        return result;
    }

    @KafkaListener(topics = "panshi")
    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
//        logger.info("{} ‐ {} : {}", cr.topic(), cr.key(), cr.value());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        long startTime = simpleDateFormat.parse("2019-07-22 00-00-00").getTime();
        long lastTime = simpleDateFormat.parse("2019-07-26 00-00-00").getTime();
        if (Long.parseLong(cr.value().toString()) > startTime && Long.parseLong(cr.value().toString()) < lastTime) {
            qunaireMapper.addInte(100);
            qunaireMapper.flow(100, 0);
        }
    }

    @KafkaListener(topics = "ps")
    public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
//        logger.info("{} ‐ {} : {}", cr.topic(), cr.key(), cr.value());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        long startTime = simpleDateFormat.parse("2019-07-22 00-00-00").getTime();
        long lastTime = simpleDateFormat.parse("2019-07-26 00-00-00").getTime();
        if (Long.parseLong(cr.value().toString().split("-")[0]) > startTime && Long.parseLong(cr.value().toString().split("-")[0]) < lastTime) {
            qunaireMapper.addInte(100);
            qunaireMapper.flow(100, 0);
        }
    }
}
