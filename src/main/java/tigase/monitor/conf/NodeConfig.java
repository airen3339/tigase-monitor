/*
 * Tigase Jabber/XMPP Server
 * Copyright (C) 2004-2008 "Artur Hefczyc" <artur.hefczyc@tigase.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://www.gnu.org/licenses/.
 *
 * $Rev: 4 $
 * Last modified by $Author: kobit $
 * $Date: 2009-09-23 19:38:59 +0100 (Wed, 23 Sep 2009) $
 */

package tigase.monitor.conf;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;

//~--- classes ----------------------------------------------------------------

/**
 * Created: Sep 9, 2009 6:29:24 PM
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 4 $
 */
public class NodeConfig {
	private String color = null;
	private String description = null;
	private String hostname = null;
	private String password = null;
	private int port = -1;
	private String userName = null;

	//~--- constructors ---------------------------------------------------------

	/**
	 * Constructs ...
	 *
	 *
	 * @param description
	 * @param colour
	 * @param hostname
	 * @param port
	 * @param userName
	 * @param password
	 */
	public NodeConfig(String description, String colour, String hostname, int port,
			String userName, String password) {
		this.description = description;
		this.color = colour;
		this.hostname = hostname;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

	//~--- get methods ----------------------------------------------------------

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public Color getColor() {
		Color myFill = Color.MAGENTA;

		if (color.equals("blue")) {
			myFill = Color.BLUE;
		}

		if (color.equals("green")) {
			myFill = Color.GREEN;
		}

		if (color.equals("white")) {
			myFill = Color.WHITE;
		}

		if (color.equals("yellow")) {
			myFill = Color.YELLOW;
		}

		if (color.equals("black")) {
			myFill = Color.BLACK;
		}

		if (color.equals("lightblue")) {
			myFill = new Color(0.75f, 0.75f, 1f);
		}

		if (color.equals("lightgreen")) {
			myFill = new Color(0.75f, 1f, 0.75f);
		}

		if (color.equals("gray")) {
			myFill = Color.GRAY;
		}

		if (color.equals("orange")) {
			myFill = Color.ORANGE;
		}

		if (color.equals("red")) {
			myFill = Color.RED;
		}

		if (color.equals("pink")) {
			myFill = Color.PINK;
		}

		return myFill;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public String getColorStr() {
		return color;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	//~--- methods --------------------------------------------------------------

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return description + " - " + hostname;
	}
}


//~ Formatted in Sun Code Convention


//~ Formatted by Jindent --- http://www.jindent.com