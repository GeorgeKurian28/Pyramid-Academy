

public class ReverseString {
    public static void main(String ...args){
        String str = "123456"; //String to be passed
        ReverseString regvserseString = new ReverseString(); // instance of the class defined
        str=regvserseString.reverseString(str);// call the method pass the string
        System.out.println(str);
    }

    public String reverseString(String str){
        char[] arr = str.toCharArray();

        for(int  i = 0; i <str.length()/2; i++){
            arr[i] = str.charAt(str.length()-1-i);// copy the last element to the c to store and swap
            arr[str.length()-1-i]=str.charAt(i);
        }
        return String.valueOf(arr);
    }
}
