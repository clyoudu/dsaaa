package github.clyoudu.stack.example;

import github.clyoudu.stack.ArrayStack;
import org.apache.commons.lang3.StringUtils;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 15:05
 * @Description SymbolPairChecker
 */
public class SymbolPairChecker {

    public static boolean check(String pattern) {

        if (StringUtils.isNoneBlank(pattern)) {
            ArrayStack<Character> stack = new ArrayStack<>();
            for (int i = 0; i < pattern.length(); i++) {
                Character character = pattern.charAt(i);
                switch (character) {
                    case '{':
                    case '[':
                    case '(':
                        if (!stack.isEmpty()) {
                            Character preCharacter = stack.peek();
                            if (!order(character, preCharacter)) {
                                throw new RuntimeException(formatError(pattern, i));
                            }
                        }
                        stack.push(character);
                        break;
                    case ')':
                    case ']':
                    case '}':
                        if (stack.isEmpty()) {
                            throw new RuntimeException(formatError(pattern, i));
                        }
                        Character preCharacter = stack.pop();
                        if (!match(character, preCharacter)) {
                            throw new RuntimeException(formatError(pattern, i));
                        }
                }
            }

            if(!stack.isEmpty()){
                throw new RuntimeException(formatError(pattern, pattern.length() - 1));
            }
        }

        return true;
    }

    private static boolean match(Character character, Character preCharacter) {
        switch (character) {
            case '}':
                if(preCharacter != '{'){
                    return false;
                }
                break;
            case ']':
                if(preCharacter != '['){
                    return false;
                }
                break;
            case ')':
                if(preCharacter != '('){
                    return false;
                }
                break;
        }
        return true;
    }

    private static boolean order(Character character, Character preCharacter) {

        return character <= preCharacter;
    }

    private static String formatError(String pattern, int i) {
        return "Symbol not match!\n" + pattern + "\n" + pointer(i);
    }

    private static String pointer(int i) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < i; j++) {
            result.append(" ");
        }
        return result + "^";
    }

    public static void main(String[] args) {
        check("{(a+b)/c-d+[(e-f)/(g+h)+i]}/100");
        //check("(a+b)/c-d+[(e-f)/(g+h)+i]}/100");
        //check("((a+b)/c-d+[(e-f)/(g+h)+i]}/100");
        //check("([])");
        //check("({})");
        check("[");
    }

}
