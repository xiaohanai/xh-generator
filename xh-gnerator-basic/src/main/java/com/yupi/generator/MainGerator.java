package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;


public class MainGerator {

    public static void main(String[] args) throws TemplateException, IOException {
        //1.静态文件生成
        //获取文件根目录
        String projectpath = System.getProperty("user.dir");
        //输入路径
        String inputPath =projectpath + File.separator + "xh-generator-demo-projects" + File.separator + "acm-template";
        System.out.println(inputPath);
        //输出路径
        String outputPath = projectpath;
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        //2.动态文件生成
        String dynamicinputPath = projectpath + File.separator + "xh-gnerator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicoutputPath = projectpath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";

        //数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setOutputTest("输出结果");
        mainTemplateConfig.setLoop(false);

        DynamicGenerator.doGnerate(dynamicinputPath , dynamicoutputPath ,mainTemplateConfig);
    }
}
