package com.patika.BusinessMind.ConsoleResponseHandler;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.data.statistics.StatisticalCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.patika.BusinessMind.Model.PolicyReportData;
import com.patika.BusinessMind.Service.XYLineChart;
import com.patika.BusinessMind.Service.abstracts.IPolicyReportDataService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class TechnicalRequirements {

	public static Scanner scanner = new Scanner(System.in);

	@Autowired
	private IPolicyReportDataService policyReportDataService;

	public void startRequirements() {

		while (true) {

			System.out.println("\n\t1. Geçmiş dönemlere ait finansal verilere ulaş"
					+ "\n\t2. Geçmiş döneme ilişkin verileri kullanarak tablo görünümünde rapor oluştur"
					+ "\n\t3. Geçmiş döneme ilişkin verileri birbirleriyle karşılaştır"
					+ "\n\t4. Geçmiş döneme ait verileri grafik üzerinde gör\n\t" + "5. Çıkış");

			int answer = scanner.nextInt();
			scanner.nextLine();
			if (answer == 5) {
				System.exit(0);
			}
			switch (answer) {
			case 1: {
				accessFinancialDataforPreviousPeriods();
				break;
			}
			case 2: {
				createReportUsingDataFromPastPeriods();
				break;
			}
			case 3: {
				compareHistoricalDataWithEachOther();
				break;
			}
			case 4: {
				showDataForPastPeriodOnChart();
				break;
			}
			default:
				System.out.println("Unexpected value: " + answer);
				startRequirements();
			}
		}
	}


	private void createStatisticalBarChart() { // Geçmiş döneme ait verileri içeren istatiksel grafik

		final StatisticalCategoryDataset dataset = createDataset();

		final CategoryAxis xAxis = new CategoryAxis("Year");
		xAxis.setLowerMargin(0.01d); // percentage of space before first bar
		xAxis.setUpperMargin(0.01d); // percentage of space after last bar
		xAxis.setCategoryMargin(0.05d); // percentage of space between categories
		final ValueAxis yAxis = new NumberAxis("Total Endorsement & Profit");

		// define the plot
		final CategoryItemRenderer renderer = new StatisticalBarRenderer();
		final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

		final JFreeChart chart = new JFreeChart("Statistical Bar Chart", new Font("Helvetica", Font.BOLD, 14), plot,
				true);
		// chart.setBackgroundPaint(Color.white);
		// add the chart to a panel...

		String filePath = "C:/Users/TAJDIN/Documents/workspace-spring-tool-suite-4-4.14.1.RELEASE/BusinessMind/BusinessMind/src/main/resources/TotalEndorsementAndProfitofAllAgencies.png";
		File imageFile = new File(filePath);
		int width = 640;
		int height = 480;

		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		System.out.println(
				"Sütun Grafik TotalEndorsementAndProfitofAllAgencies.png src/main/resources dizininde oluşturuldu");

	}

	private StatisticalCategoryDataset createDataset() {
		final DefaultStatisticalCategoryDataset result = new DefaultStatisticalCategoryDataset();

		List<PolicyReportData> financialData = getAllFinancialData();
		int totalEndorsement = 0;
		int totalProfit = 0;

		for (PolicyReportData pr : financialData) {
			totalEndorsement += pr.getEndorsement();
			totalProfit += pr.getTotalProfit();
		}
		
		// result.add(y, x, "Series 1", "Type 1");
		result.add(totalEndorsement, 17.9, "Endorsement", "2022");

		result.add(totalProfit, 7.9, "Total Profit", "2022");

		return result;
	}

	private void createPieChart() { // Geçmiş döneme ait verileri içeren pasta grafik
		List<PolicyReportData> financialData = getAllFinancialData();
		int totalEndorsement = 0;
		int totalProfit = 0;

		for (PolicyReportData pr : financialData) {
			totalEndorsement += pr.getEndorsement();
			totalProfit += pr.getTotalProfit();
		}

		String filePath = "C:/Users/TAJDIN/Documents/workspace-spring-tool-suite-4-4.14.1.RELEASE/BusinessMind/BusinessMind/src/main/resources/TotalEndorsementAndProfitofAllAgencies.jpeg";

		// pie chart
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Endorsement", 10);
		pieDataset.setValue("Total Profit", 20);

		// add value to dataset
		JFreeChart chart = ChartFactory.createPieChart("Total Endorsement and Profit of All Agencies", // Title
				pieDataset, // dataset
				true, // legend
				true, // tooltip
				false // use to generate URLs?
		);

		// generate the chart
		try {

			ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 500, 300);

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

		System.out.println(
				"Pasta Grafik TotalEndorsementAndProfitofAllAgencies.jpeg src/main/resources dizininde oluşturuldu\n");
	}

	public void showDataForPastPeriodOnChart() { // Geçmiş döneme ait verileri grafik üzerinde gör

		XYLineChart.createChartPanel();

		System.out.println("Çizgi grafik chart.png src/main/resources dizininde oluşturuldu\n");
		
		createPieChart();

		createStatisticalBarChart();

	}

	public void compareHistoricalDataWithEachOther() { // Geçmiş döneme ilişkin verileri birbirleriyle karşılaştır
		List<Long> lst = new ArrayList<>();

		System.out.print("Number of data you want to compare: ");
		int numberOfData = scanner.nextInt();

		System.out.println("Enter the data ID numbers to compare: ");

		while (numberOfData > 0) {
			long id = scanner.nextInt();
			lst.add(id);
			numberOfData--;
		}
		List<PolicyReportData> policyReportData = new ArrayList<>();

		for (int i = 0; i < lst.size(); i++) {
			policyReportDataService.findById(lst.get(i)).ifPresent(policyReportData::add);
		}

		System.out.println(
				"| ID  | Endorsement   | Manager First Name | Manager Last Name | Agency Name|  Total Profit | Start Year    | End Year | Customer ID |");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < policyReportData.size(); i++) {
			System.out.printf("| %-3s | %-14s| %-17s  | %-18s| %-11s| %-13s | %-13s | %-9s| %-12s|\n",
					policyReportData.get(i).getId(), policyReportData.get(i).getEndorsement(),
					policyReportData.get(i).getInsuranceAgency().getManagerFirstName(),
					policyReportData.get(i).getInsuranceAgency().getManagerLastName(),
					policyReportData.get(i).getInsuranceAgency().getAgencyName(),
					policyReportData.get(i).getTotalProfit(), policyReportData.get(i).getStartYear(),
					policyReportData.get(i).getEndYear(), policyReportData.get(i).getCustomer().getId());

		}

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		int highestProfit = 0;
		long biggerProfitDataID = 0;
		for (int i = 0; i < policyReportData.size(); i++) {

			if (policyReportData.get(i).getTotalProfit() > highestProfit) {
				highestProfit = policyReportData.get(i).getTotalProfit();
				biggerProfitDataID = policyReportData.get(i).getId();
			}
		}
		int highestEndorsement = 0;
		long highestEndorsementID = 0;

		for (int i = 0; i < policyReportData.size(); i++) {

			if (policyReportData.get(i).getEndorsement() > highestEndorsement) {
				highestEndorsement = policyReportData.get(i).getEndorsement();
				highestEndorsementID = policyReportData.get(i).getId();
			}
		}

		System.out.println("Highest profit: " + highestProfit + " $ id table number " + biggerProfitDataID);
		System.out.println("Highest Endorsement: " + highestEndorsement + " $ id table number " + highestEndorsementID);

	}

	public void accessFinancialDataforPreviousPeriods() { // Geçmiş dönemlere ait finansal verilere ulaş

		List<PolicyReportData> policyReportData = getAllFinancialData();

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println(
				"| ID  | Endorsement   | Manager First Name | Manager Last Name | Agency Name|  Total Profit | Start Year    | End Year | Customer ID | Agency ID   |");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < policyReportData.size(); i++) {

			System.out.printf("| %-3s | %-14s| %-17s  | %-18s| %-11s| %-13s | %-13s | %-9s| %-12s| %-12s|\n",
					policyReportData.get(i).getId(), policyReportData.get(i).getEndorsement(),
					policyReportData.get(i).getInsuranceAgency().getManagerFirstName(),
					policyReportData.get(i).getInsuranceAgency().getManagerLastName(),
					policyReportData.get(i).getInsuranceAgency().getAgencyName(),
					policyReportData.get(i).getTotalProfit(), policyReportData.get(i).getStartYear(),
					policyReportData.get(i).getEndYear(), policyReportData.get(i).getCustomer().getId(),
					policyReportData.get(i).getInsuranceAgency().getId());

		}

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

	}

	public void createReportUsingDataFromPastPeriods() { // Geçmiş döneme ilişkin verileri kullanarak tablo görünümünde rapor oluştur

		System.out.println("1. Generating Reports by Agent ID\n2. Generating Reports by Customer ID");
		int answer = scanner.nextInt();
		scanner.nextLine();

		switch (answer) {
		case 1: {
			generatingReportsbyAgentID();
			break;
		}
		case 2: {
			generatingReportsbyCustomerID();
			break;
		}
		default:
			System.out.println("Unexpected value: " + answer);
			createReportUsingDataFromPastPeriods();
		}

	}

	private void generatingReportsbyAgentID() {
		List<PolicyReportData> policyReportData = getAllFinancialData();

		System.out.print("Tablo oluşturmak istediğiniz Acente ID numarası: ");
		int agentId = scanner.nextInt();
		scanner.nextLine();

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println(
				"| ID  | Endorsement   | Manager First Name | Manager Last Name | Agency Name|  Total Profit | Start Year    | End Year | Customer ID | Agency ID   |");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < policyReportData.size(); i++) {
			if (policyReportData.get(i).getId() == agentId) {

				System.out.printf("| %-3s | %-14s| %-17s  | %-18s| %-11s| %-13s | %-13s | %-9s| %-12s| %-12s|\n",
						policyReportData.get(i).getId(), policyReportData.get(i).getEndorsement(),
						policyReportData.get(i).getInsuranceAgency().getManagerFirstName(),
						policyReportData.get(i).getInsuranceAgency().getManagerLastName(),
						policyReportData.get(i).getInsuranceAgency().getAgencyName(),
						policyReportData.get(i).getTotalProfit(), policyReportData.get(i).getStartYear(),
						policyReportData.get(i).getEndYear(), policyReportData.get(i).getCustomer().getId(),
						policyReportData.get(i).getInsuranceAgency().getId());

			}
		}

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

	}

	private void generatingReportsbyCustomerID() {
		List<PolicyReportData> policyReportData = getAllFinancialData();

		System.out.print("Tablo oluşturmak istediğiniz Customer ID numarası: ");
		int customerId = scanner.nextInt();
		scanner.nextLine();

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println(
				"| ID  | Endorsement   | Manager First Name | Manager Last Name | Agency Name|  Total Profit | Start Year    | End Year | Customer ID |");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < policyReportData.size(); i++) {
			if (policyReportData.get(i).getId() == customerId) {

				System.out.printf("| %-3s | %-14s| %-17s  | %-18s| %-11s| %-13s | %-13s | %-9s| %-12s|\n",
						policyReportData.get(i).getId(), policyReportData.get(i).getEndorsement(),
						policyReportData.get(i).getInsuranceAgency().getManagerFirstName(),
						policyReportData.get(i).getInsuranceAgency().getManagerLastName(),
						policyReportData.get(i).getInsuranceAgency().getAgencyName(),
						policyReportData.get(i).getTotalProfit(), policyReportData.get(i).getStartYear(),
						policyReportData.get(i).getEndYear(), policyReportData.get(i).getCustomer().getId());

			}
		}

		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

	}

	public List<PolicyReportData> getAllFinancialData() { // veritabanından tüm finansal verileri çektik.
		Iterable<PolicyReportData> allFinancialData = policyReportDataService.findAll();

		List<PolicyReportData> list = new ArrayList<>();

		for (PolicyReportData financialData : allFinancialData) {
			list.add(financialData);
		}
		return list;
	}

}