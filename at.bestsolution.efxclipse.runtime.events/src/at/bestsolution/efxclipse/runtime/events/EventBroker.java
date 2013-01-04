package at.bestsolution.efxclipse.runtime.events;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UISynchronize;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import at.bestsolution.efxclipse.runtime.core.log.Log;
import at.bestsolution.efxclipse.runtime.core.log.Logger;

@SuppressWarnings("restriction")
public class EventBroker implements IEventBroker {
	
	// TBD synchronization
	private Map<EventHandler, ServiceRegistration> registrations = new HashMap<EventHandler, ServiceRegistration>();

	@Inject
	@Optional
	UISynchronize uiSync;
	
	@Inject
	@Log
	Logger logger;
	
	// This is a temporary code to ensure that bundle containing
	// EventAdmin implementation is started. This code it to be removed once
	// the proper method to start EventAdmin is added.
	static {
		EventAdmin eventAdmin = Activator.getEventAdmin();
		if (eventAdmin == null) {
			Bundle[] bundles = Activator.getContext().getBundles();
			for (Bundle bundle : bundles) {
				if (!"org.eclipse.equinox.event".equals(bundle.getSymbolicName()))
					continue;
				try {
					bundle.start(Bundle.START_TRANSIENT);
				} catch (BundleException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	public EventBroker() {
		// placeholder
	}

	public boolean send(String topic, Object data) {
		Event event = constructEvent(topic, data);
		EventAdmin eventAdmin = Activator.getEventAdmin();
		if (eventAdmin == null) {
			logger.error("No event admin found");
			return false;
		}
		eventAdmin.sendEvent(event);
		return true;
	}

	public boolean post(String topic, Object data) {
		Event event = constructEvent(topic, data);
		EventAdmin eventAdmin = Activator.getEventAdmin();
		if (eventAdmin == null) {
			logger.error("No event admin found");
			return false;
		}
		eventAdmin.postEvent(event);
		return true;
	}

	@SuppressWarnings("unchecked")
	private Event constructEvent(String topic, Object data) {
		Event event;
		if (data instanceof Dictionary<?,?>) {
			event = new Event(topic, (Dictionary<String,?>)data);
		} else if (data instanceof Map<?,?>) {
			event = new Event(topic, (Map<String,?>)data);
		} else {
			Dictionary<String, Object> d = new Hashtable<String, Object>(2);
			d.put(EventConstants.EVENT_TOPIC, topic);
			if (data != null)
				d.put(IEventBroker.DATA, data);
			event = new Event(topic, d);
		}
		return event;
	}

	public boolean subscribe(String topic, EventHandler eventHandler) {
		return subscribe(topic, null, eventHandler, false);
	}
	
	public boolean subscribe(String topic, String filter, EventHandler eventHandler, boolean headless) {
		BundleContext bundleContext = Activator.getContext();
		if (bundleContext == null) {
			logger.error("No bundle context");
			return false;
		}
		String[] topics = new String[] {topic};
		Dictionary<String, Object> d = new Hashtable<String, Object>();
		d.put(EventConstants.EVENT_TOPIC, topics);
		if (filter != null)
			d.put(EventConstants.EVENT_FILTER, filter);
		EventHandler wrappedHandler = new UIEventHandler(eventHandler, headless ? null : uiSync);
		ServiceRegistration registration = bundleContext.registerService(EventHandler.class.getName(), wrappedHandler, d);
		registrations.put(eventHandler, registration);
		return true;
	}

	public boolean unsubscribe(EventHandler eventHandler) {
		ServiceRegistration registration = (ServiceRegistration) registrations.remove(eventHandler);
		if (registration == null)
			return false;
		registration.unregister();
		return true;
	}
	
	@PreDestroy
	void dispose() {
		Collection<ServiceRegistration> values = registrations.values();
		ServiceRegistration[] array = values.toArray(new ServiceRegistration[values.size()]);
		registrations.clear();
		for (int i = 0; i < array.length; i++) {
			array[i].unregister();
		}
	}
}
