import java.text.MessageFormat;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {
  protected static Scanner userInput = new Scanner(System.in);
  protected static byte seleccion;

  public static void main(String[] args) {
    Juego.iniciar();
    seleccion = userInput.nextByte();
    userInput.nextLine();

    switch (seleccion) {
      case 1:
        Juego.jugar();
        break;
      default:
        Juego.salir();
        break;

    }
  }

  static class Juego {
    private static String[] opciones = { "Juego Nuevo", "Salir" };
    private static String[] palabras = {
        "filtrado",
        "compilar",
        "formatear",
        "machete",
        "jholver",
        "javascript",
        "backend",
        "fullstack",
        "instancia",
        "referencia"
    };
    private static int luckyNum = (int) new Random().nextInt(10);
    private static boolean isDone = false;

    static void iniciar() {
      System.out.println("Bienvenidos al ahorcado de Jholver!!");
      System.out.println("Por favor elige la opcion");
      for (int i = 0; i < opciones.length; i++) {
        System.out.println(MessageFormat.format("{0} - {1}", i + 1, opciones[i]));
      }
      System.out.print(">>");
    }

    static void jugar() {
      while (!isDone) {
        String luckyWord = palabras[luckyNum];

        imprimirPantalla(null, luckyWord);

      }
    }

    private static void imprimirPantalla(ArrayList<String> guess, String palabraMagica) {
      limpiarPantalla();
      ArrayList<String> adivinadas = new ArrayList<String>();
      String intento;
      System.out.println("Adivina de una letra o la palabra!");
      for (int i = 0; i < palabraMagica.length(); i++) {
        for (int j = 0; j < adivinadas.size(); j++) {
          if (palabraMagica.charAt(i) == adivinadas.get(j).charAt(0)) {
            System.out.print(palabraMagica.charAt(i));
          }
        }
        System.out.print('_');
      }
      System.out.println();
      System.out.print(">>");
      intento = userInput.nextLine();

      if (intento.length() > 1) {
        if (intento == palabraMagica) {
          System.out.println("Lo lograste!!!");
          esperar();
          isDone = true;
        }
      } else {
        if (palabraMagica.contains(intento)) {
          System.out.println(palabraMagica.indexOf(intento));
          adivinadas.add(intento);
          esperar();
        }
      }

      imprimirPantalla(adivinadas, palabraMagica);

    }

    static void limpiarPantalla() {
      ProcessBuilder consola = new ProcessBuilder();
      try {
        if (System.getProperty("os.name").contains("Windows")) {
          consola.command("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
          consola.command("clear").inheritIO().start().waitFor();
        }
      } catch (IOException | InterruptedException ex) {
        ex.printStackTrace();
      }
    }

    static void esperar() {
      System.out.println("Presiona enter para continuar...");
      userInput.nextLine();
    }

    static void salir() {
      System.exit(0);
    }
  }
}
