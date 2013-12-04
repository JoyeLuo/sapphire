/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.samples.contacts;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.NonNullValue;
import org.eclipse.sapphire.modeling.annotations.PossibleValues;
import org.eclipse.sapphire.modeling.xml.annotations.CustomXmlValueBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.samples.contacts.internal.AreaCodeBinding;
import org.eclipse.sapphire.samples.contacts.internal.LocalNumberBinding;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@GenerateImpl

public interface IPhoneNumber

    extends IModelElement
    
{
    ModelElementType TYPE = new ModelElementType( IPhoneNumber.class );
    
    // *** Type ***
    
    @Label( standard = "type", full = "phone number type" )
    @DefaultValue( text = "home" )
    @XmlBinding( path = "type" )
    
    @PossibleValues
    (
        values =
        {
            "home",
            "mobile",
            "work",
            "other"
        },
        invalidValueSeverity = IStatus.OK
    )
    
    ValueProperty PROP_TYPE = new ValueProperty( TYPE, "Type" );
    
    Value<String> getType();
    void setType( String type );
    
    // *** AreaCode ***
    
    @Label( standard = "area code" )
    @NonNullValue
    @CustomXmlValueBinding( impl = AreaCodeBinding.class )
    
    ValueProperty PROP_AREA_CODE = new ValueProperty( TYPE, "AreaCode" );
    
    Value<String> getAreaCode();
    void setAreaCode( String areaCode );    
    
    // *** LocalNumber ***
    
    @Label( standard = "local number" )
    @NonNullValue
    @CustomXmlValueBinding( impl = LocalNumberBinding.class )
    
    ValueProperty PROP_LOCAL_NUMBER = new ValueProperty( TYPE, "LocalNumber" );
    
    Value<String> getLocalNumber();
    void setLocalNumber( String localNumber );    
    
}