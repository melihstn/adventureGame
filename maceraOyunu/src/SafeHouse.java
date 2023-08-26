public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("güvenli evdesiniz.");
        System.out.println("canınız yenilendi");
        this.getPlayer().setHealth(this.getPlayer().getOrginalHealth());
        return true;
    }
}
