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
        this.stack.push(new Pair<>("init", ""));
        this.redoStack = new LinkedList<>();
    }

    public StringBuilder getModel() {
        return this.str;
    }

    /**
     * print the current string being edited
     */
    public void show() {
        System.out.println(this.str);
    }

    /**
     * append a string at the end
     *
     * @param command
     */
    public void addLast(String command) {
        String content = getStringParam(command);
        this.str.append(content);
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
        stack.push(new Pair<String, String>(command, this.str.toString()));
    }

    /**
     * list the most recently executed commands
     * @param command
     */
    public void list(String command) {
        int count = getIntParam(command);
        int size = stack.size();

        //System.out.println("--part--");
        for(int i = 1; i <= Math.min(count, size); i++) {
            System.out.println(i + " " + stack.get(i - 1).getKey());
        }
    }

    /**
     * undo the last command by popping the stack
     */
    public void undo() {
        redoStack.push(stack.pop());
        Pair<String, String> lastState = stack.getFirst();
        this.str = new StringBuilder(lastState.getValue());
    }

    /**
     * redo the the last undo by recover the stack
     */
    public void redo() {
        Pair<String, String> lastState = redoStack.pop();
        stack.push(lastState);
        this.str = new StringBuilder(lastState.getValue());
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
        } catch (Exception e) {
            throw new RuntimeException(" -- invalid command -- ");
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
        } catch (Exception e) {
            throw new RuntimeException(" -- invalid command -- ");
        }
        return count;
    }

}
