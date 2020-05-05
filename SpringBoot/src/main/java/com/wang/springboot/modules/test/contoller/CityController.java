package com.wang.springboot.modules.test.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wang.springboot.modules.common.vo.Result;
import com.wang.springboot.modules.test.entity.City;
import com.wang.springboot.modules.test.service.CityService;

/**
 * <p>
 * Title: CityController
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午1:50:17 @version 1.0
 */
@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	private CityService cityService;

	/**
	 * http://127.0.0.1/api/cities/522
	 * <p>
	 * Title: getCitiesByCountry
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param countryId @return
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountry(@PathVariable int countryId) {// get请求方式:单个变量的写法@PathVariable
		List<City> cities = cityService.getCitiesByCountry(countryId);
		return cities;
	}

	@RequestMapping("/cities")
	public PageInfo<City> getCitiesByPage(@RequestParam int currentPage, // get请求方式:多个变量的写法@RequestParam
			@RequestParam int pageSize, @RequestParam int countryId) {
		PageInfo<City> cities = cityService.getCitiesByPage(currentPage, pageSize, countryId);
		return cities;
	}

	@RequestMapping("/city")
	public City getCityByCityName(@RequestParam(required = false) String cityName,
			@RequestParam(required = false) String localCityName) {
		return cityService.getCityByCityName(cityName, localCityName);
	}

	@PostMapping(value = "/city", consumes = "application/json") // 当返回为json时下面参数的注解要是@RequestBody
	public Result<City> insertCity(@RequestBody City city) {
		return cityService.insertCity(city);
	}

	@PutMapping(value = "city", consumes = "application/x-www-form-urlencoded")
	public Result<City> updateCity(@ModelAttribute City city) {
		return cityService.updateCity(city);
	}

	@DeleteMapping("/city/{cityId}")
	public Result<Object> deleteCity(@PathVariable int cityId) {
		return cityService.deleteCity(cityId);
	}

}
