import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {

    @Test
    public void testFreeMarker() throws IOException, TemplateException {
        // Create your Configuration instance, and specify if up to what FreeMarker
// version (here 2.3.22) do you want to apply the fixes that are not 100%
// backward-compatible. See the Configuration JavaDoc for details.
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);

// Specify the source where the template files come from. Here I set a
// plain directory for it, but non-file-system sources are possible too:
        configuration.setDirectoryForTemplateLoading(new File("src\\main\\resources\\templates"));

        configuration.setNumberFormat("0.######");

// Set the preferred charset template files are stored in. UTF-8 is
// a good choice in most applications:
        configuration.setDefaultEncoding("UTF-8");

        Template template = configuration.getTemplate("mywed.html.ftl");

// Sets how errors will appear.
// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //数据模型

        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("currentYear",2023);
        List<Map<String,Object>> menuItems = new ArrayList<>();
        Map<String,Object> menuItem1 = new HashMap<>();
        menuItem1.put("url","https://codefather.cn");
        menuItem1.put("label","编程导航");
        Map<String,Object> menuItem2 = new HashMap<>();
        menuItem2.put("url","https://laoyujianli.com");
        menuItem2.put("label","老鱼简历");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems",menuItems);

        Writer out = new FileWriter("mywed.html");
        //合并数据模型和模板
        template.process(dataModel,out);

        out.close();
    }
}
