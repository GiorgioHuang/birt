/***********************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Actuate Corporation - initial API and implementation
 ***********************************************************************/

package org.eclipse.birt.chart.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.birt.chart.model.component.ChartPreferences;
import org.eclipse.emf.common.util.URI;

/**
 * This interface provides a means to serialize and/or de-serialize the chart model instance into XML content.
 * Serialization is internally implemented using EMF tools using the Chart XSDs (XML schema definition(s)).
 * 
 * @author Actuate Corporation
 */
public interface Serializer
{

    // Write Methods
    /**
     * Write the chart described by the model to the OutputStream provided.
     * 
     * @param cModel
     *            The model to be serialized os The OutputStream to which the model is to be serialized
     */
    public void write(Chart cModel, OutputStream os) throws IOException;

    /**
     * Write the chart described by the model to the location defined by the URI provided.
     * 
     * @param cModel
     *            The model to be serialized uri The URI to which the model is to be serialized
     */
    public void write(Chart cModel, URI uri) throws IOException;

    /**
     * Write the chart described by the model to a ByteArrayOutputStream.
     * 
     * @param cModel
     *            The model to be serialized bStripHeaders Specifies whether or not the headers are to be removed while
     *            serializing the model
     * @return the ByteArrayOutputStream containing the serialized model
     */
    public ByteArrayOutputStream asXml(Chart cModel, boolean bStripHeaders) throws IOException;

    /**
     * Saves the chart preferences to the specified OutputStream.
     * 
     * @param preferences
     *            The ChartPreferences object to be saved
     * @param os
     *            The OutputStream to which the preferences are to be written
     * @throws IOException
     * @deprecated only reserved for compatibility
     */
    public void savePreferences(ChartPreferences preferences, OutputStream os) throws IOException;

    // Read Methods
    /**
     * Reads the chart model from the given InputStream
     * 
     * @return chart model read from the stream
     */
    public Chart read(InputStream is) throws IOException;

    /**
     * Reads the chart model from the location defined by the URI provided.
     * 
     * @param uri
     *            URI of the location holding the chart model
     * @return chart model read from the source
     * @throws IOException
     */
    public Chart read(URI uri) throws IOException;

    /**
     * Reads the chart model embedded inside other XML content defined by the URI provided.
     * 
     * @param uri
     *            URI of the location holding the embedded chart model
     * @return chart model read from the source
     * @throws IOException
     */
    public Chart readEmbedded(URI uri) throws IOException;

    /**
     * Reads the chart model from the ByteArrayInputStream.
     * 
     * @param byaIS
     *            The ByteArrayInputStream holding the chart model
     * @param bStripHeaders
     *            Specifies whether or not the headers were removed when the chart model was saved
     * @return chart model read from the stream
     * @throws IOException
     */
    public Chart fromXml(ByteArrayInputStream byaIS, boolean bStripHeaders) throws IOException;

    /**
     * Loads the chart preferences from the InputStream.
     * 
     * @param is
     *            InputStream from which the chart preferences are to be read
     * @return ChartPreferences instance read from the stream
     * @throws IOException
     * @deprecated only reserved for compatibility
     */
    public ChartPreferences loadPreferences(InputStream is) throws IOException;

	/**
	 * Optimizes the chart model.
	 * 
	 * @param cm
	 * @return
	 * @since 2.5.0
	 */
	public Chart optimize( Chart cm );
}