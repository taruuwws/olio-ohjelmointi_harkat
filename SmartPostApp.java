package com.example.h9;

import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SmartPostApp {

    public static ArrayList<SmartPost> smartPostArrayList = new ArrayList<SmartPost>(); {}

    private String name, city, address, availability, urlString;



    public void readXMLFIN() {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            urlString = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=FI&type=APT";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    name = element.getElementsByTagName("name").item(0).getTextContent();
                    city = element.getElementsByTagName("city").item(0).getTextContent();
                    address = element.getElementsByTagName("address").item(0).getTextContent();
                    availability = element.getElementsByTagName("availability").item(0).getTextContent();
                    smartPostArrayList.add(new SmartPost(name, city, address, availability));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
        public void readXMLEST() {

            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                urlString = " http://iseteenindus.smartpost.ee/api/?request=destinations&country=EE&type=APT";
                Document doc = builder.parse(urlString);
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getDocumentElement().getElementsByTagName("item");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        name = element.getElementsByTagName("name").item(0).getTextContent();
                        city = element.getElementsByTagName("city").item(0).getTextContent();
                        address = element.getElementsByTagName("address").item(0).getTextContent();
                        availability = element.getElementsByTagName("availability").item(0).getTextContent();
                        smartPostArrayList.add(new SmartPost(name, city, address, availability));
                    }
                }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

            }


}
