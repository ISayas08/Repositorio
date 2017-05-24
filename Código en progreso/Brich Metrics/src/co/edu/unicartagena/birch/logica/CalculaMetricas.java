package co.edu.unicartagena.birch.logica;

import co.edu.unicartagena.birch.logica.calculoDeMetricas.ControlDeCalculoDeMetricas;
import co.edu.unicartagena.birch.logica.calculoDeMetricas.ICM;
import co.edu.unicartagena.birch.logica.calculoDeMetricas.IResultado;
import co.edu.unicartagena.birch.logica.recopiladorDeDatos.ControlDeRecopilaciones;
import co.edu.unicartagena.birch.logica.recopiladorDeDatos.IRD;
import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.IVC;
import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.Validador;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Clase que implementa los métodos declrador por la interface
 * ICalcularMetricas.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.5
 * @since 08/02/2017
 */
public class CalculaMetricas implements ICalculaMetricas {

    /**
     * Constractor por defecto.
     */
    public CalculaMetricas() {

    }

    /**
     * Método que permite hacer el calculo de métricas a un único artefacto
     * perteneciente a un diagrama. Es necesario especificar la id del artefacto
     * y la ruta del archivo XMI donde se encuentran los datos del mismo.
     *
     * @param artifactId un String con la id del artefacto.
     * @param path un String con la ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido, o se produzca algún error, se
     * retornará null y se mostrará un mensaje por consola.
     */
    @Override
    public IResultado calcularMetricas(String artifactId, String path) {
        //Declaración de variables.
        String result = "NA";
        IResultado resultados = null;
        IVC validador = Validador.getInstance();
        ICM cCalculo = new ControlDeCalculoDeMetricas();

        //Se modifica la ruta para de asegurar que sea compatible con XQuery.
        path = this.modificarRuta(path);
        //Se verifica que el archivo sea válido.
        result = validador.verificarArchivoXMI(path);

        if (result.equals("The file is valid.")) {
            //Si el archivo es válido.
            //Se verifica que el artefacto exista en el diagrama.
            if (validador.verificarArtefacto(artifactId, path, false)) {
                //Si el artefacto es válido.
                //Se llama el método que realiza el cálculo de las métricas.
                //y se asigna el resultado en el objeto resultados.
                resultados = cCalculo.calcularMetricasArtefacto(artifactId, path);
            } else {
                //En caso de que el artefacto no sea válido.
                //Se informa por consola.
                System.out.println("Result: The artifact called \"" + artifactId
                        + "\" is missing.");
            }
        } else {
            //En caso de que el archivo no sea válido.
            //Se informa por consola.
            System.out.println("Result: " + result);
        }

        try {
            //Se liberan los datos del validador.
            validador.disposeSingleton();
        } catch (Throwable ex) {
            Logger.getLogger(CalculaMetricas.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        //Se retornan los datos.
        return resultados;
    }

    /**
     * Método que permite hacer el calculo de métricas a un único artefacto
     * perteneciente a un diagrama. Es necesario especificar la id del artefacto
     * y la ruta del archivo XMI donde se encuentran los datos del mismo.
     *
     * @param artifactId un String con la id del artefacto.
     * @param file un objeto File con la ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido, o se produzca algún error, se
     * retornará null y se mostrará un mensaje por consola.
     */
    @Override
    public IResultado calcularMetricas(String artifactId, File file) {
        return this.calcularMetricas(artifactId, file.getAbsolutePath());
    }

    /**
     * Método que permite calcular métricas que miden aspectos generales a nivel
     * de sistema, como la cantidad de clases, el número de paquetes, entre
     * otros. Es necesario proporcionar la ruta donde se encuentra el archivo
     * XMI que contiene los datos del diagrama.
     *
     * @param path un String que contiene La ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido o se produzca un error, se retornará
     * null.
     */
    @Override
    public IResultado calcularMetricas(String path) {
        //Declaración de variables.
        String result = "NA";
        IResultado resultados = null;
        IVC validador = Validador.getInstance();
        ICM cCalculo = new ControlDeCalculoDeMetricas();

        //Modificamos la rota para asegurar que sea compatible con XQuery.
        path = this.modificarRuta(path);

        //Verificamos que el archivo sea válido.
        result = validador.verificarArchivoXMI(path);

        if (result.equals("The file is valid.")) {
            //Si el archivo es válido.
            //Se llama al método que realiza el cálculo de las métreicas.
            //Se asigna el resultado al objeto resultados. 
            resultados = cCalculo.calcularMetricasGenerales(path);
        } else {
            //En caso de que el archivo no sea válido.
            //Se informa por consola.
            System.out.println("Result: " + result);
        }

        try {
            //Se liberan los datos del validador.
            validador.disposeSingleton();
        } catch (Throwable ex) {
            Logger.getLogger(CalculaMetricas.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        //Se retorna el resultado.
        return resultados;
    }

    /**
     * Método que permite calcular métricas que miden aspectos generales a nivel
     * de sistema, como la cantidad de clases, el número de paquetes, entre
     * otros. Es necesario proporcionar la ruta donde se encuentra el archivo
     * XMI que contiene los datos del diagrama.
     *
     * @param file un objeto File que contiene La ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido o se produzca un error, se retornará
     * null.
     */
    @Override
    public IResultado calcularMetricas(File file) {
        return this.calcularMetricas(file.getAbsolutePath());
    }

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica de aquellas que miden las caracteristicas de un artefacto. Es
     * necesario proporcionar la ruta del archivo, el nombre del artefacto y la
     * id de la métrica que se desea obtener. Todas las IDs están especificadas
     * en la interface ICalculaMetricas.
     *
     * @param artifactId un String con el nombre del artefacto a evaluar.
     * @param path un String con la ruta para acceder al archivo.
     * @param id un entero con la id de la métrica.
     * @return un String que contiene el valor de la métrica, o "NA" en caso de
     * que haya habido un error. De resultar el últmo evento se informará por
     * consola.
     */
    @Override
    public String calcularMetricas(String artifactId, String path, int id) {
        //Declaración de variables
        String dato = "NA";

        //Se verifica que la id sea válida.
        if (verificarId(id, true)) {
            //En caso de que la id sea válida.
            //Se declaran el resto de variables.
            String result = "NA";
            IVC validador = Validador.getInstance();
            ICM cCalculo = new ControlDeCalculoDeMetricas();

            //Se modifica la ruta para de asegurar que sea compatible con XQuery.
            path = this.modificarRuta(path);
            //Se verifica si el archivo es válido.
            result = validador.verificarArchivoXMI(path);

            if (result.equals("The file is valid.")) {
                //Si el archivo es válido
                //Se verifica que el artefacto a calcular exista en el diagrama.
                if (validador.verificarArtefacto(artifactId, path, true)) {
                    //Si el artefacto es válido.
                    //Se llama al método que calcula la métrica 
                    //y se asigna el resultado en la variable dato.
                    dato = "" + cCalculo.calcularMetricasArtefacto(artifactId, path, id);
                } else {
                    //Si el artefacto no es válido.
                    //Se informa por consola.
                    System.out.println("Result: The artifact called \"" + artifactId
                            + "\" is missing.");
                }
            } else {
                //Si el archivo no es válido.
                //Se informa por consola.
                System.out.println("Result: " + result);
            }

            try {
                //Se liberan los datos del validdor.
                validador.disposeSingleton();
            } catch (Throwable ex) {
                Logger.getLogger(CalculaMetricas.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        } else {
            //En caso de que la id no sea valida.
            //Se informa por consola.
            System.err.println("La id no es válida. "
                    + "Para métricas a nivel de artefactos, "
                    + "sólo se admiten id entre 0 y 14");
        }

        //Se retorna el resultado.
        return dato;
    }

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica de aquellas que miden las caracteristicas de un artefacto. Es
     * necesario proporcionar la ruta del archivo, la id del artefacto y la id
     * de la métrica que se desea obtener. Todas las IDs están especificadas en
     * la interface ICalculaMetricas.
     *
     * @param artifactId un String con la id del artefacto a evaluar.
     * @param file un objeto File con la ruta para acceder al archivo.
     * @param id un entero con la id de la métrica.
     * @return un String que contiene el valor de la métrica, o "NA" en caso de
     * que haya habido un error. De resultar el últmo evento se informará por
     * consola.
     */
    @Override
    public String calcularMetricas(String artifactId, File file, int id) {
        return this.calcularMetricas(artifactId, file.getAbsolutePath(), id);
    }

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica a nivel de diagrama. Es necesario proporcionar la ruta del
     * archivo y la id de la métrica que se desea obtener. Todas las IDs están
     * especificadas en la interface ICalculaMetricas.
     *
     * @param path un String con la ruta del archivo.
     * @param id un entero con la id de la métrica.
     * @return un String que contiene el valor de la métrica o "NA" en caso de
     * que se haya producido un error, en cuyo caso se informará por consola.
     */
    @Override
    public String calcularMetricas(String path, int id) {
        //Declaración de variables
        String dato = "NA";

        //Se verifica que la id sea válida.
        if (verificarId(id, false)) {
            //En caso de que la id sea válida.
            //Se declaran el resto de variables.
            String result = "NA";
            IVC validador = Validador.getInstance();
            ICM cCalculo = new ControlDeCalculoDeMetricas();

            //Modificamos la ruta para asegurar que sea compatible con XQuery.
            path = this.modificarRuta(path);
            //Verificamos que el archivo sea válido.
            result = validador.verificarArchivoXMI(path);

            if (result.equals("The file is valid.")) {
                //Si el archivo es válido.
                //Se llama el método que realiza el cálculo de la métrica.
                //Se asigna el valor a la variable dato.
                dato = "" + cCalculo.calcularMetricasGenerales(path, id);
            } else {
                //En caso de que el archivo no sea válido.
                //Se informa por consola.
                System.out.println("Result: " + result);
            }

            try {
                //Se liberan los datos del validador.
                validador.disposeSingleton();
            } catch (Throwable ex) {
                Logger.getLogger(CalculaMetricas.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        } else {
            //En caso de que la id no sea válida.
            //Se informa por consola.
            System.err.println("La id no es válida. "
                    + "Para métricas a nivel de sistema, "
                    + "sólo se admiten id entre 0 y 15");
        }

        //Se retornan los datos.
        return dato;
    }

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica a nivel de diagrama. Es necesario proporcionar la ruta del
     * archivo y la id de la métrica que se desea obtener. Todas las IDs están
     * especificadas en la interface ICalculaMetricas.
     *
     * @param file un objeto File con la ruta del archivo.
     * @param id un entero con la id de la métrica.
     * @return un String que contiene el valor de la métrica o "NA" en caso de
     * que se haya producido un error, en cuyo caso se informará por consola.
     */
    @Override
    public String calcularMetricas(File file, int id) {
        return this.calcularMetricas(file.getAbsolutePath(), id);
    }

    /**
     * Método encargado de modificar la ruta del archivo, con el fin de asegurar
     * de que sea compatible con XQuery.
     *
     * @param path un String con la ruta del archivo.
     * @return un String con la nueva ruta del archivo.
     */
    private String modificarRuta(String path) {
        //Declaración de variables.
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        //Divide la ruta en porciones separadas por el "file.separator" 
        //y las vuelve a unir de forma que queden enlasada con el símbolo "/".
        //Retorna la ruta modificada.
        return Arrays.asList(
                path.split(pattern))
                .stream()
                .collect(Collectors.joining("/"));
    }

    /**
     * Método que verifica que la id válida.
     *
     * @param id un entero que contiene la id.
     * @param isNivelArtefacto un boolean que especifica si se trata de una id
     * de métricas a nivel de artefacto o métricas a nivel de sistema.
     * @return retorna true si la id está en el rango apropiado, false en caso
     * contrario.
     */
    private boolean verificarId(int id, boolean isNivelArtefacto) {
        return isNivelArtefacto
                ? (id >= 0 && id <= 14) //Ids para métricas a nivel de artefacto.
                : (id >= 0 && id <= 15); //Ids para métricas a nivel de sistema.
    }

    /**
     * Método que permite obtener los id de las clases con un nombre especifico.
     * Es necesario proporcionar la ruta del archivo y el nombre del(los)
     * artefacto(s).
     *
     * @param path la ruta del archivo
     * XMI.
     * @param name el nombre del los artefactos a los que se desea conocer la
     * id.
     * @return Una lista de String que contiene las ids de los artefactos que
     * tienen el nombre indicado.
     */
    @Override
    public List<String> getArtifacId(String name, String path) {
        List<String> ids = new ArrayList();
        String result = "NA";
        IVC validador = Validador.getInstance();
        IRD cRecopilaciones = new ControlDeRecopilaciones();

        //Modificamos la ruta para asegurar que sea compatible con XQuery.
        path = this.modificarRuta(path);
        //Verificamos que el archivo sea válido.
        result = validador.verificarArchivoXMI(path);

        if (result.equals("The file is valid.")) {
            if (validador.verificarArtefacto(name, path, false)) {
                //Si el artefacto es válido.
                //Se llama al método que retorna todas las ids 
                //y se asigna el resultado en la variable dato.
                IRD ird = new ControlDeRecopilaciones();
                ids = ird.getArtifacId(name, path);
            } else {
                //Si el artefacto no es válido.
                //Se informa por consola.
                System.out.println("Result: The artifact called \"" + name
                        + "\" is missing.");
            }
        } else {
            //En caso de que el archivo no sea válido.
            //Se informa por consola.
            System.out.println("Result: " + result);
        }
        return ids;
    }

    /**
     * Método que permite obtener los id de las clases con un nombre especifico.
     * Es necesario proporcionar la ruta del archivo y el nombre del(los)
     * artefacto(s).
     *
     * @param file un objeto de la clase File que contien la ruta del archivo
     * XMI.
     * @param name el nombre del los artefactos a los que se desea conocer la
     * id.
     * @return Una lista de String que contiene las ids de los artefactos que
     * tienen el nombre indicado.
     */
    @Override
    public List<String> getArtifacId(String name, File file) {
        return this.getArtifacId(name, file.getAbsolutePath());
    }

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param path la ruta del archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    @Override
    public List<String> getAllIdentifier(String path, boolean isLookingForIds) {
        return null;
    }

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param file una instancia de la clase File que contiene la ruta del archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    @Override
    public List<String> getAllIdentifier(File file, boolean isLookingForIds) {
        return this.getAllIdentifier(file.getAbsolutePath(), isLookingForIds);
    }
}
