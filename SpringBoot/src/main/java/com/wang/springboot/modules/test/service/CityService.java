package com.wang.springboot.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wang.springboot.modules.common.vo.Result;
import com.wang.springboot.modules.test.entity.City;

/**
　 * <p>Title: CityService</p>
　 * <p>Description: </p>
　 * @author yi.wang
　 * @date 2020年4月30日 下午1:55:09 
　 * @version 1.0
*/
public interface CityService {

	/**
	*<p>Title: deleteCity</p>
	*<p>Description: </p>
	　 * @param cityId
	　 * @return
	*/
	Result<Object> deleteCity(int cityId);

	/**
	*<p>Title: updateCity</p>
	*<p>Description: </p>
	　 * @param city
	　 * @return
	*/
	Result<City> updateCity(City city);

	/**
	*<p>Title: insertCity</p>
	*<p>Description: </p>
	　 * @param city
	　 * @return
	*/
	Result<City> insertCity(City city);

	/**
	*<p>Title: getCityByCityName</p>
	*<p>Description: </p>
	　 * @param cityName
	　 * @param localCityName
	　 * @return
	*/
	City getCityByCityName(String cityName, String localCityName);

	/**
	*<p>Title: getCitiesByPage</p>
	*<p>Description: </p>
	　 * @param currentPage
	　 * @param pageSize
	　 * @param countryId
	　 * @return
	*/
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);

	/**
	*<p>Title: getCitiesByCountry</p>
	*<p>Description: </p>
	　 * @param countryId
	　 * @return
	*/
	List<City> getCitiesByCountry(int countryId);

}
