/******************************************************************************
 * Copyright (c) 2012 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.tests.services.t0005;

import static org.eclipse.sapphire.modeling.util.MiscUtil.equal;

import org.eclipse.sapphire.services.EqualityService;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class ContactEqualityService extends EqualityService
{
    @Override
    public boolean doEquals( final Object obj )
    {
        final Contact c1 = context( Contact.class );
        final Contact c2 = (Contact) obj;
        
        return equal( c1.getLastName().getText(), c2.getLastName().getText() ) &&
               equal( c1.getFirstName().getText(), c2.getFirstName().getText() );
    }

    @Override
    public int doHashCode()
    {
        final Contact c = context( Contact.class );
        final String lastName = c.getLastName().getText();
        final String firstName = c.getFirstName().getText();
        
        return ( lastName == null ? 1 : lastName.hashCode() ) ^ ( firstName == null ? 1 : firstName.hashCode() );
    }
    
}
