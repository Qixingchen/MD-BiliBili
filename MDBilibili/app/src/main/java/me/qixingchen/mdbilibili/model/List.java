package me.qixingchen.mdbilibili.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
/**
 * author: fython  (https://github.com/fython)
 * */
public class List {

	public String name;
	public int pages;
	public int code;
	public int result;

	public java.util.List<VideoItemInfo> lists;

	private JsonObject list;

	public static List createFromJson(String json) {
		List result = new Gson().fromJson(json, List.class);
		Iterator<Map.Entry<String, JsonElement>> iterator = result.list.entrySet().iterator();
		if (result.lists == null) {
			result.lists = new ArrayList<>();
		}
		while (iterator.hasNext()) {
			Map.Entry<String, JsonElement> element = iterator.next();
			try {
				result.lists.add(new Gson().fromJson(element.getValue(), VideoItemInfo.class));
			} catch (Exception e) {
				// Just ignore it.
			}
		}

		result.list = null;

		return result;
	}

}
