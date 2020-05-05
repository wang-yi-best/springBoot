package com.wang.springboot.modules.test.service;

import com.wang.springboot.modules.test.entity.Country;

/**
　 * <p>Title: CountryService</p>
　 * <p>Description: </p>
　 * @author yi.wang
　 * @date 2020年4月30日 下午1:53:54 
　 * @version 1.0
*/
public interface CountryService {

	/**
	*<p>Title: getCountryId</p>
	*<p>Description: </p>
	　 * @param countryId
	　 * @return
	*/
	Country getCountryId(int countryId);

	/**
	*<p>Title: getCountryByName</p>
	*<p>Description: </p>
	　 * @param countryName
	　 * @return
	*/
	Country getCountryByName(String countryName);

}
