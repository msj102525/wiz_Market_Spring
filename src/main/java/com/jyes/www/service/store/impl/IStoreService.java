package com.jyes.www.service.store.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jyes.www.dto.store.StoreSearchRequestDTO;
import com.jyes.www.dto.store.StoreViewResponseDTO;
import com.jyes.www.vo.store.StoreListVo;
import com.jyes.www.vo.store.StoreVo;

/*
 * Copyright jyes.co.kr.
 * All rights reserved
 * This software is the confidential and proprietary information
 * of jyes.co.kr. ("Confidential Information")
 */
public interface IStoreService {
	
	public List<StoreVo> storeByAll();
	
	public StoreVo storeById(Map<String, Object> param);
	
	public StoreViewResponseDTO storeInfoById(Map<String, Object> param) throws SQLException;
	
	public List<StoreListVo> storeListByConditionSearch(StoreSearchRequestDTO param);
	
	public int storeListByConditionSearchCount(StoreSearchRequestDTO param);
	
	public List<StoreListVo> storeListByName(StoreSearchRequestDTO param);
	
	public int storeListByNameCount(StoreSearchRequestDTO param);
	
	@Transactional(rollbackFor={SQLException.class})
	public int insertStore(StoreVo param) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int updateStore(StoreVo param) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int deleteStore(StoreVo param) throws SQLException;
	
}