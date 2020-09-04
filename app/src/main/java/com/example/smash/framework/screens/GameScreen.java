package com.example.smash.framework.screens;

import android.graphics.Color;

import com.example.smash.framework.core.Assets;
import com.example.smash.framework.core.Constant;
import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Graphics;
import com.example.smash.framework.core.Input;
import com.example.smash.framework.core.Pixmap;
import com.example.smash.framework.core.Screen;
import com.example.smash.framework.core.Settings;
import com.example.smash.framework.mrnom.Snake;
import com.example.smash.framework.mrnom.SnakePart;
import com.example.smash.framework.mrnom.Stain;
import com.example.smash.framework.mrnom.World;

import java.util.List;

public class GameScreen extends Screen {
    public static final int LINE_START_X = 0;
    public static final int LINE_START_Y = Constant.BACKGROUND_HEIGHT -
            Constant.GAP * 2 - Constant.IMAGE_NUMBER_HEIGHT;
    public static final int LINE_END_X = Constant.BACKGROUND_WIDTH;
    public static final int LINE_END_Y = LINE_START_Y;

    public static final int READY_X = Math.round((
            Constant.BACKGROUND_WIDTH - Constant.READY_WIDTH) / 2);
    public static final int READY_Y = Math.round((
            LINE_END_Y - Constant.READY_HEIGHT) / 2);

    public static final int BUTTON_PAUSE_X = 0;
    public static final int BUTTON_PAUSE_Y = 0;

    public static final int BUTTON_LEFT_X = 0;
    public static final int BUTTON_LEFT_Y = Constant.BACKGROUND_HEIGHT -
            Constant.BUTTON_HEIGHT;

    public static final int BUTTON_RIGHT_X = Constant.BACKGROUND_WIDTH -
            Constant.BUTTON_WIDTH;
    public static final int BUTTON_RIGHT_Y = BUTTON_LEFT_Y;

    public static final int RESUME_X = Math.round((
            Constant.BACKGROUND_WIDTH - Constant.RESUME_WIDTH) / 2);
    public static final int RESUME_Y = READY_Y;

    public static final int GAME_OVER_X = Math.round((
            Constant.BACKGROUND_WIDTH - Constant.GAME_OVER_WIDTH) / 2);
    public static final int GAME_OVER_Y = READY_Y;

    public static final int BUTTON_EXIT_X = Math.round(
            (Constant.BACKGROUND_WIDTH - Constant.BUTTON_WIDTH) / 2);
    public static final int BUTTON_EXIT_Y = GAME_OVER_Y +
            Constant.GAME_OVER_HEIGHT + Constant.GAP;

    public static final int BUTTON_QUIT_X = RESUME_X;
    public static final int QUIT_Y =
            RESUME_Y +
            Constant.RESUME_HEIGHT;

    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";

    public GameScreen(Game game) {
        super(game);
        this.world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = this.game.getInput().getTouchEvents();

        if (this.state == GameState.Ready) {
            this.updateReady(touchEvents);
        } else if (this.state == GameState.Running) {
            this.updateRunning(touchEvents, deltaTime);
        } else if (this.state == GameState.Paused) {
            this.updatePaused(touchEvents);
        } else if (this.state == GameState.GameOver) {
            this.updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<Input.TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            this.state = GameState.Running;
        }
    }

    private  void updateRunning(
            List<Input.TouchEvent> touchEvents,
            float deltaTime
    ) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x < Constant.BUTTON_WIDTH &&
                    event.y < BUTTON_PAUSE_Y + Constant.BUTTON_HEIGHT) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(Constant.SOUND_VOLUM_MAX);
                    }
                    this.state = GameState.Paused;
                    return;
                }
            } else if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                if (event.x < Constant.BUTTON_WIDTH &&
                    event.y > BUTTON_LEFT_Y) {
                    this.world.snake.turnLeft();
                } else if (event.x > BUTTON_RIGHT_X &&
                    event.y > BUTTON_RIGHT_Y) {
                    this.world.snake.turnRight();
                }
            }
        }

        this.world.update(deltaTime);
        if (this.world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.bitten.play(Constant.SOUND_VOLUM_MAX);
            }
            this.state = GameState.GameOver;
        }

        if (this.oldScore != this.world.score) {
            this.oldScore = this.world.score;
            this.score = "" + this.oldScore;
            if (Settings.soundEnabled) {
                Assets.eat.play(Constant.SOUND_VOLUM_MAX);
            }
        }
    }

    private void updatePaused(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for ( int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > RESUME_X &&
                    event.x < RESUME_X + Constant.RESUME_WIDTH) {
                    if (event.y > RESUME_Y &&
                        event.y < RESUME_Y + Constant.RESUME_HEIGHT) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(Constant.SOUND_VOLUM_MAX);
                        }
                        this.state = GameState.Running;
                        return;
                    }
                    if (event.y > QUIT_Y &&
                            event.y < QUIT_Y + Constant.QUIT_HEIGHT) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(Constant.SOUND_VOLUM_MAX);
                        }
                        this.game.setScreen(new MainMenuScreen(this.game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for ( int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > BUTTON_EXIT_X &&
                        event.x < BUTTON_EXIT_X + Constant.BUTTON_WIDTH) {
                    if (event.y > BUTTON_EXIT_Y &&
                            event.y < BUTTON_EXIT_Y + Constant.BUTTON_HEIGHT) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(Constant.SOUND_VOLUM_MAX);
                        }
                        this.game.setScreen(new MainMenuScreen(this.game));
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = this.game.getGraphics();

        graphics.drawPixmap(
                Assets.background,
                0,
                0
        );
        this.drawWorld();
        if (this.state == GameState.Ready) {
            this.drawReadyUI();
        } else if (this.state == GameState.Running) {
            this.drawRunningUI();
        } else if (this.state == GameState.Paused) {
            this.drawPausedUI();
        } else if (this.state == GameState.GameOver) {
            this.drawGameOverUI();
        }

        this.drawText(
                graphics,
                this.score,
                (Constant.BACKGROUND_WIDTH - Constant.IMAGE_NUMBER_WIDTH) / 2,
                Constant.BACKGROUND_HEIGHT - Constant.IMAGE_NUMBER_HEIGHT - Constant.GAP

        );
    }

    private void drawWorld() {
        Graphics graphics = this.game.getGraphics();
        Snake snake = this.world.snake;
        SnakePart head = snake.partList.get(Snake.HEAD_INDEX);
        Stain stain = this.world.stain;

        // draw stain
        Pixmap stainPixmap = null;
        if (stain.type == Constant.STAIN_TYPE_1) {
            stainPixmap = Assets.stain1;
        } else if (stain.type == Constant.STAIN_TYPE_2) {
            stainPixmap = Assets.stain2;
        } else if (stain.type == Constant.STAIN_TYPE_3) {
            stainPixmap = Assets.stain3;
        }

        int x = stain.x * Constant.STAIN_WIDTH;
        int y = stain.y * Constant.STAIN_HEIGHT;
        graphics.drawPixmap(stainPixmap, x, y);

        int len = snake.partList.size();
        for(int i = 1;i < len;i++) {
            SnakePart part = snake.partList.get(i);
            x = part.x * Constant.SNAKE_PART_WIDTH;
            y = part.y * Constant.SNAKE_PART_HEIGHT;

            graphics.drawPixmap(Assets.tail, x, y);
        }

        Pixmap headPixmap = null;
        if (snake.direction == Snake.UP) {
            headPixmap = Assets.headUp;
        } else if (snake.direction == Snake.LEFT) {
            headPixmap = Assets.headLeft;
        } else if (snake.direction == Snake.DOWN) {
            headPixmap = Assets.headDown;
        } else if (snake.direction == Snake.RIGHT) {
            headPixmap = Assets.headRight;
        }

        x = head.x * Constant.SNAKE_PART_WIDTH;
        y = head.y * Constant.SNAKE_PART_HEIGHT;

        graphics.drawPixmap(
                headPixmap,
                x,
                y
        );
    }

    private void drawSeparatorLine (Graphics graphics) {
        graphics.drawLine(
                LINE_START_X,
                LINE_START_Y,
                LINE_END_X,
                LINE_END_Y,
                Color.BLACK
        );
    }

    private void drawReadyUI() {
        Graphics graphics = this.game.getGraphics();

        graphics.drawPixmap(
                Assets.ready,
                READY_X,
                READY_Y
        );
        this.drawSeparatorLine(graphics);
    }

    private void drawRunningUI() {
        Graphics graphics = this.game.getGraphics();

        graphics.drawPixmap(
                Assets.buttons,
                BUTTON_PAUSE_X,
                BUTTON_PAUSE_Y,
                Constant.BUTTON_PAUSE_SRC_X,
                Constant.BUTTON_PAUSE_SRC_Y,
                Constant.BUTTON_WIDTH,
                Constant.BUTTON_HEIGHT
        );
        this.drawSeparatorLine(graphics);
        graphics.drawPixmap(
                Assets.buttons,
                BUTTON_LEFT_X,
                BUTTON_LEFT_Y,
                Constant.BUTTON_BACK_SRC_X,
                Constant.BUTTON_BACK_SRC_Y,
                Constant.BUTTON_WIDTH,
                Constant.BUTTON_HEIGHT
        );
        graphics.drawPixmap(
                Assets.buttons,
                BUTTON_RIGHT_X,
                BUTTON_RIGHT_Y,
                Constant.BUTTON_NEXT_SRC_X,
                Constant.BUTTON_NEXT_SRC_Y,
                Constant.BUTTON_WIDTH,
                Constant.BUTTON_HEIGHT
        );
    }

    private void drawPausedUI() {
        Graphics graphics = this.game.getGraphics();

        graphics.drawPixmap(
                Assets.pause,
                RESUME_X,
                RESUME_Y
        );
        this.drawSeparatorLine(graphics);
    }

    private void drawGameOverUI() {
        Graphics graphics = this.game.getGraphics();

        graphics.drawPixmap(
                Assets.gameOver,
                GAME_OVER_X,
                GAME_OVER_Y
        );
        graphics.drawPixmap(
                Assets.buttons,
                BUTTON_EXIT_X,
                BUTTON_EXIT_Y,
                Constant.BUTTON_EXIT_SRC_X,
                Constant.BUTTON_EXIT_SRC_Y,
                Constant.BUTTON_WIDTH,
                Constant.BUTTON_HEIGHT
        );
        this.drawSeparatorLine(graphics);
    }

    private void drawText(
            Graphics graphics,
            String line,
            int x,
            int y
    ) {
        int len = line.length();
        for (int i = 0;i < len;i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += Constant.IMAGE_NUMBER_WIDTH;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;

            if (character == '.') {
                srcX = Constant.IMAGE_DOT_SRC_X;
                srcWidth = Constant.IMAGE_DOT_WIDTH;
            } else {
                srcX = (character - '0') * Constant.IMAGE_NUMBER_WIDTH;
                srcWidth = Constant.IMAGE_NUMBER_WIDTH;
            }

            graphics.drawPixmap(
                    Assets.numbers,
                    x,
                    y,
                    srcX,
                    Constant.IMAGE_NUMBER_SRC_Y,
                    srcWidth,
                    Constant.IMAGE_NUMBER_HEIGHT
            );

            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if (this.state == GameState.Running) {
            this.state = GameState.Paused;
        }
        if (this.world.gameOver) {
            Settings.addScore(this.world.score);
            Settings.save(this.game.getFileIO());
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
