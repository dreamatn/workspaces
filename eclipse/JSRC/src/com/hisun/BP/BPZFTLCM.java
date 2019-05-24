package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTLCM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_F_TLR_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_F_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String WS_VIL_TYP1 = " ";
    String WS_VIL_TYP2 = " ";
    char WS_BR1_LVL = ' ';
    char WS_BR2_LVL = ' ';
    int WS_BRANCH_BR1 = 0;
    int WS_BRANCH_BR2 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTOT BPRTOT = new BPRTOT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPCFTLCM BPCFTLCM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFTLCM BPCFTLCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLCM = BPCFTLCM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTLCM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFTLCM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLCM.AUTH_FLG);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFTLCM.TLR.trim().length() == 0 
            && BPCFTLCM.BR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFTLCM.TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPCFTLCM.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            if (BPCFTLRQ.INFO.TLR_TYP != 'T') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLCM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCFTLCM.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCFTLCM.BR;
            S000_CALL_BPCPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            WS_VIL_TYP2 = BPCPQORG.VIL_TYP;
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            WS_BR2_LVL = BPCPQORG.LVL;
            WS_BRANCH_BR2 = BPCPQORG.BRANCH_BR;
            CEP.TRC(SCCGWA, WS_BR2_LVL);
            CEP.TRC(SCCGWA, WS_BRANCH_BR2);
        }
        CEP.TRC(SCCGWA, "NCB0601003");
        CEP.TRC(SCCGWA, BPCFTLCM.BR);
        CEP.TRC(SCCGWA, WS_BRANCH_BR2);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCFTLRQ.INFO.NEW_BR;
        S000_CALL_BPCPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
        WS_VIL_TYP1 = BPCPQORG.VIL_TYP;
        CEP.TRC(SCCGWA, BPCPQORG.LVL);
        WS_BR1_LVL = BPCPQORG.LVL;
        WS_BRANCH_BR1 = BPCPQORG.BRANCH_BR;
        CEP.TRC(SCCGWA, "NCB0601004");
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
        CEP.TRC(SCCGWA, WS_BR1_LVL);
        CEP.TRC(SCCGWA, WS_BRANCH_BR1);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
        CEP.TRC(SCCGWA, BPCFTLCM.BR);
        if (BPCFTLRQ.INFO.NEW_BR == BPCFTLCM.BR) {
            BPCFTLCM.AUTH_FLG = 'Y';
        } else {
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = BPCFTLRQ.INFO.TLR_BR;
            BPCPRGST.BR1 = BPCFTLRQ.INFO.NEW_BR;
            BPCPRGST.BR2 = BPCFTLCM.BR;
            CEP.TRC(SCCGWA, "NCB040903");
            CEP.TRC(SCCGWA, BPCPRGST.BR1);
            CEP.TRC(SCCGWA, BPCPRGST.BR2);
            S000_CALL_BPZPRGST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPRGST.FLAG);
            CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
            if (BPCPRGST.FLAG == 'N' 
                || BPCPRGST.LVL_RELATION == 'C') {
                BPCFTLCM.AUTH_FLG = 'N';
            } else {
                BPCFTLCM.AUTH_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, BPCFTLCM.AUTH_FLG);
        CEP.TRC(SCCGWA, WS_VIL_TYP1);
        CEP.TRC(SCCGWA, WS_VIL_TYP2);
        CEP.TRC(SCCGWA, WS_BR1_LVL);
        if (BPCFTLCM.AUTH_FLG != 'Y') {
            if (!WS_VIL_TYP1.equalsIgnoreCase(WS_VIL_TYP2)) {
                BPCFTLCM.AUTH_FLG = 'N';
            } else {
                if ((WS_VIL_TYP1.equalsIgnoreCase("00") 
                    && WS_BR1_LVL == '7') 
                    || (!WS_VIL_TYP1.equalsIgnoreCase("00") 
                    && WS_BR1_LVL == '3')) {
                    BPCFTLCM.AUTH_FLG = 'Y';
                } else {
                    BPCFTLCM.AUTH_FLG = 'N';
                }
            }
        }
        CEP.TRC(SCCGWA, "NCB040906");
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
        CEP.TRC(SCCGWA, BPCFTLCM.BR);
        CEP.TRC(SCCGWA, WS_BRANCH_BR1);
        CEP.TRC(SCCGWA, WS_BRANCH_BR2);
        CEP.TRC(SCCGWA, WS_BR1_LVL);
        CEP.TRC(SCCGWA, WS_BR2_LVL);
        CEP.TRC(SCCGWA, BPCFTLCM.AUTH_FLG);
        if ((WS_BRANCH_BR1 == WS_BRANCH_BR2 
            && WS_BR1_LVL > WS_BR2_LVL) 
            || BPCFTLRQ.INFO.NEW_BR == BPCFTLCM.BR 
            || WS_BR1_LVL > '6') {
            BPCFTLCM.AUTH_FLG = 'Y';
        } else {
            BPCFTLCM.AUTH_FLG = 'N';
        }
        CEP.TRC(SCCGWA, "NCB0601002");
        CEP.TRC(SCCGWA, BPCFTLCM.AUTH_FLG);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPCPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLCM = ");
            CEP.TRC(SCCGWA, BPCFTLCM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
