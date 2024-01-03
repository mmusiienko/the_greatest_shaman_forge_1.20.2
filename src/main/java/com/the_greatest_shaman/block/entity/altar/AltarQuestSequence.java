package com.the_greatest_shaman.block.entity.altar;

import java.util.ArrayList;
import java.util.List;

public class AltarQuestSequence {
    private final List<Quest> quests = new ArrayList<>();
    private final String greetingMessage;

    public int size() {
        return quests.size();
    }
    public AltarQuestSequence(String greetingMessage) {
        this.greetingMessage = greetingMessage;
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

    public String getGreetingMessage() {
        return greetingMessage;
    }
}
