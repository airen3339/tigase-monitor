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
 * $Rev: 6 $
 * Last modified by $Author: kobit $
 * $Date: 2009-09-29 13:37:21 +0100 (Tue, 29 Sep 2009) $
 */

package tigase.monitor.conf;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

//~--- classes ----------------------------------------------------------------

/**
 * Created: Sep 9, 2009 6:24:08 PM
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 6 $
 */
public class Configuration {
	private static final String HEIGHT_DEF = "1100";
	private static final String HEIGHT_KEY = "height";
	private static final String JMX_PASSWORD_DEF = "admin_pass";
	private static final String JMX_PASSWORD_KEY = "jmx-pass";
	private static final String JMX_USER_NAME_DEF = "admin";
	private static final String JMX_USER_NAME_KEY = "jmx-user";
	private static final String LOAD_HISTORY_DEF = "true";
	private static final String LOAD_HISTORY_KEY = "load-history";
	private static final String NODES_DEF =
		"pink:pink.tigase.org,green:green.tigase.org,"
		+ "blue:blue.tigase.org,white:black1.tigase.org,yellow:black2.tigase.org";
	private static final String NODES_KEY = "nodes";
	private static final String TIMELINE_DEF = "3600";
	private static final String TIMELINE_KEY = "timeline";
	private static final String UPDATERATE_DEF = "0.5";
	private static final String UPDATERATE_KEY = "framerate";
	private static final String WIDTH_DEF = "1850";
	private static final String WIDTH_KEY = "width";

	//~--- fields ---------------------------------------------------------------

	private String config_filename = "init.properties";
	private int height = 1100;
	private List<NodeConfig> nodes = null;
	private Properties props = null;
	private int timeline = 3600;
	private float updaterate = 0.5f;
	private String userName = "admin";
	private String userPass = "admin_pass";
	private int width = 1850;
	private boolean loadHistory = true;

	//~--- constructors ---------------------------------------------------------

	/**
	 * Constructs ...
	 *
	 *
	 * @param filename
	 *
	 * @throws IOException
	 */
	public Configuration(String filename) throws IOException {
		if (filename != null) {
			config_filename = filename;
		}

		props = new Properties();
		props.load(new FileInputStream(filename));

		String updaterate_str = props.getProperty(UPDATERATE_KEY, UPDATERATE_DEF);

		updaterate = Float.parseFloat(updaterate_str);
		userName = props.getProperty(JMX_USER_NAME_KEY, JMX_USER_NAME_DEF);
		userPass = props.getProperty(JMX_PASSWORD_KEY, JMX_PASSWORD_DEF);

		String[] nodes_arr = props.getProperty(NODES_KEY, NODES_DEF).split(",");

		nodes = new LinkedList<NodeConfig>();

		for (String node : nodes_arr) {
			String[] node_arr = node.split(":");
			String user = userName;

			if (node_arr.length > 2) {
				user = node_arr[2];
			}

			String pass = userPass;

			if (node_arr.length > 3) {
				pass = node_arr[3];
			}

			nodes.add(new NodeConfig(node_arr[0], node_arr[0], node_arr[1], 9050, user, pass));
		}

		String height_str = props.getProperty(HEIGHT_KEY, HEIGHT_DEF);

		height = Integer.parseInt(height_str);

		String width_str = props.getProperty(WIDTH_KEY, WIDTH_DEF);

		width = Integer.parseInt(width_str);

		String timeline_str = props.getProperty(TIMELINE_KEY, TIMELINE_DEF);

		timeline = Integer.parseInt(timeline_str);

		String loadHistoryStr = props.getProperty(LOAD_HISTORY_KEY, LOAD_HISTORY_DEF);

		loadHistory = loadHistoryStr.equals("true");
	}

	//~--- get methods ----------------------------------------------------------

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public boolean getLoadHistory() {
		return loadHistory;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public List<NodeConfig> getNodeConfigs() {
		return nodes;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public int getTimeline() {
		return timeline;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public float getUpdaterate() {
		return updaterate;
	}

	/**
	 * Method description
	 *
	 *
	 * @return
	 */
	public int getWidth() {
		return width;
	}
}


//~ Formatted in Sun Code Convention


//~ Formatted by Jindent --- http://www.jindent.com