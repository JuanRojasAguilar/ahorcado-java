import java.text.MessageFormat;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.TreeSet;
import java.util.SortedSet;

public class Main {
  protected static Scanner userInput = new Scanner(System.in);
  protected static byte seleccion;
  private static SortedSet<String> sortedWord = new TreeSet<String>();
  private static SortedSet<String> sortedGuesses = new TreeSet<String>();

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
    private static void imprimirPantalla(SortedSet<String> guess, String palabraMagica) {
      limpiarPantalla();
      String intento;
      System.out.println("Adivina de una letra o la palabra!");
      for (int i = 0; i < palabraMagica.length(); i++) {
        sortedWord.add(String.valueOf(palabraMagica.charAt(i)));
        boolean found = false;
        for (int j = 0; j < sortedGuesses.size(); j++) {
          if (sortedGuesses.contains(String.valueOf(palabraMagica.charAt(i)))) {
            found = true;
          }
        }
        if (found == true) {
            System.out.print(palabraMagica.charAt(i));
            found = false;
        } else {
            System.out.print('_');
        }
      }
      System.out.println();
      System.out.print(">>");
      intento = userInput.nextLine();
        
      if (intento == "") {
        imprimirPantalla(sortedGuesses, palabraMagica);
      }
      if (intento.length() > 1) {
        if (intento == palabraMagica) {
          ganar(palabraMagica);
        }
      } else {
        if (palabraMagica.contains(intento)) {
          System.out.println(palabraMagica.indexOf(intento));
          sortedGuesses.add(intento);
          if (sortedGuesses.equals(sortedWord)) {
            ganar(palabraMagica);
          }
          esperar();
        }
      }

      imprimirPantalla(sortedGuesses, palabraMagica);

    }
    static void ganar(String word) {
        limpiarPantalla();
        System.out.println("La palabra era: " + word);
        System.out.println("Has superado el desafio!!!");
        esperar();
        salir();
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
