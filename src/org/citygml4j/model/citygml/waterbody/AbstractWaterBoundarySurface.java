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
 * $Id: AbstractWaterBoundarySurface.java 542 2012-11-29 20:56:44Z nagel $
 */
package org.citygml4j.model.citygml.waterbody;

import java.util.List;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.core.AbstractCityObject;
import org.citygml4j.model.citygml.core.LodRepresentation;
import org.citygml4j.model.common.child.ChildList;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.AbstractGeometry;
import org.citygml4j.model.gml.geometry.GeometryProperty;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;
import org.citygml4j.model.module.citygml.WaterBodyModule;

public abstract class AbstractWaterBoundarySurface extends AbstractCityObject implements WaterBodyModuleComponent {
	private SurfaceProperty lod2Surface;
	private SurfaceProperty lod3Surface;
	private SurfaceProperty lod4Surface;
	private List<ADEComponent> ade;
	private WaterBodyModule module;
	
	public AbstractWaterBoundarySurface() {

	}

	public AbstractWaterBoundarySurface(WaterBodyModule module) {
		this.module = module;
	}
	
	public void addGenericApplicationPropertyOfWaterBoundarySurface(ADEComponent ade) {
		if (this.ade == null)
			this.ade = new ChildList<ADEComponent>(this);

		this.ade.add(ade);
	}

	public List<ADEComponent> getGenericApplicationPropertyOfWaterBoundarySurface() {
		if (ade == null)
			ade = new ChildList<ADEComponent>(this);

		return ade;
	}

	public SurfaceProperty getLod2Surface() {
		return lod2Surface;
	}

	public SurfaceProperty getLod3Surface() {
		return lod3Surface;
	}

	public SurfaceProperty getLod4Surface() {
		return lod4Surface;
	}

	public boolean isSetGenericApplicationPropertyOfWaterBoundarySurface() {
		return ade != null && !ade.isEmpty();
	}

	public boolean isSetLod2Surface() {
		return lod2Surface != null;
	}

	public boolean isSetLod3Surface() {
		return lod3Surface != null;
	}

	public boolean isSetLod4Surface() {
		return lod4Surface != null;
	}

	public void setGenericApplicationPropertyOfWaterBoundarySurface(List<ADEComponent> ade) {
		this.ade = new ChildList<ADEComponent>(this, ade);
	}

	public void setLod2Surface(SurfaceProperty lod2Surface) {
		if (lod2Surface != null)
			lod2Surface.setParent(this);
		
		this.lod2Surface = lod2Surface;
	}

	public void setLod3Surface(SurfaceProperty lod3Surface) {
		if (lod3Surface != null)
			lod3Surface.setParent(this);
		
		this.lod3Surface = lod3Surface;
	}

	public void setLod4Surface(SurfaceProperty lod4Surface) {
		if (lod4Surface != null)
			lod4Surface.setParent(this);
		
		this.lod4Surface = lod4Surface;
	}

	public void unsetGenericApplicationPropertyOfWaterBoundarySurface() {
		if (isSetGenericApplicationPropertyOfWaterBoundarySurface())
			ade.clear();

		ade = null;
	}

	public boolean unsetGenericApplicationPropertyOfWaterBoundarySurface(ADEComponent ade) {
		return isSetGenericApplicationPropertyOfWaterBoundarySurface() ? this.ade.remove(ade) : false;
	}

	public void unsetLod2Surface() {
		if (isSetLod2Surface())
			lod2Surface.unsetParent();
		
		lod2Surface = null;
	}

	public void unsetLod3Surface() {
		if (isSetLod3Surface())
			lod3Surface.unsetParent();
		
		lod3Surface = null;
	}

	public void unsetLod4Surface() {
		if (isSetLod4Surface())
			lod4Surface.unsetParent();
		
		lod4Surface = null;
	}

	public final WaterBodyModule getCityGMLModule() {
		return module;
	}

	public boolean isSetCityGMLModule() {
		return module != null;
	}

	@Override
	public BoundingShape calcBoundedBy(boolean setBoundedBy) {
		BoundingShape boundedBy = new BoundingShape();
		SurfaceProperty surfaceProperty = null;

		for (int lod = 2; lod < 5; lod++) {
			switch (lod) {
			case 2:
				surfaceProperty = lod2Surface;
				break;
			case 3:
				surfaceProperty = lod3Surface;
				break;
			case 4:
				surfaceProperty = lod4Surface;
				break;
			}

			if (surfaceProperty != null) {
				if (surfaceProperty.isSetSurface()) {
					calcBoundedBy(boundedBy, surfaceProperty.getSurface());
				} else {
					// xlink
				}
			}
		}

		if (boundedBy.isSetEnvelope()) {
			if (setBoundedBy)
				setBoundedBy(boundedBy);

			return boundedBy;
		} else
			return null;
	}
	
	@Override
	public LodRepresentation getLodRepresentation() {
		LodRepresentation lodRepresentation = new LodRepresentation();
		
		GeometryProperty<? extends AbstractGeometry> property = null;		
		for (int lod = 2; lod < 5; lod++) {
			switch (lod) {
			case 2:
				property = lod2Surface;
				break;
			case 3:
				property = lod3Surface;
				break;
			case 4:
				property = lod4Surface;
				break;
			}

			if (property != null)
				lodRepresentation.getLodGeometry(lod).add(property);
		}
		
		return lodRepresentation;
	}

	@Override
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		if (target == null)
			throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");

		AbstractWaterBoundarySurface copy = (AbstractWaterBoundarySurface)target;		
		super.copyTo(copy, copyBuilder);
		
		if (isSetLod2Surface()) {
			copy.setLod2Surface((SurfaceProperty)copyBuilder.copy(lod2Surface));
			if (copy.getLod2Surface() == lod2Surface)
				lod2Surface.setParent(this);
		}
		
		if (isSetLod3Surface()) {
			copy.setLod3Surface((SurfaceProperty)copyBuilder.copy(lod3Surface));
			if (copy.getLod3Surface() == lod3Surface)
				lod3Surface.setParent(this);
		}
		
		if (isSetLod4Surface()) {
			copy.setLod4Surface((SurfaceProperty)copyBuilder.copy(lod4Surface));
			if (copy.getLod4Surface() == lod4Surface)
				lod4Surface.setParent(this);
		}
		
		if (isSetGenericApplicationPropertyOfWaterBoundarySurface()) {
			for (ADEComponent part : ade) {
				ADEComponent copyPart = (ADEComponent)copyBuilder.copy(part);
				copy.addGenericApplicationPropertyOfWaterBoundarySurface(copyPart);

				if (part != null && copyPart == part)
					part.setParent(this);
			}
		}

		return copy;
	}
	
}
