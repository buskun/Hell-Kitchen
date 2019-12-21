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
        imageLoader.add("background", "resources/gameScene/background.png");
        imageLoader.add("head", "resources/gameScene/Iconplayer.png");
        imageLoader.add("refrigerator", "resources/gameScene/Fridge.png");
        imageLoader.add("bar","resources/gameScene/bar.png");
        imageLoader.add("plate","resources/gameScene/plate.png");
        imageLoader.add("Cutting","resources/gameScene/Cutting.png");
        imageLoader.add("Drinking","resources/gameScene/Drinking.png");
        imageLoader.add("pot","resources/gameScene/pot.png");
        imageLoader.add("pan","resources/gameScene/pan.png");
    }

    private BoundingArea map = new BoundingArea();
    private JLabel character = new JLabel();
    private JLabel refrigerator = new JLabel();
    private JLabel bar = new JLabel();
    private JLabel plate = new JLabel();
    private JLabel Cutting = new JLabel();
    private JLabel Drinking = new JLabel();
    private JLabel pot = new JLabel();
    private JLabel pan = new JLabel();
    JFrame refrigeratorFrame = new JFrame();

    private HashMap<String, Boolean> interactable = new HashMap<>();


    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(getImageLoader().getIcon("background"));

        cm.setIcon(character, imageLoader.getIcon("head"), CM.size(13, CMFlag.BY_H));
        cm.setBounds(character, CM.grid(15, 15, CM.size(13, CMFlag.BY_H)));
        add(character);

        cm.setIcon(refrigerator, imageLoader.getIcon("refrigerator"), CM.size(15, 40));
        cm.setBounds(refrigerator, CM.grid(85, 20, 15, 40));
        add(refrigerator);
        map.add("refrigerator", refrigerator);

        cm.setIcon(plate,imageLoader.getIcon("plate"),CM.size(10,CMFlag.BY_H));
        cm.setBounds(plate,CM.grid(30,29,CM.size(10, CMFlag.BY_H)));
        add(plate);
        map.add("plate",plate);

        cm.setIcon(bar,imageLoader.getIcon("bar"),CM.size(44,37));
        cm.setBounds(bar,CM.grid(24,29,44,37));
        add(bar);
        map.add("bar",bar);

        cm.setIcon(Cutting,imageLoader.getIcon("Cutting"),CM.size(7,13));
        cm.setBounds(Cutting,CM.grid(0,28,7,15));
        add(Cutting);
        map.add("Cutting",Cutting);

        cm.setIcon(Drinking,imageLoader.getIcon("Drinking"),CM.size(7,22));
        cm.setBounds(Drinking,CM.grid(0,50,7,22));
        add(Drinking);
        map.add("Drinking",Drinking);

        cm.setIcon(pot,imageLoader.getIcon("pot"),CM.size(15,12));
        cm.setBounds(pot,CM.grid(34,82,15,12));
        add(pot);
        map.add("pot",pot);

        cm.setIcon(pan,imageLoader.getIcon("pan"),CM.size(14,CMFlag.BY_H));
        cm.setBounds(pan,CM.grid(54,82,CM.size(14, CMFlag.BY_H)));
        add(pan);
        map.add("pan",pan);

        map.addIntersectionListener((name) -> {
            interactable.put(name, true);
            return true;
        });

        ready();
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
        int pixelPerMove = 25;
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

        boolean isActionKeyPressed = isKeyPressed(KeyEvent.VK_SPACE, true);

        if (Boolean.TRUE.equals(interactable.get("refrigerator")) && isActionKeyPressed) {
            System.out.println("Hi ref");
            refrigeratorFrame.setVisible(true);
            refrigeratorFrame.setSize(new Dimension(1200, 900));
            refrigeratorFrame.setResizable(false);

        }
        if (Boolean.TRUE.equals(interactable.get("plate")) && isActionKeyPressed) System.out.println("kuy");
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
