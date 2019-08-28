package com.umpay.merinfo;

import java.util.Map;
import java.util.TreeMap;

import com.umpay.util.HttpUtilClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;

public class SignTestAPI {

	private static final Logger log = LoggerFactory.getLogger(SignTestAPI.class);
	String url ="http://106.120.215.230:8011/entry-provider-plat-client/";
//	String url ="http://10.10.178.87:6904/";

	
	/**
	 * 
	 * @Description: 获取电子合约挑战码
	 * @throws Exception
	 * @return void 返回类型
	 * @author yangqr
	 * @date 2019年8月19日 上午11:41:42
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getVerifyCode() throws Exception{
		
		TreeMap<String, Object> reqSign = new TreeMap<String, Object>();

		reqSign.put("acqSpId", "Y471790403");//服务商编号	10	M	服务商编号
		reqSign.put("merId", "M2019082000000271");//报备编号	16	M	报备编号 
		
		//对请求报文做加签处理
		String reqMerinfo = AddSign.addSign(reqSign);
		Map<String, Object> reqMap = JSONObject.parseObject(reqMerinfo);

		//发送post请求
		String result = HttpUtilClient.doPostJson(url+"sign/getVerifyCode", new JSONObject(), reqMap);
		System.out.println("输出请求结果:"+ result.toString());
		
		//将响应报文转成map
		Map<String, Object> treeMap = JSON.parseObject(result, TreeMap.class);	
		
	}
	/**
	 * 
	 * @Description: 电子合约验签
	 * @throws Exception
	 * @return void 返回类型
	 * @author yangqr
	 * @date 2019年8月19日 上午11:42:18
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void doVerifyCodeSign() throws Exception{
		
		TreeMap<String, Object> reqSign = new TreeMap<String, Object>();

		reqSign.put("acqSpId", "Y471790403");//服务商编号	10	M	服务商编号
		reqSign.put("merId", "M2019082000000271");//报备编号	16	M	报备编号 
		reqSign.put("transCaId", "nxr1ji7h-lh1d-rp4u-ng4h-okq0mgmi7tq8");//缓存事务ID
		reqSign.put("verifyCode", "715601");//验证码
		
		//对请求报文做加签处理
		String reqMerinfo = AddSign.addSign(reqSign);
		Map<String, Object> reqMap = JSONObject.parseObject(reqMerinfo);

		//发送post请求
		String result = HttpUtilClient.doPostJson(url+"sign/doVerifyCodeSign", new JSONObject(), reqMap);
		System.out.println("输出请求结果:"+ result.toString());
		
		//将响应报文转成map
		Map<String, Object> treeMap = JSON.parseObject(result, TreeMap.class);	
		
	}
	
	public static void main(String args[]){
		try {
			//new SignTestAPI().getVerifyCode();
			new SignTestAPI().doVerifyCodeSign();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
