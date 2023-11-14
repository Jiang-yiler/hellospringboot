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

  > [IDEA中提示：Warning:java: 源值1.5已过时, 将在未来所有发行版中删除-CSDN博客](https://blog.csdn.net/Stripeybaby/article/details/80010734)

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

#### 处理HTTP请求

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

#### 访问静态资源

- 默认映射到`resources/static`下的文件
- 自定义映射路径：配置`spring.mvc.static-path-pattern`

#### 处理文件上传

- 浏览器端：`Content-Type`必须为`multipart/form-data`；传过来的字段必须与接收的字段一致
- 服务器端：使用`MultipartFile`和`HttpServletRequest`接收文件，并存储到Web服务器
- 通过浏览器访问上传的文件：配置`spring.web.resources.static-locations`来映射到Web服务器的地址

#### 拦截器