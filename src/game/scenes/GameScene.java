package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;
import game.Character;
import game.Data;
import game.Order;
import game.Recipe;
import utility.Utility;
import utility.bounding.BoundingArea;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class GameScene extends Scene {

    public GameScene(WindowFrame _window, Controller _controller) {
        super(_window, _controller);
    }

    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/gameScene2/Bg.png");
        imageLoader.add("orderBar", "resources/gameScene2/Order.png");
        imageLoader.add("TableCenter", "resources/gameScene2/TableCenter.png");
        imageLoader.add("Tableleft", "resources/gameScene2/Tableleft.png");
        imageLoader.add("Tabledown", "resources/gameScene2/Tabledown.png");
        imageLoader.add("Sent", "resources/gameScene2/Sent.png");
        imageLoader.add("bar", "resources/gameScene2/TableCenter.png");
        imageLoader.add("plate", "resources/gameScene2/Dish.png");
        imageLoader.add("Cuttingbar", "resources/cuttingFrame/cuttingBar.png");
        imageLoader.add("knife", "resources/cuttingFrame/Knife.png");
        imageLoader.add("transparent", "resources/cuttingFrame/transparent.png");

        imageLoader.add("trash", "resources/gameScene/Trash.png");

        imageLoader.add("character", "resources/gameScene/Iconplayer.png");
        imageLoader.add("refrigerator", "resources/gameScene/Fridge.png");

        imageLoader.add("Cutting", "resources/gameScene/Cutting.png");
        imageLoader.add("Drinking", "resources/gameScene/Drinking.png");
        imageLoader.add("potOnbar", "resources/gameScene/pot.png");
        imageLoader.add("panOnbar", "resources/gameScene/pan.png");
        imageLoader.add("scoreBar", "resources/gameScene/scoreBar.png");
        imageLoader.add("timeBar", "resources/gameScene/timeBar.png");

        imageLoader.add("backgroundFrame", "resources/refrigeratorFrame/Bgfridge.png");

        imageLoader.add("avatar-food-burger", "resources/avatar/IconWithBurger.png");
        imageLoader.add("avatar-food-fish_n_chip", "resources/avatar/IconWithFishnChip.png");
        imageLoader.add("avatar-food-sushi", "resources/avatar/IconWithSushi.png");
        imageLoader.add("avatar-food-soup", "resources/avatar/IconWithSoup.png");

        imageLoader.add("avatar-item-bread", "resources/avatar/Icon+burgur.png");
        imageLoader.add("item-bread", "resources/refrigeratorFrame/Burger.png");

        imageLoader.add("avatar-item-rice", "resources/avatar/Icon+rice.png");
        imageLoader.add("avatar-item-rice-boiled", "resources/avatar/Icon+rice.png");
        imageLoader.add("item-rice", "resources/refrigeratorFrame/Rice.png");

        imageLoader.add("avatar-item-fish", "resources/avatar/Icon+fish.png");
        imageLoader.add("avatar-item-fish-cut", "resources/avatar/IconWithFishCut.png");
        imageLoader.add("avatar-item-fish-cut-fried", "resources/avatar/IconWithFishfin.png");
        imageLoader.add("item-fish", "resources/refrigeratorFrame/Fish.png");
        imageLoader.add("cutting-item-fish", "resources/cuttingFrame/Fish.png");
        imageLoader.add("cutting-item-fish-cut", "resources/cuttingFrame/FishCut.png");
        imageLoader.add("pan-item-fish-cut-1", "resources/panFrame/FishCut.png");
        imageLoader.add("pan-item-fish-cut-2", "resources/panFrame/Fish2.png");
        imageLoader.add("pan-item-fish-cut-3", "resources/panFrame/Fish3.png");

        imageLoader.add("avatar-item-lettuce", "resources/avatar/Icon+lettuce.png");
        imageLoader.add("avatar-item-lettuce-cut", "resources/avatar/IconWithPakCut.png");
        imageLoader.add("item-lettuce", "resources/refrigeratorFrame/Lettuce.png");
        imageLoader.add("cutting-item-lettuce", "resources/cuttingFrame/Lettuce.png");
        imageLoader.add("cutting-item-lettuce-cut", "resources/cuttingFrame/LettuceCut.png");

        imageLoader.add("avatar-item-tomato", "resources/avatar/Icon+tomato.png");
        imageLoader.add("avatar-item-tomato-cut", "resources/avatar/IconWithKetchupCut.png");
        imageLoader.add("avatar-item-tomato-cut-boiled", "resources/avatar/IconWithSoup.png");
        imageLoader.add("item-tomato", "resources/refrigeratorFrame/Ketchup.png");
        imageLoader.add("cutting-item-tomato", "resources/cuttingFrame/Ketchup.png");
        imageLoader.add("cutting-item-tomato-cut", "resources/cuttingFrame/KetchupCut.png");

        imageLoader.add("avatar-item-meat", "resources/avatar/Icon+meat.png");
        imageLoader.add("avatar-item-meat-cut", "resources/avatar/IconWithMeatCut.png");
        imageLoader.add("avatar-item-meat-cut-fried", "resources/avatar/IconWithMeatfin.png");
        imageLoader.add("item-meat", "resources/refrigeratorFrame/Meat.png");
        imageLoader.add("cutting-item-meat", "resources/cuttingFrame/Meat.png");
        imageLoader.add("cutting-item-meat-cut", "resources/cuttingFrame/MeatCut.png");
        imageLoader.add("pan-item-meat-cut-1", "resources/panFrame/MeatCut.png");
        imageLoader.add("pan-item-meat-cut-2", "resources/panFrame/Meat2.png");
        imageLoader.add("pan-item-meat-cut-3", "resources/panFrame/Meat3.png");

        imageLoader.add("avatar-item-potato", "resources/avatar/Icon+potato.png");
        imageLoader.add("avatar-item-potato-cut", "resources/avatar/IconWithPotatoCut.png");
        imageLoader.add("avatar-item-potato-cut-fried", "resources/avatar/IconWithPotatofin.png");
        imageLoader.add("item-potato", "resources/refrigeratorFrame/Potato.png");
        imageLoader.add("cutting-item-potato", "resources/cuttingFrame/Potato.png");
        imageLoader.add("cutting-item-potato-cut", "resources/cuttingFrame/PotatoCut2.png");
        imageLoader.add("pan-item-potato-cut-1", "resources/panFrame/FrenchfriesCut.png");
        imageLoader.add("pan-item-potato-cut-2", "resources/panFrame/Frenchfries2.png");
        imageLoader.add("pan-item-potato-cut-3", "resources/panFrame/Frenchfries3.png");
//        String name = "plate-item-bread-item-lettuce-cut-item-tomato-cut";
        //B->L->M->T
        imageLoader.add("dish", "resources/icondish/Dish.png");
        imageLoader.add("dish-item-bread-item-meat-cut-fried", "resources/icondish/Dish_bread_meat.png");
        imageLoader.add("dish-item-bread-item-lettuce-cut-item-meat-cut-fried", "resources/icondish/Dish_bread_meat_pak.png");
        imageLoader.add("dish-item-bread-item-meat-cut-fried-item-tomato-cut", "resources/icondish/Dish_bread_meat_tomato.png");
        imageLoader.add("dish-item-bread-item-lettuce-cut", "resources/icondish/Dish_bread_pak.png");
        imageLoader.add("dish-item-bread-item-lettuce-cut-item-tomato-cut", "resources/icondish/Dish_bread_tamato_pak.png");
        imageLoader.add("dish-item-bread-item-tomato-cut", "resources/icondish/Dish_bread_tomato.png");
        imageLoader.add("dish-item-lettuce-item-cut-meat-cut-fried", "resources/icondish/Dish_meat_pak.png");
        imageLoader.add("dish-item-lettuce-cut-item-meat-cut-fried-item-tomato-cut", "resources/icondish/Dish_meat_pak_tomato.png");
        imageLoader.add("dish-item-meat-cut-fried-item-tomato-cut", "resources/icondish/Dish_meat_tomato.png");
        imageLoader.add("dish-item-lettuce-cut-item-tomato-cut", "resources/icondish/Dish_tomato_pak.png");
        imageLoader.add("dish-item-lettuce-cut", "resources/icondish/DishWithPak.png");
        imageLoader.add("dish-item-meat-cut-fried", "resources/icondish/DishWithMeat.png");
        imageLoader.add("dish-item-bread", "resources/icondish/DishBurger(1).png");
        imageLoader.add("dish-item-tomato-cut", "resources/icondish/DishWithTomato.png");
        imageLoader.add("dish-food-burger", "resources/icondish/DishBurger.png");

        imageLoader.add("dish-item-fish-cut-fried", "resources/icondish/DishWithFish.png");
        imageLoader.add("dish-item-potato-cut-fried", "resources/icondish/DishWithFrenchfrie.png");
        imageLoader.add("dish-food-fish_n_chip", "resources/icondish/DishWithFishnChip.png");

        imageLoader.add("dish-item-fish-cut", "resources/icondish/DishWithFishforSushi.png");
        imageLoader.add("dish-item-rice-boiled", "resources/icondish/DishWithRice.png");
        imageLoader.add("dish-food-sushi", "resources/icondish/DishWithSushi.png");

        imageLoader.add("dish-food-soup", "resources/icondish/DishWithSoup.png");

        imageLoader.add("cup-s", "resources/waterFrame/GlassSizeS.png");
        imageLoader.add("cup-m", "resources/waterFrame/GlassSizeM.png");
        imageLoader.add("cup-l", "resources/waterFrame/GlassSizeL.png");

        imageLoader.add("sprite-l", "resources/waterFrame/GlassSizeL-sprite.png");
        imageLoader.add("sprite-m", "resources/waterFrame/GlassSizeM-sprite.png");
        imageLoader.add("sprite-s", "resources/waterFrame/GlassSizeS-sprite.png");

        imageLoader.add("pepsi-l", "resources/waterFrame/GlassSizeL-pepsi.png");
        imageLoader.add("pepsi-m", "resources/waterFrame/GlassSizeM-pepsi.png");
        imageLoader.add("pepsi-s", "resources/waterFrame/GlassSizeS-pepsi.png");

        imageLoader.add("fanta-l", "resources/waterFrame/GlassSizeL-fanta.png");
        imageLoader.add("fanta-m", "resources/waterFrame/GlassSizeM-fanta.png");
        imageLoader.add("fanta-s", "resources/waterFrame/GlassSizeS-fanta.png");

        imageLoader.add("drop-fanta", "resources/waterFrame/Fantadrop.png");
        imageLoader.add("drop-pepsi", "resources/waterFrame/Pepsidrop.png");
        imageLoader.add("drop-sprite", "resources/waterFrame/Spritedrop.png");

        imageLoader.add("avatar-drink-fanta-l", "resources/avatar/IconWithFantaL.png");
        imageLoader.add("avatar-drink-fanta-m", "resources/avatar/IconWithFantaM.png");
        imageLoader.add("avatar-drink-fanta-s", "resources/avatar/IconWithFantaS.png");
        imageLoader.add("avatar-drink-pepsi-l", "resources/avatar/IconWithPepsiL.png");
        imageLoader.add("avatar-drink-pepsi-m", "resources/avatar/IconWithPepsiM.png");
        imageLoader.add("avatar-drink-pepsi-s", "resources/avatar/IconWithPepsiS.png");
        imageLoader.add("avatar-drink-sprite-l", "resources/avatar/IconWithSpriteL.png");
        imageLoader.add("avatar-drink-sprite-m", "resources/avatar/IconWithSpriteM.png");
        imageLoader.add("avatar-drink-sprite-s", "resources/avatar/IconWithSpriteS.png");

        imageLoader.add("Bgcutting", "resources/cuttingFrame/Bgcutting.png");

        imageLoader.add("pan", "resources/panFrame/Bgkata.png");
        imageLoader.add("pan-on", "resources/panFrame/BgkataOil.png");

        imageLoader.add("offBtn", "resources/panFrame/off.png");
        imageLoader.add("onBtn", "resources/panFrame/on.png");

        imageLoader.add("Bgdrink", "resources/waterFrame/Bgdrink.png");
        imageLoader.add("Fanta", "resources/waterFrame/Fanta.png");
        imageLoader.add("Pepsi", "resources/waterFrame/Pepsi.png");
        imageLoader.add("Sprite", "resources/waterFrame/Sprite.png");
        imageLoader.add("DrinkL", "resources/waterFrame/DrinkL.png");
        imageLoader.add("DrinkM", "resources/waterFrame/DrinkM.png");
        imageLoader.add("DrinkS", "resources/waterFrame/DrinkS.png");


        imageLoader.add("pot", "resources/potFrame/PotWithWater.png");
        imageLoader.add("pot-on", "resources/potFrame/PotWithBoilWater.png");
        imageLoader.add("pot-item-tomato-cut-1", "resources/potFrame/Potwithketchup1.png");
        imageLoader.add("pot-item-tomato-cut-2", "resources/potFrame/PotWithKetchup2.png");
        imageLoader.add("pot-item-tomato-cut-3", "resources/potFrame/PotWithKetchup3.png");
        imageLoader.add("pot-item-rice-1", "resources/potFrame/PotWithRice1.png");
        imageLoader.add("pot-item-rice-2", "resources/potFrame/PotWithRice2.png");
        imageLoader.add("pot-item-rice-3", "resources/potFrame/PotWithRice3.png");

        imageLoader.add("order-food-burger", "resources/order/orderBurger.png");
        imageLoader.add("order-food-fish_n_chip", "resources/order/orderFishNChips.png");
        imageLoader.add("order-food-soup", "resources/order/orderSoup.png");
        imageLoader.add("order-food-sushi", "resources/order/orderSushi.png");
        imageLoader.add("order-drink-fanta-l", "resources/order/orderWaterfantaL.png");
        imageLoader.add("order-drink-fanta-m", "resources/order/orderWaterfantaM.png");
        imageLoader.add("order-drink-fanta-s", "resources/order/orderWaterfantaS.png");
        imageLoader.add("order-drink-pepsi-l", "resources/order/orderWaterPepsiL.png");
        imageLoader.add("order-drink-pepsi-m", "resources/order/orderWaterPepsiM.png");
        imageLoader.add("order-drink-pepsi-s", "resources/order/orderWaterPepsiS.png");
        imageLoader.add("order-drink-sprite-l", "resources/order/orderWaterSpriteL.png");
        imageLoader.add("order-drink-sprite-m", "resources/order/orderWaterSpriteM.png");
        imageLoader.add("order-drink-sprite-s", "resources/order/orderWaterSpriteS.png");
    }

    @Override
    public void loadAudio(AudioLoader audioLoader) {
        audioLoader.add("theme", "resources/soundtrack/" + Data.songList[(int) getController().getState("song")]);
        audioLoader.add("boil", "resources/soundtrack/Boil 1.wav");
        audioLoader.add("cut", "resources/soundtrack/cuttingLettuce.wav");
        audioLoader.add("soda", "resources/soundtrack/Soda Can.wav");
        audioLoader.add("Frying", "resources/soundtrack/Frying.wav");
    }

    private Recipe[] recipes = new Recipe[]{
            new Recipe("food-sushi", "item-fish-cut", "item-rice-boiled"),
            new Recipe("food-fish_n_chip", "item-fish-cut-fried", "item-potato-cut-fried"),
            new Recipe("food-soup", "item-tomato-cut-boiled"),
            new Recipe("food-burger", "item-tomato-cut", "item-lettuce-cut", "item-meat-cut-fried", "item-bread")
    };

    private BoundingArea map = new BoundingArea();
    private Character character;
    private JLabel refrigerator = new JLabel();
    private JLabel orderBar = new JLabel();
    private JLabel bar = new JLabel();
    private JLabel sent = new JLabel();
    private JLabel plate = new JLabel();
    private JLabel Cutting = new JLabel();
    private JLabel Drinking = new JLabel();
    private JLabel pot = new JLabel();
    private JLabel pan = new JLabel();
    private JLabel Tableleft = new JLabel();
    private JLabel Tabledown = new JLabel();
    private JLabel barScore = new JLabel();
    private JLabel barTime = new JLabel();
    private JLabel totalTime = new JLabel();
    private JLabel totalScore = new JLabel();
    private int remainingTime;
    private Runnable timer;
    private Runnable orderTimer;
    private ArrayList<String> currentItemsOnPlate = new ArrayList<>();
    private HashMap<String, Boolean> interactable = new HashMap<>();

    private ArrayList<String> allOrder = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();
    private int totalScoreI = 0;

    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(getImageLoader().getIcon("background"));

        character = new Character(this, map, interactable, this::actionToPlate, this::finishOrder);

        cm.setIcon(refrigerator, imageLoader.getIcon("refrigerator"), CM.size(15, 40));
        cm.setBounds(refrigerator, CM.grid(85, 21, 15, 40));
        add(refrigerator);
        map.add("refrigerator", refrigerator);

        cm.setIcon(plate, imageLoader.getIcon("plate"), CM.size(10, CMFlag.BY_H));
        cm.setBounds(plate, CM.grid(30, 30, CM.size(10, CMFlag.BY_H)));
        add(plate);
        map.add("plate", plate);

        cm.setIcon(bar, imageLoader.getIcon("bar"), CM.size(42, 36));
        cm.setBounds(bar, CM.grid(25, 32, 42, 36));
        add(bar);
        map.add("bar", bar);

        cm.setIcon(Cutting, imageLoader.getIcon("Cutting"), CM.size(7, 13));
        cm.setBounds(Cutting, CM.grid(0, 29, 7, 15));
        add(Cutting);
        map.add("Cutting", Cutting);

        cm.setIcon(Drinking, imageLoader.getIcon("Drinking"), CM.size(7, 22));
        cm.setBounds(Drinking, CM.grid(0, 50, 7, 22));
        add(Drinking);
        map.add("Drinking", Drinking);

        cm.setIcon(pot, imageLoader.getIcon("potOnbar"), CM.size(15, 12));
        cm.setBounds(pot, CM.grid(34, 84, 15, 12));
        add(pot);
        map.add("pot", pot);

        cm.setIcon(sent, imageLoader.getIcon("Sent"), CM.size(14, CMFlag.BY_H));
        cm.setBounds(sent, CM.grid(10, 0, CM.size(14, CMFlag.BY_H)));
        add(sent);
        map.add("sent", sent);

        cm.setIcon(pan, imageLoader.getIcon("panOnbar"), CM.size(14, CMFlag.BY_H));
        cm.setBounds(pan, CM.grid(54, 84, CM.size(14, CMFlag.BY_H)));
        add(pan);
        map.add("pan", pan);

        cm.setIcon(Tableleft, imageLoader.getIcon("Tableleft"), CM.size(10, 47));
        cm.setBounds(Tableleft, CM.grid(-2, 28, CM.size(10, 47)));
        add(Tableleft);
        map.add("Tableleft", Tableleft);

        cm.setIcon(Tabledown, imageLoader.getIcon("Tabledown"), CM.size(30, 25));
        cm.setBounds(Tabledown, CM.grid(33, 83, CM.size(30, 25)));
        add(Tabledown);

        cm.setIcon(orderBar, imageLoader.getIcon("orderBar"), CM.size(51, 14));
        cm.setBounds(orderBar, CM.grid(24, 0, CM.size(51, 14)));
        add(orderBar);
        orderBar.setLayout(new FlowLayout());
        map.add("orderBar", orderBar);

        JLabel trash = new JLabel();
        cm.setIcon(trash, imageLoader.getIcon("trash"), CM.size(15, 20, CMFlag.BY_H));
        cm.setBounds(trash, CM.grid(5, 75, CM.size(15, 20, CMFlag.BY_H)));
        add(trash);
        map.add("trash", trash);

        totalScore.setText(Integer.toString(totalScoreI));
        totalScore.setFont(new Font("Dimbo", Font.PLAIN, 35));
        cm.setBounds(totalScore, CM.grid(92, -4, CM.size(25, CMFlag.BY_H)));
        add(totalScore);
        cm.setIcon(barScore, imageLoader.getIcon("scoreBar"), CM.size(19, 14));
        cm.setBounds(barScore, CM.grid(80, 2, CM.size(19, 14)));
        add(barScore);
        map.add("barScore", barScore);

        totalTime.setText("0");
        totalTime.setFont(new Font("Dimbo", Font.PLAIN, 35));
        cm.setBounds(totalTime, CM.grid(92, 74, CM.size(25, CMFlag.BY_H)));
        add(totalTime);

        cm.setIcon(barTime, imageLoader.getIcon("timeBar"), CM.size(19, 14));
        cm.setBounds(barTime, CM.grid(80, 80, CM.size(19, 14)));
        add(barTime);
        map.add("barTime", barTime);

        map.addIntersectionListener((name) -> {
            interactable.put(name, true);
            return true;
        });

        cm.recalculate();

        ready();
    }

    @Override
    public void tick() {
        ImageLoader imageLoader = getImageLoader();

        boolean isActionKeyPressed = isKeyPressed(KeyEvent.VK_SPACE, true);

        if (isActionKeyPressed) character.doAction();
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if (isKeyPressed(KeyEvent.VK_UP))
            character.move(0, -1);
        else if (isKeyPressed(KeyEvent.VK_DOWN))
            character.move(0, 1);
        else if (isKeyPressed(KeyEvent.VK_LEFT))
            character.move(-1, 0);
        else if (isKeyPressed(KeyEvent.VK_RIGHT))
            character.move(1, 0);
    }

    public void actionToPlate(String item, Consumer<String> holdItem) {
        if (currentItemsOnPlate.stream().anyMatch(item::equals)) return;
        Optional<Recipe> matched = Arrays.stream(recipes).filter(recipe -> recipe.matchAll(currentItemsOnPlate)).findFirst();

        if (matched.isPresent()) {
            holdItem.accept(matched.get().getProduct());
            currentItemsOnPlate = new ArrayList<>();
        } else if (
                Arrays.stream(recipes)
                        .filter(recipe -> currentItemsOnPlate.size() == 0 || recipe.match(currentItemsOnPlate))
                        .anyMatch(recipe -> recipe.match(Collections.singletonList(item)))
        ) {
            currentItemsOnPlate.add(item);
            holdItem.accept("");
        }

        Optional<Recipe> newMatchedRecipe = Arrays.stream(recipes).filter(recipe -> recipe.matchAll(currentItemsOnPlate)).findFirst();

        if (currentItemsOnPlate.size() == 0) {
            getCM().setIcon(plate, getImageLoader().getIcon("plate"));
        } else if (newMatchedRecipe.isPresent()) {
            getCM().setIcon(plate, getImageLoader().getIcon("dish-" + newMatchedRecipe.get().getProduct()));
        } else {
            currentItemsOnPlate.sort(String::compareTo);
            getCM().setIcon(plate, getImageLoader().getIcon("dish-" + String.join("-", currentItemsOnPlate)));
        }

        getCM().recalculateIcon(plate);
    }

    public boolean finishOrder(String orderName) {
        Optional<Order> cOrder = orderList.stream().filter(order -> order.is(orderName)).findFirst();

        if (cOrder.isPresent()) {
            getCM().remove(cOrder.get().getComponent());
            orderBar.remove(cOrder.get().getComponent());
            totalScoreI += 20;
            totalScore.setText(Integer.toString(totalScoreI));
            orderList.remove(cOrder.get());
            return true;
        }

        return false;
    }

    public void newOrder() {
        if (orderList.size() >= 6) return;
        int levelIndex = (int) Math.floor(Math.random() * ((int) getController().getState("level")));
        int subIndex = (int) Math.floor(Math.random() * Data.orderList[levelIndex].length);
        int index = IntStream.range(0, levelIndex).reduce(0, (acc, i) -> Data.orderList[i].length + acc) + subIndex;
        Order order = new Order(allOrder.get(index), new JLabel());

        getCM().setIcon(order.getComponent(), getImageLoader().getIcon(order.getName()), CM.size(14, CMFlag.BY_H));
        getCM().setSize(order.getComponent(), CM.size(14, CMFlag.BY_H));
        getCM().recalculateSize(order.getComponent());
        getCM().recalculateIcon(order.getComponent());

        orderList.add(order);
        orderBar.add(order.getComponent());
        revalidate();
    }

    @Override
    public void onStart() {
        getAudioLoader().get("theme").loop(Clip.LOOP_CONTINUOUSLY);
        allOrder = new ArrayList<>();

        int level = (int) getController().getState("level");
        IntStream.range(0, level).forEach(i -> allOrder.addAll(Arrays.asList(Data.orderList[i])));

        remainingTime = Data.totalTimeList[(int) getController().getState("difficulty") - 1];
        timer = Utility.setInterval(
                () -> {
                    totalTime.setText(Integer.toString(remainingTime));
                    repaint();
                    remainingTime--;
                    if (remainingTime < 0) {
                        getController().changeState("score", totalScoreI);
                        getController().changeScene("Result");
                    }
                    return remainingTime >= 0;
                },
                1000
        );

        orderTimer = Utility.setInterval(() -> {
            newOrder();
            return true;
        }, Data.timeList[level - 1] * 1000);
    }

    @Override
    public void onStop() {
        getAudioLoader().get("theme").stop();
        getAudioLoader().get("boil").stop();
        timer.run();
        orderTimer.run();
    }
}
