package com.xxl.job.executor.core.util;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.executor.base.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class Tc5uUtil {

    private final String APP_ID = "TC_3876";
    private final String SECRET = "WEDFGFDSVFD#@o(*&^hbggh!jjhgyghiuiiuijbbghDSAFEWGHJQWQEC98-";

    @Value("${tc5u.url}")
    private String tc5uURL;

    public BaseResponse getTc5uData(String url, Map<String, Object> data) {
        String random_str = String.valueOf(System.currentTimeMillis()/1000);
        //appid
        data.put("appid", APP_ID);
        //时间戳算法精确到秒
        data.put("timestamp", random_str);

        /**
         *将请求参数按照ASCII码从小到大排序，并将其中参数值为空的剔除
         *按照“key=value&key1=value1&key2=value2&……&appid=xx×tamp=xx&……密钥”的格式拼接起来；
         */
        SortedMap<Object, Object> sortedMap = new TreeMap<>(data);
        StringBuffer b =new StringBuffer();
        int i = 0;
        for (Map.Entry<Object, Object> m : sortedMap.entrySet()) {
            i++;
            String k = m.getKey().toString();
            Object v = m.getValue();
            if (null != v && !"".equals(v)) {
                if (i != sortedMap.size()) {
                    b.append(k + "=" + v + "&");
                } else {
                    b.append(k + "=" + v);
                }
            }
        }
        //秘钥
        b.append(SECRET);
        /**
         *将秘钥和stringbuffer的数据拼接以 MD5 方式加签，生成【sign】参数
         */
        String sign= DigestUtils.md5DigestAsHex(b.toString().getBytes()).toUpperCase();
        data.put("sign", sign);
        JSONObject res =  JSONObject.parseObject(HttpsUtil.post(tc5uURL+url, data));
        return BaseResponse.success(res);
    }
}
