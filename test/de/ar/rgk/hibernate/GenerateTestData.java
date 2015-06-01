package de.ar.rgk.hibernate;

import java.util.Random;

import de.ar.rgk.model.db.HibernateDao;
import de.ar.rgk.model.implementations.CookBook;
import de.ar.rgk.model.implementations.Ingredient;
import de.ar.rgk.model.implementations.Instruction;
import de.ar.rgk.model.implementations.Recipe;

public class GenerateTestData extends AbstractRgkTestCase {

	public void test() {

		createSchema();

		CookBook cb1 = new CookBook("CB1");
		CookBook cb2 = new CookBook("CB2");
		CookBook cb3 = new CookBook("CB3");

		addRecipesToCookBook(cb1, 1);
		addRecipesToCookBook(cb2, 2);
		addRecipesToCookBook(cb3, 3);

		HibernateDao.saveObject(cb1);
		HibernateDao.saveObject(cb2);
		HibernateDao.saveObject(cb3);
	}

	private void addRecipesToCookBook(CookBook cb1, int i) {
		Random random = new Random(1337);
		Recipe r1cb = new Recipe("r1cb" + i, random.nextInt(100), 4);
		Recipe r2cb = new Recipe("r2cb" + i, random.nextInt(100), 8);
		Recipe r3cb = new Recipe("r3cb" + i, random.nextInt(100), 1);

		cb1.addRecipe(r1cb);
		cb1.addRecipe(r2cb);
		cb1.addRecipe(r3cb);

		addIngredient(r1cb, random.nextInt());
		addIngredient(r2cb, random.nextInt());
		addIngredient(r3cb, random.nextInt());
	}

	private void addIngredient(Recipe r1cb, int i) {
		Random random = new Random(i);
		r1cb.addInstruction(new Instruction(1, new Ingredient(r1cb.getName() + "i1"), random.nextDouble(), "unit"));
		r1cb.addInstruction(new Instruction(2, new Ingredient(r1cb.getName() + "i2"), random.nextDouble(), "unit"));
		r1cb.addInstruction(new Instruction(3, new Ingredient(r1cb.getName() + "i3"), random.nextDouble(), "unit"));
		r1cb.addInstruction(new Instruction(4, new Ingredient(r1cb.getName() + "i4"), random.nextDouble(), "unit"));
		r1cb.addInstruction(new Instruction(5, new Ingredient(r1cb.getName() + "i5"), random.nextDouble(), "unit"));
	}
}