package org.csu.softwaremetric.service;

import org.csu.softwaremetric.entity.UFC;
import org.csu.softwaremetric.entity.VAF;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class FPService {
    private DecimalFormat df = new DecimalFormat("#.0000");

    public int calUFC(UFC ufc) {
        int ei = ufc.getEI1() * 3 + ufc.getEI2() * 4 + ufc.getEI3() * 6;
        int eq = ufc.getEQ1() * 4 + ufc.getEQ2() * 5 + ufc.getEQ3() * 7;
        int eo = ufc.getEO1() * 3 + ufc.getEO2() * 4 + ufc.getEO3() * 6;
        int eif = ufc.getEIF1() * 5 + ufc.getEIF2() * 7 + ufc.getEIF3() * 10;
        int ilf = ufc.getILF1() * 7 + ufc.getILF2() * 10 + ufc.getILF3() * 15;
        return ei + eq + eo + eif + eif + ilf;
    }

    public double calVAF(VAF vaf) {
        int f = vaf.getRecovery() + vaf.getCommunication() + vaf.getDistribute() + vaf.getPerformance() + vaf.getOperation()
                + vaf.getOnline() + vaf.getMultiple() + vaf.getUpdate() + vaf.getComplex() + vaf.getInterComplex()
                + vaf.getInstallation() + vaf.getReuse() + vaf.getFacilitation() * vaf.getMultiplePoints();
        return Double.parseDouble(df.format(0.65 + 0.01 * f));
    }

    public double calFP(UFC ufc, VAF vaf) {
        int u = calUFC(ufc);
        double v = calVAF(vaf);
        return Double.parseDouble(df.format(u * v));
    }
}
