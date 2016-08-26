# SpringMVC 与 Json 数据交互
## 1. 加入依赖的 jar 包
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.5.4</version>
</dependency>
```

## 2. springmvc-dispatcherservlet.xml 配置文件中配置处理 Json 的 ViewResolver
```
    <!-- 配置ViewResolver。 
    可以用多个ViewResolver。 使用order属性排序。 InternalResourceViewResolver放在最后。 -->
    <bean class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
        <property name="order" value="1" />
        <property name="defaultViews">
            <list>
                <!-- JSON View -->
                <bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView">
                </bean>
            </list>
        </property>
    </bean>

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
```

## 3. 编写 Controller
```
@Controller
@RequestMapping("/json")
public class JsonController {

	private ItemsMapper itemsMapper;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	public void setItemsMapper(ItemsMapper itemsMapper) {
		this.itemsMapper = itemsMapper;
	}

	/**
	 * 请求 json，响应 json
	 * '@RequestBody'： 将请求的 json 串转成 java 对象
	 * '@ResponseBody'： 将 java 对象转成 json 串进行响应
	 */
	@RequestMapping(value = "/requestJsonAndResponseJson", produces = "application/json")
	public @ResponseBody
	ItemsCustom requestJsonAndResponseJson(
			@RequestBody ItemsCustom itemsCustom) throws Exception {

		Items items = itemsMapper.selectByPrimaryKey(itemsCustom.getId());
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	/**
	 * 请求 key/value，响应 json
	 * '@ResponseBody'： 将 java 对象转成 json 串进行响应
	 */
	@RequestMapping(value = "/requestKeyValueAndResponseJson", produces = "application/json")
	public @ResponseBody
	ItemsCustom requestKeyValueAndResponseJson(ItemsCustom itemsCustom) throws Exception {

		// 其他业务处理
		return itemsCustom;
	}
}
```
## 4. 前端 ajax 请求
```
    <script type="text/javascript">
        function requestJsonAndResponseJson() {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/json/requestJsonAndResponseJson',
                data: '{"id":1,"name":"NikeShoes","price":200}',
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                    var jsonStr = JSON.stringify(data);
                    alert(jsonStr);
                },
                error: function (errorInfo) {
                    alert(errorInfo.toString());
                }
            });
        }
        function requestKeyValueAndResponseJson() {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/json/requestKeyValueAndResponseJson',
                data: 'id=1&name=HongshuangxiPingPong2&price=400',
                success: function (data) {
                    var jsonStr = JSON.stringify(data);
                    alert(jsonStr);
                },
                error: function (errorInfo) {
                    alert(errorInfo.toString());
                }
            });
        }
    </script>
```
