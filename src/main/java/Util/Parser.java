package Util;

import Command.ActionCommand;
import Command.FindCommand;
import Exceptions.DukeException;
import Tasks.DeadlineTask;
import Tasks.EventTask;
import Tasks.TODOTask;
import Tasks.Task;
import Constants.Commands;
import Constants.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * General utility class
 * */
public class Parser {
    /*
     * Reads the next line of user input
     * */
    public static String getNextLineOfInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        return line;
    }

    /*
     * Validate the command format and see if it is a valid action command
     * */
    public static Boolean validateActionCommand(String commandStr) {
        String[] subStr = commandStr.split(" ");
        try {
            if (subStr.length == 2) {
                int index = Integer.parseInt(subStr[1]);
                return subStr[0].equals(Commands.MARK) || subStr[0].equals(Commands.UNMARK) || subStr[0].equals(Commands.DELETE);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * Validate the command format and see if it is a valid find command
     * */
    public static Boolean validateFindCommand(String commandStr) {
        String[] subStr = commandStr.split(" ");
        try {
            if (subStr.length == 2) {
                return subStr[0].equals(Commands.FIND);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * Process components of a find command
     * */
    public static FindCommand processFindCommand(String commandStr) {
        return new FindCommand(commandStr.split(" ")[1]);
    }

    /*
     * Process components of a find command
     * */
    public static ActionCommand processActionCommand(String commandStr) throws DukeException {
        String[] subStr = commandStr.split(" ");
        TaskType type;

        if (subStr[0].equals(Commands.MARK)) {
            type = TaskType.MARK;
        } else if (subStr[0].equals(Commands.UNMARK)) {
            type = TaskType.UNMARK;
        } else if (subStr[0].equals(Commands.DELETE)) {
            type = TaskType.DELETE;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return new ActionCommand(type, Integer.parseInt(subStr[1]));
    }


    public static Task getTask(int index, String commandStr) throws DukeException {
        String[] words = commandStr.split(" ");

        String description = "";
        String time = "";

        if (words[0].equals(Commands.TODO)) {
            if (words.length == 1) {
                throw new DukeException("The description of a " + words[0] + " cannot be empty.");
            }

            for (int i = 1; i < words.length; i++) {
                description += words[i];

                if (i != words.length - 1) {
                    description += " ";
                }
            }

            return new TODOTask(index, description);
        } else if (words[0].equals(Commands.EVENT) || words[0].equals(Commands.DEADLINE)) {
            boolean isEventTask = words[0].equals(Commands.EVENT);
            int atIndex = -1;
            if (words.length == 1) {
                throw new DukeException("The description of a " + words[0] + " cannot be empty.");
            }

            for (int i = 1; i < words.length; i++) {
                if (words[i].equals(isEventTask ? "/at" : "/by")) {
                    atIndex = i;
                    break;
                }
                description += words[i];
                description += " ";
            }

            if (atIndex + 1 >= words.length || atIndex == -1) {
                throw new DukeException("failed");
            }

            for (int i = atIndex + 1; i < words.length; i++) {
                time += words[i];

                if (i != words.length - 1) {
                    time += " ";
                }
            }

            return isEventTask ? new EventTask(index, description, time) : new DeadlineTask(index, description, time);
        }

        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    public static String attemptToConvertTimeString(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toString();
        } catch (Exception e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm");
                LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
                return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toString();
            } catch (Exception e1) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
                    return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toString();
                } catch (Exception e2) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy H:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
                        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toString();
                    } catch (Exception e3) {
                        return time;
                    }
                }
            }
        }
    }
}
