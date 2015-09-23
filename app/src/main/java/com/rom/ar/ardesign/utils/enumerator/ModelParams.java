package com.rom.ar.ardesign.utils.enumerator;


/**
 * Enumerador que contiene los paths y la configuracion de tracking de los modelos.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 *
 */
public enum ModelParams {

    PARED_LADRILLO_MACIZO(1L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/pared/pared_ladrillo_macizo.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_LADRILLO_SEGURIDAD(2L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/pared/pared_ladrillo_seguridad.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_LADRILLO_A(3L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/pared/pared_ladrillo_a.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_ESTUCO_A(4L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/pared/pared_estuco_a.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_ESTUCO_B(5L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/pared/pared_estuco_b.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_PIEDRA_A(6L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/pared/pared_piedra_a.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_PIEDRA_B(7L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/pared/pared_piedra_b.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PISO_ALFOMBRA_A(8L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/piso/piso_alfombra_a.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PISO_ALFOMBRA_B(9L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/piso/piso_alfombra_b.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PISO_MADERA_A(10L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/piso/piso_madera_a.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PISO_MADERA_B(11L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/piso/piso_madera_b.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PISO_MADERA_C(12L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/piso/piso_madera_c.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PISO_TABLETA_A(13L, "ARSources/DesignModels/VizAidModel/eje_xyz.obj", "ARSources/DesignModels/piso/piso_tableta_a.obj", "ARSources/TrackingConfig/Tracking.xml");



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
