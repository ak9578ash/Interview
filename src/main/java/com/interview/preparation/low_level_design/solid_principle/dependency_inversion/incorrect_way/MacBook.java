package com.interview.preparation.low_level_design.solid_principle.dependency_inversion.incorrect_way;

import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.KeyBoard;
import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.Mouse;
import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.impl.WiredKeyBoard;
import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.impl.WiredMouse;

public class MacBook {
    private KeyBoard keyBoard;
    private Mouse mouse;

    public MacBook(){
        this.keyBoard = new WiredKeyBoard();
        this.mouse = new WiredMouse();
    }
}
