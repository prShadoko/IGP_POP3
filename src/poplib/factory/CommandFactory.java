package poplib.factory;

import poplib.command.Command;
import poplib.command.CommandApop;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.command.CommandQuit;
import poplib.command.CommandRetr;
import poplib.command.CommandStat;

public class CommandFactory {

	public Command parse(String cmd) {
		Command command = null;
		String commandName;
		String commandArg;

		if (null != cmd) {

			int firstSpacePosition = cmd.indexOf(' ');
			if (-1 == firstSpacePosition) {
				cmd = cmd.replace("\n", "");
				commandName = cmd;
				commandArg = null;
			} else {
				commandName = cmd.substring(0, firstSpacePosition);
				commandArg = cmd.substring(firstSpacePosition+1);
			}

			if (CommandApop.COMMAND_NAME.equals(commandName)) {
				if(null != commandArg) {
					String[] args = commandArg.split(" ");
	
					if (args.length == 2) {
						command = new CommandApop(args[0], args[1]);
					}
				}
			} else if (CommandOk.COMMAND_NAME.equals(commandName)) {
				command = new CommandOk(commandArg);
			} else if (CommandErr.COMMAND_NAME.equals(commandName)) {
				command = new CommandErr();
			} else if (CommandQuit.COMMAND_NAME.equals(commandName)) {
				command = new CommandQuit();
			} else if (CommandRetr.COMMAND_NAME.equals(commandName)) {
				try {
					commandArg = commandArg.replace("\n", "");
					int messageId = Integer.parseInt(commandArg);
					command = new CommandRetr(messageId);

				} catch (NumberFormatException e) {

				}
			} else if (CommandStat.COMMAND_NAME.equals(commandName)) {
				command = new CommandStat();
			}

		}
		return command;
	}
}
