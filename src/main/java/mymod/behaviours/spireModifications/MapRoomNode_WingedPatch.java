package mymod.behaviours.spireModifications;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.WingBoots;
import mymod.relics.InsidersMap;

import java.util.Iterator;

@SpirePatch2(
        clz= MapRoomNode.class,
        method="wingedIsConnectedTo"
)
public class MapRoomNode_WingedPatch {

    @SpirePrefixPatch
    public static SpireReturn<Boolean> WingedPatch(MapRoomNode __instance, MapRoomNode node){
        Iterator var2 = __instance.getEdges().iterator();
        boolean hasRelic = AbstractDungeon.player.hasRelic(InsidersMap.ID) ||
                (AbstractDungeon.player.hasRelic(WingBoots.ID) ||
                AbstractDungeon.player.getRelic(WingBoots.ID).counter > 0);
        MapEdge edge;
        do {
            if (!var2.hasNext()) {
                return SpireReturn.Return(Boolean.FALSE);
            }

            edge = (MapEdge)var2.next();
        } while(node.y != edge.dstY -2 || !hasRelic || node.taken || !node.isConnectedTo(__instance));
        return SpireReturn.Return(Boolean.TRUE);
    }
}
