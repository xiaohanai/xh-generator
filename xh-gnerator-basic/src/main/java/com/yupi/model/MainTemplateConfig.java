package com.yupi.model;

import lombok.Data;

@Data
public class MainTemplateConfig {

    /**
     * 作者
     */
    private String author = "yupi";

    /**
     * 输出信息
     */
    private String outputTest = "输出结果";

    /**
     * 是否循环开关
     */
    private boolean loop;

}
