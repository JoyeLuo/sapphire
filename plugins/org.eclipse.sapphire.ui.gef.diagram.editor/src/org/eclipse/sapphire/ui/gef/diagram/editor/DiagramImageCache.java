/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ling Hao - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.gef.diagram.editor;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.ui.def.ISapphireUiDef;
import org.eclipse.sapphire.ui.diagram.def.IDiagramConnectionDef;
import org.eclipse.sapphire.ui.diagram.def.IDiagramImageChoice;
import org.eclipse.sapphire.ui.diagram.def.IDiagramNodeDef;
import org.eclipse.sapphire.ui.diagram.def.IDiagramNodeImageDef;
import org.eclipse.sapphire.ui.diagram.editor.DiagramNodeTemplate;
import org.eclipse.sapphire.ui.diagram.editor.SapphireDiagramEditorPagePart;
import org.osgi.framework.Bundle;

/**
 * @author <a href="mailto:ling.hao@oracle.com">Ling Hao</a>
 */

public class DiagramImageCache {

    // The prefix for all identifiers of this image provider
	protected static final String PREFIX = "org.eclipse.sapphire.ui.gef.diagram.editor";
	
	// The common image identifiers
    public static final String IMG_ERROR = PREFIX + "error"; //$NON-NLS-1$
    public static final String IMG_ERROR_SMALL = PREFIX + "error_small"; //$NON-NLS-1$
    public static final String IMG_WARNING = PREFIX + "warning"; //$NON-NLS-1$
    public static final String IMG_WARNING_SMALL = PREFIX + "warning_small"; //$NON-NLS-1$

    private final Map<String, ImageDescriptor> idToImageDescriptor = new HashMap<String, ImageDescriptor>();

	public DiagramImageCache(SapphireDiagramEditorPagePart diagramPart) {
		// Add decorators
		for (IDiagramImageChoice decorator : diagramPart.getImageDecorators()) {
			registerImage(decorator);
		}
		
		// Add node images
		List<DiagramNodeTemplate> nodeTemplates = diagramPart.getNodeTemplates();
		for (DiagramNodeTemplate nodeTemplate : nodeTemplates) {
			final IDiagramNodeDef nodeDef = nodeTemplate.definition();
			final IDiagramNodeImageDef imageDef = nodeDef.getImage().element();

			if (imageDef != null) {
				ModelElementList<IDiagramImageChoice> images = imageDef.getPossibleImages();
				for (IDiagramImageChoice imageChoice : images) {
					registerImage(imageChoice);
				}
			}

			// register node tool palette image
			IDiagramImageChoice toolImage = nodeTemplate.getToolPaletteImage();
			if (toolImage != null) {
				registerImage(toolImage);
			}
		}

		// Add connection tool palette images
		List<IDiagramConnectionDef> connDefs = diagramPart.getDiagramConnectionDefs();
		for (IDiagramConnectionDef connDef : connDefs) {
			IDiagramImageChoice image = connDef.getToolPaletteImage().element();
			if (image != null) {
				registerImage(image);
			}
		}
		
		// add common images
        addImageFilePath(IMG_ERROR, "images/error.gif"); //$NON-NLS-1$
        addImageFilePath(IMG_ERROR_SMALL, "images/error_small.png"); //$NON-NLS-1$
        addImageFilePath(IMG_WARNING, "images/warning.gif"); //$NON-NLS-1$
        addImageFilePath(IMG_WARNING_SMALL, "images/warning_small.png"); //$NON-NLS-1$
	}
	
	private void registerImage(IDiagramImageChoice imageChoice) {
		ISapphireUiDef uiDef = imageChoice.nearest(ISapphireUiDef.class);
		String imageId = imageChoice.getImageId().getContent();
		String imagePath = imageChoice.getImagePath().getContent();

		// TODO should be relative to sdef?
		final Bundle bundle = uiDef.adapt(Bundle.class);

		if (imageId != null && imagePath != null) {
	        final URL url = bundle.getResource(imagePath);
	        if (url != null) {
				ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
				idToImageDescriptor.put(imageId, descriptor);
	        }
		}
	}

	private void addImageFilePath(final String imageId, final String imagePath) {
		ImageDescriptor descriptor = ImageDescriptor.createFromFile(SapphireDiagramEditor.class, imagePath);
		idToImageDescriptor.put(imageId, descriptor);
	}


	public ImageDescriptor getImageDescriptor(final String imageId) {
		return idToImageDescriptor.get(imageId);
	}
	
	public ImageDescriptor getImageDescriptor(final IDiagramImageChoice imageChoice) {
		if (imageChoice != null) {
			String imageId = imageChoice.getImageId().getContent();
			return idToImageDescriptor.get(imageId);
		}
		return null;
	}

}
