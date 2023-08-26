import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera oyununa hoşgeldiniz.");
        System.out.print("Lütfen karakter ismini giriniz : ");

        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("sayın " + player.getName() + " Bu karanlık ve sisli adaya hoşgeldiniz !");
        System.out.println("Burada yaşananların hepsi gerçek!");
        System.out.println("lütfen karakter seçiniz.");
        System.out.println("-------------------------------------------------------------------------");
        player.selectChar();

        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("####### Bölgeler ##########");
            System.out.println();
            System.out.println("1- Güvenli Ev");
            System.out.println("2- Eşya Dükkanı ");
            System.out.println("3- Mağaraya git ! Dikkatli ol zombi çıkabilir !");
            System.out.println("4- Ormana git ! Dikkatli ol vampir çıkabilir !");
            System.out.println("5- Nehire gir ! Dikkatli ol ayı çıkabilir !");
            System.out.println("6- Madene gir! Dikkatli ol yılan çıkabilir!");
            System.out.println("0 - Çıkış Yap");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectloc = input.nextInt();

            switch (selectloc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Ore(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz!");
            }
            if(location == null){
                System.out.println("Bu karanlık ve sisli adadan çabuk vaz geçtiniz !!");
                break;
            }
            if(location.getClass().getName().equals("SafeHouse")){
                if (player.getInventory().isWater()&&player.getInventory().isFood()&&player.getInventory().isFirewood()){
                    System.out.println("Tebrikler oyunu kazandınız!");
                    break;
                }
            }

            if(!location.onLocation()) {
                System.out.println("GAME OVER");
                break;
            }
        }
    }

}
