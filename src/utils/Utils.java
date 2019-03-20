package utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mx.lamarrulla.implement.implementAPI;

public class Utils {
	
	implementAPI objAPI = new implementAPI();	
		
	public JSONObject getJso() {
		return jso;
	}

	public void setJso(JSONObject jso) {
		this.jso = jso;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public int getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}

	public String getCampos() {
		return campos;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}	

	public int getIdTipoPeticion() {
		return idTipoPeticion;
	}

	public void setIdTipoPeticion(int idTipoPeticion) {
		this.idTipoPeticion = idTipoPeticion;
	}	
	
	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	private String tabla;
	private int idTabla;
	private String campos;
	private String valores;
	private String consulta;
	/*
		1.- Get
		2.- Post
		3.- Put
		4.- Delete
	*/
	private int idTipoPeticion;	
	private JSONObject jso = new JSONObject();

	public void ejecutaConsultaAPI() {
		try {
			objAPI.setConsulta("select * from fnAPI(" + idTipoPeticion + ", '"+ tabla + "', '" + campos + "', '" + valores + "', " + idTabla + ");");
			objAPI.ejecutaAPI();			
			System.out.println(objAPI.getstJS());
			jso = new JSONObject(objAPI.getstJS());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	public void ejecutaConsultaJSON() {
		try {
			objAPI.setConsulta("select row_to_json(t) from (" + consulta + ")t");
			objAPI.ejecutaAPI();			
			System.out.println(objAPI.getstJS());
			jso = new JSONObject(objAPI.getstJS());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	public void getFile() {
		StringBuilder result = new StringBuilder("");
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("resources/strings.xml").getFile());
		try(Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(Exception ex ){
			System.out.println(ex.getMessage());
		}
	}
	public String getStringFromXML(String recurso) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("resources/strings.xml").getFile());
		
		String xmlFile = file.getPath();
       	//Get DOM
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xml = db.parse(xmlFile);		
 
        //Get XPath
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
         
        //Get first match
        String name = (String) xpath.evaluate("/resources/string", xml, XPathConstants.STRING);
         
        System.out.println(name);   //Lokesh
         
        //Get all matches
        NodeList nodes = (NodeList) xpath.evaluate("/resources/string/@name", xml, XPathConstants.NODESET);
         
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());   //1 2
        }
		return "";
	}
}