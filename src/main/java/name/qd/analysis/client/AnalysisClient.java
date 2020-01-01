package name.qd.analysis.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalysisClient {
	private Logger log;
	private static String LOG_CONF_PATH = "./config/log4j2.xml";
	private JFrame frame;
	private JPanel panelAnalysis;
	private JButton btnTech;
	private JButton btnChip;
	private JPanel techPanel;
	private JPanel chipPanel;
	
	private AnalysisClient() {
		initSysProp();
		initUI();
		initFrame();
		setButtonListener();
	}
	
	private void initSysProp() {
		Properties prop = System.getProperties();
		prop.setProperty("log4j.configurationFile", LOG_CONF_PATH);
		log = LoggerFactory.getLogger(AnalysisClient.class);
	}
	
	private void initUI() {
		frame = new JFrame("Analyzer");
		panelAnalysis = new JPanel();
		btnTech = new JButton("Tech Analysis");
		btnChip = new JButton("Chip Analysis");
		techPanel = new TechPanel();
		chipPanel = new ChipPanel();
	}
	
	private void initFrame() {
		frame.setSize(1200, 768);
//		frame.setMinimumSize(new Dimension(1024, 768));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.getContentPane().setLayout(new BorderLayout());
		
		panelAnalysis.setLayout(new FlowLayout());
		panelAnalysis.add(btnTech);
		panelAnalysis.add(btnChip);
		
		frame.add(panelAnalysis, BorderLayout.NORTH);
	}
	
	private void setButtonListener() {
		btnTech.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Click tech panel.");
				frame.remove(chipPanel);
				frame.add(techPanel, BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		btnChip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Click chip panel.");
				frame.remove(techPanel);
				frame.add(chipPanel, BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		new AnalysisClient();
	}
}
