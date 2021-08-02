package chapter45.framework;

import chapter45.bean.RateLimiter;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.record.OldFormulaRecord;
import org.apache.poi.ss.formula.functions.T;
import org.dom4j.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述类的信息</p>
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
            Element root = document.getRootElement();
            List<Element> elements = root.elements("bean");
            for (Element element : elements) {
                BeanDefinition beanDefinition = new BeanDefinition();

                Attribute idAttr = element.attribute("id");
                String id = idAttr.getText();
                Attribute classAttr = element.attribute("class");
                String className = classAttr.getText();

                beanDefinition.setClassName(className);
                beanDefinition.setId(id);

                List<BeanDefinition.ConstructorArg> args = beanDefinition.getConstructorArgs();
                List<Element> elementList = element.elements("constructor-arg");
                for (Element element1 : elementList) {

                    BeanDefinition.ConstructorArg constructorArg = new BeanDefinition.ConstructorArg();

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
            Class<?> aClass = Class.forName("chapter45.bean.RateLimiter");
            Object o = aClass.getConstructor().newInstance();
            RateLimiter r =  (RateLimiter)o;
            r.test();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}