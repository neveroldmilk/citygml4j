package org.citygml4j.model.gml.base;

import org.citygml4j.builder.copy.Copyable;
import org.citygml4j.commons.child.Child;
import org.citygml4j.model.gml.GML;

public interface Association<T extends Associable> extends GML, Child, Copyable {

}
