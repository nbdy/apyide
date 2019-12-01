package io.eberlein.apyide.events;

import io.eberlein.apyide.codestyles.CodeStyle;

public class CodeStyleDeletedEvent {
    private CodeStyle style;

    public CodeStyleDeletedEvent(CodeStyle style){
        this.style = style;
    }

    public CodeStyle getStyle() {
        return style;
    }
}
