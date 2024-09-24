package com.jyes.www.service.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyes.www.dto.store.StoreSearchRequestDTO;
import com.jyes.www.dto.store.StoreViewResponseDTO;
import com.jyes.www.mapper.store.StoreMapper;
import com.jyes.www.service.store.impl.IStoreService;
import com.jyes.www.vo.store.StoreListVo;
import com.jyes.www.vo.store.StoreVo;

@Service(value="storeService")
public class StoreService implements IStoreService {
	
	@Resource(name="storeMapper")
	private StoreMapper storeMapper;
	
	@Override
	public List<StoreVo> storeByAll() {
		List<StoreVo> result = new ArrayList<StoreVo>();
		
		try {
			result = storeMapper.storeByAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public StoreVo storeById(Map<String, Object> param) {
		StoreVo result = new StoreVo();
		try {
			result = storeMapper.storeById(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<StoreListVo> storeListByConditionSearch(StoreSearchRequestDTO param){
		List<StoreListVo> result = new ArrayList<StoreListVo>();
		
		try {
			result = storeMapper.storeListByConditionSearch(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int storeListByConditionSearchCount(StoreSearchRequestDTO param){
		int result = 0;
		
		try {
			result = storeMapper.storeListByConditionSearchCount(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public List<StoreListVo> storeListByName(StoreSearchRequestDTO param){
		List<StoreListVo> result = new ArrayList<StoreListVo>();
		
		try {
			result = storeMapper.storeListByName(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int storeListByNameCount(StoreSearchRequestDTO param) {
		int result = 0;
		try {
			result = storeMapper.storeListByNameCount(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insertStore(StoreVo param) throws SQLException{
		return storeMapper.insertStore(param);
	}

	@Override
	public int updateStore(StoreVo param) throws SQLException{
		return storeMapper.updateStore(param);
	}

	@Override
	public int deleteStore(StoreVo param) throws SQLException{
		return storeMapper.deleteStore(param);
	}

	@Override
	public StoreViewResponseDTO storeInfoById(Map<String, Object> param) throws SQLException {
		return storeMapper.storeInfoById(param);
	}

}
