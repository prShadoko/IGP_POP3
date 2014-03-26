package poplib.factory;

import poplib.Command;
import poplib.CommandApop;
import poplib.CommandErr;
import poplib.CommandOk;
import poplib.CommandQuit;
import poplib.CommandRetr;
import poplib.CommandStat;

public class CommandFactory {

	public Command parse(String cmd) {
		Command command = null;
		String commandName;
		String commandArg;

		if (null != cmd) {

			int firstSpacePosition = cmd.indexOf(' ');
			if (-1 != firstSpacePosition) {
				commandName = cmd;
				commandArg = null;
			} else {
				commandName = cmd.substring(0, firstSpacePosition);
				commandArg = cmd.substring(firstSpacePosition);
			}

			if (CommandApop.COMMAND_NAME.equals(commandName)) {
				String[] args = commandArg.split(" ");

				if (args.length == 2) {
					command = new CommandApop(args[0], args[1]);
				}
			} else if (CommandOk.COMMAND_NAME.equals(commandName)) {
				command = new CommandOk(commandArg);
			} else if (CommandErr.COMMAND_NAME.equals(commandName)) {
				command = new CommandErr();
			} else if (CommandQuit.COMMAND_NAME.equals(commandName)) {
				command = new CommandQuit();
			} else if (CommandRetr.COMMAND_NAME.equals(commandName)) {
				try {
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
