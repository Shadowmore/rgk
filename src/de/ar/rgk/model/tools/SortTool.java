package de.ar.rgk.model.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.ar.rgk.model.interfaces.IInstruction;

public class SortTool {

	public static void sortListInstructions(List<IInstruction> listInstructions) {
		Collections.sort(listInstructions, new Comparator<IInstruction>() {

			@Override
			public int compare(IInstruction o1, IInstruction o2) {
				if (o1.getNumberInList() < o2.getNumberInList()) {
					return -1;
				} else if (o1.getNumberInList() > o2.getNumberInList()) {
					return 1;
				} else {
					throw new RuntimeException("Ingredient with same Number in Recipe not allowed");
				}
			}
		});
	}

}