package main.java;

import java.util.function.Function;

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
			R apply(T, t)
		 */
		Function<String, String> convertirAMayusculas = nombre -> nombre.toUpperCase();
		main.imprimirNombreEnMayusculaConFunction(convertirAMayusculas, "pipe");

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
		System.out.println("Function<T, R>: " + funcion.apply(nombre));
	}
}
