package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.bounding.BoundingArea;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.cm.DimensionStore;
import utility.cm.PointStore;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class gameScene1 extends Scene {

    public gameScene1(Window _window, Controller _controller) {
        super(_window, _controller);
    }

    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/gameScene/background.jpg");
        imageLoader.add("head", "resources/gameScene/Iconplayer.png");
        imageLoader.add("refrigerator", "resources/gameScene/refrigerator.png");
        imageLoader.add("BarLeft", "resources/gameScene/left.png");
        imageLoader.add("BarCenter", "resources/gameScene/bar.png");
        imageLoader.add("BarUnder", "resources/gameScene/under.png");

    }

    private BoundingArea map = new BoundingArea();
    private JLabel character = new JLabel();
    private JLabel refrigerator = new JLabel();
    private HashMap<String, Boolean> interactable = new HashMap<>();
    private JLabel barButton = new JLabel();
    private JLabel barLeft = new JLabel();
    private JLabel barCenter = new JLabel();

    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(getImageLoader().getIcon("background"));

        cm.setIcon(character, imageLoader.getIcon("head"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(character, CM.grid(20, 20, CM.size(20, CMFlag.BY_H)));
        add(character);

        cm.setIcon(refrigerator, imageLoader.getIcon("refrigerator"), CM.size(20, 40));
        cm.setBounds(refrigerator, CM.grid(80, 20, 20, 40));
        add(refrigerator);
        map.add("refrigerator", refrigerator);

        cm.setIcon(barButton, imageLoader.getIcon("BarUnder"), CM.size(40, 20));
        cm.setBounds(barButton, CM.grid(30, 70, 40, 20));
        add(barButton);

        cm.setIcon(barLeft, imageLoader.getIcon("BarLeft"), CM.size(20, 40));
        cm.setBounds(barLeft, CM.grid(0, 20, 20, 40));
        add(barLeft);

        cm.setIcon(barCenter, imageLoader.getIcon("BarCenter"), CM.size(40, 20));
        cm.setBounds(barCenter, CM.grid(30, 40, 40, 20));
        add(barCenter);
        ready();

        map.addIntersectionListener((name) -> {
            interactable.put(name, true);
            return true;
        });
    }

    public void moveCharacter(double pX, double pY) {
        interactable.keySet().forEach(key -> interactable.put(key, false));

        Window window = getWindow();
        CM cm = getCM();
        DimensionStore characterSize = cm.getScaledSize(character);
        PointStore characterLocation = cm.getScaledLocation(character);

        pX = Math.min(Math.max(characterLocation.getX() + pX, 0), 100 - characterSize.getW());
        pY = Math.min(Math.max(characterLocation.getY() + pY, 0), 100 - characterSize.getH());

        Rectangle newCharacterBounds = CM.grid(
                pX, pY,
                characterSize.getW(),
                characterSize.getH(),
                characterSize.getFlag()
        ).calculate(window.getWidth(), window.getHeight());

        if (map.intersects(newCharacterBounds)) return;

        cm.setLocation(character, CM.position(pX, pY));
        cm.recalculate(character);
    }

    @Override
    public void tick() {
        int pixelPerMove = 20;
        double percentPPMHeight = 100 * (double) pixelPerMove / getHeight();
        double percentPPMWidth = 100 * (double) pixelPerMove / getWidth();

        if (isKeyPressed(KeyEvent.VK_UP))
            moveCharacter(0, -percentPPMHeight);
        if (isKeyPressed(KeyEvent.VK_DOWN))
            moveCharacter(0, percentPPMHeight);
        if (isKeyPressed(KeyEvent.VK_LEFT))
            moveCharacter(-percentPPMWidth, 0);
        if (isKeyPressed(KeyEvent.VK_RIGHT))
            moveCharacter(percentPPMWidth, 0);


        if (Boolean.TRUE.equals(interactable.get("refrigerator")) && isKeyPressed(KeyEvent.VK_SPACE)) System.out.println("Hi ref");
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
