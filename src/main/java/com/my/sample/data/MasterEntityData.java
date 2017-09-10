package com.my.sample.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterEntityData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4887535955041888778L;
	Map<String, List<String>> masterData = new HashMap<String, List<String>>();

	public Map<String, List<String>> getMasterData() {
		return masterData;
	}

	public void setMasterData(Map<String, List<String>> masterData) {
		this.masterData = masterData;
	}
}
