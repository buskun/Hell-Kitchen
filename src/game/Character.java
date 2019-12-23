package game;

import base.Scene;
import base.WindowFrame;
import game.frames.*;
import utility.bounding.BoundingArea;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.cm.DimensionStore;
import utility.cm.PointStore;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Character {
    private static int pixelPerMove = 15;
    private double percentPPMHeight;
    private double percentPPMWidth;
    private JLabel characterIcon = new JLabel();
    private Scene scene;
    private CM cm;
    private BiConsumer<String, Consumer<String>> actionToPlate;
    private Predicate<String> finishOrder;
    private ImageLoader imageLoader;
    private AudioLoader audioLoader;
    private HashMap<String, Boolean> interactable;
    private WindowFrame window;
    private BoundingArea map;

    private String holdingItem = "";

    public Character(Scene cScene, BoundingArea cMap, HashMap<String, Boolean> interactableMap, BiConsumer<String, Consumer<String>> onActionToPlate, Predicate<String> onFinishOrder) {
        scene = cScene;
        cm = scene.getCM();
        imageLoader = scene.getImageLoader();
        audioLoader = scene.getAudioLoader();
        interactable = interactableMap;
        window = scene.getWindow();
        map = cMap;
        actionToPlate = onActionToPlate;
        finishOrder = onFinishOrder;

        percentPPMHeight = 100 * (double) pixelPerMove / scene.getHeight();
        percentPPMWidth = 100 * (double) pixelPerMove / scene.getHeight();

        cm.setIcon(characterIcon, imageLoader.getIcon(holdingItem.equals("") ? "character" : "avatar-" + holdingItem), CM.size(13, CMFlag.BY_H));
        cm.setBounds(characterIcon, CM.grid(15, 15, CM.size(13, CMFlag.BY_H)));
        scene.add(characterIcon);
    }

    public void move(int x, int y) {
        interactable.keySet().forEach(key -> interactable.put(key, false));

        DimensionStore characterSize = cm.getScaledSize(characterIcon);
        PointStore characterLocation = cm.getScaledLocation(characterIcon);

        double pX = Math.min(
                Math.max(characterLocation.getX() + x * percentPPMWidth, 0),
                100 - ((double) characterIcon.getWidth() / scene.getWidth()) * 100
        );
        double pY = Math.min(
                Math.max(characterLocation.getY() + y * percentPPMHeight, 0),
                100 - ((double) characterIcon.getHeight() / scene.getHeight()) * 100
        );

        Rectangle newCharacterBounds = CM.grid(
                pX, pY,
                characterSize.getW(),
                characterSize.getH(),
                characterSize.getFlag()
        ).calculate(window.getWidth(), window.getHeight());

        if (map.intersects(newCharacterBounds)) return;

        cm.setLocation(characterIcon, CM.position(pX, pY));
        cm.recalculateLocation(characterIcon);
    }

    public void doAction() {
        if (Boolean.TRUE.equals(interactable.get("refrigerator"))) {
            JFrame refrigeratorFrame = new RefrigeratorFrame(imageLoader, audioLoader, this::holdItem);
            refrigeratorFrame.setVisible(true);
        }
        if (Boolean.TRUE.equals(interactable.get("Drinking")) && holdingItem.equals("")) {
            JFrame waterFrame = new DrinkFrame(imageLoader, audioLoader, this::holdItem);
            waterFrame.setVisible(true);
        }
        if (!holdingItem.equals("")) {
            if (Boolean.TRUE.equals(interactable.get("Cutting")) && imageLoader.has("cutting-" + holdingItem)) {
                JFrame cuttingFrame = new CuttingFrame(imageLoader, audioLoader, holdingItem, this::holdItem);
            }
            if (Boolean.TRUE.equals(interactable.get("pan")) && imageLoader.has("pan-" + holdingItem + "-1")) {
                JFrame panFrame = new PanFrame(imageLoader, audioLoader, holdingItem, this::holdItem);
            }
            if (Boolean.TRUE.equals(interactable.get("pot")) && imageLoader.has("pot-" + holdingItem + "-1")) {
                JFrame potFrame = new PotFrame(imageLoader, audioLoader, holdingItem, this::holdItem);
            }
            if (Boolean.TRUE.equals(interactable.get("trash"))) {
                holdItem("");
            }
            if (Boolean.TRUE.equals(interactable.get("sent"))) {
                if (finishOrder.test(holdingItem)) holdItem("");
            }
        }

        if (Boolean.TRUE.equals(interactable.get("plate"))) {
            actionToPlate.accept(holdingItem, this::holdItem);
        }
    }

    public void holdItem(String item) {

        holdingItem = item;

        if (holdingItem.equals("")) {
            cm.setIcon(characterIcon, imageLoader.getIcon("character"));
        } else {
            cm.setIcon(characterIcon, imageLoader.getIcon("avatar-" + holdingItem));
        }
        cm.recalculateIcon(characterIcon);
    }
}
