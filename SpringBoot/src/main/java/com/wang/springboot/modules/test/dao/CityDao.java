package com.wang.springboot.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wang.springboot.modules.common.vo.Result;
import com.wang.springboot.modules.test.entity.City;


/**
 * <p>
 * Title: CityDao
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午1:50:45 @version 1.0
 */
@Repository
@Mapper
public interface CityDao {

	/**
	 * <p>
	 * Title: getCitiesByCountry
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param countryId @return
	 */
	@Select("SELECT * FROM m_city WHERE m_city.country_id=#{countryId}")
	List<City> getCitiesByCountry(int countryId);
	// List<City> getCitiesByCountry(int countryId);

	/**
	 * <p>
	 * Title: insertCity
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param city @return
	 */
	@Insert("(city_name, local_city_name, country_id, date_created) "
			+ "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
	@Options(useGeneratedKeys = true, keyColumn = "country_id", keyProperty = "countryId") // 选择生成主键及对应的主键名
	Result<City> insertCity(City city);

	/**
	 * <p>
	 * Title: getCityByCityName
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param cityName @param localCityName @return
	 */
	@Select("SELECT * FROM m_city WHERE city_name = #{cityName} AND local_city_name = #{localCityName}")
	List<City> getCityByCityName(@Param("cityName") String cityName, @Param("localCityName") String localCityName);

	/**
	 * <p>
	 * Title: updateCity
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param city @return
	 */
	Result<City> updateCity(City city);

	/**
	 * <p>
	 * Title: deleteCity
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * @param cityId @return
	 */
	@Delete("DELETE FROM m_city WHERE city_id = #{cityId}")
	Result<Object> deleteCity(int cityId);
}
