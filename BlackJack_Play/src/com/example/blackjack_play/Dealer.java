package com.example.blackjack_play;

import java.io.*;
import java.util.*;

class Dealer extends GamePlayer {
	Cards cards;
	public Dealer(Cards c) {
		super("");
		cards = c;
	}
	public void deal(GamePlayer player) {
		Card c = cards.draw();
		player.getCard(c);
	}
}