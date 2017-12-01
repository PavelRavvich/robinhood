package org.robinhood;

import org.robinhood.service.OperationManagerImpl;

import java.awt.*;

/**
 * Author : Pavel Ravvich.
 * Created : 01/12/2017.
 * <p>
 * Move
 */
public class Move {
    public static void main(String[] args) {
        final OperationManagerImpl.RobotWrapper robotWrapper = new OperationManagerImpl().new RobotWrapper();
        robotWrapper.go(new Point(577, 465));
    }

}
