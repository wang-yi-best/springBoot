package com.wang.springboot.modules.test.contoller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wang.springboot.modules.test.vo.MyConfigBean;


/**
 * <p>
 * Title: TestController
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午3:10:52 @version 1.0
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	// 全局配置文件
	@Value("${server.port}")
	private int port;
	@Value("${com.hqyj.name}")
	private String name;
	@Value("${com.hqyj.age}")
	private String age;
	@Value("${com.hqyj.desc}")
	private String desc;
	@Value("${com.hqyj.random}")
	private String random;

	@Autowired
	private MyConfigBean myConfigBean;

	/**
	 * http://localhost/test/log
	 * 
	 * @return
	 */
	// 日志的五种级别trace<debug<info<warn<error
	@RequestMapping("/test/log")
	// @ResponseBody
	public String logTest() {
		LOGGER.trace("this is trace log");
		LOGGER.debug("this is debug log");
		LOGGER.info("this is info log");
		LOGGER.warn("this is warn log");
		LOGGER.error("this is error log");
		return "this is my logger test";
	}

	/**
	 * http://localhost/test/config
	 * 
	 * @return
	 */
	@RequestMapping("/test/config")
	// @ResponseBody
	public String confgTest() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(port).append("------------").append(name).append("------------").append(age)
				.append("------------").append(desc).append("------------").append(random).append("------------")
				.append("</br>");

		stringBuffer.append(myConfigBean.getName()).append("------------").append(myConfigBean.getAge())
				.append("------------").append(myConfigBean.getDesc()).append("------------")
				.append(myConfigBean.getRandom()).append("------------");
		return stringBuffer.toString();
	}

	/**
	 * http://127.0.0.1/api/test/appDesc?key=fuck
	 * http://localhost/api/test/appDsc?key=
	 * 
	 * @return
	 */
	@RequestMapping("/test/appDsc")
	// @ResponseBody //该注解表示接口
	public String GetAppDsc(HttpServletRequest request, @RequestParam String key) {
		String value = request.getParameter("key");
		return "hello,this is my first SpringBoot project" + key + value;
	}

	@RequestMapping("/downLoad")
	public ResponseEntity<Resource> downLoadFile(@RequestParam String fileName) {
		try {
			Resource resource = new UrlResource(Paths.get("D:\\file\\download\\" + fileName).toUri());
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION,
							String.format("attachment; filename=\"%s\"", resource.getFilename()))
					.body(resource);// 设置传输头，传输体的信息及格式
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * （以流的方式下载） 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int) downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileName));

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int) downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
						String.format("attachment; filename=\"%s\"", fileName));

				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}
}
