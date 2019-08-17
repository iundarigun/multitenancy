package br.com.devcave.multitenancy.tenant;

public class Tenant {
    private static ThreadLocal<String> tenant = new ThreadLocal<>();

    public static void setIdentificador(String tenantId) {
        tenant.set(tenantId);
    }

    public static String getIdentificador() {
        return tenant.get();
    }
}
