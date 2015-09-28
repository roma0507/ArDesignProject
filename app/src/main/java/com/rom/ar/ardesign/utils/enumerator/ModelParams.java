package com.rom.ar.ardesign.utils.enumerator;


import com.rom.ar.ardesign.utils.Util;

/**
 * Enumerador que contiene los paths y la configuracion de tracking de los modelos.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 *
 */
public enum ModelParams {

    PARED_LADRILLO_MACIZO(1L, Util.PATH_EJE_XYZ_PARED_MODEL, "ARSources/DesignModels/pared/pared_ladrillo_macizo.obj", Util.PATH_EJE_XYZ_PARED_TRACKING),

    PARED_LADRILLO_SEGURIDAD(2L, Util.PATH_EJE_XYZ_PARED_MODEL, "ARSources/DesignModels/pared/pared_ladrillo_seguridad.obj", Util.PATH_EJE_XYZ_PARED_TRACKING),

    PARED_LADRILLO_A(3L, Util.PATH_EJE_XYZ_PARED_MODEL, "ARSources/DesignModels/pared/pared_ladrillo_a.obj", Util.PATH_EJE_XYZ_PARED_TRACKING),

    PARED_ESTUCO_A(4L, Util.PATH_EJE_XYZ_PARED_MODEL, "ARSources/DesignModels/pared/pared_estuco_a.obj", Util.PATH_EJE_XYZ_PARED_TRACKING),

    PARED_ESTUCO_B(5L, Util.PATH_EJE_XYZ_PARED_MODEL, "ARSources/DesignModels/pared/pared_estuco_b.obj", Util.PATH_EJE_XYZ_PARED_TRACKING),

    PARED_PIEDRA_A(6L, Util.PATH_EJE_XYZ_PARED_MODEL, "ARSources/DesignModels/pared/pared_piedra_a.obj", Util.PATH_EJE_XYZ_PARED_TRACKING),

    PARED_PIEDRA_B(7L, Util.PATH_EJE_XYZ_PARED_MODEL, "ARSources/DesignModels/pared/pared_piedra_b.obj", Util.PATH_EJE_XYZ_PARED_TRACKING),

    PISO_ALFOMBRA_A(8L, Util.PATH_EJE_XYZ_PISO_MODEL, "ARSources/DesignModels/piso/piso_alfombra_a.obj", Util.PATH_EJE_XYZ_PISO_TRACKING),

    PISO_ALFOMBRA_B(9L, Util.PATH_EJE_XYZ_PISO_MODEL, "ARSources/DesignModels/piso/piso_alfombra_b.obj", Util.PATH_EJE_XYZ_PISO_TRACKING),

    PISO_MADERA_A(10L, Util.PATH_EJE_XYZ_PISO_MODEL, "ARSources/DesignModels/piso/piso_madera_a.obj", Util.PATH_EJE_XYZ_PISO_TRACKING),

    PISO_MADERA_B(11L, Util.PATH_EJE_XYZ_PISO_MODEL, "ARSources/DesignModels/piso/piso_madera_b.obj", Util.PATH_EJE_XYZ_PISO_TRACKING),

    PISO_MADERA_C(12L, Util.PATH_EJE_XYZ_PISO_MODEL, "ARSources/DesignModels/piso/piso_madera_c.obj", Util.PATH_EJE_XYZ_PISO_TRACKING),

    PISO_TABLETA_A(13L, Util.PATH_EJE_XYZ_PISO_MODEL, "ARSources/DesignModels/piso/piso_tableta_a.obj", Util.PATH_EJE_XYZ_PISO_TRACKING),

    TECHO_CONCRETO(14L, Util.PATH_EJE_XYZ_TECHO_MODEL, "ARSources/DesignModels/techo/techo_concreto.obj", Util.PATH_EJE_XYZ_TECHO_TRACKING),

    TECHO_CONCRETO_B(15L, Util.PATH_EJE_XYZ_TECHO_MODEL, "ARSources/DesignModels/techo/techo_concreto_b.obj", Util.PATH_EJE_XYZ_TECHO_TRACKING),

    TECHO_CONCRETO_C(16L, Util.PATH_EJE_XYZ_TECHO_MODEL, "ARSources/DesignModels/techo/techo_concreto_c.obj", Util.PATH_EJE_XYZ_TECHO_TRACKING),

    TECHO_GRAFICO(17L, Util.PATH_EJE_XYZ_TECHO_MODEL, "ARSources/DesignModels/techo/techo_grafico.obj", Util.PATH_EJE_XYZ_TECHO_TRACKING),

    TECHO_MADERA(18L, Util.PATH_EJE_XYZ_TECHO_MODEL, "ARSources/DesignModels/techo/techo_madera.obj", Util.PATH_EJE_XYZ_TECHO_TRACKING),

    TECHO_MARTILLADO(19L, Util.PATH_EJE_XYZ_TECHO_MODEL, "ARSources/DesignModels/techo/techo_martillado.obj", Util.PATH_EJE_XYZ_TECHO_TRACKING),

    TECHO_TABLETA(20L, Util.PATH_EJE_XYZ_TECHO_MODEL, "ARSources/DesignModels/techo/techo_tableta.obj", Util.PATH_EJE_XYZ_TECHO_TRACKING);


    /**
     * Id del modelo.
     */
    private final Long id;

    /**
     * Path del modelo.
     */
    private final String strPerfilPath;

    /**
     * Path del modelo.
     */
    private final String strModelPath;

    /**
     * Archivo de configuracion del traking para el modelo.
     */
    private final String strTrackingConfig;

    /**
     * Constructor de la clase.
     * @param id
     * @param strPerfilPath
     * @param strModelPath
     * @param strTrackingConfig
     */
    ModelParams(Long id, String strPerfilPath, String strModelPath, String strTrackingConfig) {
        this.id = id;
        this.strPerfilPath = strPerfilPath;
        this.strModelPath = strModelPath;
        this.strTrackingConfig = strTrackingConfig;
    }

    public Long getId() {
        return id;
    }

    public String getStrPerfilPath() {
        return strPerfilPath;
    }

    public String getStrModelPath() {
        return strModelPath;
    }

    public String getStrTrackingConfig() {
        return strTrackingConfig;
    }

    public static ModelParams fromId(final Long id) {
        for (ModelParams m : ModelParams.values()) {
            if (m.id.equals(id))
                return m;
        }
        return null;
    }
}
