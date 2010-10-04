package org.citygml4j.impl.citygml.generics;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.citygml.generics.AbstractGenericAttribute;
import org.citygml4j.model.common.base.ModelObject;
import org.citygml4j.model.module.citygml.GenericsModule;

public abstract class AbstractGenericAttributeImpl implements AbstractGenericAttribute {
	private String name;
	private GenericsModule module;
	private ModelObject parent;
	
	public AbstractGenericAttributeImpl() {
		
	}
	
	public AbstractGenericAttributeImpl(GenericsModule module) {
		this.module = module;
	}
	
	public String getName() {
		return name;
	}

	public boolean isSetName() {
		return name != null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void unsetName() {
		name = null;
	}

	public final GenericsModule getCityGMLModule() {
		return module;
	}

	public boolean isSetCityGMLModule() {
		return module != null;
	}
	
	public ModelObject getParent() {
		return parent;
	}

	public void setParent(ModelObject parent) {
		this.parent = parent;
	}

	public boolean isSetParent() {
		return parent != null;
	}

	public void unsetParent() {
		parent = null;
	}

	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		if (target == null)
			throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");

		AbstractGenericAttribute copy = (AbstractGenericAttribute)target;
		
		if (isSetName())
			copy.setName(copyBuilder.copy(name));
		
		copy.unsetParent();
		
		return copy;
	}

}
