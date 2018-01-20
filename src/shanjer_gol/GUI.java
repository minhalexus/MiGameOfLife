/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shanjer_gol;

/**
 *
 * @author Minhal
 */
import java.awt.*;
import static java.awt.Component.*;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class GUI implements ActionListener {

    JFrame frame;
    JPanel contentPane;
    JButton startButton;
    JButton resetButton;

    final static int rows = 40;
    final static int cols = 40;
    static JButton[][] dish;
    
    //Board is the console version of the new generation, while dish is the GUI implementation of the console (board) version.
    static Life board;
    static String[] prevBoard;
    static String[] oldBoard;

    public GUI() {
        //sets up the dish and the board
        board = new Life(rows,cols);
        dish = new JButton[rows][cols];
        prevBoard = new String[cols];
        
        //sets up the standard frame, we are using the grid layout
        frame = new JFrame("Shanjer Game Of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Create a content pane */
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0, cols-2, 1, 1));

        // Creates the set number of buttons specified by the length and width, with standard adjustments such as size and color
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                dish[i][j] = new JButton("");
                dish[i][j].setPreferredSize(new Dimension(17, 17));
                dish[i][j].setActionCommand("Inactive");
                dish[i][j].setBackground(Color.darkGray);
                dish[i][j].addActionListener(this);
                contentPane.add(dish[i][j]);
            }
        }
        //just some filler labels to center the start and reset buttons
        for (int i = 0; i < ((int) cols / 2) - 2; i++) {
            contentPane.add(new JLabel());
        }

        //Start button initiates the patterns made each generation
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(17, 17));
        startButton.setActionCommand("Start");
        startButton.addActionListener(this);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.setBackground(Color.green);
        //startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        contentPane.add(startButton);
        
        //Add Reset Button (Red in color) to reset the board when pressed.
        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(17, 17));
        resetButton.setActionCommand("Reset");
        resetButton.addActionListener(this);
        resetButton.setBackground(Color.red);
        contentPane.add(resetButton);
        
        /* Add content pane to frame */
        frame.setContentPane(contentPane);
        /* Size and then display the frame. */
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * Handles three situations:
     * Start button (green) initiates an infinite loop of generations, each displayed at an interval of 100 milliseconds
     * Reset button (red) refreshes the board to go back to it's initial state so the user can re-enter new input
     * Lastly, if the user clicks any button, it turns it active (cyan) or inactive(grey) opposite of its previous state.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();
        if (eventName.equals("Start")) {
            for (int i = 1; i < rows-1; i++) {
                for (int j = 1; j < cols-1; j++) {
                    String cellStatus = dish[i][j].getActionCommand();
                    if (cellStatus.equals("Active")) {
                        board.grid[i][j] = 1;
                    } else {
                        board.grid[i][j] = 0;
                    }
                }
            }
            //print(board);
            //oldBoard = board;
            //prevBoard = board;
            //System.out.println(board);
            board.takeStep();

            try{
                while (true) {
                int delay = 100; //milliseconds
                ActionListener taskPerformer;
                taskPerformer = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < cols; j++) {
                                try{
                                    int status = board.grid[i][j];
                                    //System.out.println(status);
                                    
                                    if (status == 1) {
                                        dish[i][j].setBackground(Color.cyan);
                                        dish[i][j].setActionCommand("Active");
                                    } else {
                                        dish[i][j].setBackground(Color.darkGray);
                                        dish[i][j].setActionCommand("Inactive");
                                    }
                                }catch(NullPointerException e){}
                            }
                        }
                        //oldBoard = prevBoard;
                        
                        try{
                            board.takeStep();
                        }catch(NullPointerException e){}
                    }
                };
                new javax.swing.Timer(delay, taskPerformer).start();
                if (sameAs(oldBoard, prevBoard)) {
                    break;
                }
            }
            }catch(NullPointerException e){}
        }else if (eventName.equals("Reset")){
            System.exit(0);
        }else{
            if (eventName.equals("Inactive")) {
                JButton button = (JButton) event.getSource();
                //label.setText(" ");
                //button.setText("Yellow");
                button.setBackground(Color.cyan);
                button.setActionCommand("Active");
            } else {
                JButton button = (JButton) event.getSource();
                //button.setText("Red");
                button.setBackground(Color.darkGray);
                button.setActionCommand("Inactive");
            }
        }
    }

    /**
     * Create and show the GUI.
     */
    public static void runGUI() {
            JFrame.setDefaultLookAndFeelDecorated(true);
            GUI greeting = new GUI();
        }

    /**
     * Method that checks if two console version of the boards are the same.
     * @param a
     * @param b
     * @return 
     */
    public static boolean sameAs(String[] a, String[] b) {
        boolean isSame = true;
        outerloop:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String one = a[i].substring(j, j + 1);
                String two = b[i].substring(j, j + 1);
                if (!one.equals(two)) {
                    isSame = false;
                    break outerloop;
                }
            }
        }
        return isSame;
    }

    public static void main(String[] args) {
        /* Methods that create and show a GUI should be 
       run from an event-dispatching thread */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });

    }
}
