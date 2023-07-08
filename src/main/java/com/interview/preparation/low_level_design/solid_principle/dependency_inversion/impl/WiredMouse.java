package com.interview.preparation.low_level_design.solid_principle.dependency_inversion.impl;

import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.Mouse;

public class WiredMouse implements Mouse {
    @Override
    public String getMouseType() {
        return "Wired Mouse";
    }
}
