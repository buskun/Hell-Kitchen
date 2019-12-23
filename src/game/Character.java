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

public class Character {
    private static int pixelPerMove = 15;
    private double percentPPMHeight;
    private double percentPPMWidth;
    private JLabel characterIcon = new JLabel();
    private Scene scene;
    private CM cm;
    private BiConsumer<String, String> changeState;
    private ImageLoader imageLoader;
    private AudioLoader audioLoader;
    private HashMap<String, Boolean> interactable;
    private WindowFrame window;
    private BoundingArea map;

    public Character(Scene cScene, BoundingArea cMap, HashMap<String, Boolean> interactableMap, BiConsumer<String, String> onChangeState) {
        scene = cScene;
        cm = scene.getCM();
        imageLoader = scene.getImageLoader();
        audioLoader = scene.getAudioLoader();
        interactable = interactableMap;
        window = scene.getWindow();
        map = cMap;
        changeState = onChangeState;

        percentPPMHeight = 100 * (double) pixelPerMove / scene.getHeight();
        percentPPMWidth = 100 * (double) pixelPerMove / scene.getHeight();

        cm.setIcon(characterIcon, imageLoader.getIcon("character"), CM.size(13, CMFlag.BY_H));
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
        if (Boolean.TRUE.equals(interactable.get("Cutting"))) {
            JFrame cuttingFrame = new CuttingFrame(imageLoader, audioLoader, this::holdItem);
            cuttingFrame.setVisible(true);
        }
        if (Boolean.TRUE.equals(interactable.get("pan"))) {
            JFrame panFrame = new panFrame(imageLoader, audioLoader, this::holdItem);
            panFrame.setVisible(true);
        }
        if (Boolean.TRUE.equals(interactable.get("Drinking"))) {
            JFrame waterFrame = new waterFrame(imageLoader, audioLoader, this::holdItem);
            waterFrame.setVisible(true);
        }
        if (Boolean.TRUE.equals(interactable.get("pot"))) {
            JFrame potFrame = new potFrame(imageLoader, audioLoader, this::holdItem);
            potFrame.setVisible(true);
        }
    }

    public void holdItem(String item) {
        changeState.accept("hold", item);

        cm.setIcon(characterIcon, imageLoader.getIcon(item));
        cm.recalculateIcon(characterIcon);
    }
}
