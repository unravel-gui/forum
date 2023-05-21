package com.kemorebi.forum;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @description: 代码生成器
 **/

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class Generator {
/*

    public static void main(String[] args) {
        //获取项目所在路径
        String path = System.getProperty("user.dir");
        String url = "jdbc:mysql://localhost:3306/forum?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
        String username = "forum";
        String password = "123456";



        // 数据库配置(DataSourceConfig)
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password)
                        .dbQuery(new MySqlQuery()))
                // 全局配置(GlobalConfig)
                .globalConfig(builder -> {
                    builder.author("葵gui") // 设置作者
                            .fileOverride()// 覆盖已生成文件
                            .outputDir(path + "/src/main/java")
                            .build(); // 指定输出目录
                })
                // 包配置(PackageConfig)
                .packageConfig(builder -> {
                    builder.parent("com.kemorebi.forum") // 设置父包名
                            .mapper("mapper")
                            .entity("entity")
                            .controller("controller")
                            .service("service")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/src/main/resources/mapper"))
                            .build(); // 设置mapperXml生成路径;
                })
                .strategyConfig((scanner, builder) -> {
                    builder.entityBuilder() // 实体层操作
                            .naming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略.默认下划线转驼峰命名:NamingStrategy.underline_to_camel
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            .enableRemoveIsPrefix() //开启 Boolean 类型字段移除 is 前缀
                            .enableLombok(); //开启 lombok 模型

                    builder.controllerBuilder() // controller层操作
                            .enableRestStyle(); //开启生成@RestController 控制器

                    builder.serviceBuilder().formatServiceFileName("%sService");

					builder.mapperBuilder()
                            .enableBaseResultMap() // 启用 BaseResultMap生成
                            .enableBaseColumnList(); //启用 BaseColumnList
                    builder.addTablePrefix("forum_"); //增加过滤表前缀
                    builder.addInclude(getTables(scanner.apply("kem_aichat_user,kem_aichat_message,kem_aichat_aisetting")))
//                    builder.addInclude(getTables(scanner.apply("all")))
                            .build(); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

 */
}
