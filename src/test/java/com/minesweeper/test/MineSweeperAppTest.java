package com.minesweeper.test;
import org.junit.jupiter.api.Test;

import com.minesweeper.MineSweeperGame;

import static org.junit.jupiter.api.Assertions.*;

class MineSweeperAppTest {

    @Test
    void testGameWinScenario() {
        MineSweeperGame game = new MineSweeperGame(2, 0); // no mines
        game.uncover(0, 0);
        game.uncover(0, 1);
        game.uncover(1, 0);
        game.uncover(1, 1);
        assertTrue(game.isWin());
    }

    @Test
    void testMineTrigger() {
        MineSweeperGame game = new MineSweeperGame(2, 4); // all mines
        game.uncover(0, 0);
        assertTrue(game.isGameOver());
        assertFalse(game.isWin());
    }
}