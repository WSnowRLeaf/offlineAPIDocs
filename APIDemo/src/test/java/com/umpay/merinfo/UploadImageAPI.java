package com.umpay.merinfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.TreeMap;

import com.umpay.util.HttpUtilClient;
import org.junit.Test;
import org.springframework.util.Base64Utils;
//import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: zhangjing
 * @date:2019年8月6日 下午6:59:34
 * @类说明:资质上传接口
 * @产品号:
 */
public class UploadImageAPI {
	public String payUrl = "http://10.10.178.157:6904/upload/qualification";

	@Test
	public void uploadImage() throws Exception{
		
		try {
			File originalFile = new File("e:\\联动优势\\图片\\idCardFront.jpeg");//指定要读取的图片E:
			FileInputStream in = new FileInputStream(originalFile);
			//写入相应的文件
			ByteArrayOutputStream outByte = new ByteArrayOutputStream();
			int n = 0;
			while((n=in.read())!=-1){
				 //写入相关文件
	            outByte.write(n);
			}
			TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
			reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
			reqPay.put("reqDate", "20190813");
			reqPay.put("reqTime", "120000");
			reqPay.put("merId", "M2019081300000102");//报备编号	20	C	商户报备编号
			reqPay.put("imageType", "idCardFront");//资质图片类型	64	M
			reqPay.put("imageName", "身份证正面");//上传资质的名称	32	M
//			reqPay.put("idCardFront", "");//法人身份证正面
//			reqPay.put("idCardBack", "");//法人身份证反面
//			reqPay.put("idCardHandle", "");//法人手持身份证
//			reqPay.put("bankCardPhotoFront", "");//银行卡正面
//			reqPay.put("bankCardPhotoBack", "");//银行卡反面
//			reqPay.put("storeHeadPhoto", "");//门店门头照
//			reqPay.put("storeShopPhoto", "");//门店外景照
//			reqPay.put("storeHallPhoto", "");//门店内景照
//			reqPay.put("storeCashierPhoto", "");//门店收银台照
//			reqPay.put("acquiringAgreementPhoto", "");//商户收单协议照片
			
			//上传资质图片	Base64编码	M
			String bStr = Base64Utils.encodeToString(outByte.toByteArray());
			reqPay.put("imageSource", bStr);
			
			//发送post请求
			String result = HttpUtilClient.doPostJson(payUrl, new JSONObject(), reqPay);
			System.out.println("输出请求结果:"+result);
			in.close();
			outByte.close();
		}catch (Exception e3) {
			e3.printStackTrace();
		}
	}
}
