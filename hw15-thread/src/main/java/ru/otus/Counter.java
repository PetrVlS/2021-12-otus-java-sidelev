package ru.otus;

public class Counter {
    private int param = 1;
    private boolean isUpwardMovement = true;

    private void checkAndChangeDirection() {
        if (param == 10) {
            isUpwardMovement = false;
        } else if (param == 1) {
            isUpwardMovement = true;
        }
    }

    public int getAndChangeParam() {
        checkAndChangeDirection();
        return isUpwardMovement ? param++ : param--;
    }
}
