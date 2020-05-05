package com.wang.springboot.modules.test.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wang.springboot.modules.test.entity.Country;
import com.wang.springboot.modules.test.service.CountryService;


/**
 * <p>
 * Title: CountryController
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午1:55:53 @version 1.0
 */
@RestController
@RequestMapping("/api")
public class CountryController {
	@Autowired
	private CountryService countryService;

	@RequestMapping("/country/{countryId}")
	public Country getCitiesByCountry(@PathVariable int countryId) {// get请求:获取(int)的写法@RequestParam
		Country country = countryService.getCountryId(countryId);
		return country;
	}

	@RequestMapping("/country")
	public Country getCountryByName(@RequestParam String countryName) {// get请求:获取键值对(String)的写法@RequestParam
		return countryService.getCountryByName(countryName);
	}
}
