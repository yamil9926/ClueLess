package com.clueless.clueless;

public class caseFile {
    card player, weapon, room;

    // constructor
    public caseFile(card player, card weapon, card room) {
        this.player = player;
        this.weapon = weapon;
        this.room = room;
    }

    // reveal method
    public card[] reveal() {
        card[] caseFile_cards = { player, weapon, room };
        return caseFile_cards;
    }
}
