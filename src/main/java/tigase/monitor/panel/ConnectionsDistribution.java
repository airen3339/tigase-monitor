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

package tigase.monitor.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;
import tigase.stats.StatisticsProviderMBean;

/**
 * Created: Sep 9, 2009 7:53:03 PM
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 4 $
 */
public class ConnectionsDistribution extends TigaseMonitor {

	private DefaultPieDataset data = null;
	private List<JFreeChart> charts = new LinkedList<JFreeChart>();
	private JPanel panel = null;

	public ConnectionsDistribution() {
		data = new DefaultPieDataset();
//		PiePlot plot = new PiePlot3D(data);
//		((PiePlot3D)plot).setDarkerSides(false);
//		JFreeChart chart3d = createChart(plot);
//		charts.add(chart3d);
		PiePlot plot = new PiePlot(data);
		JFreeChart chart2d = createChart(plot);
		charts.add(chart2d);
//		ChartPanel chart3dPanel = new ChartPanel(chart3d);
		ChartPanel chart2dPanel = new ChartPanel(chart2d);
//		JTabbedPane tabs = new JTabbedPane();
//		tabs.add("2d", chart2dPanel);
//		tabs.add("3d", chart3dPanel);
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.DARK_GRAY);
//		panel.add(tabs);
		panel.add(chart2dPanel);
	}

	private JFreeChart createChart(PiePlot plot) {
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.75f);
		plot.setNoDataMessage("Waiting for data from the server...");
		plot.setSimpleLabels(true);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
		plot.setCircular(false);
		JFreeChart chart = new JFreeChart("Users distribution",
				JFreeChart.DEFAULT_TITLE_FONT, plot, false);
		ChartUtilities.applyCurrentTheme(chart);
		return chart;
	}

	private static DefaultPieDataset createDataset() {
		return new DefaultPieDataset();
	}

	public static JPanel createChartPanel() {
		ConnectionsDistribution monitor = new ConnectionsDistribution();
		return monitor.getPanel();
	}

	public JPanel getPanel() {
		return panel;
	}

	public List<JFreeChart> getCharts() {
		return charts;
	}

	public void setValue(String key, double val) {
		data.setValue(key, val);
		long total = 0;
		for (int i = 0; i < data.getItemCount(); i++) {
			total += data.getValue(i).longValue();
		}
		for (JFreeChart jFreeChart : charts) {
			jFreeChart.setTitle("Users distribution, Total: " + total);
		}
	}

	public void setColor(String key, Paint color) {
		for (JFreeChart chart : charts) {
			PiePlot plot = (PiePlot)chart.getPlot();
			plot.setSectionPaint(key, color);
		}
	}

	@Override
	public void update(String id, StatisticsProviderMBean servBean) {
		setValue(id, servBean.getConnectionsNumber());
	}

	static class ChartTest extends ApplicationFrame {

		public ChartTest() {
			super("Users distribution");
			JPanel chartPanel = createChartPanel();
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
			setContentPane(chartPanel);
		}

	}

	public static void main(String[] args) {

		ChartTest demo = new ChartTest();
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}