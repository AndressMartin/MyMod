package mymod.behaviours.spireModifications;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import mymod.relics.InsidersMap;

import java.util.Iterator;

@SpirePatch2(
        clz= MapRoomNode.class,
        method="update"

)
public class MapRoomNode_WingedPatch {

    public static boolean InsidersConnectedTo(MapRoomNode currentNode, MapRoomNode node){
        Iterator var2 = currentNode.getEdges().iterator();
        boolean hasRelic = AbstractDungeon.player.hasRelic(InsidersMap.ID);
        MapEdge edge;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            edge = (MapEdge)var2.next();
            if (ModHelper.isModEnabled("Flight") && node.y == edge.dstY) {
                return true;
            }
        } while(node.y != edge.dstY || !hasRelic);

        return true;
    }

    @SpireInsertPatch(
        loc=276,
        localvars={"wingedConnection"}
    )
    public static void InsertInsidersCheck(MapRoomNode __instance, boolean wingedConnection){
        wingedConnection = InsidersConnectedTo(AbstractDungeon.getCurrMapNode(), __instance);
        System.out.println(wingedConnection);
    }
}
