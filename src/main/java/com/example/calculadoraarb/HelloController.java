package com.example.calculadoraarb;

import com.example.calculadoraarb.dinamicos.ArbolBinario;
import com.example.calculadoraarb.dinamicos.NodoA;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private TextArea expresionDada;

    @FXML
    private Label resultadoL;

    @FXML
    private Label cadenaPostfija;

    @FXML
    private Canvas arbolCanvas;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void calcular() {
        String expresion = expresionDada.getText();
        CalculadoraService convertidor = new CalculadoraService();
        ArbolBinario<Character> expresionEnArbol = new ArbolBinario();

        try {
            String postfija = convertidor.infijaAPostfija(expresion);
            cadenaPostfija.setText("Expresión postfija: " + postfija);

            NodoA<Character> raiz = expresionEnArbol.arbolI(postfija);
            double resultado = expresionEnArbol.evaluar(raiz);
            resultadoL.setText("Resultado: " + resultado);

            double anchoArbol = calcularAnchoArbol(raiz, 50);
            double alturaArbol = calcularAlturaArbol(raiz, 60);

            arbolCanvas.setWidth(Math.max(anchoArbol + 200, 800));
            arbolCanvas.setHeight(alturaArbol + 50);

            GraphicsContext gc = arbolCanvas.getGraphicsContext2D();
            gc.clearRect(0, 0, arbolCanvas.getWidth(), arbolCanvas.getHeight());

            double xInicio = (arbolCanvas.getWidth() - anchoArbol) / 2 + 300;
            dibujarArbolExpresiones(gc, raiz, xInicio, 30, anchoArbol / 4);
        } catch (Exception e) {
            resultadoL.setText("Error: " + e.getMessage());
        }
    }

    /**
     * Determina el ancho total del arbol para crear el canvas con dicha anchura
     *
     * @param nodo nodo actual donde se esta calculando el ancho
     * @param  espacioHorizontal espacio entre los hojas o las rama - dependiendo del elemento -
     * @return ancho total que tendra el arbol en el espacio horizontal
     * */
    private double calcularAnchoArbol(NodoA<Character> nodo, double espacioHorizontal) {
        if (nodo == null) return 0;
        if (nodo.izquierda == null && nodo.derecha == null) return espacioHorizontal;
        return calcularAnchoArbol(nodo.izquierda, espacioHorizontal) + calcularAnchoArbol(nodo.derecha, espacioHorizontal) + espacioHorizontal;
    }

    /**
     * Determina el alto total del arbol para crear el canvas con dicha altura
     * @param nodo nodo actual donde se esta calculando la altura
     * @param  espacioVertical espacio entre los niveles del arbol
     * @return altura total que tendra el arbol en el espacio vertial
     * */
    private double calcularAlturaArbol(NodoA<Character> nodo, double espacioVertical) {
        if (nodo == null) return 0;
        return Math.max(calcularAlturaArbol(nodo.izquierda, espacioVertical), calcularAlturaArbol(nodo.derecha, espacioVertical)) + espacioVertical;
    }


    /**
     * Dibuja el arbol de expresiones de forma recuriva
     *
     * @param gcCanvas clase que permite dibujar en el canvas
     * @param nodo nodo actual que se esta dibujando
     * @param x coordenada x donde se pintara el nodo actual
     * @param y coordenada y donde se pintara el nodo actual
     * @param  desplazaminetoX desplazamiento en el eje de las x para la raiz y los nodos
     * */
    private void dibujarArbolExpresiones(GraphicsContext gcCanvas, NodoA<Character> nodo, double x, double y, double desplazaminetoX) {
        if (nodo == null) return;
        if (nodo.izquierda != null) {
            gcCanvas.strokeLine(x, y, x - desplazaminetoX, y + 60);
            dibujarArbolExpresiones(gcCanvas, nodo.izquierda, x - desplazaminetoX, y + 60, desplazaminetoX / 2);
        }
        if (nodo.derecha != null) {
            gcCanvas.strokeLine(x, y, x + desplazaminetoX, y + 60);
            dibujarArbolExpresiones(gcCanvas, nodo.derecha, x + desplazaminetoX, y + 60, desplazaminetoX / 2);
        }

        gcCanvas.setFill(Color.LIGHTBLUE);
        gcCanvas.fillOval(x - 15, y - 15, 30, 30);
        gcCanvas.setFill(Color.BLACK);
        gcCanvas.fillText(String.valueOf(nodo.valor), x - 5, y + 5);
    }

}