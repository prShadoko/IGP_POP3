package poplib.factory;

import poplib.command.*;

import java.util.Scanner;

			
public class CommandFactory {
    public Command parse(String input) {
        Command command = null;
        String commandName;
        Scanner scanner = new Scanner(input);
        if(scanner.hasNext()) {
            commandName = scanner.next();
            if(CommandApop.COMMAND_NAME.equals(commandName)) {
                if(scanner.hasNext()) {
                    String mailbox = scanner.next();
                    if(scanner.hasNext()) {
                        String md5 = scanner.next();
                        command = new CommandApop(mailbox, md5);
                    } else {
//                        TODO throw exception
                    }
                } else {
//                    TODO throw exception
                }

            } else if(CommandOk.COMMAND_NAME.equals(commandName)) {
                String comment = "";
                if(scanner.hasNextInt()) {
                    int mailCount = scanner.nextInt();
                    int mailboxSize;
                    if(scanner.hasNextInt()) {
                        mailboxSize = scanner.nextInt();
                        command = new CommandOkStat(mailCount, mailboxSize);
                    } else {
                        comment = Integer.toString(mailCount);
                        if(scanner.hasNextLine()) {
                            comment += scanner.nextLine();
                        }
                        command = new CommandOk(comment);
                    }
                } else {
                    String mail = "";
                    if(scanner.hasNextLine()) {
                        comment += scanner.nextLine().trim();
                    }
                    while(scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if(!line.equals(".")) {
                            mail += line + "\n";
                        }
                    }
                    command = new CommandOkRetr(mail, comment);
                }
            } else if(CommandErr.COMMAND_NAME.equals(commandName)) {
                if(scanner.hasNextLine()) {
                    command = new CommandErr(scanner.nextLine());
                } else {
                    command = new CommandErr();
                }
            } else if(CommandQuit.COMMAND_NAME.equals(commandName)) {
                command = new CommandQuit();
            } else if(CommandRetr.COMMAND_NAME.equals(commandName)) {
                if(scanner.hasNextInt()) {
                    int messageId = scanner.nextInt();
                    command = new CommandRetr(messageId);
                } else {
//                    TODO: throw exception
                }
            } else if(CommandStat.COMMAND_NAME.equals(commandName)) {
                command = new CommandStat();
            }
        }
        scanner.close();
        return command;
    }
}
