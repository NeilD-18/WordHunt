package view;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import net.fellbaum.jemoji.*;


public class TestEmoji {
   public static void main(String[] args) {
        Set<Emoji> emojis=EmojiManager.getAllEmojis();
        
        List<Emoji> list = new ArrayList<>(emojis);
        for (int i =0; i < list.size(); i++) { 
            System.out.println(list.get(i).getAllAliases());
        }
        
        
   }

       
}
