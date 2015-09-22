package com.rom.ar.ardesign.utils.enumerator;


/**
 * Enumerador que contiene los paths y la configuracion de tracking de los modelos.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 *
 */
public enum ModelParams {

    PARED_LADRILLO(1L, "ARSources/DesignModels/VizAidModel/eje_coordenadas.obj", "ARSources/DesignModels/pared/pared_ladrillo.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_LADRILLO_B(2L, "ARSources/DesignModels/VizAidModel/eje_coordenadas.obj", "ARSources/DesignModels/pared/pared_ladrillo_b.obj", "ARSources/TrackingConfig/Tracking.xml");

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
