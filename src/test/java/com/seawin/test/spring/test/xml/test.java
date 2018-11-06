package com.seawin.test.spring.test.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;

/**
 * 
 * 解析特殊带有命名字符（xmlns="http://www.chinaport.gov.cn/ceb"）的xml文件
 * @author lijin
 * @version $Id: test.java, v 0.1 2018年10月25日 上午8:45:01 lijin Exp $
 */
public class test {
    public static void main(String[] args) {
        try {
            String directory = "E:/xml";/*存储xml文件的文件夹*/
            String fileSuffix = ".xml"; /*读取后缀为xml的文件*/
            File file = new File(directory);
            File[] filelist = file.listFiles();/*获取文件夹下的xml文件列表*/
            for (int i = 0; i < filelist.length; i++) {
                if (filelist[i].getName().lastIndexOf(fileSuffix) != -1) {
                    String filename = filelist[i].getName();/*获取每个xml文件的文件名*/
                    System.out.println(filename);
                    HashMap<String, String> nsMap = new HashMap<String, String>();
                    nsMap.put("ns", "http://www.chinaport.gov.cn/ceb"); //加入命名空间
                    SAXReader reader = new SAXReader(new DocumentFactory()); //reader.setFeature("http://apache.org/xml/features/validation/schema", true);		                    
                    reader.getDocumentFactory().setXPathNamespaceURIs(nsMap);
                    Document document = reader.read(directory + "/" + filename);/*读取xml文件，获得document对象*/
                    parse(document);
                    //获取子节点
                    /* XPath xsub = document.createXPath("//ns:OrderReturn");
                     xsub.setNamespaceURIs(nsMap);
                     Element beanNodes = (Element) xsub.selectSingleNode(document);
                     List<Element> statusNodes = beanNodes.selectNodes("//ns:returnStatus");
                     String returnStatus = statusNodes.get(0).getText();
                     System.out.println(returnStatus);*/

                    /*System.out.println(tmp.size());
                    List<Element> beanNodes = document
                        .selectNodes("//* [local-name()='OrderReturn']");
                    String returnStatus = "";
                    String returnInfo = "";
                    if (!CollectionUtils.isEmpty(beanNodes)) {
                        List<Element> statusNodes = beanNodes.get(0).selectNodes("//returnStatus");
                        List<Element> returnInfoNodes = beanNodes.get(0)
                            .selectNodes("//returnInfo");
                        if (!CollectionUtils.isEmpty(statusNodes)) {
                            returnStatus = statusNodes.get(0).getText();
                        }
                        if (!CollectionUtils.isEmpty(returnInfoNodes)) {
                            returnInfo = returnInfoNodes.get(0).getText();
                        }

                    }
                    System.out.println(returnStatus);
                    System.out.println(returnInfo);*/

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<BtcExportOrder> parse(Document document) {
        List<BtcExportOrder> returnOrderList = new ArrayList<BtcExportOrder>();
        List<Element> orderNoNodes = (List<Element>) document.selectNodes("//ns:orderNo");
        List<Element> returnStatusNodes = (List<Element>) document.selectNodes("//ns:returnStatus");
        List<Element> returnInfoNodes = (List<Element>) document.selectNodes("//ns:returnInfo");
        if (!CollectionUtils.isEmpty(orderNoNodes)) {
            for (int i = 0; i < orderNoNodes.size(); i++) {
                BtcExportOrder returnOrder = new BtcExportOrder();
                Element orderNoNode = orderNoNodes.get(i);
                Element returnStatusNode = returnStatusNodes.get(i);
                Element returnInfoNode = returnInfoNodes.get(i);
                returnOrder.setOrderNo(orderNoNode == null ? "" : orderNoNode.getText());
                returnOrder.setStatus(returnStatusNode == null ? "" : returnStatusNode.getText());
                returnOrder.setReturnInfo(returnInfoNode == null ? "" : returnInfoNode.getText());
                returnOrderList.add(returnOrder);
            }
        }
        return returnOrderList;
    }

}
