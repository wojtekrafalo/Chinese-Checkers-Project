package common.model.connection;

public enum Instruction {

    NICK_ADD(1), // client -> server param nick

    NICK_INSERTED(2), // server -> client param nick, id

    CREATE_GAME(3), // client -> server param name,nrplayers,nrboots

    CREATED(4), // server -> client param name, nrplayers, nrboots,color hosta

    START_GAME(1), // server -> client param turn

    BOOT_ADD(2), // server -> client param nickbota, kolor

    JOIN_GAME(1), // client -> server param idhosta

    JOINED(6), // server -> client param name, idhosta, kolor, info o graczach jako string!!!

    PLAYER_JOINED(3), // server -> client param id,nick,kolor

    REQUIRE_SESSIONS(0), // client -> server

    SEND_SESSIONS(1), // server -> client sesje jako lista ( zobacz sobie co do niej wchodzi

    SESSION_CHOSEN(1), // nie wiem po co to

    MAKE_MOVE(4), // client -> server param prevx prevy nextx nexty

    MOVE_MADE(5), // server -> client param prevx prevy nextx nexty turn

    CANT_MOVE(0), // server -> client

    LEAVE_GAME(0), // client -> server

    LEAVED(0), // server -> client

    HOST_LEAVED(1), // server -> client param id nowego hosta

    PLAYER_LEAVED(1), // server -> client param id gracza ktory wyszedl

    HOST_LEAVED_IN_GAME(1), // server -> client param id nowego hosta

    PLAYER_LEAVED_IN_GAME(1), // server -> client param id gracza ktory wyszedl

    PASS(0), // client -> server

    PASSED(1), //  server -> client param nowa tura

    CLIENT_ENDS(0), // client -> server

    SESSION_UNAVAILABLE(0), // server -> client

    WRONG_NUM_OF_PARAMS(0), // server -> client (chociaz u ciebie tez to poki co jest)

    WRONG_NUM_OF_PLAYERS(0), // server -> client

    WIN(0), // server -> client

    INSTANT_WIN(0), // server -> client

    LOST(1), // server -> client param id zwyciezcy

    LOST_CONTINUE(1), // server -> client param id zwyciezcy

    CONTINUE(1), // server -> client param kolejna kolejka

    NOT_CONTINUE(0), // server -> client

    IF_CONTINUE(1); // client -> server param boolean czy kontynuowac

    private int nrParams;

    Instruction(int size){
        this.nrParams = size;
    }
    Instruction(){
        this.nrParams = -1;
    }

    public int getNrParams() {
        return nrParams;
    }
}
