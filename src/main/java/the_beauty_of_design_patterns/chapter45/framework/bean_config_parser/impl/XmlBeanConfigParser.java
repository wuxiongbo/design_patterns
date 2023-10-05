package the_beauty_of_design_patterns.chapter45.framework.bean_config_parser.impl;

import the_beauty_of_design_patterns.chapter45.bean.RateLimiter;
import the_beauty_of_design_patterns.chapter45.framework.BeanDefinition;
import the_beauty_of_design_patterns.chapter45.framework.bean_config_parser.BeanConfigParser;
import org.apache.commons.io.IOUtils;
import org.dom4j.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 配置文件解析类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public class XmlBeanConfigParser implements BeanConfigParser {

    /**
     * 将配置文件解析成 bean定义
     * @param inputStream
     * @return
     */
    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        String content = null;

        try {
            content = IOUtils.toString(inputStream, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parse(content);
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        // TODO: 解析xml格式的字符串，转为 bean定义。 简版
        try {

            Document document = DocumentHelper.parseText(configContent);

            //第一层标签
            Element root = document.getRootElement();

            //第二层标签
            List<Element> elements = root.elements("bean");
            for (Element element : elements) {
                BeanDefinition beanDefinition = new BeanDefinition();

                // 标签属性
                Attribute idAttr = element.attribute("id");
                String id = idAttr.getText();
                Attribute classAttr = element.attribute("class");
                String className = classAttr.getText();

                beanDefinition.setClassName(className);
                beanDefinition.setId(id);


                List<BeanDefinition.ConstructorArg> args = beanDefinition.getConstructorArgs();
                //第三层标签
                List<Element> elementList = element.elements("constructor-arg");
                for (Element element1 : elementList) {

                    BeanDefinition.ConstructorArg constructorArg = new BeanDefinition.ConstructorArg();

                    // 标签属性
                    Attribute typeAttr = element1.attribute("type");
                    String type = null;
                    if(typeAttr!=null){
                        type = typeAttr.getText();
                        constructorArg.setType(convert(type));
                    }
                    Attribute valueAttr = element1.attribute("value");
                    if(valueAttr!=null){
                        String valueStr = valueAttr.getText();
                        Object value = convertValue(valueStr,type);
                        constructorArg.setArg(value);
                    }
                    Attribute refAttr = element1.attribute("ref");
                    if(refAttr!=null){
                        String refId = refAttr.getText();
                        constructorArg.setArg(refId);
                        constructorArg.setIsRef(true);
                    }

                    args.add(constructorArg);
                }

                beanDefinitions.add(beanDefinition);

            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return beanDefinitions;
    }

    public Class<?> convert(String typeName){
        Class<?> clazz = null;
        switch (typeName){
            case "String":
                clazz = String.class;
                break;

            case "int":
                clazz = int.class;
                break;
            default:
                return clazz;
        }
        return clazz;
    }

    public Object convertValue(String value,String type){
        Object target = null;
        switch (type){
            case "String":
                target = value;
                break;
            case "int":
                target = Integer.parseInt(value);
                break;
            default:
        }
        return target;
    }


    public static void main(String[] args){

        try(InputStream in = XmlBeanConfigParser.class.getClassLoader().getResourceAsStream("beans.xml")) {
            if (in == null) {
                throw new RuntimeException("Can not find config file: " + "beans.xml");
            }
            XmlBeanConfigParser xmlBeanConfigParser = new XmlBeanConfigParser();
            List<BeanDefinition> beanDefinitions = xmlBeanConfigParser.parse(in);

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Class<?> aClass = Class.forName("the_beauty_of_design_patterns.chapter45.bean.RateLimiter");
            Object o = aClass.getConstructor().newInstance();
            RateLimiter r =  (RateLimiter)o;
            r.test();
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}