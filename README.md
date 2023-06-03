# 论坛系统【后端】

本系统是一个开源的、基于代码的论坛平台，旨在为用户提供一个简单易用的在线讨论和交流的平台。

## 功能特点

- 用户注册和登录：用户可以创建自己的账号并通过登录进行访问。
- 论坛分类与标签：用户可以在不同分类下发表帖子，并且创建自定义标签标记文章。
- 帖子评论：用户可以对帖子进行评论和回复。
- 权限管理：管理员可以对用户文章进行审核，用户可以对文章的评论审核以及删除。
- 论坛搜索：用户可以通过关键词搜索相关主题和帖子。
- 文章管理：用户可以自由增删改自己的文章

## 网站架构

论坛系统的网站架构包含以下组件：

- 前端：用于用户界面和交互的部分，使用Vue+Element-UI构建，采用 HTML、CSS 和 JavaScript 开发(此处不做详细介绍)。
- 后端：
  - 使用 Java作为主要编程语言开发。
  - 采用典型的三层架构，包括表示层、业务逻辑层和数据访问层。
  - 基于Spring Boot和MyBatis Plus开发。
  - 使用RESTful风格编程，用于与前端进行数据交互。
- 数据库：
  - 使用MYSQL用于存储用户、分类、帖子等数据。
  - 使用Redis作为数据缓存，存储验证码等数据。

## 使用到的技术

论坛系统使用到的技术以及框架：

- Spring Boot：作为后端框架，用于处理业务逻辑和提供 RESTful API。
- Spring Boot Starter：用于简化 Spring Boot 项目的配置和依赖管理。
- Spring Boot Test：用于编写和执行单元测试。
- Fastjson：用于处理 JSON 数据的序列化和反序列化。
- Dozer：用于对象之间的映射和转换。
- EasyCaptcha：用于生成和验证验证码。
- SLF4J：简化日志记录的接口。
- Logback：用于日志记录和日志管理。
- Log4j：用于日志记录和日志管理。
- MySQL Connector-J：用于与 MySQL 数据库进行连接和交互。
- PageHelper：用于处理分页查询结果。
- Commons Codec：用于编解码操作。
- Commons Lang：提供了许多通用的工具类。
- Hutool：提供了丰富的 Java 工具类库。
- J2Cache：用于缓存数据。
- P6Spy：用于 SQL 监控和日志记录。
- MyBatis Plus：用于简化 MyBatis 的操作和配置。
- MyBatis Plus Generator：用于生成 MyBatis 代码。
- Swagger：用于生成 API 文档和测试 API。
- JWT：用于用户认证和授权。
- Lombok：用于简化 Java 代码的编写。

## 安装和配置

以下是安装和配置本论坛系统的简要步骤：

1. 克隆代码库到您的本地机器：

```
git clone https://github.com/unravel-gui/forum.git
```

2. 项目使用`maven` 管理依赖，所以请使用`maven`来加载依赖把
3. 配置数据库：
   1. 初始化数据库：创建数据库后使用database目录下带的建表语句即可
   2. 修改配置文件:  在application-dev.yml中修改对应的数据库配置 (mysql、redis)

4. 启动数据库服务 包括mysql、redis

5. 启动服务器

## 技术要求

- Java ==1.8
- MySQL >= 5.7

## 贡献

如果您对本论坛系统有任何建议或改进意见，欢迎您提交 issue 或者直接提出 Pull Request。我们非常欢迎并感谢您的贡献！

## 授权许可

本论坛系统基于 MIT 许可证进行授权。详细信息请参阅 [LICENSE](https://chat.openai.com/LICENSE) 文件。

## 联系我们

如果您对本论坛系统有任何疑问或需要帮助，请通过以下方式联系我们：

- 电子邮件：2450511288i@gmail.com

感谢您选择使用我们的论坛系统！希望它能满足您的需求，如果您在使用过程中遇到任何问题，请随时联系我们。祝您使用愉快！

