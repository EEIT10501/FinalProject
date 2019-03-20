package com.funwork.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.funwork.viewResolver.ExcelViewResolver;
import com.funwork.viewResolver.JsonViewResolver;
import com.funwork.viewResolver.PdfViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.funwork")
public class WebAppConfig extends WebMvcConfigurerAdapter {


	/**
	 * ViewResolver for jsp.
	 */
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
		registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/views/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/schedule/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/schedule/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/views/images/workimages/");
		registry.addResourceHandler("/DataTables/**").addResourceLocations("/WEB-INF/views/DataTables/datatables/");
		registry.addResourceHandler("/datatableimages/**")
				.addResourceLocations("/WEB-INF/views/DataTables/datatableimages/");
	}

	/**
	 * For globalization.
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}

	/**
	 * For upload file. MaxUploadSize定義上傳檔案限制的大小.
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(81920000);
		return resolver;
	}

	/**
	 * Json view bean, 提供內容協商視圖解析器用.
	 */
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		return view;
	}

	/**
	 * For Json、PDF、XML、Excel等視圖解析.
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager,ServletContext context) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jsonViewResolver());
		resolvers.add(excelViewResolver());
		resolvers.add(pdfViewResolver(context));
		resolvers.add(internalResourceViewResolver());
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	@Bean
	public ViewResolver excelViewResolver() {
		System.out.println("excelViewResolver");
		return new ExcelViewResolver();
	}

	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	@Bean
	public ViewResolver pdfViewResolver(ServletContext context) {
		return new PdfViewResolver(context);
	}

	/**
	 * Mail Bean.
	 */
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.starttls.required", true);
		properties.put("mail.smtp.socketFactory.port", 465);
		properties.put("mail.smtp.debug", true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", false);
		javaMailSenderImpl.setJavaMailProperties(properties);
		javaMailSenderImpl.setJavaMailProperties(properties);
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(465);
		javaMailSenderImpl.setProtocol("smtp");
		javaMailSenderImpl.setUsername("sam810331");
		javaMailSenderImpl.setPassword("mino810125");
		return javaMailSenderImpl;
	}
}