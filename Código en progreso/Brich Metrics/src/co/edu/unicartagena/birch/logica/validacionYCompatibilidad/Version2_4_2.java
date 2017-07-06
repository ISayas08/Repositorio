package co.edu.unicartagena.birch.logica.validacionYCompatibilidad;

import java.io.File;
import java.util.HashMap;

/**
 * Clase que contiene todas las sentencias para trabajar archivos XMI generados
 * con la versión 2.4.2 o 2.4.1.
 *
 * @author Ismael Sayas Arrieta
 */
public final class Version2_4_2 extends Version {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public Version2_4_2() {
        this.setUmlNamespace("http://www.omg.org/spec/UML/20110701");
        this.setXmiNamespace("http://www.omg.org/spec/XMI/20110701");
        this.prefijo = "declare namespace uml =\"" + umlNamespace + "\";\n"
                + "declare namespace xmi =\"" + xmiNamespace + "\";\n"
                + "declare variable $doc := doc(\"";
        this.llenarSufijosArtefactos();
        this.llenarSufijosGenerales();
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite armar una sentencia especifica para recopilar datos de
     * un artefacto especifico. La sentencia está compuesta por un prefijo, la
     * ruta del archivo, un intermedio, el nombre del artefacto y finalmente un
     * sufijo el cuál debe ser idenificado con un entero.
     *
     * @param nombre el nombre del artefacto.
     * @param path la dirección de archivo xmi.
     * @param id la id que identifica a la sentencia deseada.
     * @param isSearchAsId atributo que permite especificar si la sentencia se
     * armará a través de la id del artefacto o a través del nombre.
     * @return un String que contiene la sentencia armada.
     */
    @Override
    public final String armarSentenciaArtefacto(String nombre, String path, String id, boolean isSearchAsId) {
        return this.prefijo
                + path
                + this.getIntermedioArtefactos(isSearchAsId)
                + nombre
                + this.sufijosArtefactos.get(id);
    }

    /**
     * Método que permite armar una sentencia especifica para recopilar datos de
     * un artefacto especifico. La sentencia está compuesta por un prefijo, la
     * ruta del archivo, un intermedio, el nombre del artefacto y finalmente un
     * sufijo el cuál debe ser idenificado con un entero.
     *
     * @param nombre el nombre del artefacto.
     * @param file un objeto File que contiene la dirección de archivo xmi.
     * @param id la id que identifica a la sentencia deseada.
     * @param isSearchAsId atributo que permite especificar si la sentencia se
     * armará a través de la id del artefacto o a través del nombre.
     * @return un String que contiene la sentencia armada.
     */
    @Override
    public final String armarSentenciaArtefacto(String nombre, File file, String id, boolean isSearchAsId) {
        return this.armarSentenciaArtefacto(
                nombre,
                file.getAbsolutePath(),
                id,
                isSearchAsId);
    }

    /**
     * Método que permite armar una sentencia especifica para recopilar datos
     * generales. La sentencia está compuesta por un prefijo, la ruta del
     * archivo y un sufijo que debe ser identificado por un entero.
     *
     * @param path la dirección del archivo xmi.
     * @param id la ide que identifica la sentencia deseada.
     * @return un String que contiene la sentencia armada.
     */
    @Override
    public final String armarSentenciaGeneral(String path, String id) {
        return this.prefijo
                + path
                + this.sufijosDiagrama.get(id);
    }

    /**
     * Método que permite armar una sentencia especifica para recopilar datos
     * generales. La sentencia está compuesta por un prefijo, la ruta del
     * archivo y un sufijo que debe ser identificado por un entero.
     *
     * @param file un objeto File que contiene la dirección del archivo xmi.
     * @param id la ide que identifica la sentencia deseada.
     * @return un String que contiene la sentencia armada.
     */
    @Override
    public final String armarSentenciaGeneral(File file, String id) {
        return this.armarSentenciaGeneral(file.getAbsolutePath(), id);
    }

//==============================================================================
//  Métodos secundarios.
//==============================================================================
    /**
     * Método que se encarga de llenar el vector que contiene todos los sufijos
     * de las sentencias de artefactos.
     */
    @Override
    protected void llenarSufijosArtefactos() {
        this.sufijosArtefactos = new HashMap();

        //Atributos totales.
        this.sufijosArtefactos.put("MA Atributos totales", "\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedAttribute[not(@association)])");

        //Atributos públicos.
        this.sufijosArtefactos.put("MA Atributos públicos", "\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedAttribute[not(@association)][not(@visibility) or @visibility eq \"public\"])");

        //Atributos estáticos.
        this.sufijosArtefactos.put("MA Atributos estáticos", "\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedAttribute[not(@association)][@isStatic = \"true\"])");

        //Métodos totales.
        this.sufijosArtefactos.put("MA Métodos totales", "\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation)");

        //Métodos públicos.
        this.sufijosArtefactos.put("MA Métodos públicos", "\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation[@visibility=\"public\" or not(@visibility)])");

        //Métodos estáticos.
        this.sufijosArtefactos.put("MA Métodos estáticos", "\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation[@isStatic = \"true\"])");

        //Datos de los métodos de una clase especifica.
        this.sufijosArtefactos.put("MA Datos métodos", "\"][1];\n"
                + "\n"
                + "declare function local:parametros($metodo as element())as xs:string{\n"
                + "	fn:string-join($metodo/ownedParameter[not(fn:exists(@direction))]/@type, \"#\")\n"
                + "};\n"
                + "\n"
                + "for $metodo in $claseAEvaluar/ownedOperation\n"
                + "     let $tipoRetorno := data($metodo/ownedParameter[fn:exists(@direction)]/@type)\n"
                + "	 let $nombreMetodo := $metodo/@name\n"
                + "	 let $parametros := local:parametros($metodo)\n"
                + "	 return fn:string-join(($tipoRetorno, $nombreMetodo, $parametros), \"%\")");

        //Ides clases heredadas por una clase especifica.
        this.sufijosArtefactos.put("MA Ides clases padre", "\"][1];\n"
                + "declare function local:id-padres($clase)as xs:string{\n"
                + "  if(not(fn:exists($clase/generalization)))\n"
                + "    	then data(\"\")\n"
                + "    	else \n"
                + "		fn:string-join((data($clases[@xmi:id eq $clase/generalization/@general]/@xmi:id), local:id-padres($clases[@xmi:id eq $clase/generalization/@general])), \";\")\n"
                + "};\n"
                + "local:id-padres($claseAEvaluar)");

        //Ides interfaces implementadas por una clase especifica.
        this.sufijosArtefactos.put("MA Ides interfaces implementadas", "\"][1];\n"
                + "declare variable $realizacion := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Realization\"];\n"
                + "fn:string-join(data($clases[@xmi:id = $realizacion[@client = $claseAEvaluar/@xmi:id]/@supplier]/@xmi:id),\";\")");

        //Atributos heredados.
        this.sufijosArtefactos.put("MA Atributos heredados", "\"][1];\n"
                + "declare function local:metodos-heredados($clase as element(packagedElement)) as xs:integer{\n"
                + "if(not(fn:exists($clase/generalization))) then\n"
                + "0 else \n"
                + "count($clases[@xmi:id eq $clase/generalization/@general]/ownedAttribute[not(@association)][@visibility eq \"public\" or not(@visibility) or @visibility eq \"protected\"]) \n"
                + "+ local:metodos-heredados($clases[@xmi:id eq $clase/generalization/@general])\n"
                + "};\n"
                + "local:metodos-heredados($claseAEvaluar)");

        //Métodos heredados.
        this.sufijosArtefactos.put("MA Métodos heredados", "\"][1];\n"
                + "\n"
                + "declare function local:metodos-heredados($clase as element(packagedElement)) as xs:integer\n"
                + "{\n"
                + "(:Si la clase no hereda de ninguna otra:)\n"
                + "if(not(fn:exists($clase/generalization))) then\n"
                + "0\n"
                + "else \n"
                + "count($clases[@xmi:id eq $clase/generalization/@general]/ownedOperation[@visibility eq \"public\" or not(@visibility) or @visibility eq \"protected\"]) \n"
                + "+ local:metodos-heredados($clases[@xmi:id eq $clase/generalization/@general])\n"
                + "};\n"
                + "\n"
                + "local:metodos-heredados($claseAEvaluar)");

        //Parámetros totales.
        this.sufijosArtefactos.put("MA Parámetros totales", "\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation/ownedParameter[@name != \"return\"])");

        //Profundidad del arbol de herencias.
        this.sufijosArtefactos.put("MA DHT", "\"][1];\n"
                + "declare function local:DIT($clase) as xs:integer {\n"
                + "     if(not(fn:exists($clase/generalization))) then\n"
                + "         0\n"
                + "     else \n"
                + "         1 + local:DIT($clases[@xmi:id = $clase/generalization/@general])\n"
                + " };\n"
                + "local:DIT($claseAEvaluar)");

        //Hijos inmediatos.
        this.sufijosArtefactos.put("MA Hijos inmediatos", "\"][1];\n"
                + "\n"
                + "count($clases[generalization/@general eq $claseAEvaluar/@xmi:id])");

        //Acoplamiento.
        this.sufijosArtefactos.put("MA Acoplamiento", "\"][1];\n"
                + "declare variable $relacionesRDU := $doc//packagedElement[@xmi:type eq \"uml:Realization\" or @xmi:type eq \"uml:Dependency\" or @xmi:type eq \"uml:Usage\"];\n"
                + "(:elaciones eferentes:)\n"
                + "declare variable $conRDUE := $relacionesRDU[@client = $claseAEvaluar/@xmi:id];\n"
                + "declare variable $conHerenciaE := $claseAEvaluar/generalization;\n"
                + "declare variable $conAsociacionesE := $clases[ownedAttribute/type/@xmi:idref = $claseAEvaluar/@xmi:id];\n"
                + "declare variable $conBindE := $claseAEvaluar/templateBinding;\n"
                + "declare variable $conImportE := $claseAEvaluar/elementImport;\n"
                + "(:Relaciones aferentes:)\n"
                + "declare variable $conRDUA := $relacionesRDU[@supplier eq $claseAEvaluar/@xmi:id];\n"
                + "declare variable $conHerenciaA := $clases/generalization[@general eq $claseAEvaluar/@xmi:id];\n"
                + "declare variable $conAsociacionesA := $claseAEvaluar/ownedAttribute[boolean(@association)];\n"
                + "declare variable $conBindA := $clases/templateBinding[@boundElement eq $claseAEvaluar/@xmi:id];\n"
                + "declare variable $conImportA := $clases/elementImport[@importedElement eq $claseAEvaluar/@xmi:id];\n"
                + "count($conRDUE) + count($conHerenciaE) + count($conAsociacionesE) + count($conBindE) + count($conImportE) +\n"
                + "count($conRDUA) + count($conHerenciaA) + count($conAsociacionesA) + count($conBindA) + count($conImportA)");

        //Datos herencia clase.
        this.sufijosArtefactos.put("MA Datos de herencia", "\"][1];\n"
                + "data($claseAEvaluar/generalization/@general)");

        //Verificación de artefacto.
        this.sufijosArtefactos.put("MA Verificar artefacto", "\"][1];\n"
                + "exists($claseAEvaluar)");

        //Get ids.
        this.sufijosArtefactos.put("MA Get id", "\"];\n"
                + "fn:string-join(data($claseAEvaluar/@xmi:id), \";\")");
    }

    /**
     * Método que se encarga de llenar el vector que contiene todos los sufijos
     * de las sentencias generales.
     */
    @Override
    protected void llenarSufijosGenerales() {
        this.sufijosDiagrama = new HashMap();

        //Número total de clases.
        this.sufijosDiagrama.put("MS Total clases", "\") ;\n"
                + "declare variable $clase := $doc//uml:Model//packagedElement[@xmi:type = \"uml:Class\"];\n"
                + "count($clase)");

        //Número total de clases abstractas.
        this.sufijosDiagrama.put("MS Total clases abstractas", "\") ;\n"
                + "declare variable $claseAbstracta := $doc//uml:Model//packagedElement[@xmi:type = \"uml:Class\" and @isAbstract = \"true\"];\n"
                + "count($claseAbstracta)");

        //Número total de interfaces.
        this.sufijosDiagrama.put("MS Total interfaces", "\");\n"
                + "declare variable $interface := $doc//uml:Model//packagedElement[@xmi:type = \"uml:Interface\" ];\n"
                + "count($interface)");

        //Número total de paquetes.
        this.sufijosDiagrama.put("MS Total paquetes", "\") ;\n"
                + "declare variable $paquete := $doc//uml:Model//packagedElement[@xmi:type = \"uml:Package\"];\n"
                + "count($paquete)");

        //Número total de atributos.
        this.sufijosDiagrama.put("MS Total atributos", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "count($clases/ownedAttribute[not(@association)])");

        //Número total de atributos públicos.
        this.sufijosDiagrama.put("MS Total atributos públicos", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "declare variable $clase := $clases[@xmi:type eq \"uml:Class\"];\n"
                + "count($clase/ownedAttribute[not(@association)][not(@visibility) or @visibility = \"public\"])");

        //Número total de métodos.
        this.sufijosDiagrama.put("MS Total métodos", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "count($clases/ownedOperation)");

        //Número total de métodos públicos.
        this.sufijosDiagrama.put("MS Total métodos públicos", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "count($clases/ownedOperation[not(@visibility) or @visibility = \"public\"])");

        //Obtener todas las ids de los artefactos del diagrama.
        this.sufijosDiagrama.put("MS Get Ids", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "fn:string-join(data($clases/@xmi:id), \";\")");

        //Obtener todas los nombres de los artefactos del diagrama.
        this.sufijosDiagrama.put("MS Get Names", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "fn:string-join(data($clases/@name), \";\")");

        //Número total de clases hijas.
        this.sufijosDiagrama.put("MS Total clases hijas", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\"];\n"
                + "count($clases[not(not(generalization))])");

        //Número total de clusters.
        this.sufijosDiagrama.put("MS Total clusters", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\"];\n"
                + "for $clase in $clases\n"
                + "let $clasesMadre := $clases[@xmi:id eq $clase/generalization/@general]\n"
                + "where $clasesMadre[not(generalization)]\n"
                + "return count($clasesMadre)");

        //Número total de relaciones NH.
        this.sufijosDiagrama.put("MS Total relaciones noH", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "declare variable $relacionesRDU := $doc//packagedElement[@xmi:type eq \"uml:Realization\" or @xmi:type eq \"uml:Dependency\" or @xmi:type eq \"uml:Usage\"];               \n"
                + "declare variable $asociaciones := $clases/ownedAttribute[boolean(@association)];\n"
                + "declare variable $Bind := $clases/templateBinding;\n"
                + "declare variable $Import := $clases/elementImport;\n"
                + "\n"
                + "count($relacionesRDU) + count($asociaciones) + count($Bind) + count($Import) ");

        //Número total de atributos privados.
        this.sufijosDiagrama.put("MS Total atributos privados", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "count($clases/ownedAttribute[not(@association)][@visibility eq \"private\"])");

        //Número total de métodos privados.
        this.sufijosDiagrama.put("MS Total métodos privados", "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "count($clases/ownedOperation[@visibility=\"private\"])");
    }

//==============================================================================
//  Getters and Setters.
//==============================================================================
    /**
     * Método que permite obtener la parte intermedia de las sentencias a nivel
     * de artefactos. Este método pemite cambiar entre seleccionar la clase a
     * evaluar a trvés de su id o de su nombre.
     *
     * @param isIdSearch parámetro utilizado para cambiar entre búsqueda por id
     * y búsqueda por nombre, para las sentencias a nivel de artefacto.
     * @return un String con el intermedio de la sentencia.
     */
    @Override
    public String getIntermedioArtefactos(boolean isIdSearch) {
        String attr = isIdSearch ? "@xmi:id" : "@name";
        return "\");\n"
                + "declare variable $clases := $doc//uml:Model//packagedElement[@xmi:type"
                + " eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "declare variable $claseAEvaluar := $clases[" + attr + " eq \"";
    }
}
