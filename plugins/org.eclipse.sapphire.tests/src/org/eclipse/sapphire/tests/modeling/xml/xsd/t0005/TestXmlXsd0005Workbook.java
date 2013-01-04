/******************************************************************************
 * Copyright (c) 2013 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.tests.modeling.xml.xsd.t0005;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ListProperty;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@GenerateImpl
@XmlNamespace( uri = "http://www.eclipse.org/sapphire/tests/xml/xsd/0004/workbook" )
@XmlBinding( path = "workbook" )

public interface TestXmlXsd0005Workbook extends IModelElement
{
    ModelElementType TYPE = new ModelElementType( TestXmlXsd0005Workbook.class );
    
    // *** Shapes ***
    
    @Type( base = TestXmlXsd0005Shape.class, possible = { TestXmlXsd0005Circle.class, TestXmlXsd0005Rectangle.class } )
    @XmlListBinding( path = "" )
    
    ListProperty PROP_SHAPES = new ListProperty( TYPE, "Shapes" );
    
    ModelElementList<TestXmlXsd0005Shape> getShapes();
    
}