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

package org.eclipse.sapphire.services;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public abstract class DefaultValueService extends DataService<DefaultValueServiceData>
{
    @Override
    protected final void initDataService()
    {
        initDefaultValueService();
    }

    protected void initDefaultValueService()
    {
    }
    
    public final String value()
    {
        return data().value();
    }
    
}