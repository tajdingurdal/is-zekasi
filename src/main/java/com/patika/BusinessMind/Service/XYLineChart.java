package com.patika.BusinessMind.Service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patika.BusinessMind.Model.PolicyReportData;
import com.patika.BusinessMind.Repository.PolicyReportDataRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class XYLineChart {

	private static PolicyReportDataRepository policyReportDataRepository;

	@Autowired
	public XYLineChart(PolicyReportDataRepository policyReportDataRepository) {

		this.policyReportDataRepository = policyReportDataRepository;
	}

	public static void createChartPanel() {
		// creates a line chart object
		// returns the chart panel
		String chartTitle = "Data graph for the past period";
		String xAxisLabel = "End Year";
		String yAxisLabel = "Endorsement && Total Profit";

		XYDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, false, false);

		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
 
		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.DARK_GRAY);

		String filePath = "C:/Users/TAJDIN/Documents/workspace-spring-tool-suite-4-4.14.1.RELEASE/BusinessMind/BusinessMind/src/main/resources/chart.png";
		File imageFile = new File(filePath);
		int width = 640;
		int height = 480;

		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public static XYDataset createDataset() {
		List<PolicyReportData> policyReportData = getAllFinancialData();
		// creates an XY dataset...
		// returns the dataset
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Endorsement");
		XYSeries series2 = new XYSeries("Total Profit");

		for (PolicyReportData pr : policyReportData) {

			series1.add(pr.getEndYear(), pr.getEndorsement());
			series2.add(pr.getEndYear(), pr.getTotalProfit());
		}

		dataset.addSeries(series1);
		dataset.addSeries(series2);

		return dataset;
	}

	public static List<PolicyReportData> getAllFinancialData() {
		Iterable<PolicyReportData> allFinancialData = policyReportDataRepository.findAll();

		List<PolicyReportData> list = new ArrayList<>();

		for (PolicyReportData financialData : allFinancialData) {
			list.add(financialData);
		}
		return list;
	}

}
