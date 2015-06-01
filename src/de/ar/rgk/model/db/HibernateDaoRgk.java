package de.ar.rgk.model.db;

import java.util.List;

import org.hibernate.Session;

import de.ar.rgk.model.interfaces.ICookBook;
import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.interfaces.IRecipe;

public class HibernateDaoRgk extends HibernateDao {

	@SuppressWarnings("unchecked")
	public static ICookBook getCookBookById(int id) {

		ICookBook cookBook = null;
		Session session = getSessionAndBeginTransaktion();
		List<ICookBook> listResult = (List<ICookBook>) session.createQuery("from CookBook as cookBook where cookBook.id=" + id).list();
		if (listResult != null) {
			if (listResult.size() > 0) {
				cookBook = listResult.get(0);
				if (listResult.size() > 1) {
					throw new RuntimeException("Duplicate CookBook found: " + id);
				}
			}
		}
		commitOrRollbackOnError(session);
		return cookBook;
	}

	@SuppressWarnings("unchecked")
	public static List<ICookBook> getAllCookBooks() {

		Session session = getSessionAndBeginTransaktion();
		String hql = "from CookBook as cookBook";
		List<ICookBook> list = (List<ICookBook>) session.createQuery(hql).list();
		session.getTransaction().commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	public static IRecipe getRecipeById(int id) {

		IRecipe recipe = null;
		Session session = getSessionAndBeginTransaktion();
		List<IRecipe> listResult = (List<IRecipe>) session.createQuery("from Recipe as recipe where recipe.id=" + id).list();
		if (listResult != null) {
			if (listResult.size() > 0) {
				recipe = listResult.get(0);
				if (listResult.size() > 1) {
					throw new RuntimeException("Duplicate Recipe found: " + id);
				}
			}
		}
		commitOrRollbackOnError(session);
		return recipe;
	}

	@SuppressWarnings("unchecked")
	public static List<IRecipe> getAllRecipes() {

		Session session = getSessionAndBeginTransaktion();
		String hql = "from Recipe as recipe";
		List<IRecipe> list = (List<IRecipe>) session.createQuery(hql).list();
		session.getTransaction().commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	public static IIngredient getIngredientById(int id) {

		IIngredient ingredient = null;
		Session session = getSessionAndBeginTransaktion();
		List<IIngredient> listResult = (List<IIngredient>) session.createQuery("from Ingredient as ingredient where ingredient.id=" + id).list();
		if (listResult != null) {
			if (listResult.size() > 0) {
				ingredient = listResult.get(0);
				if (listResult.size() > 1) {
					throw new RuntimeException("Duplicate Ingredient found: " + id);
				}
			}
		}
		commitOrRollbackOnError(session);
		return ingredient;
	}

	@SuppressWarnings("unchecked")
	public static List<IIngredient> getAllIngredients() {

		Session session = getSessionAndBeginTransaktion();
		String hql = "from Ingredient as ingredient";
		List<IIngredient> list = (List<IIngredient>) session.createQuery(hql).list();
		session.getTransaction().commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	public static IInstruction getInstructionById(int id) {

		IInstruction instruction = null;
		Session session = getSessionAndBeginTransaktion();
		List<IInstruction> listResult = (List<IInstruction>) session.createQuery("from Instruction as instruction where instruction.id=" + id).list();
		if (listResult != null) {
			if (listResult.size() > 0) {
				instruction = listResult.get(0);
				if (listResult.size() > 1) {
					throw new RuntimeException("Duplicate Instruction found: " + id);
				}
			}
		}
		commitOrRollbackOnError(session);
		return instruction;
	}

	@SuppressWarnings("unchecked")
	public static List<IInstruction> getAllInstructions() {

		Session session = getSessionAndBeginTransaktion();
		String hql = "from Instruction as instruction";
		List<IInstruction> list = (List<IInstruction>) session.createQuery(hql).list();
		session.getTransaction().commit();
		return list;
	}
}