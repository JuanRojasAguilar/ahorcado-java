import java.text.MessageFormat;
import java.io.IOException;
import java.util.Scanner;
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
    private static Random luckyNum = new Random();
  
    static void iniciar() {
      System.out.println("Bienvenidos al ahorcado de Jholver!!");
      System.out.println("Por favor elige la opcion");
      for (int i = 0; i < opciones.length; i++) {
        System.out.println(MessageFormat.format("{0} - {1}", i + 1, opciones[i]));
      }
      System.out.print(">>");
    }

    static void jugar() {
      boolean isDone = false;
      String intento;
      int numeroPalabra = luckyNum.nextInt(10);
      while (isDone == false) {
        String luckyWord = palabras[numeroPalabra];
        char[] adivinadas = [];

        System.out.println("Adivina de una letra o la palabra!");
        imprimirPantalla(null,luckyWord);

      }
    }

    private static void imprimirPantalla(String guess, String palabraMagica) {
        limpiarPantalla();
      System.out.print(">>");
      intento = userInput.nextLine();
      for(int i = 0; i < palabraMagica.length(); i++) {
        for (int j =)
        System.out.print('_');
      }
      System.out.println();
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

    static void salir() { System.exit(0); }
  }
}
