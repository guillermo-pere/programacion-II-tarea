module ni.edu.uni.fcys.programacion2.conversionfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ni.edu.uni.fcys.programacion2.conversionfx to javafx.fxml;
    exports ni.edu.uni.fcys.programacion2.conversionfx;
    exports ni.edu.uni.fcys.programacion2.conversionfx.Controller;
}
