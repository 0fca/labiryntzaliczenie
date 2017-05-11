/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labiryntzaliczenie.gui;
 
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
/**
 *
 * @author Obsidiam
 */
public class MainWindow extends javax.swing.JFrame {
 
    private final String SOUTH = java.awt.BorderLayout.SOUTH;
    private final String NORTH = java.awt.BorderLayout.NORTH;
    private final String EAST = java.awt.BorderLayout.EAST;
    private final String WEST = java.awt.BorderLayout.WEST;
    private static HashMap<Integer, String> MAP = new HashMap<>();
    private static Stack<Integer> STACK = new Stack<>();
    private static boolean[] visited = new boolean[200];
    private ObstacleGenerator OT = new ObstacleGenerator("ObstacleGenerator");
    private int CELL_NUM,LAST_CELL;
    private int TRY = 3;
    
    private UserObject jl = new UserObject();
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        //inicjalizacja komponentów okna
        initComponents();
        //przygotowanie siatki pod mapę
        prepareMap();
        //przygotowanie labiryntu w oparciu o algorytm DFS
        prepareMaze();
        //ustawienie postaci na mapie
        setPacman();
    }
   //initializer, init obiektu MAP
    {
        MAP.put(1, NORTH);
        MAP.put(2, EAST);
        MAP.put(3, SOUTH);
        MAP.put(4, WEST);
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PakMen");
        setExtendedState(6);
        setPreferredSize(new java.awt.Dimension(900, 500));
        setSize(new java.awt.Dimension(900, 500));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(10, 20));
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("Próba:");
        jPanel2.add(jLabel2);

        jLabel1.setText(String.valueOf(TRY));
        jPanel2.add(jLabel1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        Cell last  = (Cell)jPanel1.getComponent(CELL_NUM);//dokonuje wyboru komórki w oparciu o numer indexu
        //przełączanie pomiędzy odpowiednimi polami klasy KeyEvent tak aby zmienić wartości globalnych zmiennych LAST_CELL i CELL_NUM zgodnie z kierunkiem ruchu gracza, wykonanie przeniesienia postaci.
        switch(evt.getExtendedKeyCode()){
            case KeyEvent.VK_UP:
                if(checkIfMovePossible(last,0)){
                    LAST_CELL = CELL_NUM;
                    CELL_NUM -= 20;
                    movePacman();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(checkIfMovePossible(last,1)){
                    LAST_CELL = CELL_NUM;
                    CELL_NUM += 1;
                    movePacman();
                }
                break;
            case KeyEvent.VK_LEFT:
                if(checkIfMovePossible(last,3)){
                    LAST_CELL = CELL_NUM;
                    CELL_NUM -= 1;
                    movePacman();
                }
                break;
            case KeyEvent.VK_DOWN:
                if(checkIfMovePossible(last,2)){
                    LAST_CELL = CELL_NUM;
                    CELL_NUM += 20;
                    movePacman();
                }
                break;
        }
        jPanel1.revalidate();
    }//GEN-LAST:event_formKeyPressed
 
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
    Map<Integer, Cell> cells = new HashMap<>();
   
    private void prepareMap() {
        for (int ix = 0; ix < 200; ix++) {
            //tworzę obiekt c klasy Cell
            Cell c = new Cell();
            for (int i = 1; i <= 4; i++) {
                //dodaję ścianę do komórki na każdym z jej czterech boków
                c.setWall(new Wall(), MAP.get(i));
                //jeśli ix = 199 to dodany zostaje obiekt klasy JLabel z grafiką zamku
                if(ix == 199){
                    JLabel castle = new JLabel();
                    InputStream in = getClass().getResourceAsStream("castle.png");
                    Image cas;
                    try {
                        cas = ImageIO.read(in);
                        ImageIcon im = new ImageIcon(cas);
                        castle.setIcon(im);
                        castle.setHorizontalAlignment(JLabel.CENTER);
                        c.add(castle,0);
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            //umieszczenie wszystkich komórek w mapie komórek oraz w modelu widocznym dla użyszkodnika
            cells.put(ix, c);
            jPanel1.add(c);
            
        }
    }
   
    private void prepareMaze() {
        //ujednolicenie stanów w tablicy visited.
        for(int i = 0; i < 200; i++){
            visited[i] = false;
        }
        //wezwanie metody dfs, by przygotowac labirynt
        dfs();
    }
   //przemieszczenie postaci pacmana
    private void movePacman(){
        Cell last  = (Cell)jPanel1.getComponent(LAST_CELL);
        Cell c = (Cell)jPanel1.getComponent(CELL_NUM);
        last.remove(jl);
        last.revalidate();
        last.updateUI();
        //s
        //jl.setGraphic(dir);
        if(!c.checkIfGhosted()){
            if(CELL_NUM != 199){
                c.add(jl,java.awt.BorderLayout.CENTER);
                c.revalidate();
                c.updateUI();
            }else{
                int choice = JOptionPane.showConfirmDialog(this, "Wygrałeś! Chcesz zagrać ponownie?", "Game Over", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                    resetAmcia();
                }else{
                    System.exit(0);
                }
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            int choice = JOptionPane.showConfirmDialog(this, "Game Over! Chcesz zagrać ponownie?", "Game Over", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                resetAmcia();
            }else{
                System.exit(0);
            }
        }
    }
    //ustawienie postaci na mapie
    private void setPacman(){
        CELL_NUM = 0;
        LAST_CELL = 0;
        Cell c = (Cell)jPanel1.getComponent(0);
        c.add(jl,"Center");
        
        if(!OT.isAlive()){
            System.out.println("ObstacleGenerator thread started.");
            OT.start();
        }
    }
    //implementacja algorytmu DFS
    private void dfs() {
        int x = 0, y = 0;
        
        //Create entry and exit from maze
        cells.get(0).remove(cells.get(0).walls[0]);
        cells.get(199).remove(cells.get(199).walls[2]);
        while(hasUnvisitedCells()){
            visited[20*y+x] = true;
            Cell cell = cells.get(20 * y + x);
            List<Integer> unvisited = new ArrayList<>();
            if (y > 0 && !visited[20 * (y - 1) + x]) unvisited.add((20 * (y - 1) + x));
            if (x > 0 && !visited[20 * y + (x - 1)]) unvisited.add((20 * y + (x - 1)));
            if (y < 9 && !visited[20 * (y + 1) + x]) unvisited.add((20 * (y + 1) + x));
            if (x < 19 && !visited[20 * y + (x + 1)]) unvisited.add((20 * y + (x + 1)));
            if (unvisited.size() > 0) {
                STACK.add(20 * y + x);
                int i = unvisited.get(new Random().nextInt(unvisited.size()));
                //0-top
                //1-right
                //2-bottom
                //3-left
                Cell nextCell = cells.get(i);
                if(i == 20 * y + x+1){
                    nextCell.remove(nextCell.walls[3]);
                    cell.remove(cell.walls[1]);
                    nextCell.walls[3] = null;
                    cell.walls[1] = null;
                }else if(i == 20 * y + x-1){
                    nextCell.remove(nextCell.walls[1]);
                    cell.remove(cell.walls[3]);
                    nextCell.walls[1] = null;
                    cell.walls[3] = null;
                }else if(i == 20 * y + x+20){
                    nextCell.remove(nextCell.walls[0]);
                    cell.remove(cell.walls[2]);
                    nextCell.walls[0] = null;
                    cell.walls[2] = null;
                }else if(i == 20 * y + x-20){
                    nextCell.remove(nextCell.walls[2]);
                    cell.remove(cell.walls[0]);
                    nextCell.walls[2] = null;
                    cell.walls[0] = null;
                }
 
                x = i % 20;
                y = (int) Math.floor(i / 20d);
            } else {
                Integer pop = STACK.pop();
                x = pop % 20;
                y = (int) Math.floor(pop / 20d);
 
            }
        }
    }
 //sprawdza czy na mapie są jeszcze komórki, które nie zostały odwiedzone
    private boolean hasUnvisitedCells() {
        for(boolean b : visited){
            if(!b){
                return true;
            }
        }
        return false;
    }
//sprawdza czy ruch jest możliwy
    private boolean checkIfMovePossible(Cell c, int direction) {
        return c.checkIfThereIsNoWall(direction);
    }
//resetuje stan postaci gracza
    private void resetAmcia() {
        if(TRY >= 1){
            Component[] c = jPanel1.getComponents();
            for(Component com : c){
                Cell cell = (Cell)com;
                Component[] c2 = cell.getComponents();
                for(Component com2 : c2){
                    if(com2 instanceof UserObject | com2 instanceof Obstacle){
                        cell.remove(com2);
                        cell.revalidate();
                        cell.updateUI();
                    }
                }
            }
            setPacman();
            TRY -= 1;
            jLabel1.setText(String.valueOf(TRY));
            jPanel1.revalidate();
            jPanel1.updateUI();
        }else{
             JOptionPane.showMessageDialog(this, "Przekroczyłeś limit prób! Chcesz zagrać ponownie?", "Limit prób", JOptionPane.INFORMATION_MESSAGE);
             System.exit(0);
        }
   }
 
    public class Cell extends JPanel {
 
        private boolean isVisited = false;
        private Component[] walls = new Component[4];
        private int vi = 0;
        private boolean isGhosted = false;
        
        {
            this.setSize(100, 100);
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(8, 46, 121));
        }
       
        public void setWall(Wall w, String pos) {
            walls[vi++] = w;
            this.add(w, pos);
        }
       
        public void removeWall(int i) {
            this.remove(i);
        }
       
        public boolean isVisited() {
            return isVisited;
        }
       
        public void setVisited(boolean visited) {
            this.isVisited = visited;
        }
 
        public int getCompCount() {
            return this.getComponentCount();
        }
        
        public boolean checkIfThereIsNoWall(int direction){
            if(walls[direction] == null){
                return true;
            }else{
                return false;
            }
        }
        
        public boolean checkIfGhosted(){
            return isGhosted;
        }
        
        public void setIsGhosted(boolean ghosted){
            this.isGhosted = ghosted;
        }
    }
   
    public class Wall extends JLabel {
       
        private int X = 10;
        private int Y = 10;
       
        {
            this.setOpaque(true);
            this.setPreferredSize(new Dimension(X, Y));
            this.setBackground(Color.BLACK);
        }
    }
    
    public class ObstacleGenerator extends Thread implements Runnable{
        private Thread TH;
        private HashMap<Integer,Obstacle> OBSTC_MAP = new HashMap<>();
        private String name;
        
        public ObstacleGenerator(String name){
            this.name = name;
        }
        
        @Override
        public void start(){
            if(TH == null){
                TH = new Thread(this,name);
                TH.start();
            }
        }
        
        @Override
        public void run(){
            while(true){
                generateObstacles();
                try {
                    Thread.sleep(5000);
                    jPanel1.revalidate();
                    jPanel1.updateUI();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        private void generateObstacles(){
            OBSTC_MAP.forEach((x,y) ->{
                Obstacle l = (Obstacle)y;
                Cell c = (Cell)jPanel1.getComponent(x);
                c.setIsGhosted(false);
                c.remove(l);
                c.revalidate();
                c.updateUI();
            });
            
            OBSTC_MAP.clear();
            
            for(int i = 0; i < 5; i++){
                Obstacle o = new Obstacle();
                Random r = new Random();
                int cell = r.nextInt(199);
                Cell c = (Cell)jPanel1.getComponent(cell);
                c.add(o);
                c.setIsGhosted(true);
                c.revalidate();
                c.updateUI();
                OBSTC_MAP.put(cell, o);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
