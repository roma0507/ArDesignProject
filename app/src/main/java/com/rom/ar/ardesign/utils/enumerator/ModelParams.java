package com.rom.ar.ardesign.utils.enumerator;

public enum ModelParams {

    PARED_LADRILLO(1L, "ARSources/DesignModels/VizAidModel/eje_coordenadas.obj", "ARSources/DesignModels/pared/pared_ladrillo.obj", "ARSources/TrackingConfig/Tracking.xml"),

    PARED_LADRILLO_B(2L, "ARSources/DesignModels/VizAidModel/eje_coordenadas.obj", "ARSources/DesignModels/pared/pared_ladrillo_b.obj", "ARSources/TrackingConfig/Tracking.xml");

    private final Long id;
    private final String strPerfilPath;
    private final String strModelPath;
    private final String strTrackingConfig;

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
