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

package org.eclipse.sapphire.services.internal;

import java.util.List;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelProperty;
import org.eclipse.sapphire.modeling.ModelPropertyService;
import org.eclipse.sapphire.modeling.ModelPropertyServiceFactory;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.MustExist;
import org.eclipse.sapphire.modeling.util.NLS;
import org.eclipse.sapphire.services.FactsService;

/**
 * Creates fact statements about existence requirement on the entity referenced by property's value 
 * by using semantical information specified by @MustExist annotation.
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class MustExistFactsService extends FactsService
{
    @Override
    protected void facts( final List<String> facts )
    {
        facts.add( Resources.statement );
    }
    
    public static final class Factory extends ModelPropertyServiceFactory
    {
        @Override
        public boolean applicable( final IModelElement element,
                                   final ModelProperty property,
                                   final Class<? extends ModelPropertyService> service )
        {
            return ( property instanceof ValueProperty && property.hasAnnotation( MustExist.class ) );
        }
    
        @Override
        public ModelPropertyService create( final IModelElement element,
                                            final ModelProperty property,
                                            final Class<? extends ModelPropertyService> service )
        {
            return new MustExistFactsService();
        }
    }
    
    private static final class Resources extends NLS
    {
        public static String statement;
        
        static
        {
            initializeMessages( MustExistFactsService.class.getName(), Resources.class );
        }
    }
    
}
