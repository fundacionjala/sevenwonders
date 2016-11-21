package org.fundacionjala.sevenwonders.core;

/**
 * Created by Jorge on 20/11/2016.
 */
public class ChooseCard {
    private Player player;
    private Card card;
    private Age age;

    public ChooseCard(Player player, Card card, Age age) {
        this.player = player;
        this.card = card;
        this.age = age;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }

    public Age getAge() {
        return age;
    }
}
