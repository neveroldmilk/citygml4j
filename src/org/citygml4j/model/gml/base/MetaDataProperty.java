/*
 * This file is part of citygml4j.
 * Copyright (c) 2007 - 2012
 * Institute for Geodesy and Geoinformation Science
 * Technische Universität Berlin, Germany
 * http://www.igg.tu-berlin.de/
 *
 * The citygml4j library is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see 
 * <http://www.gnu.org/licenses/>.
 * 
 * $Id: MetaDataProperty.java 542 2012-11-29 20:56:44Z nagel $
 */
package org.citygml4j.model.gml.base;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.gml.GMLClass;

public class MetaDataProperty extends AssociationByRepOrRef<MetaData> {
	private String about;
	
	public MetaDataProperty() {
		
	}
	
	public MetaDataProperty(MetaData metaData) {
		super(metaData);
	}
	
	public MetaDataProperty(String href) {
		super(href);
	}
	
	public String getAbout() {
		return about;
	}

	public MetaData getMetaData() {
		return super.getObject();
	}

	public boolean isSetAbout() {
		return about != null;
	}

	public boolean isSetMetaData() {
		return super.isSetObject();
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setMetaData(MetaData metaData) {
		super.setObject(metaData);
	}

	public void unsetAbout() {
		about = null;
	}

	public void unsetMetaData() {
		super.unsetObject();
	}

	public GMLClass getGMLClass() {
		return GMLClass.META_DATA_PROPERTY;
	}
	
	public Class<MetaData> getAssociableClass() {
		return MetaData.class;
	}

	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new MetaDataProperty(), copyBuilder);
	}

	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		MetaDataProperty copy = (target == null) ? new MetaDataProperty() : (MetaDataProperty)target;
		super.copyTo(copy, copyBuilder);
		
		if (isSetAbout())
			copy.setAbout(copyBuilder.copy(about));
		
		return copy;
	}

}
