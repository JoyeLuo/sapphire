/******************************************************************************
 * Copyright (c) 2016 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.forms.internal;

import org.eclipse.sapphire.modeling.Status;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class OutlineNodeShowNextErrorActionHandler extends OutlineNodeShowNextProblemActionHandler
{
    public OutlineNodeShowNextErrorActionHandler()
    {
        super( Status.Severity.ERROR );
    }

}
