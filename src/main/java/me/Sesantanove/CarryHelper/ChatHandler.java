package me.Sesantanove.CarryHelper;

import net.minecraft.client.Minecraft;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatHandler {
    @SubscribeEvent
    public void ChatHandler ( ClientChatReceivedEvent e){
        if (e.message.getUnformattedText ().contains ( "Trade completed with" )){
            try {
                Thread.sleep ( 100 );
            }catch ( InterruptedException exception ){
                System.out.println ( exception );
            }
            String nickname = e.message.getUnformattedText ().replaceAll ( "Trade completed with ", "" ).replaceAll ( "[VIP] ", "" ).replaceAll ( "[VIP+] ", "" ).replaceAll ( "[MVP] ", "" ).replaceAll ( "[MVP+] ", "" ).replaceAll ( "[MVP++] ", "" ).replaceAll ( "!", "" );
            IChatComponent comp = new ChatComponentText ("§9§lCarry§a§lHelper §7» §eClick me to add §a"+ nickname + "§e to the list of people to carry");
            ChatStyle style = new ChatStyle().setChatClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/carryhelper "+nickname+" 1" ) {
                @Override
                public Action getAction() {
                    return ClickEvent.Action.RUN_COMMAND;
                }
            });
            style.setChatHoverEvent ( new HoverEvent ( HoverEvent.Action.SHOW_TEXT, new ChatComponentText ( "§eClicking me will add "+nickname+ " to /carryhelper menu!" ) ) );
            comp.setChatStyle(style);
            Minecraft.getMinecraft ().thePlayer.addChatComponentMessage ( comp );
        }
    }
}
