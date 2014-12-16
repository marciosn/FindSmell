package findsmell.test;

import static org.junit.Assert.assertFalse;

import java.io.File;

import org.junit.Test;

import findsmell.main.FindSmell;
import findsmell.module.Module;

public class FindSmellTest {

	FindSmell findSmell = new FindSmell("lib" + File.separator + "educaBrasil.jar");
	
	//org.educabrasil.beans
	//org.educabrasil.controller
	//org.educabrasil.parsers
	//org.educabrasil.servlets
	//org.educabrasil.util
	
	@Test
	public void testQuantidadeMetodosPacote() {
		Module module = new Module();
		Module module2 = new Module();
		module.add("org.educabrasil.controller");
		module2.add("org.educabrasil.beans");
		assertFalse(findSmell.verifyQuantityMethods(module, 3));
	}
	
	@Test
	public void testQuantidadeMetodosClasse() {
		Module module = new Module();
		Module module2 = new Module();
		module.add("org.educabrasil.controller");
		module2.add("org.educabrasil.beans");
		assertFalse(findSmell.verifyQuantityMethodsInClass("ControladorOrcamentos", 3));
	}
	
	@Test
	public void testQuantidadeParametros() {
		Module module = new Module();
		Module module2 = new Module();
		module.add("org.educabrasil.controller");
		module2.add("org.educabrasil.beans");
		assertFalse(findSmell.verifyQuantityParameters(module, 2));
	}
	
	@Test
	public void testQuantidadeParametrosClasse() {
		Module module = new Module();
		Module module2 = new Module();
		module.add("org.educabrasil.controller");
		module2.add("org.educabrasil.beans");
		assertFalse(findSmell.verifyQuantityParametersInClass("ControladorOrcamentos", 2));
	}

	@Test
	public void testTamanhoNomeMetodo() {
		Module module = new Module();
		Module module2 = new Module();
		module.add("org.educabrasil.controller");
		module2.add("org.educabrasil.beans");
		assertFalse(findSmell.verifySizeNamesMethodFromPackage(module, 6));
	}
	
	@Test
	public void testTamanhoNomeMetodoClasse() {
		Module module = new Module();
		Module module2 = new Module();
		module.add("org.educabrasil.controller");
		module2.add("org.educabrasil.beans");
		assertFalse(findSmell.verifySizeNamesMethodFromClass("ControladorOrcamentos", 6));
	}
}
