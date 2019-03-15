package utils;

import org.json.JSONObject;
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

	private String tabla;
	private int idTabla;
	private String campos;
	private String valores;
	/*
		1.- Get
		2.- Post
		3.- Put
		4.- Delete
	*/
	private int idTipoPeticion;	
	private JSONObject jso = new JSONObject();
//	private JsonGenerator jgen;
//	private ResultSet rs;
	
//	public JsonGenerator getJgen() {
//		return jgen;
//	}
//
//	public void setJgen(JsonGenerator jgen) {
//		this.jgen = jgen;
//	}
//
//	public ResultSet getRs() {
//		return rs;
//	}
//
//	public void setRs(ResultSet rs) {
//		this.rs = rs;
//	}

	public void EjecutaConsulta() {
		try {
			objAPI.setConsulta("select * from fnAPI(" + idTipoPeticion + ", '"+ tabla + "', '" + campos + "', '" + valores + "', " + idTabla + ");");
			objAPI.ejecutaAPI();			
			System.out.println(objAPI.getstJS());
			jso = new JSONObject(objAPI.getstJS());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
//	public void ExtractJSON() {
//		
//		try {
//			ResultSetMetaData rsmd = rs.getMetaData();
//	        int numColumns = rsmd.getColumnCount();
//	        String[] columnNames = new String[numColumns];
//	        int[] columnTypes = new int[numColumns];
//
//	        for (int i = 0; i < columnNames.length; i++) {
//	            columnNames[i] = rsmd.getColumnLabel(i + 1);
//	            columnTypes[i] = rsmd.getColumnType(i + 1);
//	        }
//			
//			jgen.writeStartArray();
//
//	        while (rs.next()) {
//
//	            jgen.writeStartObject();
//
//	            for (int i = 0; i < columnNames.length; i++) {
//	            	int c = i+1;
//	            	if(rs.wasNull()) {
//	                	jgen.write(columnNames[i], "");	                	
//	                }else {
//	                	switch (columnTypes[i]) {
//	                	case Types.BIGINT:
//		                case Types.INTEGER:
//		                	jgen.write(columnNames[i], rs.getInt(c));	                       		                    
//		                    break;
//		                case Types.DECIMAL:		                	
//		                case Types.NUMERIC:
//		                case Types.REAL:		                	
//		                case Types.DOUBLE:
//		                    jgen.write(columnNames[i], rs.getDouble(c));
//		                    break;
//		                case Types.FLOAT:
//		                	jgen.write(columnNames[i], rs.getFloat(c));
//		                	break;
//		                case Types.NVARCHAR:		                	
//		                case Types.VARCHAR:
//		                case Types.LONGNVARCHAR:
//		                case Types.LONGVARCHAR:
//		                case Types.BINARY:
//		                case Types.VARBINARY:
//		                case Types.LONGVARBINARY:
//		                    jgen.write(columnNames[i], rs.getString(i + 1));
//		                    break;
//		                case Types.BOOLEAN:
//		                	jgen.write(columnNames[i], rs.getBoolean(c));
//		                	break;
//		                case Types.BIT:		                    		                    
//		                    jgen.write(columnNames[i], rs.getByte(c));
//		                    break;		                
//		                case Types.TINYINT:
//		                case Types.SMALLINT:
//		                    jgen.write(columnNames[i], rs.getShort(c));		                    
//		                    break;
//		                case Types.DATE:
//		                	jgen.write(columnNames[i], rs.getDate(c).toString());
//		                    break;
//		                case Types.TIMESTAMP:
//		                	jgen.write(columnNames[i], rs.getTime(c).toString());
//		                    break;
//		                case Types.BLOB:
//		                	jgen.write(columnNames[i], rs.getBlob(c).toString());		                    		                   
//		                    break;
//		                case Types.CLOB:
//		                	jgen.write(columnNames[c], rs.getClob(c).toString());		                    
//		                    break;
//		                case Types.ARRAY:
//		                    throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type ARRAY");
//
//		                case Types.STRUCT:
//		                    throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type STRUCT");
//
//		                case Types.DISTINCT:
//		                    throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type DISTINCT");
//
//		                case Types.REF:
//		                    throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type REF");
//
//		                case Types.JAVA_OBJECT:
//		                default:
//		                	jgen.write(columnNames[c], rs.getString(c).toString());
//		                    break;
//		                }		            
//	                }	                
//	            	jgen.writeEnd();
//	            	}
//	            }	        
//		}catch(Exception ex){
//			System.out.println(ex.getMessage());
//		}			
//}
}