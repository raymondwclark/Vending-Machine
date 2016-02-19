import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Vending extends JFrame{
	final int goodyType = 4;
	final String[] goodyImageNames = {"water-bottle.jpg","soda.jpg","pretzel.jpg","chocolate-bar.jpg"};
	final String[] goodyNames = { "W", "S", "P", "C" };
	final String[] goodyTips = {"Water Bottle","Soda","Pretzel","Chocalate Bar"};
	final double[] price = {1.25, 1.50, 1.75, 2.00};

	class PriceCheckListener implements ActionListener{
		String goodyName;
		double price;
		JTextField inputTF;
		JTextField outputTF;
		JButton itemButton;
		
		public PriceCheckListener(JTextField inputTF, double price, JTextField outputTF, JButton itemButton, String goodyName){
			this.goodyName = goodyName;
			this.inputTF = inputTF;
			this.price = price;
			this.outputTF = outputTF;
			this.itemButton = itemButton;
		}

		public void actionPerformed(ActionEvent e){
			String dAmount = inputTF.getText();
			double dollars = Double.parseDouble(dAmount);

			if(dollars >= price){
				if((dollars-price) > 0){
					double a = dollars - price;
					outputTF.setText("Your change is $" + Double.toString(a));
					itemButton.setText("Here is your " + goodyName);
				}
				else{
					outputTF.setText("No change for you");
					itemButton.setText("Here is your " + goodyName);
				}
			}
				else{
					outputTF.setText("Not enough" + "\nmoney for " + goodyName);
				}
			}
		}
	
	class GoodyListener implements ActionListener{
		double price;
		JTextField priceTF;

		public GoodyListener(JTextField pTF, double price){
			this.priceTF = pTF;
			this.price = price;
		}
		public void actionPerformed(ActionEvent e){
			priceTF.setText(Double.toString(price));
		}
	}

	public Vending(String s) {
		super(s);
				
		// Image of a dollar bill
		int dollarImgWidth = 160, dollarImgHeight = 70;
		ImageIcon dImage1 = new ImageIcon("one-dollar.jpg");
		ImageIcon dImage2 = new ImageIcon(dImage1.getImage().getScaledInstance((int)dollarImgWidth,(int)dollarImgHeight, java.awt.Image.SCALE_SMOOTH));			
		JButton oneDollarBotton = new JButton(dImage2);
		
		// The panel of price
		JPanel pPrice = new JPanel();
		pPrice.setLayout(new BoxLayout(pPrice, BoxLayout.Y_AXIS));
		JPanel pPriceText = new JPanel();
		pPriceText.setLayout(new FlowLayout());
		JLabel priceTextLabel = new JLabel("Price");
		JTextField amountTF = new JTextField("$1.25");
		pPriceText.add(priceTextLabel);
		pPriceText.add(amountTF);
		JTextField enterMoneyTF = new JTextField("Enter Money Here ..");
		pPrice.add(pPriceText);
		pPrice.add(enterMoneyTF);
		
		// The "choose one" panel
		JPanel pChooseOne = new JPanel();
		pChooseOne.setLayout(new GridLayout(4,1));
		pChooseOne.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton[] goodyTextButtons = new JButton[goodyType];
		for (int i = 0; i < goodyType; i++){
			goodyTextButtons[i] = new JButton(goodyNames[i]);
			goodyTextButtons[i].setToolTipText(goodyTips[i]);
			pChooseOne.add(goodyTextButtons[i]);
		}
	
		pChooseOne.setBorder(new TitledBorder("Choose one"));
		
		// The panel of "return amount"
		JTextField returnAmountTF = new JTextField("Return amount ..");
		
		// Add the above four panels together
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setPreferredSize(new Dimension (200, 400));
		p1.add(oneDollarBotton);
		p1.add(pPrice);
		p1.add(pChooseOne);
		p1.add(returnAmountTF);
		
		// The panel of goodies
		JPanel pGoodies = new JPanel();
		pGoodies.setLayout(new GridLayout(4,1,0,0));
		pGoodies.setBorder(new TitledBorder("Goodies"));
		((TitledBorder) pGoodies.getBorder()).setTitleColor(Color.BLACK);
		int goodyImgWidth = 100, goodyImgHeight = 100;
		JButton[] goodyImageButtons = new JButton[goodyType];
		for (int i = 0; i < goodyImageButtons.length; i++) {
			ImageIcon image1 = new ImageIcon(goodyImageNames[i]);
			ImageIcon image2 = new ImageIcon(image1.getImage().getScaledInstance((int)goodyImgWidth,(int)goodyImgHeight,java.awt.Image.SCALE_SMOOTH));			
			goodyImageButtons[i] = new JButton(image2);
			goodyImageButtons[i].setContentAreaFilled(false);
			goodyImageButtons[i].setBorderPainted(true);
			pGoodies.add(goodyImageButtons[i]);
		}

		// The panel of "where you get your item"
		JButton whereButton = new JButton("This is where you get your item!");
		whereButton.setPreferredSize(new Dimension(250, 100));
		
		// Add everything onto the frame
		this.add(pGoodies, BorderLayout.CENTER);
		this.add(p1, BorderLayout.EAST);
		this.add(whereButton, BorderLayout.SOUTH);
		

		for(int i = 0; i < goodyImageButtons.length; i++){
			goodyImageButtons[i].addActionListener(new GoodyListener(amountTF, price[i]));
		}		

		for(int i = 0; i < goodyType; i++){
			goodyTextButtons[i].addActionListener(new PriceCheckListener(enterMoneyTF, price[i], returnAmountTF, whereButton, goodyTips[i]));
		}
	}

	public static void main(String[] args) {
		Vending v = new Vending("THE FRONT VIEW OF A VENDING MACHINE");
		v.setSize(400, 800);
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
	}

}
