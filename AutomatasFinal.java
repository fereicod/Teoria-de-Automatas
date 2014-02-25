package Paquete1;
/*Este programa analizara un lenguaje que:

* Tomara como entrada un archivo con un texto, el cual será analizado en su Léxico y su sintaxis.
* El texto de entrada contará con las instrucciones cuyos formatos son los siguientes

      P x ( C ); Sf.
    
Donde:
 
    C -> b | b, C
    S -> A | A; S
    A -> i( C ) | d( C ) | rSum | wmSf | a
  
    x = ( a|b|c )*
    a = [ 0_9 ]+
    b = las inciales de cualquiera de los nombres de los miembro del equipo, en minúsculas
    m = la matricula de cualquiera de los miembro del equipo
    
    Matriculas                  Nombres
    
    1446729             Fernando Espino Iracheta
    920050              Carlos Alberto Hernandez Torres
    1446458             Luis Alfredo Castillo Moreno
  
    Entonces:
  
    b = fei | caht | lacm
    m = 1446729 | 920050 | 1446458

*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;
import javax.swing.*;

public class AutomatasFinal extends JFrame{
    
    private JTextArea areaSalida;//declaracion de un texto de area
    private JScrollPane panelDespl;//declaracion de una barra desplazadora
    private JLabel tema;//declaracion de un texto
    private JButton cargar;//declaracion de un boton
    private JButton validar;//declaracion de un boton
    private JButton borrar;//declaracion de un boton
    private int ax;//declaracion de una variable auxiliar 
    
    //creacion de constructor
    public AutomatasFinal(){
        
        super("Analizador de un Lenguaje");//declaracion de un titulo en la ventana
        setLayout(new FlowLayout());//crea la ventana
        
        tema = new JLabel("Cargue su código para validar...");//declarando un texto
        add(tema);//añadir el texto
        
        areaSalida = new JTextArea(20,30);//declarando el tamaño del area de texto
        panelDespl = new JScrollPane(areaSalida);//declarando una barra desplazador dentro del area de texto
        add(panelDespl, BorderLayout.CENTER);//añadir el area de texto con la barra desplazador
        
        cargar = new JButton("Cargar...");//delcarando el boton
        add(cargar);//añadir el boton
        Presionar1 click1 = new Presionar1();//instanciar la accion del boton
        cargar.addActionListener(click1);//delcarar una accion en el boton
        
        borrar = new JButton("Borrar");//delcarando el boton
        add(borrar);//añadir el boton
        Presionar2 click2 = new Presionar2();//instanciar la accion del boton
        borrar.addActionListener(click2);//delcarar una accion en el boton
        
        validar = new JButton("Validar");//delcarando el boton
        add(validar);//añadir el boton
        Presionar3 click3 = new Presionar3();//instanciar la accion del boton
        validar.addActionListener(click3);//delcarar una accion en el boton
        
        setSize(425, 425);//declarar tamaño de la ventana
        setLocationRelativeTo(null);//centrar la ventana
        setVisible(true);//mostarar ventana
        
    }//fin de constructor
//----------------------------------------------------------------------------------------------------------------------------------    
    private class Presionar1 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            leerGrafico();//llamada al metodo leerGrafico
        }//fin de ActionEvent
    }//fin de class Presionar1
//----------------------------------------------------------------------------------------------------------------------------------    
    private class Presionar2 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            areaSalida.setText("");//imprimira blanco en el area de texto
        }//fin de ActionEvent
    }//fin de class Presionar2
//----------------------------------------------------------------------------------------------------------------------------------
    private class Presionar3 implements ActionListener{
		public void actionPerformed(ActionEvent evento){
			String Input = areaSalida.getText();//todo lo que se lea en el area de texto, se guardara en una variable tipo string
                        
                        String esp="([ ]|[\\n]|[\\t])*";//se aceptan espacios o saltos de linea
                        String cad1=esp+"((caht|fei|lacm)|(((caht|fei|lacm)"+esp+","+esp+")+(caht|fei|lacm)))"+esp;
                        String opc1=esp+"(i"+esp+"\\("+cad1+"\\))"+esp;
                        String opc2=esp+"(d"+esp+"\\("+cad1+"\\))"+esp;
                        String opc5=esp+"([0-9]+)"+esp;
                        String subc3="(("+opc1+")|("+opc2+")|("+esp+"(r"+esp+"u"+esp+"(920050|1446729|1446458))"+esp+")|("+esp+"(w"+esp+"(920050|1446729|1446458)"+esp+"f)"+esp+")|("+opc5+"))";
                        String subc2="(("+opc1+")|("+opc2+")|("+esp+"(r"+esp+"(("+subc3+")|(((("+subc3+");)+)"+subc3+"))"+"u"+esp+"(920050|1446729|1446458))"+esp+")|("+esp+"(w"+esp+"(920050|1446729|1446458)"+esp+"(("+subc3+")|(((("+subc3+");)+)"+subc3+"))"+"f)"+esp+")|("+opc5+"))";
                        String subc1="(("+opc1+")|("+opc2+")|("+esp+"(r"+esp+"(("+subc2+")|(((("+subc2+");)+)"+subc2+"))"+"u"+esp+"(920050|1446729|1446458))"+esp+")|("+esp+"(w"+esp+"(920050|1446729|1446458)"+esp+"(("+subc2+")|(((("+subc2+");)+)"+subc2+"))"+"f)"+esp+")|("+opc5+"))";
                        String opc3=esp+"(r"+esp+"(("+subc1+")|(((("+subc1+");)+)"+subc1+"))"+"u"+esp+"(920050|1446729|1446458))"+esp;
                        String opc4=esp+"(w"+esp+"(920050|1446729|1446458)"+esp+"(("+subc1+")|(((("+subc1+");)+)"+subc1+"))"+"f)"+esp;
                        
                        String opcA="(("+opc1+")|("+opc2+")|("+opc3+")|("+opc4+")|("+opc5+"))";
                        String opcS="(("+opcA+")|(((("+opcA+");)+)"+opcA+"))";
                        
                        Pattern p = Pattern.compile("^P"+esp+"[abc]+"+esp+"\\("+cad1+"\\)"+esp+";"+esp+"("+opcS+")"+esp+"f.$");
			Matcher m = p.matcher(Input);
			if( !m.find() ) {//si no se cumple
				JOptionPane.showMessageDialog(null, "¡Error de código!\nPor favor, intente de nuevo..");
			}
			else {//de lo contrario
				JOptionPane.showMessageDialog(null, "¡Código válido!");
			}//fin de if/else
		}//fin de ActionEvent
	}//fin de class Presionar3
//----------------------------------------------------------------------------------------------------------------------------------    
    public void leerGrafico(){
        
        File f;//declara varibale de tipo archivo
        javax.swing.JFileChooser j = new javax.swing.JFileChooser();//instancian en una variable la ventaba de busqueda
        j.showOpenDialog(j);//se declara la ventana de busqueda
        
            try{
                String path = j.getSelectedFile().getAbsolutePath();// declaracion de una path
            
                f = new File(path);//se instancia la variable de tipo archivo con la direccion de path
            
                try{
                    FileReader fr = new FileReader(f);//se leera el archivo que se encuentra en la path
                    BufferedReader br = new BufferedReader(fr);//se guardara lo leido en el buffer
                
                    String aux;//declara una variable auxiliar de tipo string
                
                    while((aux = br.readLine()) != null){//mientras que en la variable auxiliarque contiene cada linea del buffer sea diferente de null
                        areaSalida.append(aux+"\n");//se imprimira con saltos de linea tras cada leida de cada linea
                    }//fin de while
                }catch(IOException e){}//fin de try/catch
            }catch(NullPointerException e){
                ax = javax.swing.JOptionPane.showConfirmDialog(j, "Seguro que desea salir?");
                
                if(ax == JOptionPane.YES_OPTION)//si es presionada el boton si
                    System.exit(0);//se termina el programa
            }//fin de try/catch
    }//fin del metodo leerGrafico
//-----------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args){
        
        AutomatasFinal app = new AutomatasFinal();//se instancia el constructor
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//si se termina la accion del programa, se termina la ejecucion y cierra
        
    }//fin de main
}//fin de la clase AutomatasFinal


/*
    Copiar esto en .txt tal como esta escrito
    Esta es una prueba, se  puede leer los saltos de linea
    y tabulaciones.


P abbcba ( caht, fei, lacm);
i ( caht, fei, lacm);
r
    d ( caht, fei, lacm)
u 920050;
w 1446458 i ( caht, fei, lacm);
    r
        1;
        58901;
        d ( caht, fei, lacm)
    u 1446729
    f;
    34
f.

*/