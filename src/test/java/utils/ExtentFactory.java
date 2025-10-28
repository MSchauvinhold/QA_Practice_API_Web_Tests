package utils;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {

    // Método para crear y configurar una instancia de ExtentReports
    public static ExtentReports getInstance() {
        ExtentReports extent = new ExtentReports();

        // Información básica del sistema que aparecerá en los reportes
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Navegador", "Chrome");
        extent.setSystemInfo("Ambiente", "QA");

        return extent;
    }
}

