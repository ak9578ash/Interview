package com.interview.preparation.low_level_design.solid_principle.dependency_inversion.impl;

import com.interview.preparation.low_level_design.solid_principle.dependency_inversion.Mouse;

public class BluetoothMouse implements Mouse {
    @Override
    public String getMouseType() {
        return "Bluetooth Mouse";
    }
}
