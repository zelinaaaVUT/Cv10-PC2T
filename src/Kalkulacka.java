import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Kalkulacka extends JFrame implements ActionListener {
    String vysledek;
    static JTextField text;
    static JFrame okno;
    public Kalkulacka(){
        super();
        vysledek = "";
    }
    public static void main(String[] args) {
        Kalkulacka kalkulacka = new Kalkulacka();
        okno = new JFrame("Kalkulačka");
        JPanel panel = new JPanel(new GridBagLayout());
        okno.setSize(300,200);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints constraints = new GridBagConstraints();

        //tlacitka
        text = new JTextField(16);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        panel.add(text, constraints);
        int x = 0, y = 1;
        for (int i = 0; i < 10; i++){
            if (i % 4 == 0 && i != 0){
                x = 0;
                y++;
            }
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(kalkulacka);
            constraints.gridx = x;
            constraints.gridy = y;
            constraints.gridwidth = 1;
            constraints.weightx = 0.5;
            constraints.weighty = 0.5;
            constraints.fill = GridBagConstraints.BOTH;
            panel.add(button, constraints);
            x++;
        }

        JButton bplus = new JButton("+");
        JButton bminus = new JButton("-");
        JButton bequals = new JButton("=");
        bplus.addActionListener(kalkulacka);
        bminus.addActionListener(kalkulacka);
        bequals.addActionListener(kalkulacka);
        bequals.setPreferredSize(new Dimension(16, bequals.getPreferredSize().height));

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(bplus, constraints);
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(bminus, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 4;
        panel.add(bequals, constraints);

        okno.add(panel);
        okno.pack();
        okno.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());
        String nakliknuto = e.getActionCommand();

        if (nakliknuto.charAt(0) <= '9' && nakliknuto.charAt(0) >= 0){
            System.out.println(nakliknuto);
            vysledek = vysledek + nakliknuto;
            text.setText(vysledek);
        } else if (nakliknuto.charAt(0) == '+') {
            vysledek = vysledek + '+';
            text.setText(vysledek);
        } else if (nakliknuto.charAt(0) == '-') {
            vysledek = vysledek + '-';
            text.setText(vysledek);
        } else if (nakliknuto.charAt(0) == '=') {
            text.setText("");

            String[] cisla = vysledek.split("(?=[-+])");
            Integer test = Integer.parseInt(cisla[0]);
            vysledek = cisla[0];

            for (int i = 0; i < cisla.length; i++){
                char operator = cisla[i].charAt(0);
                int operand = Integer.parseInt(cisla[i].substring(1));

                if (operator == '+'){
                    test += operand;
                } else if (operator == '-') {
                    test -= operand;
                }
            }
            System.out.println(test);
            vysledek = Integer.toString(test);

            text.setText(vysledek);
        }
    }
}
