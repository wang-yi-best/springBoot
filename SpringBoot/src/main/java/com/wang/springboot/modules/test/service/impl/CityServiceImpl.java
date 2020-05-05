package com.wang.springboot.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.springboot.modules.common.vo.Result;
import com.wang.springboot.modules.test.dao.CityDao;
import com.wang.springboot.modules.test.entity.City;
import com.wang.springboot.modules.test.service.CityService;

/**
 * <p>
 * Title: CityServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午1:55:24 @version 1.0
 */
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;

	/*
	 * (non-Javadoc) <p>Title: getCitiesByCountry</p> <p>Description: </p>
	 * 
	 * @param countryId
	 * 
	 * @return
	 * 
	 * @see
	 * com.wangyi.springBoot.modules.test.service.CityService#getCitiesByCountry
	 * (int)
	 */
	@Override
	public List<City> getCitiesByCountry(int countryId) {
		List<City> cities = cityDao.getCitiesByCountry(countryId);
		return cities;
	}

	/*
	 * (non-Javadoc) <p>Title: getCitiesByPage</p> <p>Description: </p>
	 * 
	 * @param currentPage
	 * 
	 * @param pageSize
	 * 
	 * @param countryId
	 * 
	 * @return
	 * 
	 * @see
	 * com.wangyi.springBoot.modules.test.service.CityService#getCitiesByPage(
	 * int, int, int)
	 */
	@Override
	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		PageHelper.startPage(currentPage, pageSize);
		List<City> cities = cityDao.getCitiesByCountry(countryId);
		return new PageInfo(cities);
	}

	/*
	 * (non-Javadoc) <p>Title: insertCity</p> <p>Description: </p>
	 * 
	 * @param city
	 * 
	 * @return
	 * 
	 * @see
	 * com.wangyi.springBoot.modules.test.service.CityService#insertCity(com.
	 * wangyi.springBoot.modules.test.entity.City)
	 */
	@Override
	public Result<City> insertCity(City city) {
		return cityDao.insertCity(city);
	}

	/*
	 * (non-Javadoc) <p>Title: getCityByCityName</p> <p>Description: </p>
	 * 
	 * @param cityName
	 * 
	 * @param localCityName
	 * 
	 * @return
	 * 
	 * @see
	 * com.wangyi.springBoot.modules.test.service.CityService#getCityByCityName(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public City getCityByCityName(String cityName, String localCityName) {
		List<City> list = Optional.ofNullable(cityDao.getCityByCityName(cityName, localCityName))
				.orElse(Collections.emptyList());
		return list.isEmpty() ? null : list.get(0);
	}

	/*
	 * (non-Javadoc) <p>Title: updateCity</p> <p>Description: </p>
	 * 
	 * @param city
	 * 
	 * @return
	 * 
	 * @see
	 * com.wangyi.springBoot.modules.test.service.CityService#updateCity(com.
	 * wangyi.springBoot.modules.test.entity.City)
	 */
	@Override
	public Result<City> updateCity(City city) {
		return cityDao.updateCity(city);
	}

	/*
	 * (non-Javadoc) <p>Title: deleteCity</p> <p>Description: </p>
	 * 
	 * @param cityId
	 * 
	 * @return
	 * 
	 * @see
	 * com.wangyi.springBoot.modules.test.service.CityService#deleteCity(int)
	 */
	@Override
	public Result<Object> deleteCity(int cityId) {
		return cityDao.deleteCity(cityId);
	}
}
