package me.Sesantanove.CarryHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import org.apache.logging.log4j.LogManager;

public class CommandHandler extends CommandBase {
    @Override
    public String getCommandName() {
        return "CarryHelper";
    }

    @Override
    public String getCommandUsage( ICommandSender sender) {
        return "Open an UI to remember how many times you need to carry another dude";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args){
        Main.screenToOpenNextTick = new GUI_Handler();
        LogManager.getLogger("Comando Test GUI").info("Teoricamente dovrei aver aperto la GUI!");
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

}