package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFQARP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_ARP = "BP-R-MGM-ARP       ";
    char WS_IN_FLG = ' ';
    String WS_CHNL_NO = " ";
    char WS_TBL_ARP_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRARP BPRARP = new BPRARP();
    BPCRARP BPCRARP = new BPCRARP();
    SCCGWA SCCGWA;
    BPCFQARP BPCFQARP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFQARP BPCFQARP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFQARP = BPCFQARP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFQARP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRARP);
        IBS.init(SCCGWA, BPCRARP);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFQARP.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFQARP);
        if (BPCFQARP.ATH_RSN_CD.trim().length() == 0 
            || BPCFQARP.ATH_LVL_ORI1 == ' ' 
            || BPCFQARP.ATH_LVL_ORI2 == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FQARP_RSN_CD, BPCFQARP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_IN_FLG = 'O';
        IBS.init(SCCGWA, BPCRARP);
        IBS.init(SCCGWA, BPRARP);
        BPCRARP.INFO.FUNC = 'Q';
        BPRARP.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRARP.KEY.IN_FLG = WS_IN_FLG;
        BPRARP.KEY.SYS_MMO = SCCGWA.COMM_AREA.CHNL;
        BPRARP.KEY.TX_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPRARP.KEY.ATH_CD = BPCFQARP.ATH_RSN_CD;
        BPRARP.KEY.OATH_LVL1 = BPCFQARP.ATH_LVL_ORI1;
        BPRARP.KEY.OATH_LVL2 = BPCFQARP.ATH_LVL_ORI2;
        S000_CALL_BPZRARP();
        if (pgmRtn) return;
        if (BPCRARP.RETURN_INFO == 'N' 
            || (BPCRARP.RETURN_INFO == 'F' 
            && (BPRARP.EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
            || BPRARP.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE))) {
            WS_IN_FLG = 'I';
            WS_CHNL_NO = "IBS";
            IBS.init(SCCGWA, BPCRARP);
            IBS.init(SCCGWA, BPRARP);
            BPCRARP.INFO.FUNC = 'Q';
            BPRARP.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRARP.KEY.IN_FLG = WS_IN_FLG;
            BPRARP.KEY.SYS_MMO = WS_CHNL_NO;
            BPRARP.KEY.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPRARP.KEY.ATH_CD = BPCFQARP.ATH_RSN_CD;
            BPRARP.KEY.OATH_LVL1 = BPCFQARP.ATH_LVL_ORI1;
            BPRARP.KEY.OATH_LVL2 = BPCFQARP.ATH_LVL_ORI2;
            S000_CALL_BPZRARP();
            if (pgmRtn) return;
            if (BPCRARP.RETURN_INFO == 'N') {
                BPCFQARP.ATH_LVL_NEW1 = BPCFQARP.ATH_LVL_ORI1;
                BPCFQARP.ATH_LVL_NEW2 = BPCFQARP.ATH_LVL_ORI2;
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPRARP.EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                || BPRARP.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                BPCFQARP.ATH_LVL_NEW1 = BPCFQARP.ATH_LVL_ORI1;
                BPCFQARP.ATH_LVL_NEW2 = BPCFQARP.ATH_LVL_ORI2;
                Z_RET();
                if (pgmRtn) return;
            }
        }
        BPCFQARP.ATH_LVL_NEW1 = BPRARP.NATH_LVL1;
        BPCFQARP.ATH_LVL_NEW2 = BPRARP.NATH_LVL2;
    }
    public void S000_CALL_BPZRARP() throws IOException,SQLException,Exception {
        BPCRARP.INFO.POINTER = BPRARP;
        BPCRARP.INFO.LEN = 61;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_ARP, BPCRARP);
        if (BPCRARP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRARP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFQARP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFQARP.ATH_LVL_NEW1);
        CEP.TRC(SCCGWA, BPCFQARP.ATH_LVL_NEW2);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFQARP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFQARP = ");
            CEP.TRC(SCCGWA, BPCFQARP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
