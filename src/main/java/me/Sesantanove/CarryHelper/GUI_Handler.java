package me.Sesantanove.CarryHelper;

import javafx.scene.control.Skin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.util.ResourceLocation;
import scala.collection.parallel.ParIterableLike;

import java.io.IOException;
import java.util.HashMap;

public class GUI_Handler extends GuiScreen {

    int lastClickedButton = 0;
    static HashMap <String, Integer> carry = new HashMap <> ();
    private GuiTextField text;
    private GuiTextField text2;

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton (0, width / 2 - 85, height / 2 + 60, 30, 20, "§aAdd"));
        this.buttonList.add(new GuiButton(1, width / 2 - 35, height / 2 + 60, 45, 20, "§cRemove"));
        this.buttonList.add(new GuiButton(2, width / 2 + 25, height / 2 + 60, 50, 20, "§4§lCLEAR"));
        this.buttonList.add(new GuiButton(3, width / 2 + 85, height / 2 + 60, 30, 20, "§9Lap"));
        this.text = new GuiTextField( 4, this.fontRendererObj, width / 2 - 84, height / 2 + 30, 147, 20);
        this.text.setMaxStringLength(50);
        this.text.setText("Player Nickname");
        this.text.setTextColor ( -1 );
        this.text.setFocused(false);
        this.text2 = new GuiTextField( 4, this.fontRendererObj, width / 2 + 85, height / 2 + 30, 30, 20);
        this.text2.setMaxStringLength(50);
        this.text2.setText("1");
        this.text2.setTextColor ( -1 );
        this.text2.setFocused(false);
    }
    @Override
    protected void keyTyped(char par1, int par2) {
        try {
            super.keyTyped(par1, par2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.text.textboxKeyTyped(par1, par2);
        this.text2.textboxKeyTyped(par1, par2);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.text.updateCursorCounter();
        this.text2.updateCursorCounter();
    }

    @Override
    protected void mouseClicked(int x, int y, int btn) {
        try {
            super.mouseClicked(x, y, btn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.text.mouseClicked(x, y, btn);
        this.text2.mouseClicked(x, y, btn);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        lastClickedButton=button.id;
        if (lastClickedButton == 0) {
            carry.put (text.getText(), Integer.parseInt(text2.getText()) );
        } else if (lastClickedButton == 1) {
            carry.remove ( text.getText () );
        } else if (lastClickedButton == 2) {
            carry.clear ();
        }else if (lastClickedButton == 3) {
            HashMap<String, Integer> c = new HashMap<String, Integer>();
            for (final String i : this.carry.keySet()) {
                final int tmp = this.carry.get(i) - 1;
                if (tmp > 0) {
                    c.put(i, tmp);
                }
            }
            this.carry.clear();
            this.carry = c;
        }
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("carryhelper", "textures/gui/background.png"));
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        int n=0;
        String txt="";
        for (String i : carry.keySet()) {
            txt=i+" "+carry.get ( i )+"x Times";
            fr.drawString(txt, width / 2 - 85, height / 2 - (58-(n*10)), -1);
            n++;
        }
        this.text.drawTextBox();
        this.text2.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}







//       ⣠⣤⣤⣤⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⢰⡿⠋⠁⠀⠀⠈⠉⠙⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⢀⣿⠇⠀⢀⣴⣶⡾⠿⠿⠿⢿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//⠀⠀⣀⣀⣸⡿⠀⠀⢸⣿⣇⠀⠀⠀⠀⠀⠀⠙⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//⠀⣾⡟⠛⣿⡇⠀⠀⢸⣿⣿⣷⣤⣤⣤⣤⣶⣶⣿⠇⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀
//⢀⣿⠀⢀⣿⡇⠀⠀⠀⠻⢿⣿⣿⣿⣿⣿⠿⣿⡏⠀⠀⠀⠀⢴⣶⣶⣿⣿⣿⣆
//⢸⣿⠀⢸⣿⡇⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⣿⡇⣀⣠⣴⣾⣮⣝⠿⠿⠿⣻⡟
//⢸⣿⠀⠘⣿⡇⠀⠀⠀⠀⠀⠀⠀⣠⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠉⠀
//⠸⣿⠀⠀⣿⡇⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀
//⠀⠻⣷⣶⣿⣇⠀⠀⠀⢠⣼⣿⣿⣿⣿⣿⣿⣿⣛⣛⣻⠉⠁⠀⠀⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠙⠛⠛⠛⠋⠁⠀⠙⠻⠿⠟⠋⠑⠛⠋
// Why u still here????
// Now that u saw me like the repo thx
