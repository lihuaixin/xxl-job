package com.xxl.job.executor.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：
 * @author：liqiang
 * @date：2019-06-04 11:03
 * @warning：本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class GeneratorService {

    private String jdbcUrl;
    private String username;
    private String password;
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String AUTHOR = "chenlei";
    public static final String MAPPER_NAME = "%sMapper";
    public static String PATH_SEPARATOR = "\\";
    private String packageName;
    private String directoryName;
    private TemplateConfig templateConfig;
    private boolean outPutEntityQuery = true;
    private String entityQueryPackage = "dao.query";
    private String baseClassPackage = null;
    private boolean fileOverride = false;

    public GeneratorService(String packageName, String directoryName, String jdbcUrl, String userName, String password) {
        this.packageName = packageName;
        this.directoryName = directoryName;
        this.templateConfig = new TemplateConfig();
        this.jdbcUrl = jdbcUrl;
        this.username = userName;
        this.password = password;
    }

    private String getCurrentProjectMainPath() {
        File directory = new File("");
        String rootProjectMain = null;
        try {
            rootProjectMain = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String property = System.getProperties().getProperty("os.name");
        if(property.contains("Mac")){
            PATH_SEPARATOR = "/";
            rootProjectMain = rootProjectMain
                    .concat(PATH_SEPARATOR)
                    .concat(directoryName)
                    .concat("src/main");
        }else{
            rootProjectMain = rootProjectMain
                    .concat(PATH_SEPARATOR)
                    .concat(directoryName)
                    .concat("\\src\\main");
        }

        return rootProjectMain;
    }

    public void generateByTables(boolean serviceNameStartWithI, String... tableNames) {
        String projectMainPath = getCurrentProjectMainPath();

        String rootCodePath = projectMainPath.concat("/java/");
        String rootMapperPath = projectMainPath.concat("/resources/mybatis-mapper/");

        System.out.println(rootCodePath);
        System.out.println(rootMapperPath);

        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setUrl(jdbcUrl)
                .setUsername(username)
                .setPassword(password)
                .setDriverName(DRIVER_NAME);
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setEntityBuilderModel(true)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setLogicDeleteFieldName("is_delete")
                .setInclude(tableNames);
        if (baseClassPackage != null) {
            strategyConfig
                    .setSuperControllerClass(baseClassPackage.concat(".BaseController"))
                    .setSuperEntityClass(baseClassPackage.concat(".BaseEntity"))
                    .setSuperMapperClass(baseClassPackage.concat(".BaseDao"))
                    .setSuperServiceClass(baseClassPackage.concat(".BaseService"))
                    .setSuperEntityColumns("id", "is_delete")
                    .setSuperServiceImplClass(baseClassPackage.concat(".BaseServiceImpl"));
        }

        config.setActiveRecord(false)
                .setEnableCache(false)
                .setMapperName(MAPPER_NAME)
                .setAuthor(AUTHOR)
                .setOutputDir(rootCodePath)
                .setFileOverride(fileOverride)
                .setBaseColumnList(true)
                .setBaseResultMap(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        Map<String, Object> map = new HashMap<>(5);
        map.put("BaseClassPackage", baseClassPackage);
        map.put("RootPackage", packageName);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                this.setMap(map);
            }
        };
        AutoGenerator mpg = new AutoGenerator();
        List<FileOutConfig> focList = new ArrayList<>();
        if (templateConfig.getXml() != null) {
            //调整xml生成目录
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return rootMapperPath.concat(tableInfo.getEntityName()+"Mapper").concat(".xml");
                }
            });
        }
        //生成实体查询对象
        if (outPutEntityQuery) {
            String queryFullPackage = packageName.concat(".").concat(entityQueryPackage);
            map.put("EntityQueryPackage", entityQueryPackage);
            String outPutPath = rootCodePath.concat(queryFullPackage.replace(".", PATH_SEPARATOR)).concat(PATH_SEPARATOR);
            focList.add(new FileOutConfig("/templates/entityQuery.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return outPutPath.concat(tableInfo.getEntityName()).concat("Query.java");
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //关闭默认的xml生成
        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);

        mpg.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("web.controller")
                                .setEntity("dao.domain")
                                .setMapper("dao.mapper")
                                .setService("api.service")
                                .setServiceImpl("api.service.impl")
                                .setXml("mapper")
                ).execute();
    }

    public void generateByTables(String... tableNames) {
        generateByTables(false, tableNames);
    }

    public GeneratorService outPutXml(boolean value) {
        if (!value) {
            this.templateConfig.setXml(null);
        }
        return this;
    }

    public GeneratorService outPutServiceImpl(boolean value) {
        if (!value) {
            this.templateConfig.setServiceImpl(null);
        }
        return this;
    }

    public GeneratorService outPutService(boolean value) {
        if (!value) {
            this.templateConfig.setService(null);
        }
        return this;
    }

    public GeneratorService outPutEntity(boolean value) {
        if (!value) {
            this.templateConfig.setEntity(null);
        }
        return this;
    }

    public GeneratorService outPutMapper(boolean value) {
        if (!value) {
            this.templateConfig.setMapper(null);
        }
        return this;
    }

    public GeneratorService outPutController(boolean value) {
        if (!value) {
            this.templateConfig.setController(null);
        }
        return this;
    }

    public GeneratorService outPutEntityKt(boolean value) {
        if (!value) {
            this.templateConfig.setEntityKt(null);
        }
        return this;
    }

    public GeneratorService outPutEntityQuery(boolean value) {
        outPutEntityQuery = value;
        return this;
    }

    public GeneratorService setBaseClassPackage(String baseClassPackage) {
        this.baseClassPackage = baseClassPackage;
        return this;
    }

    public GeneratorService setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
        return this;
    }
}
