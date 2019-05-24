package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBOGS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_ORGS = "BP-R-BRW-ORGS       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_F_TLR_QRY_BR_CHK = "BP-F-TLR-QRY-BR-CHK";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    BPZSBOGS_WS_ORGS_DETAIL WS_ORGS_DETAIL = new BPZSBOGS_WS_ORGS_DETAIL();
    int WS_TEMP_BR = 0;
    int WS_CNT = 0;
    char WS_SUB_ORG_FLAG = ' ';
    char WS_ORG_HEAD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRBOGS BPCRBOGS = new BPCRBOGS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRORGS BPRORGS = new BPRORGS();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPCSBOGS BPCSBOGS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBOGS BPCSBOGS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBOGS = BPCSBOGS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBOGS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCRBOGS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_BROWSE_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_BROWSE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBOGS);
        BPCRBOGS.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCRBOGS.BR = BPCSBOGS.BR;
        BPCRBOGS.ORG_STS = BPCSBOGS.ORG_STS;
        CEP.TRC(SCCGWA, BPCRBOGS.BNK);
        CEP.TRC(SCCGWA, BPCRBOGS.BR);
        CEP.TRC(SCCGWA, BPCRBOGS.ORG_STS);
        BPCRBOGS.FUNC = 'S';
        S000_CALL_BPZTBOGS();
        if (pgmRtn) return;
        BPCRBOGS.FUNC = 'R';
        S000_CALL_BPZTBOGS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRBOGS.BNK);
        CEP.TRC(SCCGWA, BPCRBOGS.BR);
        CEP.TRC(SCCGWA, BPCRBOGS.ORG_STS);
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        for (WS_CNT = 1; WS_CNT <= 5000 
            && BPCRBOGS.RTN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B010_02_CHOOSE_SUB_ORG();
            if (pgmRtn) return;
            if (WS_SUB_ORG_FLAG == 'Y') {
                IBS.init(SCCGWA, BPCFTLCM);
                BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCFTLCM.BR = BPCRBOGS.BR;
                S000_CALL_BPZFTLCM();
                if (pgmRtn) return;
                if (BPCFTLCM.AUTH_FLG == 'Y') {
                    B010_03_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, BPCRBOGS);
            BPCRBOGS.FUNC = 'R';
            S000_CALL_BPZTBOGS();
            if (pgmRtn) return;
        }
        BPCRBOGS.FUNC = 'E';
        S000_CALL_BPZTBOGS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_QRY_BR_CHK, BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 7;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 2;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_02_CHOOSE_SUB_ORG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBOGS.BR);
        CEP.TRC(SCCGWA, BPCRBOGS.BR);
        WS_SUB_ORG_FLAG = 'N';
        if (BPCSBOGS.BR != 0) {
            if (BPCSBOGS.BR == BPCRBOGS.BR) {
                WS_SUB_ORG_FLAG = 'Y';
            }
        } else {
            WS_SUB_ORG_FLAG = 'Y';
        }
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_ORGS_DETAIL.WS_ORGS_BR = BPCRBOGS.BR;
        WS_ORGS_DETAIL.WS_ORGS_STS = BPCRBOGS.ORG_STS;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_ORGS_DETAIL);
        SCCMPAG.DATA_LEN = 7;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTBOGS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORGS, BPCRBOGS);
        if (BPCRBOGS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBOGS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
