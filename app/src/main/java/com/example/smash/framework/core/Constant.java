package com.example.smash.framework.core;

public class Constant {
    public static final boolean SCREEN_ON = true;

    // should be equal to the background to scale full screen
    public static int FRAME_BUFFER_WIDTH = 492;
    public static int FRAME_BUFFER_HEIGHT = 732;

    public static final float SECOND_NANO = 1000000000.0f;

    public static final String DEFAULT_IMAGE_PATH = "images/";
    public static final String PATH_IMAGE_BACKGROUND = "background.png";
    public static final String PATH_IMAGE_LOGO = "logo.png";
    public static final String PATH_IMAGE_MAIN_MENU = "mainmenu.png";
    public static final String PATH_IMAGE_BUTTONS = "buttons.png";
    public static final String PATH_IMAGE_HELP1 = "help1.png";
    public static final String PATH_IMAGE_HELP2 = "help2.png";
    public static final String PATH_IMAGE_HELP3 = "help3.png";
    public static final String PATH_IMAGE_NUMBERS = "numbers.png";
    public static final String PATH_IMAGE_READY = "ready.png";
    public static final String PATH_IMAGE_PAUSE = "pause.png";
    public static final String PATH_IMAGE_GAME_OVER = "gameover.png";
    public static final String PATH_IMAGE_HEAD_UP = "headup.png";
    public static final String PATH_IMAGE_HEAD_DOWN = "headdown.png";
    public static final String PATH_IMAGE_HEAD_LEFT = "headleft.png";
    public static final String PATH_IMAGE_HEAD_RIGHT = "headright.png";
    public static final String PATH_IMAGE_TAIL = "tail.png";
    public static final String PATH_IMAGE_STAIN1 = "stain1.png";
    public static final String PATH_IMAGE_STAIN2 = "stain2.png";
    public static final String PATH_IMAGE_STAIN3 = "stain3.png";
    public static final String PATH_SETTING_MR_NORM = ".mrnorm";

    public static final String DEFAULT_SOUND_PATH = "sounds/";
    public static final String PATH_SOUND_CLICK = "click.mp4";
    public static final String PATH_SOUND_EAT = "eat.mp4";
    public static final String PATH_SOUND_BITTEN = "bitten.mp4";

    public static final float SOUND_VOLUM_MAX = 1.0f;

    public static final int NUM_HIGH_SCORES = 5;

    public static final int GAP = 24;

    public static final int IMAGE_NUMBER_HEIGHT = 48;
    public static final int IMAGE_NUMBER_WIDTH = 34;
    public static final int IMAGE_NUMBER_SRC_Y = 0;
    public static final int IMAGE_DOT_WIDTH = IMAGE_NUMBER_WIDTH;
    public static final int IMAGE_DOT_SRC_X = IMAGE_NUMBER_WIDTH * 10;
    public static final int IMAGE_DOT_SRC_Y = 0;



    public static final int BACKGROUND_WIDTH = FRAME_BUFFER_WIDTH;
    public static final int BACKGROUND_HEIGHT = FRAME_BUFFER_HEIGHT;

    public static final int LOGO_WIDTH = 424;
    public static final int LOGO_HEIGHT = 280;

    public static final int MAIN_MENU_WIDTH = 290;
    public static final int MAIN_MENU_HEIGHT = 194;

    public static final int BUTTON_WIDTH = 96;
    public static final int BUTTON_HEIGHT = 96;

    public static final int BUTTON_MAIN_MENU_WIDTH = MAIN_MENU_WIDTH;
    public static final int BUTTON_MAIN_MENU_HEIGHT = Math.round(MAIN_MENU_HEIGHT / 3);

    public static final int BUTTON_SOUND_ON_SRC_X = 0;
    public static final int BUTTON_SOUND_ON_SRC_Y = 0;

    public static final int BUTTON_SOUND_OFF_SRC_X =
            BUTTON_SOUND_ON_SRC_X +
            BUTTON_WIDTH;
    public static final int BUTTON_SOUND_OFF_SRC_Y = BUTTON_SOUND_ON_SRC_Y;


    public static final int BUTTON_NEXT_SRC_X = BUTTON_SOUND_ON_SRC_X;
    public static final int BUTTON_NEXT_SRC_Y =
            BUTTON_SOUND_ON_SRC_Y + BUTTON_HEIGHT;

    public static final int BUTTON_BACK_SRC_X = BUTTON_SOUND_OFF_SRC_X;
    public static final int BUTTON_BACK_SRC_Y = BUTTON_NEXT_SRC_Y;

    public static final int BUTTON_EXIT_SRC_X = BUTTON_SOUND_ON_SRC_X;
    public static final int BUTTON_EXIT_SRC_Y = BUTTON_NEXT_SRC_Y +
            BUTTON_HEIGHT;

    public static final int BUTTON_PAUSE_SRC_X = BUTTON_SOUND_OFF_SRC_X;
    public static final int BUTTON_PAUSE_SRC_Y = BUTTON_EXIT_SRC_Y;

    public static final int HELP_WIDTH = 290;
    public static final int HELP_HEIGHT = 386;

    public static final int READY_HEIGHT = 145;
    public static final int READY_WIDTH = 338;

    public static final int PAUSE_HEIGHT = 144;
    public static final int PAUSE_WIDTH = 240;

    public static final int RESUME_HEIGHT = Math.round(PAUSE_HEIGHT / 2) ;
    public static final int RESUME_WIDTH = PAUSE_WIDTH;

    public static final int QUIT_HEIGHT = RESUME_HEIGHT ;
    public static final int QUIT_WIDTH = PAUSE_WIDTH;

    public static final int GAME_OVER_HEIGHT = 173;
    public static final int GAME_OVER_WIDTH = 294;

    public static final int NUM_STAIN_TYPE = 3;

    public static final int STAIN_TYPE_1=0;
    public static final int STAIN_TYPE_2=1;
    public static final int STAIN_TYPE_3=2;

    public static final int STAIN_WIDTH = 48;
    public static final int STAIN_HEIGHT = STAIN_WIDTH;

    public static final int SNAKE_PART_WIDTH = STAIN_WIDTH;
    public static final int SNAKE_PART_HEIGHT = SNAKE_PART_WIDTH;

    public static final int MAP_TOP = 0;
    public static final int MAP_LEFT = 0;
    public static final int MAP_RIGHT = Math.round(BACKGROUND_WIDTH / SNAKE_PART_WIDTH) - 1;
    public static final int MAP_BOTTOM = Math.round((Constant.BACKGROUND_HEIGHT -
            Constant.GAP * 2 - Constant.IMAGE_NUMBER_HEIGHT) / SNAKE_PART_HEIGHT) - 1;

}
