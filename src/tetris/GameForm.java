package tetris;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameForm extends JFrame 
{
    private GameForm gf = this; 
    private GameArea ga;
    public InputMap im;
    public ActionMap am; 
    public GameForm() 
    {
        initComponents();
        initControls();
    }
    
    public void startGame()
    {
        Tetris.audio.playTheme();
        ga = new GameArea( gf, placeholderPanel, 15 );
        add( ga );
        new GameThread(ga, this).start(); //GamThread.run(); means running in the same thread, so use start() in order to run in seperate thread. 
        ga.isWorking = true; 
        initControls(); 
    }
    
    public void initControls()
    {
        im = this.getRootPane().getInputMap();
        am = this.getRootPane().getActionMap();

        im.put( KeyStroke.getKeyStroke("RIGHT"), "right" );
        im.put( KeyStroke.getKeyStroke("LEFT"), "left"   );
        im.put( KeyStroke.getKeyStroke("UP"), "rotate"   );
        im.put( KeyStroke.getKeyStroke("DOWN"), "drop"   );
        
        am.put("right", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }
        });

        am.put("left", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
            }
        });

        am.put("rotate", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }
        });

        am.put("drop", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();
            }
        });
    }
    public void setScore(int score)
    {
        scoreLabel.setText("Score: " + score); 
    }
    public void setLevel(int level)
    {
        levelLabel.setText("Level: " + level);
    }
    public void noKeyBoard()
    {
        am.clear(); 
        im.clear(); 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        placeholderPanel = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        mainmenuBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris Game");
        setResizable(false);

        placeholderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout placeholderPanelLayout = new javax.swing.GroupLayout(placeholderPanel);
        placeholderPanel.setLayout(placeholderPanelLayout);
        placeholderPanelLayout.setHorizontalGroup(
            placeholderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        placeholderPanelLayout.setVerticalGroup(
            placeholderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );

        scoreLabel.setText("Score:");

        levelLabel.setText("Level:");

        mainmenuBtn.setText("Main Menu");
        mainmenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainmenuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainmenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(placeholderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreLabel)
                    .addComponent(levelLabel))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainmenuBtn)
                    .addComponent(placeholderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(scoreLabel)
                        .addGap(18, 18, 18)
                        .addComponent(levelLabel)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainmenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainmenuBtnActionPerformed
        Tetris.mf.setVisible(true);
        this.setVisible(false); 
        ga.isWorking = true; 
        initControls(); 
    }//GEN-LAST:event_mainmenuBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel levelLabel;
    private javax.swing.JButton mainmenuBtn;
    private javax.swing.JPanel placeholderPanel;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
