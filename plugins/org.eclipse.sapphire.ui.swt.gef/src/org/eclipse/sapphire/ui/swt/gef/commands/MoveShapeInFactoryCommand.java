/******************************************************************************
 * Copyright (c) 2014 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Shenxue Zhou - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.swt.gef.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.sapphire.ui.diagram.editor.ShapeFactoryPart;
import org.eclipse.sapphire.ui.diagram.editor.ShapePart;

/**
 * @author <a href="mailto:shenxue.zhou@oracle.com">Shenxue Zhou</a>
 */

public class MoveShapeInFactoryCommand extends Command 
{
	private ShapeFactoryPart shapeFactory;
	private ShapePart shapePart;
	private int newIndex;
	
	public MoveShapeInFactoryCommand(ShapeFactoryPart shapeFactory, ShapePart shapePart, int newIndex)
	{
		this.shapeFactory = shapeFactory;
		this.shapePart = shapePart;
		this.newIndex = newIndex;
	}
	
	@Override
	public void execute() 
	{
		this.shapeFactory.moveChild(this.shapePart, newIndex);
	}
}
