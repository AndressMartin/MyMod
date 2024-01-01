package mymod.behaviours.spireModifications;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.map.MapRoomNode;

@SpirePatch2(
        clz= MapRoomNode.class,
        method="update"
)
public class MapRoomNode_UpdatePatch {

    public static void Patch(){
        
    }
}
