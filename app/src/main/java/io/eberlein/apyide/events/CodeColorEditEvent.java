package io.eberlein.apyide.events;

import io.eberlein.apyide.codestyles.CodeColor;

public class CodeColorEditEvent {
    private CodeColor codeColor;

    public CodeColorEditEvent(CodeColor codeColor){
        this.codeColor = codeColor;
    }

    public CodeColor getCodeColor() {
        return codeColor;
    }
}
