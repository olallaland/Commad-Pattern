package commands;

import javafx.util.Pair;
import java.util.LinkedList;

public class Model {
    StringBuilder str;
    LinkedList<Pair<String, String>> stack;
    LinkedList<Pair<String, String>> redoStack;

    public Model() {
        this.str = new StringBuilder();
        this.stack = new LinkedList<>();
        this.redoStack = new LinkedList<>();
    }
    /**
     * print the current string being edited
     */
    public void show(String command) {
        System.out.println(this.str);
        stack.push(new Pair<String, String>(command, this.str.toString()));
    }

    /**
     * append a string at the end
     *
     * @param command
     */
    public void addLast(String command) {
        String content = getStringParam(command);
        this.str.append(content);
        System.out.println(this.str);
        stack.push(new Pair<String, String>(command, this.str.toString()));
    }

    /**
     * insert a string at the head
     *
     * @param command
     */
    public void addFirst(String command) {
        String content = getStringParam(command);
        this.str = new StringBuilder(content + this.str);
        System.out.println(this.str);
        stack.push(new Pair<String, String>(command, this.str.toString()));
    }

    /**
     * delete string at the end
     *
     * @param command
     */
    public void deleteLast(String command) {
        int length = getIntParam(command);
        try {
            this.str.delete(str.length() - length, str.length());
        } catch (StringIndexOutOfBoundsException e) {
            this.str = new StringBuilder();
        }
        System.out.println(str);
        stack.push(new Pair<String, String>(command, this.str.toString()));
    }

    /**
     * delete string at the head
     *
     * @param command
     */
    public void deleteFirst(String command) {
        int length = getIntParam(command);
        try {
            this.str.delete(0, length);
        } catch (StringIndexOutOfBoundsException e) {
            this.str = new StringBuilder();
        }
        System.out.println(str);
        stack.push(new Pair<String, String>(command, this.str.toString()));
    }

    /**
     * list the most recently executed commands
     * @param command
     */
    public void list(String command) {
        int count = getIntParam(command);
        int size = stack.size();

        System.out.println("--part--");
        for(int i = 1; i <= Math.min(count, size); i++) {
            System.out.println(i + " " + stack.get(i - 1).getKey());
        }
        stack.push(new Pair<String, String>(command, this.str.toString()));
    }

    /**
     * undo the last command
     */
    public void undo() {
        redoStack.push(stack.pop());
        Pair<String, String> lastState = stack.getFirst();
        this.str = new StringBuilder(lastState.getValue());
        System.out.println(this.str);
    }

    /**
     * redo the the last undo
     */
    public void redo() {
        Pair<String, String> lastState = redoStack.pop();
        stack.push(lastState);
        this.str = new StringBuilder(lastState.getValue());
        System.out.println(this.str);
    }

    /**
     * combine the last several commands executed into a macro command
     * @param count
     * @param name
     */
    public void define(int count, String name) {

    }


    /**
     * split content from input
     * @param input
     * @return
     */
    static String getStringParam(String input) {
        String[] strings = input.split("\"");
        String content = "";
        try {
            content = strings[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            content = "";
        }
        return content;
    }

    /**
     * split content from input
     * @param input
     * @return
     */
    static int getIntParam(String input) {
        String[] strings = input.split(" ");
        int count = 0;
        try {
            count = Integer.parseInt(strings[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            count = 1;
        }
        return count;
    }

    static void printStack(LinkedList<Pair<String, String>> stack) {
        System.out.println("--whole--");
        for (int i = 0; i < stack.size(); i++) {
            System.out.println(stack.get(i));
        }
    }
}
