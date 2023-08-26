import java.util.Random;



public abstract class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    boolean isPlayerTurn = determineFirstTurn();
    public BattleLoc(Player player, String name,Obstacle obstacle,String award,int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;

    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol Burada " + obsNumber +" tane "+  this.getObstacle().getName() + " yaşıyor!!");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();

        if(selectCase.equals("S") && combat(obsNumber) ){
                System.out.println(this.getName() + "tüm düşmanları yendiniz !");
                return true;

        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz !");
            return false;
        }

        return true;
    }


    public boolean combat(int obsNumber){

        for(int i = 1; i < obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                if (isPlayerTurn) {
                    System.out.println("Oyuncu ilk hamleyi yapıyor.");
                    System.out.print("<V>ur veya <K>aç :");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println("siz vurdunuz !");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if(this.getObstacle().getHealth() > 0 ) {
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    }
                }
                    else{
                        System.out.println("Canavar ilk hamleyi yapıyor!");
                        if(this.getObstacle().getHealth() > 0 ) {
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                            System.out.println("Oyuncunun hamle sırası");
                            System.out.print("<V>ur veya <K>aç :");
                            String selectCombat = input.nextLine().toUpperCase();
                            if (selectCombat.equals("V")) {
                                System.out.println("siz vurdunuz !");
                                this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                                afterHit();
                            }
                        }
                    }
            }

            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("düşmanı yendiniz !");
                System.out.println(this.getObstacle().getAward()  + " para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());

            }
            if(i == obsNumber&&this.getObstacle().getName().equals("Zombie")){
                this.getPlayer().getInventory().setWater(true);
                System.out.println("su kazandınız! ");
            }
            if(i == obsNumber&&this.getObstacle().getName().equals("Vampire")){
                this.getPlayer().getInventory().setFood(true);
                System.out.println("yemek kazandınız!");
            }
            if(i == obsNumber&&this.getObstacle().getName().equals("Bear")){
                this.getPlayer().getInventory().setFirewood(true);
                System.out.println("odun kazandınız!");
            }

        }

        return true;
    }
    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;

    }


    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.obstacle.getHealth());
        System.out.println();
        System.out.println("-----------");
    }

    public void playerStats(){
        System.out.println("Oyuncu değerleri");
        System.out.println("----------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırf :" + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();

    }
    public void obstacleStats(int i){
        System.out.println(i + "." + this.getObstacle().getName() + "Değerleri : ");
        System.out.println("-----------------------------------------");
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Ödül : " + this.getObstacle().getAward());
        System.out.println();

    }
    public static boolean determineFirstTurn() {
        Random random = new Random();
        return random.nextBoolean(); // %50 ihtimalle true veya false dönecek
    }





    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}

