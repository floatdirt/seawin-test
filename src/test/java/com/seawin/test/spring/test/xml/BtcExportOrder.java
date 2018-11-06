package com.seawin.test.spring.test.xml;

import java.io.Serializable;
import java.util.Date;

public class BtcExportOrder  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7367307841215116239L;
	private String id;
	private String appType;               //报关类型(1-新增 2-变更 3-删除)
	private String appTime;               //报送时间
	private String appStatus;             //报送类型(1-暂存 2-申报)
	private String orderType;             //订单类型(I - 进口  E - 出口)
	private String orderNo;               //订单编号
	private String goodsValue;        //商品金额
	private String freight;           //运杂费
	private String currency;              //币种
	private String gnum;                  //序号
	private String itemNo;                //商品货号
	private String itemName;              //商品名称
	private String unit;                  //计量单位
	private String qty;               //数量
	private String price;             //单价
	private String totalPrice;        //总价
	private String status;           //回执状态
	private String returnInfo;       //回执信息
	private Date createTime;              //创建时间
	private String createTimeStr;         //创建时间
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getAppType() {
		return appType;
	}



	public void setAppType(String appType) {
		this.appType = appType;
	}



	public String getAppTime() {
		return appTime;
	}



	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}



	public String getAppStatus() {
		return appStatus;
	}



	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}



	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public String getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}



	public String getGoodsValue() {
		return goodsValue;
	}



	public void setGoodsValue(String goodsValue) {
		this.goodsValue = goodsValue;
	}



	public String getFreight() {
		return freight;
	}



	public void setFreight(String freight) {
		this.freight = freight;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public String getGnum() {
		return gnum;
	}



	public void setGnum(String gnum) {
		this.gnum = gnum;
	}



	public String getItemNo() {
		return itemNo;
	}



	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getQty() {
		return qty;
	}



	public void setQty(String qty) {
		this.qty = qty;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}



	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getReturnInfo() {
		return returnInfo;
	}



	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}



    //	public String getCreateTimeStr() {
    //		if(!ValidateHelper.isNullOrEmpty(this.createTime)){
    //			return this.createTime.toString().substring(0, 19);
    //		}
    //		return createTimeStr;
    //	}

	
	
}
