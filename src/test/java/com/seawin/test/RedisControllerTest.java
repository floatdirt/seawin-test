package com.seawin.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.seawin.test.base.BaseTest;

public class RedisControllerTest extends BaseTest {

	private static final String LOGINURL = "http://localhost:8080/SeawinWebappBase/j_spring_security_check.do";
	private static final String BASEPATH = "http://localhost:8080/SeawinWebappBase/workflow";

	// // 流程取回
	// @Test
	// public void test_reback() throws IOException, InterruptedException {
	public static void main(String[] args) throws Exception {
		// 锁住所有线程，等待并发执行
		final CountDownLatch begin = new CountDownLatch(1);
		// final ExecutorService executorService =
		// Executors.newCachedThreadPool();
		final ExecutorService executorService = Executors
				.newFixedThreadPool(50);
		for (int i = 0; i < 50; i++) {
			final Map<String, String> param = new HashMap<String, String>();

			/*
			 * if(i%2>0){ param.put( "skuID", "92936"); }else{ param.put(
			 * "skuID", "92794"); } param.put("code", "12");
			 * param.put("uuID","1477015200000_1602774953"+i);
			 * param.put("memberID", "10000000271443ss3"+i); param.put("token",
			 * "246810"+i); param.put( "sessionID", "1234"+i);
			 * param.put("activityCode", "MS_2017041213110");
			 */
			final Map<String, String> headers = new HashMap<String, String>();
			Runnable run = new Runnable() {

				public void run() {
					try {
						// 等待，所有一起执行
						begin.await();
						// 开始模拟登录等待。。。
						// Thread.sleep((long) (Math.random() * 10000));

						String result = HttpClientSupport
								.sendRequest("http://localhost:8080/SeawinWebappBase/redisController/listIdNameByName.do?name=basedataPortOP");

						// LogUtil.debug(logger, result);

						System.out.println(JSON.toJSONString(result,
								SerializerFeature.WriteMapNullValue,
								SerializerFeature.PrettyFormat));
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
					}
				}
			};
			executorService.submit(run);
		}

		System.out.println("开始执行");
		// begin减一，开始并发执行
		begin.countDown();

		// 关闭执行
		executorService.shutdown();
	}

	// }
	/*
	 * Map<String, String> loginParams = new HashMap<String, String>();
	 * loginParams.put("account", "admin"); loginParams.put("password",
	 * "123456"); loginParams.put("company", "2"); String sessionId =
	 * HttpClientSupport.getSessionId(LOGINURL, loginParams, null);
	 * 
	 * Map<String, String> params = new HashMap<String, String>();
	 * params.put("taskId", "422525"); Map<String, String> headers = new
	 * HashMap<String, String>(); headers.put("Cookie", "JSESSIONID=" +
	 * sessionId); String url = BASEPATH + "/reback.do"; RequestResultVO result
	 * = HttpClientSupport.sendNormalRequestWithHeader(url,
	 * RequestResultVO.class, params, headers); printResult(result, "reback",
	 * true);
	 */
	// }
}
