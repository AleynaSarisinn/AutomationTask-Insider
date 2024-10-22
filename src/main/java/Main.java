    public class Main{
        public static void main(String[] args){
            String str = "Hello";
            String reversedStr = "";

            for(int i = str.length() -1 ; i>=0 ; i--){
                reversedStr += str.charAt(i);
            }
            System.out.println("ReversedArray:"  + reversedStr);
        }
    }

