package at.bestsolution.efxclipse.runtime.workbench.renderers.base;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import at.bestsolution.efxclipse.runtime.workbench.renderers.widgets.WWidget;
import at.bestsolution.efxclipse.runtime.workbench.rendering.AbstractRenderer;

@SuppressWarnings("restriction")
public abstract class BaseRenderer<M extends MUIElement, W extends WWidget<M>> extends AbstractRenderer<M, W> {
	private static final String RENDERING_CONTEXT_KEY = "fx.rendering.context";
	
	public static final String CONTEXT_DOM_ELEMENT = "fx.rendering.domElement";
//	public static final String CONTEXT_WIDGET_ELEMENT = "fx.rendering.widget";
	
	@Inject
	IEclipseContext _context; // The rendering context
	
	@Inject
	EModelService modelService;
	
	@Override
	public final W createWidget(M element) {
		IEclipseContext context = setupRenderingContext(element);
		
		W widget =  ContextInjectionFactory.make(getWidgetClass(), context);
//		context.set(CONTEXT_WIDGET_ELEMENT, widget);
		initWidget(element, widget);
		
		return widget;
	}
	
	public final IEclipseContext setupRenderingContext(M element) {
		IEclipseContext context = (IEclipseContext) element.getTransientData().get(RENDERING_CONTEXT_KEY);
		if( context == null ) {
			context = _context.createChild("Element RenderingContext");
			element.getTransientData().put(RENDERING_CONTEXT_KEY, context);
			context.set(CONTEXT_DOM_ELEMENT, element);
			initRenderingContext(element, context);			
		}
		return context;
	}
	
	protected void initRenderingContext(M element, IEclipseContext context) {
		
	}
	
	protected void initWidget(M element, W widget) {
		
	}
	
	@Override
	public void destroyWidget(M element) {
		if( element.getTransientData().containsKey(RENDERING_CONTEXT_KEY) ) {
			IEclipseContext ctx = (IEclipseContext) element.getTransientData().get(RENDERING_CONTEXT_KEY);
			ctx.dispose();
			element.getTransientData().remove(RENDERING_CONTEXT_KEY);
		}
	}
	
	@Override
	public void bindWidget(M me, W widget) {
		widget.setDomElement(me);
		widget.addStyleClasses(me.getTags());
		if( me.getElementId() != null ) {
			widget.setStyleId(me.getElementId());
		}
		me.setWidget(widget);
	}

	@Override
	public void postProcess(M element) {
	}
	
	protected IPresentationEngine getPresentationEngine() {
		return _context.get(IPresentationEngine.class);
	}
	
	protected abstract Class<? extends W> getWidgetClass();
	
	@SuppressWarnings("unchecked")
	protected <LW extends WWidget<PM>, PM extends MUIElement> LW engineCreateWidget(PM pm) {
		return (LW) getPresentationEngine().createGui(pm);
	}
	
	protected IEclipseContext getRenderingContext(M element) {
		return (IEclipseContext) element.getTransientData().get(RENDERING_CONTEXT_KEY);
	}
	
	protected IEclipseContext getContextForParent(MUIElement element) {
		return modelService.getContainingContext(element);
	}

	protected IEclipseContext getModelContext(MUIElement part) {
		if (part instanceof MContext) {
			return ((MContext) part).getContext();
		}
		return getContextForParent(part);
	}

	protected void activate(MPart element) {
		IEclipseContext curContext = getModelContext(element);
		if (curContext != null) {
			EPartService ps = (EPartService) curContext.get(EPartService.class
					.getName());
			if (ps != null)
				ps.activate(element, requiresFocus(element));
		}
	}
	
	protected boolean requiresFocus(MPart element) {
		return false;
	}
}
