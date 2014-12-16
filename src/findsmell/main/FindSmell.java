package findsmell.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.designwizard.design.ClassNode;
import org.designwizard.design.MethodNode;
import org.designwizard.main.DesignWizard;

import findsmell.module.Module;
import findsmell.util.RegularExpression;
import findsmell.util.SelectType;

public class FindSmell {
	private DesignWizard designWizard;
	private SelectType type;
	private RegularExpression reg;
	public static void main(String[] args) {
		FindSmell findSmell = new FindSmell("lib" + File.separator + "educaBrasil.jar");
		
		Module module = new Module();
		Module module2 = new Module();
		module.add("org.educabrasil.controller");
		module2.add("org.educabrasil.beans");

		findSmell.verifySizeNamesClassFromPackage(module, 5);
		
		if (findSmell.verifyQuantityMethodsInClass("ControladorOrcamentos", 2)) {
			System.out.println("Não Passou No Teste!");
		} else {
			System.out.println("Passou No Teste!");
		}
	}
	
	public FindSmell(String appJarPath) {
		try {
			designWizard = new DesignWizard(appJarPath);
			type = new SelectType(designWizard);
			reg = new RegularExpression();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
	
	public boolean verifyQuantityMethods(Module module, int quantityMethods){
		boolean violation = false;
		
		Set<ClassNode> classNodesModule = type.getTypeModule(module);
		
		for(ClassNode classNode : classNodesModule){
			Set<MethodNode> methods = classNode.getAllMethods();
			//System.out.println(classNode.getShortName() + " " + methods.size());
				if(methods.size() > quantityMethods){
					violation = true;
				}
		}
		return violation;
	}
	
	public boolean verifyQuantityMethodsInClass(String nameClass, int quantityMethods){
		boolean violation = false;
		Set<MethodNode> methods = new HashSet<MethodNode>();
		Set<MethodNode> methodNoConstrutor = new HashSet<MethodNode>();
		Set<ClassNode> classNodesModule = designWizard.getAllClasses();
		
		for(ClassNode classNode : classNodesModule){
			if(classNode.getShortName().equals(nameClass))
				methods = classNode.getAllMethods();
		}
		for(MethodNode methodNode : methods){
			if(!methodNode.isConstructor()){
				methodNoConstrutor.add(methodNode);
			}
		}
	
		if(methodNoConstrutor.size() > quantityMethods)
			violation = true;
		
		return violation;
	}
	
	public boolean verifyQuantityParameters(Module module, int quantityParameters){
		boolean violation = false;
		
		Set<ClassNode> classNodesModule = type.getTypeModule(module);
		
		for(ClassNode classNode : classNodesModule){
			Set<MethodNode> methods = classNode.getAllMethods();
			for(MethodNode methodNode : methods){
				if(methodNode.getParameters().size() > quantityParameters){
					violation = true;
				}
				//System.out.println("Nome do metodo " + methodNode.getShortName() + "Quantidade parametros " +methodNode.getParameters().size());
			}
		}
		return violation;
	}
	
	public boolean verifyQuantityParametersInClass(String nameClass, int quantityParameters){
		boolean violation = false;
		Set<MethodNode> methods = new HashSet<MethodNode>();
		Set<ClassNode> classNodesModule = designWizard.getAllClasses();
		
		for(ClassNode classNode : classNodesModule){
			if(classNode.getShortName().equals(nameClass))
				methods = classNode.getAllMethods();
		}
		for(MethodNode methodNode : methods){
			if(!methodNode.isConstructor()){
				
				//System.out.println("Nome do metodo " + methodNode.getShortName() + "Quantidade parametros " +methodNode.getParameters().size());
				
				if(methodNode.getParameters().size() > quantityParameters){
					violation = true;
				}
			}
		}
		
		return violation;
	}
	
	public boolean verifySizeNamesMethodFromPackage(Module module, int sizeName){
		boolean violation = false;
		
		Set<ClassNode> classNodesModule = type.getTypeModule(module);
		
		for(ClassNode classNode : classNodesModule){
			Set<MethodNode> methods = classNode.getAllMethods();
			for(MethodNode methodNode : methods){
				if(!methodNode.isConstructor()){
					
					String nomeMethod = reg.getOnlyName(methodNode.getShortName());
					
					//System.out.println(nomeMethod + nomeMethod.length());
					if(nomeMethod.length() < sizeName){
						violation = true;
					}
				}
			}
		}
		return violation;
	}
	
	public boolean verifySizeNamesMethodFromClass(String nameClass, int sizeName){
		boolean violation = false;
		Set<MethodNode> methods = new HashSet<MethodNode>();
		Set<ClassNode> classNodesModule = designWizard.getAllClasses();
		
		for(ClassNode classNode : classNodesModule){
			if(classNode.getShortName().equals(nameClass))
				methods = classNode.getAllMethods();
		}
		for(MethodNode methodNode : methods){
			if(!methodNode.isConstructor()){
				String nomeMethod = reg.getOnlyName(methodNode.getShortName());
				
				//System.out.println(nomeMethod + nomeMethod.length());
				if(nomeMethod.length() < sizeName){
					violation = true;
				}
			}
		}
		
		return violation;
	}
	
	public boolean verifySizeNamesClassFromPackage(Module module, int sizeName){
		boolean violation = false;
		
		Set<ClassNode> classNodesModule = type.getTypeModule(module);
		
		for(ClassNode classNode : classNodesModule){
			if(classNode.getShortName().length() < sizeName){
				violation = true;
			}
		}
		return violation;
	}

}
