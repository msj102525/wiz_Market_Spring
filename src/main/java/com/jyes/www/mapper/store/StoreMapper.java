package com.jyes.www.mapper.store;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jyes.www.dto.store.StoreSearchRequestDTO;
import com.jyes.www.dto.store.StoreViewResponseDTO;
import com.jyes.www.vo.store.StoreListVo;
import com.jyes.www.vo.store.StoreVo;

@Repository(value = "storeMapper")
public interface StoreMapper {
	
	public List<StoreVo> storeByAll() throws SQLException;
	
	public StoreVo storeById(Map<String, Object> param) throws SQLException;
	
	public StoreViewResponseDTO storeInfoById(Map<String, Object> param) throws SQLException;
	
	public List<StoreListVo> storeListByConditionSearch(StoreSearchRequestDTO param) throws SQLException;
	
	public int storeListByConditionSearchCount(StoreSearchRequestDTO param) throws SQLException;
	
	public List<StoreListVo> storeListByName(StoreSearchRequestDTO param) throws SQLException;
	
	public int storeListByNameCount(StoreSearchRequestDTO param) throws SQLException;
	
	public int insertStore(StoreVo param) throws SQLException;
	
	public int updateStore(StoreVo param) throws SQLException;
	
	public int deleteStore(StoreVo param) throws SQLException;

}