package at.bestsolution.efxclipse.tooling.model.internal;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.CompilationParticipant;
import org.eclipse.jdt.core.compiler.ReconcileContext;

import at.bestsolution.efxclipse.tooling.model.FXPlugin;

public class ModelUpdater extends CompilationParticipant {

	public ModelUpdater() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean isActive(IJavaProject project) {
		return true;
	}
	
	@Override
	public void reconcile(ReconcileContext context) {
		IJavaElementDelta delta = context.getDelta();
		if( delta.getElement() instanceof ICompilationUnit ) {
			
			ICompilationUnit u = (ICompilationUnit) delta.getElement();
			try {
				for( IType t : u.getTypes() ) {
					FXPlugin.getClassmodel().clearCache(t);
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.reconcile(context);
	}
}