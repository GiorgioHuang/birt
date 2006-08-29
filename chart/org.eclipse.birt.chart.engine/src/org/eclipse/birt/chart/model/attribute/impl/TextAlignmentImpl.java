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

package org.eclipse.birt.chart.model.attribute.impl;

import org.eclipse.birt.chart.model.attribute.AttributeFactory;
import org.eclipse.birt.chart.model.attribute.AttributePackage;
import org.eclipse.birt.chart.model.attribute.HorizontalAlignment;
import org.eclipse.birt.chart.model.attribute.TextAlignment;
import org.eclipse.birt.chart.model.attribute.VerticalAlignment;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Text Alignment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.birt.chart.model.attribute.impl.TextAlignmentImpl#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.birt.chart.model.attribute.impl.TextAlignmentImpl#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TextAlignmentImpl extends EObjectImpl implements TextAlignment
{

	/**
	 * The default value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHorizontalAlignment()
	 * @generated
	 * @ordered
	 */
	protected static final HorizontalAlignment HORIZONTAL_ALIGNMENT_EDEFAULT = HorizontalAlignment.LEFT_LITERAL;

	/**
	 * The cached value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHorizontalAlignment()
	 * @generated
	 * @ordered
	 */
	protected HorizontalAlignment horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;

	/**
	 * This is true if the Horizontal Alignment attribute has been set. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean horizontalAlignmentESet = false;

	/**
	 * The default value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVerticalAlignment()
	 * @generated
	 * @ordered
	 */
	protected static final VerticalAlignment VERTICAL_ALIGNMENT_EDEFAULT = VerticalAlignment.TOP_LITERAL;

	/**
	 * The cached value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVerticalAlignment()
	 * @generated
	 * @ordered
	 */
	protected VerticalAlignment verticalAlignment = VERTICAL_ALIGNMENT_EDEFAULT;

	/**
	 * This is true if the Vertical Alignment attribute has been set. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean verticalAlignmentESet = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TextAlignmentImpl( )
	{
		super( );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass( )
	{
		return AttributePackage.Literals.TEXT_ALIGNMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public HorizontalAlignment getHorizontalAlignment( )
	{
		return horizontalAlignment;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHorizontalAlignment(
			HorizontalAlignment newHorizontalAlignment )
	{
		HorizontalAlignment oldHorizontalAlignment = horizontalAlignment;
		horizontalAlignment = newHorizontalAlignment == null ? HORIZONTAL_ALIGNMENT_EDEFAULT
				: newHorizontalAlignment;
		boolean oldHorizontalAlignmentESet = horizontalAlignmentESet;
		horizontalAlignmentESet = true;
		if ( eNotificationRequired( ) )
			eNotify( new ENotificationImpl( this,
					Notification.SET,
					AttributePackage.TEXT_ALIGNMENT__HORIZONTAL_ALIGNMENT,
					oldHorizontalAlignment,
					horizontalAlignment,
					!oldHorizontalAlignmentESet ) );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHorizontalAlignment( )
	{
		HorizontalAlignment oldHorizontalAlignment = horizontalAlignment;
		boolean oldHorizontalAlignmentESet = horizontalAlignmentESet;
		horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;
		horizontalAlignmentESet = false;
		if ( eNotificationRequired( ) )
			eNotify( new ENotificationImpl( this,
					Notification.UNSET,
					AttributePackage.TEXT_ALIGNMENT__HORIZONTAL_ALIGNMENT,
					oldHorizontalAlignment,
					HORIZONTAL_ALIGNMENT_EDEFAULT,
					oldHorizontalAlignmentESet ) );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHorizontalAlignment( )
	{
		return horizontalAlignmentESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public VerticalAlignment getVerticalAlignment( )
	{
		return verticalAlignment;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerticalAlignment( VerticalAlignment newVerticalAlignment )
	{
		VerticalAlignment oldVerticalAlignment = verticalAlignment;
		verticalAlignment = newVerticalAlignment == null ? VERTICAL_ALIGNMENT_EDEFAULT
				: newVerticalAlignment;
		boolean oldVerticalAlignmentESet = verticalAlignmentESet;
		verticalAlignmentESet = true;
		if ( eNotificationRequired( ) )
			eNotify( new ENotificationImpl( this,
					Notification.SET,
					AttributePackage.TEXT_ALIGNMENT__VERTICAL_ALIGNMENT,
					oldVerticalAlignment,
					verticalAlignment,
					!oldVerticalAlignmentESet ) );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVerticalAlignment( )
	{
		VerticalAlignment oldVerticalAlignment = verticalAlignment;
		boolean oldVerticalAlignmentESet = verticalAlignmentESet;
		verticalAlignment = VERTICAL_ALIGNMENT_EDEFAULT;
		verticalAlignmentESet = false;
		if ( eNotificationRequired( ) )
			eNotify( new ENotificationImpl( this,
					Notification.UNSET,
					AttributePackage.TEXT_ALIGNMENT__VERTICAL_ALIGNMENT,
					oldVerticalAlignment,
					VERTICAL_ALIGNMENT_EDEFAULT,
					oldVerticalAlignmentESet ) );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVerticalAlignment( )
	{
		return verticalAlignmentESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet( int featureID, boolean resolve, boolean coreType )
	{
		switch ( featureID )
		{
			case AttributePackage.TEXT_ALIGNMENT__HORIZONTAL_ALIGNMENT :
				return getHorizontalAlignment( );
			case AttributePackage.TEXT_ALIGNMENT__VERTICAL_ALIGNMENT :
				return getVerticalAlignment( );
		}
		return super.eGet( featureID, resolve, coreType );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet( int featureID, Object newValue )
	{
		switch ( featureID )
		{
			case AttributePackage.TEXT_ALIGNMENT__HORIZONTAL_ALIGNMENT :
				setHorizontalAlignment( (HorizontalAlignment) newValue );
				return;
			case AttributePackage.TEXT_ALIGNMENT__VERTICAL_ALIGNMENT :
				setVerticalAlignment( (VerticalAlignment) newValue );
				return;
		}
		super.eSet( featureID, newValue );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset( int featureID )
	{
		switch ( featureID )
		{
			case AttributePackage.TEXT_ALIGNMENT__HORIZONTAL_ALIGNMENT :
				unsetHorizontalAlignment( );
				return;
			case AttributePackage.TEXT_ALIGNMENT__VERTICAL_ALIGNMENT :
				unsetVerticalAlignment( );
				return;
		}
		super.eUnset( featureID );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet( int featureID )
	{
		switch ( featureID )
		{
			case AttributePackage.TEXT_ALIGNMENT__HORIZONTAL_ALIGNMENT :
				return isSetHorizontalAlignment( );
			case AttributePackage.TEXT_ALIGNMENT__VERTICAL_ALIGNMENT :
				return isSetVerticalAlignment( );
		}
		return super.eIsSet( featureID );
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String toString( )
	{
		if ( eIsProxy( ) )
			return super.toString( );

		StringBuffer result = new StringBuffer( super.toString( ) );
		result.append( " (horizontalAlignment: " ); //$NON-NLS-1$
		if ( horizontalAlignmentESet )
			result.append( horizontalAlignment );
		else
			result.append( "<unset>" ); //$NON-NLS-1$
		result.append( ", verticalAlignment: " ); //$NON-NLS-1$
		if ( verticalAlignmentESet )
			result.append( verticalAlignment );
		else
			result.append( "<unset>" ); //$NON-NLS-1$
		result.append( ')' );
		return result.toString( );
	}

	/**
	 * A convenient method to create a new TextAlignment instance and initialize
	 * its members
	 * 
	 * @return
	 */
	public static final TextAlignment create( )
	{
		final TextAlignment ta = AttributeFactory.eINSTANCE.createTextAlignment( );
		( (TextAlignmentImpl) ta ).initialize( );
		return ta;
	}

	/**
	 * Resets all member variables within this object recursively
	 * 
	 * Note: Manually written
	 */
	protected final void initialize( )
	{
		setHorizontalAlignment( HorizontalAlignment.LEFT_LITERAL );
		setVerticalAlignment( VerticalAlignment.TOP_LITERAL );
	}

	/**
	 * A convenient method to get an instance copy. This is much faster than the
	 * ECoreUtil.copy().
	 * 
	 * @param src
	 * @return
	 */
	public static TextAlignment copyInstance( TextAlignment src )
	{
		if ( src == null )
		{
			return null;
		}
		TextAlignmentImpl ta = new TextAlignmentImpl( );
		ta.horizontalAlignment = src.getHorizontalAlignment( );
		ta.horizontalAlignmentESet = src.isSetHorizontalAlignment( );
		ta.verticalAlignment = src.getVerticalAlignment( );
		ta.verticalAlignmentESet = src.isSetVerticalAlignment( );
		return ta;
	}

} // TextAlignmentImpl
