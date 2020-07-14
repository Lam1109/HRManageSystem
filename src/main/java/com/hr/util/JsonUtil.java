package com.hr.util;

import com.alibaba.druid.support.json.JSONUtils;

public class JsonUtil {
	@SuppressWarnings("unchecked")
	public static <T> T Json2Object(String json,Class<T> t) {
		T object=(T)JSONUtils.parse(json);
		return object;
	}
	public static <T> String Obejct2Json(T t) {
		return JSONUtils.toJSONString(t);
	}
}
