package dusk.programs.lcg.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dusk.programs.lcg.src.Main;
import dusk.programs.lcg.src.Main.Mode;

public class GraphicsHandler {

	static JFrame frame;
	static BufferedImage backbuffer;
	static Graphics2D g2d;

	public static JPanel graphicsPanel;
	public static JPanel menuPanel;

	public static void graphicsUpdate() {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1920, 1080);
		g2d.setColor(Color.WHITE);

		switch (Main.mode) {
		case Menu:
			//There ARE no graphics you FOOL
			break;
		case Game:
			doGameGraphics();
			break;
		}
		
		frame.repaint();
	}
	
	public static void doGameGraphics() {
		//TODO Game graphics
	}

	@SuppressWarnings("unused")
	public static void init() {
		backbuffer = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();

		frame = new JFrame("Lovecraft Game");

		JLayeredPane jlp = frame.getLayeredPane();

		// Setup Graphics panel, with paint method
		graphicsPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				g.drawImage(backbuffer, 0, 0, graphicsPanel.getWidth(), graphicsPanel.getHeight(), graphicsPanel);
			}
		};
		graphicsPanel.setLocation(0, 0);

		// Setup menuPanel for putting buttons on overlaid on graphics
		menuPanel = new JPanel();
		menuPanel.setLocation(0, 0);
		menuPanel.setBackground(new Color(0, 0, 0, 0)); // Transparent
		menuPanel.setLayout(null); // Menupanel doesn't shuffle around its internal components here, it's done on
									// frame resize.

		// MainMenu
		JPanel mainMenuContainer = new JPanel();
		doMainMenu: {
			mainMenuContainer.setPreferredSize(new Dimension(300, 300));
			mainMenuContainer.setBackground(new Color(0, 0, 0, 0));
			mainMenuContainer.setLayout(new LayoutManagerStrictSizes());
			JLabel mainMenuText = new JLabel("Main Menu");
			mainMenuText.setPreferredSize(new Dimension(300, 100));
			mainMenuText.setLayout(new LayoutManagerStrictSizes());
			mainMenuText.setLocation(0, 0);
			mainMenuText.setForeground(Color.WHITE);
			mainMenuText.setHorizontalAlignment(SwingConstants.CENTER);
			mainMenuText.setFont(mainMenuText.getFont().deriveFont(Font.BOLD, 30));// big, bold
			mainMenuContainer.add(mainMenuText);
			menuPanel.add(mainMenuContainer);
			JButton mainMenuJoin = new JButton("Join Server");
			mainMenuJoin.setPreferredSize(new Dimension(300, 100));
			mainMenuJoin.setLayout(new LayoutManagerStrictSizes());
			mainMenuJoin.setLocation(0, 100);
			mainMenuJoin.setFocusable(false);
			mainMenuJoin.setHorizontalAlignment(SwingConstants.CENTER);
			mainMenuJoin.setFont(mainMenuJoin.getFont().deriveFont(Font.BOLD, 30));// big, bold
			mainMenuContainer.add(mainMenuJoin);
			mainMenuJoin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// bottomBarContainer.setVisible(true);
					// topBarContainer.setVisible(true);
					// chatContainer.setVisible(true);
					mainMenuContainer.setVisible(false);
					Main.mode = Mode.Game;
				}
			});
		}

		jlp.add(menuPanel);
		jlp.add(graphicsPanel);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				Main.stop();
			}

		});
		frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// Set to layered pane size (frame size is actually larger
				Dimension size = frame.getLayeredPane().getSize();
				graphicsPanel.setSize(size);
				menuPanel.setSize(size);
				// set the location for components that hug the sides
				// resourcesGUI.setLocation(0, 0);
				// topBarContainer.setLocation(0, 0);
				// bottomBarContainer.setLocation(0, size.height -
				// bottomBarContainer.getHeight());
				// chatContainer.setLocation(size.width-chatContainer.getWidth(), size.height -
				// chatContainer.getHeight());
				mainMenuContainer.setLocation((size.width - mainMenuContainer.getWidth()) / 2, (size.height - mainMenuContainer.getHeight()) / 2);
				// mapGUIPopin.setLocation((size.width - mapGUIPopin.getWidth()) / 2,
				// (size.height - mapGUIPopin.getHeight()) / 2);
				// escapeMenuContainer.setLocation((size.width - mainMenuContainer.getWidth()) / 2, (size.height - mainMenuContainer.getHeight()) / 2);

			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		frame.setSize(1920, 1080);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
