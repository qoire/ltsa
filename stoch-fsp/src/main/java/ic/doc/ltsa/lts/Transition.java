/******************************************************************************
 * LTSA (Labelled Transition System Analyser) - LTSA is a verification tool   *
 * for concurrent systems. It mechanically checks that the specification of a *
 * concurrent system satisfies the properties required of its behaviour.      *
 * Copyright (C) 2001-2004 Jeff Magee (with additions by Robert Chatley)      *
 *                                                                            *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License          *
 * along with this program; if not, write to the Free Software                *
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA *
 *                                                                            *
 * The authors can be contacted by email at {jnm,rbc}@doc.ic.ac.uk            *
 *                                                                            *
 ******************************************************************************/

package ic.doc.ltsa.lts;

/** Represents a simple transition in a process */
public class Transition {
    int from;
    int to;
    Symbol event;

    Transition() {}

    Transition(int from) {this.from = from;}

    Transition(int from, Symbol event, int to) {
        this.from = from;
        this.to = to;
        this.event = event;
    }

	/** Adds this transition to the specified state machine. */
	public void addToMachine( CompactState c, StateMachine s ) {
		c.getStates()[from] = EventState.add( c.getStates()[from], new EventState( s.getEventNumber( event ), to ) );
	}

    public String toString() {
        return ""+from+" "+event+" "+to;
    }
}
