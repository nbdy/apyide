package io.eberlein.apyide.events;

import io.eberlein.apyide.codestyles.CodeColor;

public class CodeColorEditEvent {
    private final CodeColor codeColor;

    public CodeColorEditEvent(CodeColor codeColor){
        this.codeColor = codeColor;
    }

    public CodeColor getCodeColor() {
        return codeColor;
    }
}
