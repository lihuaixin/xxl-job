package com.xxl.job.executor.core.util;

import com.xxl.job.executor.core.enmus.BizNoPrefixEnum;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author:chenglvpeng
 * @Date:2018/7/19 10:53
 * @Description: 生成集群部署对应的编号，如订单编号中自增部分
 * @注意：本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component("generatorClusterNo")
public class GeneratorClusterNo {


    /**
     * 获取业务单号，默认生成18位长，新的业务请使用此方法来生成业务单号
     *
     * @author: wangli
     * @date: 2018/12/28 18:59
     * @param prefix 业务前缀
     * @return java.lang.String
     */
    public static String getBizNo(BizNoPrefixEnum prefix) {
        return getBizNo(prefix, 18);
    }

    /**
     * 获取业务单号，新的业务请使用此方法来生成业务单号
     *
     * @author: wangli
     * @date: 2018/12/28 19:26
     * @param prefix 业务前缀
     * @param length 生成长度
     * @return java.lang.String
     */
    public static String getBizNo(BizNoPrefixEnum prefix, int length) {
        StringBuilder randomNumBuilder = new StringBuilder();
        String uuid = UUID.randomUUID().toString();
        //有可能是负数,取绝对值
        long hashCode =  Math.abs(uuid.hashCode());
        randomNumBuilder.append(hashCode);

        // 生成随机数长度 = 所需长度 - 2位业务前缀
        int randomNumberLength = length - 2;
        // 补位数长度 = 生成随机数长度 - hashCode的长度
        int supplementLength = randomNumberLength - randomNumBuilder.length() > 0 ?
                randomNumberLength - randomNumBuilder.length() : 0;
        if (supplementLength > 0) {
            randomNumBuilder.append(CommonUtil.getRandomNumber(supplementLength));
        }

        // 0 代表前面补充0 d 代表参数为正数型
        StringBuilder formatBuilder = new StringBuilder("%s%0").append(randomNumberLength).append("d");
        return String.format(formatBuilder.toString(), prefix.getCode(), Long.valueOf(randomNumBuilder.toString()));
    }
}
