package co.edu.unicartagena.birch.logica.validacionYCompatibilidad;

import java.io.File;
import java.util.ArrayList;

/**
 * Clase que contiene todas las sentencias para trabajar archivos XMI generados
 * en la versión 2.1.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.3
 * @since 08/05/2017
 */
public final class Version2_1 extends Version {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public Version2_1() {
        this.setUmlNamespace("http://schema.omg.org/spec/UML/2.1");
        this.setXmiNamespace("http://schema.omg.org/spec/XMI/2.1");
        this.prefijo
                = "declare namespace uml =\"" + umlNamespace + "\";\n"
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
    public final String armarSentenciaArtefacto(String nombre, String path, int id, boolean isSearchAsId) {
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
    public final String armarSentenciaArtefacto(String nombre, File file, int id, boolean isSearchAsId) {
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
    public final String armarSentenciaGeneral(String path, int id) {
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
    public final String armarSentenciaGeneral(File file, int id) {
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
        this.sufijosArtefactos = new ArrayList<>();

        //[0] Atributos totales.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedAttribute[not(@association)])");

        //[1] Atributos públicos.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedAttribute[not(@association)][not(@visibility) or @visibility eq \"public\"])");

        //[2] Atributos estáticos.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedAttribute[not(@association)][@isStatic = \"true\"])");

        //[3] Métodos totales.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation)");

        //[4] Métodos públicos.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation[@visibility=\"public\" or not(@visibility)])");

        //[5] Métodos estáticos.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation[@isStatic = \"true\"])");

        //[6] Datos de los métodos de una clase especifica.
        this.sufijosArtefactos.add("\"][1];\n"
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

        //[7] Ides clases padre de una clase especifica.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "declare variable $generalizacion := $doc//uml:Model//packagedElement[@xmi:type eq \"uml:Realization\"];\n"
                + "declare function local:nombre-padres($clase)as xs:string{\n"
                + "	\n"
                + "	if(not(fn:exists($clase/generalization)) and not(fn:exists($generalizacion[@client eq $clase/@xmi:id])))\n"
                + "	then data(\"\")\n"
                + "	else fn:string-join((data($clases[@xmi:id eq $clase/generalization/@general]/@xmi:id), data($clases[@xmi:id eq $generalizacion[@client eq $clase/@xmi:id]/@supplier]/@xmi:id), local:nombre-padres($clases[@xmi:id eq $clase/generalization/@general])), \";\")\n"
                + "};\n"
                + "\n"
                + "local:nombre-padres($claseAEvaluar)");

        //[8] Métodos heredados.
        this.sufijosArtefactos.add("\"][1];\n"
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

        //[9] Parámetros totales.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($claseAEvaluar/ownedOperation/ownedParameter[@name != \"return\"])");

        //[10] Profundidad del arbol de herencias.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "declare function local:DIT($clase as element(packagedElement)) as xs:integer\n"
                + "{\n"
                + "  (:Si la clase no hereda de ninguna otra:)\n"
                + "  if(not(fn:exists($clase/generalization))) then\n"
                + "	0\n"
                + "  else \n"
                + "    1 + local:DIT($clases[@xmi:id eq $clase/generalization/@general])\n"
                + "};\n"
                + "\n"
                + "local:DIT($claseAEvaluar)");

        //[11] Hijos inmediatos.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "count($clases[generalization/@general eq $claseAEvaluar/@xmi:id])");

        //[12] Acoplamiento.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "declare variable $realizaciones := $doc//packagedElement[@xmi:type = \"uml:Realization\"];\n"
                + "declare variable $dependencias := $doc//packagedElement[@xmi:type = \"uml:Dependency\"];\n"
                + "\n"
                + "(:Relaciones eferentes:)\n"
                + "declare variable $conHerenciaE := count($claseAEvaluar//generalization);\n"
                + "declare variable $conRealizacionE := count($realizaciones[@client eq $claseAEvaluar/@xmi:id]);\n"
                + "declare variable $conDependenciaE := count($dependencias[@client eq $claseAEvaluar/@xmi:id]);\n"
                + "declare variable $conAsociacionesE := count($claseAEvaluar/ownedAttribute[boolean(@association)]);\n"
                + "(:Relaciones aferentes:)\n"
                + "declare variable $conHerenciaA := count($clases[generalization/@general eq $claseAEvaluar/@xmi:id]);\n"
                + "declare variable $conRealizacionA := count($realizaciones[@supplier eq $claseAEvaluar/@xmi:id]);\n"
                + "declare variable $conDependenciaA := count($dependencias[@supplier eq $claseAEvaluar/@xmi:id]);\n"
                + "declare variable $conAsociacionesA := count($clases[ownedAttribute/type/@xmi:idref = $claseAEvaluar/@xmi:id]);\n"
                + "\n"
                + " $conAsociacionesE + $conAsociacionesA + $conHerenciaE + $conRealizacionE + $conDependenciaE + $conHerenciaA + $conRealizacionA + $conDependenciaA");

        //[13] Datos herencia clase.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "fn:string-join(($claseAEvaluar/@xmi:id,$claseAEvaluar/generalization/@general),\";\")");

        //[14] Verificación de artefacto.
        this.sufijosArtefactos.add("\"][1];\n"
                + "\n"
                + "exists($claseAEvaluar)");
        
        //[15] Get ids.
        this.sufijosArtefactos.add("\"];\n"
                + "\n"
                + "fn:string-join(data($claseAEvaluar/@xmi:id), \";\")");
    }

    /**
     * Método que se encarga de llenar el vector que contiene todos los sufijos
     * de las sentencias de diagrama.
     */
    @Override
    protected void llenarSufijosGenerales() {
        this.sufijosDiagrama = new ArrayList<>();

        //[0] Número total de clases.
        this.sufijosDiagrama.add("\") ;\n"
                + "\n"
                + "declare variable $clase := $doc//packagedElement[@xmi:type = \"uml:Class\"];\n"
                + "count($clase)");

        //[1] Número total de clases abstractas.
        this.sufijosDiagrama.add("\") ;\n"
                + "\n"
                + "declare variable $claseAbstracta := $doc//packagedElement[@xmi:type = \"uml:Class\" and @isAbstract = \"true\"];\n"
                + "count($claseAbstracta)");

        //[2] Número total de interfaces.
        this.sufijosDiagrama.add("\");\n"
                + "\n"
                + "declare variable $interface := $doc//packagedElement[@xmi:type = \"uml:Interface\" ];\n"
                + "count($interface)");

        //[3] Número total de paquetes.
        this.sufijosDiagrama.add("\") ;\n"
                + "\n"
                + "declare variable $paquete := $doc//packagedElement[@xmi:type = \"uml:Package\"];\n"
                + "count($paquete)");

        //[4] Número total de atributos.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "count($clases/ownedAttribute[not(@association)])");

        //[5] Número total de atributos públicos.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "declare variable $clase := $clases[@xmi:type eq \"uml:Class\"];\n"
                + "count($clase/ownedAttribute[not(@association)][not(@visibility) or @visibility = \"public\"])");

        //[6] Número total de métodos.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "count($clases/ownedOperation)");

        //[7] Número total de métodos públicos.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "declare variable $clase := $clases[@xmi:type eq \"uml:Class\"];\n"
                + "count($clase/ownedOperation[not(@visibility) or @visibility = \"public\"])");

        //[8] Obtener todos los nombres.
        this.sufijosDiagrama.add("\");\n"
                + "\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "for $clase in $clases\n"
                + "return data($clase/@name)");

        //[9] Número total de clases hijas.
        this.sufijosDiagrama.add("\");\n"
                + "\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\"];\n"
                + "count($clases[not(not(generalization))])");

        //[10] Número total de clusters.
        this.sufijosDiagrama.add("\");\n"
                + "\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\"];\n"
                + "for $clase in $clases\n"
                + "let $clasesMadre := $clases[@xmi:id eq $clase/generalization/@general]\n"
                + "where $clasesMadre[not(generalization)]\n"
                + "return count($clasesMadre)");

        //[11] Número total de relaciones NH.
        this.sufijosDiagrama.add("\");\n"
                + "\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "declare variable $dependencias := $doc//packagedElement[@xmi:type = \"uml:Dependency\"];\n"
                + "declare variable $asociaciones := $clases/ownedAttribute[boolean(@association)];\n"
                + "declare variable $realizaciones := $doc//packagedElement[@xmi:type = \"uml:Realization\"];\n"
                + "declare variable $conDependencia := count($dependencias);\n"
                + "declare variable $conAsociaciones := count($asociaciones);\n"
                + "declare variable $conRealizaciones := count($realizaciones);\n"
                + "$conAsociaciones + $conDependencia + $conRealizaciones");

        //[12] Número total de atributos privados.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "count($clases/ownedAttribute[not(@association)][@visibility eq \"private\"])");

        //[13] Número total de métodos privados.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "count($clases/ownedOperation[@visibility=\"private\"])");

        //[14] Número total de métodos heredados.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "declare function local:metodos-heredados($clasesList)as xs:integer{\n"
                + "	let $firstClass := $clasesList[fn:position() eq 1]\n"
                + "	let $mHeredados := (count($firstClass/ownedOperation[@visibility=\"public\" or not(@visibility)	or @visibility eq \"protected\"]) * count($clases[generalization/@general eq $firstClass/@xmi:id]))\n"
                + "\n"
                + "	return \n"
                + "		if (count($clasesList) = 1)\n"
                + "		then $mHeredados\n"
                + "		else \n"
                + "		$mHeredados + local:metodos-heredados(fn:subsequence($clasesList,2))\n"
                + "};\n"
                + "\n"
                + "local:metodos-heredados($clases)");

        //[15] Número total de atributos heredados.
        this.sufijosDiagrama.add("\");\n"
                + "declare variable $clases := $doc//packagedElement[@xmi:type eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "\n"
                + "declare function local:atributos-heredados($clasesList)as xs:integer{\n"
                + "	let $firstClass := $clasesList[fn:position() eq 1]\n"
                + "	let $mHeredados := (count($firstClass/ownedAttribute[@visibility=\"public\" or not(@visibility)	or @visibility eq \"protected\"]) * count($clases[generalization/@general eq $firstClass/@xmi:id]))\n"
                + "\n"
                + "	return \n"
                + "		if (count($clasesList) = 1)\n"
                + "		then $mHeredados\n"
                + "		else \n"
                + "		$mHeredados + local:atributos-heredados(fn:subsequence($clasesList,2))\n"
                + "};\n"
                + "\n"
                + "local:atributos-heredados($clases)");
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
                + "declare variable $clases := $doc//packagedElement[@xmi:type"
                + " eq \"uml:Class\" or @xmi:type eq \"uml:Interface\"];\n"
                + "declare variable $claseAEvaluar := $clases[" + attr + " eq \"";
    }

    
}
