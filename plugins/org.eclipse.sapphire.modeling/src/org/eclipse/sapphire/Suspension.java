/******************************************************************************
 * Copyright (c) 2015 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire;


/**
 * A handle that's returned when something is temporarily suspended.
 * 
 * <p>For instance, it is used when suspending event delivery from a property. See {@link Property#suspend()}.</p>
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public abstract class Suspension implements Disposable, AutoCloseable
{
    @Override
    
    public final void close()
    {
        dispose();
    }

}
