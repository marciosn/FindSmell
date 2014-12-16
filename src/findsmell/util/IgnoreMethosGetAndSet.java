package findsmell.util;

import java.util.Set;

import org.designwizard.design.ClassNode;
import org.designwizard.design.MethodNode;

public class IgnoreMethosGetAndSet {

	public int quantityMethodsNoGetAndSet(ClassNode classNode){
		
		Set<MethodNode> methodNodes = classNode.getAllMethods();
		
		for(MethodNode node : methodNodes){
			if(node.getShortName().equalsIgnoreCase("") || node.getShortName().equalsIgnoreCase("")){
				
			}
		}
		
		
		return 2;
	}
}
