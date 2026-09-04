import java.util.ArrayList;
import java.util.Scanner;


class Linjestig {
    public static void main(String[] args) {
        
    
        // Scanner = klass i java för att läsa indata
        // hasNextLine() = Returnerar "false" när ingen rad finns kvar att läsa
        // nextLine = Hämtar nästa rad som text
        Scanner scanner = new Scanner(System.in);

        // Lista som ska hålla ALLA punkter vi läser in.
        // Varje punkt är i sig en int-array, t.ex. {1, 2}
        // ArrayList<int[]> = en lista som kan innehålla flera int-arrayer
        ArrayList<int[]> punktLista = new ArrayList<>();
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Om raden är tom (t.ex. en extra "tom" rad vid manuell inmatning),
            // hoppa över den och gå direkt till nästa varv i loopen
            if (line.trim().isEmpty()) {
                continue;
            }

            // Ta bort hakparenteser
            line = line.replace("[", "").replace("]", "");

            // Dela upp texten vid varje kommatecken "1, 2" -> "1" och " 2"
            String[] delar = line.split(",");

            // Skapar en int-array (heltalslista) med samma antal platser som delar.length
            int[] punkt = new int[delar.length];

            // Går igenom varje textdel, konverterar till tal och sparar i punkt-array
            for(int i = 0; i < delar.length; i++) {
                // .trim() tar bort varje mellanslag i början/slutet
                // Integer.parseInt(...) gör om texten "2" till talet 2
                punkt[i] = Integer.parseInt(delar[i].trim());  
            }

            // Lägger till den färdiga punkten i vår lista över alla punkter
            punktLista.add(punkt);

            }

        // Variabel som håller reda på den sammanlagda längden av hela linjestigen.
        // Börjar på 0.0 eftersom vi inte lagt till något avstånd än.
        // double används eftersom avstånd nästan alltid blir decimaltal.
        double totalLangd = 0.0;

        // Går igenom punktLista två och två: punkt 0 & 1, sedan 1 & 2, osv.
        // OBS: loopen slutar EN punkt tidigare (punktLista.size() - 1),
        // eftersom vi alltid tittar på ETT PAR punkter åt gången (aktuell + nästa)
        for (int i = 0; i < punktLista.size() - 1; i++) {
            int[] punktA = punktLista.get(i);       // hämtar punkt nummer i
            int[] punktB = punktLista.get(i + 1);   // hämtar NÄSTA punkt

            // Anropar vår hjälpmetod och lägger till resultatet i totalen
            totalLangd += avstand(punktA, punktB);
        }

        // Skriver ut totalsumman avrundad till 2 decimaler
        // %.2f = flyttal med 2 decimaler, %n = radbrytning
        System.out.printf("%.2f%n", totalLangd);
        }

    // Egen hjälpmetod som räknar ut det euklidiska avståndet mellan två punkter.
    // Fungerar oavsett hur många dimensioner (koordinater) punkterna har.
    // "static" krävs eftersom main (som anropar metoden) också är static.
    static double avstand(int[] punktA, int[] punktB) {
        // Variabel som samlar ihop summan av (skillnad)^2 för varje dimension
        double summa = 0.0;

        // Går igenom varje koordinat/dimension i punkterna
        // (punktA.length = antal dimensioner, t.ex. 2 för [x, y])
        for (int i = 0; i < punktA.length; i++) {
            // Skillnaden mellan koordinat i hos punktB och punktA
            double skillnad = punktB[i] - punktA[i];

            // Lägger till (skillnad)^2 till summan
            // skillnad * skillnad är samma sak som Math.pow(skillnad, 2), men snabbare
            summa += skillnad * skillnad;
        }

        // Kvadratroten av summan ger själva avståndet mellan punkterna
        return Math.sqrt(summa);
    }
    }