package com.puxtech.ybk.qidong;

import org.w3c.dom.Element;


public class BaseParser {

	protected String getString(Element e, String tag) {
		try{
			if (((Element) (e.getElementsByTagName(tag).item(0))).getFirstChild() != null) {
				return ((Element) (e.getElementsByTagName(tag).item(0)))
						.getFirstChild().getNodeValue();
			}
		}
		catch(Exception ex){
		}
		return "";
		
	}
	
	protected Element getElement(Element parent, String elementTag){
		return (Element) parent.getElementsByTagName(elementTag).item(0);
	}

}
