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
 * $Id: DowngradeConverter.java 542 2012-11-29 20:56:44Z nagel $
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.citygml4j.CityGMLContext;
import org.citygml4j.builder.CityGMLBuilder;
import org.citygml4j.model.citygml.bridge.AbstractBridge;
import org.citygml4j.model.citygml.core.AbstractCityObject;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.LodRepresentation;
import org.citygml4j.model.citygml.generics.GenericCityObject;
import org.citygml4j.model.citygml.tunnel.AbstractTunnel;
import org.citygml4j.model.gml.feature.AbstractFeature;
import org.citygml4j.model.gml.geometry.GeometryProperty;
import org.citygml4j.model.gml.geometry.aggregates.MultiGeometry;
import org.citygml4j.model.module.citygml.CityGMLVersion;
import org.citygml4j.model.module.citygml.CoreModule;
import org.citygml4j.util.walker.FeatureWalker;
import org.citygml4j.xml.io.CityGMLInputFactory;
import org.citygml4j.xml.io.CityGMLOutputFactory;
import org.citygml4j.xml.io.reader.CityGMLReader;
import org.citygml4j.xml.io.reader.FeatureReadMode;
import org.citygml4j.xml.io.writer.CityModelWriter;

public class DowngradeConverter {

	public static void main(String[] args) throws Exception {
		final SimpleDateFormat df = new SimpleDateFormat("[HH:mm:ss] "); 

		System.out.println(df.format(new Date()) + "setting up citygml4j context and JAXB builder");
		CityGMLContext ctx = new CityGMLContext();
		CityGMLBuilder builder = ctx.createCityGMLBuilder();

		System.out.println(df.format(new Date()) + "reading CityGML 2.0.0 file LOD3_Railway_v200.gml chunk-wise");
		CityGMLInputFactory in = builder.createCityGMLInputFactory();
		in.setProperty(CityGMLInputFactory.FEATURE_READ_MODE, FeatureReadMode.SPLIT_PER_COLLECTION_MEMBER);
		CityGMLReader reader = in.createCityGMLReader(new File("../datasets/LOD3_Railway_v200.gml"));

		CityGMLVersion version = CityGMLVersion.v1_0_0;
		System.out.println(df.format(new Date()) + "writing citygml4j object tree as CityGML " + version + " document");
		CityGMLOutputFactory out = builder.createCityGMLOutputFactory();
		out.setCityGMLVersion(version);

		CityModelWriter writer = out.createCityModelWriter(new File("LOD3_Railway_v100.gml"));
		writer.setPrefixes(version);
		writer.setDefaultNamespace(CoreModule.v1_0_0);
		writer.setSchemaLocations(version);
		writer.setIndentString("  ");

		// we iterate through all <cityObjectMember>s of the <CityModel>
		while (reader.hasNext()) {
			AbstractFeature feature = (AbstractFeature)reader.nextFeature();

			// if we find a tunnel or a bridge we convert it to a generic city object as proxy
			if (feature instanceof AbstractTunnel || feature instanceof AbstractBridge) {
				final GenericCityObject proxy = new GenericCityObject();
				proxy.setLod3Geometry(new GeometryProperty<MultiGeometry>(new MultiGeometry()));

				System.out.println(df.format(new Date()) + "converting " + ((AbstractCityObject)feature).getCityGMLClass() + " and nested features to " + proxy.getCityGMLClass());				

				// we use a feature walker to visit the tunnel/bridge and all its nested city objects
				FeatureWalker walker = new FeatureWalker() {
					public void visit(AbstractCityObject abstractCityObject) {
						// simply collect all LOD3 geometries and add them to the gml:MultiGeometry of the proxy
						System.out.println(df.format(new Date()) + "addind geometry of " + abstractCityObject.getCityGMLClass());				

						LodRepresentation lods = abstractCityObject.getLodRepresentation();
						if (lods.isSetLod3Geometry()) {
							MultiGeometry multiGeometry = (MultiGeometry)proxy.getLod3Geometry().getGeometry();
							multiGeometry.getGeometryMember().addAll(lods.getLodGeometry(3));	
						}
					}

					public void visit(AbstractBridge abstractBridge) {
						// copy attributes of the brigde
						proxy.setClazz(abstractBridge.getClazz());
						proxy.setFunction(abstractBridge.getFunction());
						proxy.setUsage(abstractBridge.getUsage());
						super.visit(abstractBridge);
					}

					public void visit(AbstractTunnel abstractTunnel) {
						// copy attributes of the tunnel
						proxy.setClazz(abstractTunnel.getClazz());
						proxy.setFunction(abstractTunnel.getFunction());
						proxy.setUsage(abstractTunnel.getUsage());
						super.visit(abstractTunnel);
					}

				};

				feature.accept(walker);
				
				// copy common attributes
				proxy.setId(feature.getId());
				proxy.setName(feature.getName());
				proxy.setRelativeToTerrain(((AbstractCityObject)feature).getRelativeToTerrain());
				proxy.setRelativeToWater(((AbstractCityObject)feature).getRelativeToWater());

				// let's swap the bridge/tunnel with the proxy object
				feature = proxy;
			}

			if (!(feature instanceof CityModel))
				writer.writeFeatureMember(feature);
		}

		reader.close();
		writer.close();

		System.out.println(df.format(new Date()) + "CityGML file LOD3_Railway_v100.gml written");
		System.out.println(df.format(new Date()) + "sample citygml4j application successfully finished");
	}
}
