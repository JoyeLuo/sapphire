/******************************************************************************
 * Copyright (c) 2010 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.modeling.expr;

/**
 * An expression that always evaluates to the same value. 
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class StaticExpression<T>

    extends Expression<T>

{
    private T value;
    
    public StaticExpression( final T value )
    {
        this.value = value;
    }

    @Override
    protected T evaluate()
    {
        return this.value;
    }
}
