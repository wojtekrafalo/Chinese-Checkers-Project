package common.model.connection;

public enum Instruction {

    ERROR(),

    CREATE_GAME(),

    JOIN_GAME(),

    REQUIRE_SESSIONS(),

    MAKE_MOVE(),

    LEAVE_GAME(),

    PASS(),

    CLIENT_ENDS(),

    STOP_WAITING(),

    START_GAME(),

    MOVE_MADE(),

    WRONG_NUM_OF_PARAMS();




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
