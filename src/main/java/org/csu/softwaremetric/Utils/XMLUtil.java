package org.csu.softwaremetric.Utils;

import org.csu.softwaremetric.entity.*;
import org.dom4j.Attribute;
import org.dom4j.Document;

import org.dom4j.Element;

import org.dom4j.io.SAXReader;



import java.util.List;

public class XMLUtil {

    private static Classes targetClass;
    private static Association association;    //存储正在解析的关联
    private static Dependency dep;    //存储正在解析的依赖
    private static org.csu.softwaremetric.entity.Attribute att;    // 存储正在解析的属性
    private static Operation ope;    // 存储正在解析的方法


    public Document load(String filename) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(filename); //读取xml文件，获得document对象
            System.out.println("读取成功");
        } catch (Exception e) {
            System.out.println("读取失败");
            e.printStackTrace();
        }
        return document;
    }

//    public String UmlToXml(String filename) {
//        //存储XML文档中所有元素ID
//        List<String> List_id = new ArrayList<String>();
//        //存储XML文档中对应ID元素的name
//        List<String> List_name = new ArrayList<String>();
//        //存储XML文档中所有的继承关系
//
//        //1.读取XML文件
//        System.out.println("读取.xml文件");
//        Document document = load(filename);
//
//        //2.获取文档根结点
//        Element rootElem = document.getRootElement();
//        // String value = rootElem.attributeValue("id");
//        //System.out.println("根结点ID："  + value);
//
//        //3.获取文档中所有packageElement即文档元素的ID和Name
//        List nodes = rootElem.elements("packagedElement");
//        for (Iterator it = nodes.iterator(); it.hasNext();) {
//            Element elm = (Element) it.next();
//            String id = elm.attributeValue("id");
//            String name = elm.attributeValue("name");
//            List_id.add(id);
//            List_name.add(name);
//        }
//
//        int count = 0;
//
//        try{
//            // 1、创建document对象
//            Document newXML = DocumentHelper.createDocument();
//            // 2、创建根节点temp
//            Element temp = newXML.addElement("rss");
//            // 3、向rss节点添加version属性
//            temp.addAttribute("version", "2.0");
//
//            for (Iterator it = nodes.iterator(); it.hasNext();) {
//                Element elm = (Element) it.next();
//                String elm_type = elm.attributeValue("type");      //type = uml:...
//                elm_type = elm_type.substring(4, elm_type.length());  //type : class/interface/realization/dependency
//                //如果是数据类型，则跳过本次循环
//                //System.out.println(elm_type);
//                if (elm_type.equals("PrimitiveType")) {
//                    continue;
//                }
//                String elm_id = elm.attributeValue("id");          //elm在xml文档中的编号
//                String elm_name = elm.attributeValue("name");      //elm在xml文档中的名字
//                String obj_count = Integer.toString(count);           //elm在存储xml文档中的编号
//                count++;
//                Element c = temp.addElement(elm_type);
//                c.addAttribute("Id", obj_count);
//                Element obj_name = c.addElement("objectName");
//                obj_name.setText(elm_name);
//                Element obj_id = c.addElement("objectId");
//                obj_id.setText(elm_id);
//                //如果是class or interface，则存在属性、方法
//                if (elm_type.equals("Class") || elm_type.equals("Interface")) {
//                    //System.out.println("类或接口：" + elm_type);
//                    //System.out.println(elm_name);
//
//                    //1. 属性
//                    List nodes_attr = elm.elements("ownedAttribute");
//                    //如果该类存在属性，则写入<attributes>...<attribute>
//                    if (nodes_attr.size() > 0) {
//                        Element attrs = c.addElement("attributes");
//                        for (Iterator it_attr = nodes_attr.iterator(); it_attr.hasNext();){
//                            Element attr = (Element) it_attr.next();
//                            Element attribute = attrs.addElement("attribute");
//
//                            //属性名
//                            Element attr_name = attribute.addElement("name");
//                            attr_name.setText(attr.attributeValue("name"));
//
//                            //属性类型
//                            String attr_type_id = attr.attributeValue("type");      //获取属性类型在xml中的ID
//                            //将ID转化为类型名称
//                            int tmp_id = List_id.indexOf(attr_type_id);
//
//                            String attr_type_name = List_name.get(tmp_id);
//                            Element attr_type = attribute.addElement("type");
//                            attr_type.setText(attr_type_name);
//
//                            //属性可见性
//                            Element attr_visibility = attribute.addElement("visibility");
//                            attr_visibility.setText(attr.attributeValue("visibility"));
//                        }
//                    }
//
//                    //2. 方法
//                    List nodes_method = elm.elements("ownedOperation");
//                    //如果该类存在方法，则写入<methods>...</methods>
//                    if (nodes_method.size() > 0) {
//                        Element methods = c.addElement("methods");
//                        for (Iterator it_method = nodes_method.iterator(); it_method.hasNext(); ) {
//                            Element mtd = (Element)it_method.next();
//                            Element method = methods.addElement("method");
//
//                            //方法名
//                            Element method_name = method.addElement("name");
//                            method_name.setText(mtd.attributeValue("name"));
//
//                            //方法可见性
//                            Element method_visibility = method.addElement("visibility");
//                            method_visibility.setText(mtd.attributeValue("visibility"));
//
//                            //方法参数和方法返回值类型
//                            List nodes_method_attr = mtd.elements("ownedParameter");
//                            if (nodes_method_attr.size() > 0) {
//                                Element method_attrs = method.addElement("attributes");
//                                for (Iterator it_method_attr = nodes_method_attr.iterator(); it_method_attr.hasNext(); ) {
//                                    Element mtd_attr = (Element) it_method_attr.next();
//                                    //System.out.println("返回值：" +mtd_attr.attributeValue("direction"));
//                                    //不是返回值属性
//                                    if (mtd_attr.attributeValue("direction") == null) {
//                                        //增加<attribute>...</attribute>标签
//                                        //System.out.println(mtd_attr.attributeValue("name"));
//                                        Element mtd_attribute = method_attrs.addElement("attribute");
//
//                                        //参数名<name>...</name>
//                                        Element mtd_attr_name = mtd_attribute.addElement("name");
//                                        mtd_attr_name.setText(mtd_attr.attributeValue("name"));
//
//                                        //类型<type>...</type>
//                                        String mtd_attr_type_id = mtd_attr.attributeValue("type");
//                                        //将ID转换为类型名称
//                                        int tmp_id = List_id.indexOf(mtd_attr_type_id);
//                                        String mtd_attr_type_name = List_name.get(tmp_id);
//                                        Element mtd_attr_type = mtd_attribute.addElement("type");
//                                        mtd_attr_type.setText(mtd_attr_type_name);
//                                    }
//                                    else {
//                                        //返回值类型<returnType>...</returnType>
//                                        Element mtd_returnType = method.addElement("returnType");
//                                        int tmp_id = List_id.indexOf(mtd_attr.attributeValue("type"));
//                                        String mtd_return_type_name = List_name.get(tmp_id);
//                                        mtd_returnType.setText(mtd_return_type_name);
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    //generation
//                    Element generalization = elm.element("generalization");
//                    if (generalization != null) {
//                        Element general = c.addElement("general");
//
//                        //System.out.println(elm_name + " " +generalization.attributeValue("general"));
//                        int tmp_id = List_id.indexOf(generalization.attributeValue("general"));
//                        String tmp_general = List_name.get(tmp_id);
//                        general.setText(tmp_general);
//                    }
//                }
//                //如果是Association
//                else if (elm_type.equals("Association")) {
//                    //System.out.println("关联关系：" + elm_type);
//                    //System.out.println(elm_name);
//
//                    //association: <association>...</association>
//                    Element association = c.addElement("association");
//                    List nodes_property = elm.elements("ownedEnd");
//                    int tmp_flag = 0;
//                    for (Iterator it_association = nodes_property.iterator(); it_association.hasNext(); ) {
//                        Element asso_property = (Element) it_association.next();
//                        int tmp_id = List_id.indexOf(asso_property.attributeValue("type"));
//                        String tmp_property = List_name.get(tmp_id);
//                        Element property = association.addElement("property");
//                        property.setText(tmp_property);
//                        if (tmp_flag == 1) {
//                            Element aggregation = association.addElement("aggregation");
//                            if (asso_property.attributeValue("aggregation") != null) {
//                                aggregation.setText(asso_property.attributeValue("aggregation"));
//                            }
//                            else{
//                                aggregation.setText("association");
//                            }
//                        }
//                        tmp_flag ++;
//                    }
//                }
//                //如果是Dependency or Realization
//                else {
//                    //System.out.println("依赖/实现/继承:" + elm_type);
//                    //System.out.println(elm_name);
//
//                    //supplier: <supplier>...</supplier>
//                    Element supplier = c.addElement("supplier");
//                    //System.out.println(elm.attributeValue("supplier"));
//                    int tmp_id = List_id.indexOf(elm.attributeValue("supplier"));
//                    String supplier_name = List_name.get(tmp_id);
//                    supplier.setText(supplier_name);
//
//                    //client: <client>...</client>
//                    Element client = c.addElement("client");
//                    //System.out.println(elm.attributeValue("client"));
//                    tmp_id = List_id.indexOf(elm.attributeValue("client"));
//                    String client_name = List_name.get(tmp_id);
//                    client.setText(client_name);
//                }
//            }
//
//            // 5、设置生成xml的格式
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            // 设置编码格式
//            format.setEncoding("UTF-8");
//
//
//            // 6、生成xml文件
//            //获取当前时间进行命名
//            //获取当前时间
//            SimpleDateFormat df = new SimpleDateFormat("yyyyMMDDmmss");
//            String tmp_fileName = df.format(new Date());
//            String fileName = tmp_fileName + ".xml";
//
//            File file = new File(fileName);
//
//            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
//            // 设置是否转义，默认使用转义字符
//            //writer.setEscapeText(false);
//            writer.write(newXML);
//            writer.close();
//            System.out.println("生成"+ fileName + "成功");
//            return fileName;
//        } catch (Exception ex){
//            ex.printStackTrace();
//            System.out.println("生成XML文件失败");
//        }
//        return "false";
//    }

    public static String getType(Element element){
        Attribute typeAttribute = element.attribute("type");
        String typeValue = typeAttribute.getValue();
        return typeValue;
    }
    public static String getName(Element element){
        Attribute nameAttribute = element.attribute("name");
        String nameValue = nameAttribute.getValue();
        return nameValue;
    }
    public static String getID(Element element){
        Attribute idAttribute = element.attribute("id");
        String idValue = idAttribute.getValue();
        return idValue;
    }

    public static String getVisibility(Element element){
        Attribute visibilityAttribute = element.attribute("visibility");
        String visibilityValue = visibilityAttribute.getValue();
        return visibilityValue;
    }

    public static String getGeneral(Element element){
        Attribute generalAttribute = element.attribute("general");
        String generalValue = generalAttribute.getValue();
        return generalValue;
    }
    public static void analysisXML(Element element, List<Classes> classList, List<Association> associationList, List<Dependency> dependencyList){
//        System.out.println("--------Node: "+element.getName()+"--------");
        if(element.getName().equals("packagedElement")){    // 判断节点是否为类
            if(getType(element).equals("uml:Class")){
                targetClass = new Classes();
                targetClass.setName(getName(element));
                targetClass.setId(getID(element));
                classList.add(targetClass);
            }else if(getType(element).equals("uml:Association")){   // 判断节点是否存在关联关系
                association = new Association();
                associationList.add(association);
            }else if(getType(element).equals("uml:Dependency")){    // 判断节点是否存在依赖关系
                dep = new Dependency();
                dependencyList.add(dep);
            }


        }else if (element.getName().equals("ownedAttribute")) {     // 判断节点是否为属性
            att = new org.csu.softwaremetric.entity.Attribute();
            att.setName(getName(element));
            att.setVisibility(getVisibility(element));
            targetClass.addAttribute(att);

        } else if (element.getName().equals("ownedOperation")) {    // 判断节点是否为方法
            ope = new Operation();
            ope.setName(getName(element));
            ope.setVisibility(getVisibility(element));
            targetClass.addOperation(ope);

        } else if(element.getName().equals("generalization")){
            targetClass.setGeneralization(getGeneral(element));

        }
    }

    public void clean(List<Classes> list1){
        for (int i = 0; i < list1.size(); i++) {
            Classes s = list1.get(i);
            for(int j = 0; j < list1.size(); j++){
                Classes tempClass = list1.get(j);
                if(tempClass.getId().equals(s.getGeneralization())){
                    list1.get(j).addChildren(s);
                    list1.get(i).setFather(tempClass);
                }
            }
        }
    }
}
