package com.seawin.test.workflow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.seawin.pojo.vo.RequestResultVO;
import com.seawin.pojo.vo.sale.CustomerAuditVO;
import com.seawin.test.HttpClientSupport;
import com.seawin.test.base.BaseTest;

public class WorkflowControllerTest extends BaseTest {

    private static final String LOGINURL = "http://localhost:8080/SeawinWebappBase/j_spring_security_check.do";
    private static final String BASEPATH = "http://localhost:8080/SeawinWebappBase/workflow";

    //审核
    @Test
    public void test_audit() throws IOException, InterruptedException {
        Map<String, String> loginParams = new HashMap<String, String>();
        loginParams.put("account", "admin");
        loginParams.put("password", "123456");
        loginParams.put("company", "2");
        String sessionId = HttpClientSupport.getSessionId(LOGINURL, loginParams, null);

        Map<String, String> params = new HashMap<String, String>();
        params.put("taskId", "542560");
        params.put("isPass", "true");
        params.put("remark", "同意");
        params.put("keys", getKeyJson());
        printResult(params, "params", true);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Cookie", "JSESSIONID=" + sessionId);
        String url = BASEPATH + "/audit.do";
        RequestResultVO result = HttpClientSupport.sendNormalRequestWithHeader(url,
            RequestResultVO.class, params, headers);
        printResult(result, "audit", true);
    }

    private String getKeyJson() {
        CustomerAuditVO vo = new CustomerAuditVO();
        vo.setCustomerId(1023);
        vo.setLadingBillTypeId(2);
        vo.setVerificationBillTypeId(3);
        String jsonStr = JSON.toJSONString(vo);
        System.out.println(jsonStr);
        return jsonStr;
    }

    //流程取回
    @Test
    public void test_reback() throws IOException, InterruptedException {
        Map<String, String> loginParams = new HashMap<String, String>();
        loginParams.put("account", "admin");
        loginParams.put("password", "123456");
        loginParams.put("company", "2");
        String sessionId = HttpClientSupport.getSessionId(LOGINURL, loginParams, null);

        Map<String, String> params = new HashMap<String, String>();
        params.put("taskId", "422525");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Cookie", "JSESSIONID=" + sessionId);
        String url = BASEPATH + "/reback.do";
        RequestResultVO result = HttpClientSupport.sendNormalRequestWithHeader(url,
            RequestResultVO.class, params, headers);
        printResult(result, "reback", true);
    }
}
