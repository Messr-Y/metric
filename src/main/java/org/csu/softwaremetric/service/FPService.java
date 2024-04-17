package org.csu.softwaremetric.service;

import org.csu.softwaremetric.entity.UFC;
import org.csu.softwaremetric.entity.VAF;
import org.springframework.stereotype.Service;

@Service
public class FPService {
    public int calUFC(UFC ufc){
        int ei = ufc.getNumEI() * ufc.getEI();
        int eq = ufc.getNumEQ() * ufc.getEQ();
        int eo = ufc.getNumEO() * ufc.getEO();
        int eif = ufc.getNumEIF() * ufc.getEIF();
        int ilf = ufc.getNumILF() * ufc.getILF();
        return ei + eq + eo + eif + eif + ilf;
    }

    public double calVAF(VAF vaf){
        int f = vaf.getRecovery() + vaf.getCommunication() + vaf.getDistribute() + vaf.getPerformance() + vaf.getOperation()
                + vaf.getOnline() + vaf.getMultiple() + vaf.getUpdate() + vaf.getComplex() + vaf.getInterComplex()
                + vaf.getInstallation() + vaf.getReuse() + vaf.getFacilitation();
        return 0.65 + 0.01 * f;
    }

    public double calFP(UFC ufc, VAF vaf){
        int u = calUFC(ufc);
        double v = calVAF(vaf);
        return u * v;
    }
}
