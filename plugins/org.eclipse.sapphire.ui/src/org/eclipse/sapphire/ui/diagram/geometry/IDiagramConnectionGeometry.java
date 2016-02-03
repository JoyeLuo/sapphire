/******************************************************************************
 * Copyright (c) 2012 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Shenxue Zhou - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.diagram.geometry;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ListProperty;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;

/**
 * @author <a href="mailto:shenxue.zhou@oracle.com">Shenxue Zhou</a>
 */

@GenerateImpl

public interface IDiagramConnectionGeometry extends IModelElement 
{
    ModelElementType TYPE = new ModelElementType( IDiagramConnectionGeometry.class );
    
    // *** ConnectionId ***
    
    @XmlBinding( path = "id")
    @Required

    ValueProperty PROP_CONNECTION_ID = new ValueProperty( TYPE, "ConnectionId" );

    Value<String> getConnectionId();
    void setConnectionId( String name );

    // *** LabelX ***
    
    @Type( base = Integer.class )
    @XmlBinding( path = "labelPosition/@x" )
    
    ValueProperty PROP_LABEL_X = new ValueProperty( TYPE, "LabelX");
    
    Value<Integer> getLabelX();
    void setLabelX(Integer value);
    void setLabelX(String value);

    // *** LabelY ***
    
    @Type( base = Integer.class )
    @XmlBinding( path = "labelPosition/@y" )

    ValueProperty PROP_LabelY = new ValueProperty( TYPE, "LabelY");
    
    Value<Integer> getLabelY();
    void setLabelY(Integer value);
    void setLabelY(String value);
    
    // *** ConnectionBendpoints***

    @Type( base = IBendPoint.class )
    @XmlListBinding( mappings = @XmlListBinding.Mapping( element = "bendpoint", type = IBendPoint.class ) )
    
    ListProperty PROP_CONNECTION_BENDPOINTS = new ListProperty( TYPE, "ConnectionBendPoints" );
    
    ModelElementList<IBendPoint> getConnectionBendpoints();
    
    
}