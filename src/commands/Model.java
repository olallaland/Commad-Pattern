package commands;

import javafx.util.Pair;

import java.util.Stack;

public class Model {
    StringBuilder str;
    Stack<Pair<String, String>> stack;
    Stack<Pair<String, String>> redoStack;

    public Model() {
        this.str = new StringBuilder();
        this.stack = new Stack<>();
        this.redoStack = new Stack<>();
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
     * @param content
     */
    public void addLast(String content) {
        this.str.append(content);
    }

    /**
     * insert a string at the head
     *
     * @param content
     */
    public void addFirst(String content) {
        this.str.insert(0, content);
    }

    /**
     * delete string at the end
     *
     * @param length
     */
    public void deleteLast(int length) {
        this.str.delete(str.length() - length, str.length());
    }

    /**
     * delete string at the head
     *
     * @param length
     */
    public void deleteFirst(int length) {
        this.str.delete(0, length);
    }

    /**
     * list the most recently executed commands
     * @param count
     */
    public void list(int count) {

    }

    /**
     * undo the last command
     */
    public void undo() {

    }

    /**
     * redo the the last undo
     */
    public void redo() {

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
}
