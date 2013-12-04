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

package org.eclipse.sapphire.samples.ezbug;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.sapphire.modeling.IExecutableModelElement;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ImpliedElementProperty;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.annotations.DelegateImplementation;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlRootBinding;
import org.eclipse.sapphire.samples.ezbug.internal.FileBugReportOpMethods;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@GenerateImpl
@XmlRootBinding( elementName = "report" )

public interface IFileBugReportOp

    extends IModelElement, IExecutableModelElement
    
{
    ModelElementType TYPE = new ModelElementType( IFileBugReportOp.class );
    
    // *** BugReport ***
    
    @Type( base = IBugReport.class )
    @Label( standard = "bug report" )
    @XmlBinding( path = "bug" )
    
    ImpliedElementProperty PROP_BUG_REPORT = new ImpliedElementProperty( TYPE, "BugReport" );
    
    IBugReport getBugReport();
    
    // *** Method: execute ***
    
    @DelegateImplementation( FileBugReportOpMethods.class )
    
    IStatus execute( IProgressMonitor monitor );
    
}