package tictactoe;
import java.util.*;

import static java.lang.System.exit;

public class Tic_Tac_Toe {
    private char[][] table;
    private int filledCells=0,filledX=0,filledO=0;
    Result r;
    public int getFilledX(){
        return filledX;
    }
    public int getFilledO(){
        return filledO;
    }
    public void increaseFilledX(int x){
        filledX+=x;
    }
    public void decreaseFilledX(int x){
        filledX-=x;
    }
    public void decreaseFilledO(int x){
        filledO-=x;
    }
    public void increaseFilledO(int x){
        filledO+=x;
    }
    public void setValidCell(int r,int c,char x){
        char temp=table[r][c];
        table[r][c]=x;
        if(x==' ') {
            decreaseFilledCells(1);
            if(temp=='X'){
                decreaseFilledX(1);
            } else{
                decreaseFilledO(1);
            }
            result();
            return;
        }
        increaseFilledCells(1);
        if(x=='X')
            increaseFilledX(1);
        else if(x=='O')
            increaseFilledO(1);
        result();
    }
    public void printTable(){
        System.out.println("---------");
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                if(j==0||j==4){
                    System.out.print("| ");
                }
                else{
                    System.out.print(table[i][j-1]+" ");
                }
            }
            System.out.println();
        }
        System.out.println("---------");
    }
    Tic_Tac_Toe(){
        table= new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        r=Result.NOT_OVER;
    }
    public int getFilledCells(){
        return this.filledCells;
    }
    public void result(){
        int i,j;
        for(i=0;i<3;i++){
            if(table[i][0]!=' ') {
                for (j = 1; j < 3; j++) {
                    if (this.table[i][j] != this.table[i][0])
                        break;
                }
                if (j == 3) {
                    r=r.findByState(table[i][0]);
                    return;
                }
            }
            if(table[0][i]!=' ') {
                for (j = 1; j < 3; j++) {
                    if (table[j][i] != table[0][i])
                        break;
                }
                if (j == 3) {
                    r=r.findByState(table[0][i]);
                    return;
                }
            }
        }
        if(table[0][0]!=' ') {
            if (table[1][1] == table[0][0] && table[2][2] == table[0][0]) {
                try {
                    r = r.findByState(table[0][0]);
                    return;
                } catch(NullPointerException e){
                    System.out.println("Error");
                    return;
                }
            }
        }
        if(table[0][2]!=' ') {
            if (table[1][1] == table[0][2] && table[2][0] == table[0][2]) {
                r=r.findByState(table[0][2]);
                return;
            }
        }
        if(getFilledCells()==9){
            r=r.findByState('D');
        }
        else{
            r=r.findByState('N');
        }
        //System.out.println(r.name());
        //printResult(r);
    }
    public void printResult(Result r){
        try {
            switch (r) {
                case X_WINS:
                    System.out.println("X wins");
                    break;
                case O_WINS:
                    System.out.println("O wins");
                    break;
                case DRAW:
                    System.out.println("Draw");
                    break;
                case NOT_OVER:
                    System.out.println("Game not finished");
                    break;
            }
        } catch(NullPointerException e){
            System.out.println("Error!");
        }
    }
    public void playUser(char ch){
        int r=0,c=0;
        Scanner sc=new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the coordinates: ");
                r = sc.nextInt();
                c = sc.nextInt();

            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
            }
            if (r < 1 || r > 3 || c < 1 || c > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (table[r - 1][c - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            setValidCell(r-1,c-1,ch);
            break;
        }
    }
    public void playEasy(char ch){
        int r=0,c=0;
        Scanner sc=new Scanner(System.in);
        Random rr=new Random();
        Random rc=new Random();
        while(true) {
            r = rr.nextInt(3);
            c = rc.nextInt(3);
            if(table[r][c]==' ') {
                System.out.println("Making move level \"easy\"");
                setValidCell(r,c,ch);
                break;
            }
        }
    }
    public void playMedium(char ch){
        int r=0,c=0;
        Scanner sc=new Scanner(System.in);
        Random rr=new Random();
        Random rc=new Random();
        System.out.println("Making move level \"medium\"");
        int chcount=0;
        for(int i=0;i<3;i++){
            chcount=0;
            for(int j=0;j<3;j++){
                if(table[i][j]==' '){
                    r=i;
                    c=j;
                }
                if(table[i][j]==ch){
                    chcount++;
                }
            }
            if(chcount==2&&table[r][c]==' '){
                setValidCell(r,c,ch);
                return;
            }
        }
        for(int i=0;i<3;i++){
            chcount=0;
            for(int j=0;j<3;j++){
                if(table[j][i]==' '){
                    r=j;
                    c=i;
                }
                if(table[j][i]==ch){
                    chcount++;
                }
            }
            if(chcount==2&&table[r][c]==' '){
                setValidCell(r,c,ch);
                return;
            }
        }
        chcount=0;
        for(int i=0;i<3;i++){
            if(table[i][i]==' '){
                r=i;
                c=i;
            }
            if(table[i][i]==ch){
                chcount++;
            }
        }
        if(chcount==2&&table[r][c]==' '){
            setValidCell(r,c,ch);
            return;
        }
        chcount=0;
        for(int i=0;i<3;i++){
            if(table[i][2-i]==' '){
                r=i;
                c=2-i;
            }
            if(table[i][2-i]==ch){
                chcount++;
            }
        }
        if(chcount==2&&table[r][c]==' '){
            setValidCell(r,c,ch);
            return;
        }
        for(int i=0;i<3;i++){
            chcount=0;
            for(int j=0;j<3;j++){
                if(table[i][j]==' '){
                    r=i;
                    c=j;
                }
                if(table[i][j]!=ch&&table[i][j]!=' '){
                    chcount++;
                }
            }
            if(chcount==2&&table[r][c]==' '){
                setValidCell(r,c,ch);
                return;
            }
        }
        for(int i=0;i<3;i++){
            chcount=0;
            for(int j=0;j<3;j++){
                if(table[j][i]==' '){
                    r=j;
                    c=i;
                }
                if(table[j][i]!=ch&&table[j][i]!=' '){
                    chcount++;
                }
            }
            if(chcount==2&&table[r][c]==' '){
                setValidCell(r,c,ch);
                return;
            }
        }
        chcount=0;
        for(int i=0;i<3;i++){
            if(table[i][i]==' '){
                r=i;
                c=i;
            }
            if(table[i][i]!=ch&&table[i][i]!=' '){
                chcount++;
            }
        }
        if(chcount==2&&table[r][c]==' '){
            setValidCell(r,c,ch);
            return;
        }
        chcount=0;
        for(int i=0;i<3;i++){
            if(table[i][2-i]==' '){
                r=i;
                c=2-i;
            }
            if(table[i][2-i]!=ch&&table[i][2-i]!=' '){
                chcount++;
            }
        }
        if(chcount==2&&table[r][c]==' '){
            setValidCell(r,c,ch);
            return;
        }
        while(true) {
            r = rr.nextInt(3);
            c = rc.nextInt(3);
            if(table[r][c]==' ') {
                setValidCell(r,c,ch);
                return;
            }
        }
    }
    public void playHard(char ch){
        System.out.println("Making move level \"hard\"");
        int bestr=-1,bestc=-1,bestScore=Integer.MIN_VALUE,score;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(table[i][j]!=' ')
                    continue;
                setValidCell(i,j,ch);
                score=minimax(ch,0,false);
              //  System.out.println(i+" "+j+" "+score);
                if(score>bestScore){
                    bestScore=score;
                    bestr=i;
                    bestc=j;
                }
                setValidCell(i,j,' ');
            }
        }
        //System.out.println("Best Move: "+bestr+" "+bestc);
        setValidCell(bestr,bestc,ch);
    }
    int minimax(char ch,int depth,boolean isMaximizing) {
        int score,bestscore;
        if (r != r.NOT_OVER) {
            //System.out.println(r.state+" "+r.NOT_OVER.state);
            if (r.state == ch)
                return 1;
            else if (r.state == 'D')
                return 0;
            else
                return -1;
        }
        if (isMaximizing){
            bestscore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(table[i][j]!=' ')
                        continue;
                    setValidCell(i,j,ch);
                    score=minimax(ch,depth+1,false);
                    setValidCell(i,j,' ');
                    bestscore=Integer.max(score,bestscore);
                }
            }
       } else{
            bestscore=Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(table[i][j]!=' ')
                        continue;
                    if(ch=='X')
                        setValidCell(i,j,'O');
                    else
                        setValidCell(i,j,'X');
                    score=minimax(ch,depth+1,true);
                    setValidCell(i,j,' ');
                    bestscore=Integer.min(score,bestscore);
                }
            }
        }
        return bestscore;
    }
    public void playLevel(String player,char ch){
        switch(player){
            case "easy": playEasy(ch);
                         break;
            case "user": playUser(ch);
                         break;
            case "medium": playMedium(ch);  
                           break;
            case "hard": playHard(ch);
                         break;
            default: System.out.println("Bad parameters!");
                     break;
        }
    }
    public void playGame(String player1,String player2){
        printTable();
        while(r==Result.NOT_OVER){
            if(getFilledX()<=getFilledO()) {
               playLevel(player1,'X');
            }
            else {
               playLevel(player2,'O');
            }
                //printResult(this.r);
                printTable();
            }
          printResult(r);
        }
    public void setTable(){
        Scanner sc=new Scanner(System.in);
        String s;
        while(true){
            System.out.print("Enter the cells: ");
            s=sc.nextLine();
            boolean valid=true;
            for(char ch: s.toCharArray()){
                if(ch!='X'&&ch!='O'&&ch!='_'){
                    valid=false;
                    break;
                }
            }
            if(!valid)
                continue;
            break;
        }
        for(int i=0;i<s.length();i++){
            char temp=s.charAt(i);
            if(temp=='_'){
                temp=' ';
            }
            setValidCell(i/3,i%3,temp);
        }
        printTable();
    }
    public void increaseFilledCells(int x){
        this.filledCells+=x;
    }
    public void decreaseFilledCells(int x){
        this.filledCells-=x;
    }
    public void menu(){
        Scanner sc=new Scanner(System.in);
        String com="",player1="",player2="";
        String[] level=new String[]{"easy","user","medium","hard"};
        while(true) {
            System.out.print("Input commands: ");
            String temp = sc.nextLine();
            String[] arr = temp.split("\s+");
            com = arr[0];
            switch (com) {
                case "exit": exit(0);
                case "start":
                    try {
                        player1 = arr[1];
                        player2 = arr[2];
                    }catch (Exception e){
                        System.out.println("Bad parameters!");
                        break;
                    }
                    if(!Arrays.asList(level)
                        .contains(player1)){
                        System.out.println("Bad parameters!");
                        break;
                    }
                    if(!Arrays.asList(level)
                            .contains(player2)){
                        System.out.println("Bad parameters!");
                        break;
                    }
                    playGame(player1, player2);
                    break;
                default:
                    System.out.println("Bad parameters!");
                    break;
            }
        }

    }
    public void setCell(){
        int r=0,c=0;
        Scanner sc=new Scanner(System.in);
        while(true){
            try {
                System.out.println("Enter the coordinates: ");
                r=sc.nextInt();
                c=sc.nextInt();

            } catch(Exception e){
                System.out.println("You should enter numbers!");
                sc.nextLine();
            }
            if(r<1||r>3||c<1||c>3){
               System.out.println ("Coordinates should be from 1 to 3!");
               continue;
            }
            if(table[r-1][c-1]!=' '){
                System.out.println ("This cell is occupied! Choose another one!");
                continue;
            }
            if(getFilledX()<=getFilledO()){
                setValidCell(r-1,c-1,'X');
            }
            else{

                setValidCell(r-1,c-1,'O');
            }
            printTable();
            break;
        }

    }
}
