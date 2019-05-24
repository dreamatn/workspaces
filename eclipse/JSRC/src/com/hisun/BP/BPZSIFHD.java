package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIFHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_INQ_FHISD = "BP-U-INQ-FHISD";
    String WS_COMPONENT_NAME = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCUIFHD BPCUIFHD = new BPCUIFHD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCSIFHD BPCSIFHD;
    public void MP(SCCGWA SCCGWA, BPCSIFHD BPCSIFHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIFHD = BPCSIFHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIFHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSIFHD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQ_FHIST_DETIAL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_FHIST_DETIAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIFHD);
        IBS.init(SCCGWA, BPCUIFHD);
        BPCUIFHD.INPUT.JRNNO = BPCSIFHD.INPUT.JRNNO;
        BPCUIFHD.INPUT.JRN_SEQ = BPCSIFHD.INPUT.JRN_SEQ;
        BPCUIFHD.INPUT.AC_DT = BPCSIFHD.INPUT.AC_DT;
        S000_CALL_BPZUIFHD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUIFHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_INQ_FHISD, BPCUIFHD);
        CEP.TRC(SCCGWA, BPCUIFHD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIFHD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIFHD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSIFHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
