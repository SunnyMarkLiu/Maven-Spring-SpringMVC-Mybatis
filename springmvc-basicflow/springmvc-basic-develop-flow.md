# SpringMVC Basic Develop Flow
## 1. web.xml中配置前端控制器 DispatcherServlet
```
    <servlet>
        <servlet-name>springmvc-dispatcherservlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <!-- contextConfigLocation 配置springmvc加载的配置文件，用于配置（处理器映射器、处理器等） -->
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/config/springmvc-dispatcherservlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc-dispatcherservlet</servlet-name>
        <!-- url-pattern 的三种配置方式：
        第一种：*.action ： 访问.action结尾的资源时，由 DispatcherServlet 解析
        第二种：/        ： 访问所有资源都由 DispatcherServlet 解析，对于html、css、js、image等静态资源，需要配置不让 DispatcherServlet 解析
        第三种：/*       ： 注意这种方式错误，因为使用这种配置方式，最终咬转发到一个jsp页面时，仍然会由 DispatcherServlet 解析 jsp 地址，
                          不能根据jsp页面找到处理的handler，会报错。
         -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```
## 2. 配置处理器映射器和处理器适配器
在 SpringMVC 上下文配置文件 springmvc-dispatcherservlet.xml 中配置处理器映射器和处理器适配器
```
    <!-- 配置处理器适配器 -->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
    <!-- 配置处理器映射器：BeanNameUrlHandlerMapping 表示控制器handler的bean名陈与请求url的映射 -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>

```
## 3. 开发处理器 Handler
开发的 Handler 需要遵循配置的 HandlerAdapter 的规则，查看源代码可知 Handler 需要实现 COntroller 接口：
```
public class SimpleControllerHandlerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof Controller);
	}
    ...
}
```
开发针对该请求的 controller：
```
/**
 * 处理器适配值调用该 Handler，用于设置模型数据跳转视图
 * Author: markliu
 * Time  : 16-8-3 下午2:19
 */
public class ItemsHandlerController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ...
        return modelAndView;
    }
}

```
## 4. 配置处理器 Handler
在 SpringMVC 上下文配置文件 springmvc-dispatcherservlet.xml 中配置该 Handler 与请求url 的映射
```
    <!-- 配置 Handler 与请求 url 的映射 -->
    <bean name="/query_items.action" class="com.markliu.springmvc_basicflow.controller.ItemsHandlerController" />
```
## 5. 配置视图解析器 ViewResolver
配置需要解析 jsp 页面的视图解析器
```
    <!-- 配置视图解析器 ViewResolver -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>
```
## 6. 开发视图 View
开发 jsp 等视图页面
