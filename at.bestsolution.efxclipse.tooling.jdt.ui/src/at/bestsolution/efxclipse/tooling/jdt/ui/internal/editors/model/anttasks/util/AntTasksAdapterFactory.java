/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.util;

import at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.AntTask;
import at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.AntTasksPackage;
import at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.Deploy;
import at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.Jar;
import at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.SignJar;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.AntTasksPackage
 * @generated
 */
public class AntTasksAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AntTasksPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AntTasksAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AntTasksPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AntTasksSwitch<Adapter> modelSwitch =
		new AntTasksSwitch<Adapter>() {
			@Override
			public Adapter caseAntTask(AntTask object) {
				return createAntTaskAdapter();
			}
			@Override
			public Adapter caseDeploy(Deploy object) {
				return createDeployAdapter();
			}
			@Override
			public Adapter caseJar(Jar object) {
				return createJarAdapter();
			}
			@Override
			public Adapter caseSignJar(SignJar object) {
				return createSignJarAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.AntTask <em>Ant Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.AntTask
	 * @generated
	 */
	public Adapter createAntTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.Deploy <em>Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.Deploy
	 * @generated
	 */
	public Adapter createDeployAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.Jar <em>Jar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.Jar
	 * @generated
	 */
	public Adapter createJarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.SignJar <em>Sign Jar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.bestsolution.efxclipse.tooling.jdt.ui.internal.editors.model.anttasks.SignJar
	 * @generated
	 */
	public Adapter createSignJarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AntTasksAdapterFactory
