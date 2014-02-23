package at.pavlov.cannons.event;

import at.pavlov.cannons.cannon.Cannon;
import at.pavlov.cannons.config.MessageEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CannonDestroyedEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Cannon cannon;

	public CannonDestroyedEvent(Cannon cannon) {

        this.cannon = cannon;
    }
	
	public HandlerList getHandlers() {
		return handlers;
	}
	
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Cannon getCannon() {
        return cannon;
    }
}