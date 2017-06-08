import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Copy extends JFrame implements ActionListener {

    GridBagConstraints gbc = new GridBagConstraints();
    JTextField from = new JTextField(20);
    JTextField to = new JTextField(20);
    JButton copyButton = new JButton("Copy");
    JButton cancelButton = new JButton("Cancel");
    JLabel fromLabel = new JLabel("From");
    JLabel toLabel = new JLabel("To");
    JProgressBar jProgressBar = new JProgressBar();
    Progress progress;

    public Copy() {

        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,200);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        JPanel jProgressBarPanel = new JPanel();
        jProgressBarPanel.add(jProgressBar);
        jProgressBar.setStringPainted(true);
        jProgressBar.setVisible(false);
        jProgressBar.setPreferredSize(new Dimension(260,30));
        jProgressBarPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(jPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(jProgressBarPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(buttonPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        fromLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jPanel.add(fromLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        jPanel.add(from, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        toLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jPanel.add(toLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        jPanel.add(to, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(copyButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(cancelButton);

        copyButton.addActionListener(this);
        copyButton.setActionCommand("copy");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("copy")){
            if(!from.getText().equals("") && !to.getText().equals("")){
                copyButton.setEnabled(false);
                from.setEnabled(false);
                to.setEnabled(false);
                cancelButton.setEnabled(true);
                jProgressBar.setVisible(true);
                progress = new Progress(new File(from.getText()), new File(to.getText()), jProgressBar);
                progress.execute();
            }
        } else if (e.getActionCommand().equals("cancel")) {
            progress.cancel(true);
            copyButton.setEnabled(true);
            from.setEnabled(true);
            to.setEnabled(true);
            jProgressBar.setVisible(false);
            cancelButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Copy().setVisible(true);
            }
        });
    }
}
