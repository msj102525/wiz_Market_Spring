package com.jyes.www.service.shop;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyes.www.mapper.shop.ShopMapper;
import com.jyes.www.service.shop.impl.IShopService;

@Service(value="shopService")
public class ShopService implements IShopService {
	
	@Resource(name="shopMapper")
	private ShopMapper shopMapper;

}
