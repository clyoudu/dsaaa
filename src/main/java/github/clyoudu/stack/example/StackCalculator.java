package github.clyoudu.stack.example;

import github.clyoudu.stack.ArrayStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 15:35
 * @Description StackCalculator
 */
public class StackCalculator {

    private final static Map<Character, Integer> CHARACTER_WEIGHT_MAP = new HashMap<>();

    static {
        CHARACTER_WEIGHT_MAP.put('+', 1);
        CHARACTER_WEIGHT_MAP.put('-', 1);
        CHARACTER_WEIGHT_MAP.put('*', 2);
        CHARACTER_WEIGHT_MAP.put('/', 2);
        CHARACTER_WEIGHT_MAP.put('(', 3);
        CHARACTER_WEIGHT_MAP.put(')', 4);
    }

    public static Double eval(String exp) {
        exp = exp.replaceAll(" ", "")//干掉空格
                .replaceAll("\\(-", "(0-");//转换负数(-2) -> (0-2)，其中负数必须用括号括起来
        SymbolPairChecker.check(exp);

        List<String> reversePolishExp = reversePolishExp(exp);

        ArrayStack<Double> numStack = new ArrayStack<>();

        for (String s : reversePolishExp) {
            try {
                Double d = Double.parseDouble(s);
                numStack.push(d);
            } catch (NumberFormatException e){
                Double b = numStack.pop();
                Double a = numStack.pop();
                switch (s){
                    case "+":
                        numStack.push(a + b);
                        break;
                    case "-":
                        numStack.push(a - b);
                        break;
                    case "*":
                        numStack.push(a * b);
                        break;
                    case "/":
                        numStack.push(a / b);
                        break;
                }
            }
        }

        return numStack.pop();
    }

    public static List<String> reversePolishExp(String exp) {
        List<String> list = new ArrayList<>();
        ArrayStack<Character> stack = new ArrayStack<>();

        StringBuilder num = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            Character character = exp.charAt(i);
            String type = typeOfCharacter(character);
            if (type.equals("num")) {
                num.append(character);
            } else if (type.equals("op")) {
                if(num.length() > 0){
                    list.add(num.toString());
                    num = new StringBuilder();
                }
                if (stack.isEmpty()) {//空栈则直接将操作符入栈
                    stack.push(character);
                } else {
                    if (character == ')') {//如果是)，依次打印栈中所有操作符，直到出现(，其中(需要弹出但不输出
                        Character toAppend = stack.pop();
                        while (toAppend != '(') {
                            list.add(String.valueOf(toAppend));
                            toAppend = stack.pop();
                        }
                    } else {//其他情况时，如果此次操作符权重高于或等于栈中操作符，本次操作符压栈；否则输出栈顶操作符
                        Character preCharacter = stack.peek();

                        while (preCharacter != '(' && CHARACTER_WEIGHT_MAP.get(character) <= CHARACTER_WEIGHT_MAP.get(preCharacter)) {
                            list.add(String.valueOf(stack.pop()));
                            if(stack.isEmpty()){
                                break;
                            }
                            preCharacter = stack.peek();
                        }
                    }

                    //本次操作符入栈
                    if(character != ')'){
                        stack.push(character);
                    }
                }
            }
        }

        if(num.length() > 0){
            list.add(num.toString());
        }

        if(!stack.isEmpty()){
            while (!stack.isEmpty()){
                list.add(String.valueOf(stack.pop()));
            }
        }

        return list;
    }


    public static String typeOfCharacter(Character character) {
        switch (character) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
                return "op";
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '.':
                return "num";
            default:
                throw new RuntimeException("Character not allowed here : " + character);
        }
    }

    public static void main(String[] args) {
        System.out.println(eval("((12+40)*2-4+((5-6/2)/(1+2)+100))/100"));
        System.out.println(eval("11+22*33+(44*55+66)*77"));
        System.out.println(eval("1/2-3*(4+5)"));
    }

}
