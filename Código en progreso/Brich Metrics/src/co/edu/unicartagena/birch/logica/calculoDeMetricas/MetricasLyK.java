package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import co.edu.unicartagena.birch.logica.recopiladorDeDatos.ControlDeRecopilaciones;
import co.edu.unicartagena.birch.logica.recopiladorDeDatos.IRD;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase encargada de calcular las métricas de Lorenz and Kidd (Lyk). Para éste
 * componente se tomaron en consideración los siguientes métricas de la familia
 * de métricas de LyK:
 * <ul>
 * <li>Number of Public Methods.</li>
 * <li>Number of Methods.</li>
 * <li>Number of Public Variables.</li>
 * <li>Number of Variables.</li>
 * <li>Number of Class Variables.</li>
 * <li>Number of Class Methods.</li>
 * <li>Number of Method Inherited.</li>
 * <li>Number of Method Overriden.</li>
 * <li>Number of New Methods.</li>
 * <li>Avarage Parameter Per Method.</li>
 * <li>Specialization Index.</li>
 * </ul>
 *
 * @author Ismael Sayas Arrieta
 * @version 1.3
 * @since 16/09/2016
 */
public final class MetricasLyK implements IMArtefactos {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public MetricasLyK() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite calcular una métrica de artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto que se desea
     * evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea evaluar.
     * @return un String que contiene el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Override
    public String calcularMetrica(String artifactId, String path, String id)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        //Solicitamos todos los métodos de la clase.
        List<Method> metodos = Arrays.asList(this.getClass()
                .getDeclaredMethods());

        //Ejecutamos el método cuyo nombre termine con "_Mi",
        //donde i es el valor de la id.
        String dato = (String) metodos.stream()
                .filter(m -> m.getName().endsWith("_" + id))
                .collect(Collectors.toList()).get(0).invoke(this, artifactId, path);

        return dato == null ? "-1" : dato;
    }

    /**
     * Método que permite calcular una métrica de artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto que se desea
     * evaluar.
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea evaluar.
     * @return un String que contiene el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Override
    public String calcularMetrica(String artifactId, File file, String id)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return this.calcularMetrica(artifactId, file.getAbsolutePath(), id);
    }

    /**
     * Mètodo que permite calcular contar la cantidad de mètodos pùblicos que
     * hay en una clase o interface.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor de la mètric solicitada.
     */
    private String numbeOfPublicMethods_LyK_0(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.METODOS_PUBLICOS, IRD.FAMILIA_LYK);
    }

    /**
     * Método que permite contar el número de métodos que tiene una clase o
     * interface.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número de método que teiene el
     * artefacto evaluado.
     */
    private String numberOfMethods_LyK_1(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.METODOS_TOTALTES, IRD.FAMILIA_LYK);
    }

    /**
     * Método que permite contar el número de atributos públicos de una clase o
     * interface.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número de atributos públicos del
     * artefacto evaluado.
     */
    private String numberOfPublicVariables_LyK_2(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.ATRIBUTOS_PUBLICOS, IRD.FAMILIA_LYK);
    }

    /**
     * Método que cuenta el número de atributos de una clase o interface.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el numero de atributos del artefacto
     * evaluado.
     */
    private String numberOfVariables_LyK_3(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.ATRIBUTOS_TOTALES, IRD.FAMILIA_LYK);
    }

    /**
     * Método que permite contar el número de atributos estáticos de una claase
     * o inteface.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número de atributos estáticos del
     * artefacto a evaluar.
     */
    private String numberOfClassVariables_LyK_4(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.ATRIBUTOS_ESTATICOS, IRD.FAMILIA_LYK);
    }

    /**
     * Método que cuenta el número de métodos estáticos de una clase o
     * interface.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo a evaluar.
     * @return un String que contiene el número de métodos estáticos del
     * artefacto evaluado.
     */
    private String numberOfClassMethods_LyK_5(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.METODOS_ESTATICOS, IRD.FAMILIA_LYK);
    }

    /**
     * Método que cuenta el número de métodos que hereda una clase.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI
     * @return un String que contiene el número de métodos heredados por el
     * artefacto evaluado.
     */
    private String numberOfMethodInherited_LyK_6(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.METODOS_HEREDADOS, IRD.FAMILIA_LYK);
    }

    /**
     * Método que cuenta el número de métodos que sobrescribe una clase
     * especifica.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número de métodos que sobrescribe el
     * artefacto evaluado.
     */
    private String numberOfMethodOverridden_LyK_7(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        List<Artefacto> clasesPadres = new ArrayList<>();
        Artefacto claseAEvaluar = new Artefacto(artifactId);

        //Llenando los datos de la clase a evaluar.
        //Solicitamos los nombres de los métodos de la clase a evaluar.
        Arrays.asList(cdr.recopilarDatos(artifactId, path,
                IRD.DATOS_METODOS_CLASE, IRD.FAMILIA_LYK)
                .split(";"))
                .stream()
                .forEach(ms -> {
                    claseAEvaluar.addNuewMethod(ms);
                });

        //Solicitamos la id de las clases de las cules hereda la clase a evaluar.
        List<String> iDCPadres = Arrays.asList(cdr.
                recopilarDatos(artifactId, path,
                        IRD.IDS_CLASES_E_INTEFACES_SOBRESCRITAS, IRD.FAMILIA_LYK)
                .split(";"));

        if (iDCPadres.get(0).equals("-1")) {
            //Si la clase no tiene padres entonces no tiene métodos sobrescritos.
            return "0";
        } else {
            //Se recopilan los datos de cada clase padre y se almacenan en 
            //objetos tipo Artefacto, que son añadidos a la lista de clases padres.
            iDCPadres.stream().forEach(iDCP -> {
                Artefacto a = new Artefacto(iDCP);
                Arrays.asList(cdr
                        .recopilarDatos(iDCP, path,
                                IRD.DATOS_METODOS_CLASE, IRD.FAMILIA_LYK)
                        .split(";"))
                        .stream()
                        .forEach(ms -> {
                            a.addNuewMethod(ms);
                        });

                clasesPadres.add(a);
            });

            //Creamos la pila de métodos coincidentes para no repetir métodos.
            List<Metodo> pilaMetodosCoincidentes = new ArrayList<>();

            //Se comparan método a método cada clase padre con la clase a evaluar
            //para encontrar métodos coincidentes.
            clasesPadres.stream().forEach((clasesPadre) -> {
                for (int f = 0; f < clasesPadre.getMetodos().size(); f++) {
                    for (int c = 0; c < claseAEvaluar.getMetodos().size(); c++) {
                        if (clasesPadre.getMetodos().get(f).comparar(claseAEvaluar.getMetodos().get(c))) {
                            if (!pilaMetodosCoincidentes.contains(claseAEvaluar.getMetodos().get(c))) {
                                pilaMetodosCoincidentes.add(claseAEvaluar.getMetodos().get(c));
                            }
                        }
                    }
                }
            });

            return Integer.toString(pilaMetodosCoincidentes.size());
        }
    }

    /**
     * Método que cuenta el número de métodos añadidos por una clase especifica.
     * Los métodos añadidos de una clases son aquellos métodos que no son
     * sobreescritos.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número de métodos que añade el
     * artefacto evaluado.
     */
    private String numberOfNewMethods_LyK_8(String artifactId, String path) {
        //Se solicitan los métodos totales de la clase.
        int metodosTotales = Integer.parseInt(
                this.numberOfMethods_LyK_1(artifactId, path));
        //Se solicitan los métodos sobreescritos.
        int metodosSobrescritos = Integer.parseInt(
                this.numberOfMethodOverridden_LyK_7(artifactId, path));

        //Los métodos añadidos son aquellos métodos de la clase que no son
        //sobrescritos. Por lo tanto:
        return metodosTotales > metodosSobrescritos
                ? "" + (metodosTotales - metodosSobrescritos)
                : "0";
    }

    /**
     * Mètodo que realiza el càlculo de la mètrica LyK: Promedio de paràmetros
     * por mètodos para una clase o interface especifica.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor de la mètrica corresponiente al
     * artefacto evaluado.
     */
    private String averageParameterPerMethod_LyK_9(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int nParametros = Integer.parseInt(cdr.
                recopilarDatos(artifactId, path,
                        IRD.PARAMETROS_TOTALES, IRD.FAMILIA_LYK));

        int NM = Integer.parseInt(this.numberOfMethods_LyK_1(artifactId, path));
        return NM > 0 //Se valida la división por cero.
                ? Double.toString((double) nParametros / (double) NM)
                : "0";
    }

    /**
     * Mètodo que realiza el càlculo de la mètrica LyK: Specialization Index
     * para una clase o interface especifica.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor de la mètrica correspondiente al
     * artefacto evaluado.
     */
    private String specializationIndex_LyK_10(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int NMO = Integer.parseInt(
                this.numberOfMethodOverridden_LyK_7(artifactId, path));

        int DIT = Integer.parseInt(cdr.
                recopilarDatos(artifactId, path,
                        IRD.PROFUNDIDAD_DEL_ARBOL_DE_HERENCIAS, IRD.FAMILIA_CYK));

        int NM = Integer.parseInt(this.numberOfMethods_LyK_1(artifactId, path));

        return NM > 0 //Se valida la división or cero.
                ? Double.toString(((double) NMO * (double) DIT) / (double) NM)
                : "0";
    }
    
    private String nMethodOverriddenNoR_LyK__1(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        List<Artefacto> clasesPadres = new ArrayList<>();
        Artefacto claseAEvaluar = new Artefacto(artifactId);

        //Llenando los datos de la clase a evaluar.
        //Solicitamos los nombres de los métodos de la clase a evaluar.
        Arrays.asList(cdr.recopilarDatos(artifactId, path,
                IRD.DATOS_METODOS_CLASE, IRD.FAMILIA_LYK)
                .split(";"))
                .stream()
                .forEach(ms -> {
                    claseAEvaluar.addNuewMethod(ms);
                });

        //Solicitamos la id de las clases de las cules hereda la clase a evaluar.
        List<String> iDCPadres = Arrays.asList(cdr.
                recopilarDatos(artifactId, path,
                        IRD.IDS_CLASES_PADRE, IRD.FAMILIA_LYK)
                .split(";"));

        if (iDCPadres.get(0).equals("-1")) {
            //Si la clase no tiene padres entonces no tiene métodos sobrescritos.
            return "0";
        } else {
            //Se recopilan los datos de cada clase padre y se almacenan en 
            //objetos tipo Artefacto, que son añadidos a la lista de clases padres.
            iDCPadres.stream().forEach(iDCP -> {
                Artefacto a = new Artefacto(iDCP);
                Arrays.asList(cdr
                        .recopilarDatos(iDCP, path,
                                IRD.DATOS_METODOS_CLASE, IRD.FAMILIA_LYK)
                        .split(";"))
                        .stream()
                        .forEach(ms -> {
                            a.addNuewMethod(ms);
                        });

                clasesPadres.add(a);
            });

            //Creamos la pila de métodos coincidentes para no repetir métodos.
            List<Metodo> pilaMetodosCoincidentes = new ArrayList<>();

            //Se comparan método a método cada clase padre con la clase a evaluar
            //para encontrar métodos coincidentes.
            clasesPadres.stream().forEach((clasesPadre) -> {
                for (int f = 0; f < clasesPadre.getMetodos().size(); f++) {
                    for (int c = 0; c < claseAEvaluar.getMetodos().size(); c++) {
                        if (clasesPadre.getMetodos().get(f).comparar(claseAEvaluar.getMetodos().get(c))) {
                            if (!pilaMetodosCoincidentes.contains(claseAEvaluar.getMetodos().get(c))) {
                                pilaMetodosCoincidentes.add(claseAEvaluar.getMetodos().get(c));
                            }
                        }
                    }
                }
            });

            return Integer.toString(pilaMetodosCoincidentes.size());
        }
    }

//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
