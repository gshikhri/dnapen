import javax.swing.*;
import java.awt.*;

public class OptionPane {
    public OptionPane(){
        JFrame optionPane=new JFrame();
        optionPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionPane.setVisible(true);
        optionPane.setSize(400,200);

        JPanel mainPanel=new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.insets=new Insets(5,5,5,5);

        JLabel gcContentJLabel=new JLabel("GC Content (range in %)");
        JCheckBox highCentralEnergyJCheckBox=new JCheckBox("High Free Energy");
        highCentralEnergyJCheckBox.setSelected(false);
        JLabel freeEnergyRangeJLabel=new JLabel("Free Energy Range");

        JTextField minGCContentTextField=new JTextField(5);
        JTextField maxGCContentTextField=new JTextField(5);
        JTextField minFreeEnergyTextField=new JTextField(5);
        JTextField maxFreeEnergyTextField=new JTextField(5);

        JButton okButton=new JButton("OK");
        JButton cancelButton=new JButton("Cancel");

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        mainPanel.add(gcContentJLabel,gridBagConstraints);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;
        mainPanel.add(minGCContentTextField,gridBagConstraints);
        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=0;
        mainPanel.add(maxGCContentTextField,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        mainPanel.add(highCentralEnergyJCheckBox,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        mainPanel.add(freeEnergyRangeJLabel,gridBagConstraints);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=2;
        mainPanel.add(minFreeEnergyTextField,gridBagConstraints);
        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=2;
        mainPanel.add(maxFreeEnergyTextField,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=3;
        mainPanel.add(okButton,gridBagConstraints);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=3;
        mainPanel.add(cancelButton,gridBagConstraints);

        optionPane.add(mainPanel,BorderLayout.CENTER);
        optionPane.setVisible(true);

    }
}
