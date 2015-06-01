package de.ar.rgk.hibernate;

import de.ar.rgk.model.db.HibernateDao;
import de.ar.rgk.model.implementations.CookBook;
import de.ar.rgk.model.implementations.Ingredient;
import de.ar.rgk.model.implementations.Instruction;
import de.ar.rgk.model.implementations.Recipe;

public class GenerateTestDataQuarkauflaufMitRhabarber extends AbstractRgkTestCase {

	public void test1() {

		createSchema();

		CookBook cookBook = new CookBook("Süßspeisen");

		Recipe recipe = new Recipe("Quarkauflauf mit Rhabarber", 42, 4);
		Ingredient iZucker = new Ingredient("Zucker");

		recipe.addInstruction(new Instruction(1, "<u><b>Kompott:</b></u>"));
		Instruction instruction = new Instruction(2, new Ingredient("Rhabarber"), 800, "g", "putzen, waschen, in Stücke schneiden und mit");
		recipe.addInstruction(instruction);
		recipe.addInstruction(new Instruction(3, iZucker, 250, "g", ""));
		recipe.addInstruction(new Instruction(4, new Ingredient("trockener Weißwein"), 0.25, "l",
				"einmal aufkochen. Den Rhabarber dann zugedeckt bei schwacher Hitze etwa 5 Minuten garen, bis er bißfest ist."));
		recipe.addInstruction(new Instruction(5, new Ingredient("Erdbeeren"), 800, "g",
				"waschen, putzen und in Würfel schneiden. Die Erdbeeren unter das abgekühlte Rhabarberkompott mischen. Das Kompott bis zum Servieren zugedeckt in den Kühlschrank stellen."));
		recipe.addInstruction(new Instruction(6, "</br></br>"));

		recipe.addInstruction(new Instruction(7, "<u><b>Auflauf:</b></u>"));
		recipe.addInstruction(new Instruction(8, new Ingredient("Eigelb"), 6, "", ""));
		recipe.addInstruction(new Instruction(9, new Ingredient("Quark"), 500, "g", ""));
		recipe.addInstruction(new Instruction(10, iZucker, 100, "g", ""));
		recipe.addInstruction(new Instruction(11, new Ingredient("Salz"), 1, "Prise", ""));
		recipe.addInstruction(new Instruction(12, new Ingredient("Zimt"), 2, "TL", ""));
		recipe.addInstruction(new Instruction(13, new Ingredient("Zitronenschale"), 1, "", ""));
		recipe.addInstruction(new Instruction(14, "Saft von 1 Zitrone", "gründlich verquirlen"));
		recipe.addInstruction(new Instruction(15, "<br/>"));
		recipe.addInstruction(new Instruction(16, new Ingredient("Eiweiß"), 6, "", "steif schlagen"));
		recipe.addInstruction(new Instruction(17, new Ingredient("Sahne"), 250, "g", "steif schlagen und beides auf die Eigelbmasse geben."));
		recipe.addInstruction(new Instruction(18, new Ingredient("Weizenvollkornmehl"), 60, "g", "darüber stäuben und alles mit einem Schneebesen locker aber gründlich mischen"));
		recipe.addInstruction(new Instruction(
				19,
				"Eine höhere feuerfeste Form nur am Boden mit wenig Butter ausstreichen. Die Quarkmasse hineinfüllen. Den Auflauf etwa 60min bei 200 Grad garen bis die Oberfläche leicht gebräunt und die Quarkmasse fest ist."));
		recipe.addInstruction(new Instruction(20, "<br/>"));
		recipe.addInstruction(new Instruction(21, "<b>Varianten</b>: Statt Kompott kann man auch einen Salat aus beliebigen rohen Früchten servieren"));
		cookBook.addRecipe(recipe);

		HibernateDao.saveObject(cookBook);
	}
}