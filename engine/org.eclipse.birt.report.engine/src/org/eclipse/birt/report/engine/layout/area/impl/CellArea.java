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

package org.eclipse.birt.report.engine.layout.area.impl;

import java.util.Iterator;

import org.eclipse.birt.report.engine.content.ICellContent;
import org.eclipse.birt.report.engine.content.IContent;
import org.eclipse.birt.report.engine.content.IStyle;
import org.eclipse.birt.report.engine.css.engine.value.FloatValue;
import org.eclipse.birt.report.engine.css.engine.value.Value;
import org.eclipse.birt.report.engine.layout.pdf.cache.DummyCell;
import org.eclipse.birt.report.engine.util.BidiAlignmentResolver;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

public class CellArea extends ContainerArea
{

	static Value DEFAULT_PADDING = new FloatValue(
			CSSPrimitiveValue.CSS_NUMBER, 1500 );

	protected int rowSpan = 0;
	
	private boolean aligned = false;
	private boolean verticalAligned = false;

	public CellArea( )
	{
		super( (IContent) null );
	}

	CellArea( ICellContent cell )
	{
		super( cell );
		// remove all border
		removeBorder( );
		setDefaultPadding( );
	}

	public int getColumnID( )
	{
		if ( content != null )
		{
			return ( (ICellContent) content ).getColumn( );
		}
		return 0;
	}

	public int getRowID( )
	{
		if ( content != null )
		{
			return ( (ICellContent) content ).getRow( );
		}
		return 0;
	}

	public int getColSpan( )
	{
		if ( content != null )
		{
			return ( (ICellContent) content ).getColSpan( );
		}
		return 1;
	}

	public int getRowSpan( )
	{
		if ( rowSpan == 0 && content != null )
		{
			return ( (ICellContent) content ).getRowSpan( );
		}
		else
		{
			return rowSpan;
		}
	}

	public void setRowSpan( int rowSpan )
	{
		this.rowSpan = rowSpan;
	}

	protected void setDefaultPadding( )
	{

		if ( content != null )
		{
			IStyle contentStyle = content.getStyle( );
			CSSValue padding = contentStyle
					.getProperty( IStyle.STYLE_PADDING_TOP );
			if ( padding == null )
			{
				style.setProperty( IStyle.STYLE_PADDING_TOP, DEFAULT_PADDING );
			}
			padding = contentStyle.getProperty( IStyle.STYLE_PADDING_BOTTOM );
			if ( padding == null )
			{
				style
						.setProperty( IStyle.STYLE_PADDING_BOTTOM,
								DEFAULT_PADDING );
			}
			padding = contentStyle.getProperty( IStyle.STYLE_PADDING_LEFT );
			if ( padding == null )
			{
				style.setProperty( IStyle.STYLE_PADDING_LEFT, DEFAULT_PADDING );
			}
			padding = contentStyle.getProperty( IStyle.STYLE_PADDING_RIGHT );
			if ( padding == null )
			{
				style.setProperty( IStyle.STYLE_PADDING_RIGHT, DEFAULT_PADDING );
			}
		}
	}
	
	public void align( )
	{
		if( aligned && verticalAligned )
		{
			return;
		}
		CellArea cell;
		if ( this instanceof DummyCell )
		{
			cell = ( (DummyCell) this ).getCell( );
		}
		else
		{
			cell = this;
		}
		IContent content = cell.getContent( );
		if ( content == null )
		{
			return;
		}
		CSSValue verticalAlign = content.getComputedStyle( ).getProperty(
				IStyle.STYLE_VERTICAL_ALIGN );
		if ( IStyle.BOTTOM_VALUE.equals( verticalAlign )
				|| IStyle.MIDDLE_VALUE.equals( verticalAlign ) )
		{
			int totalHeight = 0;
			Iterator iter = cell.getChildren( );
			while ( iter.hasNext( ) )
			{
				AbstractArea child = (AbstractArea) iter.next( );
				totalHeight += child.getAllocatedHeight( );
			}
			int offset = cell.getContentHeight( ) - totalHeight;
			if ( offset > 0 )
			{
				if ( IStyle.BOTTOM_VALUE.equals( verticalAlign ) )
				{
					iter = cell.getChildren( );
					while ( iter.hasNext( ) )
					{
						AbstractArea child = (AbstractArea) iter.next( );
						child.setAllocatedPosition( child.getAllocatedX( ),
								child.getAllocatedY( ) + offset );
					}
				}
				else if ( IStyle.MIDDLE_VALUE.equals( verticalAlign ) )
				{
					iter = cell.getChildren( );
					while ( iter.hasNext( ) )
					{
						AbstractArea child = (AbstractArea) iter.next( );
						child.setAllocatedPosition( child.getAllocatedX( ),
								child.getAllocatedY( ) + offset / 2 );
					}
				}
			}
		}
		verticalAligned = true;

		CSSValue align = content.getComputedStyle( ).getProperty(
				IStyle.STYLE_TEXT_ALIGN );

		// bidi_hcg: handle empty or justify align in RTL direction as right
		// alignment
		boolean isRightAligned = BidiAlignmentResolver.isRightAligned( content,
				align, false );

		// single line
		if ( isRightAligned || IStyle.CENTER_VALUE.equals( align ) )
		{

			Iterator iter = cell.getChildren( );
			while ( iter.hasNext( ) )
			{
				AbstractArea area = (AbstractArea) iter.next( );
				int spacing = cell.getContentWidth( )
						- area.getAllocatedWidth( );
				if ( spacing > 0 )
				{
					if ( isRightAligned )
					{
						area.setAllocatedPosition( spacing
								+ area.getAllocatedX( ), area.getAllocatedY( ) );
					}
					else if ( IStyle.CENTER_VALUE.equals( align ) )
					{
						area.setAllocatedPosition( spacing / 2
								+ area.getAllocatedX( ), area.getAllocatedY( ) );
					}
				}
			}
		}
		aligned = true;
	}

}
