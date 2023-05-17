package main.java.keyinput;

public enum Keys  {
    MOVE_LEFT,
    MOVE_RIGHT,
    MOVE_FORWARD,
    MOVE_BACK,
    MOVE_DOWN,
    MOVE_UP,
    ROLL_RIGHT,
    ROLL_LEFT,
    ROTATE_UP,
    ROTATE_DOWN,
    ROTATE_RIGHT,
    ROTATE_LEFT,
    SPEED_UP,
    RESET,
    ESCAPE,
    FULL_SCREEN;

    private int keyCode;
    private boolean pressed;
    private boolean pressedOneTick;

    Keys() {
        this.pressed = false;
        this.pressedOneTick = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public boolean isPressedOneTick() {
        return pressedOneTick;
    }

    public void setPressedOneTick(boolean pressedOneTick) {
        this.pressedOneTick = pressedOneTick;
    }
}
