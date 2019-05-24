package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQACNO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_SC_AC_DIGIT = "SC-AC-DIGIT";
    String CPN_CI_CISOACCU = "CI-CISOACCU";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ACNO_TEMP = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCKDGAC SCCKDGAC = new SCCKDGAC();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCCKAC BPCCKAC;
    public void MP(SCCGWA SCCGWA, BPCCKAC BPCCKAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCKAC = BPCCKAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQACNO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCKDGAC);
        IBS.init(SCCGWA, CICACCU);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INPUT DATA:");
        CEP.TRC(SCCGWA, BPCCKAC.ACNO);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CALL_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "OUTPUT DATA:");
        CEP.TRC(SCCGWA, BPCCKAC.ACNO);
        CEP.TRC(SCCGWA, BPCCKAC.PRD_CODE);
        CEP.TRC(SCCGWA, BPCCKAC.APP);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCCKAC.ACNO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPCCKAC.RC);
        }
    }
    public void B020_CALL_PROCESS() throws IOException,SQLException,Exception {
        S020_CALL_CISOACCU_PROCESS();
        if (pgmRtn) return;
    }
    public void S010_CALL_SCZKDGAC_PROCESS() throws IOException,SQLException,Exception {
        if (BPCCKAC.ACNO == null) BPCCKAC.ACNO = "";
        JIBS_tmp_int = BPCCKAC.ACNO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPCCKAC.ACNO += " ";
        WS_ACNO_TEMP = BPCCKAC.ACNO.substring(0, 2);
        CEP.TRC(SCCGWA, WS_ACNO_TEMP);
        if (!WS_ACNO_TEMP.equalsIgnoreCase("10")) {
            IBS.init(SCCGWA, SCCKDGAC);
            SCCKDGAC.NO = BPCCKAC.ACNO;
            CEP.TRC(SCCGWA, BPCCKAC.ACNO);
            CEP.TRC(SCCGWA, SCCKDGAC.NO);
            SCCKDGAC.FUNC = 'K';
            T010_CALL_SCZKDGAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCKDGAC.RC.RC_CODE);
            if (SCCKDGAC.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCKDGAC.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCKAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S020_CALL_CISOACCU_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPCCKAC.ACNO;
        T020_CALL_CISOACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCKAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            BPCCKAC.AC_TYPE = CICACCU.DATA.FRM_APP;
            BPCCKAC.BOOK_ORG = CICACCU.DATA.BBR;
            BPCCKAC.PRD_CODE = CICACCU.DATA.PROD_CD;
            BPCCKAC.APP = CICACCU.DATA.FRM_APP;
        }
    }
    public void T010_CALL_SCZKDGAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SC_AC_DIGIT, SCCKDGAC);
    }
    public void T020_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CI_CISOACCU, CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCKAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCKAC = ");
            CEP.TRC(SCCGWA, BPCCKAC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
