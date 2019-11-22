package main.java;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main implements InterfaceSuma {

	public static void main(String args[]) {

		Main main = new Main();

		System.out.println("Función normal: " + main.sumar(2, 3));

		System.out.println("Función con interface: " + main.aplicarSuma(1, 1));

		InterfaceSuma interfaceSuma = (a, b) -> a + b;

		System.out.println("Función de orden superior: " + main
			.sumarConFuncionDeOrdenSuperior(interfaceSuma, 10, 10));

		// Interfaz funcional Function<T, R>
		/*
		interface Function<T t, R r> {
			R apply(t)
		 */
		Function<String, String> convertirAMayusculas = nombre -> nombre.toUpperCase();
		main.imprimirNombreEnMayusculaConFunction(convertirAMayusculas, "pipe");

		// Interfaz funcional BiFunction<T, U, R>
		/*
		interface BiFunction<T, U, R> {
			R apply(t, u)
		 */

		// Interfaz funcional Predicate<T>
		/*
		interface Predicate<T t> {
			Boolean test(t)
		 */
		BiFunction<List<Integer>, Predicate<Integer>, List<Integer>> filtrar = (lista, predicado) ->
			lista.stream().filter(numero -> predicado.test(numero)).collect(Collectors.toList());
		List<Integer> listaNumeros = Arrays.asList(1, 4, -7, 10, -5, 90);
		System.out.println("Lista obtenida con BiFunction<T, U, R> y Predicate<T>: " +
			filtrar.apply(listaNumeros, numero -> numero > 0));

		// Interfaz funcional Consumer<T>
		/*
		interface Consumer<T t> {
			void accept(t)
		 */
		List<String> listaNombres = Arrays.asList("Santiago", "Pipe", "Laura");
		main.filtrarNombres(listaNombres, 6,
			nombres ->
				System.out.println("Nombre obtenido con Predicate<T> y Consumer<T>: " + nombres));
	}

	public int sumar(int a, int b) {
		return a + b;
	}

	@Override
	public int aplicarSuma(int a, int b) {
		return a + b;
	}

	public int sumarConFuncionDeOrdenSuperior(InterfaceSuma interfaceSuma, int a, int b) {
		return interfaceSuma.aplicarSuma(a, b);
	}

	public void imprimirNombreEnMayusculaConFunction(Function<String, String> funcion,
		String nombre) {
		System.out.println(
			"Nombre convertido en mayúscula utilizando Function<T, R>: " + funcion.apply(nombre));
	}

	public void filtrarNombres(List<String> listaNombres, int maximoCaracteres,
		Consumer<String> consumer) {
		listaNombres.stream().filter(logicaPredicado(maximoCaracteres)).forEach(consumer);
	}

	public Predicate<String> logicaPredicado(int maximoCaracteres) {
		return nombre -> nombre.length() < maximoCaracteres;
	}
}
