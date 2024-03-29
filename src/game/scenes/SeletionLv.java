package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SeletionLv extends Scene {

    public SeletionLv(WindowFrame _window, Controller _controller) {
        super(_window, _controller);
    }

    @Override
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("seletion", "resources/seletionlv/LevelSelection.png");
        imageLoader.add("closebtn", "resources/seletionlv/closebtn.png");
        imageLoader.add("1", "resources/seletionlv/1.png");
        imageLoader.add("2", "resources/seletionlv/2.png");
        imageLoader.add("3", "resources/seletionlv/3.png");
        imageLoader.add("4", "resources/seletionlv/4.png");
        imageLoader.add("5", "resources/seletionlv/5.png");
        imageLoader.add("6", "resources/seletionlv/6.png");
        imageLoader.add("7", "resources/seletionlv/7.png");
        imageLoader.add("8", "resources/seletionlv/8.png");
        imageLoader.add("9", "resources/seletionlv/9.png");
        imageLoader.add("10", "resources/seletionlv/10.png");
        imageLoader.add("star", "resources/seletionlv/star.png");
        imageLoader.add("starvictory", "resources/seletionlv/starvictory.png");
    }

    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(getImageLoader().getIcon("seletion"));

        JButton backBtn = new JButton();
        cm.setIcon(backBtn, getImageLoader().getIcon("closebtn"), CM.size(13, CMFlag.BY_H));
        cm.setBounds(backBtn, CM.grid(82, 5, CM.size(13, CMFlag.BY_H)));
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);

        backBtn.addActionListener(e -> getController().changeScene("menu"));
        add(backBtn);

        java.util.List<String> levelIconNameList = Arrays.asList("1", "2", "3", "4", "5");
        java.util.List<JButton> levelButtonList = IntStream.range(0, levelIconNameList.size())
                .mapToObj(index -> {
                    JButton btn = new JButton();
                    cm.setIcon(btn, imageLoader.getIcon(levelIconNameList.get(index)), CM.size(25, CMFlag.BY_H));
                    cm.setBounds(btn, CM.grid(
                            5 + 19 * (index % 5),
                            50 + 30 * Math.floorDiv(index, 5),
                            CM.size(25, CMFlag.BY_H)));

                    btn.setOpaque(false);
                    btn.setContentAreaFilled(false);
                    btn.setBorderPainted(false);

                    add(btn);

                    btn.addActionListener(e -> {
                        getController().changeState("level", index + 1);
                        getController().changeScene("gameScene");
                    });

                    return btn;
                })
                .collect(Collectors.toList());



/*        JLabel levelBorder = new JLabel(getImageLoader().getIcon("closebtn").resize(sizeByH(0.15)));
        levelBorder.setBounds(grid(0.07, 0.3, sizeByH(0.15)));
        add(levelBorder);
        setComponentZOrder(levelBorder, 0);

        Animation[] lastAnimation = new Animation[2];
        levelButtonList.forEach(
                btn -> btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        if (lastAnimation[0] != null) lastAnimation[0].stop();
                        if (lastAnimation[1] != null) lastAnimation[1].stop();

                        lastAnimation[0] = new Animation(SeletionLv.this,
                                x -> levelBorder.setLocation((int) Math.round(x), levelBorder.getY()),
                                levelBorder::getX,
                                AnimationMap.EASE_IN_OUT_ELASTIC,
                                e.getComponent().getX(),
                                300
                        );
                        lastAnimation[1] = new Animation(SeletionLv.this,
                                y -> levelBorder.setLocation(levelBorder.getX(), (int) Math.round(y)),
                                levelBorder::getY,
                                AnimationMap.EASE_IN_OUT_ELASTIC,
                                e.getComponent().getY(),
                                300
                        );
                    }
                })
        );*/

        cm.recalculate();

        ready();
    }

    @Override
    public void tick() {


    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
