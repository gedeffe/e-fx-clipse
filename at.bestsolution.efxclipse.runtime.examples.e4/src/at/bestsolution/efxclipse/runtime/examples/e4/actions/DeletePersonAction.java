 
package at.bestsolution.efxclipse.runtime.examples.e4.actions;

import org.eclipse.e4.core.di.annotations.Execute;

@SuppressWarnings("restriction")
public class DeletePersonAction {
	@Execute
	public void execute() {
		System.err.println("Delete person");
	}
		
}