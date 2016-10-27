package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categorylist")
public class CategoryList {

	private List<CategoryDTO> categorydtoLs;

	public List<CategoryDTO> getCategorydtoLs() {

		if (null == this.categorydtoLs) {
			categorydtoLs = new ArrayList<CategoryDTO>();
		}
		return categorydtoLs;
	}

	public void setCategorydtoLs(List<CategoryDTO> categorydtoLs) {
		this.categorydtoLs = categorydtoLs;
	}

}
