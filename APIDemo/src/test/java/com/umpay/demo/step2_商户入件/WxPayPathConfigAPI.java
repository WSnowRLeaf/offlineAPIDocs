package com.umpay.demo.step2_商户入件;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.TreeMap;

import org.junit.Test;

//import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;
import com.umpay.common.Common;

/**
 * @author: zhangjing
 * @date:2019年8月6日 下午4:50:15
 * @类说明:
 * @产品号:
 */
public class WxPayPathConfigAPI {
	public String payUrl ="http://106.120.215.230:8011/entry-provider-plat-client/merchants/jsApiPath";
	
	@Test
	public void queryOrder() throws UnsupportedEncodingException, GeneralSecurityException, IOException{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
		reqPay.put("acqMerId", "41509208");//商户编号	8	C	merId和acqMerId至少存在一个
		reqPay.put("jsapiPath", "");//支付授权目录
		reqPay.put("signature", "");	

		String reqpay = AddSign.addSign(reqPay);
		JSONObject jsonObject1 =JSONObject.parseObject(reqpay);
		String result = Common.runJsonPost(payUrl, jsonObject1,"UTF-8");
		System.out.println("输出请求结果:"+result);
		
	}
}
