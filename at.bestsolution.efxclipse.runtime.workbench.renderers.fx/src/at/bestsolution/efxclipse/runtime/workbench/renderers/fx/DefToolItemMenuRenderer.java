/*******************************************************************************
 * Copyright (c) 2012 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package at.bestsolution.efxclipse.runtime.workbench.renderers.fx;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MToolItem;
import org.eclipse.emf.ecore.EObject;

import at.bestsolution.efxclipse.runtime.workbench.renderers.base.BaseMenuRenderer;
import at.bestsolution.efxclipse.runtime.workbench.renderers.base.BaseRenderer;
import at.bestsolution.efxclipse.runtime.workbench.renderers.base.widget.WMenu;
import at.bestsolution.efxclipse.runtime.workbench.renderers.base.widget.WMenuElement;
import at.bestsolution.efxclipse.runtime.workbench.renderers.base.widget.WToolItem;
import at.bestsolution.efxclipse.runtime.workbench.renderers.fx.widget.ToolItemMenu;
import at.bestsolution.efxclipse.runtime.workbench.renderers.fx.widget.WWidgetImpl;

@SuppressWarnings("restriction")
public class DefToolItemMenuRenderer extends BaseMenuRenderer<ToolItemMenu> {

	@Override
	protected Class<? extends WMenu<ToolItemMenu>> getWidgetClass(MMenu item) {
		return WMenuImpl.class;
	}

	static class WMenuImpl extends WWidgetImpl<ToolItemMenu, MMenu> implements WMenu<ToolItemMenu> {
		private SplitMenuButton button;
		Runnable showingCallback;
		
		@Inject
		public WMenuImpl(@Named(BaseRenderer.CONTEXT_DOM_ELEMENT) MMenu domElement) {
			MToolItem item = (MToolItem) ((EObject)domElement).eContainer();
			@SuppressWarnings("unchecked")
			WToolItem<SplitMenuButton> w = (WToolItem<SplitMenuButton>) item.getWidget();
			this.button = (SplitMenuButton) w.getWidget();
			this.button.showingProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if( showingCallback != null && newValue.booleanValue() ) {
						showingCallback.run();
					}
				}
			});
		}
		
		@Override
		public void addStyleClasses(List<String> classnames) {
			getWidget().getStyleClass().addAll(classnames);
		}
		
		@Override
		public void addStyleClasses(String... classnames) {
			getWidget().getStyleClass().addAll(classnames);
		}
		
		@Override
		public void setStyleId(String id) {
			getWidget().setId(id);
		}

		@Override
		protected ToolItemMenu createWidget() {
			return new ToolItemMenu(button);
		}

		@Override
		protected void setUserData(WWidgetImpl<ToolItemMenu, MMenu> widget) {
			getWidget().setUserData(widget);
		}

		@Inject
		public void setLabel(@Named(ATTRIBUTE_localizedLabel) String label) {
			getWidget().setText(label);
		}

		@Override
		public void addElement(WMenuElement<MMenuElement> widget) {
			getWidget().getItems().add((MenuItem) widget.getWidget());
		}
		
		@Override
		public void addElement(int idx, WMenuElement<MMenuElement> widget) {
			getWidget().getItems().add(idx, (MenuItem) widget.getWidget());			
		}
		
		@Override
		public void removeElement(WMenuElement<MMenuElement> widget) {
			getWidget().getItems().remove(widget.getWidget());
		}

		@Override
		public void setShowingCallback(Runnable showingCallback) {
			this.showingCallback = showingCallback;
		}
	}
	
}