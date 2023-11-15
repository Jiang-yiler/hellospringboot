## Java/Vue全栈项目

### 1. 搭建Java开发环境

> - JDK（Java Development Kit，Java开发工具包）：JRE+开发工具集（Javac，Java编译工具等）
> - JRE（Java Runtime Environment，Java运行环境）：JVM+Java的标准类库
> - JVM（Java Virtual Machine，Java虚拟机）

1. 安装JDK

- JDK8安装包下载地址：[Java Archive | Oracle 中国](https://www.oracle.com/cn/java/technologies/downloads/archive/)

- 配置环境变量：[JDK 安装下载（图文全套方法）_jdk下载_酒酿小小丸子的博客-CSDN博客](https://blog.csdn.net/2201_75362610/article/details/132757390?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2~default~YuanLiJiHua~Position-2-132757390-blog-109993896.235^v38^pc_relevant_sort&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~YuanLiJiHua~Position-2-132757390-blog-109993896.235^v38^pc_relevant_sort&utm_relevant_index=5)

  > 在任何一个目录下都能执行java和javac命令，原先只能在安装目录bin文件夹下执行

2. 安装IntelliJ IDEA

- 安装包下载地址：[其他版本 - IntelliJ IDEA (jetbrains.com)](https://www.jetbrains.com/zh-cn/idea/download/other.html)
- 激活教程：https://mp.weixin.qq.com/s/jqblQYzbdOxwOOFh9-cgTw

3. 安装Maven

>  Maven的作用：自动化构建；依赖管理；统一开发结构

- 安装包下载地址：[Index of /dist/maven/maven-3 (apache.org)](https://archive.apache.org/dist/maven/maven-3/)
- 配置环境变量：[maven安装及配置教程 - 哔哩哔哩 (bilibili.com)](https://www.bilibili.com/read/cv27488592/)
- 配置本地仓库和镜像：仓库服务 (aliyun.com)](https://developer.aliyun.com/mvn/guide)
  - 配置IDEA：覆盖idea默认的Maven目录和`setting.xml`路径

### 2. 创建第一个SpringBoot项目

> SSM：Spring + Spring MVC + Mybatis
>
> SpringBoot的特点：

- 使用SpringBoot和Maven创建第一个Java项目

  ![image-20231115232150774](C:\Users\ChenH\AppData\Roaming\Typora\typora-user-images\image-20231115232150774.png)

  ![image-20231115232840802](C:\Users\ChenH\AppData\Roaming\Typora\typora-user-images\image-20231115232840802.png)

  > 遇到的问题及解决方法：
  >
  > [IDEA中提示：Warning:java: 源值1.5已过时, 将在未来所有发行版中删除-CSDN博客](https://blog.csdn.net/Stripeybaby/article/details/80010734)
  >
  > [Initialization failed for 'https://start.spring.io' Please check URL, network and proxy settings解决办法_initialization failed怎么解决-CSDN博客](https://blog.csdn.net/qq_42815122/article/details/85551956)

- 配置热更新

  1. 添加依赖：在`pom.xml`文件中添加spring-boot-devtools依赖，在`application.properties`中启用插件
  
     ```pom.xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-devtools</artifactId>
         <optional>true</optional>
     </dependency>
     ```
  
  2. 配置项目自动编译：Setting ->Build, Execution. Deploymeny -> Complier勾选Build project automatically

### 3. 使用SpringBoot

#### 3.1 处理HTTP请求

> [Spring之RequestBody的使用姿势小结 - 掘金 (juejin.cn)](https://juejin.cn/post/6844903648858734600#heading-0)

- `@Controller`和`@RequestController`负责接收和处理HTTP请求。前者请求页面和数据；后者只请求数据，默认会转为JSON格式

  | 注解                                                         | 功能                                                         |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  |                                                              |                                                              |
  | [@RequestMapping](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-requestmapping.html) | 将路由映射到控制器的方法                                     |
  | [@RequestParam](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestparam.html) | 将请求参数映射到控制器方法的参数，Content-Type通常为application/x-www-form-urlencoded |
  | [@RequestBody](https://serinryu.medium.com/spring-requestbody-vs-requestparam-78b1b433fc0c) | 接收请求体的数据，Content-Type通常为application/json         |

- 接口测试

  - IDEA 自带的[HTTP Client](https://bbs.huaweicloud.com/blogs/268840)
  - 客户端（Postman/Apifox）
  - 命令行（curl）

#### 3.2 访问静态资源

- 默认映射到`resources/static`下的文件
- 自定义映射路径：配置`spring.mvc.static-path-pattern`

#### 3.3 处理文件上传

- 浏览器端：`Content-Type`必须为`multipart/form-data`；传过来的字段必须与接收的字段一致
- 服务器端：使用`MultipartFile`和`HttpServletRequest`接收文件，并存储到Web服务器
- 通过浏览器访问上传的文件：配置`spring.web.resources.static-locations`来映射到Web服务器的地址

#### 3.4 拦截器

对于全局统一的操作，可以提取到拦截器中实现，常见使用场景有权限检查、读取cookie、性能监控等

![image-20231115225034527](C:\Users\ChenH\AppData\Roaming\Typora\typora-user-images\image-20231115225034527.png)

- 创建拦截器：使用`HandlerInterceptor`接口
- 注册拦截器：使用`WebMvcConfigurer`定义拦截路径

### 4. 接口

#### 4.1 RESTful

RESTful是**定义接口的一种规范**，该规范要求接口：

- 安全性：安全的方法不会产生任何副作用。比如只用于获取资源的GET请求不会改变资源、也不会改变服务器的状态
- 幂等性：重复进行一次请求和一次请求的效果相同。并不是指相应总是相同，而是指服务器上资源的状态不再改变

HTTP 方法和状态码

- HTTP提供POST、GET、PUT、DELETE等操作类型来对Web资源进行Create、Read、Update、Delete等

- 状态码分为以下五类

  | 错误码 | 含义       |
  | ------ | ---------- |
  | 1xx    | 信息       |
  | 2xx    | 成功       |
  | 3xx    | 重定向     |
  | 4xx    | 客户端错误 |
  | 5xx    | 服务器错误 |

#### 4.2 Swagger

Swagger是一种API表达工具，可用于调试接口、生成Restful风格的接口文档。

在SpringBoot中集成Swagger的步骤：

1. 添加依赖

   ```xml
   <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger2</artifactId>
       <version>2.9.2</version>
   </dependency>
   
   <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger-ui</artifactId>
       <version>2.9.2</version>
   </dependency>
   ```

2. 添加配置

   > SpringBoot 2.6.X后与Swagger有版本冲突，需要添加如下配置

   ```properties
   spring.mvc.pathmatch.matching-strategy=ant_path_matcher
   ```

3. 创建配置类

   ```java
   @Configuration // 声明配置类
   @EnableSwagger2 // 启用Swagger2
   public class SwaggerConfig {
       /* 配置Swagger2 相关的bean */
       @Bean
       public Docket createRestApi() {
           return new Docket(DocumentationType.SWAGGER_2)
                   .apiInfo(apiInfo())
                   .select()
                   .apis(RequestHandlerSelectors.basePackage("com"))
                   .paths(PathSelectors.any()).build();
       }
   
       /* API文档页面显示相关的信息 */
       private ApiInfo apiInfo() {
           return new ApiInfoBuilder()
                   .title("这是标题")
                   .description("这是描述")
                   .version("这是版本")
                   .build();
       }
   }
   ```

4. 启动项目后，访问`http://xxxx:xxx/swagger-ui.html`
