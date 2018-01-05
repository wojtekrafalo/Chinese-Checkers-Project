package common.model.connection;

public enum Instruction {

    ERROR(1),

    CREATE_GAME(2),

    JOIN_GAME(1),

    SESSION_CHOSEN(1),

    REQUIRE_SESSIONS(0),

    MAKE_MOVE(6),

    LEAVE_GAME(),

    PASS(1),

    CLIENT_ENDS(0),

    START_GAME(),

    CREATED(2),

    JOINED(1),

    CANT_MOVE(),

    MOVE_MADE(4),

    SEND_SESSIONS(1),

    SESSION_UNAVAILABLE(0),

    WRONG_NUM_OF_PARAMS(0);





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
