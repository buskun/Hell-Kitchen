package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;
import game.Character;
import utility.bounding.BoundingArea;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class GameScene extends Scene {

    public GameScene(WindowFrame _window, Controller _controller) {
        super(_window, _controller);
    }

    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/gameScene/background.png");
        imageLoader.add("head", "resources/gameScene/Iconplayer.png");
        imageLoader.add("character", "resources/gameScene/Iconplayer.png");
        imageLoader.add("refrigerator", "resources/gameScene/Fridge.png");
        imageLoader.add("bar", "resources/gameScene/bar.png");
        imageLoader.add("plate", "resources/gameScene/plate.png");
        imageLoader.add("Cutting", "resources/gameScene/Cutting.png");
        imageLoader.add("Drinking", "resources/gameScene/Drinking.png");
        imageLoader.add("pot", "resources/gameScene/pot.png");
        imageLoader.add("pan", "resources/gameScene/pan.png");
        imageLoader.add("scoreBar","resources/gameScene/scoreBar.png");
        imageLoader.add("timeBar","resources/gameScene/timeBar.png");

        imageLoader.add("backgroundFrame", "resources/refrigeratorFrame/Bgfridge.png");
        imageLoader.add("BurgerFridge", "resources/refrigeratorFrame/Burger.png");
        imageLoader.add("FishFridge", "resources/refrigeratorFrame/Fish.png");
        imageLoader.add("KetchupFridge", "resources/refrigeratorFrame/Ketchup.png");
        imageLoader.add("LettuceFridge", "resources/refrigeratorFrame/Lettuce.png");
        imageLoader.add("MeatFridge", "resources/refrigeratorFrame/Meat.png");
        imageLoader.add("PotatoFridge", "resources/refrigeratorFrame/Potato.png");
        imageLoader.add("RiceFridge", "resources/refrigeratorFrame/Rice.png");

        imageLoader.add("iconRice", "resources/iconPickup/Icon+burgur.png");
        imageLoader.add("iconFish", "resources/iconPickup/Icon+fish.png");
        imageLoader.add("iconLettuce", "resources/iconPickup/Icon+lettuce.png");
        imageLoader.add("iconMeat", "resources/iconPickup/Icon+meat.png");
        imageLoader.add("iconPotato", "resources/iconPickup/Icon+potato.png");
        imageLoader.add("IconWithBurger", "resources/iconPickup/IconWithBurger.png");
        imageLoader.add("IconWithFantaL", "resources/iconPickup/IconWithFantaL.png");
        imageLoader.add("IconWithFantaM", "resources/iconPickup/IconWithFantaM.png");
        imageLoader.add("IconWithFantaS", "resources/iconPickup/IconWithFantaS.png");
        imageLoader.add("IconWithFishCut", "resources/iconPickup/IconWithFishCut.png");
        imageLoader.add("IconWithFishfin", "resources/iconPickup/IconWithFishfin.png");
        imageLoader.add("IconWithFishnChip", "resources/iconPickup/IconWithFishnChip.png");
        imageLoader.add("IconWithKetchupCut", "resources/iconPickup/IconWithKetchupCut.png");
        imageLoader.add("IconWithMeatCut", "resources/iconPickup/IconWithMeatCut.png");
        imageLoader.add("IconWithMeatfin", "resources/iconPickup/IconWithMeatfin.png");
        imageLoader.add("IconWithPakCut", "resources/iconPickup/IconWithPakCut.png");
        imageLoader.add("IconWithPepsiL", "resources/iconPickup/IconWithPepsiL.png");
        imageLoader.add("IconWithPepsiM", "resources/iconPickup/IconWithPepsiM.png");
        imageLoader.add("IconWithPepsiS", "resources/iconPickup/IconWithPepsiS.png");
        imageLoader.add("IconWithPotatoCut", "resources/iconPickup/IconWithPotatoCut.png");
        imageLoader.add("IconWithPotatofin", "resources/iconPickup/IconWithPotatofin.png");
        imageLoader.add("IconWithSoup", "resources/iconPickup/IconWithSoup.png");
        imageLoader.add("IconWithSpriteL", "resources/iconPickup/IconWithSpriteL.png");
        imageLoader.add("IconWithSpriteM", "resources/iconPickup/IconWithSpriteM.png");
        imageLoader.add("IconWithSpriteS", "resources/iconPickup/IconWithSpriteS.png");
        imageLoader.add("IconWithSushi", "resources/iconPickup/IconWithSushi.png");




        imageLoader.add("Bgcutting", "resources/cuttingFrame/Bgcutting.png");
        imageLoader.add("Potato", "resources/cuttingFrame/Potato.png");
        imageLoader.add("Meat", "resources/cuttingFrame/Meat.png");
        imageLoader.add("Lettuce", "resources/cuttingFrame/Lettuce.png");
        imageLoader.add("Fish", "resources/cuttingFrame/Fish.png");
        imageLoader.add("FishCut", "resources/cuttingFrame/FishCut.png");
        imageLoader.add("KetchupCut", "resources/cuttingFrame/KetchupCut.png");
        imageLoader.add("LettuceCut", "resources/cuttingFrame/LettuceCut.png");
        imageLoader.add("MeatCut", "resources/cuttingFrame/MeatCut.png");

        imageLoader.add("bgPan", "resources/panFrame/Bgkata.png");
        imageLoader.add("BgkataOil", "resources/panFrame/BgkataOil.png");
        imageLoader.add("FishCut1", "resources/panFrame/FishCut.png");
        imageLoader.add("FishCut2", "resources/panFrame/Fish2.png");
        imageLoader.add("FishCut3", "resources/panFrame/Fish3.png");
        imageLoader.add("FrenchfriesCut1", "resources/panFrame/FrenchfriesCut.png");
        imageLoader.add("FrenchfriesCut2", "resources/panFrame/Frenchfries2.png");
        imageLoader.add("FrenchfriesCut3", "resources/panFrame/Frenchfries3.png");
        imageLoader.add("MeatCut1", "resources/panFrame/MeatCut.png");
        imageLoader.add("MeatCut2", "resources/panFrame/Meat2.png");
        imageLoader.add("MeatCut3", "resources/panFrame/Meat3.png");
        imageLoader.add("offBtn", "resources/panFrame/Off.png");
        imageLoader.add("onBtn", "resources/panFrame/On.png");

        imageLoader.add("Bgdrink", "resources/waterFrame/Bgdrink.png");
        imageLoader.add("Fanta", "resources/waterFrame/Fanta.png");
        imageLoader.add("Pepsi", "resources/waterFrame/Pepsi.png");
        imageLoader.add("Sprite", "resources/waterFrame/Sprite.png");
        imageLoader.add("DrinkL", "resources/waterFrame/DrinkL.png");
        imageLoader.add("DrinkM", "resources/waterFrame/DrinkM.png");
        imageLoader.add("DrinkS", "resources/waterFrame/DrinkS.png");

        imageLoader.add("Bgpot", "resources/ponFrame/Bgpot.png");
        imageLoader.add("PotWithBoilWater", "resources/ponFrame/PotWithBoilWater.png");
        imageLoader.add("Potwithketchup1", "resources/ponFrame/Potwithketchup1.png");
        imageLoader.add("PotWithKetchup2", "resources/ponFrame/PotWithKetchup2.png");
        imageLoader.add("PotWithKetchup3", "resources/ponFrame/PotWithKetchup3.png");
        imageLoader.add("PotWithRice1", "resources/ponFrame/PotWithRice1.png");
        imageLoader.add("PotWithRice2", "resources/ponFrame/PotWithRice2.png");
        imageLoader.add("PotWithRice3", "resources/ponFrame/PotWithRice3.png");
        imageLoader.add("PotWithWater", "resources/ponFrame/PotWithWater.png");

        imageLoader.add("orderburger","resources/order/orderburger.png");
        imageLoader.add("orderFishandchips","resources/order/orderFishandchips.png");
        imageLoader.add("orderFrenchfries","resources/order/orderFrenchfries.png");
        imageLoader.add("orderSoup","resources/order/orderSoup.png");
        imageLoader.add("orderSushi","resources/order/orderSushi.png");
        imageLoader.add("orderWaterfantaL","resources/order/orderWaterfantaL.png");
        imageLoader.add("orderWaterfantaM","resources/order/orderWaterfantaM.png");
        imageLoader.add("orderWaterfantaS","resources/order/orderWaterfantaS.png");
        imageLoader.add("orderWaterPepsiL","resources/order/orderWaterPepsiL.png");
        imageLoader.add("orderWaterPepsiM","resources/order/orderWaterPepsiM.png");
        imageLoader.add("orderWaterPepsiS","resources/order/orderWaterPepsiS.png");
        imageLoader.add("orderWaterSpriteL","resources/order/orderWaterSpriteL.png");
        imageLoader.add("orderWaterSpriteM","resources/order/orderWaterSpriteM.png");
        imageLoader.add("orderWaterSpriteS","resources/order/orderWaterSpriteS.png");




    }

    private BoundingArea map = new BoundingArea();
    private Character character;
    private JLabel refrigerator = new JLabel();
    private JLabel bar = new JLabel();
    private JLabel plate = new JLabel();
    private JLabel Cutting = new JLabel();
    private JLabel Drinking = new JLabel();
    private JLabel pot = new JLabel();
    private JLabel pan = new JLabel();
    private JLabel barScore = new JLabel();
    private JLabel barTime = new JLabel();

    private HashMap<String, Boolean> interactable = new HashMap<>();


    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(getImageLoader().getIcon("background"));

        character = new Character(this, map, interactable, this::onHoldItem);

        cm.setIcon(refrigerator, imageLoader.getIcon("refrigerator"), CM.size(15, 40));
        cm.setBounds(refrigerator, CM.grid(85, 20, 15, 40));
        add(refrigerator);
        map.add("refrigerator", refrigerator);

        cm.setIcon(plate, imageLoader.getIcon("plate"), CM.size(10, CMFlag.BY_H));
        cm.setBounds(plate, CM.grid(30, 29, CM.size(10, CMFlag.BY_H)));
        add(plate);
        map.add("plate", plate);

        cm.setIcon(bar, imageLoader.getIcon("bar"), CM.size(44, 37));
        cm.setBounds(bar, CM.grid(24, 29, 44, 37));
        add(bar);
        map.add("bar", bar);

        cm.setIcon(Cutting, imageLoader.getIcon("Cutting"), CM.size(7, 13));
        cm.setBounds(Cutting, CM.grid(0, 28, 7, 15));
        add(Cutting);
        map.add("Cutting", Cutting);

        cm.setIcon(Drinking, imageLoader.getIcon("Drinking"), CM.size(7, 22));
        cm.setBounds(Drinking, CM.grid(0, 50, 7, 22));
        add(Drinking);
        map.add("Drinking", Drinking);

        cm.setIcon(pot, imageLoader.getIcon("pot"), CM.size(15, 12));
        cm.setBounds(pot, CM.grid(34, 82, 15, 12));
        add(pot);
        map.add("pot", pot);

        cm.setIcon(pan, imageLoader.getIcon("pan"), CM.size(14, CMFlag.BY_H));
        cm.setBounds(pan, CM.grid(54, 82, CM.size(14, CMFlag.BY_H)));
        add(pan);
        map.add("pan", pan);

        JLabel totalScore = new JLabel();
        totalScore.setText("0");
        totalScore.setFont(new Font("Dimbo", Font.PLAIN, 35));
        cm.setBounds(totalScore, CM.grid(92, -4, CM.size(25, CMFlag.BY_H)));
        add(totalScore);
        cm.setIcon(barScore,imageLoader.getIcon("scoreBar"),CM.size(19, 14));
        cm.setBounds(barScore,CM.grid(80,2,CM.size(19, 14)));
        add(barScore);
        map.add("barScore",barScore);

        JLabel totalTime = new JLabel();
        totalTime.setText("0");
        totalTime.setFont(new Font("Dimbo", Font.PLAIN, 35));
        cm.setBounds(totalTime, CM.grid(92, 74, CM.size(25, CMFlag.BY_H)));
        add(totalTime);

        cm.setIcon(barTime,imageLoader.getIcon("timeBar"),CM.size(19, 14));
        cm.setBounds(barTime,CM.grid(80,80,CM.size(19, 14)));
        add(barTime);
        map.add("barTime",barTime);




        map.addIntersectionListener((name) -> {
            interactable.put(name, true);
            return true;
        });

        ready();
    }

    @Override
    public void tick() {
        ImageLoader imageLoader = getImageLoader();

        boolean isActionKeyPressed = isKeyPressed(KeyEvent.VK_SPACE, true);
        CM cm = getCM();

        if (isActionKeyPressed) character.doAction();
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if (isKeyPressed(KeyEvent.VK_UP))
            character.move(0, -1);
        if (isKeyPressed(KeyEvent.VK_DOWN))
            character.move(0, 1);
        if (isKeyPressed(KeyEvent.VK_LEFT))
            character.move(-1, 0);
        if (isKeyPressed(KeyEvent.VK_RIGHT))
            character.move(1, 0);
    }

    public void onHoldItem(String item) {
        ImageLoader imageLoader = getImageLoader();
        CM cm = getCM();

//        cm.setIcon(character, imageLoader.getIcon(item));
//        cm.recalculateIcon(character);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
