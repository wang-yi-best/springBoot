package com.wang.springboot.modules.common.vo;

/**
 * <p>
 * Title: Result
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午2:48:57 @version 1.0
 */
public class Result<T> {// 类部类的写法，里面包含enum类
	/** status 状态码 */
	private int status;
	/** massege 返回信息 */
	private String massege;
	/** object 类型 */
	private T object;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMassege() {
		return massege;
	}

	public void setMassege(String massege) {
		this.massege = massege;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public enum resultEnum {// enum是一个类在书写时不要当成方法resultEnum(而不是"resultEnum()"这种写法)
		SUCCESS(200), FAILD(500);
		public int status;

		private resultEnum(int status) {
			this.status = status;
		}

	}
}
