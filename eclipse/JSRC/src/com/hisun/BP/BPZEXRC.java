package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZEXRC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_R_TQPH_B = "BP-R-TQPH-B      ";
    String CPN_R_TQPH_M = "BP-R-TQPH-M      ";
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_FWD_TENOR = " ";
    double WS_QTP_NEW = 0;
    double WS_QTP_OLD = 0;
    short WS_READ_CNT = 0;
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    BPCTQPHM BPCTQPHM = new BPCTQPHM();
    BPRTQPH BPRTQPH = new BPRTQPH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCCLEA BPCCLEA;
    public void MP(SCCGWA SCCGWA, BPCCLEA BPCCLEA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCLEA = BPCCLEA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZEXRC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B010_CLEAR_DATA();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!BPCCLEA.DATA.NO.equalsIgnoreCase("BPTTQPH")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TYPE_NOT_EXIT, BPCCLEA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CLEAR_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        BPRTQPH.KEY.EXP_DT = BPCCLEA.DATA.STOR_DATE;
        BPCRTQPH.INFO.FUNC = '8';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_DT);
            CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_TM);
            CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
            CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
            CEP.TRC(SCCGWA, BPRTQPH.KEY.BR);
            CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
            CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
            IBS.init(SCCGWA, BPCTQPHM);
            BPCTQPHM.INFO.FUNC = 'R';
            S000_CALL_BPZTQPHM();
            if (pgmRtn) return;
            BPCTQPHM.INFO.FUNC = 'D';
            S000_CALL_BPZTQPHM();
            if (pgmRtn) return;
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTTQPH() throws IOException,SQLException,Exception {
        BPCRTQPH.INFO.POINTER = BPRTQPH;
        BPCRTQPH.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH_B, BPCRTQPH);
        if (BPCRTQPH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTQPH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCLEA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTQPHM() throws IOException,SQLException,Exception {
        BPCTQPHM.INFO.POINTER = BPRTQPH;
        BPCTQPHM.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH_M, BPCTQPHM);
        if (BPCTQPHM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTQPHM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCLEA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCLEA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCLEA = ");
            CEP.TRC(SCCGWA, BPCCLEA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
