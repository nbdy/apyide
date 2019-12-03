package io.eberlein.apyide.events;

import io.eberlein.apyide.codestyles.CodeColor;

public class CodeColorDeletedEvent {
    private final CodeColor codeColor;

    public CodeColorDeletedEvent(CodeColor codeColor){
        this.codeColor = codeColor;
    }

    public CodeColor getCodeColor() {
        return codeColor;
    }
}
