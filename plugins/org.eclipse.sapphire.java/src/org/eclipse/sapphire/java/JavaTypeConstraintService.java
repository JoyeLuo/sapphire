/******************************************************************************
 * Copyright (c) 2011 Oracle and Liferay
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 *    Gregory Amerson - initial implementation
 ******************************************************************************/

package org.eclipse.sapphire.java;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.sapphire.services.Service;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 * @author <a href="mailto:gregory.amerson@liferay.com">Gregory Amerson</a>
 */

public abstract class JavaTypeConstraintService extends Service
{
    private State state = null;
    
    @Override
    protected final void init()
    {
        initJavaTypeConstraintService();
        refresh();
    }

    protected void initJavaTypeConstraintService()
    {
    }
    
    public final Set<JavaTypeKind> kind()
    {
        return this.state.kind();
    }
    
    public final Set<String> type()
    {
        return this.state.type();
    }
    
    public final JavaTypeConstraintBehavior behavior()
    {
        return this.state.behavior();
    }
    
    protected abstract State compute();
    
    protected final void refresh()
    {
        final State newState = compute();
        final boolean notifyListeners = ( this.state == null );
        
        if( this.state == null || ! this.state.equals( newState ) )
        {
            this.state = newState;
            
            if( notifyListeners )
            {
                broadcast();
            }
        }
    }
    
	protected static final class State
	{
	    private final Set<JavaTypeKind> kind;
	    private final Set<String> type;
	    private final JavaTypeConstraintBehavior behavior;
	    
	    public State( final Set<JavaTypeKind> kind,
	                  final Set<String> type,
	                  final JavaTypeConstraintBehavior behavior )
	    {
	        if( kind == null )
	        {
	            throw new IllegalArgumentException();
	        }
	        
            for( JavaTypeKind item : kind )
            {
                if( item == null )
                {
                    throw new IllegalArgumentException();
                }
            }
            
	        if( type == null )
	        {
	            throw new IllegalArgumentException();
	        }
	        
	        for( String item : type )
	        {
	            if( item == null || item.length() == 0 )
	            {
	                throw new IllegalArgumentException();
	            }
	        }
	        
	        if( behavior == null )
	        {
	            throw new IllegalArgumentException();
	        }
	        
	        this.kind = Collections.unmodifiableSet( EnumSet.copyOf( kind ) );
	        this.type = Collections.unmodifiableSet( new HashSet<String>( type ) );
	        this.behavior = behavior;
	    }
	    
	    public State( final Set<JavaTypeKind> kind,
	                  final Set<String> type )
	    {
	        this( kind, type, JavaTypeConstraintBehavior.ALL );
	    }
	    
	    public Set<JavaTypeKind> kind()
	    {
	        return this.kind;
	    }
	    
	    public Set<String> type()
	    {
	        return this.type;
	    }
	    
	    public JavaTypeConstraintBehavior behavior()
	    {
	        return this.behavior;
	    }

        @Override
        public boolean equals( final Object obj )
        {
            if( obj instanceof State )
            {
                final State st = (State) obj;
                return this.kind.equals( st.kind ) && this.type.equals( st.type ) && ( this.behavior == st.behavior );
            }
            
            return false;
        }

        @Override
        public int hashCode()
        {
            return this.kind.hashCode() ^ this.type.hashCode() ^ this.behavior.hashCode();
        }
	}

}
