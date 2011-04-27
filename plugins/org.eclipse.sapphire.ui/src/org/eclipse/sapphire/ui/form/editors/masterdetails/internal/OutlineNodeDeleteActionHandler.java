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

package org.eclipse.sapphire.ui.form.editors.masterdetails.internal;

import static java.lang.Math.min;

import java.util.Collections;
import java.util.List;

import org.eclipse.sapphire.modeling.ElementProperty;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.IModelParticle;
import org.eclipse.sapphire.modeling.ModelElementHandle;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.ui.ISapphirePart;
import org.eclipse.sapphire.ui.SapphireActionHandler;
import org.eclipse.sapphire.ui.SapphireRenderingContext;
import org.eclipse.sapphire.ui.form.editors.masterdetails.MasterDetailsContentNode;
import org.eclipse.sapphire.ui.form.editors.masterdetails.MasterDetailsEditorPagePart;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class OutlineNodeDeleteActionHandler

    extends SapphireActionHandler
    
{
    public static final String ID = "Sapphire.Outline.Delete";
    
    public OutlineNodeDeleteActionHandler()
    {
        setId( ID );
    }
    
    @Override
    protected Object run( final SapphireRenderingContext context )
    {
        final ISapphirePart part = getPart();
        final List<MasterDetailsContentNode> nodes;
        
        if( part instanceof MasterDetailsContentNode )
        {
            nodes = Collections.singletonList( (MasterDetailsContentNode) part );
        }
        else if( part instanceof MasterDetailsEditorPagePart )
        {
            nodes = ( (MasterDetailsEditorPagePart) part ).getContentOutline().getSelectedNodes();
        }
        else
        {
            throw new IllegalStateException();
        }
        
        MasterDetailsContentNode newSelection = null;
 
        final MasterDetailsContentNode parent = nodes.get( 0 ).getParentNode();
        final List<MasterDetailsContentNode> siblings = parent.getChildNodes();
        final int size = siblings.size();
        
        if( size == nodes.size() )
        {
            newSelection = parent;
        }
        else
        {
            int lowestIndexOfRemovedNode = -1;
            
            for( MasterDetailsContentNode node : nodes )
            {
                final int indexOfRemovedNode = siblings.indexOf( node );
                
                if( indexOfRemovedNode != -1 )
                {
                    if( lowestIndexOfRemovedNode == -1 )
                    {
                        lowestIndexOfRemovedNode = indexOfRemovedNode;
                    }
                    else
                    {
                        lowestIndexOfRemovedNode = min( lowestIndexOfRemovedNode, indexOfRemovedNode );
                    }
                }
            }
            
            if( lowestIndexOfRemovedNode != -1 )
            {
                int indexOfNewSelection = -1;
                
                if( lowestIndexOfRemovedNode == 0 )
                {
                    for( int i = 0; i < size; i++ )
                    {
                        final MasterDetailsContentNode node = siblings.get( i );
                        
                        if( ! nodes.contains( node ) )
                        {
                            indexOfNewSelection = i;
                            break;
                        }
                    }
                }
                else
                {
                    indexOfNewSelection = lowestIndexOfRemovedNode - 1;
                }
    
                if( indexOfNewSelection != -1 )
                {
                    newSelection = siblings.get( indexOfNewSelection );
                }
            }
        }
        
        for( MasterDetailsContentNode node : nodes )
        {
            final IModelElement element = node.getModelElement();
            final IModelParticle elementParent = element.parent();
            
            if( elementParent instanceof ModelElementList )
            {
                ( (ModelElementList<?>) elementParent ).remove( element );
            }
            else
            {
                final ElementProperty property = (ElementProperty) element.getParentProperty();
                final ModelElementHandle<?> handle = ( (IModelElement) elementParent ).read( property );
                
                if( handle.element() == element )
                {
                    handle.remove();
                }
            }
        }
        
        if( newSelection != null )
        {
            newSelection.getContentTree().setSelectedNode( newSelection );
        }
        
        return null;
    }
    
}
