package lv.dependency_injection;

import lv.fitness_app.dependency_injection.ClassFinder;
import lv.fitness_app.dependency_injection.DIComponentFilter;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DIComponentFilterTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		ClassFinder classFinder = new ClassFinder();
		DIComponentFilter filter = new DIComponentFilter();
		List<Class> classes = classFinder.findClassesInsidePackage("lv");
		List<Class> diComponents = filter.filter(classes);
		diComponents.forEach(aClass -> {
			System.out.println(aClass.getName());
		});
	}

}