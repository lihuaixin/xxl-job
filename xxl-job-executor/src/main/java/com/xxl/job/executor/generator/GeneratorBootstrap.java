package com.xxl.job.executor.generator;



public class GeneratorBootstrap {

    public static final String JDBC_URL = "jdbc:mysql://192.168.106.76:3306/taoche?serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
    public static final String USERNAME = "51fanbei";
    public static final String PASSWORD = "Hello1234";

    public static void main(String[] args) {
        String packageName = "com.xxl.job.executor";
        String directoryName = "/xxl-job-executor";

        GeneratorService generatorService = new GeneratorService(packageName, directoryName, JDBC_URL, USERNAME, PASSWORD);
        generatorService
                .outPutController(false)
                .outPutEntity(true)
                .outPutEntityKt(true)
                .outPutMapper(true)
                .outPutService(true)
                .outPutServiceImpl(true)
                .outPutXml(true)
                .outPutEntityQuery(true)
                .setFileOverride(true)
                .setBaseClassPackage("com.xxl.job.executor.base")
                .generateByTables("app_borrow");
    }
}
