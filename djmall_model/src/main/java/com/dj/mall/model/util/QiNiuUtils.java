package com.dj.mall.model.util;

/**
 * 七牛云上传文件
 */

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 七牛云上传文件   例如头像上传到七牛云，无论在哪一台服务器登陆，都能获取到自己的头像
 * @author zouChan
 *
 */
public class QiNiuUtils {

    // 设置需要操作的账号的AK和SK
    public static final String ACCESS_KEY = "dVB-xjed5zrD6RgwnnT1F-n4zBknla2CKZTZTPo4";
    public static final String SECRET_KEY = "dSjUMTrIc6eBZ913i4s-0-hBPLCih0tyyyIB090L";
    // 要上传的空间名
    public static final String BUCKET_NAME = "1905-djmall-zw";

	public static String upload(MultipartFile file) {
		// 构造一个带指定 Region 对象的配置类
		// 华东	Region.region0(), Region.huadong()
		// 华北	Region.region1(), Region.huabei()
		// 华南	Region.region2(), Region.huanan()
		// 北美	Region.regionNa0(), Region.beimei()
		// 东南亚	Region.regionAs0(), Region.xinjiapo()
		Configuration cfg = new Configuration(Region.huabei());
		// 新建上传管理器
		UploadManager uploadManager = new UploadManager(cfg);
		// 生成上传凭证，然后准备上传
		String accessKey = ACCESS_KEY;
		String secretKey = SECRET_KEY;
		String bucket = BUCKET_NAME;
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		// String key = file.getOriginalFilename();
		String key = UUID.randomUUID().toString().replace("-", "");
		try {
			try {
				//getBytes()是Java编程语言中将一个字符串转化为一个字节数组byte[]的方法
				//String 的 getBytes()方法是得到一个系统默认的编码格式的字节数组。
				byte[] uploadBytes = file.getBytes();
				ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
				Auth auth = Auth.create(accessKey, secretKey);
				String upToken = auth.uploadToken(bucket);
				Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
				// 解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				return putRet.key;
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void del(String key) {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.huabei());
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
		    bucketManager.delete(BUCKET_NAME, key);
		} catch (QiniuException ex) {
		    //如果遇到异常，说明删除失败
		    System.err.println(ex.code());
		    System.err.println(ex.response.toString());
		}
	}
	
	//自定义回复内容  只需要自定义对应的类  再调用以下方法就可以转为自定义回复的类对象了
	//MyPutRet myPutRet=response.jsonToObject(MyPutRet.class);
	class MyPutRet {
	    public String key;
	    public String hash;
	    public String bucket;
	    public long fsize;
	}
}
