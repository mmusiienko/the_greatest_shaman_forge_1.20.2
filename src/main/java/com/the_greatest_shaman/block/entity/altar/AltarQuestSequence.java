package com.the_greatest_shaman.block.entity.altar;

import java.util.ArrayList;
import java.util.List;

public class AltarQuestSequence {
    private final List<Quest> quests = new ArrayList<>();
    public int size() {
        return quests.size();
    }
    public AltarQuestSequence() {

    }
    public AltarQuestSequence add(Quest quest) {
        quests.add(quest);
        return this;
    }
    public boolean isEmpty() {
        return quests.isEmpty();
    }
    public Quest getQuest(int index) {
        if (index < 0 || index >= quests.size()) return null;
        return quests.get(index);
    }

}
