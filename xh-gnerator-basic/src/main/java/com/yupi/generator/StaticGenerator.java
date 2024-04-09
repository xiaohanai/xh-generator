package com.yupi.generator;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
        public static void main(String[] args) {
            //获取文件根目录
            String projectpath = System.getProperty("user.dir");
            //输入路径
            String inputPath =projectpath + File.separator + "xh-generator-demo-projects" + File.separator + "acm-template";
            System.out.println(inputPath);
            //输出路径
            String outputPath = projectpath;
            copyFilesByHutool(inputPath, outputPath);

        }

    /**
     *拷贝文件
     * @param inputPanth 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPanth, String outputPath){
        FileUtil.copy(inputPanth, outputPath, false);
    }


    public static void copyFilesByRecursive
            (String inputPanth, String outputPath){
        File inputFile = new File(inputPanth);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e){
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }

    public static void copyFileByRecursive(File inputFile, File outputFile) throws Exception{
        if(inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            if(!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            //获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();

            if(ArrayUtil.isEmpty(files)){
                return;
            }

            for(File file : files){
                copyFileByRecursive(file, destOutputFile);
            }

        } else {
          //是文件，直接复制到目标目录下
            Path destPanth = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPanth, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
