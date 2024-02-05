class Util {
    public static int indexOf(String src, String tgt) {
        // your code here
        if(src.length()<tgt.length())
        return -1;
        int i;
        for(i=0;i<tgt.length();i++){
            if(src.charAt(i)!=tgt.charAt(i)){
                break;
            }
        }
        if(i==tgt.length()){
            return 0;
        }
        int temp=indexOf(src.substring(1),tgt);
        if(temp==-1)
            return temp;
        else
            return 1+temp;
    }
}