<<<<<<< HEAD
/*
 * DatosProfesionales.java
 *
 * Generated with Matisse Schema Definition Language 9.1.11
 * Generation date: Tue Apr 23 18:54:50 2019
 */

// Note: the package and extends declarations are generated by mt_sdl, do not modify them

package gest_proyectos;

import com.matisse.reflect.*;

/**
 * <code>DatosProfesionales</code> is a schema class generated by <code>mt_sdl</code>.
 * Any user-written classes will be found at the end of the file, after
 * the '// END of Matisse SDL Generated Code' comment.
 * Attribute types, default values, and relationship minimum and maximum
 * cardinality are stored in the database itself, not in this source code.
 * For more information, see <i>Getting Started with MATISSE</i>.
 */
public class DatosProfesionales extends com.matisse.reflect.MtObject {

    // BEGIN Matisse SDL Generated Code
    // DO NOT MODIFY UNTIL THE 'END of Matisse SDL Generated Code' MARK BELOW
    /*
     * Generated with Matisse Schema Definition Language 9.1.11
     * Generation Date: Tue Apr 23 18:54:50 2019
     */

    /*
     * Class variables and methods
     */

    /** Class <code>DatosProfesionales</code> cache ID */
    private static int CID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtClass.Loader("gest_proyectos.DatosProfesionales"));

    /**
     * Gets the <code>DatosProfesionales</code> class descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return a class descriptor
     */
    public static com.matisse.reflect.MtClass getClass(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtClass)db.getCachedObject(CID);
    }

    /**
     * Factory constructor. This constructor is called by <code>MtObjectFactory</code>.
     * It is public for technical reasons but is not intended to be called
     * directly by user methods.
     * @param db a database
     * @param mtOid an existing object ID in the database
     */
    public DatosProfesionales(com.matisse.MtDatabase db, int mtOid)  {
        super(db, mtOid);
    }

    /**
     * Cascaded constructor, used by subclasses to create a new object in the database.
     * It is protected for technical reasons but is not intended to be called
     * directly by user methods.
     * @param mtCls a class descriptor (the class to instantiate)
     */
    protected DatosProfesionales(com.matisse.reflect.MtClass mtCls)  {
        super(mtCls);
    }

    /**
     * Opens an iterator on all instances of this class (and its subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> instanceIterator(com.matisse.MtDatabase db) {
        return getClass(db).<E>instanceIterator(DatosProfesionales.class);
    }

    /**
     * Opens an iterator on all instances of this class (and its subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @param numObjPerBuffer maximum number of objects to fetch from the server at a time
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> instanceIterator(com.matisse.MtDatabase db, int numObjPerBuffer) {
        return getClass(db).<E>instanceIterator(numObjPerBuffer, DatosProfesionales.class);
    }

    /**
     * Counts the number of instances of this class (and its subclasses).
     * @param db a database
     * @return total number of instances
     */
    public static long getInstanceNumber(com.matisse.MtDatabase db) {
        return getClass(db).getInstanceNumber();
    }

    /**
     * Opens an iterator on all own instances of this class (excluding subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> ownInstanceIterator(com.matisse.MtDatabase db) {
        return getClass(db).<E>ownInstanceIterator(DatosProfesionales.class);
    }

    /**
     * Opens an iterator on all own instances of this class (excluding subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @param numObjPerBuffer maximum number of objects to fetch from the server at a time
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> ownInstanceIterator(com.matisse.MtDatabase db, int numObjPerBuffer) {
        return getClass(db).<E>ownInstanceIterator(numObjPerBuffer, DatosProfesionales.class);
    }

    /**
     * Counts the number of own instances of this class (excluding subclasses).
     * @param db a database
     * @return total number of instances
     */
    public static long getOwnInstanceNumber(com.matisse.MtDatabase db) {
        return getClass(db).getOwnInstanceNumber();
    }

    /*
     * Attribute access methods
     */

    /* Attribute 'dni' */

    /** Attribute <code>dni</code> cache ID */
    private static int dniCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("dni",CID));

    /**
     * Gets the <code>dni</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getDniAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(dniCID);
    }


    /**
     * Gets the <code>dni</code> attribute value.
     * @return the value
     *
     * @see #setDni
     * @see #removeDni
     */
    public final String getDni() {
        return getString(getDniAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>dni</code> attribute value.
     * @param val the new value
     *
     * @see #getDni
     * @see #removeDni
     */
    public final void setDni(String val) {
        setString(getDniAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>dni</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getDni
     * @see #setDni
     */
    public final void removeDni() {
        removeValue(getDniAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getDni
     * @see #setDni
     */
    public final boolean isDniDefaultValue() {
        return isDefaultValue(getDniAttribute(getMtDatabase()));
    }


    /* Attribute 'categoria' */

    /** Attribute <code>categoria</code> cache ID */
    private static int categoriaCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("categoria",CID));

    /**
     * Gets the <code>categoria</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getCategoriaAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(categoriaCID);
    }


    /**
     * Gets the <code>categoria</code> attribute value.
     * @return the value
     *
     * @see #setCategoria
     * @see #removeCategoria
     */
    public final String getCategoria() {
        return getString(getCategoriaAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>categoria</code> attribute value.
     * @param val the new value
     *
     * @see #getCategoria
     * @see #removeCategoria
     */
    public final void setCategoria(String val) {
        setString(getCategoriaAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>categoria</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getCategoria
     * @see #setCategoria
     */
    public final void removeCategoria() {
        removeValue(getCategoriaAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getCategoria
     * @see #setCategoria
     */
    public final boolean isCategoriaDefaultValue() {
        return isDefaultValue(getCategoriaAttribute(getMtDatabase()));
    }


    /* Attribute 'sueldo_bruto_anual' */

    /** Attribute <code>sueldo_bruto_anual</code> cache ID */
    private static int sueldo_bruto_anualCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("sueldo_bruto_anual",CID));

    /**
     * Gets the <code>sueldo_bruto_anual</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getSueldo_bruto_anualAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(sueldo_bruto_anualCID);
    }


    /**
     * Gets the <code>sueldo_bruto_anual</code> attribute value.
     * @return the value
     *
     * @see #setSueldo_bruto_anual
     * @see #removeSueldo_bruto_anual
     */
    public final float getSueldo_bruto_anual() {
        return getFloat(getSueldo_bruto_anualAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>sueldo_bruto_anual</code> attribute value.
     * @param val the new value
     *
     * @see #getSueldo_bruto_anual
     * @see #removeSueldo_bruto_anual
     */
    public final void setSueldo_bruto_anual(float val) {
        setFloat(getSueldo_bruto_anualAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>sueldo_bruto_anual</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getSueldo_bruto_anual
     * @see #setSueldo_bruto_anual
     */
    public final void removeSueldo_bruto_anual() {
        removeValue(getSueldo_bruto_anualAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getSueldo_bruto_anual
     * @see #setSueldo_bruto_anual
     */
    public final boolean isSueldo_bruto_anualDefaultValue() {
        return isDefaultValue(getSueldo_bruto_anualAttribute(getMtDatabase()));
    }


    /*
     * Relationship access methods
     */

    /* Relationship 'datos_prof_de' */

    /** Relationship <code>datos_prof_de</code> cache ID */
    private static int datos_prof_deCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtRelationship.Loader("datos_prof_de",CID));

    /**
     * Gets the <code>datos_prof_de</code> relationship descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return a relationship descriptor object
     */
    public static com.matisse.reflect.MtRelationship getDatos_prof_deRelationship(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtRelationship)db.getCachedObject(datos_prof_deCID);
    }

    /**
     * Gets the <code>datos_prof_de</code> sucessor object.
     * @return an object 
     *
     * @see #setDatos_prof_de
     * @see #clearDatos_prof_de
     */
    public final gest_proyectos.Empleado getDatos_prof_de() {
        return (gest_proyectos.Empleado)getSuccessor(getDatos_prof_deRelationship(getMtDatabase()));
    }

    /**
     * Sets the <code>datos_prof_de</code> successor object. It is not necessary to clear the
     * relationship before setting a new successor.
     * @param succ the new successor object
     *
     * @see #getDatos_prof_de
     * @see #clearDatos_prof_de
     */
    public final void setDatos_prof_de(gest_proyectos.Empleado succ) {
        setSuccessor(getDatos_prof_deRelationship(getMtDatabase()), succ);
    }

    /**
     * Removes all <code>datos_prof_de</code> successors.  When the minimum cardinality
     * is 1, a new successor must be set before commit.
     */
    public final void clearDatos_prof_de() {
        clearSuccessors(getDatos_prof_deRelationship(getMtDatabase()));
    }


    /*
     * Index access methods
     */

    /* Index 'Empleado_DatosProf_pk' */

    /** Index <code>Empleado_DatosProf_pk</code> cache ID */
    private static int empleado_DatosProf_pkIndexCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtIndex.Loader("gest_proyectos.Empleado_DatosProf_pk"));

    /**
     * Gets the <code>Empleado_DatosProf_pk</code> index descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return an index descriptor object
     */
    public static com.matisse.reflect.MtIndex getEmpleado_DatosProf_pkIndex(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtIndex)db.getCachedObject(empleado_DatosProf_pkIndexCID);
    }

    /**
     * Finds one <code>DatosProfesionales</code> object in index <code>Empleado_DatosProf_pk</code>.
     * @param db a database
     * @param dni search parameter
     * @return the matching <code>DatosProfesionales</code> object or <code>null</code> if none was found
     */
    public static DatosProfesionales lookupEmpleado_DatosProf_pk(com.matisse.MtDatabase db, String dni) {
        return (DatosProfesionales)getEmpleado_DatosProf_pkIndex(db).lookup(new Object[] {dni}, getClass(db));
    }

    /**
     * Finds <code>DatosProfesionales</code> objects in index <code>Empleado_DatosProf_pk</code>.
     * @param db a database
     * @param dni search parameter
     * @return the matching <code>DatosProfesionales</code> objects or an empty array if none was found
     */
    public static DatosProfesionales[] lookupObjectsEmpleado_DatosProf_pk(com.matisse.MtDatabase db, String dni) {
        return (DatosProfesionales[])getEmpleado_DatosProf_pkIndex(db).lookupObjects(new Object[] {dni}, getClass(db), DatosProfesionales.class);
    }

    /**
     * Opens an iterator on index <code>Empleado_DatosProf_pk</code> for class <code>DatosProfesionales</code>.
     * Each indexed attribute has a pair of "from" and "to" parameters which
     * define the search range for that attribute.  With strings, you may use
     * <code>null</code> to start "from" at the beginning or extend "to" to the end.
     * @param <E> a MtObject class     * @param db a database
     * @param fromDni search parameter
     * @param toDni search parameter
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> empleado_DatosProf_pkIterator(com.matisse.MtDatabase db, String fromDni, String toDni) {
        return getEmpleado_DatosProf_pkIndex(db).<E>iterator(new Object[] {fromDni}, new Object[] {toDni}, getClass(db), com.matisse.reflect.MtIndex.DIRECT, com.matisse.MtDatabase.MAX_PREFETCHING, DatosProfesionales.class);
    }

    /**
     * Opens an iterator on index <code>Empleado_DatosProf_pk</code> for class <code>DatosProfesionales</code>. 
     * Each indexed attribute has a pair of "from" and "to" parameters which
     * define the search range for that attribute.  With strings, you may use
     * <code>null</code> to start "from" at the beginning or extend "to" to the end.
     * @param <E> a MtObject class     * @param db a database
     * @param fromDni search parameter
     * @param toDni search parameter
     * @param filterClass a subclass; use <code>null</code> not to restrict iterator to a subclass
     * @param direction MtIndex.DIRECT or MtIndex.REVERSE
     * @param numObjPerBuffer maximum number of objects to be retrieved in each request to server
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> empleado_DatosProf_pkIterator(com.matisse.MtDatabase db, String fromDni, String toDni, com.matisse.reflect.MtClass filterClass, int direction, int numObjPerBuffer) {
        return getEmpleado_DatosProf_pkIndex(db).iterator(new Object[] {fromDni}, new Object[] {toDni}, filterClass, direction, numObjPerBuffer, DatosProfesionales.class);
    }

    // END of Matisse SDL Generated Code

    /*
     * You may add your own code here...
     */

    /**
     * Default constructor provided as an example.
     * You may delete this constructor or modify it to suit your needs. If you
     * modify it, please revise this comment accordingly.
     * @param db a database
     */
    public DatosProfesionales(com.matisse.MtDatabase db) {
        super(getClass(db));
    }

    /**
     * Example of <code>toString</code> method.
     * You may delete this method or modify it to suit your needs. If you
     * modify it, please revise this comment accordingly.
     * @return a string
     */
    public java.lang.String toString() {
        return super.toString() + "[DatosProfesionales]";
    }
}
=======
/*
 * DatosProfesionales.java
 *
 * Generated with Matisse Schema Definition Language 9.1.11
 * Generation date: Tue Apr 23 18:54:50 2019
 */

// Note: the package and extends declarations are generated by mt_sdl, do not modify them

package gest_proyectos;

import com.matisse.reflect.*;

/**
 * <code>DatosProfesionales</code> is a schema class generated by <code>mt_sdl</code>.
 * Any user-written classes will be found at the end of the file, after
 * the '// END of Matisse SDL Generated Code' comment.
 * Attribute types, default values, and relationship minimum and maximum
 * cardinality are stored in the database itself, not in this source code.
 * For more information, see <i>Getting Started with MATISSE</i>.
 */
public class DatosProfesionales extends com.matisse.reflect.MtObject {

    // BEGIN Matisse SDL Generated Code
    // DO NOT MODIFY UNTIL THE 'END of Matisse SDL Generated Code' MARK BELOW
    /*
     * Generated with Matisse Schema Definition Language 9.1.11
     * Generation Date: Tue Apr 23 18:54:50 2019
     */

    /*
     * Class variables and methods
     */

    /** Class <code>DatosProfesionales</code> cache ID */
    private static int CID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtClass.Loader("gest_proyectos.DatosProfesionales"));

    /**
     * Gets the <code>DatosProfesionales</code> class descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return a class descriptor
     */
    public static com.matisse.reflect.MtClass getClass(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtClass)db.getCachedObject(CID);
    }

    /**
     * Factory constructor. This constructor is called by <code>MtObjectFactory</code>.
     * It is public for technical reasons but is not intended to be called
     * directly by user methods.
     * @param db a database
     * @param mtOid an existing object ID in the database
     */
    public DatosProfesionales(com.matisse.MtDatabase db, int mtOid)  {
        super(db, mtOid);
    }

    /**
     * Cascaded constructor, used by subclasses to create a new object in the database.
     * It is protected for technical reasons but is not intended to be called
     * directly by user methods.
     * @param mtCls a class descriptor (the class to instantiate)
     */
    protected DatosProfesionales(com.matisse.reflect.MtClass mtCls)  {
        super(mtCls);
    }

    /**
     * Opens an iterator on all instances of this class (and its subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> instanceIterator(com.matisse.MtDatabase db) {
        return getClass(db).<E>instanceIterator(DatosProfesionales.class);
    }

    /**
     * Opens an iterator on all instances of this class (and its subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @param numObjPerBuffer maximum number of objects to fetch from the server at a time
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> instanceIterator(com.matisse.MtDatabase db, int numObjPerBuffer) {
        return getClass(db).<E>instanceIterator(numObjPerBuffer, DatosProfesionales.class);
    }

    /**
     * Counts the number of instances of this class (and its subclasses).
     * @param db a database
     * @return total number of instances
     */
    public static long getInstanceNumber(com.matisse.MtDatabase db) {
        return getClass(db).getInstanceNumber();
    }

    /**
     * Opens an iterator on all own instances of this class (excluding subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> ownInstanceIterator(com.matisse.MtDatabase db) {
        return getClass(db).<E>ownInstanceIterator(DatosProfesionales.class);
    }

    /**
     * Opens an iterator on all own instances of this class (excluding subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @param numObjPerBuffer maximum number of objects to fetch from the server at a time
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> ownInstanceIterator(com.matisse.MtDatabase db, int numObjPerBuffer) {
        return getClass(db).<E>ownInstanceIterator(numObjPerBuffer, DatosProfesionales.class);
    }

    /**
     * Counts the number of own instances of this class (excluding subclasses).
     * @param db a database
     * @return total number of instances
     */
    public static long getOwnInstanceNumber(com.matisse.MtDatabase db) {
        return getClass(db).getOwnInstanceNumber();
    }

    /*
     * Attribute access methods
     */

    /* Attribute 'dni' */

    /** Attribute <code>dni</code> cache ID */
    private static int dniCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("dni",CID));

    /**
     * Gets the <code>dni</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getDniAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(dniCID);
    }


    /**
     * Gets the <code>dni</code> attribute value.
     * @return the value
     *
     * @see #setDni
     * @see #removeDni
     */
    public final String getDni() {
        return getString(getDniAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>dni</code> attribute value.
     * @param val the new value
     *
     * @see #getDni
     * @see #removeDni
     */
    public final void setDni(String val) {
        setString(getDniAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>dni</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getDni
     * @see #setDni
     */
    public final void removeDni() {
        removeValue(getDniAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getDni
     * @see #setDni
     */
    public final boolean isDniDefaultValue() {
        return isDefaultValue(getDniAttribute(getMtDatabase()));
    }


    /* Attribute 'categoria' */

    /** Attribute <code>categoria</code> cache ID */
    private static int categoriaCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("categoria",CID));

    /**
     * Gets the <code>categoria</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getCategoriaAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(categoriaCID);
    }


    /**
     * Gets the <code>categoria</code> attribute value.
     * @return the value
     *
     * @see #setCategoria
     * @see #removeCategoria
     */
    public final String getCategoria() {
        return getString(getCategoriaAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>categoria</code> attribute value.
     * @param val the new value
     *
     * @see #getCategoria
     * @see #removeCategoria
     */
    public final void setCategoria(String val) {
        setString(getCategoriaAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>categoria</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getCategoria
     * @see #setCategoria
     */
    public final void removeCategoria() {
        removeValue(getCategoriaAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getCategoria
     * @see #setCategoria
     */
    public final boolean isCategoriaDefaultValue() {
        return isDefaultValue(getCategoriaAttribute(getMtDatabase()));
    }


    /* Attribute 'sueldo_bruto_anual' */

    /** Attribute <code>sueldo_bruto_anual</code> cache ID */
    private static int sueldo_bruto_anualCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("sueldo_bruto_anual",CID));

    /**
     * Gets the <code>sueldo_bruto_anual</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getSueldo_bruto_anualAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(sueldo_bruto_anualCID);
    }


    /**
     * Gets the <code>sueldo_bruto_anual</code> attribute value.
     * @return the value
     *
     * @see #setSueldo_bruto_anual
     * @see #removeSueldo_bruto_anual
     */
    public final float getSueldo_bruto_anual() {
        return getFloat(getSueldo_bruto_anualAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>sueldo_bruto_anual</code> attribute value.
     * @param val the new value
     *
     * @see #getSueldo_bruto_anual
     * @see #removeSueldo_bruto_anual
     */
    public final void setSueldo_bruto_anual(float val) {
        setFloat(getSueldo_bruto_anualAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>sueldo_bruto_anual</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getSueldo_bruto_anual
     * @see #setSueldo_bruto_anual
     */
    public final void removeSueldo_bruto_anual() {
        removeValue(getSueldo_bruto_anualAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getSueldo_bruto_anual
     * @see #setSueldo_bruto_anual
     */
    public final boolean isSueldo_bruto_anualDefaultValue() {
        return isDefaultValue(getSueldo_bruto_anualAttribute(getMtDatabase()));
    }


    /*
     * Relationship access methods
     */

    /* Relationship 'datos_prof_de' */

    /** Relationship <code>datos_prof_de</code> cache ID */
    private static int datos_prof_deCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtRelationship.Loader("datos_prof_de",CID));

    /**
     * Gets the <code>datos_prof_de</code> relationship descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return a relationship descriptor object
     */
    public static com.matisse.reflect.MtRelationship getDatos_prof_deRelationship(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtRelationship)db.getCachedObject(datos_prof_deCID);
    }

    /**
     * Gets the <code>datos_prof_de</code> sucessor object.
     * @return an object 
     *
     * @see #setDatos_prof_de
     * @see #clearDatos_prof_de
     */
    public final gest_proyectos.Empleado getDatos_prof_de() {
        return (gest_proyectos.Empleado)getSuccessor(getDatos_prof_deRelationship(getMtDatabase()));
    }

    /**
     * Sets the <code>datos_prof_de</code> successor object. It is not necessary to clear the
     * relationship before setting a new successor.
     * @param succ the new successor object
     *
     * @see #getDatos_prof_de
     * @see #clearDatos_prof_de
     */
    public final void setDatos_prof_de(gest_proyectos.Empleado succ) {
        setSuccessor(getDatos_prof_deRelationship(getMtDatabase()), succ);
    }

    /**
     * Removes all <code>datos_prof_de</code> successors.  When the minimum cardinality
     * is 1, a new successor must be set before commit.
     */
    public final void clearDatos_prof_de() {
        clearSuccessors(getDatos_prof_deRelationship(getMtDatabase()));
    }


    /*
     * Index access methods
     */

    /* Index 'Empleado_DatosProf_pk' */

    /** Index <code>Empleado_DatosProf_pk</code> cache ID */
    private static int empleado_DatosProf_pkIndexCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtIndex.Loader("gest_proyectos.Empleado_DatosProf_pk"));

    /**
     * Gets the <code>Empleado_DatosProf_pk</code> index descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return an index descriptor object
     */
    public static com.matisse.reflect.MtIndex getEmpleado_DatosProf_pkIndex(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtIndex)db.getCachedObject(empleado_DatosProf_pkIndexCID);
    }

    /**
     * Finds one <code>DatosProfesionales</code> object in index <code>Empleado_DatosProf_pk</code>.
     * @param db a database
     * @param dni search parameter
     * @return the matching <code>DatosProfesionales</code> object or <code>null</code> if none was found
     */
    public static DatosProfesionales lookupEmpleado_DatosProf_pk(com.matisse.MtDatabase db, String dni) {
        return (DatosProfesionales)getEmpleado_DatosProf_pkIndex(db).lookup(new Object[] {dni}, getClass(db));
    }

    /**
     * Finds <code>DatosProfesionales</code> objects in index <code>Empleado_DatosProf_pk</code>.
     * @param db a database
     * @param dni search parameter
     * @return the matching <code>DatosProfesionales</code> objects or an empty array if none was found
     */
    public static DatosProfesionales[] lookupObjectsEmpleado_DatosProf_pk(com.matisse.MtDatabase db, String dni) {
        return (DatosProfesionales[])getEmpleado_DatosProf_pkIndex(db).lookupObjects(new Object[] {dni}, getClass(db), DatosProfesionales.class);
    }

    /**
     * Opens an iterator on index <code>Empleado_DatosProf_pk</code> for class <code>DatosProfesionales</code>.
     * Each indexed attribute has a pair of "from" and "to" parameters which
     * define the search range for that attribute.  With strings, you may use
     * <code>null</code> to start "from" at the beginning or extend "to" to the end.
     * @param <E> a MtObject class     * @param db a database
     * @param fromDni search parameter
     * @param toDni search parameter
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> empleado_DatosProf_pkIterator(com.matisse.MtDatabase db, String fromDni, String toDni) {
        return getEmpleado_DatosProf_pkIndex(db).<E>iterator(new Object[] {fromDni}, new Object[] {toDni}, getClass(db), com.matisse.reflect.MtIndex.DIRECT, com.matisse.MtDatabase.MAX_PREFETCHING, DatosProfesionales.class);
    }

    /**
     * Opens an iterator on index <code>Empleado_DatosProf_pk</code> for class <code>DatosProfesionales</code>. 
     * Each indexed attribute has a pair of "from" and "to" parameters which
     * define the search range for that attribute.  With strings, you may use
     * <code>null</code> to start "from" at the beginning or extend "to" to the end.
     * @param <E> a MtObject class     * @param db a database
     * @param fromDni search parameter
     * @param toDni search parameter
     * @param filterClass a subclass; use <code>null</code> not to restrict iterator to a subclass
     * @param direction MtIndex.DIRECT or MtIndex.REVERSE
     * @param numObjPerBuffer maximum number of objects to be retrieved in each request to server
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> empleado_DatosProf_pkIterator(com.matisse.MtDatabase db, String fromDni, String toDni, com.matisse.reflect.MtClass filterClass, int direction, int numObjPerBuffer) {
        return getEmpleado_DatosProf_pkIndex(db).iterator(new Object[] {fromDni}, new Object[] {toDni}, filterClass, direction, numObjPerBuffer, DatosProfesionales.class);
    }

    // END of Matisse SDL Generated Code

    /*
     * You may add your own code here...
     */

    /**
     * Default constructor provided as an example.
     * You may delete this constructor or modify it to suit your needs. If you
     * modify it, please revise this comment accordingly.
     * @param db a database
     */
    public DatosProfesionales(com.matisse.MtDatabase db) {
        super(getClass(db));
    }

    /**
     * Example of <code>toString</code> method.
     * You may delete this method or modify it to suit your needs. If you
     * modify it, please revise this comment accordingly.
     * @return a string
     */
    public java.lang.String toString() {
        return super.toString() + "[DatosProfesionales]";
    }
}
>>>>>>> origin/master
