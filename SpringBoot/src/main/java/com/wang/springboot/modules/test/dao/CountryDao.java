package com.wang.springboot.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wang.springboot.modules.test.entity.Country;

/**
 * <p>
 * Title: CountryDao
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午1:53:08 @version 1.0
 */
@Repository
@Mapper
public interface CountryDao {

	/**
	 * <p>
	 * Title: getCountryId
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param countryId @return
	 */
	@Select("SELECT * FROM m_country WHERE country_id=#{countryId}")
	@Results(id = "cities", value = { @Result(column = "country_id", property = "countryId"),
			@Result(column = "country_id", property = "cities", javaType = List.class, many = @Many(select = "com.wangyi.springBoot.modules.test.dao.CityDao.getCitiesByCountry")) })
	// 查询单个以多个的写法one=@One(select="com.wangyi.springBoot.modules.test.dao.CityDao.getCitiesByCountry")
	Country getCountryId(int countryId);

	/**
	 * <p>
	 * Title: getCountryByName
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param countryName @return
	 */
	@Select("SELECT * FROM m_country WHERE country_name=#{countryName}")
	@ResultMap(value = "cities")
	Country getCountryByName(String countryName);

}
