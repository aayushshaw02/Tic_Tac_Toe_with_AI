package tictactoe;

import static java.lang.constant.ConstantDescs.NULL;

public enum Result {
    X_WINS('X'),
    O_WINS('O'),
    DRAW('D'),
    NOT_OVER('N');
    char state;
    Result(char state){
        this.state=state;
    }
    public Result findByState(char state){
        for(Result it: Result.values()){
            //System.out.println(state+" "+ it.state);
            if(state==it.state){
                return it;
            }
        }
        return null;
    }
}
