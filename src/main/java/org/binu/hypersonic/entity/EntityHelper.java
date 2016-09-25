package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;

/**
 * Helper for creating entities - bomber or bomb type
 * <ul>The entityType will be:
 *  <li>For players: 0.</li>
 *  <li>For bombs: 1.</li>
 *  </ul>
 *  <ul>The owner will be:
 *  <li>For players: id of the player (0 or 1).</li>
 *  <li>For bombs: id of the bomb's owner.</li>
 *  </ul>
 *  <ul>The param1 will be:
 *  <li>For players: number of bombs the player can still place.</li>
 *  <li>For bombs: number of rounds left until the bomb explodes.</li>
 *  </ul>
 *  <ul>The param2 is not useful for the current league, and will always be:
 *  <li>For players: explosion range of the player's bombs (=3).</li>
 *  <li>For bombs: explosion range of the bomb (=3).</li>
 *  </ul>
 */
public class EntityHelper {
    public Entity createEntity(int entityType, int owner, Coordinates coordinates, int param1, int param2) {
        switch (entityType) {
            case 0:
                return new Bomber(owner, coordinates, param1, param2);
            case 1:
                return new Bomb(owner, coordinates, param1, param2);
            default:
                assert false : "Invalid entity: we don't harbour ghosts. Go away!";
                return null;
        }
    }
}
