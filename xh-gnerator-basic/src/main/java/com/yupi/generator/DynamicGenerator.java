package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {

        String projectPath = System.getProperty("user.dir") + File.separator + "xh-gnerator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";

        //数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setOutputTest("输出结果");
        mainTemplateConfig.setLoop(false);

        doGnerate(inputPath , outputPath ,mainTemplateConfig);
    }

    /**
     *生成文件
     *  @param inputPath 模板文件输入路径
     *  @param outputPath 输出路径
     *  @param model 数据模型
     *  @throws IOException
     *  @throws TemplateException
     */
    public static void doGnerate(String inputPath,String outputPath,Object model) throws IOException, TemplateException {
// new 出 Configuration 对象 ,参数为 FreeMarker 的版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

// 设置默认的字符集，防止乱码，默认为UTF-8
        configuration.setDefaultEncoding("UTF-8");
        configuration.setNumberFormat("0.######");

        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        Writer out = new FileWriter(outputPath);
        //合并数据模型和模板
        template.process(model,out);

        out.close();
    }
}
