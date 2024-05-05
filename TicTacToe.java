import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; //50 for the text pannel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX ="X";
    String playerO ="O";
    String currentPlayer = playerX;

    int scorex= 0;
    int scoreO=0;


    boolean finish  = false;
    int turns =0;


    TicTacToe() {
         frame.setVisible(true);
         frame.setSize(boardWidth,boardHeight);
         frame.setLocationRelativeTo(null);
         frame.setResizable(false);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLayout(new BorderLayout());


         textLabel.setBackground(Color.blue);
         textLabel.setForeground(Color.white);
         textLabel.setFont(new Font("Arial",Font.BOLD,50));
         textLabel.setHorizontalAlignment(JLabel.CENTER);
         textLabel.setText("TIC-TAC-TOE");
         textLabel.setOpaque(true);



         textPanel.setLayout(new BorderLayout());
         textPanel.add(textLabel);
         frame.add(textPanel, BorderLayout.NORTH);



         boardPanel.setLayout(new GridLayout(3,3));
         boardPanel.setBackground(Color.blue);
         frame.add(boardPanel);


         buttPanel.setLayout(new GridLayout(1,2));

            JButton resetButton=new JButton(" RESET ");
            resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                resetGame();
                }
            });


                JButton exiButton=new JButton("EXIT");
                exiButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                });


                buttPanel.add(resetButton);
                buttPanel.add(exiButton);
                frame.add(buttPanel,BorderLayout.SOUTH);

         for(int r=0; r < 3;r++)
         {
            for(int c=0; c <3;c++)
            {
                JButton tile = new JButton();
                board[r][c] =tile;
                boardPanel.add(tile);


                tile.setBackground(Color.BLUE);
                tile.setForeground(Color.black);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                tile.setBorder(new LineBorder(new Color(12,116,117),3));
                //tile.setText(currentplayer);


                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(finish) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText()==""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!finish) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer +" 's turn.");

                            }

                            

                        }
                        

                    }
                });


            }
         }



    }


    void checkWinner(){
        //horizontal
        for(int r=0;r<3;r++){
            if (board[r][0].getText()=="") continue;

            if(board[r][0].getText()== board[r][1].getText()&&
               board[r][1].getText()== board[r][2].getText()){
               for(int i=0;i<3;i++){
                   setWinner(board[r][i]);
               }

               finish =true;
               return;
               }

        }
        //vertical
        for(int c =0;c < 3;c++){
            if(board[0][c].getText()== "") continue;

            if(board[0][c].getText()==board[1][c].getText() &&
               board[1][c].getText()==board[2][c].getText()){
                for(int i=0;i<3;i++){
                    setWinner(board[i][c]);
                }
                finish=true;
                return;

               }
        }


        //digonally
        if(board[0][0].getText()==board[1][1].getText() &&
           board[1][1].getText()==board[2][2].getText() &&
           board[0][0].getText()!= ""){
           for(int i=0;i<3;i++){
                setWinner(board[i][i]);
           }
           finish =true;
           return;
           }


           //antidiagonaly
           if(board[0][2].getText()==board[1][1].getText() &&
              board[1][1].getText()==board[2][0].getText() &&
              board[0][2].getText()!=""){
              setWinner(board[0][2]);
              setWinner(board[1][1]);
              setWinner(board[2][0]);
              finish=true;
              return;


            }

            if(turns ==9){
                for(int r=0;r<3;r++){
                for(int c=0;c<3;c++){
                    setTie(board[r][c]);
                }
                }
                finish =true;
            
            }
    }
    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.CYAN);
        textLabel.setText( currentPlayer +" is the   WINNER! ");
    }

    void setTie(JButton tile){
        tile.setForeground(Color.yellow);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
    void resetGame(){
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                board[r][c].setText("");
                board[r][c].setBackground(Color.BLUE);
                board[r][c].setForeground(Color.BLACK);
            }
        }
        currentPlayer =playerX;
        finish=false;
        turns=0;
        textLabel.setText("TIC-TAC-TOE");
    }
}
