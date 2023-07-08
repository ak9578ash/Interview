package com.interview.preparation.low_level_design.solid_principle.dependency_inversion.correct_way;

import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.KeyBoard;
import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.Mouse;

public class MacBook {
    private KeyBoard keyBoard;
    private Mouse mouse;

    public MacBook(KeyBoard keyBoard , Mouse mouse){
        this.keyBoard = keyBoard;
        this.mouse = mouse;
    }
}
