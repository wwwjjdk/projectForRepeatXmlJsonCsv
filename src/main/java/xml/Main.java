package xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        createFileXML();
        //readFromXmlFile();
    }

    public static void readFromXmlFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();//нужно с этого момента вносить под трай кетч
        Document doc = db.parse(new File("nodes.xml"));
        Node root = doc.getDocumentElement();
        System.out.printf("root element %s\n",root.getNodeName());
        read(root);
        // TODO: 21.03.2024 почему узлы не выводятся 

    }

    public static void read(Node node){
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            var node_ = nodeList.item(i);
            if(Node.ELEMENT_NODE == node_.getNodeType()){
                System.out.println("the flowing node = "+ node_.getNodeName());
                Element element = (Element) node_;
                NamedNodeMap namedNodeMap = element.getAttributes();
                for (int j = 0; j < namedNodeMap.getLength(); j++) {
                    var value = namedNodeMap.item(j).getNodeValue();
                    var name = namedNodeMap.item(j).getNodeName();
                    System.out.printf("value: %s, name: %s \n",value,name);
                }
                read(node_);
            }
        }
    }

    public static void createFileXML() throws ParserConfigurationException, TransformerException {
        //создание объектной модели документа и сохранение ее
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //получаем в ответ пустой документ
        Document document = db.newDocument();
        //создание корневого элемента
        Element root = document.createElement("root");
        document.appendChild(root);

        Element company = document.createElement("company");
        root.appendChild(company);

        Element equipment = document.createElement("equipment");
        company.appendChild(equipment);

        Element staff = document.createElement("staff");
        company.appendChild(staff);

        Element employee1 = document.createElement("employee");
        employee1.setAttribute("id1", "name1  workingHours1");
        Element employee2 = document.createElement("employee");
        employee2.setAttribute("id2", "name2  workingHours2");
        staff.appendChild(employee1);
        staff.appendChild(employee2);

        Element tool = document.createElement("tool");
        tool.appendChild(document.createTextNode("1234"));
        equipment.appendChild(tool);
        //на основе документа создается экземляр класса домсурс, он требуется, чтобы взаимодействовать с дом как с потоком данных
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("nodesWithStep.xml"));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, streamResult);
    }
}

