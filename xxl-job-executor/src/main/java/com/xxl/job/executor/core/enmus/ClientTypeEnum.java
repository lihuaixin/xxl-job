package com.xxl.job.executor.core.enmus;


/**
 *@desc: 客户端类型枚举
 *@author:  weiqingeng
 *@date:  2018/7/9 19:45
 */
public enum ClientTypeEnum {
	
	ANDROID("Andriod", "安卓"), 
	IOS("IOS", "苹果");

	private String code;
	private String description;

	ClientTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public static ClientTypeEnum findEnumByCode(String code) {
		for (ClientTypeEnum clientType : ClientTypeEnum.values()) {
			if (clientType.getCode().equals(code)) {
				return clientType;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
