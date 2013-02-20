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

package org.eclipse.sapphire.ui.swt.internal;

import org.eclipse.sapphire.Color;
import org.eclipse.sapphire.modeling.CapitalizationType;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.ui.PropertyEditorPart;
import org.eclipse.sapphire.ui.SapphireBrowseActionHandler;
import org.eclipse.sapphire.ui.SapphirePropertyEditorCondition;
import org.eclipse.sapphire.ui.SapphireRenderingContext;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Opens a color browse dialog. Activates if the property is a value property of type Color.
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class ColorBrowseActionHandler extends SapphireBrowseActionHandler
{
    @Override
    protected String browse( final SapphireRenderingContext context )
    {
        final IModelElement element = getModelElement();
        final ValueProperty property = getProperty();
        
        final Shell contextShell = context.getShell();
        
        final Rectangle bounds = contextShell.getBounds();
        final int x = bounds.x + bounds.width / 2 - 120;       // The size of color dialog is not known. The size is hardcodded
        final int y = bounds.y + bounds.height / 2 - 170;      // based on measuring the dialog on Windows 7. Will need to generalize.

        final Shell shell = new Shell( contextShell );
        
        try
        {
            shell.setBounds( x, y, 0, 0 );
            
            final ColorDialog dialog = new ColorDialog( shell );
            
            dialog.setText( property.getLabel( false, CapitalizationType.TITLE_STYLE, false ) );
            dialog.setRGB( convert( (Color) element.read( property ).getContent() ) );
            
            final RGB pickedColor = dialog.open();
            
            if( pickedColor != null )
            {
                return convert( pickedColor ).toString();
            }
        }
        finally
        {
            shell.dispose();
        }
       
        return null;
    }
    
    private static Color convert( final RGB rgb )
    {
        return ( rgb == null ? null : new Color( rgb.red, rgb.green, rgb.blue ) );
    }
    
    private static RGB convert( final Color color )
    {
        return ( color == null ? null : new RGB( color.red(), color.green(), color.blue() ) );
    }
    
    public static final class Condition extends SapphirePropertyEditorCondition
    {
        @Override
        protected boolean evaluate( final PropertyEditorPart part )
        {
            return ( part.getProperty().getTypeClass() == Color.class );
        }
    }
    
}