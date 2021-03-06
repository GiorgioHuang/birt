/*************************************************************************************
 * Copyright (c) 2004 Actuate Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Actuate Corporation - Initial implementation.
 ************************************************************************************/

package org.eclipse.birt.report.designer.ui.editors.pages;

import org.eclipse.birt.report.designer.core.model.SessionHandleAdapter;
import org.eclipse.birt.report.designer.core.util.mediator.request.ReportRequest;
import org.eclipse.birt.report.designer.internal.ui.command.WrapperCommandStack;
import org.eclipse.birt.report.designer.internal.ui.editors.layout.ReportMasterPageEditor;
import org.eclipse.birt.report.designer.internal.ui.util.Policy;
import org.eclipse.birt.report.designer.internal.ui.util.UIUtil;
import org.eclipse.birt.report.designer.internal.ui.views.outline.DesignerOutlinePage;
import org.eclipse.birt.report.designer.ui.editors.IPageStaleType;
import org.eclipse.birt.report.designer.ui.editors.IReportEditorPage;
import org.eclipse.birt.report.designer.ui.editors.IReportProvider;
import org.eclipse.birt.report.designer.ui.editors.MultiPageReportEditor;
import org.eclipse.birt.report.model.api.MasterPageHandle;
import org.eclipse.birt.report.model.api.ModuleHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.ActivityStackEvent;
import org.eclipse.birt.report.model.api.activity.ActivityStackListener;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * Report masterpage is the graphical edior for report masterpage.
 */
public class ReportMasterPageEditorFormPage extends ReportMasterPageEditor implements
		IReportEditorPage
{

	public static final String ID = MultiPageReportEditor.LayoutMasterPage_ID;
	private FormEditor editor;
	private Control control;
	private int index;

	private ActivityStackListener commandStackListener = new ActivityStackListener( ) {

		public void stackChanged( ActivityStackEvent event )
		{
			updateStackActions( );
			editor.editorDirtyStateChanged( );
			// fix bug 158254
			staleType = IPageStaleType.MODEL_CHANGED;
		}
	};

	private int staleType;

	protected void configureGraphicalViewer( )
	{
		super.configureGraphicalViewer( );
		WrapperCommandStack stack = (WrapperCommandStack) getCommandStack( );
		if ( stack != null )
		{
			stack.addCommandStackListener( getCommandStackListener( ) );
			// fix bug 158254
			// staleType = IPageStaleType.MODEL_CHANGED;
		}
	}

	/**
	 * returns command stack listener.
	 */
	public ActivityStackListener getCommandStackListener( )
	{
		return commandStackListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.report.designer.ui.editors.IReportEditorPage#onBroughtToTop
	 * (org.eclipse.birt.report.designer.ui.editors.IReportEditorPage)
	 */
	public boolean onBroughtToTop( IReportEditorPage prePage )
	{
		if ( getEditorInput( ) != prePage.getEditorInput( ) )
		{
			setInput( prePage.getEditorInput( ) );
		}

		ModuleHandle newModel = getProvider( ).queryReportModuleHandle( );
		
		if ( newModel != null && getModel( ) != newModel )
		{
			Object oldModel = getModel( );

			getProvider( ).connect( newModel );
			setModel( newModel );

			rebuildReportDesign( oldModel );
			
			if ( getModel( ) != null )
			{
				setViewContentsAsMasterPage( );
				markPageStale( IPageStaleType.NONE );
				if ( oldModel instanceof ReportDesignHandle )
				{
					if ( !( (ReportDesignHandle) getModel( ) ).getBidiOrientation( )
							.equals( ( (ReportDesignHandle) oldModel ).getBidiOrientation( ) ) )
					{
						String newOrientation = ( (ReportDesignHandle) getModel( ) ).getBidiOrientation( );
						UIUtil.processOrientationChange( newOrientation,
								getGraphicalViewer( ) );
					}
				}
			}
			updateStackActions( );

		}
		// reselect the selection
		GraphicalViewer view = getGraphicalViewer( );

		UIUtil.resetViewSelection( view, true );
		return true;

	}

	/**
	 * Rebuild report design model.
	 * 
	 * @param oldModel
	 */
	protected void rebuildReportDesign( Object oldModel )
	{
		// Initializes command stack
		WrapperCommandStack stack = (WrapperCommandStack) getCommandStack( );
		if ( stack != null )
		{
			stack.removeCommandStackListener( getCommandStackListener( ) );
			stack.setActivityStack( getModel( ).getCommandStack( ) );
			stack.addCommandStackListener( getCommandStackListener( ) );
		}

		// Resets the mediator
		SessionHandleAdapter.getInstance( ).resetReportDesign( oldModel,
				getModel( ) );

		SessionHandleAdapter.getInstance( )
				.setReportDesignHandle( getModel( ) );

		UIUtil.processSessionResourceFolder( getEditorInput( ),
				UIUtil.getProjectFromInput( getEditorInput( ) ),
				getModel( ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.forms.editor.IFormPage#initialize(org.eclipse.ui.forms
	 * .editor.FormEditor)
	 */
	public void initialize( FormEditor editor )
	{

		this.editor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#getEditor()
	 */
	public FormEditor getEditor( )
	{
		return editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#getManagedForm()
	 */
	public IManagedForm getManagedForm( )
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#setActive(boolean)
	 */
	public void setActive( boolean active )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#isActive()
	 */
	public boolean isActive( )
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#canLeaveThePage()
	 */
	public boolean canLeaveThePage( )
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#getPartControl()
	 */
	public Control getPartControl( )
	{
		return control;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#getId()
	 */
	public String getId( )
	{
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#getIndex()
	 */
	public int getIndex( )
	{
		return index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#setIndex(int)
	 */
	public void setIndex( int index )
	{
		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#isEditor()
	 */
	public boolean isEditor( )
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#selectReveal(java.lang.Object)
	 */
	public boolean selectReveal( Object object )
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl( Composite parent )
	{
		super.createPartControl( parent );
		Control[] children = parent.getChildren( );
		control = children[children.length - 1];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.report.designer.ui.editors.IReportEditorPage#markPageStale
	 * (int)
	 */
	public void markPageStale( int type )
	{
		staleType = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.birt.report.designer.ui.editors.schematic.layout.
	 * AbstractReportGraphicalEditorWithRuler#dispose()
	 */
	public void dispose( )
	{

		if ( getCommandStack( ) != null
				&& getCommandStack( ) instanceof WrapperCommandStack )
		{
			WrapperCommandStack stack = (WrapperCommandStack) getCommandStack( );
			stack.removeCommandStackListener( getCommandStackListener( ) );
		}
		super.dispose( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.birt.report.designer.ui.editors.IReportEditorPage#getStaleType
	 * ()
	 */
	public int getStaleType( )
	{
		return staleType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	public void setInput( IEditorInput input )
	{
		super.setInput( input );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#firePropertyChange(int)
	 */
	protected void firePropertyChange( int type )
	{
		if ( type == PROP_DIRTY )
		{
			editor.editorDirtyStateChanged( );
		}
		else
		{
			super.firePropertyChange( type );
		}
	}

	protected IReportProvider getProvider( )
	{
		return (IReportProvider) editor.getAdapter( IReportProvider.class );
	}

	protected void finalize( ) throws Throwable
	{
		if ( Policy.TRACING_PAGE_CLOSE )
		{
			System.out.println( "Report master page finalized" ); //$NON-NLS-1$
		}

		super.finalize( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.birt.report.designer.internal.ui.editors.parts.
	 * GraphicalEditorWithFlyoutPalette
	 * #performRequest(org.eclipse.birt.report.designer
	 * .core.util.mediator.request.ReportRequest)
	 */
	public void performRequest( ReportRequest request )
	{
		if ( ReportRequest.SELECTION.equals( request.getType( ) )
				&& ( request.getSelectionModelList( ).size( ) == 1 )
				&& request.getSelectionModelList( ).get( 0 ) instanceof MasterPageHandle
				&& ID.equals( editor.getActivePageInstance( ).getId( ) ) &&
				request.getSource( ) instanceof DesignerOutlinePage)
		{
			handlerLoadMasterPage( request );
			return;
		}

		super.performRequest( request );
	}
}
