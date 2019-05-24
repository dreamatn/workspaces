package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSINHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_INQ_NHISD = "BP-U-INQ-NHISD";
    String WS_COMPONENT_NAME = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCUINHD BPCUINHD = new BPCUINHD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCSINHD BPCSINHD;
    public void MP(SCCGWA SCCGWA, BPCSINHD BPCSINHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSINHD = BPCSINHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSINHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSINHD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQ_NHIST_DETIAL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_NHIST_DETIAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSINHD);
        IBS.init(SCCGWA, BPCUINHD);
        BPCUINHD.INPUT.JRNNO = BPCSINHD.INPUT.JRNNO;
        BPCUINHD.INPUT.JRN_SEQ = BPCSINHD.INPUT.JRN_SEQ;
        BPCUINHD.INPUT.AC_DT = BPCSINHD.INPUT.AC_DT;
        S000_CALL_BPZUINHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "44444");
    }
    public void S000_CALL_BPZUINHD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-NHISD", BPCUINHD);
        CEP.TRC(SCCGWA, "22222");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUINHD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUINHD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSINHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "33333");
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
