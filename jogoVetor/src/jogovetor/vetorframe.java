/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jogovetor;
import javax.swing.JOptionPane;
import java.util.Random;
import java.time.Instant;
import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JButton;
/**
 *
 * @author gugas, hugoriosbrito
 */
public class vetorframe extends javax.swing.JFrame {
    
    //TODO: Resolver BUG que sempre reinicia o tempo decorrido se o usuário clicar no botão 'Iniciar'.

    /**
     * Creates new form vetorframe
     */
    String[] vetor = new String[16]; 
    private Instant tempoInicio;
    private Timer cronometro;
    private JButton[] botoes;
    boolean enc = false;
    boolean primeiraAbertura = true;
    String tempoZerado = "00:00:00";
    Random aleatorio = new Random();
    
    JFrame frame = new JFrame();

    
    public vetorframe() {
        initComponents();
        
        botoes = new JButton[] {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16};
    }
    
    public void limparTela(){
        for(int i = 0; i < 16; i++){
            vetor[i] = ("");
        }
        
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");
        b10.setText("");
        b11.setText("");
        b12.setText("");
        b13.setText("");
        b14.setText("");
        b15.setText("");
        b16.setText("");
        enc = false;
        tempo.setText("Tempo Decorrido: " + tempoZerado);
        
        
    }
    
    public void embaralhar(){
 
        for(int i = 0; i < 15; i++){
            vetor[i] = String.valueOf(i + 1);
            System.out.println(vetor[i]);
            
        }
        vetor[15] = "";
        /*
        //print do vetor organizado
        System.out.println("Organizado: ");
        for(int i = 0; i < 16; i++){
            System.out.println(vetor[i]);
        }
        */
        
        for(int i = vetor.length - 1; i > 0; i--){
            int j = aleatorio.nextInt(i + 1);
            String temp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = temp;
        }
        /*
        ///print do vetor embaralhado
        System.out.println("Embaralhado: ");
        for(int i = 0; i < 16; i++){
            System.out.println(vetor[i]);
        }
        */
    }
    
    public void iniciarJogo(){
        if (primeiraAbertura == true){
       
            embaralhar();

            b1.setText(vetor[0]);
            b2.setText(vetor[1]);
            b3.setText(vetor[2]);
            b4.setText(vetor[3]);
            b5.setText(vetor[4]);
            b6.setText(vetor[5]);
            b7.setText(vetor[6]);
            b8.setText(vetor[7]);
            b9.setText(vetor[8]);
            b10.setText(vetor[9]);
            b11.setText(vetor[10]);
            b12.setText(vetor[11]);
            b13.setText(vetor[12]);
            b14.setText(vetor[13]);
            b15.setText(vetor[14]);
            b16.setText(vetor[15]);

            enc = false;
            primeiraAbertura = false;
        } else if (primeiraAbertura == false) {
            JOptionPane.showMessageDialog(frame,"Jogo já iniciado!", "Erro!",JOptionPane.ERROR_MESSAGE); //Adicionado verificação de jogo já iniciado
        }
    }
    
    public void iniciarContador(){
        tempoInicio = Instant.now();
        cronometro = new Timer();
        
        cronometro.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(!enc){
                    tempo.setText("Tempo Decorrido: " + tempoDecorrido());
                }
                else{
                    cronometro.cancel();
                }
            }
        }, 0, 1000);
    }
    
    public void pararContador(){
        cronometro = new Timer();
        
        cronometro.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                    cronometro.cancel();
                    tempo.setText("Tempo Decorrido: " + tempoZerado);
            }
        }, 0, 1000);
    }
    
    public String tempoDecorrido(){
        if(tempoInicio != null && !enc){ //adicionado (&& !enc) para verificar também se o jogo não está encerrado
            Instant tempoAtual = Instant.now();
            Duration duracao = Duration.between(tempoInicio, tempoAtual);
            long horas = duracao.toHours();
            long minutos = duracao.toMinutesPart();
            long segundos = duracao.toSecondsPart();
            return String.format("%02d:%02d:%02d", horas, minutos, segundos);
        }
        else{
            return tempoZerado;
        }
    }
        
    /*
    
    Retorna a posição int no vetor em que está o botão atual clicado, se não, retorna -1(que não causa movimentação de nenhum botão).
    
    */
    public int getPosicaoBotaoAtual(String[] posicoes, String textoBotaoAtual){ //retira a posição no vetor 
        for (int i = 0; i < posicoes.length; i++){
            if (posicoes[i].equals(textoBotaoAtual)){
                return i;
            }
        }
        return -1;
    };
    
    /*
    
    Retorna a posição do botão ao redor do botão atual que está vazio
    
    */
    public int getProximoBotaoVazio(String[] posicoes, String textoBotaoAtual){        
        int posAtual = getPosicaoBotaoAtual(posicoes, textoBotaoAtual);
        int posicaoDireita = posAtual + 1;
        int posicaoEsquerda = posAtual - 1;
        
        int posicaoCima = posAtual - 4;
        int posicaoCimaDireita = posAtual - 3;
        int posicaoCimaEsquerda = posAtual - 5;
        
        int posicaoBaixo = posAtual + 4;
        int posicaoBaixoDireita = posAtual + 5;
        int posicaoBaixoEsquerda = posAtual + 3;
        
        int[] aVerificar = {posicaoDireita,posicaoEsquerda,posicaoCima,posicaoCimaDireita,posicaoCimaEsquerda,posicaoBaixo,posicaoBaixoDireita,posicaoBaixoEsquerda};
        
        for (int i = 0; i < aVerificar.length; i++){
            int posVizinho = aVerificar[i];
            if (posVizinho >= 0 && posVizinho < posicoes.length){
                if (posicoes[posVizinho] == null || posicoes[posVizinho].equals("")){
                    return posVizinho;
                }
            }
        }

        return -1; 
    };
    
    
    /*
    
    Troca a posição dos botões 
    
    */
    public void trocarValoresBotoes(String textoPosAtual){
        int posProx = getProximoBotaoVazio(vetor, textoPosAtual);
        int posAtual = getPosicaoBotaoAtual(vetor, textoPosAtual);
        
        if (!textoPosAtual.equals("")){
            String tempTexto = vetor[posAtual];
            vetor[posAtual] = vetor[posProx];
            vetor[posProx]  = tempTexto;
            
            atualizarInterface();
                               
        }
    }
    
    /*
    
    Atualiza a interface após a jogada
    
    */
    
    public void atualizarInterface(){
        for(int i =0; i < vetor.length; i++){
            botoes[i].setText(vetor[i]);
        }
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        tempo = new javax.swing.JLabel();
        mensagemIni = new javax.swing.JLabel();
        iniciar = new javax.swing.JButton();
        reiniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        getContentPane().add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));

        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        getContentPane().add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 80, 80));

        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        getContentPane().add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 80, 80));

        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        getContentPane().add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 80, 80));

        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        getContentPane().add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 80, 80));

        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        getContentPane().add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 80, 80));

        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        getContentPane().add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 80, 80));

        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        getContentPane().add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 80, 80));

        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });
        getContentPane().add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 80, 80));

        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });
        getContentPane().add(b10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 80, 80));

        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });
        getContentPane().add(b11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 80, 80));

        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });
        getContentPane().add(b12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 80, 80));

        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });
        getContentPane().add(b13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 80, 80));

        b14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b14ActionPerformed(evt);
            }
        });
        getContentPane().add(b14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 80, 80));

        b15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b15ActionPerformed(evt);
            }
        });
        getContentPane().add(b15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 80, 80));

        b16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b16ActionPerformed(evt);
            }
        });
        getContentPane().add(b16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 80, 80));

        tempo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tempo.setText("Tempo Decorrido: 00:00:00 ");
        getContentPane().add(tempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        mensagemIni.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mensagemIni.setText("Mensagem");
        getContentPane().add(mensagemIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        iniciar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        iniciar.setText("Iniciar");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        getContentPane().add(iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        reiniciar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reiniciar.setText("Reiniciar");
        reiniciar.setActionCommand("reiniciar");
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });
        getContentPane().add(reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b11.getText());
    }//GEN-LAST:event_b11ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b1.getText());
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b2.getText());

    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b3.getText());

    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b4.getText());

    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b5.getText());

    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b6.getText());

    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b7.getText());
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b8.getText());
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b9.getText());
    }//GEN-LAST:event_b9ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b10.getText());
    }//GEN-LAST:event_b10ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b12.getText());
    }//GEN-LAST:event_b12ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b13.getText());
        
    }//GEN-LAST:event_b13ActionPerformed

    private void b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b14.getText());
    }//GEN-LAST:event_b14ActionPerformed

    private void b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b15.getText());
    }//GEN-LAST:event_b15ActionPerformed

    private void b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16ActionPerformed
        // TODO add your handling code here:
        trocarValoresBotoes(b16.getText());
    }//GEN-LAST:event_b16ActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        // TODO add your handling code here:
        iniciarContador();
        iniciarJogo();
    }//GEN-LAST:event_iniciarActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        // TODO add your handling code here:
        int resposta = JOptionPane.showConfirmDialog(frame,"Quer mesmo reiniciar?", "Reiniciar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION){
            limparTela();
            primeiraAbertura = true;
            pararContador();
            enc=true;
        } else if (resposta == JOptionPane.NO_OPTION){
            frame.dispose();
        }
    }//GEN-LAST:event_reiniciarActionPerformed


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
            java.util.logging.Logger.getLogger(vetorframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vetorframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vetorframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vetorframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vetorframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton iniciar;
    private javax.swing.JLabel mensagemIni;
    private javax.swing.JButton reiniciar;
    private javax.swing.JLabel tempo;
    // End of variables declaration//GEN-END:variables
}
